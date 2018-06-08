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

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class DailyRow implements Comparable<DailyRow> {

  /**
   * Total number of return periods.
   */
  private static final int RETURN_PERIODS = 13;

  /**
   * There are more columns in the output than return periods. This is the total
   * number of columns other than return periods.
   */
  private static final int OFFSET = 3;

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The integer end year that is associated with this row of data.
   */
  private int endYear;

  /**
   * The double multiplicative factor for the fit curve.
   */
  private double factor;

  /**
   * The double fit value showing how well the data fits the fit curve.
   */
  private double fit;

  /**
   * The string id identifying this station..
   */
  private String id;

  /**
   * The string name of the station.
   */
  private String name;

  /**
   * The exponential power that the curve is raised to.
   */
  private double power;

  /**
   * The integer return period in days.
   */
  private Integer returnPeriod;

  /**
   * The double array of values for this row, serving as the y axis.
   */
  private double[] values;

  /**
   * The double array of consecutive days, serving as the x axis.
   */
  private double[] x;

  //</editor-fold>
  /**
   * Creates a new instance of the Daily Row.
   */
  public DailyRow() {
    values = new double[RETURN_PERIODS];
    x = new double[]{1, 2, 3, 4, 5, 6, 8, 10, 15, 20, 30, 60, 365};
  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Compares this DialyRow with another for sorting.
   *
   * @param o The other DailyRow to compare to.
   * @return An integer that is 1 if this return period i
   */
  @Override
  public final int compareTo(DailyRow o) {
    return this.returnPeriod.compareTo(o.returnPeriod);
  }

  /**
   * Gets a boolean that is true if none of the values have been assigned.
   *
   * @return A boolean showing if this row is empty or not.
   */
  public final boolean isEmpty() {
    for (Double value : values) {
      if (value != null) {
        return false;
      }
    }
    return true;
  }

  /**
   * Gets the header line for the daily row.
   *
   * @return the String header, delimited by commas for the csv file output.
   */
  public final static String getHeader() {
    return "ST_NAME,ST_ID,RETURN_PERIOD : CONSECUTIVE_DAYS,1,2,3,4,5,6,8,"
            + "10,15,20,30,60,365,YEAR";
  }

  /**
   * Gets the Root Mean Square Error between the mathematical fit and the
   * measured values for this row.
   *
   * @return the rmse
   */
  public final double getRmse() {
    double sum = 0;
    for (int i = 0; i < RETURN_PERIODS; i++) {
      double dif = values[i] - factor * Math.pow(getX()[i], power);
      sum += dif * dif;
    }
    double mean = sum / RETURN_PERIODS;
    return Math.sqrt(mean);
  }

  /**
   * This method tests the function y = Factor * (x ^ POWER) to ensure that the
   * function is monotonically increasing. This does not work with the original
   * values, which likely won't.
   *
   * @return boolean, true if every point is larger than the previous one.
   */
  public final boolean monotonicallyIncreases() {
    double previous = 0;
    for (double u : x) {
      double current = factor * Math.pow(u, power);
      if (current < previous) {
        return false;
      }
      previous = current;
    }
    return true;
  }

  /**
   * Reads the specified line a comma separated values line of text.
   *
   * @param line The string line to read.
   */
  public final void read(String line) {
    String[] cols = line.split(",");
    if (cols.length > 0) {
      this.name = cols[0];
    }
    if (cols.length > 1) {
      this.id = cols[1];
    }
    if (cols.length > 2) {
      String stringReturnPeriod = cols[2];
      if (stringReturnPeriod != null && !stringReturnPeriod.isEmpty()) {
        String trimmed = stringReturnPeriod.substring(2,
                stringReturnPeriod.length()).trim();
        this.returnPeriod = Integer.parseInt(trimmed);
      }
    }
    for (int i = 0; i < RETURN_PERIODS; i++) {
      if (cols.length > i + OFFSET) {
        if (cols[i + OFFSET] != null && !cols[i + OFFSET].isEmpty()) {
          values[i] = Double.parseDouble(cols[i + OFFSET]);
        }
      }
    }
    int total = OFFSET + RETURN_PERIODS;
    if (cols.length > total) {
      if (cols[total] != null && !cols[total].isEmpty()) {
        this.endYear = Integer.parseInt(cols[total]);
      }
    }
  }

  /**
   * Converts this row into a comma separated values line of text.
   *
   * @return The string line for this row.
   */
  @Override
  public final String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(name).append(",");
    sb.append(id).append(",");
    sb.append("RP ").append(Integer.toString(returnPeriod)).append(", ");
    for (int i = 0; i < RETURN_PERIODS; i++) {
      sb.append(Double.toString(values[i])).append(", ");
    }
    sb.append(Integer.toString(endYear));
    return sb.toString();
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the integer end year that is associated with this row of data.
   *
   * @return the endYear.
   */
  public final int getEndYear() {
    return endYear;
  }

  /**
   * Sets the integer end year that is associated with this row of data.
   *
   * @param endYear the endYear to set.
   */
  public final void setEndYear(int endYear) {
    this.endYear = endYear;
  }

  /**
   * Gets the double multiplicative factor for the fit curve.
   *
   * @return the factor.
   */
  public final double getFactor() {
    return factor;
  }

  /**
   * Sets the double multiplicative factor for the fit curve.
   *
   * @param factor the factor to set.
   */
  public final void setFactor(double factor) {
    this.factor = factor;
  }

  /**
   * Gets the double fit value showing how well the data fits the fit curve.
   *
   * @return the fit.
   */
  public final double getFit() {
    return fit;
  }

  /**
   * Sets the double fit value showing how well the data fits the fit curve.
   *
   * @param fit the fit to set.
   */
  public final void setFit(double fit) {
    this.fit = fit;
  }

  /**
   * Gets the string id identifying this row.
   *
   * @return the id.
   */
  public final String getId() {
    return id;
  }

  /**
   * Sets the string id identifying this row.
   *
   * @param id the id to set.
   */
  public final void setId(String id) {
    this.id = id;
  }

  /**
   * Gets the string name of the station.
   *
   * @return the name.
   */
  public final String getName() {
    return name;
  }

  /**
   * Sets the string name of the station.
   *
   * @param name the name to set.
   */
  public final void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the exponential power that the curve is raised to.
   *
   * @return the power.
   */
  public final double getPower() {
    return power;
  }

  /**
   * Sets the exponential power that the curve is raised to.
   *
   * @param power the power to set.
   */
  public final void setPower(double power) {
    this.power = power;
  }

  /**
   * Gets the integer return period in days.
   *
   * @return the returnPeriod.
   */
  public final Integer getReturnPeriod() {
    return returnPeriod;
  }

  /**
   * Sets the integer return period in days.
   *
   * @param returnPeriod the returnPeriod to set.
   */
  public final void setReturnPeriod(Integer returnPeriod) {
    this.returnPeriod = returnPeriod;
  }

  /**
   * Gets the double array of values for this row, serving as the y axis.
   *
   * @return the values.
   */
  public final double[] getValues() {
    return values;
  }

  /**
   * Sets the double array of values for this row, serving as the y axis.
   *
   * @param values the values to set.
   */
  public final void setValues(double[] values) {
    this.values = values;
  }

  /**
   * Gets the double array of consecutive days, serving as the x axis.
   *
   * @return the x.
   */
  public final double[] getX() {
    return x;
  }

  /**
   * Sets the double array of consecutive days, serving as the x axis.
   *
   * @param x the x to set.
   */
  public final void setX(double[] x) {
    this.x = x;
  }

  //</editor-fold>
}
