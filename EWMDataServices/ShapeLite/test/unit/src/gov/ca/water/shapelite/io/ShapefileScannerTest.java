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

import com.vividsolutions.jts.util.Assert;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Field;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.ShapefileException;
import gov.ca.water.shapelite.compare.FeatureComparer;
import gov.ca.water.shapelite.compare.FeatureSetComparer;
import gov.ca.water.shapelite.compare.FieldComparer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ShapefileScannerTest {

  @Rule
  public final TemporaryFolder testFolder = new TemporaryFolder();

  public ShapefileScannerTest() {
  }

  /**
   * Gets the shapefile scanner based on a line shapefile.
   *
   * @return A ShapefileScanner for reading the lines.
   * @throws IOException
   * @throws ShapefileScannerException
   */
  private ShapefileScanner getLineScanner() throws IOException, ShapefileScannerException {
    InputStream shp = this.getClass().getResourceAsStream("resources/Lines.shp");
    InputStream shx = this.getClass().getResourceAsStream("resources/Lines.shx");
    InputStream dbf = this.getClass().getResourceAsStream("resources/Lines.dbf");
    InputStream prj = this.getClass().getResourceAsStream("resources/Lines.prj");
    return new ShapefileScanner(shp, shx, dbf, prj);
  }

  /**
   * Reads the features using a standard feature reader for comparison.
   *
   * @return
   * @throws IOException
   */
  private FeatureSet getLineZFeatureSet() throws IOException {
    InputStream shp = this.getClass().getResourceAsStream("resources/Lines.shp");
    InputStream shx = this.getClass().getResourceAsStream("resources/Lines.shx");
    InputStream dbf = this.getClass().getResourceAsStream("resources/Lines.dbf");
    InputStream prj = this.getClass().getResourceAsStream("resources/Lines.prj");
    ShapefileReader reader = new ShapefileReader();
    reader.open(shp, shx, dbf, prj);
    FeatureSet fs = reader.getFeatures();
    return fs;
  }

  /**
   * Test of getFeatures method, of class ShapefileScanner.
   *
   * @throws java.lang.Exception if there was an error reading the using the
   * scanner or else the FeatureReader.
   */
  @Test
  public void testGetFeaturesLineZ() throws Exception {
    System.out.println("getFeatures");
    ShapefileScanner instance = getLineScanner();
    FeatureSet expResult = getLineZFeatureSet();
    FeatureSet result = instance.getFeatures();
    FieldComparer fc = new FieldComparer();
    Assert.equals(expResult.getFields().size(), result.getFields().size());
    for (int iField = 0; iField < result.getFields().size(); iField++) {
      Field a = expResult.getFields().get(iField);
      Field b = result.getFields().get(iField);
      Assert.isTrue(fc.equivalent(a, b));
    }

    for (int iFeature = 0; iFeature < expResult.getFeatures().size(); iFeature++) {

    }
  }

  @Test
  public void testPolylineM() throws IOException, FileNotFoundException,
      ShapefileScannerException, ShapefileException {
    FeatureSet expResultFeatures = TestShapefileFactory.createFeatureSet(ShapeType.PolyLineM);

    File file = testFolder.newFile("PolyLineM.shp");
    ShapefileWriter writer = new ShapefileWriter();
    writer.write(expResultFeatures, file.getAbsolutePath());

    ShapefileScanner instance = new ShapefileScanner(file.getAbsolutePath());
    FeatureSet resultFeatures = instance.getFeatures();

    FeatureSetComparer fsc = new FeatureSetComparer();
    boolean expResult = true;
    boolean result = fsc.equivalent(expResultFeatures, resultFeatures);
    assertEquals(expResult, result);
  }

  /**
   * Test of getFeatures method, of class ShapefileScanner.
   */
  @Test
  public void testGetFeatures_int_int() throws Exception {
    System.out.println("getFeatures");
    int start = 0;
    int numFeatures = 0;
    ShapefileScanner instance = null;
    FeatureSet expResult = null;
    FeatureSet result = instance.getFeatures(start, numFeatures);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getShapes method, of class ShapefileScanner.
   */
  @Test
  public void testGetShapes_0args() throws Exception {
    System.out.println("getShapes");
    ShapefileScanner instance = null;
    List<Shape> expResult = null;
    List<Shape> result = instance.getShapes();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getShapes method, of class ShapefileScanner.
   */
  @Test
  public void testGetShapes_int_int() throws Exception {
    System.out.println("getShapes");
    int start = 0;
    int numShapes = 0;
    ShapefileScanner instance = null;
    List<Shape> expResult = null;
    List<Shape> result = instance.getShapes(start, numShapes);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of close method, of class ShapefileScanner.
   */
  @Test
  public void testClose() {
    System.out.println("close");
    ShapefileScanner instance = null;
    instance.close();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of readShape method, of class ShapefileScanner.
   */
  @Test
  public void testReadShape() throws Exception {
    System.out.println("readShape");
    int index = 0;
    ShapefileScanner instance = null;
    Shape expResult = null;
    Shape result = instance.readShape(index);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of readPolyline method, of class ShapefileScanner.
   */
  @Test
  public void testReadPolyline() {
    System.out.println("readPolyline");
    Shape result_2 = null;
    ShapefileScanner instance = null;
    instance.readPolyline(result_2);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of readPolylineM method, of class ShapefileScanner.
   */
  @Test
  public void testReadPolylineM() {
    System.out.println("readPolylineM");
    Shape result_2 = null;
    ShapefileScanner instance = null;
    instance.readPolylineM(result_2);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of readPolylineZ method, of class ShapefileScanner.
   */
  @Test
  public void testReadPolylineZ() {
    System.out.println("readPolylineZ");
    Shape result_2 = null;
    ShapefileScanner instance = null;
    instance.readPolylineZ(result_2);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getFilename method, of class ShapefileScanner.
   */
  @Test
  public void testGetFilename() {
    System.out.println("getFilename");
    ShapefileScanner instance = null;
    String expResult = "";
    String result = instance.getFilename();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setFilename method, of class ShapefileScanner.
   */
  @Test
  public void testSetFilename() {
    System.out.println("setFilename");
    String filename = "";
    ShapefileScanner instance = null;
    instance.setFilename(filename);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getLengths method, of class ShapefileScanner.
   */
  @Test
  public void testGetLengths() {
    System.out.println("getLengths");
    ShapefileScanner instance = null;
    int[] expResult = null;
    int[] result = instance.getLengths();
    assertArrayEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setLengths method, of class ShapefileScanner.
   */
  @Test
  public void testSetLengths() {
    System.out.println("setLengths");
    int[] lengths = null;
    ShapefileScanner instance = null;
    instance.setLengths(lengths);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getNumShapes method, of class ShapefileScanner.
   */
  @Test
  public void testGetNumShapes() {
    System.out.println("getNumShapes");
    ShapefileScanner instance = null;
    int expResult = 0;
    int result = instance.getNumShapes();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setNumShapes method, of class ShapefileScanner.
   */
  @Test
  public void testSetNumShapes() {
    System.out.println("setNumShapes");
    int numShapes = 0;
    ShapefileScanner instance = null;
    instance.setNumShapes(numShapes);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getShapeReader method, of class ShapefileScanner.
   */
  @Test
  public void testGetShapeReader() {
    System.out.println("getShapeReader");
    ShapefileScanner instance = null;
    Scanner expResult = null;
    Scanner result = instance.getShapeReader();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setShapeReader method, of class ShapefileScanner.
   */
  @Test
  public void testSetShapeReader() {
    System.out.println("setShapeReader");
    Scanner shapeReader = null;
    ShapefileScanner instance = null;
    instance.setShapeReader(shapeReader);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getProjection method, of class ShapefileScanner.
   */
  @Test
  public void testGetProjection() {
    System.out.println("getProjection");
    ShapefileScanner instance = null;
    String expResult = "";
    String result = instance.getProjection();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setProjection method, of class ShapefileScanner.
   */
  @Test
  public void testSetProjection() {
    System.out.println("setProjection");
    String projection = "";
    ShapefileScanner instance = null;
    instance.setProjection(projection);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getName method, of class ShapefileScanner.
   */
  @Test
  public void testGetName() {
    System.out.println("getName");
    ShapefileScanner instance = null;
    String expResult = "";
    String result = instance.getName();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setName method, of class ShapefileScanner.
   */
  @Test
  public void testSetName() {
    System.out.println("setName");
    String name = "";
    ShapefileScanner instance = null;
    instance.setName(name);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getAttributes method, of class ShapefileScanner.
   */
  @Test
  public void testGetAttributes() {
    System.out.println("getAttributes");
    ShapefileScanner instance = null;
    AttributeTableScanner expResult = null;
    AttributeTableScanner result = instance.getAttributes();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setAttributes method, of class ShapefileScanner.
   */
  @Test
  public void testSetAttributes() {
    System.out.println("setAttributes");
    AttributeTableScanner attributes = null;
    ShapefileScanner instance = null;
    instance.setAttributes(attributes);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getFeatures method, of class ShapefileScanner.
   */
  @Test
  public void testGetFeatures_0args() throws Exception {
    System.out.println("getFeatures");
    ShapefileScanner instance = null;
    FeatureSet expResult = null;
    FeatureSet result = instance.getFeatures();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

}
