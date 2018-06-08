/*
 * The MIT License
 *
 * Copyright 2017 Harold A. Dunsford Jr. Ph.D..
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

import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Field;
import gov.ca.water.shapelite.FieldType;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.ShapefileException;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.progress.Progress;
import gov.ca.water.shapelite.progress.ProgressCountableCancellable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class AttributeTableWriterTest {

  @Rule
  public final TemporaryFolder testFolder = new TemporaryFolder();

  public AttributeTableWriterTest() {
  }

  /**
   * Test of write method, of class AttributeTableWriter.
   *
   * @throws java.lang.Exception
   */
  @Test
  public void testWrite() throws Exception {
    FeatureSet fs = new FeatureSet(ShapeType.Point);
    fs.getFields().add(new Field("Alpha", FieldType.Character, 10));
    fs.getFields().add(new Field("Beta", FieldType.Character, 10));
    Feature f = new Feature(new Shape(new CoordXY(1, 1)));
    f.getAttributes().put("Alpha", "1.2.3.4.5.");
    f.getAttributes().put("Beta", "1_2_3_4_5*");
    fs.getFeatures().add(f);
    f = new Feature(new Shape(new CoordXY(2, 2)));
    f.getAttributes().put("Alpha", "6.7.8.9.1.");
    f.getAttributes().put("Beta", "6_7_8_9_1_");
    fs.getFeatures().add(f);

    File file = testFolder.newFile("testShape.shp");
    ShapefileWriter writer = new ShapefileWriter();
    writer.write(fs, file.getAbsolutePath());
    ShapefileReader reader = new ShapefileReader();
    reader.open(file.getAbsolutePath());
    FeatureSet readFs = reader.getFeatures();
    boolean stop = true;
  }

  /**
   * Test of write method, of class AttributeTableWriter.
   *
   * @throws java.lang.Exception
   */
  @Test
  public void testCreate() throws Exception {
    ShapefileReader reader = new ShapefileReader();
    reader.open("D:\\Data\\HEC-RAS Compare\\CSR_Current_Condition_V0068_1997Flows\\XS_2D.shp");
    FeatureSet fs = reader.getFeatures();
    for(int i = 200; i < 210; i++){
      testMissing(fs, i);
      if(testSkippy()){
        boolean stop = true;
      }
    }
  }

  public void testMissing(FeatureSet fs, int iSkip) throws ShapefileException {
    ShapefileWriter writer = new ShapefileWriter();
    FeatureSet copy = fs.copy();
    copy.getFeatures().clear();
    int start = 0;
    int stop = 208;
    for (int i = start; i < stop; i++) {
      if (i == iSkip) {
        continue;
      }
      copy.getFeatures().add(fs.getFeatures().get(i));
    }
    writer.write(copy, "D:\\Data\\testXS.shp");
  }

  /**
   * Test of write method, of class AttributeTableWriter.
   *
   * @throws java.lang.Exception
   */
  public boolean testSkippy() throws Exception {
    ShapefileReader reader = new ShapefileReader();
    reader.open("D:\\Data\\testXS.shp");
    return reader.getAttributes().skip.contains(42);

  }

  /**
   * Test of getProgress method, of class AttributeTableWriter.
   */
  @Test
  public void testGetProgress() {
    System.out.println("getProgress");
    AttributeTableWriter instance = new AttributeTableWriter();
    Progress expResult = null;
    Progress result = instance.getProgress();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setProgress method, of class AttributeTableWriter.
   */
  @Test
  public void testSetProgress() {
    System.out.println("setProgress");
    ProgressCountableCancellable progress = null;
    AttributeTableWriter instance = new AttributeTableWriter();
    instance.setProgress(progress);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

}
