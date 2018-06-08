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
package gov.ca.water.shapelite;

import gov.ca.water.shapelite.coordinate.CoordXY;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to create quarter quarter polygons from a PLSS section
 * polygon.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class Section {

  /**
   * The number of quarter quarters in the X and Y directions.
   */
  private static final int QQ_SPAN = 4;

  /**
   * The length of the characters in the name.
   */
  private static final int QQ_LENGTH = 4;

  /**
   * The jagged array of quarter names.
   */
  private static final String[][] QQ_NAMES = {
    {"NWNW", "NENW", "NWNE", "NENE"},
    {"SWNW", "SENW", "SWNE", "SENE"},
    {"NWSW", "NESW", "NWSE", "NESE"},
    {"SWSW", "SESW", "SWSE", "SESE"}
  };

  /**
   * The jagged array of quarter names.
   */
  private static final String[][] QUARTER_NAMES = {
    {"NW", "NW", "NE", "NE"},
    {"NW", "NW", "NE", "NE"},
    {"SW", "SW", "SE", "SE"},
    {"SW", "SW", "SE", "SE"}
  };

  /**
   * Northwest.
   */
  private Coord nw;
  /**
   * Northeast.
   */
  private Coord ne;
  /**
   * Southwest.
   */
  private Coord sw;
  /**
   * Southeast.
   */
  private Coord se;

  /**
   * Top.
   */
  private Part top;

  /**
   * Left.
   */
  private Part left;

  /**
   * Right.
   */
  private Part right;

  /**
   * Bottom.
   */
  private Part bottom;

  /**
   * The bounds of the section.
   */
  private final Feature bounds;

  /**
   * Creates a new section from the specified boundary polygon.
   *
   * @param section The Feature defining the section polygon and attributes.
   */
  public Section(Feature section) {
    this.bounds = section;
  }

  /**
   * Uses affine properties to generate quarter corners for a section polygon.
   *
   * @return A list of features representing the four quarter corners.
   */
  public final List<Feature> createQuarterQuarters() {

    List<Feature> result = new ArrayList<>();
    for (int y = 0; y < QQ_SPAN; y++) {
      for (int x = 0; x < QQ_SPAN; x++) {
        Shape quarterQuarter = new Shape(ShapeType.Polygon);
        Part part = new Part();
        part.setClosed(true);
        Coord c1 = getMeshCoord(x, y);
        part.getCoordinates().add(c1);
        Coord c2 = getMeshCoord(x + 1, y);
        part.getCoordinates().add(c2);
        Coord c3 = getMeshCoord(x + 1, y + 1);
        part.getCoordinates().add(c3);
        Coord c4 = getMeshCoord(x, y + 1);
        part.getCoordinates().add(c4);
        quarterQuarter.getParts().add(part);
        quarterQuarter.calculateBounds();
        Feature qqFeature = new Feature(quarterQuarter);
        qqFeature.copyAttributes(bounds.getAttributes());
        qqFeature.getAttributes().put("QQ", QQ_NAMES[y][x]);
        qqFeature.getAttributes().put("Quarter", QUARTER_NAMES[y][x]);
        result.add(qqFeature);
      }
    }
    return result;

  }

  /**
   * Gets an interpolated coordinate in the Section.
   *
   * @param x An integer value from 0 to 4, where 0 is the leftPoint, and 4 is
   * the rightPoint.
   * @param y An integer value from 0 to 4 where 0 is the topPoint and 4 is the
   * bottomPoint.
   * @return A coordinate positioned on a mesh.
   */
  public final Coord getMeshCoord(int x, int y) {
    Coord leftPoint = getLeft(y);
    Coord rightPoint = getRight(y);
    Coord topPoint = getTop(x);
    Coord bottomPoint = getBottom(x);

    double xp = leftPoint.getX() + x * (rightPoint.getX() - leftPoint.getX()) / QQ_SPAN;
    double yp = topPoint.getY() + y * (bottomPoint.getY() - topPoint.getY()) / QQ_SPAN;
    return new CoordXY(xp, yp);
  }

  /**
   * Gets a coordinate along the topPoint line where 0 is the western corner,
   * and 4 is the eastern corner.
   *
   * @param x The integer index along the line.
   * @return The Coordinate in the line at the specified distance.
   */
  public final Coord getTop(int x) {
    if (x == 0) {
      return getTop().start().get();
    }
    if (x == QQ_SPAN) {
      return getTop().getLast().get();
    }
    double length = getTop().length();
    double fraction = (double) x / (double) (QQ_SPAN);
    double distance = length * fraction;
    Optional<Coord> maybeCoord = getTop().getCoordinateAtDistance(distance);
    return maybeCoord.get();
  }

  /**
   * Gets a coordinate along the topPoint line where 0 is the western corner,
   * and 4 is the eastern corner.
   *
   * @param x The integer index along the line.
   * @return The Coordinate in the line at the specified distance.
   */
  public final Coord getBottom(int x) {
    if (x == 0) {
      return getBottom().start().get();
    }
    if (x == QQ_SPAN) {
      return getBottom().getLast().get();
    }
    double length = getBottom().length();
    double fraction = (double) x / (double) (QQ_SPAN);
    double distance = length * fraction;
    Optional<Coord> maybeCoord = getBottom().getCoordinateAtDistance(distance);
    return maybeCoord.get();
  }

  /**
   * Gets a coordinate along the topPoint line where 0 is the western corner,
   * and 4 is the eastern corner.
   *
   * @param y The integer index along the line.
   * @return The Coordinate in the line at the specified distance.
   */
  public final Coord getLeft(int y) {
    if (y == 0) {
      return getLeft().start().get();
    }
    if (y == QQ_SPAN) {
      return getLeft().getLast().get();
    }
    double length = getLeft().length();
    double fraction = (double) y / (double) (QQ_SPAN);
    double distance = length * fraction;
    Optional<Coord> maybeCoord = getLeft().getCoordinateAtDistance(distance);
    return maybeCoord.get();
  }

  /**
   * Gets a coordinate along the topPoint line where 0 is the western corner,
   * and 4 is the eastern corner.
   *
   * @param y The integer index along the line.
   * @return The Coordinate in the line at the specified distance.
   */
  public final Coord getRight(int y) {
    if (y == 0) {
      return getRight().start().get();
    }
    if (y == QQ_SPAN) {
      return getRight().getLast().get();
    }
    double length = getRight().length();
    double fraction = (double) y / (double) (QQ_SPAN);
    double distance = length * fraction;
    try {
      Optional<Coord> maybeCoord = getRight().getCoordinateAtDistance(distance);

      return maybeCoord.get();
    } catch (Exception ex) {
      boolean stop = true;
    }
    return null;
  }

  /**
   * Gets a part for the topPoint of the line.
   *
   * @return A Part with the coordinates along the topPoint line.
   */
  public final Part getTop() {
    if (top == null) {
      top = getLine(getNorthWestCorner(), getNorthEastCorner());
      if (top.start().get().getX() > top.end().get().getX()) {
        top.reverse();
      }
    }
    return top;
  }

  /**
   * Gets a part for the bottomPoint of the line.
   *
   * @return A Part with the coordinates along the bottomPoint line.
   */
  public final Part getBottom() {
    if (bottom == null) {
      bottom = getLine(getSouthWestCorner(), getSouthEastCorner());
      if (bottom.start().get().getX() > bottom.end().get().getX()) {
        bottom.reverse();
      }
    }
    return bottom;
  }

  /**
   * Gets a part for the leftPoint of the line.
   *
   * @return A Part with the coordinates along the leftPoint line.
   */
  public final Part getLeft() {
    if (left == null) {
      left = getLine(getNorthWestCorner(), getSouthWestCorner());
      if (left.start().get().getY() < left.end().get().getY()) {
        left.reverse();
      }
    }
    return left;
  }

  /**
   * Gets a part for the rightPoint of the line.
   *
   * @return A Part with the coordinates along the rightPoint line.
   */
  public final Part getRight() {
    if (right == null) {
      right = getLine(getNorthEastCorner(), getSouthEastCorner());
      if (right.start().get().getY() < right.end().get().getY()) {
        right.reverse();
      }
    }
    return right;
  }

  /**
   * Gets a part for the topPoint of the line.
   *
   * @param start The coordinate that starts the line.
   * @param end The coordinate that ends the line.
   * @return A part defining the line.
   */
  public final Part getLine(Coord start, Coord end) {
    Part a = new Part();
    Part b = new Part();
    Part part = bounds.getShape().getParts().get(0);
    if (bounds.getShape().getParts().size() > 1) {
      boolean stop = true;
    }
    int iStart = part.getCoordinates().indexOf(start);
    int iEnd = part.getCoordinates().indexOf(end);
    if (iStart < 0 || iEnd < 0) {
      boolean stop = true;
    }
    if (iStart > part.getCoordinates().size() || iEnd > part.getCoordinates().size()) {
      boolean stop = true;
    }

    if (iStart < iEnd) {
      for (int i = iStart; i <= iEnd; i++) {
        a.getCoordinates().add(part.getCoordinates().get(i));
      }
      for (int i = iEnd; i < part.getCoordinates().size(); i++) {
        b.getCoordinates().add(part.getCoordinates().get(i));
      }
      for (int i = 0; i <= iStart; i++) {
        b.getCoordinates().add(part.getCoordinates().get(i));
      }
    } else {
      for (int i = iEnd; i <= iStart; i++) {
        b.getCoordinates().add(part.getCoordinates().get(i));
      }
      for (int i = iStart; i < part.getCoordinates().size(); i++) {
        a.getCoordinates().add(part.getCoordinates().get(i));
      }
      for (int i = 0; i <= iEnd; i++) {
        a.getCoordinates().add(part.getCoordinates().get(i));
      }
    }
    if (a.length() < b.length()) {
      return a;
    } else {
      return b;
    }

  }

  /**
   * Gets the field definitions for attributes defining the quarter quarter.
   *
   * @return The list of Fields to add.
   */
  public static List<Field> getFields() {
    List<Field> result = new ArrayList<>();
    result.add(new Field("QQ", FieldType.Character, QQ_LENGTH));
    result.add(new Field("Quarter", FieldType.Character, 2));
    return result;
  }

  /**
   * Gets the NorthWest Corner coordinate of this township range square.
   *
   * @param target The target.
   * @return The Target Corner.
   */
  public final Coord getClosest(Coord target) {

    double minDist = Double.MAX_VALUE;

    Coord result = null;
    for (Coord coord : bounds.getShape().getCoordinates()) {
      if (result == null) {
        result = coord;
        continue;
      }
      double dist = coord.distance(target);
      if (dist < minDist) {
        result = coord;
        minDist = dist;
      }
    }
    return result;

  }

  /**
   * Gets the NorthWest Corner coordinate of this township range square.
   *
   * @return The NorthWest Corner.
   */
  public final Coord getNorthWestCorner() {
    if (nw == null) {
      Envelope env = bounds.getShape().getEnvelope();
      Coord target = new CoordXY(env.getMin().getX(), env.getMax().getY());
      nw = getClosest(target);
    }
    return nw;
  }

  /**
   * Gets the NorthEast Corner coordinate of this township range square.
   *
   * @return The NorthEast Corner.
   */
  public final Coord getNorthEastCorner() {
    if (ne == null) {
      Envelope env = bounds.getShape().getEnvelope();
      Coord target = new CoordXY(env.getMax().getX(), env.getMax().getY());
      ne = getClosest(target);
    }
    return ne;
  }

  /**
   * Gets the SouthWest Corner coordinate of this township range square.
   *
   * @return The South West Corner.
   */
  public final Coord getSouthWestCorner() {
    if (sw == null) {
      Envelope env = bounds.getShape().getEnvelope();
      Coord target = new CoordXY(env.getMin().getX(), env.getMin().getY());
      sw = getClosest(target);
    }
    return sw;

  }

  /**
   * Gets the SouthEast Corner coordinate of this township range square.
   *
   * @return The SouthEastCorner.
   */
  public final Coord getSouthEastCorner() {
    if (se == null) {
      Envelope env = bounds.getShape().getEnvelope();
      Coord target = new CoordXY(env.getMax().getX(), env.getMin().getY());
      se = getClosest(target);
    }
    return se;
  }
}
