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

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Field;
import gov.ca.water.shapelite.Segment;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.ShapefileException;
import gov.ca.water.shapelite.io.ShapefileReader;
import gov.ca.water.shapelite.io.ShapefileWriter;
import gov.ca.water.shapelite.toolbox.ComboParameter;
import gov.ca.water.shapelite.toolbox.InputFileParameter;
import gov.ca.water.shapelite.toolbox.OutputFileParameter;
import gov.ca.water.shapelite.toolbox.StringParameter;
import gov.ca.water.shapelite.toolbox.ToolBase;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class AverageElevation extends ToolBase {

  /**
   * The field length of the field length.
   */
  private static final int FIELD_LENGTH = 16;

  /**
   * The decimal count of the elevation field.
   */
  private static final int DECIMAL = 8;

  /**
   * The input file parameter for the input shapes.
   */
  private final InputFileParameter paramInputShapes;
  /**
   * A combo box parameter for storing the averaging type.
   */
  private final ComboParameter paramAverageType;
  /**
   * A StringParameter for the paramOutputFile field to store the average
   * elevation in.
   */
  private final StringParameter paramOutputField;
  /**
   * An OutputFileParameter for specifying the paramOutputFile file.
   */
  private final OutputFileParameter paramOutputFile;

  /**
   * The Average elevation.
   */
  public AverageElevation() {
    setName("Average Elevation");
    setDescription("Calculates the elevations of the vertices or line segments "
            + "and returns the average Z value.");
    this.setHelpText("This calculates the average Z value.  This average can"
            + " be calculated by shape or by vertex.  The input is desigend"
            + " to be a shapefile.");
    this.setCategory("Analysis");
    this.setHelpImageFilename("resources/AverageZ.png");

    paramInputShapes = new InputFileParameter();
    paramInputShapes.setParameterText(null);
    paramInputShapes.setParameterName("Input Shapefile");
    paramInputShapes.setExtension(".shp");
    paramInputShapes.setDescription("The Shapefile containing the lines to "
            + "extract from and classify.");
    paramInputShapes.setHelpText("The Shapefile containing the lines to extract"
            + " from and classify.");
    this.getParameters().add(paramInputShapes);

    paramAverageType = new ComboParameter();
    paramAverageType.setParameterName("Averaging Type");
    paramAverageType.getOptions().add("Line");
    paramAverageType.getOptions().add("Vertex");
    paramAverageType.setParameterText("Line");
    paramAverageType.setDescription("Specifies whether average is calculated "
            + "by the line dimensions, or by vertices.");
    paramAverageType.setDescription("Specifies whether average is calculated "
            + "by the line dimensions, or by vertices.  If line is selected, "
            + "then point and multipoint shapefiles are calculated as the "
            + "average of vertex values, while line and polygon shapefiles "
            + "are calculated as the weighted average of segment elevations.");
    this.getParameters().add(paramAverageType);

    paramOutputField = new StringParameter();
    paramOutputField.setParameterName("Output Field");
    paramOutputField.setDescription("Specifies the attribute table field to "
            + "store the average Z value in.");
    paramOutputField.setHelpText("Specifies the attribute table field to store"
            + " the average Z value in.");
    paramOutputField.setParameterText("AverageZ");
    this.getParameters().add(paramOutputField);

    paramOutputFile = new OutputFileParameter();
    paramOutputFile.setParameterText(null);
    paramOutputFile.setExtension(".shp");
    paramOutputFile.setParameterName("Output Shapefile with average z field");
    paramOutputFile.setDescription("The Shapefile output file to store the "
            + "shape with average z field.");
    paramOutputFile.setHelpText("The Shapefile output file to store the shape"
            + " with average z field.");
    this.getParameters().add(paramOutputFile);

  }

  /**
   * Run immediately.
   */
  @Override
  public final void runImmediately() {
    getProgress().create("Calculating Z values: "
            + paramInputShapes.getParameterName(),
            this.getCancellable());
    ShapefileReader shapeReader = new ShapefileReader();
    try {
      shapeReader.open(paramInputShapes.getParameterText());
    } catch (IOException ex) {
      Logger.getLogger(AverageElevation.class.getName()).log(
              Level.SEVERE, ex.getMessage(), ex);
      getMessageHandler().message("Average Elevation", ex.getMessage());
      getProgress().finish();
      return;
    }
    FeatureSet fs;
    try {
      fs = shapeReader.getFeatures();
      String fieldName = paramOutputField.getParameterText();
      fs.getFields().add(new Field(fieldName, FIELD_LENGTH, DECIMAL));
      int count = fs.getFeatures().size();
      getProgress().start(count);
      for (int i = 0; i < count; i++) {

        Feature f = fs.getFeatures().get(i);
        f.getAttributes().put(fieldName, "0");
        ShapeType shapeType = f.getShape().getShapeType();
        if (shapeType == ShapeType.NullShape) {
          continue;
        }
        if (shapeType != ShapeType.MultiPointZ
                && shapeType != ShapeType.PointZ
                && shapeType != ShapeType.PolyLineZ
                && shapeType != ShapeType.PolygonZ) {
          getMessageHandler().message("Average Elevation - Invalid Shapefile",
                  "The shapefile must have a Z ");
          return;
        }
        if ("Vertex".equals(paramAverageType.getParameterText())
                || shapeType == ShapeType.PointZ
                || shapeType == ShapeType.MultiPointZ) {
          double total = 0;
          if (f.getShape() != null && f.getShape().getCoordinates() != null
                  && f.getShape().getCoordinates().size() > 0) {
            for (Coord c : f.getShape().getCoordinates()) {
              total += c.get(Coord.Index.Z);
            }
            double avg = total / f.getShape().getCoordinates().size();
            f.getAttributes().put(fieldName, Double.toString(avg));
          }
        } else {
          double total = 0;
          double len = 0;
          if (f.getShape() != null && f.getShape().getSegments() != null
                  && f.getShape().getSegments().size() > 0) {
            for (Segment seg : f.getShape().getSegments()) {
              total += seg.length() * (seg.getP1().get(Coord.Index.Z)
                      + seg.getP2().get(Coord.Index.Z)) / 2.0;
              len += seg.length();
            }
            double avg = total / len;
            f.getAttributes().put(fieldName, Double.toString(avg));
          }
        }
        getProgress().progress(i);
      }
      ShapefileWriter writer = new ShapefileWriter();
      try {
        writer.write(fs, paramOutputFile.getParameterText());
      } catch (ShapefileException ex) {
        Logger.getLogger(AverageElevation.class.getName()).log(
                Level.SEVERE, ex.getMessage(), ex);
      }

    } catch (FileNotFoundException ex) {
      Logger.getLogger(AverageElevation.class.getName()).log(
              Level.SEVERE, ex.getMessage(), ex);
    }

    getProgress().finish();

  }

}
