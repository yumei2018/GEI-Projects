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
package gov.ca.water.shapelite.gsa;

import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.io.ShapefileReader;
import gov.ca.water.shapelite.projection.ProjectionException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class GSACoversUtilsTest {

  public GSACoversUtilsTest() {
  }

  /**
   * Test of covers method, of class GSACoversUtils.
   */
  @Test
  public void testCovers_No() throws Exception {
    System.out.println("covers");
    InputStream serviceAreaStream = new FileInputStream("D:\\Data\\GSA2\\Polygon 1.zip");
    InputStream gsaAreaStream = new FileInputStream("D:\\Data\\GSA2\\Polygon 4.zip");
    boolean expResult = false;
    boolean result = GSACoversUtils.covers(serviceAreaStream, gsaAreaStream);
    assertEquals(expResult, result);

  }

  /**
   * Test of covers method, of class GSACoversUtils.
   */
  @Test
  public void testCovers_Yes() throws Exception {
    System.out.println("covers");
    InputStream serviceAreaStream = new FileInputStream("D:\\Data\\GSA2\\Polygon 1.zip");
    InputStream gsaAreaStream = new FileInputStream("D:\\Data\\GSA2\\Polygon 1.zip");
    boolean expResult = true;
    boolean result = GSACoversUtils.covers(serviceAreaStream, gsaAreaStream);
    assertEquals(expResult, result);

  }

  @Test
  public void testMergeCovers() throws FileNotFoundException, IOException, ProjectionException{
    System.out.println("covers");
    InputStream serviceAreaStream = new FileInputStream("D:\\Data\\GSA2\\Merge14.zip");
    InputStream gsaAreaStream = new FileInputStream("D:\\Data\\GSA2\\Polygon 4.zip");
    boolean expResult = true;
    boolean result = GSACoversUtils.covers(serviceAreaStream, gsaAreaStream);
    assertEquals(expResult, result);
  }

   @Test
  public void testMergeCovers2() throws FileNotFoundException, IOException, ProjectionException{
    System.out.println("covers");
    ShapefileReader reader = new ShapefileReader();
    reader.open("D:\\Data\\GSA2\\Merge14.zip");
    FeatureSet fsContainer = reader.getFeatures();
    FeatureSet fsTarget = fsContainer.copy();
    boolean expResult = true;
    boolean result = GSACoversUtils.covers(fsContainer, fsTarget);
    assertEquals(expResult, result);
  }

   @Test
  public void testMergeCovers3() throws FileNotFoundException, IOException, ProjectionException{
    System.out.println("covers");
    InputStream master = new FileInputStream("J:\\For Donghai\\GIS_Test\\overlap_test\\GEI_Test_Data\\Test Polygons\\GSA_Boundary_Master_GIS(3).zip");
    InputStream test = new FileInputStream("J:\\For Donghai\\GIS_Test\\overlap_test\\GEI_Test_Data\\Test Polygons\\20_D4.zip");
    boolean expResult = true;
    boolean result = GSACoversUtils.covers(master, test, 100);
    assertEquals(expResult, result);
  }


}
