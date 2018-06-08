/*
 * The MIT License
 *
 * Copyright 2014 J.G. "Koos" Prins, D.Eng. PE..
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

import java.util.List;

/**
 * A Wrapper for a shape of type {@linkplain ShapeType#Polygon Polygon}.
 *
 * @author J.G. "Koos" Prins, D.Eng. PE.
 */
public class Polygon extends Shape {

    //<editor-fold defaultstate="collapsed" desc="Private Fields">
  //</editor-fold>
  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Public Constructor - assuming ShapeType = Polygon.
   */
  public Polygon() {
    super(ShapeType.Polygon);
  }

  /**
   * Public Constructor with a specified polygon ShapeType.
   *
   * @param shapeType the polygon shape type
   * @exception IllegalArgumentException throws an IllegalArgumentException if
   * shapeType is not a Polygon(Z|M)
   */
  public Polygon(ShapeType shapeType) {
    super(ShapeType.Polygon);
    if ((shapeType != null) && (!this.isPolygonType(shapeType))) {
      throw new IllegalArgumentException("The Shape ShapeType["
              + shapeType + "] is invalid. Expected a Polygon.");
    }
  }

  /**
   * Constructor with shape of type Polygon to wrap. This will steal the parts
   * array from the specified shape, but only if the specified shape is a
   * polygon
   *
   * @param polygonShape the Polygon Shape to wrap.
   * @exception IllegalArgumentException throws an IllegalArgumentException if
   * polygonShape.shapeType is not a Polygon(Z|M)
   */
  public Polygon(Shape polygonShape) {
    super(ShapeType.Polygon);
    if (polygonShape != null) {
      if (!this.isPolygonType(polygonShape.getShapeType())) {
        throw new IllegalArgumentException("The Shape ShapeType["
                + polygonShape.getShapeType()
                + "] is invalid. Expected a Polygon.");
      }
      this.getParts().clear();
      this.getParts().addAll(polygonShape.getParts());
      this.calculateBounds();
    }
  }

  /**
   * Check is this is a valid Polygon Shape Type.
   *
   * @param shapeType the ShapeType to test.
   * @return true if shaepType = [Polygon|PolygonZ|PolygonM].
   */
  private boolean isPolygonType(ShapeType shapeType) {
    return ((shapeType == ShapeType.Polygon)
            || (shapeType == ShapeType.PolygonZ)
            || (shapeType == ShapeType.PolygonM));
  }
  // </editor-fold>

  /**
   * Check if the specified coordinate is inside the polygon.
   *
   * @param coordinate the coordinate to test for.
   * @return true if polygon has parts and the coordinate is inside the polygon.
   */
  public final boolean contains(Coord coordinate) {
    boolean result = false;
    List<Part> partList = this.getParts();
    if ((partList != null) && (!partList.isEmpty())) {
      int hitCount = 0;
      for (Part part : partList) {
        if (part.contains(coordinate)) {
          if (part.isHole()) {
            hitCount--;
          } else {
            hitCount++;
          }
        }
      }
      result = hitCount > 0;
    }
    return result;
  }
}
