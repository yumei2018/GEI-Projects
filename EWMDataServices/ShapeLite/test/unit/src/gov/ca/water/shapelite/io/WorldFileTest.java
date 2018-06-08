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

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.DefaultEnvelope;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Projections;
import gov.ca.water.shapelite.projection.Reproject;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class WorldFileTest {

  /**
   * A temporary folder that will be deleted after running tests.
   */
  @Rule
  public final TemporaryFolder testFolder = new TemporaryFolder();

  /**
   * Creates a new world file text class.
   */
  public WorldFileTest() {
  }

  /**
   * Generates a standard world file for testing.
   * @return THe WorldFile object.
   */
  public WorldFile getTestWorldFile() {
    WorldFile result = new WorldFile(1, 0, 0, -1, -120, 40);
    return result;
  }

  /**
   * Test of getRowCol method, of class WorldFile.
   */
  @Test
  public void testGetRowCol() {
    System.out.println("getRowCol");
    Coord coordinate = new CoordXY(-118, 38);
    WorldFile instance = getTestWorldFile();
    Point expResult = new Point(2, 2);
    Point result = instance.getRowCol(coordinate);
    assertEquals(expResult, result);

  }

  /**
   * Test of getCoord method, of class WorldFile.
   */
  @Test
  public void testGetCoord() {
    System.out.println("getCoord");
    Point point = new Point(2, 2);
    WorldFile instance = getTestWorldFile();
    CoordXY expResult = new CoordXY(-118, 38);
    CoordXY result = instance.getCoord(point);
    assertEquals(expResult, result);

  }

  /**
   * Test of getEnvelope method, of class WorldFile.
   */
  @Test
  public void testGetEnvelope() {
    System.out.println("getEnvelope");
    int width = 10;
    int height = 10;
    WorldFile instance = getTestWorldFile();
    Envelope expResult = new DefaultEnvelope(-120, 30, -110, 40);
    Envelope result = instance.getEnvelope(width, height);
    assertEquals(expResult, result);

  }

  /**
   * Test of save method, of class WorldFile.
   *
   * @throws java.lang.Exception
   */
  @Test
  public void testSaveAndRead() throws Exception {
    System.out.println("save wld");
    saveAndRead("worldtest.wld");
  }

  /**
   * Test of save method, of class WorldFile.
   *
   * @throws java.lang.Exception
   */
  @Test
  public void testSaveAndReadPng() throws Exception {
    System.out.println("save png");
    saveAndRead("worldtest.pgw");
  }

  /**
   * Test of save method, of class WorldFile.
   *
   * @throws java.lang.Exception
   */
  @Test
  public void testSaveAndReadJpg() throws Exception {
    System.out.println("save jpg");
    saveAndRead("worldtest.jgw");
  }

  /**
   * Test of save method, of class WorldFile.
   *
   * @throws java.lang.Exception
   */
  @Test
  public void testSaveAndReadGif() throws Exception {
    System.out.println("save gif");
    saveAndRead("worldtest.gfw");
  }

  /**
   * Test of save method, of class WorldFile.
   *
   * @throws java.lang.Exception
   */
  @Test
  public void testSaveAndReadTif() throws Exception {
    System.out.println("save tif");
    saveAndRead("worldtest.tfw");
  }

  /**
   * Test of save method, of class WorldFile.
   *
   * @throws java.lang.Exception
   */
  @Test
  public void testSaveAndReadBmp() throws Exception {
    System.out.println("save tif");
    saveAndRead("worldtest.bpw");
  }

  /**
   * This method tests the save and read operation.
   *
   * @param filename The filename to test.
   * @throws IOException If there was an error writing to or reading from the
   * file.
   */
  private void saveAndRead(String filename) throws Exception {
    WorldFile instance = getTestWorldFile();
    File file = testFolder.newFile(filename);
    instance.setFilename(file.getAbsolutePath());
    instance.save();
    WorldFile resultFile = new WorldFile();
    resultFile.read(file.getAbsolutePath());
    assertEquals(instance.getX(), resultFile.getX(), 0.0);
    assertEquals(instance.getY(), resultFile.getY(), 0.0);
    assertEquals(instance.getDx(), resultFile.getDx(), 0.0);
    assertEquals(instance.getDy(), resultFile.getDy(), 0.0);
    assertEquals(instance.getSx(), resultFile.getSx(), 0.0);
    assertEquals(instance.getSy(), resultFile.getSy(), 0.0);
    assertEquals(instance.getFilename(), resultFile.getFilename());
  }

  /**
   * Test of getCurrentProjection method, of class WorldFile.
   */
  @Test
  public void testGetCurrentProjection() {
    System.out.println("getCurrentProjection");
    WorldFile instance = getTestWorldFile();
    String expResult = Projections.getGeographic().getWorld().getWGS1984().toEsriString();
    instance.setCurrentProjection(expResult);
    String result = instance.getCurrentProjection();
    assertEquals(expResult, result);
  }

  /**
   * Test of getX method, of class WorldFile.
   */
  @Test
  public void testGetX() {
    System.out.println("getX");
    WorldFile instance = getTestWorldFile();
    double expResult = -120;
    double result = instance.getX();
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of setX method, of class WorldFile.
   */
  @Test
  public void testSetX() {
    System.out.println("setX");
    double expResult = 1.0;
    WorldFile instance = getTestWorldFile();
    instance.setX(expResult);
    double result = instance.getX();
    assertEquals(expResult, result, 0.0);

  }

  /**
   * Test of getY method, of class WorldFile.
   */
  @Test
  public void testGetY() {
    System.out.println("getY");
    WorldFile instance = getTestWorldFile();
    double expResult = 40;
    double result = instance.getY();
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of setY method, of class WorldFile.
   */
  @Test
  public void testSetY() {
    System.out.println("setY");
    double expResult = 1.0;
    WorldFile instance = getTestWorldFile();
    instance.setY(expResult);
    double result = instance.getY();
    assertEquals(expResult, result, 0.0);

  }

  /**
   * Test of getDx method, of class WorldFile.
   */
  @Test
  public void testGetDx() {
    System.out.println("getDx");
    WorldFile instance = getTestWorldFile();
    double expResult = 1.0;
    double result = instance.getDx();
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of setDx method, of class WorldFile.
   */
  @Test
  public void testSetDx() {
    System.out.println("setDx");
    double expResult = 2.0;
    WorldFile instance = getTestWorldFile();
    instance.setDx(expResult);
    double result = instance.getDx();
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of getDy method, of class WorldFile.
   */
  @Test
  public void testGetDy() {
    System.out.println("getDy");
    WorldFile instance = getTestWorldFile();
    double expResult = -1.0;
    double result = instance.getDy();
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of setDy method, of class WorldFile.
   */
  @Test
  public void testSetDy() {
    System.out.println("setDy");
    double expResult = 2.0;
    WorldFile instance = getTestWorldFile();
    instance.setDy(expResult);
    double result = instance.getDy();
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of getSx method, of class WorldFile.
   */
  @Test
  public void testGetSx() {
    System.out.println("getSx");
    WorldFile instance = getTestWorldFile();
    double expResult = 0.0;
    double result = instance.getSx();
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of setSx method, of class WorldFile.
   */
  @Test
  public void testSetSx() {
    System.out.println("setSx");
    double expResult = 2.0;
    WorldFile instance = getTestWorldFile();
    instance.setSx(expResult);
    double result = instance.getSx();
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of getSy method, of class WorldFile.
   */
  @Test
  public void testGetSy() {
    System.out.println("getSy");
    WorldFile instance = getTestWorldFile();
    double expResult = 0.0;
    double result = instance.getSy();
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of setSy method, of class WorldFile.
   */
  @Test
  public void testSetSy() {
    System.out.println("setSy");
    double expResult = 2.0;
    WorldFile instance = getTestWorldFile();
    instance.setSy(expResult);
    double result = instance.getSy();
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of setFilename method, of class WorldFile.
   */
  @Test
  public void testSetFilename() {
    System.out.println("setFilename");
    String expResult = "C:\\Sam.wld";
    WorldFile instance = new WorldFile();
    instance.setFilename(expResult);
    String result = instance.getFilename();
    assertEquals(expResult, result);

  }

  @Test
  public void testRead() throws Exception{
    String test = "<PAMDataset><SRS>PROJCS[&quot;NAD_1983_UTM_Zone_10N_USFeet&quot;,GEOGCS[&quot;GCS_North_American_1983&quot;,DATUM[&quot;D_North_American_1983&quot;,SPHEROID[&quot;GRS_1980&quot;,6378137.0,298.257222101]],PRIMEM[&quot;Greenwich&quot;,0.0],UNIT[&quot;Degree&quot;,0.0174532925199433]],PROJECTION[&quot;Transverse_Mercator&quot;],PARAMETER[&quot;False_Easting&quot;,1640416.7],PARAMETER[&quot;False_Northing&quot;,0.0],PARAMETER[&quot;Central_Meridian&quot;,-123.0],PARAMETER[&quot;Scale_Factor&quot;,0.9996],PARAMETER[&quot;Latitude_Of_Origin&quot;,0.0],UNIT[&quot;Foot_US&quot;,0.3048006096012192]]</SRS></PAMDataset>";
    WorldFile instance = new WorldFile();
    String result = instance.parseAux(test);
    ProjectionInfo proj = ProjectionInfo.fromEsriString(result);
    ProjectionInfo expResult = Projections.getNad83UTMZone10Foot();
    boolean stop = true;
    CoordXY _result = Reproject.reprojectCoordinate(new CoordXY(2025639.604, 14331601.6362),
            proj, Projections.getWGS84());

    // 2025639.6049368277
    // 1.4331601636157231E7
    // X:2025639.6049368277, Y:1.4150001636157231E7
    Envelope envelope = new DefaultEnvelope(new CoordXY(2025639.604, 1.4150001636157231E7), new CoordXY(2025639.604, 1.4150001636157231E7));
    Envelope _envelope = Reproject.reprojectEnvelope(envelope, proj, Projections.getWGS84());
    assertTrue("should be equal to nad83 projection", proj.equals(expResult));

//    assertTrue("should ", _result.getX());
    System.out.println("x: " + _envelope.getMin().getX());
    System.out.println("x: " + _envelope.getMax().getX());

  }

}
