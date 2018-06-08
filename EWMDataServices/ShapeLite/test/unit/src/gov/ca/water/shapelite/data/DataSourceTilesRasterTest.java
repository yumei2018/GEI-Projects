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
package gov.ca.water.shapelite.data;

import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.data.dataset.RasterDataset;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class DataSourceTilesRasterTest {

  private String path;

  @Rule
  public TemporaryFolder testFolder = new TemporaryFolder();

  public DataSourceTilesRasterTest() {
  }

  @BeforeClass
  public void createRaster() throws IOException {
    path = testFolder.toString();
    File scale = testFolder.newFolder("16");
    File row = new File(scale.getAbsolutePath() + "\100");
    row.mkdirs();
    RasterDataset rd = new RasterDataset();
    rd.getData()[1][2] = 1;
    rd.getData()[2][4] = 2;
    rd.getData()[3][6] = 3;
    rd.save(row.getAbsolutePath() + "100.asc");
  }
  
  private DataSourceTilesRaster getInstance(){
    DataSourceTilesRaster instance = new DataSourceTilesRaster();
    instance.setPath(path);
    instance.setX(100);
    instance.setY(100);
    instance.setLevel(16);
    return instance;
  }

  /**
   * Test of loadDataSet method, of class DataSourceTilesRaster.
   *
   * @throws java.lang.Exception The exception thrown.
   */
  @Test
  public void testLoadDataSet() throws Exception {
    System.out.println("loadDataSet");
    DataSourceTilesRaster instance = getInstance();
    instance.loadDataSet();
    Optional<RasterDataset> maybeRaster = instance.getDataset();
    if (maybeRaster.isPresent()) {
      RasterDataset rd = maybeRaster.get();
      assertEquals(rd.getData()[1][2], 1, 0);
      assertEquals(rd.getData()[2][4], 2, 0);
      assertEquals(rd.getData()[2][4], 3, 0);
    }
  }

  /**
   * Test of cachedTileExists method, of class DataSourceTilesRaster.
   */
  @Test
  public void testCachedTileExists() {
    System.out.println("cachedTileExists");
    DataSourceTilesRaster instance = new DataSourceTilesRaster();
    boolean expResult = false;
    boolean result = instance.cachedTileExists();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of urlTileExists method, of class DataSourceTilesRaster.
   */
  @Test
  public void testUrlTileExists() {
    System.out.println("urlTileExists");
    String urlName = "";
    DataSourceTilesRaster instance = new DataSourceTilesRaster();
    boolean expResult = false;
    boolean result = instance.urlTileExists(urlName);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getCacheFilename method, of class DataSourceTilesRaster.
   */
  @Test
  public void testGetCacheFilename() {
    System.out.println("getCacheFilename");
    DataSourceTilesRaster instance = new DataSourceTilesRaster();
    String expResult = "";
    String result = instance.getCacheFilename();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of copy method, of class DataSourceTilesRaster.
   */
  @Test
  public void testCopy() {
    System.out.println("copy");
    DataSourceTilesRaster instance = new DataSourceTilesRaster();
    DataSourceTilesRaster expResult = null;
    DataSourceTilesRaster result = instance.copy();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getCurrentEnvelopeMercator method, of class DataSourceTilesRaster.
   */
  @Test
  public void testGetCurrentEnvelopeMercator() {
    System.out.println("getCurrentEnvelopeMercator");
    DataSourceTilesRaster instance = new DataSourceTilesRaster();
    Envelope expResult = null;
    Envelope result = instance.getCurrentEnvelopeMercator();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getCurrentEnvelopeWGS84 method, of class DataSourceTilesRaster.
   */
  @Test
  public void testGetCurrentEnvelopeWGS84() {
    System.out.println("getCurrentEnvelopeWGS84");
    DataSourceTilesRaster instance = new DataSourceTilesRaster();
    Envelope expResult = null;
    Envelope result = instance.getCurrentEnvelopeWGS84();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getImage method, of class DataSourceTilesRaster.
   */
  @Test
  public void testGetImage() {
    System.out.println("getImage");
    DataSourceTilesRaster instance = new DataSourceTilesRaster();
    Optional<BufferedImage> expResult = null;
    Optional<BufferedImage> result = instance.getImage();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getTilePath method, of class DataSourceTilesRaster.
   */
  @Test
  public void testGetTilePath() {
    System.out.println("getTilePath");
    DataSourceTilesRaster instance = new DataSourceTilesRaster();
    TilePath expResult = null;
    TilePath result = instance.getTilePath();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of requestDownload method, of class DataSourceTilesRaster.
   */
  @Test
  public void testRequestDownload() {
    System.out.println("requestDownload");
    DataSourceTilesRaster instance = new DataSourceTilesRaster();
    instance.requestDownload();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getTileBounds method, of class DataSourceTilesRaster.
   */
  @Test
  public void testGetTileBounds() {
    System.out.println("getTileBounds");
    Envelope mercator = null;
    DataSourceTilesRaster instance = new DataSourceTilesRaster();
    Rectangle expResult = null;
    Rectangle result = instance.getTileBounds(mercator);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getDataset method, of class DataSourceTilesRaster.
   */
  @Test
  public void testGetDataset() {
    System.out.println("getDataset");
    DataSourceTilesRaster instance = new DataSourceTilesRaster();
    Optional<RasterDataset> expResult = null;
    Optional<RasterDataset> result = instance.getDataset();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of isLoaded method, of class DataSourceTilesRaster.
   */
  @Test
  public void testIsLoaded() {
    System.out.println("isLoaded");
    DataSourceTilesRaster instance = new DataSourceTilesRaster();
    boolean expResult = false;
    boolean result = instance.isLoaded();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getExt method, of class DataSourceTilesRaster.
   */
  @Test
  public void testGetExt() {
    System.out.println("getExt");
    DataSourceTilesRaster instance = new DataSourceTilesRaster();
    String expResult = "";
    String result = instance.getExt();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setExt method, of class DataSourceTilesRaster.
   */
  @Test
  public void testSetExt() {
    System.out.println("setExt");
    String ext = "";
    DataSourceTilesRaster instance = new DataSourceTilesRaster();
    instance.setExt(ext);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getLevel method, of class DataSourceTilesRaster.
   */
  @Test
  public void testGetLevel() {
    System.out.println("getLevel");
    DataSourceTilesRaster instance = new DataSourceTilesRaster();
    int expResult = 0;
    int result = instance.getLevel();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setLevel method, of class DataSourceTilesRaster.
   */
  @Test
  public void testSetLevel() {
    System.out.println("setLevel");
    int scale = 0;
    DataSourceTilesRaster instance = new DataSourceTilesRaster();
    instance.setLevel(scale);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getMaxLevel method, of class DataSourceTilesRaster.
   */
  @Test
  public void testGetMaxLevel() {
    System.out.println("getMaxLevel");
    DataSourceTilesRaster instance = new DataSourceTilesRaster();
    int expResult = 0;
    int result = instance.getMaxLevel();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setMaxLevel method, of class DataSourceTilesRaster.
   */
  @Test
  public void testSetMaxLevel() {
    System.out.println("setMaxLevel");
    int maxScale = 0;
    DataSourceTilesRaster instance = new DataSourceTilesRaster();
    instance.setMaxLevel(maxScale);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getPath method, of class DataSourceTilesRaster.
   */
  @Test
  public void testGetPath() {
    System.out.println("getPath");
    DataSourceTilesRaster instance = new DataSourceTilesRaster();
    String expResult = "";
    String result = instance.getPath();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setPath method, of class DataSourceTilesRaster.
   */
  @Test
  public void testSetPath() {
    System.out.println("setPath");
    String path = "";
    DataSourceTilesRaster instance = new DataSourceTilesRaster();
    instance.setPath(path);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setPathAbsolute method, of class DataSourceTilesRaster.
   */
  @Test
  public void testSetPathAbsolute() {
    System.out.println("setPathAbsolute");
    String path = "";
    DataSourceTilesRaster instance = new DataSourceTilesRaster();
    instance.setPathAbsolute(path);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getUrlFormat method, of class DataSourceTilesRaster.
   */
  @Test
  public void testGetUrlFormat() {
    System.out.println("getUrlFormat");
    DataSourceTilesRaster instance = new DataSourceTilesRaster();
    TileUrlFormat expResult = null;
    TileUrlFormat result = instance.getUrlFormat();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setUrlFormat method, of class DataSourceTilesRaster.
   */
  @Test
  public void testSetUrlFormat() {
    System.out.println("setUrlFormat");
    TileUrlFormat urlFormat = null;
    DataSourceTilesRaster instance = new DataSourceTilesRaster();
    instance.setUrlFormat(urlFormat);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getX method, of class DataSourceTilesRaster.
   */
  @Test
  public void testGetX() {
    System.out.println("getX");
    DataSourceTilesRaster instance = new DataSourceTilesRaster();
    int expResult = 0;
    int result = instance.getX();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setX method, of class DataSourceTilesRaster.
   */
  @Test
  public void testSetX() {
    System.out.println("setX");
    int x = 0;
    DataSourceTilesRaster instance = new DataSourceTilesRaster();
    instance.setX(x);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getY method, of class DataSourceTilesRaster.
   */
  @Test
  public void testGetY() {
    System.out.println("getY");
    DataSourceTilesRaster instance = new DataSourceTilesRaster();
    int expResult = 0;
    int result = instance.getY();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setY method, of class DataSourceTilesRaster.
   */
  @Test
  public void testSetY() {
    System.out.println("setY");
    int y = 0;
    DataSourceTilesRaster instance = new DataSourceTilesRaster();
    instance.setY(y);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of isLocalOnly method, of class DataSourceTilesRaster.
   */
  @Test
  public void testIsLocalOnly() {
    System.out.println("isLocalOnly");
    DataSourceTilesRaster instance = new DataSourceTilesRaster();
    boolean expResult = false;
    boolean result = instance.isLocalOnly();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setLocalOnly method, of class DataSourceTilesRaster.
   */
  @Test
  public void testSetLocalOnly() {
    System.out.println("setLocalOnly");
    boolean localOnly = false;
    DataSourceTilesRaster instance = new DataSourceTilesRaster();
    instance.setLocalOnly(localOnly);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

}
