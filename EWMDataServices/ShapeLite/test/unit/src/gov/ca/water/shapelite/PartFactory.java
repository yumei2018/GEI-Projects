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
package gov.ca.water.shapelite;

import gov.ca.water.shapelite.coordinate.CoordM;
import gov.ca.water.shapelite.coordinate.CoordZ;
import java.util.Random;

/**
 * A Class for creating random parts for testing.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class PartFactory {

  /**
   * The minimum coordinate for WGS84 with -180 longitude, -90 latitude and 0
   * elevation, and 0 M.
   */
  private static final CoordZ MIN_WGS84 = new CoordZ(-180, -90, 0, 0);

  /**
   * The maximum ground coordinate for WGS84 with 180 longitude, 90 latitude and
   * 30,000 feet elevation (slightly taller than everest), and 1 M.
   */
  private static final CoordZ MAX_WGS84 = new CoordZ(180, 90, 30000, 1);

  /**
   * The Random object for randomizing values.
   */
  private Random random;

  /**
   * The envelope that defines the bounds.
   */
  private Envelope bounds;

  /**
   * Creates a new instance of a PartFactory.
   */
  PartFactory() {
    random = new Random();
    setBoundsWGS84();
  }

  /**
   * Creates a new instance of a Part Factory.
   *
   * @param seed The Random seed to use to initialize the Random.
   */
  PartFactory(int seed) {
    random = new Random(seed);
    setBoundsWGS84();
  }

  /**
   * Sets the bounds for the WGS84 coordinate system.
   */
  private void setBoundsWGS84() {
    CoordZ min = MIN_WGS84;
    CoordZ max = MAX_WGS84;
    bounds = new DefaultEnvelope(min, max);
  }

  /**
   * The envelope that defines the bounds.
   *
   * @return the bounds
   */
  public Envelope getBounds() {
    return bounds;
  }

  /**
   * The envelope that defines the bounds.
   *
   * @param bounds the bounds to set
   */
  public void setBounds(Envelope bounds) {
    this.bounds = bounds;
  }

  /**
   * Gets the Random object for randomizing values.
   *
   * @return the random
   */
  public Random getRandom() {
    return random;
  }

  /**
   * Sets the Random object for randomizing values.
   *
   * @param random the random to set
   */
  public void setRandom(Random random) {
    this.random = random;
  }

  /**
   * Generates new part with the specified number of coordinates. The part will
   * never be closed. The part will never have M or Z ordinates.
   *
   * @param size The integer number of coordinates.
   * @return The newly created part.
   */
  public Part randomPart(int size) {
    Part result = new Part();
    for (int i = 0; i < size; i++) {
      double x = getRandomX();
      double y = getRandomY();
      double m = getRandomM();
      CoordM c = new CoordM(x, y, m);
      result.getCoordinates().add(c);
    }
    return result;
  }

  /**
   * Generates new part with the specified number of coordinates. The part will
   * never be closed. The part will never have M or Z ordinates.
   *
   * @param size The integer number of coordinates.
   * @return The newly created part.
   */
  public Part randomPartM(int size) {
    Part result = new Part();

    for (int i = 0; i < size; i++) {
      double x = getRandomX();
      double y = getRandomY();
      double m = getRandomM();
      CoordM c = new CoordM(x, y, m);
      result.getCoordinates().add(c);
    }
    return result;
  }

  /**
   * Generates new part with the specified number of coordinates. The part will
   * never be closed. The part will never have M or Z ordinates.
   *
   * @param size The integer number of coordinates.
   * @return The newly created part.
   */
  public Part randomPartZ(int size) {
    Part result = new Part();
    for (int i = 0; i < size; i++) {
      double x = getRandomX();
      double y = getRandomY();
      double m = getRandomM();
      double z = getRandomZ();
      CoordZ c = new CoordZ(x, y, z, m);
      result.getCoordinates().add(c);
    }
    return result;
  }

  /**
   * Gets a random double bound to the range of X.
   *
   * @return The double random.
   */
  public double getRandomX() {
    double minX = getMinX();
    double maxX = getMaxX();
    double dX = maxX - minX;
    return minX + dX * random.nextDouble();
  }

  /**
   * Gets a random double bound to the range of Y.
   *
   * @return The double random.
   */
  public double getRandomY() {
    double minY = getMinY();
    double maxY = getMaxY();
    double dY = maxY - minY;
    return minY + dY * random.nextDouble();
  }

  /**
   * Gets a random double bound to the range of Z.
   *
   * @return The double random.
   */
  public double getRandomZ() {
    double minZ = getMinZ();
    double maxZ = getMaxZ();
    double dZ = maxZ - minZ;
    return minZ + dZ * random.nextDouble();
  }

  /**
   * Gets a random double bound to the range of M.
   *
   * @return The double random.
   */
  public double getRandomM() {
    double minM = getMinM();
    double maxM = getMaxM();
    double dM = maxM - minM;
    return minM + dM * random.nextDouble();
  }

  /**
   * Gets the minimum X from the bounds, or 0 if the bounds is null.
   *
   * @return The minimum X.
   */
  public double getMinX() {
    if (this.bounds != null) {
      return bounds.getMin().getX();
    }
    return 0;
  }

  /**
   * Gets the maximum X from the bounds, or 1 if the bounds is null.
   *
   * @return The maximum X.
   */
  public double getMaxX() {
    if (this.bounds != null) {
      return bounds.getMax().getX();
    }
    return 1;
  }

  /**
   * Gets the minimum Y from the bounds, or 0 if the bounds is null.
   *
   * @return The minimum Y.
   */
  public double getMinY() {
    if (this.bounds != null) {
      return bounds.getMin().getY();
    }
    return 0;
  }

  /**
   * Gets the maximum Y from the bounds, or 1 if the bounds is null.
   *
   * @return The maximum Y.
   */
  public double getMaxY() {
    if (this.bounds != null) {
      return bounds.getMax().getY();
    }
    return 0;
  }

  /**
   * Gets the minimum M from the bounds, or 0 if the bounds is null.
   *
   * @return The minimum M.
   */
  public double getMinM() {
    if (this.bounds != null && bounds.hasM()) {
      return bounds.getMin().getM();
    }
    return 0;
  }

  /**
   * Gets the maximum Y from the bounds, or 1 if the bounds is null.
   *
   * @return The maximum Y.
   */
  public double getMaxM() {
    if (this.bounds != null) {
      return bounds.getMax().getM();
    }
    return 0;
  }

  /**
   * Gets the minimum Y from the bounds, or 0 if the bounds is null.
   *
   * @return The minimum Y.
   */
  public double getMinZ() {
    if (this.bounds != null) {
      return bounds.getMin().getZ();
    }
    return 0;
  }

  /**
   * Gets the maximum Y from the bounds, or 1 if the bounds is null.
   *
   * @return The maximum Y.
   */
  public double getMaxZ() {
    if (this.bounds != null) {
      return bounds.getMax().getY();
    }
    return 0;
  }

}
