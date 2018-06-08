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
package gov.ca.water.shapelite.compare;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.coordinate.CoordXY;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ShapeComparerTest {

  private final Coord a = new CoordXY(0, 0);
  private final Coord b = new CoordXY(25, 0);
  private final Coord c = new CoordXY(25, 5);
  private final Coord d = new CoordXY(20, 10);
  private final Coord e = new CoordXY(20, 5);
  private final Coord f = new CoordXY(15, 5);
  private final Coord g = new CoordXY(15, 15);

  public ShapeComparerTest() {
  }

  private Part getAbcd() {
    Part part = new Part();
    part.setClosed(true);
    part.getCoordinates().add(a.copy());
    part.getCoordinates().add(b.copy());
    part.getCoordinates().add(c.copy());
    part.getCoordinates().add(d.copy());
    return part;
  }

  private Part getEfg() {
    Part part = new Part();
    part.setClosed(true);
    part.getCoordinates().add(e.copy());
    part.getCoordinates().add(f.copy());
    part.getCoordinates().add(g.copy());
    return part;
  }

  // TODO add test methods here.
  // The methods must be annotated with annotation @Test. For example:
  //
  @Test
  public final void testBothNull() {
    ShapeComparer instance = new ShapeComparer();
    Shape shape = null;
    Shape other = null;
    boolean expResult = true;
    boolean result = instance.equivalent(shape, other);
    assertEquals(expResult, result);
  }

  @Test
  public final void testShapeNull() {
    ShapeComparer instance = new ShapeComparer();
    Shape shape = null;
    Shape other = new Shape();
    boolean expResult = false;
    boolean result = instance.equivalent(shape, other);
    assertEquals(expResult, result);
  }

  @Test
  public final void testOtherNull() {
    ShapeComparer instance = new ShapeComparer();
    Shape shape = new Shape();
    Shape other = null;
    boolean expResult = false;
    boolean result = instance.equivalent(shape, other);
    assertEquals(expResult, result);
  }

  @Test
  public final void testBothEmpty() {
    ShapeComparer instance = new ShapeComparer();
    Shape shape = new Shape();
    Shape other = new Shape();
    boolean expResult = true;
    boolean result = instance.equivalent(shape, other);
    assertEquals(expResult, result);
  }

  @Test
  public final void testOtherEmpty() {
    ShapeComparer instance = new ShapeComparer();
    Shape shape = new Shape();
    Shape other = new Shape();
    Part part = getAbcd();
    shape.getParts().add(part);
    boolean expResult = false;
    boolean result = instance.equivalent(shape, other);
    assertEquals(expResult, result);
  }

  @Test
  public final void testShapeEmpty() {
    ShapeComparer instance = new ShapeComparer();
    Shape shape = new Shape();
    Shape other = new Shape(ShapeType.Polygon);
    Part part = getAbcd();
    other.getParts().add(part);
    boolean expResult = false;
    boolean result = instance.equivalent(shape, other);
    assertEquals(expResult, result);
  }

  @Test
  public final void testBothAbc() {
    ShapeComparer instance = new ShapeComparer();
    Shape shape = new Shape(ShapeType.Polygon);
    Shape other = new Shape(ShapeType.Polygon);
    Part part = getAbcd();
    shape.getParts().add(part);
    Part otherPart = getAbcd();
    other.getParts().add(otherPart);
    boolean expResult = true;
    boolean result = instance.equivalent(shape, other);
    assertEquals(expResult, result);
  }

  @Test
  public final void testShapeTwoParts() {
    ShapeComparer instance = new ShapeComparer();
    Shape shape = new Shape(ShapeType.Polygon);
    Shape other = new Shape(ShapeType.Polygon);
    Part part = getAbcd();
    shape.getParts().add(part);
    Part second = getEfg();
    shape.getParts().add(second);
    Part otherPart = getAbcd();
    other.getParts().add(otherPart);
    boolean expResult = false;
    boolean result = instance.equivalent(shape, other);
    assertEquals(expResult, result);
  }

  @Test
  public final void testOtherTwoParts() {
    ShapeComparer instance = new ShapeComparer();
    Shape shape = new Shape(ShapeType.Polygon);
    Shape other = new Shape(ShapeType.Polygon);
    Part part = getAbcd();
    shape.getParts().add(part);
    Part otherPart = getAbcd();
    Part otherSecond = getEfg();
    other.getParts().add(otherPart);
    other.getParts().add(otherSecond);
    boolean expResult = false;
    boolean result = instance.equivalent(shape, other);
    assertEquals(expResult, result);
  }

  @Test
  public final void testBothTwoParts() {
    ShapeComparer instance = new ShapeComparer();
    Shape shape = new Shape(ShapeType.Polygon);
    Shape other = new Shape(ShapeType.Polygon);
    Part part = getAbcd();
    shape.getParts().add(part);
    Part second = getEfg();
    shape.getParts().add(second);
    Part otherPart = getAbcd();
    Part otherSecond = getEfg();
    other.getParts().add(otherPart);
    other.getParts().add(otherSecond);
    boolean expResult = true;
    boolean result = instance.equivalent(shape, other);
    assertEquals(expResult, result);
  }

  @Test
  public final void testBothTwoPartsOutOfOrder() {
    ShapeComparer instance = new ShapeComparer();
    Shape shape = new Shape(ShapeType.Polygon);
    Shape other = new Shape(ShapeType.Polygon);
    Part part = getAbcd();
    shape.getParts().add(part);
    Part second = getEfg();
    shape.getParts().add(second);
    Part otherPart = getAbcd();
    Part otherSecond = getEfg();
    other.getParts().add(otherSecond);
    other.getParts().add(otherPart);
    boolean expResult = true;
    boolean result = instance.equivalent(shape, other);
    assertEquals(expResult, result);
  }

  /**
   * Holes and shells should be distinguished as they are not actually equal.
   */
  @Test
  public final void testBothTwoPartsReversed() {
    ShapeComparer instance = new ShapeComparer();
    Shape shape = new Shape(ShapeType.Polygon);
    Shape other = new Shape(ShapeType.Polygon);
    Part part = getAbcd();
    shape.getParts().add(part);
    Part second = getEfg();
    shape.getParts().add(second);
    Part otherPart = getAbcd();
    otherPart.reverse();
    Part otherSecond = getEfg();
    otherSecond.reverse();
    other.getParts().add(otherPart);
    other.getParts().add(otherSecond);
    boolean expResult = false;
    boolean result = instance.equivalent(shape, other);
    assertEquals(expResult, result);
  }

  @Test
  public final void testFirstPartDifferent() {
    ShapeComparer instance = new ShapeComparer();
    Shape shape = new Shape(ShapeType.Polygon);
    Shape other = new Shape(ShapeType.Polygon);
    Part part = getAbcd();
    shape.getParts().add(part);
    Part second = getEfg();
    shape.getParts().add(second);
    Part otherPart = getEfg();
    Part otherSecond = getEfg();
    other.getParts().add(otherPart);
    other.getParts().add(otherSecond);
    boolean expResult = false;
    boolean result = instance.equivalent(shape, other);
    assertEquals(expResult, result);
  }

  @Test
  public final void testSecondPartDifferent() {
    ShapeComparer instance = new ShapeComparer();
    Shape shape = new Shape(ShapeType.Polygon);
    Shape other = new Shape(ShapeType.Polygon);
    Part part = getAbcd();
    shape.getParts().add(part);
    Part second = getEfg();
    shape.getParts().add(second);
    Part otherPart = getAbcd();
    Part otherSecond = getAbcd();
    other.getParts().add(otherPart);
    other.getParts().add(otherSecond);
    boolean expResult = false;
    boolean result = instance.equivalent(shape, other);
    assertEquals(expResult, result);
  }

  /**
   * In polygons, winding order matters and distinguishes a hole from a solid
   * polygon.
   */
  @Test
  public final void testPolygonWindingMatters() {
    Shape first = polygonWithHole();
    Shape second = polygonWithHole();
    List<Coord> coords = second.getParts().get(1).getCoordinates();
    Collections.reverse(coords);
    ShapeComparer shapes = new ShapeComparer();
    boolean expResult = false;
    assertEquals(expResult, shapes.equivalent(first, second));
  }

  /**
   * A test polygon rectangle with interior hole is returned with a hole in it.
   *
   * @return The Shape.
   */
  private Shape polygonWithHole() {
    Shape result = new Shape(ShapeType.Polygon);

    // What should happen for shell is that the last point should be removed
    // and then the ring should be reversed to get a valid clockwise shell.
    Part shell = new Part();
    shell.getCoordinates().add(new CoordXY(100, 1));
    shell.getCoordinates().add(new CoordXY(101, 1));
    shell.getCoordinates().add(new CoordXY(101, 0));
    shell.getCoordinates().add(new CoordXY(100, 0));
    shell.setClosed(true);
    result.getParts().add(shell);
    Part hole = new Part();
    hole.getCoordinates().add(new CoordXY(100.2, 0.2));
    hole.getCoordinates().add(new CoordXY(100.8, 0.2));
    hole.getCoordinates().add(new CoordXY(100.8, 0.8));
    hole.getCoordinates().add(new CoordXY(100.2, 0.8));
    hole.setClosed(true);
    result.getParts().add(hole);
    return result;
  }

}
