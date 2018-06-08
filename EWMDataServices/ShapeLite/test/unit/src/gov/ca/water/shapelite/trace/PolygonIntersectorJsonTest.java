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
package gov.ca.water.shapelite.trace;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.io.json.JsonPolygon;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PolygonIntersectorJsonTest {

  public PolygonIntersectorJsonTest() {
  }

  /**
   * Test of clip method, of class PolygonIntersectorJson.
   */
  @Test
  public void testClip() throws Exception {
    System.out.println("clip");
    FeatureSet points = new FeatureSet();
    Coord first = new CoordXY(100, 100);
    Coord second = new CoordXY(300, 300);
    points.getFeatures().add(new Feature(new Shape(first)));
    points.getFeatures().add(new Feature(new Shape(second)));
    PolygonIntersectorJson instance = new PolygonIntersectorJson(points);
    JsonPolygon pg = new JsonPolygon();
    pg.setRings(new double[][][]{{{0,200},{200,200},{200, 0},{0,0}}});
    String jsonPolygon = pg.toString();
    String expResult = "{\"x\":100.0,\"y\":100.0,\"z\":0.0,\"m\":0.0}";
    String result = instance.clip(jsonPolygon);
    assertEquals(expResult, result);
  }

}
