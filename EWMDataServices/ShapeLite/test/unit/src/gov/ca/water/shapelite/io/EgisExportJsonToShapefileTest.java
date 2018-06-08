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

import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.FileHelper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class EgisExportJsonToShapefileTest {

  @Rule
  public final TemporaryFolder testFolder = new TemporaryFolder();

  public EgisExportJsonToShapefileTest() {
  }

  /**
   * Test of toZippedShapefiles method, of class EgisExportJsonToShapefile.
   * @throws java.lang.Exception The exception that is thrown if there is an
   * error writing the files.
   */
  @Test
  public void testToZippedShapefiles() throws Exception {
    System.out.println("toZippedShapefiles");
    String jsonText = FileHelper.readAll(getClass()
        .getResourceAsStream("resources/EGIS_EXPORT.json"));
    File file = testFolder.newFile("EgisZipped.zip");
    try (OutputStream stream = new FileOutputStream(file)) {
      EgisExportJsonToShapefile.toZippedShapefiles(jsonText, stream);
      stream.flush();
    }
    FileInputStream fInput = new FileInputStream(file);
    List<FeatureSet> featureSets = ShapefileUnzipper.unzip(file);
    boolean stop = true;
  }

  



}
