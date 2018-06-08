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
package gov.ca.water.shapelite.trace;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.NonNull;
import gov.ca.water.shapelite.Nullable;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.io.AttributeTableReader;
import gov.ca.water.shapelite.io.ShapefileReader;
import gov.ca.water.shapelite.io.ShapefileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ShapeSnapper {

  // <editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The target shapes to snap to.
   */
  private final List<Shape> shapes;

  /**
   * The actual attribute values from the identifying column.
   */
  private final List<String> fieldValues;

  /**
   * The String name of the identifying column.
   */
  private String fieldName;

  /**
   * The String name of the shapefile (ending in .shp).
   */
  private String shapefile;

  // </editor-fold>
  /**
   * Creates a new shape snapper with the specified FeatureSet to use for
   * snapping. For polygons, this will snap to the nearest point on the
   * boundary.
   */
  public ShapeSnapper() {
    shapes = new ArrayList<>();
    fieldValues = new ArrayList<>();
    fieldName = null;
  }

  // <editor-fold desc="Methods">
  /**
   * Opens a shapefile for snapping to the nearest shape.
   *
   * @param shapefile The shapefile to open.
   * @throws java.io.IOException Throws an IO Exception if there was a problem
   * reading the file.
   */
  public final void open(String shapefile) throws IOException {
    openShapes(shapefile);
    fieldName = null;
    fieldValues.clear();
  }

  /**
   * Opens the shapefile shapes and the string field names so that a specified
   * field value can identify a shape for snapping.
   *
   * @param shapefile The string shapefile.
   * @param fieldName The string field name to identify a shape.
   * @throws java.io.IOException if there was a problem reading the file.
   * @throws SnapException if there were problems opening the shapes or
   * attributes.
   */
  public final void open(String shapefile, String fieldName)
      throws IOException, SnapException {
    openShapes(shapefile);
    openAttributes(fieldName);
  }

  /**
   * Opens the shapes only.
   *
   * @param shapefile The String shapefile to open shapes from.
   * @throws IOException if there was an error reading the file.
   */
  private void openShapes(String shapefile) throws IOException {
    ShapefileReader reader = new ShapefileReader();
    reader.open(shapefile);
    shapes.clear();
    shapes.addAll(reader.getShapes());
  }

  /**
   * .
   * @param fieldName The String column name storing ID values to use later.
   * @throws IOException Throws IOException if there was an error reading the
   * dbf file.
   * @throws SnapException if the the fieldName could not be found or did not
   * produce any values.
   */
  public final void openAttributes(String fieldName)
      throws IOException, SnapException {
    if (shapefile == null) {
      throw new SnapException("Open the shapefile first to specify the shapefile name.");
    }
    this.fieldName = fieldName;
    fieldValues.clear();
    AttributeTableReader at = new AttributeTableReader();
    at.open(ShapefileWriter.setExtension(shapefile, ".dbf"));
    Optional<String[]> maybeValues = at.getFieldStrings(fieldName);
    if (maybeValues.isPresent()) {
      fieldValues.addAll(Arrays.asList(maybeValues.get()));
    } else {
      throw new SnapException("The values for the "
          + this.fieldName + " column were not found.");
    }
  }

  /**
   * Gets a snap coordinate on the target FeatureSet for the shape with the
   * field name that matches the specified field name.
   *
   * @param location The location to test.
   * @param fieldValue The string representation of the value. A null value will
   * simply test all shapes to return the closest point. Envelope optimizations
   * are used to attempt to improve efficiency.
   * @return the CoordXY of the snap point.
   * @throws SnapException if the id fields were not found, or if there
   */
  public final Optional<Coord> snap(@NonNull Coord location, @Nullable String fieldValue)
      throws SnapException {
    if (location == null) {
      throw new IllegalArgumentException("Location cannot be null.");
    }
    Optional<Coord> result = Optional.empty();
    if (shapes.isEmpty()) {
      throw new SnapException("The shapes were not loaded. "
          + " Use the open method to open shapes first.");
    }
    if (fieldValues.isEmpty() || fieldName == null) {
      throw new SnapException("Open a shapefile specifying a field name first "
          + "in order to use the idValue.");
    }
    if (fieldValue != null) {
      int index = fieldValues.indexOf(fieldValue);
      if (index < 0) {
        throw new SnapException("The id value " + fieldValue + " could not be "
            + "found in the string representation of the values from the "
            + fieldName + " column.");
      }
      if (index >= shapes.size()) {
        throw new SnapException("The identified field index " + index
            + " was outside of the bounds of the shapes, which only had "
            + shapes.size() + " values.");
      }
      Shape shape = shapes.get(index);
      return shape.closestPointTo(location);
    } else {
      double minFarthest = Double.MAX_VALUE;
      // Check the envelopes first to find an elimination distance
      for (Shape shape : shapes) {
        double distance = shape.getEnvelope().maxDistance(location);
        if (distance < minFarthest) {
          minFarthest = distance;
        }
      }
      // We can eliminate doing potentially time consuming distnace calcs
      // on any shapes that have envelopes where the closest point is
      // further away than the farthest point on the closest envelope.
      double minDistance = minFarthest;
      for (Shape shape : shapes) {
        if (shape.getEnvelope().distance(location) < minDistance) {
          Optional<Coord> maybeC = shape.closestPointTo(location);
          if (maybeC.isPresent()) {
            double distance = location.distance(maybeC.get());
            if (distance < minDistance) {
              minDistance = distance;
              result = maybeC;
            }
          }
        }
      }
    }
    return result;

  }

  // </editor-fold>
  // <editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the String name of the shapefile (ending in .shp).
   *
   * @return the shapefile
   */
  public final String getShapefile() {
    return shapefile;
  }

  /**
   * Sets the String name of the shapefile (ending in .shp).
   *
   * @param shapefile the shapefile to set
   */
  public final void setShapefile(String shapefile) {
    this.shapefile = shapefile;
  }

  /**
   * Gets the String name of the identifying column.
   *
   * @return the fieldName
   */
  public final String getFieldName() {
    return fieldName;
  }

  /**
   * Sets the String name of the identifying column.
   *
   * @param fieldName the fieldName to set
   */
  public final void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }

  /**
   * If there was a serious exception, like being unable to find the attributes.
   */
  public static class SnapException extends Exception {

    /**
     * Creates a new instance of <code>SnapException</code> without detail
     * message.
     */
    public SnapException() {
    }

    /**
     * Constructs an instance of <code>SnapException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public SnapException(String msg) {
      super(msg);
    }

    /**
     * Constructs an instance of <code>SnapException</code> with the specified
     * exception passed as the inner exception.
     *
     * @param source The Exception source.
     */
    public SnapException(Exception source) {
      super(source);
    }
  }

  // </editor-fold>
}
