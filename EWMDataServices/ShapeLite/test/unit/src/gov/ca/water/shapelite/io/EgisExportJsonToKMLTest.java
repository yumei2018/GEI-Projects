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
package gov.ca.water.shapelite.io;

import gov.ca.water.shapelite.FileHelper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class EgisExportJsonToKMLTest {

  @Rule
  public final TemporaryFolder testFolder = new TemporaryFolder();

  public EgisExportJsonToKMLTest() {
  }

  /**
   * Test of toKMZ method, of class EgisExportJsonToKML.
   *
   * @throws java.lang.Exception If an error occurred with the zip libary.
   */
  @Test
  public void testToKMZ() throws Exception {
    System.out.println("toZippedKMZ");
    String jsonText = FileHelper.readAll(getClass()
        .getResourceAsStream("resources/EGIS_EXPORT.json"));
    File file = testFolder.newFile("EgisZipped.zip");
    try (OutputStream stream = new FileOutputStream(file)) {
      EgisExportJsonToKML.toKMZ(jsonText, stream, "Egis.kml");
    }
  }

  /**
   * Test of toKMZ method, of class EgisExportJsonToKML.
   *
   * @throws java.lang.Exception If an error occurred with the zip libary.
   */
  @Test
  public void testToKML() throws Exception {
    System.out.println("toZippedKMZ");
    String jsonText = FileHelper.readAll(getClass()
        .getResourceAsStream("resources/EGIS_EXPORT.json"));
    File file = testFolder.newFile("Egis.kml");
    try (OutputStream stream = new FileOutputStream(file)) {
      EgisExportJsonToKML.toKML(jsonText, stream);
    }
  }

}
