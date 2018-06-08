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

import com.opencsv.CSVReader;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.Field;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.FieldType;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.ShapefileException;
import gov.ca.water.shapelite.events.ParameterChangedEvent;
import gov.ca.water.shapelite.io.ShapefileWriter;
import gov.ca.water.shapelite.toolbox.ComboParameter;
import gov.ca.water.shapelite.toolbox.InputFileParameter;
import gov.ca.water.shapelite.toolbox.OutputFileParameter;
import gov.ca.water.shapelite.toolbox.ToolBase;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class CsvToShapefile extends ToolBase {

  /**
   * The maximum number of characters that can exist in a field.
   */
  private static final int MAX_FIELD_SIZE = 255;

  /**
   * Input point shapes.
   */
  private final InputFileParameter paramInputCsv;
  /**
   * Output file parameter.
   */
  private final OutputFileParameter paramOutputPoints;

  /**
   * Parameter for entering the name of CSV Field storing X coordinates.
   */
  private final ComboParameter paramX;

  /**
   * Parameter for entering the name of CSV Field storing Y coordinates.
   */
  private final ComboParameter paramY;

  /**
   * Parameter for entering the name of CSV Field storing Z coordinates.
   */
  private final ComboParameter paramZ;

  /**
   * A logger.
   */
  private static final Logger LOGGER = Logger.getLogger(CsvToShapefile.class.getName());

  /**
   * Creates a new instance of a Shapefile CSV.
   */
  public CsvToShapefile() {
    setName("CSV to Shapefile");
    setDescription("This cycles through the rows in the csv and turns the"
        + " field values into attributes.  This uses the X, Y, and optional "
        + "Z to generate the point shapes.");
    this.setHelpText("This cycles through the rows in the csv and turns the"
        + " field values into attributes.  This uses the X, Y, and optional "
        + "Z to generate the point shapes.");
    this.setCategory("IO");
    this.setHelpImageFilename("resources/CsvToShape.png");

    paramInputCsv = new InputFileParameter();
    paramInputCsv.setParameterName("CSV File");
    paramInputCsv.setExtension(".csv");
    paramInputCsv.setDescription("The Shapefile containing the points "
        + "to export.");
    paramInputCsv.setHelpText("The Shapefile containing the bank lines "
        + "of the river.  These can be created using the GmzToShapefiles "
        + "tool.");

    this.getParameters().add(paramInputCsv);

    paramX = new ComboParameter();
    paramX.setParameterName("X Field");
    paramX.setDescription("The Field storing the X parameter or longitude.");
    paramX.setHelpText("The X field storing the X parameter or longitude.");
    paramX.setRequired(true);

    this.getParameters().add(paramX);

    paramY = new ComboParameter();
    paramY.setParameterName("Y Field");
    paramY.setDescription("The Field storing the Y parameter, or latitude.");
    paramY.setHelpText("The Field storing the Y parameter, or latitude.");
    paramY.setRequired(true);
    this.getParameters().add(paramY);

    paramZ = new ComboParameter();
    paramZ.setParameterName("Z Field");
    paramZ.setDescription("The Field storing the Z parameter, or elevation.");
    paramZ.setHelpText("The Field storing the Z parameter, or elevation.");
    paramZ.setRequired(false);
    this.getParameters().add(paramZ);

    paramInputCsv.addParameterChangedListener(new ParameterChangedEvent.Listener() {
      @Override
      public void parameterChanged(ParameterChangedEvent e) {
        CSVReader reader;
        try {
          reader = new CSVReader(new FileReader(paramInputCsv.getParameterText()));
          String[] header = reader.readNext();
          paramX.getOptions().clear();
          paramX.setSelectedOption(null);
          paramY.getOptions().clear();
          paramY.setSelectedOption(null);
          paramZ.getOptions().clear();
          paramZ.setSelectedOption(null);
          for (String field : header) {
            if (field == null) {
              continue;
            }
            paramX.getOptions().add(field);
            paramY.getOptions().add(field);
            paramZ.getOptions().add(field);

            String lcField = field.toLowerCase();
            if ("x".equals(lcField) || "lng".equals(lcField)
                || "lon".equals(lcField) || "longitude".equals(lcField)) {
              paramX.setSelectedOption(field);
            }
            if ("y".equals(lcField) || "lat".equals(lcField)
                || "lattitude".equals(lcField)) {
              paramY.setSelectedOption(field);
            }
            if ("z".equals(lcField) || "elevation".equals(lcField)) {
              paramZ.setSelectedOption(field);
            }
          }
          reader.close();
          paramX.fireComboOptionsChanged();
          paramY.fireComboOptionsChanged();
          paramZ.fireComboOptionsChanged();
        } catch (Exception ex) {

        }
      }
    });

    paramOutputPoints = new OutputFileParameter();
    paramOutputPoints.setParameterName("Output Shape file");
    paramOutputPoints.setExtension(".shp");
    paramOutputPoints.setDescription("The output csv to store the extracted"
        + " attributes.");
    paramOutputPoints.setHelpText("The output shapefile store the extracted "
        + "attributes.");
    this.getParameters().add(paramOutputPoints);
  }

  /**
   * This allows the tool to be executed directly from code.
   *
   * @param bankLineShapefile The bank line shapefile.
   * @param outputShapefile The output shapefile.
   */
  public final void runImmediately(String bankLineShapefile, String outputShapefile) {
    paramInputCsv.setParameterText(bankLineShapefile);
    paramOutputPoints.setParameterText(outputShapefile);
  }

  /**
   * Run immediately.
   */
  @Override
  public final void runImmediately() {
    getProgress().create("Converting CSV to Shapefile...", this.getCancellable());
    File file = new File(paramInputCsv.getParameterText());
    if (!file.exists()) {
      getMessageHandler().message("File not found", "The input file "
          + paramInputCsv.getParameterText() + " could not be found.");
    }
    long fileLength = file.length();
    int lineLength = -1;
    CSVReader reader;
    FeatureSet result = new FeatureSet();
    try {
      reader = new CSVReader(new FileReader(paramInputCsv.getParameterText()));
      String[] header = reader.readNext();
      int xIndex = -1;
      int yIndex = -1;
      int zIndex = -1;
      int[] fieldLength = new int[header.length];
      for (int iField = 0; iField < header.length; iField++) {
        if (header[iField] != null && header[iField].equals(paramX.getParameterText())) {
          xIndex = iField;
        }
        if (header[iField] != null && header[iField].equals(paramY.getParameterText())) {
          yIndex = iField;
        }
        if (header[iField] != null && header[iField].equals(paramZ.getParameterText())) {
          zIndex = iField;
        }
        result.getFields().add(new Field(header[iField], FieldType.Character,
            MAX_FIELD_SIZE));
      }
      if (xIndex == -1) {
        getMessageHandler().message("X Field not found.", "The field "
            + paramX.getParameterText() + " could not be found.");
        getProgress().finish();
        return;
      }
      if (yIndex == -1) {
        getMessageHandler().message("Y Field not found.", "The field "
            + paramX.getParameterText() + " could not be found.");
        getProgress().finish();
        return;
      }

      int numLines = -1;
      int iLine = 0;
      String[] line;
      while ((line = reader.readNext()) != null) {
        if (lineLength <= 0) {
          lineLength = line.length;
          if (lineLength > 0) {
            numLines = (int) (fileLength / lineLength);
            //numLines = lines.size();
            getProgress().start(numLines);
          }
        }
        Feature currentFeature = new Feature();
        Shape point = new Shape(ShapeType.Point);
        currentFeature.setShape(point);
        boolean failed = false;
        for (int iField = 0; iField < line.length; iField++) {
          int length = line[iField].length();
          if (length > fieldLength[iField]) {
            fieldLength[iField] = length;
          }
          String fieldName = header[iField];
          currentFeature.getAttributes().put(fieldName, line[iField]);
          if (iField == xIndex) {
            String xText = line[iField];
            try {
              Double xValue = Double.parseDouble(xText);
              point.setX(xValue);
            } catch (NumberFormatException ex) {
              LOGGER.log(Level.FINE, "Failed to parse " + xText
                  + " as X coordinate.", ex);
              failed = true;
              break;
            }
          }
          if (iField == yIndex) {
            String yText = line[iField];
            try {
              Double yValue = Double.parseDouble(yText);
              point.setY(yValue);
            } catch (NumberFormatException ex) {
              LOGGER.log(Level.FINE, "Failed to parse " + yText
                  + " as Y coordinate.", ex);
              failed = true;
              break;
            }
          }
          if (iField == zIndex) {
            String zText = line[iField];
            try {
              Double zValue = Double.parseDouble(zText);
              point.setZ(zValue);
            } catch (NumberFormatException ex) {
              LOGGER.log(Level.FINE, "Failed to parse " + zText
                  + " as Z coordinate.", ex);
              failed = true;
              break;
            }
          }
        }
        if (failed) {
          continue;
        }
        result.getFeatures().add(currentFeature);
        if (iLine < numLines) {
          getProgress().progress(iLine);
        }
        iLine++;
      }
      //reader.close();
    } catch (FileNotFoundException ex) {
      Logger.getLogger(CsvToShapefile.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(CsvToShapefile.class.getName()).log(Level.SEVERE, null, ex);
    }

    getProgress().finish();

    ShapefileWriter writer = new ShapefileWriter();
    try {
      writer.write(result, paramOutputPoints.getParameterText());
    } catch (ShapefileException ex) {
      Logger.getLogger(CsvToShapefile.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

}
