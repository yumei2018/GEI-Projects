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
package gov.ca.water.shapelite.map;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class MapTileTypeTest {

  /**
   * Test of values method, of class MapTileType.
   */
  @Test
  public final void testValues() {
    System.out.println("values");
    MapTileType[] expResult = new MapTileType[]{MapTileType.StreetMap,
      MapTileType.Satellite, MapTileType.Topo};
    MapTileType[] result = MapTileType.values();
    assertArrayEquals(expResult, result);
  }

  /**
   * Test of valueOf method, of class MapTileType.
   */
  @Test
  public final void testValueOf() {
    System.out.println("valueOf");
    String name = "StreetMap";
    MapTileType expResult = MapTileType.StreetMap;
    MapTileType result = MapTileType.valueOf(name);
    assertEquals(expResult, result);
  }

  /**
   * Test of next method, of class MapTileType.
   */
  @Test
  public final void testNext() {
    System.out.println("next");
    MapTileType instance = MapTileType.StreetMap;
    MapTileType expResult = MapTileType.Satellite;
    MapTileType result = instance.next();
    assertEquals(expResult, result);
    expResult = MapTileType.Topo;
    result = result.next();
    assertEquals(expResult, result);
    expResult = MapTileType.StreetMap;
    result = result.next();
    assertEquals(expResult, result);
  }

  /**
   * Test of from method, of class MapTileType.
   */
  @Test
  public final void testFrom() {
    System.out.println("from");
    int index = 0;
    MapTileType expResult = MapTileType.StreetMap;
    MapTileType result = MapTileType.from(index);
    assertEquals(expResult, result);
    index = 1;
    expResult = MapTileType.Satellite;
    result = MapTileType.from(index);
    assertEquals(expResult, result);
    index = 2;
    expResult = MapTileType.Topo;
    result = MapTileType.from(index);
    assertEquals(expResult, result);
  }

  /**
   * Test of getIndex method, of class MapTileType.
   */
  @Test
  public final void testGetIndex() {
    System.out.println("getIndex");
    MapTileType instance = MapTileType.StreetMap;
    int expResult = 0;
    int result = instance.getIndex();
    assertEquals(expResult, result);
    instance = MapTileType.Satellite;
    expResult = 1;
    result = instance.getIndex();
    assertEquals(expResult, result);
    instance = MapTileType.Topo;
    expResult = 2;
    result = instance.getIndex();
    assertEquals(expResult, result);

  }

}
