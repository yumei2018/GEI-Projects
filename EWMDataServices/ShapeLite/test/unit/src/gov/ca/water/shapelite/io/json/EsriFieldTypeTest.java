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
package gov.ca.water.shapelite.io.json;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class EsriFieldTypeTest {

  public EsriFieldTypeTest() {
  }

  /**
   * Test of getAll method, of class EsriFieldType.
   */
  @Test
  public void testGetAll() {
    System.out.println("getAll");
    List<String> expResult = new ArrayList<>();
    expResult.add("esriFieldTypeSmallInteger");
    expResult.add("esriFieldTypeInteger");
    expResult.add("esriFieldTypeSingle");
    expResult.add("esriFieldTypeDouble");
    expResult.add("esriFieldTypeString");
    expResult.add("esriFieldTypeDate");
    expResult.add("esriFieldTypeOID");
    expResult.add("esriFieldTypeGeometry");
    expResult.add("esriFieldTypeBlob");
    expResult.add("esriFieldTypeRaster");
    expResult.add("esriFieldTypeGUID");
    expResult.add("esriFieldTypeGlobalID");
    List<String> result = EsriFieldType.getAll();
    for (int i = 0; i < expResult.size(); i++) {
      assertEquals(expResult.get(i), result.get(i));
    }
    assertEquals(expResult.size(), result.size());
  }

}
