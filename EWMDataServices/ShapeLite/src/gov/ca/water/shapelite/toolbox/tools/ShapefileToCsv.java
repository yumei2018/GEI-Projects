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

import com.opencsv.CSVWriter;
import gov.ca.water.shapelite.Field;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.io.AttributeTableScanner;
import gov.ca.water.shapelite.io.ShapefileChannelScanner;
import gov.ca.water.shapelite.io.ShapefileWriter;
import gov.ca.water.shapelite.toolbox.InputFileParameter;
import gov.ca.water.shapelite.toolbox.OutputFileParameter;
import gov.ca.water.shapelite.toolbox.ToolBase;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ShapefileToCsv extends ToolBase {

  /**
   * Field offset.
   */
  private static final int FIELD_OFFSET = 3;

  /**
   * Input point shapes.
   */
  private final InputFileParameter paramInputPointShapes;
  /**
   * Output file parameter.
   */
  private final OutputFileParameter paramOutputCsv;
  /**
   * A logger.
   */
  private static final Logger LOGGER = Logger.getLogger(
          ShapefileToCsv.class.getName());

  /**
   * Creates a new instance of a Shapefile CSV.
   */
  public ShapefileToCsv() {
    setName("Shapefile to CSV");
    setDescription("This cycles through the shapes in the shapefile, turns the"
            + " attributes into CSV rows.  This adds an X, Y for the points, "
            + "or XMIN, XMAX, YMIN, YMAX for other shapes.");
    this.setHelpText("This cycles through the shapes in the shapefile, turns "
            + "the attributes into CSV rows.  This adds an X, Y for the points, "
            + "or XMIN, XMAX, YMIN, YMAX for other shapes.");
    this.setCategory("IO");
    this.setHelpImageFilename("resources/ShapeToCsv.png");

    paramInputPointShapes = new InputFileParameter();
    paramInputPointShapes.setParameterName("Point Shapefile");
    paramInputPointShapes.setExtension(".shp");
    paramInputPointShapes.setDescription("The Shapefile containing the points "
            + "to export.");
    paramInputPointShapes.setHelpText("The Shapefile containing the bank lines "
            + "of the river.  These can be created using the GmzToShapefiles "
            + "tool.");
    this.getParameters().add(paramInputPointShapes);

    paramOutputCsv = new OutputFileParameter();
    paramOutputCsv.setParameterName("Output CSV file");
    paramOutputCsv.setExtension(".csv");
    paramOutputCsv.setDescription("The output csv to store the extracted"
            + " attributes.");
    paramOutputCsv.setHelpText("The output shapefile store the extracted "
            + "attributes.");
    this.getParameters().add(paramOutputCsv);
  }

  /**
   * This allows the tool to be executed directly from code.
   *
   * @param bankLineShapefile The bank line shapefile.
   * @param outputShapefile The output shapefile.
   */
  public final void runImmediately(String bankLineShapefile, String outputShapefile) {
    paramInputPointShapes.setParameterText(bankLineShapefile);
    paramOutputCsv.setParameterText(outputShapefile);
  }

  /**
   * Run immediately.
   */
  @Override
  public final void runImmediately() {
    getProgress().create("Find Bank Intersections", this.getCancellable());

    String name = paramInputPointShapes.getParameterText();
    try (ShapefileChannelScanner shapefile = new ShapefileChannelScanner(name);
            CSVWriter writer = new CSVWriter(
                    new FileWriter(paramOutputCsv.getParameterText()));
            FileInputStream stream = new FileInputStream(
                    ShapefileWriter.setExtension(name, ".dbf"))) {
      AttributeTableScanner dbf = new AttributeTableScanner(stream);

      int numCols = dbf.getFields().size() + FIELD_OFFSET;
      String[] headerLine = new String[numCols];
      headerLine[0] = "FID";
      headerLine[1] = "X";
      headerLine[2] = "Y";
      for (int i = 0; i < dbf.getFields().size(); i++) {
        Field fld = dbf.getFields().get(i);
        headerLine[FIELD_OFFSET + i] = fld.getName();
      }
      writer.writeNext(headerLine);
      getProgress().start(shapefile.getNumShapes());
      for (int row = 0; row < dbf.getNumRecords(); row++) {
        Shape shape = shapefile.getShapes(row, 1).get(0);
        String[] line = new String[numCols];
        line[0] = Integer.toString(row);
        line[1] = Double.toString(shape.getX());
        line[2] = Double.toString(shape.getY());
        Optional<String[]> atts = dbf.getNextRow();
        if (atts.isPresent()) {
          for (int i = 0; i < atts.get().length; i++) {
            line[i + FIELD_OFFSET] = atts.get()[i].replace("\r\n", "");
          }
        }
        writer.writeNext(line);
        getProgress().progress(row);
        getProgress().progress("Row " + row);
      }
    } catch (Exception ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
    }

    getProgress().finish();

  }

}
