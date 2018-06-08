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
package gov.ca.water.shapelite.utils;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class was developed for translating geoJson ArrayList representations of
 * coordinates into an array formats of coordinates and back.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class ArrayUtils {

  /**
   * A private constructor for this utilities class.
   */
  private ArrayUtils() {

  }

  /**
   * Converts a three dimensional arraylist of Doubles to a three dimensional
   * array of doubles.
   *
   * @param list The two dimensional list of coordinates.
   * @return The array of double arrays.
   */
  public static double[][][][] toArray4(ArrayList<?> list) {
    double[][][][] result = new double[list.size()][][][];
    for (int i = 0; i < list.size(); i++) {
      Object item = list.get(i);
      if (item instanceof ArrayList<?>) {
        ArrayList<?> innerList = (ArrayList<?>) item;
        result[i] = toArray3(innerList);
      }
    }
    return result;
  }

  /**
   * Converts a three dimensional arraylist of Doubles to a three dimensional
   * array of doubles.
   *
   * @param list The two dimensional list of coordinates.
   * @return The array of double arrays.
   */
  public static double[][][] toArray3(ArrayList<?> list) {
    double[][][] result = new double[list.size()][][];
    for (int i = 0; i < list.size(); i++) {
      Object item = list.get(i);
      if (item instanceof ArrayList<?>) {
        ArrayList<?> innerList = (ArrayList<?>) item;
        result[i] = toArray2(innerList);
      }
    }
    return result;
  }

  /**
   * Converts an arraylist of arraylists of Doubles to a two dimensional array
   * of coordinates.
   *
   * @param list The two dimensional list of coordinates.
   * @return The array of double arrays.
   */
  public static double[][] toArray2(ArrayList<?> list) {
    double[][] result = new double[list.size()][];
    for (int i = 0; i < list.size(); i++) {
      Object item = list.get(i);
      if (item instanceof ArrayList<?>) {
        ArrayList<?> innerList = (ArrayList<?>) item;
        result[i] = toArray(innerList);
      }
    }
    return result;
  }

  /**
   * Converts an arraylist of doubles to an array of doubles.
   *
   * @param items the items to convert.
   * @return The double array.
   */
  public static double[] toArray(ArrayList<?> items) {
    double[] result = new double[items.size()];
    for (int i = 0; i < items.size(); i++) {
      Object item = items.get(i);
      if (item instanceof Double) {
        Double val = (Double) item;
        result[i] = val;
      }
    }
    return result;
  }

  /**
   * Gets a nested ArrayList representing the jagged four dimensional array of
   * values.
   *
   * @param values The four dimensional double array of values.
   * @return The list representation of the values.
   */
  public static ArrayList<ArrayList<ArrayList<ArrayList<Double>>>> toList(
      double[][][][] values) {
    ArrayList<ArrayList<ArrayList<ArrayList<Double>>>> result = new ArrayList<>();
    for (double[][][] array : values) {
      result.add(toList(array));
    }
    return result;
  }

  /**
   * Gets a nested ArrayList representing the jagged three dimensional array of
   * values.
   *
   * @param values The three dimensional double array of values.
   * @return The list representation of the values.
   */
  public static ArrayList<ArrayList<ArrayList<Double>>> toList(double[][][] values) {
    ArrayList<ArrayList<ArrayList<Double>>> result = new ArrayList<>();
    for (double[][] array : values) {
      result.add(toList(array));
    }
    return result;
  }

  /**
   * Gets a nested ArrayList representing the jagged two dimensional array of
   * values.
   *
   * @param values The two dimensional double array of values.
   * @return The list representation of the values.
   */
  public static ArrayList<ArrayList<Double>> toList(double[][] values) {
    ArrayList<ArrayList<Double>> result = new ArrayList<>();
    for (double[] array : values) {
      result.add(toList(array));
    }
    return result;
  }

  /**
   * Gets the ArrayList from the values.
   *
   * @param values The double array of values.
   * @return The ArrayList of double values.
   */
  public static ArrayList<Double> toList(double[] values) {
    ArrayList<Double> result = new ArrayList<>();
    for (double val : values) {
      result.add(val);
    }
    return result;
  }

  /**
   * Copies the original array of doubles. Assumes that each layer is either an
   * array list or doubles. No other types are currently supported for this.
   *
   * @param values the values to copy.
   * @return a new list of values.
   */
  public static ArrayList<?> deepCopy(ArrayList<?> values) {
    ArrayList<?> result = new ArrayList<>();
    if (values == null || values.isEmpty()) {
      return result;
    }
    if (values.get(0) instanceof ArrayList<?>) {
      ArrayList<ArrayList<?>> listResult = new ArrayList<>();
      for (int i = 0; i < values.size(); i++) {
        if (values.get(i) instanceof ArrayList<?>) {
          ArrayList<?> item = deepCopy((ArrayList<?>) values.get(i));
          listResult.add(item);
        }
      }
      result = listResult;
    } else {

      ArrayList<Double> doubleResult = new ArrayList<>();
      for (Object item : values) {
        if (item instanceof Double) {
          double value = ((Double) item);
          doubleResult.add(value);
        }

      }
      result = doubleResult;
    }

    return result;
  }

  /**
   * Gets a 2 dimensional array of random double values with length1 for the
   * outer dimension and length2 for the inner dimension.
   *
   * @param length1 The length of the outer array.
   * @param length2 The length of the inner array.
   * @return A double jagged array like double[length1][length2];
   */
  public static double[][] random2(int length1, int length2) {
    Random rnd = new Random();
    double[][] result = new double[length1][];
    for (int i = 0; i < length1; i++) {
      result[i] = new double[length2];
      for (int j = 0; j < length2; j++) {
        result[i][j] = rnd.nextDouble();
      }
    }
    return result;
  }

  /**
   * Deep copy a 2D array of doubles.
   *
   * @param array The array to copy.
   * @return The jagged 2-d array of doubles.
   */
  public static double[][] copy2(double[][] array) {
    double[][] result = new double[array.length][];
    for (int i = 0; i < array.length; i++) {
      result[i] = new double[array[i].length];
      System.arraycopy(array[i], 0, result[i], 0, array[i].length);
    }
    return result;
  }

  /**
   * Gets a randomly created 3 dimensional jagged array of doubles.
   *
   * @param length1 The outer length.
   * @param length2 The middle length.
   * @param length3 The inner length.
   * @return The three dimensional array of doubles.
   */
  public static double[][][] random3(int length1, int length2, int length3) {
    double[][][] result = new double[length1][][];
    for (int i = 0; i < length1; i++) {
      result[i] = random2(length2, length3);
    }
    return result;
  }

  /**
   * Copy a 3 dimensional array of doubles.
   *
   * @param array The array to copy.
   * @return A duplicate 3 D array of doubles.
   */
  public static double[][][] copy3(double[][][] array) {
    double[][][] result;
    result = new double[array.length][][];
    for (int i = 0; i < array.length; i++) {
      result[i] = copy2(array[i]);
    }
    return result;
  }

  /**
   * Gets a randomly created 4 dimensional array.
   *
   * @param length1
   * @param length2
   * @param length3
   * @param length4
   * @return
   */
  public static double[][][][] random4(int length1, int length2, int length3, int length4) {
    double[][][][] result = new double[length1][][][];
    for (int i = 0; i < length1; i++) {
      result[i] = random3(length2, length3, length4);
    }
    return result;
  }

  /**
   * Copy a 3 dimensional array of doubles.
   *
   * @param array
   * @return
   */
  public static double[][][][] copy4(double[][][][] array) {
    double[][][][] result;
    result = new double[array.length][][][];
    for (int i = 0; i < array.length; i++) {
      result[i] = copy3(array[i]);
    }
    return result;
  }

}
