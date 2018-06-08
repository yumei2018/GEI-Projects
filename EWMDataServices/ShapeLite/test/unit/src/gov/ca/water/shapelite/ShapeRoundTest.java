/*
 * The MIT License
 *
 * Copyright 2017 Harold A. Dunsford Jr. Ph.D..
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

import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.projection.Projections;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ShapeRoundTest {

  public ShapeRoundTest() {
  }

  // TODO add test methods here.
  // The methods must be annotated with annotation @Test. For example:
  //
  @Test
  public void testShapeRounding() {
    double x = 1234.1234;
    double y = 4321.4321;
    Coord c = new CoordXY(x, y);
    Shape shape = new Shape(c);
    Shape result = shape.round(100);
    double expectedX = 1234.12;
    double expectedY = 4321.43;
    assertEquals(expectedX, result.getX(), 0.0001);
    assertEquals(expectedY, result.getY(), 0.0001);
  }

  @Test
  public void testShapeRounding10() {
    double x = 1234.1234;
    double y = 4321.4321;
    Coord c = new CoordXY(x, y);
    Shape shape = new Shape(c);
    Shape result = shape.round(.1);
    double expectedX = 1230.0;
    double expectedY = 4320.0;
    assertEquals(expectedX, result.getX(), 0.0001);
    assertEquals(expectedY, result.getY(), 0.0001);
  }
  @Test
  public void testFeatureSetRounding() {
    FeatureSet fs = new FeatureSet(ShapeType.Point);
    fs.setProjectionFrom(Projections.getNad83UTMZone10Foot());
    double x = 1234.1234;
    double y = 4321.4321;
    Coord c = new CoordXY(x, y);
    Shape shape = new Shape(c);
    fs.getFeatures().add(new Feature(shape));
    double expectedX = 1230.0;
    double expectedY = 4320.0;
    fs.roundToFeet(10);
    Shape result = fs.getFeatures().get(0).getShape();
    assertEquals(expectedX, result.getX(), 0.0001);
    assertEquals(expectedY, result.getY(), 0.0001);
  }

}
