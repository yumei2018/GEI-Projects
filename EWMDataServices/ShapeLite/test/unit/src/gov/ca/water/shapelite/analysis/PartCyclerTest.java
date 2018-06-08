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
package gov.ca.water.shapelite.analysis;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.coordinate.CoordXY;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PartCyclerTest {

  public PartCyclerTest() {
  }

  /**
   * Tests the forward method, without cycling including end coordinates in an
   * open part.
   */
  @Test
  public final void testForwardOpenPart() {
    Coord a = new CoordXY(0, 50);
    Coord b = new CoordXY(0, 20);
    Coord c = new CoordXY(10, 20);
    Coord d = new CoordXY(10, 10);
    Coord e = new CoordXY(30, 10);
    PartCycler instance = new PartCycler();
    Part closed = new Part();
    closed.getCoordinates().add(a);
    closed.getCoordinates().add(b);
    closed.getCoordinates().add(c);
    closed.getCoordinates().add(d);
    closed.getCoordinates().add(e);

    List<Coord> resultCoords = instance.forward(closed, a, e);
    Coord[] expCoord = new Coord[]{a, b, c, d, e};
    int expSize = 5;
    assertEquals(expSize, resultCoords.size());
    boolean expResult = true;
    for (int index = 0; index < resultCoords.size(); index++) {
      boolean result = expCoord[index].equals(resultCoords.get(index));
      assertEquals(expResult, result);
    }
  }

  /**
   * Tests the forward method, with cycling including end coordinates in an
   * open part.
   */
  @Test
  public final void testForwardOpenPartCycle() {
    Coord a = new CoordXY(0, 50);
    Coord b = new CoordXY(0, 20);
    Coord c = new CoordXY(10, 20);
    Coord d = new CoordXY(10, 10);
    Coord e = new CoordXY(30, 10);
    PartCycler instance = new PartCycler();
    Part closed = new Part();
    closed.getCoordinates().add(a);
    closed.getCoordinates().add(b);
    closed.getCoordinates().add(c);
    closed.getCoordinates().add(d);
    closed.getCoordinates().add(e);
    List<Coord> resultCoords = instance.forward(closed, c, b);
    int expSize = 0;
    assertEquals(expSize, resultCoords.size());
  }

  /**
   * Tests the backward method, without cycling including end coordinates
   * in an open part.
   */
  @Test
  public final void testBackwardOpenPart() {
    Coord a = new CoordXY(0, 50);
    Coord b = new CoordXY(0, 20);
    Coord c = new CoordXY(10, 20);
    Coord d = new CoordXY(10, 10);
    Coord e = new CoordXY(30, 10);
    PartCycler instance = new PartCycler();
    Part open = new Part();
    open.getCoordinates().add(a);
    open.getCoordinates().add(b);
    open.getCoordinates().add(c);
    open.getCoordinates().add(d);
    open.getCoordinates().add(e);
    List<Coord> resultCoords = instance.backward(open, e, a);
    Coord[] expCoord = new Coord[]{e, d, c, b, a};
    int expSize = 5;
    assertEquals(expSize, resultCoords.size());
    boolean expResult = true;
    for (int index = 0; index < resultCoords.size(); index++) {
      boolean result = expCoord[index].equals(resultCoords.get(index));
      assertEquals(expResult, result);
    }
  }

  /**
   * Tests the backward method, with cycling including end coordinate
   * in an open part.
   */
  @Test
  public final void testBackwardOpenPartCycle() {
    Coord a = new CoordXY(0, 50);
    Coord b = new CoordXY(0, 20);
    Coord c = new CoordXY(10, 20);
    Coord d = new CoordXY(10, 10);
    Coord e = new CoordXY(30, 10);
    PartCycler instance = new PartCycler();
    Part open = new Part();
    open.getCoordinates().add(a);
    open.getCoordinates().add(b);
    open.getCoordinates().add(c);
    open.getCoordinates().add(d);
    open.getCoordinates().add(e);
    List<Coord> resultCoords = instance.backward(open, b, c);
    int expSize = 0;
    assertEquals(expSize, resultCoords.size());

  }

   /**
   * Tests the forward method, without cycling including end coordinates in a
 open part..
   */
  @Test
  public final void testForwardClosedPart() {
    Coord a = new CoordXY(0, 50);
    Coord b = new CoordXY(0, 20);
    Coord c = new CoordXY(10, 20);
    Coord d = new CoordXY(10, 10);
    Coord e = new CoordXY(30, 10);
    PartCycler instance = new PartCycler();
    Part closed = new Part();
    closed.setClosed(true);
    closed.getCoordinates().add(a);
    closed.getCoordinates().add(b);
    closed.getCoordinates().add(c);
    closed.getCoordinates().add(d);
    closed.getCoordinates().add(e);

    List<Coord> resultCoords = instance.forward(closed, b, e);
    Coord[] expCoord = new Coord[]{b, c, d, e};
    int expSize = 4;
    assertEquals(expSize, resultCoords.size());
    boolean expResult = true;
    for (int index = 0; index < resultCoords.size(); index++) {
      boolean result = expCoord[index].equals(resultCoords.get(index));
      assertEquals(expResult, result);
    }
  }

  /**
   * Tests the forward method, with cycling including end coordinates in a
 open part.
   */
  @Test
  public final void testForwardClosedPartCycle() {
    Coord a = new CoordXY(0, 50);
    Coord b = new CoordXY(0, 20);
    Coord c = new CoordXY(10, 20);
    Coord d = new CoordXY(10, 10);
    Coord e = new CoordXY(30, 10);
    PartCycler instance = new PartCycler();
    Part closed = new Part();
    closed.setClosed(true);
    closed.getCoordinates().add(a);
    closed.getCoordinates().add(b);
    closed.getCoordinates().add(c);
    closed.getCoordinates().add(d);
    closed.getCoordinates().add(e);
    List<Coord> resultCoords = instance.forward(closed, c, b);
    Coord[] expCoord = new Coord[]{c, d, e, a, b};
    int expSize = 5;
    assertEquals(expSize, resultCoords.size());
    boolean expResult = true;
    for (int index = 0; index < resultCoords.size(); index++) {
      boolean result = expCoord[index].equals(resultCoords.get(index));
      assertEquals(expResult, result);
    }
  }

  /**
   * Tests the backward method, without cycling including end coordinates
 in a open part.
   */
  @Test
  public final void testBackwardClosedPart() {
    Coord a = new CoordXY(0, 50);
    Coord b = new CoordXY(0, 20);
    Coord c = new CoordXY(10, 20);
    Coord d = new CoordXY(10, 10);
    Coord e = new CoordXY(30, 10);
    PartCycler instance = new PartCycler();
    Part closed = new Part();
    closed.setClosed(true);
    closed.getCoordinates().add(a);
    closed.getCoordinates().add(b);
    closed.getCoordinates().add(c);
    closed.getCoordinates().add(d);
    closed.getCoordinates().add(e);
    List<Coord> resultCoords = instance.backward(closed, e, b);
    Coord[] expCoord = new Coord[]{e, d, c, b};
    int expSize = 4;
    assertEquals(expSize, resultCoords.size());
    boolean expResult = true;
    for (int index = 0; index < resultCoords.size(); index++) {
      boolean result = expCoord[index].equals(resultCoords.get(index));
      assertEquals(expResult, result);
    }
  }

  /**
   * Tests the backward method, with cycling including end coordinate
 in a open part.
   */
  @Test
  public final void testBackwardClosedPartCycle() {
    Coord a = new CoordXY(0, 50);
    Coord b = new CoordXY(0, 20);
    Coord c = new CoordXY(10, 20);
    Coord d = new CoordXY(10, 10);
    Coord e = new CoordXY(30, 10);
    PartCycler instance = new PartCycler();
    Part closed = new Part();
    closed.setClosed(true);
    closed.getCoordinates().add(a);
    closed.getCoordinates().add(b);
    closed.getCoordinates().add(c);
    closed.getCoordinates().add(d);
    closed.getCoordinates().add(e);
    List<Coord> resultCoords = instance.backward(closed, b, d);
    Coord[] expCoord = new Coord[]{b, a, e, d};
    int expSize = 4;
    assertEquals(expSize, resultCoords.size());
    boolean expResult = true;
    for (int index = 0; index < resultCoords.size(); index++) {
      boolean result = expCoord[index].equals(resultCoords.get(index));
      assertEquals(expResult, result);
    }
  }


  /**
   * Tests the forward method, without cycling including end coordinates.
   */
  @Test
  public final void testForwardCoord() {
    Coord a = new CoordXY(0, 50);
    Coord b = new CoordXY(0, 20);
    Coord c = new CoordXY(10, 20);
    Coord d = new CoordXY(10, 10);
    Coord e = new CoordXY(30, 10);
    PartCycler instance = new PartCycler();
    List<Coord> coords = new ArrayList<>();
    coords.add(a);
    coords.add(b);
    coords.add(c);
    coords.add(d);
    coords.add(e);
    List<Coord> resultCoords = instance.forward(coords, a, e);
    Coord[] expCoord = new Coord[]{a, b, c, d, e};
    int expSize = 5;
    assertEquals(expSize, resultCoords.size());
    boolean expResult = true;
    for (int index = 0; index < resultCoords.size(); index++) {
      boolean result = expCoord[index].equals(resultCoords.get(index));
      assertEquals(expResult, result);
    }
  }

  /**
   * Tests the forward method, with cycling including end coordinates.
   */
  @Test
  public final void testForwardCoordCycle() {
    Coord a = new CoordXY(0, 50);
    Coord b = new CoordXY(0, 20);
    Coord c = new CoordXY(10, 20);
    Coord d = new CoordXY(10, 10);
    Coord e = new CoordXY(30, 10);
    PartCycler instance = new PartCycler();
    List<Coord> coords = new ArrayList<>();
    coords.add(a);
    coords.add(b);
    coords.add(c);
    coords.add(d);
    coords.add(e);
    List<Coord> resultCoords = instance.forward(coords, c, b);
    Coord[] expCoord = new Coord[]{c, d, e, a, b};
    int expSize = 5;
    assertEquals(expSize, resultCoords.size());
    boolean expResult = true;
    for (int index = 0; index < resultCoords.size(); index++) {
      boolean result = expCoord[index].equals(resultCoords.get(index));
      assertEquals(expResult, result);
    }
  }

  /**
   * Tests the backward method, without cycling including end coordinates.
   */
  @Test
  public final void testBackwardCoord() {
    Coord a = new CoordXY(0, 50);
    Coord b = new CoordXY(0, 20);
    Coord c = new CoordXY(10, 20);
    Coord d = new CoordXY(10, 10);
    Coord e = new CoordXY(30, 10);
    PartCycler instance = new PartCycler();
    List<Coord> coords = new ArrayList<>();
    coords.add(a);
    coords.add(b);
    coords.add(c);
    coords.add(d);
    coords.add(e);
    List<Coord> resultCoords = instance.backward(coords, e, a);
    Coord[] expCoord = new Coord[]{e, d, c, b, a};
    int expSize = 5;
    assertEquals(expSize, resultCoords.size());
    boolean expResult = true;
    for (int index = 0; index < resultCoords.size(); index++) {
      boolean result = expCoord[index].equals(resultCoords.get(index));
      assertEquals(expResult, result);
    }
  }

  /**
   * Tests the backward method, with cycling including end coordinates.
   */
  @Test
  public final void testBackwardCoordCycle() {
    Coord a = new CoordXY(0, 50);
    Coord b = new CoordXY(0, 20);
    Coord c = new CoordXY(10, 20);
    Coord d = new CoordXY(10, 10);
    Coord e = new CoordXY(30, 10);
    PartCycler instance = new PartCycler();
    List<Coord> coords = new ArrayList<>();
    coords.add(a);
    coords.add(b);
    coords.add(c);
    coords.add(d);
    coords.add(e);
    List<Coord> resultCoords = instance.backward(coords, b, c);
    Coord[] expCoord = new Coord[]{b, a, e, d, c};
    int expSize = 5;
    assertEquals(expSize, resultCoords.size());
    boolean expResult = true;
    for (int index = 0; index < resultCoords.size(); index++) {
      boolean result = expCoord[index].equals(resultCoords.get(index));
      assertEquals(expResult, result);
    }
  }

  /**
   * Tests the forward method, without cycling including endpoints.
   */
  @Test
  public final void testForward() {
    Coord a = new CoordXY(0, 50);
    Coord b = new CoordXY(0, 20);
    Coord c = new CoordXY(10, 20);
    Coord d = new CoordXY(10, 10);
    Coord e = new CoordXY(30, 10);
    PartCycler instance = new PartCycler();
    List<Coord> coords = new ArrayList<>();
    coords.add(a);
    coords.add(b);
    coords.add(c);
    coords.add(d);
    coords.add(e);
    List<Coord> resultCoords = instance.forward(coords, 0, 4);
    Coord[] expCoord = new Coord[]{a, b, c, d, e};
    int expSize = 5;
    assertEquals(expSize, resultCoords.size());
    boolean expResult = true;
    for (int index = 0; index < resultCoords.size(); index++) {
      boolean result = expCoord[index].equals(resultCoords.get(index));
      assertEquals(expResult, result);
    }
  }

  /**
   * Tests the forward method, without cycling including endpoints.
   */
  @Test
  public final void testForwardStartOnly() {
    Coord a = new CoordXY(0, 50);
    Coord b = new CoordXY(0, 20);
    Coord c = new CoordXY(10, 20);
    Coord d = new CoordXY(10, 10);
    Coord e = new CoordXY(30, 10);
    PartCycler instance = new PartCycler();
    List<Coord> coords = new ArrayList<>();
    coords.add(a);
    coords.add(b);
    coords.add(c);
    coords.add(d);
    coords.add(e);
    List<Coord> resultCoords = instance.forward(coords, 2);
    Coord[] expCoord = new Coord[]{c, d, e, a, b};
    int expSize = 5;
    assertEquals(expSize, resultCoords.size());
    boolean expResult = true;
    for (int index = 0; index < resultCoords.size(); index++) {
      boolean result = expCoord[index].equals(resultCoords.get(index));
      assertEquals(expResult, result);
    }
  }


  /**
   * Tests the forward method, with cycling including endpoints.
   */
  @Test
  public final void testForwardCycle() {
    Coord a = new CoordXY(0, 50);
    Coord b = new CoordXY(0, 20);
    Coord c = new CoordXY(10, 20);
    Coord d = new CoordXY(10, 10);
    Coord e = new CoordXY(30, 10);
    PartCycler instance = new PartCycler();
    List<Coord> coords = new ArrayList<>();
    coords.add(a);
    coords.add(b);
    coords.add(c);
    coords.add(d);
    coords.add(e);
    List<Coord> resultCoords = instance.forward(coords, 2, 1);
    Coord[] expCoord = new Coord[]{c, d, e, a, b};
    int expSize = 5;
    assertEquals(expSize, resultCoords.size());
    boolean expResult = true;
    for (int index = 0; index < resultCoords.size(); index++) {
      boolean result = expCoord[index].equals(resultCoords.get(index));
      assertEquals(expResult, result);
    }
  }

  /**
   * Tests the backward method, without cycling including endpoints.
   */
  @Test
  public final void testBackward() {
    Coord a = new CoordXY(0, 50);
    Coord b = new CoordXY(0, 20);
    Coord c = new CoordXY(10, 20);
    Coord d = new CoordXY(10, 10);
    Coord e = new CoordXY(30, 10);
    PartCycler instance = new PartCycler();
    List<Coord> coords = new ArrayList<>();
    coords.add(a);
    coords.add(b);
    coords.add(c);
    coords.add(d);
    coords.add(e);
    List<Coord> resultCoords = instance.backward(coords, 4, 0);
    Coord[] expCoord = new Coord[]{e, d, c, b, a};
    int expSize = 5;
    assertEquals(expSize, resultCoords.size());
    boolean expResult = true;
    for (int index = 0; index < resultCoords.size(); index++) {
      boolean result = expCoord[index].equals(resultCoords.get(index));
      assertEquals(expResult, result);
    }
  }

  /**
   * Tests the backward method, without cycling including endpoints.
   */
  @Test
  public final void testBackwardStartOnly() {
    Coord a = new CoordXY(0, 50);
    Coord b = new CoordXY(0, 20);
    Coord c = new CoordXY(10, 20);
    Coord d = new CoordXY(10, 10);
    Coord e = new CoordXY(30, 10);
    PartCycler instance = new PartCycler();
    List<Coord> coords = new ArrayList<>();
    coords.add(a);
    coords.add(b);
    coords.add(c);
    coords.add(d);
    coords.add(e);
    List<Coord> resultCoords = instance.backward(coords, 3);
    Coord[] expCoord = new Coord[]{d, c, b, a, e};
    int expSize = 5;
    assertEquals(expSize, resultCoords.size());
    boolean expResult = true;
    for (int index = 0; index < resultCoords.size(); index++) {
      boolean result = expCoord[index].equals(resultCoords.get(index));
      assertEquals(expResult, result);
    }
  }


  /**
   * Tests the backward method, with cycling including endpoints.
   */
  @Test
  public final void testBackwardCycle() {
    Coord a = new CoordXY(0, 50);
    Coord b = new CoordXY(0, 20);
    Coord c = new CoordXY(10, 20);
    Coord d = new CoordXY(10, 10);
    Coord e = new CoordXY(30, 10);
    PartCycler instance = new PartCycler();
    List<Coord> coords = new ArrayList<>();
    coords.add(a);
    coords.add(b);
    coords.add(c);
    coords.add(d);
    coords.add(e);
    List<Coord> resultCoords = instance.backward(coords, 1, 2);
    Coord[] expCoord = new Coord[]{b, a, e, d, c};
    int expSize = 5;
    assertEquals(expSize, resultCoords.size());
    boolean expResult = true;
    for (int index = 0; index < resultCoords.size(); index++) {
      boolean result = expCoord[index].equals(resultCoords.get(index));
      assertEquals(expResult, result);
    }
  }

  /**
   * Tests the forward method, without cycling excluding endpoints.
   */
  @Test
  public final void testForwardBetween() {
    Coord a = new CoordXY(0, 50);
    Coord b = new CoordXY(0, 20);
    Coord c = new CoordXY(10, 20);
    Coord d = new CoordXY(10, 10);
    Coord e = new CoordXY(30, 10);
    PartCycler instance = new PartCycler();
    List<Coord> coords = new ArrayList<>();
    coords.add(a);
    coords.add(b);
    coords.add(c);
    coords.add(d);
    coords.add(e);
    List<Coord> resultCoords = instance.forwardBetween(coords, 0, 4);
    Coord[] expCoord = new Coord[]{b, c, d};
    int expSize = 3;
    assertEquals(expSize, resultCoords.size());
    boolean expResult = true;
    for (int index = 0; index < resultCoords.size(); index++) {
      boolean result = expCoord[index].equals(resultCoords.get(index));
      assertEquals(expResult, result);
    }
  }

  /**
   * Tests the forward method, with cycling excluding endpoints.
   */
  @Test
  public final void testForwardBetweenCycle() {
    Coord a = new CoordXY(0, 50);
    Coord b = new CoordXY(0, 20);
    Coord c = new CoordXY(10, 20);
    Coord d = new CoordXY(10, 10);
    Coord e = new CoordXY(30, 10);
    PartCycler instance = new PartCycler();
    List<Coord> coords = new ArrayList<>();
    coords.add(a);
    coords.add(b);
    coords.add(c);
    coords.add(d);
    coords.add(e);
    List<Coord> resultCoords = instance.forwardBetween(coords, 2, 1);
    Coord[] expCoord = new Coord[]{d, e, a};
    int expSize = 3;
    assertEquals(expSize, resultCoords.size());
    boolean expResult = true;
    for (int index = 0; index < resultCoords.size(); index++) {
      boolean result = expCoord[index].equals(resultCoords.get(index));
      assertEquals(expResult, result);
    }
  }

  /**
   * Tests the backward method, without cycling excluding endpoints.
   */
  @Test
  public final void testBackwardBetween() {
    Coord a = new CoordXY(0, 50);
    Coord b = new CoordXY(0, 20);
    Coord c = new CoordXY(10, 20);
    Coord d = new CoordXY(10, 10);
    Coord e = new CoordXY(30, 10);
    PartCycler instance = new PartCycler();
    List<Coord> coords = new ArrayList<>();
    coords.add(a);
    coords.add(b);
    coords.add(c);
    coords.add(d);
    coords.add(e);
    List<Coord> resultCoords = instance.backwardBetween(coords, 4, 0);
    Coord[] expCoord = new Coord[]{d, c, b};
    int expSize = 3;
    assertEquals(expSize, resultCoords.size());
    boolean expResult = true;
    for (int index = 0; index < resultCoords.size(); index++) {
      boolean result = expCoord[index].equals(resultCoords.get(index));
      assertEquals(expResult, result);
    }
  }

  /**
   * Tests the backward method, without cycling excluding endpoints.
   */
  @Test
  public final void testBackwardBetweenCycle() {
    Coord a = new CoordXY(0, 50);
    Coord b = new CoordXY(0, 20);
    Coord c = new CoordXY(10, 20);
    Coord d = new CoordXY(10, 10);
    Coord e = new CoordXY(30, 10);
    PartCycler instance = new PartCycler();
    List<Coord> coords = new ArrayList<>();
    coords.add(a);
    coords.add(b);
    coords.add(c);
    coords.add(d);
    coords.add(e);
    List<Coord> resultCoords = instance.backwardBetween(coords, 1, 2);
    Coord[] expCoord = new Coord[]{a, e, d};
    int expSize = 3;
    assertEquals(expSize, resultCoords.size());
    boolean expResult = true;
    for (int index = 0; index < resultCoords.size(); index++) {
      boolean result = expCoord[index].equals(resultCoords.get(index));
      assertEquals(expResult, result);
    }
  }

}
