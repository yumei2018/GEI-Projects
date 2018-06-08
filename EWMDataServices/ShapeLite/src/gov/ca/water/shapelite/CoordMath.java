/*
 * The MIT License
 *
 * Copyright 2016 rmarquez.
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

/**
 *
 * @author rmarquez
 */
public class CoordMath {

  private CoordMath() {
  }
  public static Double dotXY(Coord c1, Coord c2) {
    Double dot = c1.getX()*c2.getX() + c1.getY()*c2.getY();
    return dot;
  }
  public static CoordXY multiplyXY(Coord coord, Double multiplier) {
    CoordXY b = new CoordXY(coord.copy());
    b.setX(b.getX() * multiplier);
    b.setY(b.getY() * multiplier);
    return b;
  }


  public static CoordXY subtract2D(@NonNull Coord c2, @NonNull Coord c1) {
    CoordXY result = new CoordXY(c2.getX() - c1.getX(), c2.getY() - c1.getY());
    return result;
  }

  public static CoordXY add2D(@NonNull Coord coord1, @NonNull Coord coord2) {
    CoordXY result = new CoordXY(coord1.getX() + coord2.getX(), coord1.getY() + coord2.getY());
    return result;
  }

  public static CoordXY normalizeXY(Coord coord) {
    double magnitude = Math.sqrt(Math.pow(coord.getX(), 2) + Math.pow(coord.getY(), 2));
    CoordXY b = multiplyXY(coord, 1.0 / magnitude);
    return b;
  }

  public static CoordXY normalToXY(Coord coord) {
    CoordXY result = new CoordXY(coord.getY(), -coord.getX());
    return result;
  }

  public static Optional<CoordXY> asOptional(CoordXY coord){
    return new Optional<>(coord);
  }
}
