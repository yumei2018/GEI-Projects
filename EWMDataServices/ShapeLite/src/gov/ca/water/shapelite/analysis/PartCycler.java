/*
 * The MIT License
 *
 * Copyright 2015 hdunsford.
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
import gov.ca.water.shapelite.Part;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PartCycler {

  /**
   * This method iterates forward through the coordinates, wrapping at the
   * endpoints, and including the specified start and end points.
   *
   * @param part The part with coordinates to iterate through.
   * @param start The start coordinate.
   * @param end The end coordinate.
   * @return The list of coordinates, including the endpoints. This is empty if
   * the part is not closed end is below the start, and returns just one point
   * if end is the same as start.
   */
  public final List<Coord> forward(Part part, Coord start, Coord end) {
    if (part.isClosed()) {
      return forward(part.getCoordinates(), start, end);
    } else {
      List<Coord> result = new ArrayList<>();
      int iStart = part.getCoordinates().indexOf(start);
      int iEnd = part.getCoordinates().indexOf(end);
      for (int i = iStart; i <= iEnd; i++) {
        result.add(part.getCoordinates().get(i));
      }
      return result;
    }

  }

  /**
   * This method iterates backward through the coordinates, wrapping at the
   * endpoints, and including the specified start and end points.
   *
   * @param part The part with oordinates to iterate through.
   * @param start The start coordinate.
   * @param end The end coordinate.
   * @return The list of coordinates, not including the endpoints. This is empty
   * if the part is not closed end is above the start, and returns just one
   * point if end is the same as start.
   */
  public final List<Coord> backward(Part part, Coord start, Coord end) {
    if (part.isClosed()) {
      return backward(part.getCoordinates(), start, end);
    } else {
      List<Coord> result = new ArrayList<>();
      int iStart = part.getCoordinates().indexOf(start);
      int iEnd = part.getCoordinates().indexOf(end);
      for (int i = iStart; i >= iEnd; i--) {
        result.add(part.getCoordinates().get(i));
      }
      return result;
    }
  }

  /**
   * This method iterates forward through the coordinates, wrapping at the
   * endpoints, and including the specified start and end points.
   *
   * @param coords The coordinates to iterate through.
   * @param start The start coordinate.
   * @param end The end coordinate.
   * @return The list of coordinates, not including the endpoints.
   */
  public final List<Coord> forward(List<Coord> coords, Coord start, Coord end) {
    int iStart = coords.indexOf(start);
    int iEnd = coords.indexOf(end);
    return forward(coords, iStart, iEnd);
  }

  /**
   * This method iterates backward through the coordinates, wrapping at the
   * endpoints, and including the specified start and end points.
   *
   * @param coords The coordinates to iterate through.
   * @param start The start coordinate.
   * @param end The end coordinate.
   * @return The list of coordinates, not including the endpoints.
   */
  public final List<Coord> backward(List<Coord> coords, Coord start, Coord end) {
    int iStart = coords.indexOf(start);
    int iEnd = coords.indexOf(end);
    return backward(coords, iStart, iEnd);
  }

  /**
   * This method iterates forward through the coordinates, wrapping at the
   * endpoints and including the specified start and end points.
   *
   * @param coords The coordinates to iterate through.
   * @param start The start index.
   * @param end The end index.
   * @return The list of coordinates, not including the endpoints.
   */
  public final List<Coord> forward(List<Coord> coords, int start, int end) {
    List<Coord> results = new ArrayList<>();
    if (end > start) {
      for (int i = start; i <= end; i++) {
        results.add(coords.get(i));
      }
    } else {
      for (int i = start; i < coords.size(); i++) {
        results.add(coords.get(i));
      }
      for (int i = 0; i <= end; i++) {
        results.add(coords.get(i));
      }
    }
    return results;

  }

  /**
   * This method iterates forward through all the coordinates, wrapping at the
   * endpoints starting with the specified coordinate.
   *
   * @param coords The coordinates to iterate through.
   * @param start The start index.
   * @return The list of coordinates, not including the endpoints.
   */
  public final List<Coord> forward(List<Coord> coords, int start) {
    List<Coord> results = new ArrayList<>();
    for (int i = start; i < coords.size(); i++) {
      results.add(coords.get(i));
    }
    for (int i = 0; i < start; i++) {
      results.add(coords.get(i));
    }
    return results;
  }

  /**
   * This method iterates forward through the coordinates, wrapping at the
   * endpoints.
   *
   * @param coords The coordinates to iterate through.
   * @param start The start index.
   * @return The list of coordinates, not including the endpoints.
   */
  public final List<Coord> backward(List<Coord> coords, int start) {
    List<Coord> results = new ArrayList<>();
    for (int i = start; i >= 0; i--) {
      results.add(coords.get(i));
    }
    for (int i = coords.size() - 1; i > start; i--) {
      results.add(coords.get(i));
    }
    return results;

  }

  /**
   * This method iterates forward through the coordinates, wrapping at the
   * endpoints.
   *
   * @param coords The coordinates to iterate through.
   * @param start The start index.
   * @param end The end index.
   * @return The list of coordinates, not including the endpoints.
   */
  public final List<Coord> backward(List<Coord> coords, int start, int end) {
    List<Coord> results = new ArrayList<>();
    if (start > end) {
      for (int i = start; i >= end; i--) {
        results.add(coords.get(i));
      }
    } else {
      for (int i = start; i >= 0; i--) {
        results.add(coords.get(i));
      }
      for (int i = coords.size() - 1; i >= end; i--) {
        results.add(coords.get(i));
      }
    }
    return results;

  }

  /**
   * This method iterates forward through the coordinates, wrapping at the
   * endpoints, but excludes the specified start and end indices.
   *
   * @param coords The coordinates to iterate through.
   * @param start The start index.
   * @param end The end index.
   * @return The list of coordinates, not including the endpoints.
   */
  public final List<Coord> forwardBetween(List<Coord> coords, int start, int end) {
    List<Coord> results = new ArrayList<>();
    if (end > start) {
      for (int i = start + 1; i < end; i++) {
        results.add(coords.get(i));
      }
    } else {
      for (int i = start + 1; i < coords.size(); i++) {
        results.add(coords.get(i));
      }
      for (int i = 0; i < end; i++) {
        results.add(coords.get(i));
      }
    }
    return results;

  }

  /**
   * This method iterates forward through the coordinates, wrapping at the
   * endpoints but excludes the specified start and end points.
   *
   * @param coords The coordinates to iterate through.
   * @param start The start index.
   * @param end The end index.
   * @return The list of coordinates, not including the endpoints.
   */
  public final List<Coord> backwardBetween(List<Coord> coords, int start, int end) {
    List<Coord> results = new ArrayList<>();
    if (start > end) {
      for (int i = start - 1; i > end; i--) {
        results.add(coords.get(i));
      }
    } else {
      for (int i = start - 1; i >= 0; i--) {
        results.add(coords.get(i));
      }
      for (int i = coords.size() - 1; i > end; i--) {
        results.add(coords.get(i));
      }
    }
    return results;

  }
}
