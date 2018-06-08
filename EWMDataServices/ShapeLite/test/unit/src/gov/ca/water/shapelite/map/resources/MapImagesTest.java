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
package gov.ca.water.shapelite.map.resources;

import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.testutils.ImageCompare;
import java.awt.image.BufferedImage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class MapImagesTest {

  public MapImagesTest() {
  }

  /**
   * Test of get method, of class MapImages.
   */
  @Test
  public void testGet() {
    System.out.println("get");
    String filename = "AddShape32.png";
    Optional<BufferedImage> expResult = TestMapImages.get(filename);
    // loading from resource
    Optional<BufferedImage> result = MapImages.get(filename);
    assertTrue(ImageCompare.areEqual(expResult.get(), result.get()));
    // from hashmap
    result = MapImages.get(filename);
    assertTrue(ImageCompare.areEqual(expResult.get(), result.get()));

  }

   /**
   * Test of get method, of class MapImages.
   */
  @Test
  public void testGetNotFound() {
    System.out.println("get");
    String filename = "Fish.png";
    boolean expResult = false;
    Optional<BufferedImage> result = MapImages.get(filename);
    assertEquals(expResult, result.isPresent());
  }

   /**
   * Test of get method, of class MapImages.
   */
  @Test
  public void testIOException() {
    System.out.println("get");
    String filename = ".lla";
    boolean expResult = false;
    Optional<BufferedImage> result = MapImages.get(filename);
    assertEquals(expResult, result.isPresent());
  }

}
