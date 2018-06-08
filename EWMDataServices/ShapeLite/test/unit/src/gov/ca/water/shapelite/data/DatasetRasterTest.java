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
package gov.ca.water.shapelite.data;

import gov.ca.water.shapelite.data.dataset.RasterDataset;
import com.vividsolutions.jts.util.Assert;
import gov.ca.water.shapelite.DefaultEnvelope;
import gov.ca.water.shapelite.Envelope;
import static gov.ca.water.shapelite.data.dataset.RasterDataset.INCHES_PER_METER;
import static gov.ca.water.shapelite.data.dataset.RasterDataset.METERS_PER_DEGREE;
import static gov.ca.water.shapelite.data.dataset.RasterDataset.PPI;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Projections;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class DatasetRasterTest {

  /**
   * A default integer size for testing.
   */
  public static final int TEST_SIZE = 100;

  /**
   * A test value for allowing some give for equality testing.
   */
  public static final double TEST_EPSILON = .000000001;

  @Rule
  public TemporaryFolder testFolder = new TemporaryFolder();
 
    @Test
    public void testInTempFolder() throws IOException {
        
        //File tempFolder = testFolder.newFolder("folder");
        //System.out.println("Test folder: " + testFolder.getRoot());
        // test...
    }
    
   
  /**
   * This tests the save and open capability.
   *
   * @throws FileNotFoundException
   * @throws IOException
   */
  @Test
  public void saveAndOpenRaster() throws FileNotFoundException, IOException {
    RasterDataset raster = new RasterDataset(TEST_SIZE, TEST_SIZE, 1);
    for (int row = 0; row < TEST_SIZE; row++) {
      for (int col = 0; col < TEST_SIZE; col++) {
        double val = TEST_SIZE * row + col;
        raster.getData()[row][col] = val;
      }
    }
    for (int row = 0; row < TEST_SIZE; row++) {
      for (int col = 0; col < TEST_SIZE; col++) {
        double val = TEST_SIZE * row + col;
        Assert.equals(val, raster.getData()[row][col]);
      }
    }

    Path path = Files.createTempDirectory("RasterTests");

    String file = path.toString() + "\\SaveAndOpen.asc";
    raster.save(file);
    RasterDataset result = new RasterDataset();
    result.open(file);

    for (int row = 0; row < TEST_SIZE; row++) {
      for (int col = 0; col < TEST_SIZE; col++) {
        double val = TEST_SIZE * row + col;
        Assert.equals(val, result.getData()[row][col]);
      }
    }

  }

  /**
   * This tests the save and open capability.
   *
   * @throws FileNotFoundException
   * @throws IOException
   */
  @Test
  public void EnvelopeTest() throws FileNotFoundException, IOException {
    RasterDataset raster = new RasterDataset(TEST_SIZE, TEST_SIZE, 1);
    raster.getProjection().copyProperties(
        Projections.getProjected().getWorld().getWebMercator());
    Envelope original = new DefaultEnvelope(0, 0, TEST_SIZE, TEST_SIZE);
    raster.getEnvelopeMercator().copyProperties(original);
    boolean expResult = true;
    Envelope created = raster.getEnvelopeMercator();
    boolean equals = created.equals(original);
    // First check that our assignment worked.
    Assert.equals(equals, expResult);

    Path path = Files.createTempDirectory("RasterTests");
    String file = path.toString() + "\\Envelope.asc";
    raster.save(file);
    RasterDataset result = new RasterDataset();
    result.open(file);

    Envelope test = result.getEnvelopeMercator();
    equals = test.equals(original, .000001);
    // check that the envelope generated from attributes works.
    Assert.equals(equals, expResult);
  }

  /**
   * This tests the save and open capability.
   *
   * @throws FileNotFoundException
   * @throws IOException
   */
  @Test
  public void saveAndOpenRasterProjection() throws FileNotFoundException, IOException {
    RasterDataset raster = new RasterDataset(TEST_SIZE, TEST_SIZE, 1);
    ProjectionInfo web = Projections.getProjected().getWorld().getWebMercator();
    raster.getProjection().copyProperties(web);
    ProjectionInfo expResult = web;
    /**
     * Don't use Assert.equals directly on the projections because it uses
     * object equality, and not the overridden equals method.
     */
    boolean equal = raster.getProjection().equals(expResult);
    Assert.equals(equal, true);

    Path path = Files.createTempDirectory("RasterTests");
    String file = path.toString() + "\\SaveAndOpenRasterProjection.asc";
    raster.save(file);
    RasterDataset result = new RasterDataset();
    result.open(file);
    equal = result.getProjection().equals(expResult);
    Assert.equals(equal, true);

  }

  /**
   * Test of getClosestLevel method, of class RasterDataset.
   */
  @Test
  public void testGetClosestLevel() {
    System.out.println("getClosestLevel");
    RasterDataset instance = new RasterDataset();
    instance.getEnvelopeMercator().copyProperties(new DefaultEnvelope(-180,-180,180,180));
    int expResult = 0;
    int result = instance.getClosestLevel();
    assertEquals(expResult, result);
  }

  /**
   * Test of getProjector method, of class RasterDataset.
   */
  @Test
  public void testGetProjector() {
    System.out.println("getProjector");
    RasterDataset instance = new RasterDataset();
    Projector result = instance.getProjector();
    assertEquals(instance, result.getDataFrame());
  }

  /**
   * Test of getScale method, of class RasterDataset.
   */
  @Test
  public void testGetScale() {
    System.out.println("getScale");
    RasterDataset instance = new RasterDataset();
    double worldInch = 360 * METERS_PER_DEGREE * INCHES_PER_METER;
    double displayInch = 256 / PPI;
    double expResult = worldInch / displayInch;
    double result = instance.getScale();
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of writeNoData method, of class RasterDataset.
   */
  @Test
  public void testWriteNoData() {
    System.out.println("writeNoData");
    RasterDataset instance = new RasterDataset();
    instance.writeNoData();
    double expValue = instance.getNoDataValue();
    for(int row = 0; row < instance.getHeight(); row++){
      for(int col = 0; col < instance.getWidth(); col++){
        assertEquals(expValue, instance.getData()[row][col], 0.0);
      }
    }
  }

  /**
   * Test of getView method, of class RasterDataset.
   */
  @Test
  public void testGetView() {
    System.out.println("getView");
    RasterDataset instance = new RasterDataset();
    Rectangle expResultRect = new Rectangle(0,0,256,256);
    Rectangle resultRect = instance.getView();
    assertEquals(expResultRect, resultRect);
    
  }

  /**
   * Test of open method, of class RasterDataset.
   * @throws java.lang.Exception
   */
  @Test
  public void testOpen_String() throws Exception {
    System.out.println("open");
    RasterDataset source = new RasterDataset();
    source.getData()[1][2] = 12;
    File tempFile = testFolder.newFile("raster.asc");
    source.save(tempFile.getAbsolutePath());
    RasterDataset instance = new RasterDataset();
    instance.open(tempFile.getAbsoluteFile());
    assertEquals(source.getData()[1][2], instance.getData()[1][2], 0.0);
  }

  /**
   * Test of open method, of class RasterDataset.
   */
  @Test
  public void testOpen_File() throws Exception {
    System.out.println("open");
    RasterDataset source = new RasterDataset();
    source.getData()[1][2] = 12;
    File tempFile = testFolder.newFile("raster.asc");
    source.save(tempFile.getAbsolutePath());
    RasterDataset instance = new RasterDataset();
    instance.open(tempFile);
    assertEquals(source.getData()[1][2], instance.getData()[1][2], 0.0);
  }

 
 
  /**
   * Test of calculateMinMax method, of class RasterDataset.
   */
  @Test
  public void testCalculateMinMax() {
    System.out.println("calculateMinMax");
    RasterDataset instance = new RasterDataset();
    instance.getData()[0][0] = 12;
    instance.getData()[1][0] = 0;
    instance.calculateMinMax();
    assertEquals(0, instance.getMin(), 0);
    assertEquals(12, instance.getMax(), 0);
  }
  
  /**
   * Test of getEnvelope method, of class RasterDataset.
   */
  @Test
  public void testGetEnvelope() {
    System.out.println("getEnvelope");
    RasterDataset instance = new RasterDataset();
    Envelope expResult = new DefaultEnvelope(-180,-85.05112877980659,180,85.05112877980659);
    Envelope result = instance.getEnvelope();
    assertEquals(expResult, result);
  }

  /**
   * Test of getHeight method, of class RasterDataset.
   */
  @Test
  public void testGetHeight() {
    System.out.println("getHeight");
    RasterDataset instance = new RasterDataset();
    int expResult = 256;
    int result = instance.getHeight();
    assertEquals(expResult, result);
  }

  /**
   * Test of getWidth method, of class RasterDataset.
   */
  @Test
  public void testGetWidth() {
    System.out.println("getWidth");
    RasterDataset instance = new RasterDataset();
    int expResult = 256;
    int result = instance.getWidth();
    assertEquals(expResult, result);
  }

  /**
   * Test of setWidth method, of class RasterDataset.
   */
  @Test
  public void testSetWidth() {
    System.out.println("setWidth");
    int width = 100;
    RasterDataset instance = new RasterDataset();
    instance.setWidth(width);
    assertEquals(width, instance.getWidth());
  }

  /**
   * Test of setHeight method, of class RasterDataset.
   */
  @Test
  public void testSetHeight() {
    System.out.println("setHeight");
    int height = 100;
    RasterDataset instance = new RasterDataset();
    instance.setHeight(height);
    assertEquals(height, instance.getHeight());
  }

  /**
   * Test of getBands method, of class RasterDataset.
   */
  @Test
  public void testGetBands() {
    System.out.println("getBands");
    RasterDataset instance = new RasterDataset();
    instance.getBands().clear();
    Band b = new Band(40,40,-99);
    instance.getBands().add(b);
    List<Band> result = instance.getBands();
    assertEquals(1, result.size());
    assertEquals(b, result.get(0));
  }

  /**
   * Test of getNoDataValue method, of class RasterDataset.
   */
  @Test
  public void testGetNoDataValue() {
    System.out.println("getNoDataValue");
    RasterDataset instance = new RasterDataset();
    double expResult = -99;
    double result = instance.getNoDataValue();
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of setNoDataValue method, of class RasterDataset.
   */
  @Test
  public void testSetNoDataValue() {
    System.out.println("setNoDataValue");
    double noDataValue = 47;
    RasterDataset instance = new RasterDataset();
    instance.setNoDataValue(noDataValue);
    double result = instance.getNoDataValue();
    assertEquals(noDataValue, result, 0.0);
  }

  /**
   * Test of getXllcorner method, of class RasterDataset.
   */
  @Test
  public void testGetXllcorner() {
    System.out.println("getXllcorner");
    RasterDataset instance = new RasterDataset();
    double expResult = 0.0;
    double result = instance.getXllcorner();
    assertEquals(expResult, result, 0.0);

  }

  /**
   * Test of setXllcorner method, of class RasterDataset.
   */
  @Test
  public void testSetXllcorner() {
    System.out.println("setXllcorner");
    double xllcorner = 12;
    RasterDataset instance = new RasterDataset();
    instance.setXllcorner(xllcorner);
    double result = instance.getXllcorner();
    assertEquals(xllcorner, result, 0.0);
  }

  /**
   * Test of getYllcorner method, of class RasterDataset.
   */
  @Test
  public void testGetYllcorner() {
    System.out.println("getYllcorner");
    RasterDataset instance = new RasterDataset();
    double expResult = 0.0;
    double result = instance.getYllcorner();
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of setYllcorner method, of class RasterDataset.
   */
  @Test
  public void testSetYllcorner() {
    System.out.println("setYllcorner");
    double yllcorner = 12;
    RasterDataset instance = new RasterDataset();
    instance.setYllcorner(yllcorner);
    double result = instance.getYllcorner();
    assertEquals(yllcorner, result, 0.0);
  }

  /**
   * Test of getCellsize method, of class RasterDataset.
   */
  @Test
  public void testGetCellsize() {
    System.out.println("getCellsize");
    RasterDataset instance = new RasterDataset();
    double expResult = 0.0;
    double result = instance.getCellsize();
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of setCellsize method, of class RasterDataset.
   */
  @Test
  public void testSetCellsize() {
    System.out.println("setCellsize");
    double cellsize = 1;
    RasterDataset instance = new RasterDataset();
    instance.setCellsize(cellsize);
     double result = instance.getCellsize();
    assertEquals(cellsize, result, 0.0);
  }

  
  /**
   * Test of setMin method, of class RasterDataset.
   */
  @Test
  public void testSetMin() {
    System.out.println("setMin");
    double min = 3;
    RasterDataset instance = new RasterDataset();
    instance.setMin(min);
    assertEquals(min, instance.getMin(), 0.0);
  }

  /**
   * Test of setMax method, of class RasterDataset.
   */
  @Test
  public void testSetMax() {
    System.out.println("getMax");
    double max = 3;
    RasterDataset instance = new RasterDataset();
    instance.setMax(max);
    assertEquals(max, instance.getMax(), 0.0);
  }

  /**
   * Test of getFormat method, of class RasterDataset.
   */
  @Test
  public void testGetFormat() {
    System.out.println("getFormat");
    DecimalFormat expResult = new DecimalFormat("#.0");
    RasterDataset.setFormat(expResult);
    DecimalFormat result = RasterDataset.getFormat();
    assertEquals(expResult, result);
  }

 

}
