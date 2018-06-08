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

import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapefileException;
import gov.ca.water.shapelite.io.ShapefileReader;
import gov.ca.water.shapelite.io.ShapefileWriter;
import gov.ca.water.shapelite.toolbox.DoubleParameter;
import gov.ca.water.shapelite.toolbox.InputFileParameter;
import gov.ca.water.shapelite.toolbox.OutputFileParameter;
import gov.ca.water.shapelite.toolbox.ToolBase;
import gov.ca.water.shapelite.topology.Simplify;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class SimplifyLines extends ToolBase {

  /**
   * The parameter for input lines.
   */
  private final InputFileParameter paramInputLines;
  /**
   * The parameter for tolerance.
   */
  private final DoubleParameter paramTolerance;
  /**
   * The parameter for output file.
   */
  private final OutputFileParameter output;

  /**
   * Simplify lines.
   */
  public SimplifyLines() {
    setName("Simplify Lines (2D)");
    setDescription("Perform Duglass-Peucker Line Simplification");
    this.setHelpText("This tool simplifies linear geometries by testing each"
            + " point for how far the X, Y point deflects the existing line.  "
            + "If that deflection is greater than the tolerance, the vertex "
            + "is kept, while points with deflections less than the tolerance "
            + "are skipped.");
    this.setCategory("Analysis");
    this.setHelpImageFilename("resources/ClassifyLines.png");

    paramInputLines = new InputFileParameter();
    paramInputLines.setParameterName("Input Line Shapefile");
    paramInputLines.setExtension(".shp");
    paramInputLines.setDescription("The Shapefile containing the lines to "
            + "simplify.");
    paramInputLines.setHelpText("The Shapefile containing the lines to "
            + "simplify.");
    this.getParameters().add(paramInputLines);

    paramTolerance = new DoubleParameter();
    paramTolerance.setParameterName("Tolerance");
    paramTolerance.setMin(0.0);
    paramTolerance.setMax(Double.MAX_VALUE);
    paramTolerance.setRequired(true);
    paramTolerance.setParameterText("1.0");
    paramTolerance.setDescription("The acceptable difference in projection "
            + "units r instance, if the projection is in US_Foot, then the "
            + "tolerance will also be in US_Foot.");
    paramTolerance.setHelpText("The tolerance in projection units for which "
            + "the simplified line may not match the original line in "
            + "projection units.  For instance, if the projection is in"
            + " US_Foot, then the tolerance will also be in US_Foot.");

    output = new OutputFileParameter();
    output.setParameterName("Output Folder for the Simplified Line Shapefiles");
    output.setDescription("The Shapefile output folder to store the shapefiles"
            + " with extracted lines.");
    output.setHelpText("The Shapefile output folder to store the shapefiles"
            + " with extracted lines.");
    output.setExtension(".shp");
    this.getParameters().add(output);

  }

  /**
   * Run immediately.
   */
  @Override
  public final void runImmediately() {
    getProgress().create("Simplifying: "
            + paramInputLines.getParameterName(), this.getCancellable());
    ShapefileReader lineReader = new ShapefileReader();

    try {
      lineReader.open(paramInputLines.getParameterText());
      FeatureSet result = new FeatureSet();
      Double tol = paramTolerance.getValue();
      if (tol == null) {
        tol = 1.0;
      }
      getProgress().start();
      for (Shape line : lineReader.shapes) {
        Shape simple = Simplify.simplify(line, tol);
        Feature f = new Feature();
        f.setShape(simple);
        result.getFeatures().add(f);
        if (this.isCancelled()) {
          getProgress().finish();
          return;
        }
      }
      ShapefileWriter writer = new ShapefileWriter();
      writer.write(result, output.getParameterText());
      String dbf = ShapefileWriter.setExtension(
              paramInputLines.getParameterText(), ".dbf");
      String prj = ShapefileWriter.setExtension(
              paramInputLines.getParameterText(), ".prj");
      String dbfOut = ShapefileWriter.setExtension(
              output.getParameterText(), ".dbf");
      String prjOut = ShapefileWriter.setExtension(
              output.getParameterText(), ".prj");
      File db = new File(dbfOut);
      if (db.exists()) {
        db.delete();
      }
      Files.copy(Paths.get(dbf), Paths.get(dbfOut));

      File pr = new File(prjOut);
      if (pr.exists()) {
        pr.delete();
      }
      Files.copy(Paths.get(prj), Paths.get(prjOut));

    } catch (IOException | ShapefileException ex) {
      Logger.getLogger(SimplifyLines.class.getName()).log(Level.SEVERE,
              ex.getMessage(), ex);
      getMessageHandler().message("Simplify Lines", ex.getMessage());
    }
    getProgress().finish();

  }

}
