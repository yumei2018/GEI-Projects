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
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class UtmOtherTest {

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
  private static final UtmOther UTM = Projections.getProjected().getUtmOther();

  /**
   * Tests converting values to WGS84 and back.
   *
   * @param projection The ProjectionInfo to test.
   */
  public void testToWGS84AndBack(ProjectionInfo projection) {

    try {
      Coord original = new CoordXY(500000, 5000000);
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

  @Test
  public void testATS1977UTMZone19N() {
    testToWGS84AndBack(UTM.getATS1977UTMZone19N());
  }

  @Test
  public void testATS1977UTMZone20N() {
    testToWGS84AndBack(UTM.getATS1977UTMZone20N());
  }

  @Test
  public void testAbidjan1987UTMZone29N() {
    testToWGS84AndBack(UTM.getAbidjan1987UTMZone29N());
  }

  @Test
  public void testAbidjan1987UTMZone30N() {
    testToWGS84AndBack(UTM.getAbidjan1987UTMZone30N());
  }

  @Test
  public void testAdindanUTMZone37N() {
    testToWGS84AndBack(UTM.getAdindanUTMZone37N());
  }

  @Test
  public void testAdindanUTMZone38N() {
    testToWGS84AndBack(UTM.getAdindanUTMZone38N());
  }

  @Test
  public void testAfgooyeUTMZone38N() {
    testToWGS84AndBack(UTM.getAfgooyeUTMZone38N());
  }

  @Test
  public void testAfgooyeUTMZone39N() {
    testToWGS84AndBack(UTM.getAfgooyeUTMZone39N());
  }

  @Test
  public void testAinelAbd1970UTMZone37N() {
    testToWGS84AndBack(UTM.getAinelAbd1970UTMZone37N());
  }

  @Test
  public void testAinelAbd1970UTMZone38N() {
    testToWGS84AndBack(UTM.getAinelAbd1970UTMZone38N());
  }

  @Test
  public void testAinelAbd1970UTMZone39N() {
    testToWGS84AndBack(UTM.getAinelAbd1970UTMZone39N());
  }

  @Test
  public void testAmericanSamoa1962UTMZone2S() {
    testToWGS84AndBack(UTM.getAmericanSamoa1962UTMZone2S());
  }

  @Test
  public void testAratuUTMZone22S() {
    testToWGS84AndBack(UTM.getAratuUTMZone22S());
  }

  @Test
  public void testAratuUTMZone23S() {
    testToWGS84AndBack(UTM.getAratuUTMZone23S());
  }

  @Test
  public void testAratuUTMZone24S() {
    testToWGS84AndBack(UTM.getAratuUTMZone24S());
  }

  @Test
  public void testArc1950UTMZone34S() {
    testToWGS84AndBack(UTM.getArc1950UTMZone34S());
  }

  @Test
  public void testArc1950UTMZone35S() {
    testToWGS84AndBack(UTM.getArc1950UTMZone35S());
  }

  @Test
  public void testArc1950UTMZone36S() {
    testToWGS84AndBack(UTM.getArc1950UTMZone36S());
  }

  @Test
  public void testArc1960UTMZone35N() {
    testToWGS84AndBack(UTM.getArc1960UTMZone35N());
  }

  @Test
  public void testArc1960UTMZone35S() {
    testToWGS84AndBack(UTM.getArc1960UTMZone35S());
  }

  @Test
  public void testArc1960UTMZone36N() {
    testToWGS84AndBack(UTM.getArc1960UTMZone36N());
  }

  @Test
  public void testArc1960UTMZone36S() {
    testToWGS84AndBack(UTM.getArc1960UTMZone36S());
  }

  @Test
  public void testArc1960UTMZone37N() {
    testToWGS84AndBack(UTM.getArc1960UTMZone37N());
  }

  @Test
  public void testArc1960UTMZone37S() {
    testToWGS84AndBack(UTM.getArc1960UTMZone37S());
  }

  @Test
  public void testAzoresCentral1995UTMZone26N() {
    testToWGS84AndBack(UTM.getAzoresCentral1995UTMZone26N());
  }

  @Test
  public void testAzoresOriental1995UTMZone26N() {
    testToWGS84AndBack(UTM.getAzoresOriental1995UTMZone26N());
  }

  @Test
  public void testBataviaUTMZone48S() {
    testToWGS84AndBack(UTM.getBataviaUTMZone48S());
  }

  @Test
  public void testBataviaUTMZone49S() {
    testToWGS84AndBack(UTM.getBataviaUTMZone49S());
  }

  @Test
  public void testBataviaUTMZone50S() {
    testToWGS84AndBack(UTM.getBataviaUTMZone50S());
  }

  @Test
  public void testBissauUTMZone28N() {
    testToWGS84AndBack(UTM.getBissauUTMZone28N());
  }

  @Test
  public void testBogotaUTMZone17N() {
    testToWGS84AndBack(UTM.getBogotaUTMZone17N());
  }

  @Test
  public void testBogotaUTMZone18N() {
    testToWGS84AndBack(UTM.getBogotaUTMZone18N());
  }

  @Test
  public void testCSG1967UTMZone22N() {
    testToWGS84AndBack(UTM.getCSG1967UTMZone22N());
  }

  @Test
  public void testCamacupaUTMZone32S() {
    testToWGS84AndBack(UTM.getCamacupaUTMZone32S());
  }

  @Test
  public void testCamacupaUTMZone33S() {
    testToWGS84AndBack(UTM.getCamacupaUTMZone33S());
  }

  @Test
  public void testCampoInchauspeUTM19S() {
    testToWGS84AndBack(UTM.getCampoInchauspeUTM19S());
  }

  @Test
  public void testCampoInchauspeUTM20S() {
    testToWGS84AndBack(UTM.getCampoInchauspeUTM20S());
  }

  @Test
  public void testCapeUTMZone34S() {
    testToWGS84AndBack(UTM.getCapeUTMZone34S());
  }

  @Test
  public void testCapeUTMZone35S() {
    testToWGS84AndBack(UTM.getCapeUTMZone35S());
  }

  @Test
  public void testCapeUTMZone36S() {
    testToWGS84AndBack(UTM.getCapeUTMZone36S());
  }

  @Test
  public void testCarthageUTMZone32N() {
    testToWGS84AndBack(UTM.getCarthageUTMZone32N());
  }

  @Test
  public void testCombani1950UTMZone38S() {
    testToWGS84AndBack(UTM.getCombani1950UTMZone38S());
  }

  @Test
  public void testConakry1905UTMZone28N() {
    testToWGS84AndBack(UTM.getConakry1905UTMZone28N());
  }

  @Test
  public void testConakry1905UTMZone29N() {
    testToWGS84AndBack(UTM.getConakry1905UTMZone29N());
  }

  @Test
  public void testCorregoAlegreUTMZone23S() {
    testToWGS84AndBack(UTM.getCorregoAlegreUTMZone23S());
  }

  @Test
  public void testCorregoAlegreUTMZone24S() {
    testToWGS84AndBack(UTM.getCorregoAlegreUTMZone24S());
  }

  @Test
  public void testDabolaUTMZone28N() {
    testToWGS84AndBack(UTM.getDabolaUTMZone28N());
  }

  @Test
  public void testDabolaUTMZone29N() {
    testToWGS84AndBack(UTM.getDabolaUTMZone29N());
  }

  @Test
  public void testDatum73UTMZone29N() {
    testToWGS84AndBack(UTM.getDatum73UTMZone29N());
  }

  @Test
  public void testDoualaUTMZone32N() {
    testToWGS84AndBack(UTM.getDoualaUTMZone32N());
  }

  @Test
  public void testED1950ED77UTMZone38N() {
    testToWGS84AndBack(UTM.getED1950ED77UTMZone38N());
  }

  @Test
  public void testED1950ED77UTMZone39N() {
    testToWGS84AndBack(UTM.getED1950ED77UTMZone39N());
  }

  @Test
  public void testED1950ED77UTMZone40N() {
    testToWGS84AndBack(UTM.getED1950ED77UTMZone40N());
  }

  @Test
  public void testED1950ED77UTMZone41N() {
    testToWGS84AndBack(UTM.getED1950ED77UTMZone41N());
  }

  @Test
  public void testELD1979UTMZone32N() {
    testToWGS84AndBack(UTM.getELD1979UTMZone32N());
  }

  @Test
  public void testELD1979UTMZone33N() {
    testToWGS84AndBack(UTM.getELD1979UTMZone33N());
  }

  @Test
  public void testELD1979UTMZone34N() {
    testToWGS84AndBack(UTM.getELD1979UTMZone34N());
  }

  @Test
  public void testELD1979UTMZone35N() {
    testToWGS84AndBack(UTM.getELD1979UTMZone35N());
  }

  @Test
  public void testETRF1989UTMZone28N() {
    testToWGS84AndBack(UTM.getETRF1989UTMZone28N());
  }

  @Test
  public void testETRF1989UTMZone29N() {
    testToWGS84AndBack(UTM.getETRF1989UTMZone29N());
  }

  @Test
  public void testETRF1989UTMZone30N() {
    testToWGS84AndBack(UTM.getETRF1989UTMZone30N());
  }

  @Test
  public void testETRF1989UTMZone31N() {
    testToWGS84AndBack(UTM.getETRF1989UTMZone31N());
  }

  @Test
  public void testETRF1989UTMZone32N() {
    testToWGS84AndBack(UTM.getETRF1989UTMZone32N());
  }

  @Test
  public void testETRF1989UTMZone33N() {
    testToWGS84AndBack(UTM.getETRF1989UTMZone33N());
  }

  @Test
  public void testETRF1989UTMZone34N() {
    testToWGS84AndBack(UTM.getETRF1989UTMZone34N());
  }

  @Test
  public void testETRF1989UTMZone35N() {
    testToWGS84AndBack(UTM.getETRF1989UTMZone35N());
  }

  @Test
  public void testETRF1989UTMZone36N() {
    testToWGS84AndBack(UTM.getETRF1989UTMZone36N());
  }

  @Test
  public void testETRF1989UTMZone37N() {
    testToWGS84AndBack(UTM.getETRF1989UTMZone37N());
  }

  @Test
  public void testETRF1989UTMZone38N() {
    testToWGS84AndBack(UTM.getETRF1989UTMZone38N());
  }

  @Test
  public void testETRS1989UTMZone26N() {
    testToWGS84AndBack(UTM.getETRS1989UTMZone26N());
  }

  @Test
  public void testETRS1989UTMZone27N() {
    testToWGS84AndBack(UTM.getETRS1989UTMZone27N());
  }

  @Test
  public void testETRS1989UTMZone28N() {
    testToWGS84AndBack(UTM.getETRS1989UTMZone28N());
  }

  @Test
  public void testETRS1989UTMZone29N() {
    testToWGS84AndBack(UTM.getETRS1989UTMZone29N());
  }

  @Test
  public void testETRS1989UTMZone30N() {
    testToWGS84AndBack(UTM.getETRS1989UTMZone30N());
  }

  @Test
  public void testETRS1989UTMZone31N() {
    testToWGS84AndBack(UTM.getETRS1989UTMZone31N());
  }

  @Test
  public void testETRS1989UTMZone32N() {
    testToWGS84AndBack(UTM.getETRS1989UTMZone32N());
  }

  @Test
  public void testETRS1989UTMZone33N() {
    testToWGS84AndBack(UTM.getETRS1989UTMZone33N());
  }

  @Test
  public void testETRS1989UTMZone34N() {
    testToWGS84AndBack(UTM.getETRS1989UTMZone34N());
  }

  @Test
  public void testETRS1989UTMZone35N() {
    testToWGS84AndBack(UTM.getETRS1989UTMZone35N());
  }

  @Test
  public void testETRS1989UTMZone36N() {
    testToWGS84AndBack(UTM.getETRS1989UTMZone36N());
  }

  @Test
  public void testETRS1989UTMZone37N() {
    testToWGS84AndBack(UTM.getETRS1989UTMZone37N());
  }

  @Test
  public void testETRS1989UTMZone38N() {
    testToWGS84AndBack(UTM.getETRS1989UTMZone38N());
  }

  @Test
  public void testETRS1989UTMZone39N() {
    testToWGS84AndBack(UTM.getETRS1989UTMZone39N());
  }

  @Test
  public void testEuropeanDatum1950UTMZone28N() {
    testToWGS84AndBack(UTM.getEuropeanDatum1950UTMZone28N());
  }

  @Test
  public void testEuropeanDatum1950UTMZone29N() {
    testToWGS84AndBack(UTM.getEuropeanDatum1950UTMZone29N());
  }

  @Test
  public void testEuropeanDatum1950UTMZone30N() {
    testToWGS84AndBack(UTM.getEuropeanDatum1950UTMZone30N());
  }

  @Test
  public void testEuropeanDatum1950UTMZone31N() {
    testToWGS84AndBack(UTM.getEuropeanDatum1950UTMZone31N());
  }

  @Test
  public void testEuropeanDatum1950UTMZone32N() {
    testToWGS84AndBack(UTM.getEuropeanDatum1950UTMZone32N());
  }

  @Test
  public void testEuropeanDatum1950UTMZone33N() {
    testToWGS84AndBack(UTM.getEuropeanDatum1950UTMZone33N());
  }

  @Test
  public void testEuropeanDatum1950UTMZone34N() {
    testToWGS84AndBack(UTM.getEuropeanDatum1950UTMZone34N());
  }

  @Test
  public void testEuropeanDatum1950UTMZone35N() {
    testToWGS84AndBack(UTM.getEuropeanDatum1950UTMZone35N());
  }

  @Test
  public void testEuropeanDatum1950UTMZone36N() {
    testToWGS84AndBack(UTM.getEuropeanDatum1950UTMZone36N());
  }

  @Test
  public void testEuropeanDatum1950UTMZone37N() {
    testToWGS84AndBack(UTM.getEuropeanDatum1950UTMZone37N());
  }

  @Test
  public void testEuropeanDatum1950UTMZone38N() {
    testToWGS84AndBack(UTM.getEuropeanDatum1950UTMZone38N());
  }

  @Test
  public void testFahudUTMZone39N() {
    testToWGS84AndBack(UTM.getFahudUTMZone39N());
  }

  @Test
  public void testFahudUTMZone40N() {
    testToWGS84AndBack(UTM.getFahudUTMZone40N());
  }

  @Test
  public void testFortDesaixUTMZone20N() {
    testToWGS84AndBack(UTM.getFortDesaixUTMZone20N());
  }

  @Test
  public void testFortMarigotUTMZone20N() {
    testToWGS84AndBack(UTM.getFortMarigotUTMZone20N());
  }

  @Test
  public void testGarouaUTMZone33N() {
    testToWGS84AndBack(UTM.getGarouaUTMZone33N());
  }

  @Test
  public void testGraciosaBaseSW1948UTMZone26N() {
    testToWGS84AndBack(UTM.getGraciosaBaseSW1948UTMZone26N());
  }

  @Test
  public void testGrandComorosUTMZone38S() {
    testToWGS84AndBack(UTM.getGrandComorosUTMZone38S());
  }

  @Test
  public void testHitoXVIII1963UTMZone19S() {
    testToWGS84AndBack(UTM.getHitoXVIII1963UTMZone19S());
  }

  @Test
  public void testHjorsey1955UTMZone26N() {
    testToWGS84AndBack(UTM.getHjorsey1955UTMZone26N());
  }

  @Test
  public void testHjorsey1955UTMZone27N() {
    testToWGS84AndBack(UTM.getHjorsey1955UTMZone27N());
  }

  @Test
  public void testHjorsey1955UTMZone28N() {
    testToWGS84AndBack(UTM.getHjorsey1955UTMZone28N());
  }

  @Test
  public void testHongKong1980UTMZone49N() {
    testToWGS84AndBack(UTM.getHongKong1980UTMZone49N());
  }

  @Test
  public void testHongKong1980UTMZone50N() {
    testToWGS84AndBack(UTM.getHongKong1980UTMZone50N());
  }

  @Test
  public void testIGM1995UTMZone32N() {
    testToWGS84AndBack(UTM.getIGM1995UTMZone32N());
  }

  @Test
  public void testIGM1995UTMZone33N() {
    testToWGS84AndBack(UTM.getIGM1995UTMZone33N());
  }

  @Test
  public void testIGN53MareUTMZone58S() {
    testToWGS84AndBack(UTM.getIGN53MareUTMZone58S());
  }

  @Test
  public void testIGN56LifouUTMZone58S() {
    testToWGS84AndBack(UTM.getIGN56LifouUTMZone58S());
  }

  @Test
  public void testIGN72GrandeTerreUTMZone58S() {
    testToWGS84AndBack(UTM.getIGN72GrandeTerreUTMZone58S());
  }

  @Test
  public void testIGN72NukuHivaUTMZone7S() {
    testToWGS84AndBack(UTM.getIGN72NukuHivaUTMZone7S());
  }

  @Test
  public void testIRENET95UTMZone29N() {
    testToWGS84AndBack(UTM.getIRENET95UTMZone29N());
  }

  @Test
  public void testIndian1954UTMZone46N() {
    testToWGS84AndBack(UTM.getIndian1954UTMZone46N());
  }

  @Test
  public void testIndian1954UTMZone47N() {
    testToWGS84AndBack(UTM.getIndian1954UTMZone47N());
  }

  @Test
  public void testIndian1954UTMZone48N() {
    testToWGS84AndBack(UTM.getIndian1954UTMZone48N());
  }

  @Test
  public void testIndian1960UTMZone48N() {
    testToWGS84AndBack(UTM.getIndian1960UTMZone48N());
  }

  @Test
  public void testIndian1960UTMZone49N() {
    testToWGS84AndBack(UTM.getIndian1960UTMZone49N());
  }

  @Test
  public void testIndian1975UTMZone47N() {
    testToWGS84AndBack(UTM.getIndian1975UTMZone47N());
  }

  @Test
  public void testIndian1975UTMZone48N() {
    testToWGS84AndBack(UTM.getIndian1975UTMZone48N());
  }

  @Test
  public void testIndonesia1974UTMZone46N() {
    testToWGS84AndBack(UTM.getIndonesia1974UTMZone46N());
  }

  @Test
  public void testIndonesia1974UTMZone46S() {
    testToWGS84AndBack(UTM.getIndonesia1974UTMZone46S());
  }

  @Test
  public void testIndonesia1974UTMZone47N() {
    testToWGS84AndBack(UTM.getIndonesia1974UTMZone47N());
  }

  @Test
  public void testIndonesia1974UTMZone47S() {
    testToWGS84AndBack(UTM.getIndonesia1974UTMZone47S());
  }

  @Test
  public void testIndonesia1974UTMZone48N() {
    testToWGS84AndBack(UTM.getIndonesia1974UTMZone48N());
  }

  @Test
  public void testIndonesia1974UTMZone48S() {
    testToWGS84AndBack(UTM.getIndonesia1974UTMZone48S());
  }

  @Test
  public void testIndonesia1974UTMZone49N() {
    testToWGS84AndBack(UTM.getIndonesia1974UTMZone49N());
  }

  @Test
  public void testIndonesia1974UTMZone49S() {
    testToWGS84AndBack(UTM.getIndonesia1974UTMZone49S());
  }

  @Test
  public void testIndonesia1974UTMZone50N() {
    testToWGS84AndBack(UTM.getIndonesia1974UTMZone50N());
  }

  @Test
  public void testIndonesia1974UTMZone50S() {
    testToWGS84AndBack(UTM.getIndonesia1974UTMZone50S());
  }

  @Test
  public void testIndonesia1974UTMZone51N() {
    testToWGS84AndBack(UTM.getIndonesia1974UTMZone51N());
  }

  @Test
  public void testIndonesia1974UTMZone51S() {
    testToWGS84AndBack(UTM.getIndonesia1974UTMZone51S());
  }

  @Test
  public void testIndonesia1974UTMZone52N() {
    testToWGS84AndBack(UTM.getIndonesia1974UTMZone52N());
  }

  @Test
  public void testIndonesia1974UTMZone52S() {
    testToWGS84AndBack(UTM.getIndonesia1974UTMZone52S());
  }

  @Test
  public void testIndonesia1974UTMZone53N() {
    testToWGS84AndBack(UTM.getIndonesia1974UTMZone53N());
  }

  @Test
  public void testIndonesia1974UTMZone53S() {
    testToWGS84AndBack(UTM.getIndonesia1974UTMZone53S());
  }

  @Test
  public void testIndonesia1974UTMZone54S() {
    testToWGS84AndBack(UTM.getIndonesia1974UTMZone54S());
  }

  @Test
  public void testJGD2000UTMZone51N() {
    testToWGS84AndBack(UTM.getJGD2000UTMZone51N());
  }

  @Test
  public void testJGD2000UTMZone52N() {
    testToWGS84AndBack(UTM.getJGD2000UTMZone52N());
  }

  @Test
  public void testJGD2000UTMZone53N() {
    testToWGS84AndBack(UTM.getJGD2000UTMZone53N());
  }

  @Test
  public void testJGD2000UTMZone54N() {
    testToWGS84AndBack(UTM.getJGD2000UTMZone54N());
  }

  @Test
  public void testJGD2000UTMZone55N() {
    testToWGS84AndBack(UTM.getJGD2000UTMZone55N());
  }

  @Test
  public void testJGD2000UTMZone56N() {
    testToWGS84AndBack(UTM.getJGD2000UTMZone56N());
  }

  @Test
  public void testK01949UTMZone42S() {
    testToWGS84AndBack(UTM.getK01949UTMZone42S());
  }

  @Test
  public void testKertauUTMZone47N() {
    testToWGS84AndBack(UTM.getKertauUTMZone47N());
  }

  @Test
  public void testKertauUTMZone48N() {
    testToWGS84AndBack(UTM.getKertauUTMZone48N());
  }

  @Test
  public void testKousseriUTMZone33N() {
    testToWGS84AndBack(UTM.getKousseriUTMZone33N());
  }

  @Test
  public void testLaCanoaUTMZone18N() {
    testToWGS84AndBack(UTM.getLaCanoaUTMZone18N());
  }

  @Test
  public void testLaCanoaUTMZone19N() {
    testToWGS84AndBack(UTM.getLaCanoaUTMZone19N());
  }

  @Test
  public void testLaCanoaUTMZone20N() {
    testToWGS84AndBack(UTM.getLaCanoaUTMZone20N());
  }

  @Test
  public void testLaCanoaUTMZone21N() {
    testToWGS84AndBack(UTM.getLaCanoaUTMZone21N());
  }

  @Test
  public void testLocodjo1965UTMZone29N() {
    testToWGS84AndBack(UTM.getLocodjo1965UTMZone29N());
  }

  @Test
  public void testLocodjo1965UTMZone30N() {
    testToWGS84AndBack(UTM.getLocodjo1965UTMZone30N());
  }

  @Test
  public void testLomeUTMZone31N() {
    testToWGS84AndBack(UTM.getLomeUTMZone31N());
  }

  @Test
  public void testMOP78UTMZone1S() {
    testToWGS84AndBack(UTM.getMOP78UTMZone1S());
  }

  @Test
  public void testMalongo1987UTMZone32S() {
    testToWGS84AndBack(UTM.getMalongo1987UTMZone32S());
  }

  @Test
  public void testManoca1962UTMZone32N() {
    testToWGS84AndBack(UTM.getManoca1962UTMZone32N());
  }

  @Test
  public void testMassawaUTMZone37N() {
    testToWGS84AndBack(UTM.getMassawaUTMZone37N());
  }

  @Test
  public void testMhastUTMZone32S() {
    testToWGS84AndBack(UTM.getMhastUTMZone32S());
  }

  @Test
  public void testMinnaUTMZone31N() {
    testToWGS84AndBack(UTM.getMinnaUTMZone31N());
  }

  @Test
  public void testMinnaUTMZone32N() {
    testToWGS84AndBack(UTM.getMinnaUTMZone32N());
  }

  @Test
  public void testMoznetUTMZone36S() {
    testToWGS84AndBack(UTM.getMoznetUTMZone36S());
  }

  @Test
  public void testMoznetUTMZone37S() {
    testToWGS84AndBack(UTM.getMoznetUTMZone37S());
  }

  @Test
  public void testMporalokoUTMZone32N() {
    testToWGS84AndBack(UTM.getMporalokoUTMZone32N());
  }

  @Test
  public void testMporalokoUTMZone32S() {
    testToWGS84AndBack(UTM.getMporalokoUTMZone32S());
  }

  @Test
  public void testNAD1927BLMZone14N() {
    testToWGS84AndBack(UTM.getNAD1927BLMZone14N());
  }

  @Test
  public void testNAD1927BLMZone15N() {
    testToWGS84AndBack(UTM.getNAD1927BLMZone15N());
  }

  @Test
  public void testNAD1927BLMZone16N() {
    testToWGS84AndBack(UTM.getNAD1927BLMZone16N());
  }

  @Test
  public void testNAD1927BLMZone17N() {
    testToWGS84AndBack(UTM.getNAD1927BLMZone17N());
  }

  @Test
  public void testNAD1983HARNUTMZone11N() {
    testToWGS84AndBack(UTM.getNAD1983HARNUTMZone11N());
  }

  @Test
  public void testNAD1983HARNUTMZone12N() {
    testToWGS84AndBack(UTM.getNAD1983HARNUTMZone12N());
  }

  @Test
  public void testNAD1983HARNUTMZone13N() {
    testToWGS84AndBack(UTM.getNAD1983HARNUTMZone13N());
  }

  @Test
  public void testNAD1983HARNUTMZone18N() {
    testToWGS84AndBack(UTM.getNAD1983HARNUTMZone18N());
  }

  @Test
  public void testNAD1983HARNUTMZone2S() {
    testToWGS84AndBack(UTM.getNAD1983HARNUTMZone2S());
  }

  @Test
  public void testNAD1983HARNUTMZone4N() {
    testToWGS84AndBack(UTM.getNAD1983HARNUTMZone4N());
  }

  @Test
  public void testNAD1983HARNUTMZone5N() {
    testToWGS84AndBack(UTM.getNAD1983HARNUTMZone5N());
  }

  @Test
  public void testNEA74NoumeaUTMZone58S() {
    testToWGS84AndBack(UTM.getNEA74NoumeaUTMZone58S());
  }

  @Test
  public void testNGNUTMZone38N() {
    testToWGS84AndBack(UTM.getNGNUTMZone38N());
  }

  @Test
  public void testNGNUTMZone39N() {
    testToWGS84AndBack(UTM.getNGNUTMZone39N());
  }

  @Test
  public void testNGO1948UTMZone32N() {
    testToWGS84AndBack(UTM.getNGO1948UTMZone32N());
  }

  @Test
  public void testNGO1948UTMZone33N() {
    testToWGS84AndBack(UTM.getNGO1948UTMZone33N());
  }

  @Test
  public void testNGO1948UTMZone34N() {
    testToWGS84AndBack(UTM.getNGO1948UTMZone34N());
  }

  @Test
  public void testNGO1948UTMZone35N() {
    testToWGS84AndBack(UTM.getNGO1948UTMZone35N());
  }

  @Test
  public void testNZGD1949UTMZone58S() {
    testToWGS84AndBack(UTM.getNZGD1949UTMZone58S());
  }

  @Test
  public void testNZGD1949UTMZone59S() {
    testToWGS84AndBack(UTM.getNZGD1949UTMZone59S());
  }

  @Test
  public void testNZGD1949UTMZone60S() {
    testToWGS84AndBack(UTM.getNZGD1949UTMZone60S());
  }

  @Test
  public void testNZGD2000UTMZone58S() {
    testToWGS84AndBack(UTM.getNZGD2000UTMZone58S());
  }

  @Test
  public void testNZGD2000UTMZone59S() {
    testToWGS84AndBack(UTM.getNZGD2000UTMZone59S());
  }

  @Test
  public void testNZGD2000UTMZone60S() {
    testToWGS84AndBack(UTM.getNZGD2000UTMZone60S());
  }

  @Test
  public void testNahrwan1967UTMZone38N() {
    testToWGS84AndBack(UTM.getNahrwan1967UTMZone38N());
  }

  @Test
  public void testNahrwan1967UTMZone39N() {
    testToWGS84AndBack(UTM.getNahrwan1967UTMZone39N());
  }

  @Test
  public void testNahrwan1967UTMZone40N() {
    testToWGS84AndBack(UTM.getNahrwan1967UTMZone40N());
  }

  @Test
  public void testNaparima1955UTMZone20N() {
    testToWGS84AndBack(UTM.getNaparima1955UTMZone20N());
  }

  @Test
  public void testNaparima1972UTMZone20N() {
    testToWGS84AndBack(UTM.getNaparima1972UTMZone20N());
  }

  @Test
  public void testNordSahara1959UTMZone29N() {
    testToWGS84AndBack(UTM.getNordSahara1959UTMZone29N());
  }

  @Test
  public void testNordSahara1959UTMZone30N() {
    testToWGS84AndBack(UTM.getNordSahara1959UTMZone30N());
  }

  @Test
  public void testNordSahara1959UTMZone31N() {
    testToWGS84AndBack(UTM.getNordSahara1959UTMZone31N());
  }

  @Test
  public void testNordSahara1959UTMZone32N() {
    testToWGS84AndBack(UTM.getNordSahara1959UTMZone32N());
  }

  @Test
  public void testObservMeteorologico1939UTMZone25N() {
    testToWGS84AndBack(UTM.getObservMeteorologico1939UTMZone25N());
  }

  @Test
  public void testOldHawaiianUTMZone4N() {
    testToWGS84AndBack(UTM.getOldHawaiianUTMZone4N());
  }

  @Test
  public void testOldHawaiianUTMZone5N() {
    testToWGS84AndBack(UTM.getOldHawaiianUTMZone5N());
  }

  @Test
  public void testPDO1993UTMZone39N() {
    testToWGS84AndBack(UTM.getPDO1993UTMZone39N());
  }

  @Test
  public void testPDO1993UTMZone40N() {
    testToWGS84AndBack(UTM.getPDO1993UTMZone40N());
  }

  @Test
  public void testPointeNoireUTMZone32S() {
    testToWGS84AndBack(UTM.getPointeNoireUTMZone32S());
  }

  @Test
  public void testPortoSanto1936UTMZone28N() {
    testToWGS84AndBack(UTM.getPortoSanto1936UTMZone28N());
  }

  @Test
  public void testPortoSanto1995UTMZone28N() {
    testToWGS84AndBack(UTM.getPortoSanto1995UTMZone28N());
  }

  @Test
  public void testProvSAmerDatumUTMZone17s() {
    testToWGS84AndBack(UTM.getProvSAmerDatumUTMZone17s());
  }

  @Test
  public void testProvSAmerDatumUTMZone18N() {
    testToWGS84AndBack(UTM.getProvSAmerDatumUTMZone18N());
  }

  @Test
  public void testProvSAmerDatumUTMZone18S() {
    testToWGS84AndBack(UTM.getProvSAmerDatumUTMZone18S());
  }

  @Test
  public void testProvSAmerDatumUTMZone19N() {
    testToWGS84AndBack(UTM.getProvSAmerDatumUTMZone19N());
  }

  @Test
  public void testProvSAmerDatumUTMZone19S() {
    testToWGS84AndBack(UTM.getProvSAmerDatumUTMZone19S());
  }

  @Test
  public void testProvSAmerDatumUTMZone20N() {
    testToWGS84AndBack(UTM.getProvSAmerDatumUTMZone20N());
  }

  @Test
  public void testProvSAmerDatumUTMZone20S() {
    testToWGS84AndBack(UTM.getProvSAmerDatumUTMZone20S());
  }

  @Test
  public void testProvSAmerDatumUTMZone21N() {
    testToWGS84AndBack(UTM.getProvSAmerDatumUTMZone21N());
  }

  @Test
  public void testProvSAmerDatumUTMZone22S() {
    testToWGS84AndBack(UTM.getProvSAmerDatumUTMZone22S());
  }

  @Test
  public void testPuertoRicoUTMZone20N() {
    testToWGS84AndBack(UTM.getPuertoRicoUTMZone20N());
  }

  @Test
  public void testQornoq1927UTMZone22N() {
    testToWGS84AndBack(UTM.getQornoq1927UTMZone22N());
  }

  @Test
  public void testQornoq1927UTMZone23N() {
    testToWGS84AndBack(UTM.getQornoq1927UTMZone23N());
  }

  @Test
  public void testREGVENUTMZone18N() {
    testToWGS84AndBack(UTM.getREGVENUTMZone18N());
  }

  @Test
  public void testREGVENUTMZone19N() {
    testToWGS84AndBack(UTM.getREGVENUTMZone19N());
  }

  @Test
  public void testREGVENUTMZone20N() {
    testToWGS84AndBack(UTM.getREGVENUTMZone20N());
  }

  @Test
  public void testRGFG1995UTMZone22N() {
    testToWGS84AndBack(UTM.getRGFG1995UTMZone22N());
  }

  @Test
  public void testRGR1992UTMZone40S() {
    testToWGS84AndBack(UTM.getRGR1992UTMZone40S());
  }

  @Test
  public void testRRAF1991UTMZone20N() {
    testToWGS84AndBack(UTM.getRRAF1991UTMZone20N());
  }

  @Test
  public void testSIRGASUTMZone17N() {
    testToWGS84AndBack(UTM.getSIRGASUTMZone17N());
  }

  @Test
  public void testSIRGASUTMZone17S() {
    testToWGS84AndBack(UTM.getSIRGASUTMZone17S());
  }

  @Test
  public void testSIRGASUTMZone18N() {
    testToWGS84AndBack(UTM.getSIRGASUTMZone18N());
  }

  @Test
  public void testSIRGASUTMZone18S() {
    testToWGS84AndBack(UTM.getSIRGASUTMZone18S());
  }

  @Test
  public void testSIRGASUTMZone19N() {
    testToWGS84AndBack(UTM.getSIRGASUTMZone19N());
  }

  @Test
  public void testSIRGASUTMZone19S() {
    testToWGS84AndBack(UTM.getSIRGASUTMZone19S());
  }

  @Test
  public void testSIRGASUTMZone20N() {
    testToWGS84AndBack(UTM.getSIRGASUTMZone20N());
  }

  @Test
  public void testSIRGASUTMZone20S() {
    testToWGS84AndBack(UTM.getSIRGASUTMZone20S());
  }

  @Test
  public void testSIRGASUTMZone21N() {
    testToWGS84AndBack(UTM.getSIRGASUTMZone21N());
  }

  @Test
  public void testSIRGASUTMZone21S() {
    testToWGS84AndBack(UTM.getSIRGASUTMZone21S());
  }

  @Test
  public void testSIRGASUTMZone22N() {
    testToWGS84AndBack(UTM.getSIRGASUTMZone22N());
  }

  @Test
  public void testSIRGASUTMZone22S() {
    testToWGS84AndBack(UTM.getSIRGASUTMZone22S());
  }

  @Test
  public void testSIRGASUTMZone23S() {
    testToWGS84AndBack(UTM.getSIRGASUTMZone23S());
  }

  @Test
  public void testSIRGASUTMZone24S() {
    testToWGS84AndBack(UTM.getSIRGASUTMZone24S());
  }

  @Test
  public void testSIRGASUTMZone25S() {
    testToWGS84AndBack(UTM.getSIRGASUTMZone25S());
  }

  @Test
  public void testST71BelepUTMZone58S() {
    testToWGS84AndBack(UTM.getST71BelepUTMZone58S());
  }

  @Test
  public void testST84IledesPinsUTMZone58S() {
    testToWGS84AndBack(UTM.getST84IledesPinsUTMZone58S());
  }

  @Test
  public void testST87OuveaUTMZone58S() {
    testToWGS84AndBack(UTM.getST87OuveaUTMZone58S());
  }

  @Test
  public void testSaintPierreetMiquelon1950UTMZone21N() {
    testToWGS84AndBack(UTM.getSaintPierreetMiquelon1950UTMZone21N());
  }

  @Test
  public void testSainteAnneUTMZone20N() {
    testToWGS84AndBack(UTM.getSainteAnneUTMZone20N());
  }

  @Test
  public void testSambojaUTMZone50S() {
    testToWGS84AndBack(UTM.getSambojaUTMZone50S());
  }

  @Test
  public void testSaoBrazUTMZone26N() {
    testToWGS84AndBack(UTM.getSaoBrazUTMZone26N());
  }

  @Test
  public void testSapperHill1943UTMZone20S() {
    testToWGS84AndBack(UTM.getSapperHill1943UTMZone20S());
  }

  @Test
  public void testSapperHill1943UTMZone21S() {
    testToWGS84AndBack(UTM.getSapperHill1943UTMZone21S());
  }

  @Test
  public void testSchwarzeckUTMZone33S() {
    testToWGS84AndBack(UTM.getSchwarzeckUTMZone33S());
  }

  @Test
  public void testSelvagemGrande1938UTMZone28N() {
    testToWGS84AndBack(UTM.getSelvagemGrande1938UTMZone28N());
  }

  @Test
  public void testSierraLeone1968UTMZone28N() {
    testToWGS84AndBack(UTM.getSierraLeone1968UTMZone28N());
  }

  @Test
  public void testSierraLeone1968UTMZone29N() {
    testToWGS84AndBack(UTM.getSierraLeone1968UTMZone29N());
  }

  @Test
  public void testSouthAmerican1969UTMZone17S() {
    testToWGS84AndBack(UTM.getSouthAmerican1969UTMZone17S());
  }

  @Test
  public void testSouthAmerican1969UTMZone18N() {
    testToWGS84AndBack(UTM.getSouthAmerican1969UTMZone18N());
  }

  @Test
  public void testSouthAmerican1969UTMZone18S() {
    testToWGS84AndBack(UTM.getSouthAmerican1969UTMZone18S());
  }

  @Test
  public void testSouthAmerican1969UTMZone19N() {
    testToWGS84AndBack(UTM.getSouthAmerican1969UTMZone19N());
  }

  @Test
  public void testSouthAmerican1969UTMZone19S() {
    testToWGS84AndBack(UTM.getSouthAmerican1969UTMZone19S());
  }

  @Test
  public void testSouthAmerican1969UTMZone20N() {
    testToWGS84AndBack(UTM.getSouthAmerican1969UTMZone20N());
  }

  @Test
  public void testSouthAmerican1969UTMZone20S() {
    testToWGS84AndBack(UTM.getSouthAmerican1969UTMZone20S());
  }

  @Test
  public void testSouthAmerican1969UTMZone21N() {
    testToWGS84AndBack(UTM.getSouthAmerican1969UTMZone21N());
  }

  @Test
  public void testSouthAmerican1969UTMZone21S() {
    testToWGS84AndBack(UTM.getSouthAmerican1969UTMZone21S());
  }

  @Test
  public void testSouthAmerican1969UTMZone22N() {
    testToWGS84AndBack(UTM.getSouthAmerican1969UTMZone22N());
  }

  @Test
  public void testSouthAmerican1969UTMZone22S() {
    testToWGS84AndBack(UTM.getSouthAmerican1969UTMZone22S());
  }

  @Test
  public void testSouthAmerican1969UTMZone23S() {
    testToWGS84AndBack(UTM.getSouthAmerican1969UTMZone23S());
  }

  @Test
  public void testSouthAmerican1969UTMZone24S() {
    testToWGS84AndBack(UTM.getSouthAmerican1969UTMZone24S());
  }

  @Test
  public void testSouthAmerican1969UTMZone25S() {
    testToWGS84AndBack(UTM.getSouthAmerican1969UTMZone25S());
  }

  @Test
  public void testSudanUTMZone35N() {
    testToWGS84AndBack(UTM.getSudanUTMZone35N());
  }

  @Test
  public void testSudanUTMZone36N() {
    testToWGS84AndBack(UTM.getSudanUTMZone36N());
  }

  @Test
  public void testTahaaUTMZone5S() {
    testToWGS84AndBack(UTM.getTahaaUTMZone5S());
  }

  @Test
  public void testTahitiUTMZone6S() {
    testToWGS84AndBack(UTM.getTahitiUTMZone6S());
  }

  @Test
  public void testTananarive1925UTMZone38S() {
    testToWGS84AndBack(UTM.getTananarive1925UTMZone38S());
  }

  @Test
  public void testTananarive1925UTMZone39S() {
    testToWGS84AndBack(UTM.getTananarive1925UTMZone39S());
  }

  @Test
  public void testTeteUTMZone36S() {
    testToWGS84AndBack(UTM.getTeteUTMZone36S());
  }

  @Test
  public void testTeteUTMZone37S() {
    testToWGS84AndBack(UTM.getTeteUTMZone37S());
  }

  @Test
  public void testTimbalai1948UTMZone49N() {
    testToWGS84AndBack(UTM.getTimbalai1948UTMZone49N());
  }

  @Test
  public void testTimbalai1948UTMZone50N() {
    testToWGS84AndBack(UTM.getTimbalai1948UTMZone50N());
  }

  @Test
  public void testTokyoUTMZone51N() {
    testToWGS84AndBack(UTM.getTokyoUTMZone51N());
  }

  @Test
  public void testTokyoUTMZone52N() {
    testToWGS84AndBack(UTM.getTokyoUTMZone52N());
  }

  @Test
  public void testTokyoUTMZone53N() {
    testToWGS84AndBack(UTM.getTokyoUTMZone53N());
  }

  @Test
  public void testTokyoUTMZone54N() {
    testToWGS84AndBack(UTM.getTokyoUTMZone54N());
  }

  @Test
  public void testTokyoUTMZone55N() {
    testToWGS84AndBack(UTM.getTokyoUTMZone55N());
  }

  @Test
  public void testTokyoUTMZone56N() {
    testToWGS84AndBack(UTM.getTokyoUTMZone56N());
  }

  @Test
  public void testTrucialCoast1948UTMZone39N() {
    testToWGS84AndBack(UTM.getTrucialCoast1948UTMZone39N());
  }

  @Test
  public void testTrucialCoast1948UTMZone40N() {
    testToWGS84AndBack(UTM.getTrucialCoast1948UTMZone40N());
  }

  @Test
  public void testYemenNGN1996UTMZone38N() {
    testToWGS84AndBack(UTM.getYemenNGN1996UTMZone38N());
  }

  @Test
  public void testYemenNGN1996UTMZone39N() {
    testToWGS84AndBack(UTM.getYemenNGN1996UTMZone39N());
  }

  @Test
  public void testYoff1972UTMZone28N() {
    testToWGS84AndBack(UTM.getYoff1972UTMZone28N());
  }

  @Test
  public void testZanderij1972UTMZone21N() {
    testToWGS84AndBack(UTM.getZanderij1972UTMZone21N());
  }

}
