/*
 * The MIT License
 *
 * Copyright 2015 hdunsford.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package gov.ca.water.shapelite.toolbox.tools;

import com.vividsolutions.jts.geom.Geometry;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Field;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.ShapefileException;
import gov.ca.water.shapelite.io.ShapefileReader;
import gov.ca.water.shapelite.io.ShapefileWriter;
import gov.ca.water.shapelite.toolbox.InputFileParameter;
import gov.ca.water.shapelite.toolbox.InputFolderParameter;
import gov.ca.water.shapelite.toolbox.OutputFolderParameter;
import gov.ca.water.shapelite.toolbox.ToolBase;
import gov.ca.water.shapelite.topology.Adapter;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ClassifyLines extends ToolBase {

  /**
   * The file parameter.
   */
  private final InputFileParameter paramInputLines;

  /**
   * The input folder with polygons to use.
   */
  private final InputFolderParameter paramInputPolygons;

  /**
   * The paramOutputFolder folder.
   */
  private final OutputFolderParameter paramOutputFolder;

  /**
   * Creates a new instance of the classify lines tool.
   */
  public ClassifyLines() {
    setName("Classify Lines");
    setDescription("Extract Lines by Intersecting with Polygons");
    this.setHelpText("This tool calculates the intersection between all the "
            + "lines in the line shapefile and each polygon from the polygon"
            + " shapefile.  The fields are combined, with the fields from the "
            + "original line file starting.");
    this.setCategory("Analysis");
    this.setHelpImageFilename("resources/ClassifyLines.png");

    paramInputLines = new InputFileParameter();
    paramInputLines.setParameterName("Input Line Shapefile");
    paramInputLines.setExtension(".shp");
    paramInputLines.setDescription("The Shapefile containing the lines to extract "
            + "from and classify.");
    paramInputLines.setHelpText("The Shapefile containing the lines to extract"
            + " from and classify.");
    this.getParameters().add(paramInputLines);

    paramInputPolygons = new InputFolderParameter();
    paramInputPolygons.setParameterName("Input Folder with Poylgon Shapefiles");
    paramInputPolygons.setDescription("The Shapefile containing the polygons to"
            + " intersect with the lines.");
    paramInputPolygons.setHelpText("The Shapefile containing the polygons to "
            + "intersect with the lines.");
    this.getParameters().add(paramInputPolygons);

    paramOutputFolder = new OutputFolderParameter();
    paramOutputFolder.setParameterName("Output Folder for Line Shapefiles");
    paramOutputFolder.setDescription("The Shapefile output folder to store the "
            + "shapefiles with extracted lines.");
    paramOutputFolder.setHelpText("The Shapefile output folder to store the "
            + "shapefiles with extracted lines.");
    this.getParameters().add(paramOutputFolder);

  }

  /**
   * Run immediately.
   */
  @Override
  public final void runImmediately() {
    getProgress().create("Intersecting: "
            + paramInputLines.getParameterName(), this.getCancellable());
    ShapefileReader lineReader = new ShapefileReader();
    try {
      lineReader.open(paramInputLines.getParameterText());
    } catch (IOException ex) {
      Logger.getLogger(ClassifyLines.class.getName()).log(
              Level.SEVERE, ex.getMessage(), ex);
      getMessageHandler().message("Classify Lines - Error:", ex.getMessage());
      getProgress().finish();
      return;
    }
    ShapefileReader polygonReader;
    List<Shape> lines = lineReader.getShapes();
    if (lines == null) {
      return;
    }
    Shape mergedLines = new Shape(ShapeType.PolyLine);
    for (Shape line : lines) {
      mergedLines.getParts().addAll(line.getParts());
    }
    Optional<Geometry> geom = Adapter.getGeometry(mergedLines);
    if (!geom.isPresent()) {
      return;
    }
    Geometry mergedLineGeom = geom.get();
    File folder = new File(paramInputPolygons.getParameterText());
    File[] files = folder.listFiles(new FilenameFilter() {

      @Override
      public boolean accept(File dir, String name) {
        return name.endsWith(".shp");
      }
    });
    int count = files.length;
    getProgress().start(count);
    for (int i = 0; i < files.length; i++) {
      polygonReader = new ShapefileReader();
      try {
        polygonReader.open(files[i].toString());
        FeatureSet polygons = polygonReader.getFeatures();
        if (polygons == null) {
          continue;
        }
        FeatureSet result = new FeatureSet();
        result.setProjectionESRI(lineReader.getProjection());
        List<Field> resultFields = result.appendCopiedFields(polygons.getFields());
        for (Feature feature : polygons.getFeatures()) {
          Optional<Geometry> polygonGeom = Adapter.getGeometry(feature.getShape());
          if (polygonGeom.isPresent()) {
            Geometry intersection = mergedLineGeom.intersection(polygonGeom.get());
            Shape resultLines = Adapter.getShape(intersection, false, false);
            if (resultLines != null) {
              Feature resultLineFeature = new Feature();
              resultLineFeature.setShape(resultLines);
              for (int iField = 0; iField < resultFields.size(); iField++) {
                String sourceName = polygons.getFields().get(iField).getName();
                String value = feature.getAttributes().get(sourceName);
                String destName = resultFields.get(iField).getName();
                resultLineFeature.getAttributes().put(destName, value);
              }
              resultLineFeature.copyAttributes(feature.getAttributesCopy());
              result.getFeatures().add(resultLineFeature);
            }
          }

        }
        ShapefileWriter writer = new ShapefileWriter();
        String file = files[i].getName();
        String fileBase = file.substring(0, file.lastIndexOf("."));
        String outFile = paramOutputFolder.getParameterText() + File.separator
                + fileBase + "_Routes.shp";
        writer.write(result, outFile);
      } catch (IOException | ShapefileException ex) {
        Logger.getLogger(ClassifyLines.class.getName()).log(
                Level.SEVERE, ex.getMessage(), ex);
      }
      String polygonFile = files[i].getName();
      getProgress().progress(polygonFile);
      getProgress().progress(i);
    }
    getProgress().finish();

  }

}
