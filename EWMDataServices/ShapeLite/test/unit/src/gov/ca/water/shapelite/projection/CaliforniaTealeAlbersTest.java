/*
 * The MIT License
 *
 * Copyright 2014 hdunsford.
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

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.FileHelper;
import gov.ca.water.shapelite.coordinate.CoordXY;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class CaliforniaTealeAlbersTest {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    //</editor-fold>
  /**
   * Creates a new instance of the CaliforniaTealeAlbersTest class.
   */
  public CaliforniaTealeAlbersTest() {

  }

    //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   *
   * @throws java.io.IOException
   */
  @Test
  public void testReadESRI() throws IOException {
    String text = Projections.getProjected().getStateSystems()
        .getNAD_1927_California_Teale_Albers().toEsriString();
    ProjectionInfo proj = ProjectionInfo.fromEsriString(text);
    String result = proj.getName();
    String expResult = "NAD_1927_California_Teale_Albers";
    assertEquals(expResult, result);
  }



  @Test
  public void selfConsistent() throws ProjectionException {
    ProjectionInfo wgs84 = Projections.getGeographic().getWorld().getWGS1984();
    ProjectionInfo teal = Projections.getProjected().getStateSystems().getNAD_1983_California_Teale_Albers();
    Coord c = new CoordXY(1, 1);
    Coord world = Reproject.reprojectCoordinate(c, teal, wgs84);
    Coord result = Reproject.reprojectCoordinate(world, wgs84, teal);
    if (Math.abs(result.getX() - c.getX()) > .01 || Math.abs(result.getY() - c.getY()) > .01) {
      fail("Reproject to world and back did not produce the same point.");
    }
  }


  private String getName(String filename) {
    String name = FileHelper.getBaseName(filename);
    name = name.replace(" ", "_");
    name = name.replace("(", "");
    name = name.replace(")", "");
    return name;
  }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Properties">
    //</editor-fold>
}
