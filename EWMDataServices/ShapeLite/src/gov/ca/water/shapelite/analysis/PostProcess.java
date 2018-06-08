/*
 * The MIT License
 *
 * Copyright 2012 hdunsford.
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
package gov.ca.water.shapelite.analysis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Post processing was written for team Kwabena on the RainBrain project in
 * order to post process data.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PostProcess {

  public static final int RETURN_PERIODS = 13;

  /**
   * This method actually handles fitting a single row using a power law. The
   * values in a single column should also be increasing as a function of return
   * period, but this is not guaranteed by the curve fitting approach.
   *
   * @param row
   */
  public final void fit(DailyRow row) {
    double[] x = row.getX();
    double[] y = row.getValues();

    // Guess values
    double myfactor = 5;
    double mypower = .5;
    LeastSumOfSquares squares = new LeastSumOfSquares();
    Exponent exp = new Exponent(myfactor, mypower);
    squares.setFunction(exp);
    squares.setX(x);
    squares.setY(y);
    squares.optimize();
    row.setFit(squares.getRmse());
    row.setFactor(exp.getParameter(Exponent.FACTOR));
    row.setPower(exp.getParameter(Exponent.POWER));
  }

  /**
   * This test looks for self consistency in the non-linear least squares fit
   * for points. If any values fall significantly outside of the valid range,
   * then the whole block is added to the badCsv. If all the values are
   * acceptable, then the results are sent to the good CSV. Blank blocks are
   * ignored.
   *
   * @param sourceCsv The original data source.
   * @param fitCsv The csv file to store the resulting curve definitions and
   * fits.
   * @param goodCsv The csv file to store the good results from the fits.
   * @param badCsv The csv file to store any results from the fits that are bad.
   * @throws java.io.FileNotFoundException if an input file is not found.
   * @throws java.io.IOException if there is an error reading the csv fiiles.
   */
  public final void fitDaily(String sourceCsv, String fitCsv, String goodCsv,
          String badCsv) throws FileNotFoundException, IOException {

    StringBuilder builder;
    List<DailyRow> result;
    try (BufferedReader reader = new BufferedReader(
            new FileReader(sourceCsv))) {
      String line;
      reader.readLine(); // skip row header
      builder = new StringBuilder();
      builder.append("ST_ID,ST_NO,CALC_FIELD,FACTOR,POWER,FIT,"
              + "RMSE,YEAR\n");
      result = new ArrayList<>();
      while ((line = reader.readLine()) != null) {
        DailyRow row = new DailyRow();
        row.read(line);
        if (!row.isEmpty()) {
          fit(row);
          builder.append(row.getName()).append(",");
          builder.append(row.getId()).append(",");
          builder.append("RP ").append(row.getReturnPeriod()).
                  append(",");
          builder.append(row.getFactor()).append(",");
          builder.append(row.getPower()).append(",");
          builder.append(row.getFit()).append(",");
          builder.append(row.getRmse()).append(",");
          builder.append(row.getEndYear()).append("\n");
        }
        result.add(row);
      }
    }
    if (fitCsv != null) {
      File f = new File(fitCsv);
      if (f.exists()) {
        f.delete();
      }
      write(builder.toString(), fitCsv);
    }
    verifyIncreasing(result, goodCsv, badCsv);
  }

  /**
   * After running the fit algorithm, the functional results should be
   * monotonically increasing as a function of X as well as a function of the
   * return period. Any blocks that fail the criteria will be written to the
   * badCSV fileCsv file.
   *
   * @param result The array list of DailyRow objects to check.
   * @param goodCsv the string path to write good values to.
   * @param badCsv the string path to write bad values to.
   */
  public final void verifyIncreasing(List<DailyRow> result, String goodCsv,
          String badCsv) {
    DailyRow previous = null;
    StringBuilder sb = new StringBuilder();
    boolean blockIsBad = false;

    // If the files exist already, delete them.
    if (goodCsv != null) {
      File f = new File(goodCsv);
      if (f.exists()) {
        f.delete();
      }
      write(DailyRow.getHeader() + "\n", goodCsv);
    }
    if (badCsv != null) {
      File f = new File(badCsv);
      if (f.exists()) {
        f.delete();
      }
      write(DailyRow.getHeader() + "\n", badCsv);
    }
    for (DailyRow row : result) {
      if (previous != null) {
        if (row.getEndYear() != previous.getEndYear()) {
          // start a new block
          if (blockIsBad) {
            if (badCsv != null) {
              write(sb.toString(), badCsv);
            }
          } else {
            if (goodCsv != null) {
              write(sb.toString(), goodCsv);
            }
          }
          sb = new StringBuilder();
          blockIsBad = false;
          previous = null;
        }
      }
      //previous is now null if the row is not in the same block.
      if (previous != null) {
        for (int i = 0; i < RETURN_PERIODS; i++) {
          double x = row.getX()[i];
          double y = row.getFactor() * Math.pow(x, row.getPower());
          double prev = previous.getFactor() * Math.pow(x,
                  previous.getPower());
          if (y < prev) {
            blockIsBad = true;
          }
        }

      }

      // Do this part for all rows, even if we don't have a previous row.
      sb.append(row.toString()).append("\n");
      if (!row.monotonicallyIncreases()) {
        blockIsBad = true;
      }
      previous = row;
    }
  }

  /**
   * Writes the specified text to the specified outFile.
   *
   * @param text The string text to write.
   * @param outFile The string file path of the output file.
   */
  private void write(String text, String outFile) {
    BufferedWriter writer = null;
    try {
      File directory = new File(outFile).getParentFile();
      if (!directory.exists()) {
        directory.mkdirs();
      }
      writer = new BufferedWriter(new FileWriter(outFile, true));
      writer.write(text);
    } catch (Exception ex) {
      ex.printStackTrace(System.out);
    } finally {
      if (writer != null) {
        try {
          writer.close();
        } catch (Exception ex) {
          ex.printStackTrace(System.out);
        }
      }
    }
  }

}
