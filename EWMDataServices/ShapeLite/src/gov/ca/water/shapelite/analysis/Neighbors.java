/*
 * The MIT License
 *
 * Copyright 2012 hdunsford.
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

/**
 * This class is useful for cycling through neighbors in N dimensions in a for
 * loop. In a 1D function, there are two neighbors, one smaller, one greater.
 * These can be stored as x-1, x, x+1. (Including staying in place) The maximum
 * number of total relative directions is 3. In a 2D function, the relative x,y
 * positions can be encoded by also including y-1, y, y+1. The total number of
 * neighbor directions to cycle is 9.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class Neighbors {

  /**
   * Hidden constructor.
   */
  private Neighbors() {

  }

  /**
   * Since each dimension can be -1, 0, and 1, the total possible number of
   * states is 3.
   */
  public static final int NUM_STATES = 3;

  /**
   * Gets the direction vector indexed by "address". A for loop cycles through
   * from 0 to getMaximumAddress(dimension) and getDirection returns the
   * associated vector for that direction. This builds a multi-dimensional
   * representation of a direction vector.
   *
   * @param address The integer address.
   * @param numDimensions the integer number of dimensions.
   * @return the array of integers making up the direction vector.
   */
  public static int[] getDirection(int address, int numDimensions) {
    int value = address;
    int[] result = new int[numDimensions];
    for (int d = 0; d < numDimensions; d++) {
      int current = value % NUM_STATES;
      // if current = 0, we don't need to do anything.
      if (current == 1) {
        result[d] = -1;
      }
      if (current == 2) {
        result[d] = 1;
      }
      value = value / NUM_STATES;
    }
    return result;
  }

  /**
   * Gets the integer index for a specified direction.
   *
   * @param direction - a unit vector direction, one dimension for each
   * parameter. Each value can be -1, 0 or 1. An address of 0 is the current
   * position.
   * @return In 2D space, there can be 8 neighbors, plus staying still. So the
   * resulting integer can be a value from 0 to 8. A value from 0 to
   * (3^dimension)-1.
   */
  public static int getAddress(int[] direction) {
    int value = 0;
    for (int i = 0; i < direction.length; i++) {

      if (direction[i] < 0) {
        value += (int) Math.pow(NUM_STATES, i);
      } else if (direction[i] > 0) {
        value += (int) (2 * Math.pow(NUM_STATES, i));
      }
    }
    return value;
  }

  /**
   * If the neighbors are indexed, this finds the maximum value that should be
   * cycled over in a for loop to test all the possible directions. The maximum
   * variability accounts for -1, 0, or 1 for each parameter, so
   * 3^numParameters.
   *
   * @param numParameters The number of parameters (dimensions) for the
   * neighbors function.
   * @return The
   */
  public static int getMaximumAddress(int numParameters) {
    return (int) (Math.pow(NUM_STATES, numParameters));
  }

}
