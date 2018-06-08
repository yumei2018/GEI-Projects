/*
 * The MIT License
 *
 * Copyright 2016 Harold A. Dunsford Jr. Ph.D..
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
package gov.ca.water.shapelite.io.json;

import gov.ca.water.shapelite.coordinate.HasM;
import gov.ca.water.shapelite.coordinate.HasXY;
import gov.ca.water.shapelite.coordinate.HasZ;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class JsonPoint extends JsonGeometryBase<JsonPoint> implements HasXY, HasZ, HasM {

  /**
   * X.
   */
  private double x;
  /**
   * Y.
   */
  private double y;
  /**
   * Z.
   */
  private double z;
  /**
   * M.
   */
  private double m;

  /**
   * Creates a point with all zero values.
   */
  public JsonPoint() {

  }

  /**
   * Creates a new instance of the JsonPoint class.
   *
   * @param x The double x coordinate.
   * @param y The double y coordinate.
   */
  public JsonPoint(double x, double y) {
    this.x = x;
    this.y = y;
  }

  // <editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * @return the x
   */
  @Override
  public final double getX() {
    return x;
  }

  /**
   * @param x the x to set
   */
  @Override
  public final void setX(double x) {
    this.x = x;
  }

  /**
   * @return the y
   */
  @Override
  public final double getY() {
    return y;
  }

  /**
   * @param y the y to set
   */
  @Override
  public final void setY(double y) {
    this.y = y;
  }

  /**
   * @return the z
   */
  @Override
  public final double getZ() {
    return z;
  }

  /**
   * @param z the z to set
   */
  @Override
  public final void setZ(double z) {
    this.z = z;
  }

  /**
   * @return the m
   */
  @Override
  public final double getM() {
    return m;
  }

  /**
   * @param m the m to set
   */
  @Override
  public final void setM(double m) {
    this.m = m;
  }

  // </editor-fold>
  /**
   * Gets a JsonPoint representing the copy of this point.
   *
   * @return The JsonPoint duplicate of this point.
   */
  @Override
  public final JsonPoint copy() {
    JsonPoint result = new JsonPoint();
    result.copyProperties(this);
    return result;
  }

  /**
   * Copies the properties of the other point to this one.
   *
   * @param other the other JsonPoint item.
   */
  @Override
  public final void copyProperties(JsonPoint other) {
    super.setSpatialReference(other.getSpatialReference());
    x = other.getX();
    y = other.getY();
    z = other.getZ();
    m = other.getM();
  }
}
