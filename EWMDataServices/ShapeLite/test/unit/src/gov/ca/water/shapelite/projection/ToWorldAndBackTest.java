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
import gov.ca.water.shapelite.coordinate.CoordZ;
import gov.ca.water.shapelite.projection.categories.CoordinateSystemCategory;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ToWorldAndBackTest {

  public ToWorldAndBackTest() {

  }

  @Test
  public void test(){
    
  }
  
  /**
   * Generally every projection has a 0,0 that can be projected to a point in
   * WGS84. Then if we project that point back, we should get something close to
   * 0, 0 again. This is not a complete test, however, because 0 is a degenerate
   * case for many calculations, so you may get a pass, when a coordinate of 1,1
   * might not pass.
   *
   * @throws gov.ca.water.shapelite.projection.ProjectionException
   */
  public void zeroGeographicTests() throws ProjectionException {
    ProjectionInfo latLong = Projections.getGeographic().getWorld().getWGS1984();
    for (CoordinateSystemCategory category : Projections.getGeographic().getAll()) {
      List<ProjectionInfo> coordinateSytems = category.getAll();
      for (ProjectionInfo source : coordinateSytems) {
        try {

          Coord startCoord = new CoordZ(0, 0, 0);
          Coord geoCoord = Reproject.reprojectCoordinate(startCoord, source, latLong);
          Coord endCoord = Reproject.reprojectCoordinate(geoCoord, latLong, source);
          if (Math.abs(startCoord.getX() - endCoord.getX()) > 1 || Math.abs(startCoord.getY() - endCoord.getY()) > 1) {
            System.out.println(source.getName() + " failed, start: 0,0 -> geo: "
                + geoCoord.getX() + ", " + geoCoord.getY() + " -> end: " + endCoord.getX()
                + ", " + endCoord.getY());
          }
        } catch (ProjectionException ex) {
          System.out.println(source.getName() + " failed: " + ex.getMessage());
        }
      }
    }
  }

  public void zeroProjectedTests() throws ProjectionException {
    ProjectionInfo latLong = Projections.getGeographic().getWorld().getWGS1984();
    ProjectionInfo previous = null;
    for (CoordinateSystemCategory category : Projections.getProjected().getAll()) {
      List<ProjectionInfo> coordinateSytems = category.getAll();
      for (ProjectionInfo source : coordinateSytems) {
        try {

          Coord startCoord = new CoordZ(0, 0, 0);
          Coord geoCoord = Reproject.reprojectCoordinate(startCoord, source, latLong);
          Coord endCoord = Reproject.reprojectCoordinate(geoCoord, latLong, source);
          if (Math.abs(startCoord.getX() - endCoord.getX()) > 1 || Math.abs(startCoord.getY() - endCoord.getY()) > 1) {
            System.out.println(source.getName() + " failed, start: 0,0 -> geo: "
                + geoCoord.getX() + ", " + geoCoord.getY() + " -> end: " + endCoord.getX()
                + ", " + endCoord.getY());
          }
        } catch (ProjectionException ex) {
          System.out.println(source.getName() + " failed: " + ex.getMessage());
        }
        previous = source;
      }
    }
  }


  public void eurpeTest() {
    Projections.getProjected().getEurope();
  }


  public void oneGeographicTests() throws ProjectionException {
    ProjectionInfo latLong = Projections.getGeographic().getWorld().getWGS1984();
    for (CoordinateSystemCategory category : Projections.getGeographic().getAll()) {
      List<ProjectionInfo> coordinateSytems = category.getAll();
      for (ProjectionInfo source : coordinateSytems) {
        try {

          Coord startCoord = new CoordZ(1, 1, 0);
          Coord geoCoord = Reproject.reprojectCoordinate(startCoord, source, latLong);
          Coord endCoord = Reproject.reprojectCoordinate(geoCoord, latLong, source);
          if (Math.abs(startCoord.getX() - endCoord.getX()) > 1 || Math.abs(startCoord.getY() - endCoord.getY()) > 1) {
            System.out.println(source.getName() + " failed, start: 0,0 -> geo: "
                + geoCoord.getX() + ", " + geoCoord.getY() + " -> end: " + endCoord.getX()
                + ", " + endCoord.getY());
          }
        } catch (ProjectionException ex) {
          System.out.println(source.getName() + " failed: " + ex.getMessage());
        }
      }
    }
  }

  public void oneProjectedTests() throws ProjectionException {
    ProjectionInfo latLong = Projections.getGeographic().getWorld().getWGS1984();
    for (CoordinateSystemCategory category : Projections.getProjected().getAll()) {
      List<ProjectionInfo> coordinateSytems = category.getAll();
      for (ProjectionInfo source : coordinateSytems) {
        try {

          Coord startCoord = new CoordZ(1, 1, 0);
          Coord geoCoord = Reproject.reprojectCoordinate(startCoord, source, latLong);
          Coord endCoord = Reproject.reprojectCoordinate(geoCoord, latLong, source);
          if (Math.abs(startCoord.getX() - endCoord.getX()) > 1 || Math.abs(startCoord.getY() - endCoord.getY()) > 1) {
            System.out.println(source.getName() + " failed, start: 0,0 -> geo: "
                + geoCoord.getX() + ", " + geoCoord.getY() + " -> end: " + endCoord.getX()
                + ", " + endCoord.getY());
          }
        } catch (ProjectionException ex) {
          System.out.println(source.getName() + " failed: " + ex.getMessage());
        }
      }
    }
  }

}
