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

import gov.ca.water.shapelite.projection.Projections;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Scanner;
import org.junit.Test;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class EsriJsonUtilsTest {

  /**
   * A polygon test string.
   */
  public String polygonTest;

  /**
   * Creates a new EsriJsonUtils test.
   */
  public EsriJsonUtilsTest() {
  }

  /**
   * Gets a pre-configured polygon from a stored example json.
   *
   * @return
   */
  String getPolygonFeatureset() {
    if (polygonTest == null) {
      InputStream stream = this.getClass().getResourceAsStream(
          "resources/PolygonExample.json");
      Scanner s = new Scanner(stream).useDelimiter("\\A");
      if (s.hasNext()) {
        polygonTest = s.next();
      }

    }
    return polygonTest;
  }

  /**
   * Test of esriFeatureCollectionToShapefile method, of class EsriJsonUtils.
   *
   * @throws java.lang.Exception
   */
  @Test
  public void testEsriFeatureCollectionToShapefile() throws Exception {
    System.out.println("esriFeatureCollectionToShapefile");
    String esriJsonString = getPolygonFeatureset();
    try (FileOutputStream stream = new FileOutputStream("D:\\testPolygon.zip")) {
      EsriJsonUtils.esriFeatureCollectionToShapefile(esriJsonString, stream, "Bob");
      stream.flush();
    } catch (Exception ex) {
      boolean stop = true;
    }
  }

  @Test
  public void projectionTest() {
    try {

      String text = Projections.getEpsgEsri().get(2000);
    } catch (Exception ex) {
      boolean stop = true;
    }

  }

}
