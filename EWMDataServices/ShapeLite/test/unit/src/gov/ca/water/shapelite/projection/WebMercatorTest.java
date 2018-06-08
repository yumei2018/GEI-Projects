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
package gov.ca.water.shapelite.projection;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.coordinate.CoordXY;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class WebMercatorTest {
  
  /**
   * Web Mercator
   *
   * @throws gov.ca.water.shapelite.projection.ProjectionException
   */
  @Test
  public void testWebMercator() throws ProjectionException {
    ProjectionInfo wgs84 = Projections.getGeographic().getWorld().getWGS1984();
    ProjectionInfo mercator = Projections.getProjected().getWorld().getWebMercator();
    Coord point = new CoordXY(-125.0000000, 35.0000000);
    Coord merc = Reproject.reprojectCoordinate(point, wgs84, mercator);
    Coord result = Reproject.reprojectCoordinate(merc, mercator, wgs84);
    if (Math.abs(point.getX() - result.getX()) > .0000001 || Math.abs(point.getY() - result.getY()) > .0000001) {
      fail("Reprojected expected -125, 35 but got " + result.getX() + ", " + result.getY());
    }

  }

}
