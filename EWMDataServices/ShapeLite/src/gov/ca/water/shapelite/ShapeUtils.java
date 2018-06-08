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
package gov.ca.water.shapelite;

import gov.ca.water.shapelite.coordinate.CoordM;
import gov.ca.water.shapelite.coordinate.CoordType;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.coordinate.CoordZ;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class ShapeUtils {

  /**
   * Shape utilities class.
   */
  private ShapeUtils() {

  }

  /**
   * Takes the original shape coordinates and creates a new shape that has the Z
   * type instead. Coordinates are replicated with M and Z values.
   *
   * @param original the original shape.
   * @param outputType the coordinate type for the output coordinates. The Shape
   * category will be preserved.
   * @return The resulting shape with Z values.
   */
  public static Shape convertCoordinates(Shape original, CoordType outputType) {
    if (original.getShapeType() == ShapeType.NullShape) {
      return original.copy();
    }
    boolean hasM = (outputType == CoordType.M || outputType == CoordType.Z);
    boolean hasZ = outputType == CoordType.Z;

    ShapeType type = ShapeType.fromCategory(original.getShapeType()
        .getCategory(), hasZ, hasM);

    Shape result = new Shape(type);
    for (Part part : original.getParts()) {
      Part resultPart = new Part();
      resultPart.setClosed(part.isClosed());
      for (Coord c : part.getCoordinates()) {
        switch (outputType) {
          case Z:
            resultPart.getCoordinates().add(new CoordZ(c));
            break;
          case M:
            resultPart.getCoordinates().add(new CoordM(c));
            break;
          case XY:
            resultPart.getCoordinates().add(new CoordXY(c));
            break;
          default:
          // do nothing.
        }
      }
      result.getParts().add(part);
    }
    result.calculateBounds();
    return result;
  }
}
