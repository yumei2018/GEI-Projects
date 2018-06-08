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
package gov.ca.water.shapelite.analysis;

import com.vividsolutions.jts.geom.Geometry;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.topology.Adapter;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class Buffer {

  /**
   * Buffers the existing shape by a double distance.
   *
   * @param original The original shape, which can be point, multi-point,
   * polyline or polygon.
   * @param distance The double buffer distance in the units of the coordinate
   * system. If the coordinate system is latitude, longitude, then the units are
   * decimal degrees.
   * @return The 2D buffered polygon or multi-polygon shape. This may be a null
   * type shape if the original shape was a null type. If the input shape had Z
   * or M values, these will be lost.
   */
  public final Shape buffer(Shape original, double distance) {
    Shape result = Shape.getNullShape();
    if (original.getShapeType() == ShapeType.MultiPatch
        || original.getShapeType() == ShapeType.NullShape) {
      return result;
    }
    Optional<Geometry> maybeGeom = Adapter.getGeometry(original);
    if (maybeGeom.isPresent()) {
      Geometry geom = maybeGeom.get();
      Geometry buffer = geom.buffer(distance);
      result = Adapter.getShape(buffer, false, false);
    }
    return result;
  }
}
