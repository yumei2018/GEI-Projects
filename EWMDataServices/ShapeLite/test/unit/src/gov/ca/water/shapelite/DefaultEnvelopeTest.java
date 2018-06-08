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
package gov.ca.water.shapelite;

import gov.ca.water.shapelite.coordinate.CoordM;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.coordinate.CoordZ;
import gov.ca.water.shapelite.events.EnvelopeChangedEvent;
import java.awt.geom.Rectangle2D;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import gov.ca.water.shapelite.coordinate.ObservableCoord;
import gov.ca.water.shapelite.segment.DefaultSegment;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class DefaultEnvelopeTest {

  /**
   * A boolean to be toggled by callback methods.
   */
  private boolean worked;

  /**
   * M.
   */
  private static final int M = Coord.Index.M;

  /**
   * Z.
   */
  private static final int Z = Coord.Index.Z;

  /**
   * A 2D envelope.
   */
  private static final DefaultEnvelope DEFAULT_ENV_2D
      = new DefaultEnvelope(1, 2, 3, 4);

  /**
   * A 2D envelope.
   */
  private static final DefaultEnvelope DEFAULT_ENV_M
      = new DefaultEnvelope(new CoordM(1, 2, 5), new CoordM(3, 4, 6));

  /**
   * A 3D envelope.
   */
  private static final DefaultEnvelope DEFAULT_ENV_3D
      = new DefaultEnvelope(new CoordZ(1, 2, 3), new CoordZ(4, 5, 6));

  /**
   * Test of addEnvelopeChangedListener method, of class DefaultEnvelope.
   */
  @Test
  public final void testEnvelopeChangedListener() {
    System.out.println("addEnvelopeChangedListener");
    worked = false;
    EnvelopeChangedEvent.Listener listener = new EnvelopeChangedEvent.Listener() {

      @Override
      public void envelopeChanged(EnvelopeChangedEvent e) {
        worked = true;
        if (e.getEnvelope().getMin().getX() != 1
            && e.getEnvelope().getMin().getY() != 2
            && e.getEnvelope().getMax().getX() != 3
            && e.getEnvelope().getMax().getY() != 4) {
          fail("Envelope Changed fired, but argument was incorrect.");
        }
      }
    };
    DefaultEnvelope instance = new DefaultEnvelope(1, 2, 3, 4);
    instance.addEnvelopeChangedListener(listener);
    instance.fireEnvelopeChanged(new EnvelopeChangedEvent(this, instance));
    boolean expResult = true;
    assertEquals("Event was not successfully fired.", expResult, worked);
    worked = false;
    instance.removeEnvelopeChangedListener(listener);
    instance.fireEnvelopeChanged(new EnvelopeChangedEvent(this, instance));
    expResult = false;
    assertEquals("Event was fired, even after handler should have been removed.", expResult, worked);

  }

  /**
   * Test of containsPoint method, of class DefaultEnvelope.
   */
  @Test
  public void testContainsPointBothZ() {
    System.out.println("containsPoint");
    Coord point = new CoordZ(1, 1, 1);
    DefaultEnvelope instance = new DefaultEnvelope(new CoordZ(0, 0, 0), new CoordZ(2, 2, 2));
    boolean expResult = true;
    boolean result = instance.containsPoint(point);
    assertEquals(expResult, result);
  }

  /**
   * Test of containsPoint method, of class DefaultEnvelope.
   */
  @Test
  public void testContainsPointBothXY() {
    System.out.println("containsPoint");
    Coord point = new CoordXY(2, 2);
    DefaultEnvelope instance = new DefaultEnvelope(new CoordXY(0, 0), new CoordXY(4, 4));
    boolean expResult = true;
    boolean result = instance.containsPoint(point);
    assertEquals(expResult, result);
  }

  /**
   * Test of containsPoint method, of class DefaultEnvelope.
   */
  @Test
  public void testContainsPointZEnvXY() {
    System.out.println("containsPoint");
    Coord point = new CoordZ(1, 1, 1);
    DefaultEnvelope instance = new DefaultEnvelope(new CoordXY(0, 0), new CoordXY(2, 2));
    boolean expResult = true;
    boolean result = instance.containsPoint(point);
    assertEquals(expResult, result);
  }

  @Test
  public void testConstructorCoords() {
    Coord min = new CoordZ(1, 2, 3);
    Coord max = new CoordZ(4, 5, 6);
    DefaultEnvelope env = new DefaultEnvelope(min, max);
    boolean expResult = true;
    boolean result = min.equals(env.getMin());
    assertEquals(expResult, result);
    result = max.equals(env.getMax());
    assertEquals(expResult, result);
    boolean stop = true;
  }

  /**
   * Test of getXY method, of class DefaultEnvelope.
   */
  @Test
  public void testGetXY_FOUR() {
    System.out.println("getXY_FOUR");
    double[] values = new double[]{1, 2, 3, 4};
    DefaultEnvelope expResult = new DefaultEnvelope(1, 2, 3, 4);
    DefaultEnvelope result = DefaultEnvelope.getXY(values);
    assertEquals(expResult, result);
  }

  /**
   * Test of getXY method, of class DefaultEnvelope.
   */
  @Test
  public void testGetXY_EIGHT() {
    System.out.println("getXY_EIGHT");
    double[] values = new double[]{1, 2, 3, 4};
    DefaultEnvelope expEnv = new DefaultEnvelope(new CoordZ(1, 2, 7, 5), new CoordZ(3, 4, 8, 6));
    DefaultEnvelope resultEnv = DefaultEnvelope.getZ(values);
    resultEnv.getMin().set(M, 5);
    resultEnv.getMin().set(Z, 7);
    resultEnv.getMax().set(M, 6);
    resultEnv.getMax().set(Z, 8);
    boolean expResult = true;
    boolean result = expEnv.equals(resultEnv);
    assertEquals(expResult, result);
  }

  /**
   * Test of getM method, of class DefaultEnvelope.
   */
  @Test
  public final void testGetM() {
    System.out.println("getM");
    double[] values = new double[]{1, 2, 3, 4};
    DefaultEnvelope expEnv = DEFAULT_ENV_M.copy();
    DefaultEnvelope resultEnv = DefaultEnvelope.getM(values);
    resultEnv.getMin().set(M, 5);
    resultEnv.getMax().set(M, 6);
    boolean expResult = true;
    boolean result = expEnv.equals(resultEnv);
    assertEquals(expResult, result);
  }

  /**
   * Test of getZ method, of class DefaultEnvelope.
   */
  @Test
  public final void testGetZ() {
    System.out.println("getZ");
    double[] values = new double[]{1, 2, 4, 5};
    DefaultEnvelope expEnv = DEFAULT_ENV_3D.copy();
    DefaultEnvelope resultEnv = DefaultEnvelope.getZ(values);
    resultEnv.getMin().set(Z, 3);
    resultEnv.getMax().set(Z, 6);
    boolean expResult = true;
    boolean result = expEnv.equals(resultEnv);
    assertEquals(expResult, result);

  }

  /**
   * Test of copy method, of class DefaultEnvelope.
   */
  @Test
  public final void testCopy() {
    System.out.println("copy");
    DefaultEnvelope instance
        = new DefaultEnvelope(new double[]{1, 2, 3, 4}, true, true);
    DefaultEnvelope expEnv = instance;
    DefaultEnvelope resultEnv = instance.copy();
    boolean expResult = true;
    boolean result = expEnv.equals(resultEnv);
    assertEquals(expResult, result);
  }

  /**
   * Test of copyProperties method, of class DefaultEnvelope.
   */
  @Test
  public final void testCopyProperties_Envelope() {
    System.out.println("copyProperties");
    Envelope other = DEFAULT_ENV_2D.copy();
    DefaultEnvelope instance = new DefaultEnvelope();
    boolean expResult = false;
    boolean result = other.equals(instance);
    assertEquals(expResult, result);
    instance.copyProperties(other);
    expResult = true;
    result = other.equals(instance);
    assertEquals(expResult, result);

  }

  /**
   * Test of copyProperties method, of class DefaultEnvelope.
   */
  @Test
  public void testCopyProperties_Coord() {
    System.out.println("copyProperties");
    Coord other = new CoordXY(1, 2);
    DefaultEnvelope instance = new DefaultEnvelope();
    instance.copyProperties(other);
    DefaultEnvelope expResult = new DefaultEnvelope(1, 2, 1, 2);
    assertEquals(instance, expResult);
  }

  /**
   * Test of distance method, of class DefaultEnvelope.
   */
  @Test
  public void testDistance() {
    System.out.println("distance");
    Coord position = new CoordXY(0, 0);
    DefaultEnvelope instance = new DefaultEnvelope(3, 4, 5, 6);
    double expResult = 5;
    double result = instance.distance(position);
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of equals method, of class DefaultEnvelope.
   */
  @Test
  public void testEquals() {
    System.out.println("equals");
    Object obj = new DefaultEnvelope(1, 2, 3, 4);
    DefaultEnvelope instance = new DefaultEnvelope(1, 2, 3, 4);
    boolean expResult = true;
    boolean result = instance.equals(obj);
    assertEquals(expResult, result);
    expResult = false;

    obj = new DefaultEnvelope(1, 2, 3, 5);
    result = instance.equals(obj);
    assertEquals(expResult, result);

    obj = new DefaultEnvelope(1, 2, 4, 4);
    result = instance.equals(obj);
    assertEquals(expResult, result);

    obj = new DefaultEnvelope(1, 4, 3, 4);
    result = instance.equals(obj);
    assertEquals(expResult, result);

    obj = new DefaultEnvelope(4, 2, 3, 4);
    result = instance.equals(obj);
    assertEquals(expResult, result);
  }

  //<editor-fold defaultstate="collapsed" desc="Default Tests">
  /**
   * Test of expandToInclude method, of class DefaultEnvelope.
   */
  @Test
  public void testExpandToInclude_Coord() {
    System.out.println("expandToInclude");
    Coord coordinate = new CoordXY(5, 5);
    DefaultEnvelope instance = new DefaultEnvelope(1, 1, 2, 2);
    instance.expandToInclude(coordinate);
    Coord max = instance.getMax();
    double expResult = 5;
    assertEquals(expResult, max.getX(), Double.MIN_NORMAL);
    assertEquals(expResult, max.getY(), Double.MIN_NORMAL);
  }

  /**
   * Test of expandToInclude method, of class DefaultEnvelope.
   */
  @Test
  public void testExpandToInclude_3args() {
    System.out.println("expandToInclude");
    Coord coordinate = new CoordZ(8, 8, 8, 8);
    boolean hasM = true;
    boolean hasZ = true;
    DefaultEnvelope instance = DEFAULT_ENV_3D.copy();
    instance.expandToInclude(coordinate, hasM, hasZ);
    Coord max = instance.getMax();
    double expResult = 8;
    assertEquals(expResult, max.getX(), Double.MIN_NORMAL);
    assertEquals(expResult, max.getY(), Double.MIN_NORMAL);
    assertEquals(expResult, max.get(Coord.Index.Z), Double.MIN_NORMAL);
  }

  /**
   * Test of expandToInclude method, of class DefaultEnvelope.
   */
  @Test
  public void testExpandToInclude_Envelope() {
    System.out.println("expandToInclude");
    DefaultEnvelope instance = DEFAULT_ENV_2D.copy();
    DefaultEnvelope other = new DefaultEnvelope(3, 3, 5, 5);
    instance.expandToInclude(other);
    Coord max = instance.getMax();
    double expResult = 5;
    assertEquals(expResult, max.getX(), Double.MIN_NORMAL);
    assertEquals(expResult, max.getY(), Double.MIN_NORMAL);
  }

  /**
   * Test of expandToInclude method, of class DefaultEnvelope.
   */
  @Test
  public void testExpandToInclude_Rectangle2D() {
    System.out.println("expandToInclude");
    Rectangle2D other = new Rectangle2D.Double(3, 3, 2, 2);
    DefaultEnvelope instance = DEFAULT_ENV_2D.copy();
    instance.expandToInclude(other);
    Coord max = instance.getMax();
    double expResult = 5;
    assertEquals(expResult, max.getX(), Double.MIN_NORMAL);
    assertEquals(expResult, max.getY(), Double.MIN_NORMAL);
  }

  /**
   * Test of getCenter method, of class DefaultEnvelope.
   */
  @Test
  public void testGetCenter() {
    System.out.println("getCenter");
    DefaultEnvelope instance = DEFAULT_ENV_2D.copy();
    CoordXY expCoord = new CoordXY(2, 3);
    CoordXY center = instance.getCenter();
    boolean expResult = true;
    boolean result = expCoord.equals(center);
    assertEquals(expResult, result);
  }

  /**
   * Test of hashCode method, of class DefaultEnvelope, to ensure that two
   * separate envelope objects will still have the same hashcode if the values
   * are the same, while the hash code of different values will test
   * differently.
   */
  @Test
  public void testHashCode() {
    System.out.println("hashCode");
    DefaultEnvelope instance = DEFAULT_ENV_2D.copy();
    DefaultEnvelope other = DEFAULT_ENV_2D.copy();
    DefaultEnvelope different = new DefaultEnvelope(2, 2, 3, 3);
    boolean expResult = true;
    boolean result = instance.hashCode() == other.hashCode();
    assertEquals(expResult, result);
    expResult = false;
    result = instance.hashCode() == different.hashCode();
    assertEquals(expResult, result);
  }

  /**
   * Test of getHeight method, of class DefaultEnvelope.
   */
  @Test
  public void testGetHeight() {
    System.out.println("getHeight");
    DefaultEnvelope instance = DEFAULT_ENV_2D.copy();
    double expResult = 2;
    double result = instance.getHeight();
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of getWidth method, of class DefaultEnvelope.
   */
  @Test
  public void testGetWidth() {
    System.out.println("getWidth");
    DefaultEnvelope instance = DEFAULT_ENV_2D.copy();
    double expResult = 2;
    double result = instance.getWidth();
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of intersect method, of class DefaultEnvelope.
   */
  @Test
  public void testIntersect() {
    System.out.println("intersect");
    Envelope other = new DefaultEnvelope(0, 5, 20, 30);
    DefaultEnvelope instance = new DefaultEnvelope(2, 3, 4, 5);
    DefaultEnvelope expResult = new DefaultEnvelope(2, 5, 4, 5);
    DefaultEnvelope result = instance.intersect(other);
    assertEquals(expResult, result);

  }

  /**
   * Test of intersects method, of class DefaultEnvelope.
   */
  @Test
  public void testIntersects_Coord() {
    System.out.println("intersects");
    Coord inside = new CoordXY(2, 3);
    Coord outside = new CoordXY(4, 5);
    DefaultEnvelope instance = DEFAULT_ENV_2D.copy();
    boolean expResult = true;
    boolean result = instance.intersects(inside);
    assertEquals(expResult, result);
    expResult = false;
    result = instance.intersects(outside);
    assertEquals(expResult, result);
  }

  /**
   * Test of intersects method, of class DefaultEnvelope.
   */
  @Test
  public void testIntersects_Envelope() {
    System.out.println("intersects");
    Envelope intersecting = new DefaultEnvelope(0, 0, 2, 2);
    Envelope nonIntersecting = new DefaultEnvelope(5, 5, 6, 6);
    DefaultEnvelope instance = DEFAULT_ENV_2D.copy();
    boolean expResult = true;
    boolean result = instance.intersects(intersecting);
    assertEquals(expResult, result);
    expResult = false;
    result = instance.intersects(nonIntersecting);
    assertEquals(expResult, result);
  }

  /**
   * Test of intersects2D method, of class DefaultEnvelope.
   */
  @Test
  public void testIntersects2D() {
    System.out.println("intersects");
    Envelope intersecting = new DefaultEnvelope(0, 0, 2, 2);
    Envelope nonIntersecting = new DefaultEnvelope(5, 5, 6, 6);
    DefaultEnvelope instance = DEFAULT_ENV_2D.copy();
    boolean expResult = true;
    boolean result = instance.intersects2D(intersecting);
    assertEquals(expResult, result);
    expResult = false;
    result = instance.intersects2D(nonIntersecting);
    assertEquals(expResult, result);

  }

  /**
   * Test of isEmpty method, of class DefaultEnvelope.
   */
  @Test
  public void testIsEmpty() {
    System.out.println("isEmpty");
    DefaultEnvelope instance = new DefaultEnvelope();
    boolean expResult = true;
    boolean result = instance.isEmpty();
    assertEquals(expResult, result);
    instance = DEFAULT_ENV_2D.copy();
    expResult = false;
    result = instance.isEmpty();
    assertEquals(expResult, result);
  }

  /**
   * Test of isFlat method, of class DefaultEnvelope.
   */
  @Test
  public final void testIsFlat() {
    System.out.println("isFlat");
    DefaultEnvelope instance = DEFAULT_ENV_2D.copy();
    boolean expResult = true;
    boolean result = instance.isFlat();
    assertEquals(expResult, result);

    expResult = false;
    instance = DEFAULT_ENV_3D.copy();
    result = instance.isFlat();
    assertEquals(expResult, result);
  }

  /**
   * Test of maxDistance method, of class DefaultEnvelope.
   */
  @Test
  public void testMaxDistance() {
    System.out.println("maxDistance");
    Coord position = new CoordXY(0, 0);
    DefaultEnvelope instance = DEFAULT_ENV_2D.copy();
    double expResult = 5;
    double result = instance.maxDistance(position);
    assertEquals(expResult, result, 0.0);

  }

  /**
   * Test of toArray method, of class DefaultEnvelope.
   */
  @Test
  public void testToArray() {
    System.out.println("toArray");
    DefaultEnvelope instance = DEFAULT_ENV_2D.copy();
    double[] expResult = new double[]{1, 2, 3, 4};
    double[] result = instance.toArray();
    assertEquals(expResult.length, result.length);
    for (int i = 0; i < expResult.length; i++) {
      assertEquals(expResult[i], result[i], 0.0);
    }

  }

  /**
   * Test of toArray2D method, of class DefaultEnvelope.
   */
  @Test
  public void testToArray2D() {
    System.out.println("toArray2D");
    System.out.println("toArray");
    DefaultEnvelope instance = DEFAULT_ENV_2D.copy();
    double[] expResult = new double[]{1, 2, 3, 4};
    double[] result = instance.toArray2D();
    assertEquals(expResult.length, result.length);
    for (int i = 0; i < expResult.length; i++) {
      assertEquals(expResult[i], result[i], 0.0);
    }
  }

  /**
   * Test of toRectangle2D method, of class DefaultEnvelope.
   */
  @Test
  public void testToRectangle2D() {
    System.out.println("toRectangle2D");
    DefaultEnvelope instance = new DefaultEnvelope(1, 2, 3, 4);
    Rectangle2D original = new Rectangle2D.Double(1, 2, 2, 2);
    Rectangle2D export = instance.toRectangle2D();
    boolean expResult = true;
    boolean result = export.equals(original);
    assertEquals(expResult, result);
  }

  /**
   * Test of getBorder method, of class DefaultEnvelope.
   */
  @Test
  public void testGetBorder() {
    System.out.println("getBorder");
    DefaultEnvelope instance = new DefaultEnvelope(1, 1, 2, 2);
    Part part = new Part();
    part.getCoordinates().add(new CoordXY(1, 2));
    part.getCoordinates().add(new CoordXY(2, 2));
    part.getCoordinates().add(new CoordXY(2, 1));
    part.getCoordinates().add(new CoordXY(1, 1));
    part.getCoordinates().add(new CoordXY(1, 2));
    Shape resultShape = instance.getBorder();
    for (int i = 0; i < resultShape.getParts().get(0).getCoordinates().size(); i++) {
      boolean expResult = true;
      boolean result = part.getCoordinates().get(i).equals(
          resultShape.getParts().get(0).getCoordinates().get(i));
      assertEquals(expResult, result);
    }
  }

  /**
   * Test of zoom method, of class DefaultEnvelope.
   */
  @Test
  public void testZoom_double() {
    System.out.println("zoom");
    double scale = 2;
    DefaultEnvelope instance = new DefaultEnvelope(0, 0, 4, 6);
    DefaultEnvelope expResult = new DefaultEnvelope(-2, -3, 6, 9);
    DefaultEnvelope result = instance.zoom(scale);
    assertEquals(expResult, result);

  }

  /**
   * Test of zoom method, of class DefaultEnvelope.
   */
  @Test
  public void testZoom_double_Coord() {
    System.out.println("zoom");
    double scale = 2;
    Coord anchor = new CoordXY(0, 0);
    DefaultEnvelope instance = new DefaultEnvelope(0, 0, 4, 6);
    DefaultEnvelope expResult = new DefaultEnvelope(0, 0, 8, 12);
    DefaultEnvelope result = instance.zoom(scale, anchor);
    assertEquals(expResult, result);

  }

  /**
   * Test of zoom method, of class DefaultEnvelope.
   */
  @Test
  public void testZoom_double_Coord2() {
    System.out.println("zoom");
    double scale = 2;
    Coord anchor = new CoordXY(10, 10);
    DefaultEnvelope instance = new DefaultEnvelope(5, 5, 10, 10);
    DefaultEnvelope expResult = new DefaultEnvelope(0, 0, 10, 10);
    DefaultEnvelope result = instance.zoom(scale, anchor);
    assertEquals(expResult, result);

  }

  /**
   * Test of within2D method, of class DefaultEnvelope.
   */
  @Test
  public void testWithin2D() {
    System.out.println("within2D");
    Envelope other = new DefaultEnvelope(1.5, 2.5, 2.5, 3.5);
    DefaultEnvelope instance = DEFAULT_ENV_2D.copy();
    boolean expResult = false;
    boolean result = instance.within2D(other);
    assertEquals(expResult, result);
    expResult = true;
    result = other.within2D(instance);
    assertEquals(expResult, result);
  }

  /**
   * Test of contains2D method, of class DefaultEnvelope.
   */
  @Test
  public void testContains2D() {
    System.out.println("contains2D");
    Envelope other = new DefaultEnvelope(1.5, 2.5, 2.5, 3.5);
    DefaultEnvelope instance = DEFAULT_ENV_2D.copy();
    boolean expResult = true;
    boolean result = instance.contains2D(other);
    assertEquals(expResult, result);
    expResult = false;
    result = other.contains2D(instance);
    assertEquals(expResult, result);
  }

  /**
   * Test of containsPoint2D method, of class DefaultEnvelope.
   */
  @Test
  public final void testContainsPoint2D_True() {
    System.out.println("containsPoint2D");
    Coord point = new CoordXY(1, 2);
    DefaultEnvelope instance = new DefaultEnvelope(0, 0, 3, 3);
    boolean expResult = true;
    boolean result = instance.containsPoint2D(point);
    assertEquals(expResult, result);
  }

  /**
   * Test of containsPoint2D method, of class DefaultEnvelope.
   */
  @Test
  public final void testContainsPoint2D_FalseX() {
    System.out.println("containsPoint2D");
    Coord point = new CoordXY(5, 2);
    DefaultEnvelope instance = new DefaultEnvelope(0, 0, 3, 3);
    boolean expResult = false;
    boolean result = instance.containsPoint2D(point);
    assertEquals(expResult, result);
  }

  /**
   * Test of containsPoint2D method, of class DefaultEnvelope.
   */
  @Test
  public final void testContainsPoint2D_FalseY() {
    System.out.println("containsPoint2D");
    Coord point = new CoordXY(5, 2);
    DefaultEnvelope instance = new DefaultEnvelope(0, 0, 3, 3);
    boolean expResult = false;
    boolean result = instance.containsPoint2D(point);
    assertEquals(expResult, result);
  }

  /**
   * Test of correct2D method, of class DefaultEnvelope.
   */
  @Test
  public final void testCorrect2D() {
    System.out.println("correct2D");
    Coord max = new CoordZ(4, 5, 6);
    Coord min = new CoordZ(1, 2, 3);
    DefaultEnvelope instance = new DefaultEnvelope(true, true);
    instance.getMin().copyProperties(max);
    instance.getMax().copyProperties(min);
    DefaultEnvelope corrected = new DefaultEnvelope(min, max);
    instance.correct();
    boolean expResult = true;
    boolean result = instance.equals(corrected);
    assertEquals(expResult, result);
  }

  /**
   * Test of correct2D method, of class DefaultEnvelope.
   */
  @Test
  public final void testWithoutCorrect2D() {
    System.out.println("correct2D");
    Coord max = new CoordZ(4, 5, 6);
    Coord min = new CoordZ(1, 2, 3);
    DefaultEnvelope instance = new DefaultEnvelope(true, true);
    instance.getMin().copyProperties(max);
    instance.getMax().copyProperties(min);
    DefaultEnvelope corrected = new DefaultEnvelope(min, max);
    // instance.correct2D(); Same as testCorrect2D, but without correction.
    boolean expResult = false;
    boolean result = instance.equals(corrected);
    assertEquals(expResult, result);
  }

  /**
   * Test of getMin method, of class DefaultEnvelope.
   */
  @Test
  public final void testGetMin() {
    System.out.println("getMin");
    DefaultEnvelope instance = DEFAULT_ENV_2D.copy();
    Coord expCoord = new CoordXY(1, 2);
    ObservableCoord min = instance.getMin();
    boolean expResult = true;
    boolean result = expCoord.equals(min);
    assertEquals(expResult, result);
  }

  /**
   * Test of getMax method, of class DefaultEnvelope.
   */
  @Test
  public final void testGetMax() {
    System.out.println("getMax");
    DefaultEnvelope instance = DEFAULT_ENV_2D.copy();
    Coord expResult = new CoordXY(3, 4);
    ObservableCoord result = instance.getMax();
    assertEquals(expResult, result);
  }

  //</editor-fold>
  /**
   * Test of hasZ method, of class DefaultEnvelope.
   */
  @Test
  public final void testHasZ_YES() {
    System.out.println("hasZ_YES");
    DefaultEnvelope instance = DEFAULT_ENV_3D;
    boolean expResult = true;
    boolean result = instance.hasZ();
    assertEquals(expResult, result);
  }

  /**
   * Test of hasZ method, of class DefaultEnvelope.
   */
  @Test
  public final void testHasZ_NO() {
    System.out.println("hasZ_NO");
    DefaultEnvelope instance = DEFAULT_ENV_2D;
    boolean expResult = false;
    boolean result = instance.hasZ();
    assertEquals(expResult, result);
  }

  /**
   * Test of hasM method, of class DefaultEnvelope.
   */
  @Test
  public final void testHasM_YES() {
    System.out.println("hasM_YES");
    DefaultEnvelope instance = DEFAULT_ENV_3D;
    boolean expResult = true;
    boolean result = instance.hasM();
    assertEquals(expResult, result);
  }

  /**
   * Test of hasM method, of class DefaultEnvelope.
   */
  @Test
  public final void testHasM_NO() {
    System.out.println("hasM_NO");
    DefaultEnvelope instance = DEFAULT_ENV_2D;
    boolean expResult = false;
    boolean result = instance.hasM();
    assertEquals(expResult, result);
  }

  /**
   * Test of toString method, of class DefaultEnvelope.
   */
  @Test
  public final void testToString() {
    System.out.println("toString");
    DefaultEnvelope instance = DEFAULT_ENV_2D;
    String expResult = "[1.0, 2.0]-[3.0, 4.0]";
    String result = instance.toString();
    assertEquals(expResult, result);
  }

  /**
   * Test of toJtsEnvelope method, of class DefaultEnvelope.
   */
  @Test
  public final void testToJtsEnvelope() {
    System.out.println("toJtsEnvelope");
    DefaultEnvelope instance = DEFAULT_ENV_2D;
    com.vividsolutions.jts.geom.Envelope expEnv
        = new com.vividsolutions.jts.geom.Envelope(1, 3, 2, 4);
    com.vividsolutions.jts.geom.Envelope resultEnv = instance.toJtsEnvelope();
    boolean expResult = true;
    boolean result = expEnv.equals(resultEnv);
    assertEquals(expResult, result);
  }

  @Test
  public final void testIntersection2D_Segment() {
    Segment seg = new DefaultSegment(-1, 0, 1, 0);
    Envelope env = new DefaultEnvelope(-1, -1, 1, 1);
    boolean expResult = true;
    boolean result = env.intersects2D(seg);
    assertEquals(expResult, result);
  }

  @Test
  public final void testIntersection2D_Segment_bothInside() {
    Segment seg = new DefaultSegment(-1, 0, 1, 0);
    Envelope env = new DefaultEnvelope(-2, -2, 2, 2);
    boolean expResult = true;
    boolean result = env.intersects2D(seg);
    assertEquals(expResult, result);
  }

  @Test
  public final void testIntersection2D_Segment_Edge() {
    Segment seg = new DefaultSegment(-1, 2, 1, 2);
    Envelope env = new DefaultEnvelope(-2, -2, 2, 2);
    boolean expResult = true;
    boolean result = env.intersects2D(seg);
    assertEquals(expResult, result);
  }

  @Test
  public final void testIntersection2D_Segment_rightOutside() {
    Segment seg = new DefaultSegment(-1, 0, 2, 0);
    Envelope env = new DefaultEnvelope(-2, -2, 1, 1);
    boolean expResult = true;
    boolean result = env.intersects2D(seg);
    assertEquals(expResult, result);
  }

  @Test
  public final void testIntersection2D_Segment_EdgePoint() {
    Segment seg = new DefaultSegment(-2, 2, -2, 2);
    Envelope env = new DefaultEnvelope(-2, -2, 2, 2);
    boolean expResult = true;
    boolean result = env.intersects2D(seg);
    assertEquals(expResult, result);
  }

  @Test
  public final void testIntersection2D_Segment_bothOutside() {
    Segment seg = new DefaultSegment(-2, 0, 2, 0);
    Envelope env = new DefaultEnvelope(-1, -1, 1, 1);
    boolean expResult = true;
    boolean result = env.intersects2D(seg);
    assertEquals(expResult, result);
  }

  @Test
  public final void testIntersection2D_Segment_leftOutside() {
    Segment seg = new DefaultSegment(-2, 0, 1, 0);
    Envelope env = new DefaultEnvelope(-1, -1, 2, 2);
    boolean expResult = true;
    boolean result = env.intersects2D(seg);
    assertEquals(expResult, result);
  }

  @Test
  public final void testIntersection2D_Segment2() {
    Segment seg = new DefaultSegment(-3, 0, -2, 0);
    Envelope env = new DefaultEnvelope(-1, -1, 1, 1);
    boolean expResult = false;
    boolean result = env.intersects2D(seg);
    assertEquals(expResult, result);
  }

}
