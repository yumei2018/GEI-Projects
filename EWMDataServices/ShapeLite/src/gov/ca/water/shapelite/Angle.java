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
package gov.ca.water.shapelite;

/**
 * A geometric angle measured in degrees or radians the angle will wrap around,
 * so setting larger values will result in the appropriate angle.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class Angle {

  /**
   * The double angle value in radians.
   */
  private double radians;

  /**
   * Creates a new 0 instance of an Angle.
   */
  public Angle() {
    radians = 0;
  }

  /**
   * Creates a new instance of an Angle.
   *
   * @param angle specifies the angle in radians.
   */
  public Angle(final double angle) {
    this.radians = wrapAngle(angle);
  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * This method will take angles that are outside the range of - 2 Pi to 2 Pi
   * and re-express it within that range.
   *
   * @param angle The original angle that can be outside the -2Pi - 2Pi range.
   * @return the double angle.
   */
  private double wrapAngle(final double angle) {
    double result = angle;
    if (angle > 2 * Math.PI || angle < -2 * Math.PI) {
      result = angle % (Math.PI * 2);
    }
    return result;
  }

   //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the double angle value in Degrees.
   *
   * @return The double precision angle value.
   */
  public final double getDegrees() {
    return Math.toDegrees(getRadians());
  }

  /**
   * Sets the double angle value in Degrees.
   *
   * @param degreeAngle
   */
  public final void setDegrees(final double degreeAngle) {
    double radians = Math.toRadians(degreeAngle);
    setRadians(radians);
  }

  /**
   * Gets the angle in radians.
   *
   * @return the radians
   */
  public final double getRadians() {
    return radians;
  }

  /**
   * Sets the angle in radians.
   *
   * @param radianAngle
   */
  public final void setRadians(final double radianAngle) {
    this.radians = wrapAngle(radianAngle);
  }

  //</editor-fold>
}
