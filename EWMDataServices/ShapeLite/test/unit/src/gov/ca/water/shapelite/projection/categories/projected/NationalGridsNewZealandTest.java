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
package gov.ca.water.shapelite.projection.categories.projected;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Projections;
import gov.ca.water.shapelite.projection.Reproject;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class NationalGridsNewZealandTest {

  /**
   * Since UTM is in meters, demand a TOLERANCE of 1mm.
   */
  private static final double TOLERANCE = .001;

  /**
   * The WGS84 projection, which is used to test going to geographic coordinates
   * and back.
   */
  private static final ProjectionInfo WGS84
      = Projections.getGeographic().getWorld().getWGS1984();

  /**
   * The UtmOther projection coordinate system list.
   */
  private static final NationalGridsNewZealand PROJ = Projections.getProjected().getNationalGridsNewZealand();

  /**
   * Tests converting values to WGS84 and back.
   *
   * @param projection The ProjectionInfo to test.
   */
  public void testToWGS84AndBack(ProjectionInfo projection) {

    try {
      Coord original = new CoordXY(100, 100);
      Coord world = Reproject.reprojectCoordinate(original, projection, WGS84);
      Coord result = Reproject.reprojectCoordinate(world, WGS84, projection);
      assertEquals(projection.getName() + " failed.", original.getX(),
          result.getX(), TOLERANCE);
      assertEquals(projection.getName() + " failed.", original.getY(),
          result.getY(), TOLERANCE);
    } catch (ProjectionException ex) {
      Logger.getLogger(UtmOtherTest.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Test of getChathamIslands1979MapGrid method, of class NationalGridsNewZealand.
   */
  @Test
  public void testChathamIslands1979MapGrid() {
    testToWGS84AndBack(PROJ.getChathamIslands1979MapGrid());
  }

  /**
   * Test of getNZGD1949AmuriCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD1949AmuriCircuit() {
    testToWGS84AndBack(PROJ.getNZGD1949AmuriCircuit());
  }

  /**
   * Test of getNZGD1949BayofPlentyCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD1949BayofPlentyCircuit() {
    testToWGS84AndBack(PROJ.getNZGD1949BayofPlentyCircuit());
  }

  /**
   * Test of getNZGD1949BluffCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD1949BluffCircuit() {
    testToWGS84AndBack(PROJ.getNZGD1949BluffCircuit());
  }

  /**
   * Test of getNZGD1949BullerCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD1949BullerCircuit() {
    testToWGS84AndBack(PROJ.getNZGD1949BullerCircuit());
  }

  /**
   * Test of getNZGD1949CollingwoodCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD1949CollingwoodCircuit() {
    testToWGS84AndBack(PROJ.getNZGD1949CollingwoodCircuit());
  }

  /**
   * Test of getNZGD1949GawlerCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD1949GawlerCircuit() {
    testToWGS84AndBack(PROJ.getNZGD1949GawlerCircuit());
  }

  /**
   * Test of getNZGD1949GreyCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD1949GreyCircuit() {
    testToWGS84AndBack(PROJ.getNZGD1949GreyCircuit());
  }

  /**
   * Test of getNZGD1949HawkesBayCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD1949HawkesBayCircuit() {
    testToWGS84AndBack(PROJ.getNZGD1949HawkesBayCircuit());
  }

  /**
   * Test of getNZGD1949HokitikaCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD1949HokitikaCircuit() {
    testToWGS84AndBack(PROJ.getNZGD1949HokitikaCircuit());
  }

  /**
   * Test of getNZGD1949JacksonsBayCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD1949JacksonsBayCircuit() {
    testToWGS84AndBack(PROJ.getNZGD1949JacksonsBayCircuit());
  }

  /**
   * Test of getNZGD1949KarameaCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD1949KarameaCircuit() {
    testToWGS84AndBack(PROJ.getNZGD1949KarameaCircuit());
  }

  /**
   * Test of getNZGD1949LindisPeakCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD1949LindisPeakCircuit() {
    testToWGS84AndBack(PROJ.getNZGD1949LindisPeakCircuit());
  }

  /**
   * Test of getNZGD1949MarlboroughCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD1949MarlboroughCircuit() {
    testToWGS84AndBack(PROJ.getNZGD1949MarlboroughCircuit());
  }

  /**
   * Test of getNZGD1949MountEdenCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD1949MountEdenCircuit() {
    testToWGS84AndBack(PROJ.getNZGD1949MountEdenCircuit());
  }

  /**
   * Test of getNZGD1949MountNicholasCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD1949MountNicholasCircuit() {
    testToWGS84AndBack(PROJ.getNZGD1949MountNicholasCircuit());
  }

  /**
   * Test of getNZGD1949MountPleasantCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD1949MountPleasantCircuit() {
    testToWGS84AndBack(PROJ.getNZGD1949MountPleasantCircuit());
  }

  /**
   * Test of getNZGD1949MountYorkCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD1949MountYorkCircuit() {
    testToWGS84AndBack(PROJ.getNZGD1949MountYorkCircuit());
  }

  /**
   * Test of getNZGD1949NelsonCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD1949NelsonCircuit() {
    testToWGS84AndBack(PROJ.getNZGD1949NelsonCircuit());
  }

  /**
   * Test of getNZGD1949NorthTaieriCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD1949NorthTaieriCircuit() {
    testToWGS84AndBack(PROJ.getNZGD1949NorthTaieriCircuit());
  }

  /**
   * Test of getNZGD1949ObservationPointCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD1949ObservationPointCircuit() {
    testToWGS84AndBack(PROJ.getNZGD1949ObservationPointCircuit());
  }

  /**
   * Test of getNZGD1949OkaritoCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD1949OkaritoCircuit() {
    testToWGS84AndBack(PROJ.getNZGD1949OkaritoCircuit());
  }

  /**
   * Test of getNZGD1949PovertyBayCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD1949PovertyBayCircuit() {
    testToWGS84AndBack(PROJ.getNZGD1949PovertyBayCircuit());
  }

  /**
   * Test of getNZGD1949TaranakiCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD1949TaranakiCircuit() {
    testToWGS84AndBack(PROJ.getNZGD1949TaranakiCircuit());
  }

  /**
   * Test of getNZGD1949TimaruCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD1949TimaruCircuit() {
    testToWGS84AndBack(PROJ.getNZGD1949TimaruCircuit());
  }

  /**
   * Test of getNZGD1949TuhirangiCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD1949TuhirangiCircuit() {
    testToWGS84AndBack(PROJ.getNZGD1949TuhirangiCircuit());
  }

  /**
   * Test of getNZGD1949UTMZone58S method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD1949UTMZone58S() {
    testToWGS84AndBack(PROJ.getNZGD1949UTMZone58S());
  }

  /**
   * Test of getNZGD1949UTMZone59S method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD1949UTMZone59S() {
    testToWGS84AndBack(PROJ.getNZGD1949UTMZone59S());
  }

  /**
   * Test of getNZGD1949UTMZone60S method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD1949UTMZone60S() {
    testToWGS84AndBack(PROJ.getNZGD1949UTMZone60S());
  }

  /**
   * Test of getNZGD1949WairarapaCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD1949WairarapaCircuit() {
    testToWGS84AndBack(PROJ.getNZGD1949WairarapaCircuit());
  }

  /**
   * Test of getNZGD1949WanganuiCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD1949WanganuiCircuit() {
    testToWGS84AndBack(PROJ.getNZGD1949WanganuiCircuit());
  }

  /**
   * Test of getNZGD1949WellingtonCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD1949WellingtonCircuit() {
    testToWGS84AndBack(PROJ.getNZGD1949WellingtonCircuit());
  }

  /**
   * Test of getNZGD2000AmuriCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD2000AmuriCircuit() {
    testToWGS84AndBack(PROJ.getNZGD2000AmuriCircuit());
  }

  /**
   * Test of getNZGD2000BayofPlentyCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD2000BayofPlentyCircuit() {
    testToWGS84AndBack(PROJ.getNZGD2000BayofPlentyCircuit());
  }

  /**
   * Test of getNZGD2000BluffCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD2000BluffCircuit() {
    testToWGS84AndBack(PROJ.getNZGD2000BluffCircuit());
  }

  /**
   * Test of getNZGD2000BullerCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD2000BullerCircuit() {
    testToWGS84AndBack(PROJ.getNZGD2000BullerCircuit());
  }

  /**
   * Test of getNZGD2000ChathamIslandCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD2000ChathamIslandCircuit() {
    testToWGS84AndBack(PROJ.getNZGD2000ChathamIslandCircuit());
  }

  /**
   * Test of getNZGD2000CollingwoodCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD2000CollingwoodCircuit() {
    testToWGS84AndBack(PROJ.getNZGD2000CollingwoodCircuit());
  }

  /**
   * Test of getNZGD2000GawlerCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD2000GawlerCircuit() {
    testToWGS84AndBack(PROJ.getNZGD2000GawlerCircuit());
  }

  /**
   * Test of getNZGD2000GreyCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD2000GreyCircuit() {
    testToWGS84AndBack(PROJ.getNZGD2000GreyCircuit());
  }

  /**
   * Test of getNZGD2000HawkesBayCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD2000HawkesBayCircuit() {
    testToWGS84AndBack(PROJ.getNZGD2000HawkesBayCircuit());
  }

  /**
   * Test of getNZGD2000HokitikaCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD2000HokitikaCircuit() {
    testToWGS84AndBack(PROJ.getNZGD2000HokitikaCircuit());
  }

  /**
   * Test of getNZGD2000JacksonsBayCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD2000JacksonsBayCircuit() {
    testToWGS84AndBack(PROJ.getNZGD2000JacksonsBayCircuit());
  }

  /**
   * Test of getNZGD2000KarameaCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD2000KarameaCircuit() {
    testToWGS84AndBack(PROJ.getNZGD2000KarameaCircuit());
  }

  /**
   * Test of getNZGD2000LindisPeakCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD2000LindisPeakCircuit() {
    testToWGS84AndBack(PROJ.getNZGD2000LindisPeakCircuit());
  }

  /**
   * Test of getNZGD2000MarlboroughCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD2000MarlboroughCircuit() {
    testToWGS84AndBack(PROJ.getNZGD2000MarlboroughCircuit());
  }

  /**
   * Test of getNZGD2000MountEdenCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD2000MountEdenCircuit() {
    testToWGS84AndBack(PROJ.getNZGD2000MountEdenCircuit());
  }

  /**
   * Test of getNZGD2000MountNicholasCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD2000MountNicholasCircuit() {
    testToWGS84AndBack(PROJ.getNZGD2000MountNicholasCircuit());
  }

  /**
   * Test of getNZGD2000MountPleasantCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD2000MountPleasantCircuit() {
    testToWGS84AndBack(PROJ.getNZGD2000MountPleasantCircuit());
  }

  /**
   * Test of getNZGD2000MountYorkCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD2000MountYorkCircuit() {
    testToWGS84AndBack(PROJ.getNZGD2000MountYorkCircuit());
  }

  /**
   * Test of getNZGD2000NelsonCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD2000NelsonCircuit() {
    testToWGS84AndBack(PROJ.getNZGD2000NelsonCircuit());
  }

  /**
   * Test of getNZGD2000NewZealandTransverseMercator method, of class
   * NationalGridsNewZealand.
   */
  @Test
  public void testNZGD2000NewZealandTransverseMercator() {
    testToWGS84AndBack(PROJ.getNZGD2000NewZealandTransverseMercator());
  }

  /**
   * Test of getNZGD2000NorthTaieriCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD2000NorthTaieriCircuit() {
    testToWGS84AndBack(PROJ.getNZGD2000NorthTaieriCircuit());
  }

  /**
   * Test of getNZGD2000ObservationPointCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD2000ObservationPointCircuit() {
    testToWGS84AndBack(PROJ.getNZGD2000ObservationPointCircuit());
  }

  /**
   * Test of getNZGD2000OkaritoCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD2000OkaritoCircuit() {
    testToWGS84AndBack(PROJ.getNZGD2000OkaritoCircuit());
  }

  /**
   * Test of getNZGD2000PovertyBayCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD2000PovertyBayCircuit() {
    testToWGS84AndBack(PROJ.getNZGD2000PovertyBayCircuit());
  }

  /**
   * Test of getNZGD2000TaranakiCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD2000TaranakiCircuit() {
    testToWGS84AndBack(PROJ.getNZGD2000TaranakiCircuit());
  }

  /**
   * Test of getNZGD2000TimaruCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD2000TimaruCircuit() {
    testToWGS84AndBack(PROJ.getNZGD2000TimaruCircuit());
  }

  /**
   * Test of getNZGD2000TuhirangiCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD2000TuhirangiCircuit() {
    testToWGS84AndBack(PROJ.getNZGD2000TuhirangiCircuit());
  }

  /**
   * Test of getNZGD2000UTMZone58S method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD2000UTMZone58S() {
    testToWGS84AndBack(PROJ.getNZGD2000UTMZone58S());
  }

  /**
   * Test of getNZGD2000UTMZone59S method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD2000UTMZone59S() {
    testToWGS84AndBack(PROJ.getNZGD2000UTMZone59S());
  }

  /**
   * Test of getNZGD2000UTMZone60S method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD2000UTMZone60S() {
    testToWGS84AndBack(PROJ.getNZGD2000UTMZone60S());
  }

  /**
   * Test of getNZGD2000WairarapaCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD2000WairarapaCircuit() {
    testToWGS84AndBack(PROJ.getNZGD2000WairarapaCircuit());
  }

  /**
   * Test of getNZGD2000WanganuiCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD2000WanganuiCircuit() {
    testToWGS84AndBack(PROJ.getNZGD2000WanganuiCircuit());
  }

  /**
   * Test of getNZGD2000WellingtonCircuit method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNZGD2000WellingtonCircuit() {
    testToWGS84AndBack(PROJ.getNZGD2000WellingtonCircuit());
  }

  /**
   * Test of getNewZealandMapGrid method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNewZealandMapGrid() {
    testToWGS84AndBack(PROJ.getNewZealandMapGrid());
  }

  /**
   * Test of getNewZealandNorthIsland method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNewZealandNorthIsland() {
    testToWGS84AndBack(PROJ.getNewZealandNorthIsland());
  }

  /**
   * Test of getNewZealandSouthIsland method, of class NationalGridsNewZealand.
   */
  @Test
  public void testNewZealandSouthIsland() {
    testToWGS84AndBack(PROJ.getNewZealandSouthIsland());
  }

}
