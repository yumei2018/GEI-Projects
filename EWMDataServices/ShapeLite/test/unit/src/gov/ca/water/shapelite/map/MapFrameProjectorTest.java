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
package gov.ca.water.shapelite.map;

import gov.ca.water.shapelite.data.Projector;
import java.awt.Point;
import java.awt.Rectangle;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.DefaultEnvelope;
import gov.ca.water.shapelite.Envelope;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class MapFrameProjectorTest {

  public MapFrameProjectorTest() {
  }

  /**
   * Test of getPoint method, of class Mercator.
   */
  @Test
  public void testGetPoint() {
    Envelope env = new DefaultEnvelope(-180, -85, 180, 85);
    Rectangle r = new Rectangle(0, 0, 1000, 1000);
    Point pt = new Point(250, 250);
    GeoFrame gf = new GeoFrame(env, r);
    Projector p = new Projector(gf);
    Coord c = p.getCoordinate(pt);
    Point test = p.getPoint(c);
    if (test.x != pt.x || test.y != pt.y) {
      Assert.fail("Reprojection of point was not self consistent.");
    }
  }

  @Test
  public void testMerc() {
    Envelope env = new DefaultEnvelope(-180, -85, 180, 85);
    Rectangle r = new Rectangle(0, 0, 1000, 1000);
    Point pt = new Point(250, 250);
    GeoFrame gf = new GeoFrame(env, r);
    double y = 45;
    double m = Mercator.latToMerc(y);
    double y2 = Mercator.mercToLat(m);
    if (Math.abs(y - y2) > .000001) {
      Assert.fail("The Merc projection is not self consistent.");
    }
  }

}
