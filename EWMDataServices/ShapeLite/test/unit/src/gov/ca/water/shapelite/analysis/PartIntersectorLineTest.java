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

import gov.ca.water.shapelite.DefaultEnvelope;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.coordinate.CoordZ;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PartIntersectorLineTest {

  public PartIntersectorLineTest() {
  }

  /**
   * Test of intersection method, of class PartIntersectorLine.
   */
  @Test
  public final void testStartOutside() {
    System.out.println("intersection");
    Part original = new Part();
    original.getCoordinates().add(new CoordZ(0, 0, 0));
    original.getCoordinates().add(new CoordZ(5, 10, 1));
    original.getCoordinates().add(new CoordZ(10, 0, 2));
    original.getCoordinates().add(new CoordZ(0, 0, 0));
    Envelope envelope = new DefaultEnvelope(3, -2, 12, 8);
    PartIntersectorLine instance = new PartIntersectorLine();
    List<Part> result = instance.intersection(original, envelope);
    if (result == null || result.size() != 2) {
      fail("Expected 2 parts.");
    }
    for (Part part : result) {
      if (part.getCoordinates().size() == 2) {
        assertEquals("Short segment x should be equal.",
            3.0, part.getCoordinates().get(0).getX(), 0.0);
        assertEquals("Short segment y should be equal.",
            6.0, part.getCoordinates().get(0).getY(), 0.0);
        assertEquals("Short segment x should be equal.",
            4.0, part.getCoordinates().get(1).getX(), 0.0);
        assertEquals("Short segment y should be equal.",
            8.0, part.getCoordinates().get(1).getY(), 0.0);

      } else if (part.getCoordinates().size() == 3) {
        assertEquals("Short segment x should be equal.",
            6.0, part.getCoordinates().get(0).getX(), 0.0);
        assertEquals("Short segment y should be equal.",
            8.0, part.getCoordinates().get(0).getY(), 0.0);
        assertEquals("Short segment x should be equal.",
            10.0, part.getCoordinates().get(1).getX(), 0.0);
        assertEquals("Short segment y should be equal.",
            0.0, part.getCoordinates().get(1).getY(), 0.0);
        assertEquals("Short segment x should be equal.",
            3.0, part.getCoordinates().get(2).getX(), 0.0);
        assertEquals("Short segment y should be equal.",
            0.0, part.getCoordinates().get(2).getY(), 0.0);
      } else {
        fail("Unexpected part size of " + part.getCoordinates().size());
      }
    }
  }

    /**
   * Test of intersection method, of class PartIntersectorLine.
   */
  @Test
  public final void testStartOutsideNextInside() {
    System.out.println("intersection");
    Part original = new Part();
    original.getCoordinates().add(new CoordZ(0, 0, 0));
    original.getCoordinates().add(new CoordZ(5, 10, 1));
    original.getCoordinates().add(new CoordZ(10, 0, 2));
    original.getCoordinates().add(new CoordZ(0, 0, 0));
    Envelope envelope = new DefaultEnvelope(3, -2, 12, 12);
    PartIntersectorLine instance = new PartIntersectorLine();
    List<Part> result = instance.intersection(original, envelope);
    if (result == null || result.size() != 1) {
      fail("Expected 1 part.");
    }
    for (Part part : result) {
      if (part.getCoordinates().size() == 4) {
        assertEquals("Short segment x should be equal.",
            3.0, part.getCoordinates().get(0).getX(), 0.0);
        assertEquals("Short segment y should be equal.",
            6.0, part.getCoordinates().get(0).getY(), 0.0);
        assertEquals("Short segment x should be equal.",
            5.0, part.getCoordinates().get(1).getX(), 0.0);
        assertEquals("Short segment y should be equal.",
            10.0, part.getCoordinates().get(1).getY(), 0.0);
        assertEquals("Short segment x should be equal.",
            10.0, part.getCoordinates().get(2).getX(), 0.0);
        assertEquals("Short segment y should be equal.",
            0.0, part.getCoordinates().get(2).getY(), 0.0);
        assertEquals("Short segment x should be equal.",
            3.0, part.getCoordinates().get(3).getX(), 0.0);
        assertEquals("Short segment y should be equal.",
            0.0, part.getCoordinates().get(3).getY(), 0.0);

      } else {
        fail("Unexpected part size of " + part.getCoordinates().size());
      }
    }
  }



  /**
   * Test of intersection method, of class PartIntersectorLine.
   */
  @Test
  public final void testStartInside() {
    System.out.println("intersection");
    Part original = new Part();
    original.getCoordinates().add(new CoordZ(0, 0, 0));
    original.getCoordinates().add(new CoordZ(5, 10, 1));
    original.getCoordinates().add(new CoordZ(10, 0, 2));
    original.getCoordinates().add(new CoordZ(0, 0, 0));
    Envelope envelope = new DefaultEnvelope(-2, -2, 8, 8);
    PartIntersectorLine instance = new PartIntersectorLine();
    List<Part> result = instance.intersection(original, envelope);
    if (result == null || result.size() != 3) {
      fail("Expected 3 parts.");
      return;
    }
    Part first = result.get(0);
    if (first.getCoordinates().size() == 2) {
      assertEquals("Short segment x should be equal.",
          0.0, first.getCoordinates().get(0).getX(), 0.0);
      assertEquals("Short segment y should be equal.",
          0.0, first.getCoordinates().get(0).getY(), 0.0);
      assertEquals("Short segment x should be equal.",
          4.0, first.getCoordinates().get(1).getX(), 0.0);
      assertEquals("Short segment y should be equal.",
          8.0, first.getCoordinates().get(1).getY(), 0.0);

    } else {
      fail("Expected two points for first part, but got "
          + first.getCoordinates().size());
    }
    Part second = result.get(1);
    if (second.getCoordinates().size() == 2) {
      assertEquals("Short segment x should be equal.",
          6.0, second.getCoordinates().get(0).getX(), 0.0);
      assertEquals("Short segment y should be equal.",
          8.0, second.getCoordinates().get(0).getY(), 0.0);
      assertEquals("Short segment x should be equal.",
          8.0, second.getCoordinates().get(1).getX(), 0.0);
      assertEquals("Short segment y should be equal.",
          4.0, second.getCoordinates().get(1).getY(), 0.0);
    } else {
      fail("Expected two points for second part, but got "
          + second.getCoordinates().size());
    }
    Part third = result.get(2);
    if (third.getCoordinates().size() == 2) {
      assertEquals("Short segment x should be equal.",
          8.0, third.getCoordinates().get(0).getX(), 0.0);
      assertEquals("Short segment y should be equal.",
          0.0, third.getCoordinates().get(0).getY(), 0.0);
      assertEquals("Short segment x should be equal.",
          0.0, third.getCoordinates().get(1).getX(), 0.0);
      assertEquals("Short segment y should be equal.",
          0.0, third.getCoordinates().get(1).getY(), 0.0);
    } else {
      fail("Expected two points for third part, but got "
          + third.getCoordinates().size());
    }

  }

  /**
   * Test of intersection method, of class PartIntersectorLine.
   */
  @Test
  public final void testAllInside() {
    System.out.println("intersection");
    Part original = new Part();
    original.getCoordinates().add(new CoordZ(0, 0, 0));
    original.getCoordinates().add(new CoordZ(10, 10, 1));
    Envelope envelope = new DefaultEnvelope(-2, -2, 12, 12);
    PartIntersectorLine instance = new PartIntersectorLine();
    List<Part> result = instance.intersection(original, envelope);
    if (result == null || result.size() != 1) {
      fail("Expected 1 part.");
      return;
    }
    Part first = result.get(0);
    if (first.getCoordinates().size() == 2) {
      assertEquals("Short segment x should be equal.",
          0.0, first.getCoordinates().get(0).getX(), 0.0);
      assertEquals("Short segment y should be equal.",
          0.0, first.getCoordinates().get(0).getY(), 0.0);
      assertEquals("Short segment x should be equal.",
          10.0, first.getCoordinates().get(1).getX(), 0.0);
      assertEquals("Short segment y should be equal.",
          10.0, first.getCoordinates().get(1).getY(), 0.0);

    } else {
      fail("Expected two points for first part, but got "
          + first.getCoordinates().size());
    }
  }

  /**
   * Test of intersection method, of class PartIntersectorLine.
   */
  @Test
  public final void testAllOutside() {
    System.out.println("intersection");
    Part original = new Part();
    original.getCoordinates().add(new CoordZ(0, 0, 0));
    original.getCoordinates().add(new CoordZ(10, 10, 1));
    Envelope envelope = new DefaultEnvelope(-2, -2, -1, -1);
    PartIntersectorLine instance = new PartIntersectorLine();
    List<Part> result = instance.intersection(original, envelope);
    if (result == null || !result.isEmpty()) {
      fail("Expected list with 0 parts.");
    }
  }



}
