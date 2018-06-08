/*
 * The MIT License
 *
 * Copyright 2014 hdunsford.
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
package gov.ca.water.shapelite.topology;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.simplify.DouglasPeuckerSimplifier;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.coordinate.CoordZ;
import gov.ca.water.shapelite.Nullable;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class Simplify {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  //</editor-fold>
  /**
   * Creates a new instance of the Simplify class.
   */
  private Simplify() {

  }


  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * This implements DP Simplification to the specified tolerance.
   *
   * @param original The original shape to simplify. If original is null, this
   * returns a null shape. If it is empty, this returns the original shape.
   * Otherwise, it returns a simplified geometry.
   * @param tolerance The tolerance for deviation from the original in
   * coordinate units.
   * @return The simplified Shape
   */
  public static Shape simplify(@Nullable Shape original, double tolerance) {
    if (original == null) {
      return Shape.getNullShape();
    }
    if (original.isEmpty()) {
      return original;
    }

    Optional<Geometry> geom = Adapter.getGeometry(original);
    if (geom.isPresent()) {
      DouglasPeuckerSimplifier simplifier = new DouglasPeuckerSimplifier(geom.get());
      simplifier.setDistanceTolerance(tolerance);
      Geometry resultGeom = simplifier.getResultGeometry();
      return Adapter.getShape(resultGeom, original.hasM(), original.hasZ());
    }

    return original;
  }

  /**
   * Given an X, Y, Z shape, this simplifies in the Z dimension by treating X
   * and Y as a linear distance, reducing them to a composite X axis, and treats
   * the Z dimension as a Y axis value. The vertical units should be comperable
   * to the horizontal units.
   *
   * @param original The original shape to simplify. If original is null, this
   * returns a null shape. If it is empty, this returns the original shape.
   * Otherwise, it returns a simplified geometry.
   * @param tolerance The tolerance for deviation from the original in
   * coordinate units.
   * @return
   */
  public static Shape simplifyZ(@Nullable Shape original, double tolerance) {
    if (original == null) {
      return Shape.getNullShape();
    }
    if (original.isEmpty()) {
      return original;
    }
    Shape trans = new Shape(ShapeType.PolyLine);

    for (Part originalPart : original.getParts()) {
      Part part = new Part();
      double dist = 0;
      Coord previous = null;
      for (Coord c : originalPart.getCoordinates()) {
        if (previous != null) {
          dist += c.distance(previous);
        }
        previous = c;
        Coord resC = new CoordXY(dist, c.get(Coord.Index.Z));
        part.getCoordinates().add(resC);
      }
      trans.getParts().add(part);
    }

    trans.calculateBounds();
    Optional<Geometry> geom = Adapter.getGeometry(trans);
    if (!geom.isPresent()) {
      return original;
    }
    DouglasPeuckerSimplifier simplifier = new DouglasPeuckerSimplifier(geom.get());
    simplifier.setDistanceTolerance(tolerance);
    Geometry resultGeom = simplifier.getResultGeometry();
    Shape simpleShape = Adapter.getShape(resultGeom, original.hasM(), original.hasZ());
    Shape result = new Shape(ShapeType.PolyLineZ);
    for (Part simplePart : simpleShape.getParts()) {
      Part prt = new Part();
      Coord prevSimple = null;
      for (Coord simpleC : simplePart.getCoordinates()) {
        Coord prevOriginal = null;
        double dist = 0;
        double prevDist = 0;
        boolean found = false;
        for (Coord originalC : original.getCoordinates()) {
          if (prevOriginal != null) {
            dist += originalC.distance(prevOriginal);
            if (dist > simpleC.getX()) {
              double dx = originalC.getX() - prevOriginal.getX();
              double dy = originalC.getY() - prevOriginal.getY();
              double d = originalC.distance(prevOriginal);
              double s;
              if (prevSimple != null) {
                s = simpleC.getX() - prevDist;
              } else {
                s = simpleC.getX();
              }
              double outX = (s / d) * dx + prevOriginal.getX();
              double outY = (s / d) * dy + prevOriginal.getY();
              prt.getCoordinates().add(new CoordZ(outX, outY, simpleC.getY()));
              found = true;
              break;
            }
            prevDist = dist;
          }
          prevOriginal = originalC;
        }
        if (!found) {
          // The last point could be just beyond the end.
          Coord last = original.last();
          prt.getCoordinates().add(new CoordZ(last.getX(), last.getY(), simpleC.getY()));
        }
        prevSimple = simpleC;
      }
      result.getParts().add(prt);
    }

    result.calculateBounds();
    return result;
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  //</editor-fold>
}
