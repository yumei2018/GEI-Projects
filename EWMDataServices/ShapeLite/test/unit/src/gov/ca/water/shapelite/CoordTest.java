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
package gov.ca.water.shapelite;


import gov.ca.water.shapelite.coordinate.CoordZ;
import gov.ca.water.shapelite.coordinate.CoordXY;
import org.junit.Assert;
import org.junit.Test;


/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class CoordTest {

  public CoordTest() {
  }


  @Test
  public void Constructor2D()
  {
    Coord a = new CoordXY(10.1231, 33.1231);

    Assert.assertTrue(a.getX() == 10.1231);
    Assert.assertTrue(a.getY() == 33.1231);
  }

  @Test
  public void Constructor3D()
  {
    Coord a = new CoordZ(10.1231, 33.1231, 44.5345);
    Assert.assertTrue(a.getX() == 10.1231);
    Assert.assertTrue(a.getY() == 33.1231);
    Assert.assertTrue(a.get(Coord.Index.Z) == 44.5345);
  }

  @Test
  public void Equals2D() {
    Coord a = new CoordXY(10, 5);
    Coord b = new CoordXY(10, 5);
    Assert.assertTrue("Coordinate 2D equals test of integer valued coordinates failed.", a.equals(b));
  }

  @Test
  public void equals3D(){
    Coord a = new CoordZ(1.22311, 1241.1231, 123.1231);
    Coord b = new CoordZ(1.22311, 1241.1231, 123.1231);
    Assert.assertTrue("Coordinate 2D equals test of integer valued coordinates failed.", a.equals(b));
  }

  @Test
  public void distance2D(){
    Coord a = new CoordXY(1, 1);
    Coord b = new CoordXY(4, 5);
    Assert.assertTrue(a.distance(b) == 5);
    Assert.assertTrue(b.distance(a) == 5);
  }

  @Test
  public void distance3D(){
    Coord a = new CoordZ(1, 1, 1);
    Coord b = new CoordZ(3, 3, 3);
    Assert.assertTrue(a.distance(b) == Math.sqrt(12));
  }



}
