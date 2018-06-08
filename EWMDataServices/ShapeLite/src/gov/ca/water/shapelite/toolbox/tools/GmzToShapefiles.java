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

import gov.ca.water.shapelite.io.FeatureModifierXS;
import gov.ca.water.shapelite.io.GmlToShapefile;
import gov.ca.water.shapelite.io.LinestringSimplifier;
import gov.ca.water.shapelite.toolbox.InputFileParameter;
import gov.ca.water.shapelite.toolbox.OutputFolderParameter;
import gov.ca.water.shapelite.toolbox.ProjectionParameter;
import gov.ca.water.shapelite.toolbox.ToolBase;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class GmzToShapefiles extends ToolBase {

  /**
   * The length of a three character extension.
   */
  private static final int EXT_LENGTH = 4;

  /**
   * A Parameter for the input GMZ file.
   */
  private final InputFileParameter paramInputGmzFile;

  /**
   * A projection parameter.
   */
  private final ProjectionParameter paramProjectionParam;

  /**
   * An output folder parameter.
   */
  private final OutputFolderParameter paramOutput;

  /**
   * Creates a new instance of the GmzToShapefiles tool.
   */
  public GmzToShapefiles() {
    setName("GMZ To Shapefile");
    setDescription("Extract Lines by Intersecting with Polygons");
    this.setHelpText("This tool extracts the single GMZ file to form several"
            + " shapefiles that are within the GMZ file.");
    this.setCategory("DSS");
    //this.setHelpImageFilename("resources/ClassifyLines.png");

    paramInputGmzFile = new InputFileParameter();
    paramInputGmzFile.setParameterName("Input GMZ file to read");
    paramInputGmzFile.setExtension(".gmz");
    paramInputGmzFile.setDescription("The GMZ geometry file containing geometry"
            + " content.");
    paramInputGmzFile.setHelpText("The GMZ geometry file containing geometry"
            + " content.");
    this.getParameters().add(paramInputGmzFile);

    paramProjectionParam = new ProjectionParameter();
    paramProjectionParam.setParameterName("Projection");
    paramInputGmzFile.setDescription("The projection of the gmz file.");
    paramInputGmzFile.setHelpText("The projection of the gmz file.");
    this.getParameters().add(paramProjectionParam);

    paramOutput = new OutputFolderParameter();
    paramOutput.setParameterName("Output Folder");
    paramOutput.setDescription("The output folder to store the extracted"
            + " shapefiles in.");
    paramOutput.setHelpText("The output folder to store the extracted"
            + " shapefiles in.");
    this.getParameters().add(paramOutput);
  }

  /**
   * Run immediately.
   */
  @Override
  public final void runImmediately() {

    getProgress().create("Gmz to Shapefiles", this.getCancellable());
    getProgress().start();

    try {
      GmlToShapefile gmlExport = new GmlToShapefile();
      gmlExport.setProjection(paramProjectionParam.getProjection().toEsriString());
      ZipFile zipFile = new ZipFile(paramInputGmzFile.getParameterText());
      Enumeration<? extends ZipEntry> entries = zipFile.entries();
      while (entries.hasMoreElements()) {
        ZipEntry entry = entries.nextElement();
        InputStream stream = zipFile.getInputStream(entry);
        if (entry.getName().endsWith(".gml")) {
          String shp = entry.getName().substring(0, entry.getName().length()
                  - EXT_LENGTH) + ".shp";
          getProgress().progress(shp);
          String shpPath = paramOutput.getParameterText() + File.separator + shp;
          if ("XS.gml".equals(entry.getName())) {
            gmlExport.setCustomHandler(new FeatureModifierXS());
            gmlExport.convert(stream, shpPath);
            gmlExport.setCustomHandler(null);
          } else {
            gmlExport.convert(stream, shpPath);
          }
        }
        if ("XS.gml".equals(entry.getName())) {
          stream = zipFile.getInputStream(entry);
          String shp = "XS_2D.shp";
          getProgress().progress(shp);
          String shpPath = paramOutput.getParameterText() + File.separator + shp;
          // Create a flat, simplified 2D representation for display.
          gmlExport.setCustomHandler(new LinestringSimplifier());
          gmlExport.convert(stream, shpPath);
          gmlExport.setCustomHandler(null);
        }
        if (this.isCancelled()) {

          break;
        }
      }
    } catch (IOException | ParserConfigurationException | SAXException ex) {
      Logger.getLogger(GmzToShapefiles.class.getName()).log(Level.SEVERE,
              ex.getMessage(), ex);
    } finally {
      getProgress().finish();
    }
  }

}
