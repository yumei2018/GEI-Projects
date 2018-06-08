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
package gov.ca.water.shapelite.analysis;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Segment;
import gov.ca.water.shapelite.coordinate.ClosestCoord;
import gov.ca.water.shapelite.coordinate.CoordDistance2D;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.coordinate.HasXY;
import gov.ca.water.shapelite.segment.DefaultSegment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PointThinner {

  /**
   * This function is designed to thin any kind of coordinate that has XY
   * getters and setters. Points will be sub-sampled based on an equal number of
   * segments. Each segment will use the point closest to the line to represent
   * that segment.
   *
   * @param <T> The type of coordinate going in should match the type going out
   * because it will be the same objects, just potentially fewer of them.
   * @param coords The sorted list of coordinates to be thinned. If the total
   * size is less than count, the entire list will be returned.
   * @param start The start location, even if there is no point there.
   * @param end The end location, even if there is no point there.
   * @param count The intended maximum count.
   * @return A new ArrayList with the points.
   */
  public final <T extends HasXY> List<T> thin(List<T> coords,
      HasXY start, HasXY end, int count) {
    CoordDistance2D dist = new CoordDistance2D();
    ArrayList<T> result = new ArrayList<>();

    if (count > coords.size()) {
      for (T coord : coords) {
        result.add(coord);
      }
    } else {
      // first sort the points from start to end.
      Segment seg = new DefaultSegment(start, end);
      List<PointDist<T>> distlist = new ArrayList<>();
      for (T coord : coords) {
        PointDist<T> point = new PointDist<>(coord);
        ClosestCoord cc = seg.closestPointTo(new CoordXY(coord));
        point.distance = dist.distance(start, cc.getCoord());
        distlist.add(point);
      }
      Collections.sort(distlist);

      double length = dist.distance(start, end);
      double ds = length / count;
      double cutoff = ds;
      
      double closestDist = Double.MAX_VALUE;
      PointDist<T> closest = null;
      for (PointDist<T> point : distlist) {
        T coord = point.getCoord();
        double segDist = seg.distanceTo(coord);
        if (point.getDistance() >= cutoff) {
          if (closest != null) {
            result.add(closest.coord);
            cutoff += ds;
            System.out.println("seg: " + (int)(cutoff*100/length) + ", cutoff: "
                + cutoff + ", pt: " + closest.distance);
          }
          while (point.getDistance() >= cutoff) {
            cutoff += ds;
          }
          closest = point;
          closestDist = segDist;
        } else if (segDist < closestDist) {
          closestDist = segDist;
          closest = point;
        }
      }
    }
    return result;
  }

  /**
   * An internal class for tracking the distance on a coordinate.
   *
   * @param <T>
   */
  private class PointDist<T extends HasXY> implements Comparable<PointDist<T>> {

    /**
     * The Coordinate.
     */
    private T coord;

    /**
     * The distance from the start.
     */
    private double distance;

    /**
     * Creates a new instance of a PointDist class from the specified
     * coordinate.
     *
     * @param coord The coordinate to use for this point.
     */
    public PointDist(T coord) {
      this.coord = coord;
    }

    /**
     * Gets the coordinate.
     *
     * @return the coord
     */
    public T getCoord() {
      return coord;
    }

    /**
     * Sets the coordinate.
     *
     * @param coord the coord to set
     */
    public void setCoord(T coord) {
      this.coord = coord;
    }

    /**
     * Gets the distance.
     *
     * @return the distance
     */
    public double getDistance() {
      return distance;
    }

    /**
     * Sets the distance.
     *
     * @param distance the distance to set
     */
    public void setDistance(double distance) {
      this.distance = distance;
    }

    /**
     * Compares this point dist to the other based on the distance property.
     *
     * @param o The other PointDist object.
     * @return integer, 1 if this distance is greater, -1 if this distance is
     * less and 0 if they are the same.
     */
    @Override
    public int compareTo(PointDist<T> o) {
      return Double.compare(distance, o.distance);
    }

  }

}
