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

import com.google.gson.Gson;
import gov.ca.water.shapelite.FileHelper;
import gov.ca.water.shapelite.ShapefileException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class MapServerReaderTest {

  @Rule
  public final TemporaryFolder testFolder = new TemporaryFolder();

  public MapServerReaderTest() {
  }

  /**
   * Test export.
   *
   * @throws IOException
   * @throws java.net.MalformedURLException
   * @throws gov.ca.water.shapelite.ShapefileException
   */
  @Test
  public void testExport() throws IOException, MalformedURLException,
      ShapefileException {

    File f = testFolder.newFile("HighWaterStaking.zip");
    try (FileOutputStream stream = new FileOutputStream(f)) {
      MapServerReader mapServer = new MapServerReader(
          "http://arcgis04.geiconsultants.com/arcgis/rest/services/ferix/HighWaterStaking/MapServer");
      mapServer.export(0, stream);
      stream.flush();
    }
    boolean stop = true;
  }



  /**
   * Test export.
   *
   * @throws IOException
   */
  @Test
  public void testFeatureName() throws IOException {

    URL url = new URL("http://arcgis04.geiconsultants.com/arcgis/rest/"
        + "services/ferix/HighWaterStaking/MapServer/0?f=pjson");
    String text = FileHelper.readAll(url.openStream());
    Gson gson = new Gson();
    EsriLayerDef result = gson.fromJson(text, EsriLayerDef.class);

    boolean stop = true;
  }

}
