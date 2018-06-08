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
package gov.ca.water.shapelite.projection;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class DistanceUtilsTest {

  public DistanceUtilsTest() {
  }

  /**
   * Test of feetToProjectedDistanceY method, of class DistanceUtils.
   */
  @Test
  public void feetToWGS84() {
    System.out.println("feetToWGS84");
    double feetUS = 200;
    ProjectionInfo proj = Projections.getWGS84();
    double expResult = 200;
    double resultDD = DistanceUtils.feetToProjectedDistanceY(feetUS, proj);
    double resultFeet = DistanceUtils.projectedDistanceToFeetY(resultDD, proj);

    assertEquals(expResult, resultFeet, 0.000001);
  }

  /**
   * Test of projectedDistanceToFeetY method, of class DistanceUtils.
   */
  @Test
  public void feetToUTMFoot() {
    System.out.println("feetToUTMFoot");
    double feetUS = 200;
    ProjectionInfo proj = Projections.getNad83UTMZone10Foot();
    double expResult = 200;
    double resultPrj = DistanceUtils.feetToProjectedDistanceY(feetUS, proj);
    double resultFeet = DistanceUtils.projectedDistanceToFeetY(resultPrj, proj);
    assertEquals(expResult, resultFeet, 0.000001);
  }

  /**
   * Test of projectedDistanceToFeetY method, of class DistanceUtils.
   */
  @Test
  public void feetToUTM() {
    System.out.println("feetToUTM");
    double feetUS = 200;
    ProjectionInfo proj = Projections.getProjected().getUtmNad1983()
        .getNAD1983UTMZone10N();
    double expResult = 60.9601219264374;
    double resultPrj = DistanceUtils.feetToProjectedDistanceY(feetUS, proj);
    assertEquals(expResult, resultPrj, 0.000001);
    expResult = 200;
    double resultFeet = DistanceUtils.projectedDistanceToFeetY(resultPrj, proj);
    assertEquals(expResult, resultFeet, 0.000001);
  }

  /**
   * Test of feetToProjectedDistanceY method, of class DistanceUtils.
   */
  @Test
  public void feetToWGS84Getter() {
    System.out.println("feetToWGS84");
    double feetUS = 200;
    ProjectionInfo proj = Projections.getWGS84();

    double expResult = 5.476115875533363E-4;
    double resultDD = proj.feetToProj(feetUS);
    assertEquals(resultDD, expResult, 0.000001);
    double resultFeet = proj.projToFeet(resultDD);
    expResult = 200;
    assertEquals(expResult, resultFeet, 0.000001);
  }

  /**
   * Test of feetToProjectedDistanceY method, of class DistanceUtils.
   */
  @Test
  public void feetToUTMGetter() {
    System.out.println("feetToUTM");
    double feetUS = 200;
    ProjectionInfo proj = Projections.getProjected().getUtmNad1983()
        .getNAD1983UTMZone10N();
    double expResult = 60.9601219264374;
    double resultDD = proj.feetToProj(feetUS);
    assertEquals(resultDD, expResult, 0.000001);
    double resultFeet = proj.projToFeet(resultDD);
    expResult = 200;
    assertEquals(expResult, resultFeet, 0.000001);
  }

}
