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

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.trace.ShapeSnapper;
import gov.ca.water.shapelite.trace.ShapeSnapper.SnapException;
import java.io.IOException;

/**
 * A utilities class to handle snapping.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class SnapUtils {

  /**
   * The Snapper element with potentially cached shape and attribute data for
   * faster repeat calls.
   */
  private static final ShapeSnapper SNAPPER = new ShapeSnapper();

  /**
   * Private constructor.
   */
  private SnapUtils() {

  }

  /**
   * Snaps the shape.
   *
   * @param location The guide location to attempt to find the closest point on
   * the shape for snapping.
   * @param shapefile The shapefile containing shapes to snap to. The shapes of
   * the previous snap test are cached, so future snaps that use the same shapes
   * @param fieldName The String field name.
   * @param fieldValue The String field value.
   * @return The optional Coord, which is empty if the shapes list is empty.
   * @throws SnapException If there was a problem with the shapefile or
   * attribute values for identifying the shape to snap to.
   * @throws java.io.IOException If there was a problem opening the shapefile.
   */
  public static Optional<Coord> snap(Coord location, String shapefile,
      String fieldName, String fieldValue) throws SnapException,
      IOException {
    if (SNAPPER.getShapefile() == null || !SNAPPER.getShapefile().equals(shapefile)) {
      SNAPPER.open(shapefile);
    }
    if (fieldValue != null && SNAPPER.getFieldName() != null
        && SNAPPER.getFieldName().equals(fieldName)) {
      SNAPPER.openAttributes(fieldName);
    }
    return SNAPPER.snap(location, fieldValue);
  }
}
