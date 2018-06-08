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
package gov.ca.water.shapelite;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.GeometryFactory;
import gov.ca.water.shapelite.analysis.FeaturesetTypeConverter;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.delegate.IntersectDelegate;
import gov.ca.water.shapelite.io.ShapefileReader;
import gov.ca.water.shapelite.projection.DistanceUtils;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Reproject;
import gov.ca.water.shapelite.topology.Adapter;
import gov.ca.water.shapelite.utils.JTSUtils;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.sql.TIMESTAMP;

/**
 * A FeatureSet class containing a list of features.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class FeatureSet implements Serializable/*, List<Feature>/**/ {

  private static final Logger LOGGER = Logger.getLogger(FeatureSet.class.getName());

  /**
   * Dbf files cannot contain fields larger than 255.
   */
  private static final int MAX_FIELD_SIZE = 254;

  /**
   * If dates have to be converted to a character representation, then this default character length is used.
   */
  private static final int DEFAULT_DATE_LENGTH = 25;

  /**
   * This is the default field length when creating a FeatureSet from an existing feature.
   */
  private static final int DEFAULT_STRING_LENGTH = 50;

  /**
   * Numeric type.
   */
  private static final int NUMERIC = 101;

  /**
   * A number of characters large enough to hold typical numbers.
   */
  private static final int NUMBER_SIZE = 32;

  /**
   * A precision to allow about half of the digits to be right of the decimal.
   */
  private static final int PRECISION = 16;

  /**
   * Default scale to use when building from attributes with small decimals of precision.
   */
  private static final int COURSE_PRECISION = 8;

  /**
   * The shape type to use if the feature set is empty.
   */
  private ShapeType defaultShapeType;

  /**
   * Base 10 for power of 10.
   */
  private static final int BASE_10 = 10;

  /**
   * By default preserve coordinates to 1 foot of precision.
   */
  private static final double DEFUALT_PRECISION_FT = 1;

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The list of Fields that are in the attributes of all the features.
   */
  private final List<Field> fields;

  /**
   * The list of Features in this FeatureSet.
   */
  private final List<Feature> features;

  /**
   * The String text that would make up the ESRI prj file.
   */
  private final ProjectionInfo projection;

  /**
   * This is the fieldName of the shapefile but without any extensions. This is important to set when creating a zip
   * output stream.
   */
  private String name;

  /**
   * The date format used for writing dates as strings.
   */
  private SimpleDateFormat dateFormat;

  /**
   * The coordinate precision in feet to use. By default this is 1. While this can be set to be accurate to greater
   * precision, it may cause problems when doing intersection/overlay topology if the real data does not have the same
   * corresponding precision.
   */
  private double precision;

  private Double rounding;

  //</editor-fold>
  /**
   * Creates a new instance of the FeatureSet class.
   */
  public FeatureSet() {
    fields = new ArrayList<>();
    features = new ArrayList<>();
    dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    projection = ProjectionInfo.getDefault();
    defaultShapeType = ShapeType.NullShape;
    precision = DEFUALT_PRECISION_FT;
  }

  /**
   * Creates a new instance of the FeatureSet class.
   *
   * @param shapeType The shape type that is used for the default shape type in the case when no shapes are in the
   * feature set.
   */
  public FeatureSet(ShapeType shapeType) {
    fields = new ArrayList<>();
    features = new ArrayList<>();
    dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    projection = ProjectionInfo.getDefault();
    defaultShapeType = shapeType;
    precision = DEFUALT_PRECISION_FT;
  }

  /**
   * Gets the coordinate precision in feet to use. By default this is 1. While this can be set to be accurate to greater
   * precision, it may cause problems when doing intersection/overlay topology if the real data does not have the same
   * corresponding precision.
   *
   * @return the precision
   */
  public final double getPrecision() {
    return precision;
  }

  /**
   * Sets the coordinate precision in feet to use. By default this is 1. While this can be set to be accurate to greater
   * precision, it may cause problems when doing intersection/overlay topology if the real data does not have the same
   * corresponding precision.
   *
   * @param precision the precision to set
   */
  public final void setPrecision(double precision) {
    this.precision = precision;
  }

  /**
   * Gets a boolean that is true if this FeatureSet's feature list is empty.
   *
   * @return True if the feature list is empty.
   */
  public final boolean isEmpty() {
    return this.features.isEmpty();
  }

  /**
   * Creates a new instance of the FeatureSet class.
   *
   * @param feature A reference feature that has the attribute fields. By default, the attribute fields and lengths will
   * be set as string fields of char length 50, unless the value in the field is longer than 50, in which case it will
   * use a maximum of 255.
   */
  public FeatureSet(Feature feature) {
    this();
    features.add(feature);
    for (String key : feature.getAttributes().keySet()) {
      if (key != null) {
        String value = feature.getAttributes().get(key);
        Field f;
        if (value.length() > DEFAULT_STRING_LENGTH) {
          f = new Field(key, FieldType.Character, MAX_FIELD_SIZE);

        } else {
          f = new Field(key, FieldType.Character, DEFAULT_STRING_LENGTH);
        }
        fields.add(f);
      }
    }

  }

  /**
   * This method will take all of the shapes in the two feature sets, create a single geometry for each and then perform
   * the analysis using JTS. An empty feature set will be treated like an empty geometry collection.
   *
   * @param other The other feature set to test.
   * @return boolean, true if this featureSet contains the other.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If the projections do not match and reproject fails.
   */
  public final boolean contains(FeatureSet other) throws ProjectionException {
    FeatureSet projOther = other;
    if (!this.projection.equals(other.getProjection())) {
      projOther = other.copy();
      projOther.reproject(projection);
    }
    if (!this.getEnvelope().intersects(projOther.getEnvelope())) {
      return false;
    }
    Geometry myGeometry = Adapter.getGeometry(this);
    Geometry innerGeometry = Adapter.getGeometry(projOther);
    innerGeometry = innerGeometry.union();
    return myGeometry.contains(innerGeometry);
  }

  /**
   * This method will take all of the shapes in the two feature sets, create a single geometry for each and then perform
   * the analysis using JTS. An empty feature set will be treated like an empty geometry collection.
   *
   * @param other The other feature set to test.
   * @return boolean, true if this featureSet contains the other.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If the projections do not match and reproject fails.
   */
  public final boolean covers(FeatureSet other) throws ProjectionException {
    FeatureSet projOther = other;
    if (!this.projection.equals(other.getProjection())) {
      projOther = other.copy();
      projOther.reproject(projection);
    }
    if (!this.getEnvelope().intersects(projOther.getEnvelope())) {
      return false;
    }
    Geometry cont = Adapter.getGeometry(this);
    cont = cont.union();
    Geometry inter = Adapter.getGeometry(projOther);
    inter = inter.union();
    return cont.covers(inter);
  }

  /**
   * This method will take all of the shapes in the two feature sets, create a single geometry for each and then perform
   * the analysis using JTS. An empty feature set will be treated like an empty geometry collection.
   *
   * @param other The other feature set to test.
   * @param buffer A buffer in US Feet applied to this FeatureSet before testing for covers. If the buffer is positive,
   * then the covers test will be more likely to be true, and if it is negative, it will be less likely to be true.
   * @return boolean, true if this featureSet contains the other.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If the projections do not match and reproject fails.
   */
  public final boolean covers(FeatureSet other, double buffer)
      throws ProjectionException {
    FeatureSet projOther = other;
    if (!this.projection.equals(other.getProjection())) {
      projOther = other.copy();
      projOther.reproject(projection);
    }
    Envelope env = this.getEnvelope();
    double dist = 0;
    if (buffer != 0) {
      dist = DistanceUtils.feetToProjectedDistanceY(buffer, projection);
      env.buffer(dist);
      if (env.isEmpty()) {
        // If our envelope is buffered out of existance by a negative
        // buffer, return false.
        return false;
      }
    }
    if (!env.intersects(projOther.getEnvelope())) {
      return false;
    }
    FeatureSet big = this;
    if (buffer != 0) {
      big = this.buffer(dist);
    }
    Geometry bigGeometry = Adapter.getGeometry(big);
    bigGeometry = bigGeometry.union();
    Geometry littleGeometry = Adapter.getGeometry(projOther);
    littleGeometry = littleGeometry.union();
    return bigGeometry.covers(littleGeometry);
  }

  /**
   * Creates a new FeatureSet where each feature has a buffer in the distance units that match the corresponding
   * projection. It is the responsibility of the user to ensure that the projection distance units are appropriate.
   *
   * @param distance The distance in projection units.
   * @return The FeatureSet resulting from the buffer operation.
   */
  public final FeatureSet buffer(double distance) {
    FeatureSet result = copy();
    result.getFeatures().clear();
    for (Feature f : features) {
      Feature buffer = f.buffer(distance);
      if (buffer.getShape().getShapeType() == ShapeType.NullShape) {
        continue;
      }
      result.getFeatures().add(buffer);
    }
    return result;

  }

  /**
   * Creates a deep copy of this feature set. The shapes and attributes will be the same, but not use any of the same
   * object references.
   *
   * @return A deep copy of this FeatureSet.
   */
  public final FeatureSet copy() {
    FeatureSet copy = new FeatureSet();
    copy.copyProperties(this);
    return copy;
  }

  /**
   * Copies all the attributes from the other source to this object.
   *
   * @param source The source to copy from.
   */
  public final void copyProperties(FeatureSet source) {
    copyProperties(source, true);
  }

  /**
   * Copies all the attributes from the other source to this object.
   *
   * @param source The source to copy from.
   * @param copyFeatures Boolean, if this is false, then everything but the features will be copied.
   */
  public final void copyProperties(FeatureSet source, boolean copyFeatures) {
    if (source == null) {
      return;
    }
    fields.clear();
    for (Field field : source.fields) {
      fields.add(field.copy());
    }
    features.clear();
    if (copyFeatures) {
      Feature copied = null;
      for (Feature feature : source.features) {
        copied = feature.copy();
        copied.setParent(this);
        features.add(copied);
      }
    }
    projection.copyProperties(source.projection);
    name = source.name;
    dateFormat = (SimpleDateFormat) source.dateFormat.clone();
    defaultShapeType = source.defaultShapeType;
  }

  /**
   * This method uses any result set to create a set of shapefile attributes that are close to representing the same
   * information. This does not actually use the information itself.
   *
   * @param metadata The schema information from a ResultSet.
   * @throws java.sql.SQLException An exception if the metadata cannot be read.
   */
  public final void addFields(ResultSetMetaData metadata)
      throws SQLException {
    int count = metadata.getColumnCount();
    for (int i = 1; i <= count; i++) {
      String fieldName = metadata.getColumnName(i);
      int type = metadata.getColumnType(i);
      int length = metadata.getPrecision(i);
      if (length > MAX_FIELD_SIZE || length == 0) {
        length = MAX_FIELD_SIZE;
      }
      Field fld;
      switch (type) {
        case Types.NUMERIC:
        case Types.DOUBLE:
        case Types.FLOAT:
        case Types.INTEGER:
        case Types.BIGINT:
          int dec = metadata.getScale(i);
          if (dec < 0) {
            dec = COURSE_PRECISION;
          }
          fld = new Field(fieldName, length, dec);
          break;
        case Types.VARCHAR:
        case Types.CHAR:
        case Types.CLOB:
          fld = new Field(fieldName, FieldType.Character, length);
          break;
        case Types.DATE:
        case Types.TIMESTAMP:
        // case Types.TIMESTAMP_WITH_TIMEZONE:
        case Types.TIME:
          // case Types.TIME_WITH_TIMEZONE:
          // Don't use the shapefile date format since it
          // doesn't support time.
          fld = new Field(fieldName, FieldType.Character,
              DEFAULT_DATE_LENGTH);
          break;
        case Types.BOOLEAN:
          fld = new Field(fieldName, FieldType.Logical, length);
          break;
        case NUMERIC:
          fld = new Field(fieldName, NUMBER_SIZE, PRECISION);
          fld.setDataType(Double.class);
          fld.setType("N");
          break;
        default:
          fld = new Field(fieldName, FieldType.Character, length);
      }
      this.fields.add(fld);
    }
    calculateFieldOffsets();
  }

  /**
   * Adds the list of fields to this FeatureSet.
   *
   * @param fields The fields to create.
   */
  public final void addFields(List<Field> fields) {
    ArrayList<Field> newfields = new ArrayList<>(fields);
    newfields.addAll(this.fields);
    this.setFields(newfields);
  }

  /**
   * Adds the list of fields to this FeatureSet, but only if a field with the same name does not already exist.
   *
   * @param fields The fields to potentially create.
   * @return List of Field objects actually created.
   */
  public final List<Field> addMissingFields(List<Field> fields) {
    List<Field> results = new ArrayList<>();
    for (Field field : fields) {
      boolean found = false;
      for (Field myField : this.fields) {
        if (myField.getName() != null && myField.getName().equals(field.getName())) {
          found = true;
          break;
        }
      }
      if (!found) {
        Field createdField = field.copy();
        results.add(createdField);
      }
    }
    for (Field field : results) {
      this.fields.add(field);
    }
    return results;
  }

  /**
   * The field offsets are only used when reading attributes, but for some purposes, it may be helpful to be able to
   * calculate the offsets without reading the attributes from a file.
   */
  public final void calculateFieldOffsets() {
    int offset = 1;
    for (Field field : fields) {
      field.setOffset(offset);
      offset += field.getLength();
    }
  }

  /**
   * Adds the specified result set, converting rows into points.
   *
   * @param resultSet The result set of actual data values.
   * @param xFieldName The field name that has X coordinate information.
   * @param yFieldName The field name that has the Y coordinate information.
   * @throws java.sql.SQLException An exception thrown if the ResultSet cannot be read.
   */
  public final void addPoints(ResultSet resultSet, String xFieldName,
      String yFieldName) throws SQLException {

    this.addFields(resultSet.getMetaData());
    while (resultSet.next()) {
      double x = resultSet.getDouble(xFieldName);
      double y = resultSet.getDouble(yFieldName);
      Coord coord = new CoordXY(x, y);
      Shape shape = new Shape(coord);
      Feature f = new Feature();
      f.setShape(shape);
      for (Field fld : this.getFields()) {
        Object value = resultSet.getObject(fld.getName());
        if (value != null) {
          if (value instanceof Date) {
            Date dValue = (Date) value;
            f.getAttributes().put(fld.getName(),
                dateFormat.format(dValue));

          } else if (value instanceof TIMESTAMP) {
            TIMESTAMP ts = (TIMESTAMP) value;
            Date dValue = ts.dateValue();
            f.getAttributes().put(fld.getName(),
                dateFormat.format(dValue));
          } else {
            if ("LATITUDE".equals(fld.getName())) {
              boolean stop = true;
            }
            f.getAttributes().put(fld.getName(), value.toString());
          }
        }
      }
      this.getFeatures().add(f);
    }
  }

  /**
   * This starts with the assumption that no fields exist in this featureset and simply duplicates the table schema from
   * the other featureset.
   *
   * @param source The FeatureSet to copy fields from.
   */
  public final void copyFields(FeatureSet source) {
    for (Field field : source.getFields()) {
      this.fields.add(field.copy());
    }
  }

  /**
   * Cycles through the list of fields and adds a field to this FeatureSet that has the same properties. If the field
   * exists in the field list, it will be modified with a numeric ending. A list of the created output fields is
   * returned.
   *
   * @param fields The list of fields to append.
   * @return List of Fields that may be different from the original list.
   */
  public final List<Field> appendCopiedFields(List<Field> fields) {
    List<Field> result = new ArrayList<>();
    for (Field field : fields) {
      result.add(appendCopiedField(field));
    }
    return result;
  }

  /**
   * Appends a new Field to the featureset using the specified field as the template. If the specified field fieldName
   * matches an existing field, it will be modified with a number to ensure uniqueness in 10 characters.
   *
   * @param template the existing Field template to add.
   * @return the Field that was copied.
   */
  public final Field appendCopiedField(Field template) {
    Field result = template.copy();
    String base = result.getShortName();
    String fieldName = base;
    int i = 1;
    // Note that this will run into problems if there are more than
    // 9 identical fields.
    while (containsFieldNamed(fieldName)) {
      fieldName = base + Integer.toString(i);
    }
    result.setName(fieldName);
    return result;
  }

  /**
   * Gets a boolean indicating whether a field with the specified fieldName exists.
   *
   * @param shortName the 10 character field shortname.
   * @return A boolean, which is true if a field with a matching name was found.
   */
  public final boolean containsFieldNamed(String shortName) {
    for (Field field : fields) {
      if (field.getShortName() == null) {
        if (shortName == null) {
          return true;
        }
        continue;
      }
      if (field.getShortName().equals(shortName)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Removes the Features where the field name matches the specified fieldValue.
   *
   * @param fieldName The String field name.
   * @param fieldValue The String fieldValue.
   * @return the list of features removed.
   */
  public final List<Feature> remove(String fieldName, String fieldValue) {
    List<Feature> result = new ArrayList<>();
    if (this.containsFieldNamed(fieldName) && fieldValue != null) {
      Iterator<Feature> it = this.getFeatures().iterator();
      Feature f = null;
      while (it.hasNext()) {
        f = it.next();
        if (f.getAttributes().containsKey(fieldName)) {
          String value = f.getAttributes().get(fieldName);
          if (value != null && value.equals(fieldValue)) {
            it.remove();
          }
        }
      }
    }

    return result;
  }

  /**
   * Given another feature set, this will cycle through all the features of this feature set and produce a corresponding
   * output feature set made up entirely of shapes that are the differences between the current featureset and the union
   * of the shapes on the other featureset.
   *
   * @param other The feature set to subtract from this one.
   * @return The FeatureSet with subtracted shapes. No null shapes will be included, so the shapes may be empty. The
   * attributes from this featureset will be included, but the other attributes will be ignored.
   */
  public final FeatureSet difference(FeatureSet other) {
    return difference(other, 0);
  }

  /**
   * Given another feature set, this will cycle through all the features of this feature set and produce a corresponding
   * output feature set made up entirely of shapes that are the differences between the current featureset and the union
   * of the shapes on the other featureset.
   *
   * @param other The feature set to subtract from this one.
   * @param bufferFeet If this is non-zero, then before a difference is allowed to be added, the buffer is first applied
   * to the other shape. If the buffered other shape has a difference, then the the real difference will be included in
   * the results. A positive buffer is expected in order to make differencing harder.
   * @return The FeatureSet with subtracted shapes. No null shapes will be included, so the shapes may be empty. The
   * attributes from this featureset will be included, but the other attributes will be ignored.
   */
  public final FeatureSet difference(FeatureSet other, double bufferFeet) {
    FeatureSet result = new FeatureSet();
    if (other == null) {
      return result;
    }
    result.appendCopiedFields(other.getFields());
    FeatureSet union = other.union();
    FeatureSet buffer = union;
    if (bufferFeet != 0 && union.getProjection() != null) {
      double dist = union.getProjection().feetToProj(bufferFeet);
      buffer = union.buffer(dist);
    }

    for (Feature feature : getFeatures()) {
      Feature resultFeature = new Feature();
      resultFeature.addAttributes(feature.getAttributes());
      Shape shape = feature.getShape();
      for (int i = 0; i < union.getFeatures().size(); i++) {
        Shape bufferShape = buffer.getFeatures().get(i).getShape();
        if (!bufferShape.contains(shape)) {
          Feature otherFeature = union.getFeatures().get(i);
          Shape diff = shape.difference(otherFeature.getShape());
          if (diff.getShapeType() != ShapeType.NullShape) {
            if (resultFeature.getShape().getShapeType() == ShapeType.NullShape) {
              resultFeature.setShape(diff);
            } else {
              resultFeature.getShape().getParts().addAll(diff.getParts());
            }
          }
        }

      }

      resultFeature.getShape().calculateBounds();
      if (!resultFeature.getShape().isEmpty()) {
        result.getFeatures().add(resultFeature);
      }
    }
    return result;
  }

  /**
   * Given another feature set, this will cycle through all the features of this feature set and produce a corresponding
   * output feature set made up entirely of shapes that are the intersections between the shapes in the current
   * featureset and the shapes on the other featureset. Attributes from both sources will be preserved.
   *
   * @param other The feature set to subtract from this one.
   * @return The FeatureSet with subtracted shapes. No null shapes will be included, so the shapes may be empty. The
   * attributes from this featureset will be included, but the other attributes will be ignored.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if the projections don't match and an error occurs
   * during an effort to reproject the other FeatureSet to match this feature set's projection.
   */
  public final FeatureSet intersection(FeatureSet other) throws ProjectionException {
    return this.intersection(other, 0);
  }

  public final FeatureSet intersection(FeatureSet other, IntersectDelegate delegate) throws ProjectionException {
    return this.intersection(other, 0, 0, delegate);
  }

  /**
   * Given another feature set, this will cycle through all the features of this feature set and produce a corresponding
   * output feature set made up entirely of shapes that are the intersections between the shapes in the current
   * featureset and the shapes on the other featureset. Attributes from both sources will be preserved.
   *
   * @param other The feature set to subtract from this one.
   * @return The FeatureSet with subtracted shapes. No null shapes will be included, so the shapes may be empty. The
   * attributes from this featureset will be included, but the other attributes will be ignored.
   * @param bufferOtherFeet a distance to buffer the other shape in feet. If the buffered shape does not intersect then
   * the shape is not included in the results. The original shape will be used for the actual intersection result
   * creation. A negative distance is expected to help remove slivers.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if the projections don't match and an error occurs
   * during an effort to reproject the other FeatureSet to match this feature set's projection.
   *
   */
  public final FeatureSet intersection(FeatureSet other, double bufferOtherFeet) throws ProjectionException {
    return this.intersection(other, bufferOtherFeet, 0, null);
  }

  public final FeatureSet intersection(FeatureSet other, double bufferOtherFeet, IntersectDelegate delegate) throws ProjectionException {
    return this.intersection(other, bufferOtherFeet, 0, delegate);
  }

  /**
   * Given another feature set, this will cycle through all the features of this feature set and produce a corresponding
   * output feature set made up entirely of shapes that are the intersections between the shapes in the current
   * featureset and the shapes on the other featureset. Attributes from both sources will be preserved.
   *
   * @param other The feature set to subtract from this one.
   * @return The FeatureSet with subtracted shapes. No null shapes will be included, so the shapes may be empty. The
   * attributes from this featureset will be included, but the other attributes will be ignored.
   * @param bufferOtherFeet a distance to buffer the other shape in feet. If the buffered shape does not intersect then
   * the shape is not included in the results. The original shape will be used for the actual intersection result
   * creation. A negative distance is expected to help remove slivers.
   * @param bufferSelfFeet a distance to buffer the shapes on this FeatureSet in feet. If the buffered shape does not
   * intersect then the shape is not included in the results. The original shape will be used for the actual
   * intersection result creation. A negative distance is expected to help remove slivers.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if the projections don't match and an error occurs
   * during an effort to reproject the other FeatureSet to match this feature set's projection.
   *
   */
  public final FeatureSet intersection(FeatureSet other, double bufferOtherFeet, double bufferSelfFeet)
      throws ProjectionException {
    return this.intersection(other, bufferOtherFeet, bufferSelfFeet, null);
  }

  public final FeatureSet intersection(FeatureSet other, double bufferOtherFeet, double bufferSelfFeet, IntersectDelegate delegate)
      throws ProjectionException {
    ProjectedBuffer selfBuffer = null;
    if (bufferSelfFeet != 0) {
      selfBuffer = new ProjectedBuffer(this.getProjection(), bufferSelfFeet);
    }
    ProjectedBuffer otherBuffer = null;
    if (other != null && bufferOtherFeet != 0) {
      otherBuffer = new ProjectedBuffer(other.getProjection(), bufferOtherFeet);
    }
    return this.intersection(other, otherBuffer, selfBuffer, delegate);
  }

  /**
   * Given another feature set, this will cycle through all the features of this feature set and produce a corresponding
   * output feature set made up entirely of shapes that are the intersections between the shapes in the current
   * featureset and the shapes on the other featureset. Attributes from both sources will be preserved.
   *
   * @param other The feature set to subtract from this one.
   * @param bufferOther The buffer for the other FeatureSet in projected units.
   * @param bufferSelf The buffer for this FeatureSet in projected units.
   * @return The FeatureSet with subtracted shapes. No null shapes will be included, so the shapes may be empty. The
   * attributes from this featureset will be included, but the other attributes will be ignored.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if the projections don't match and an error occurs
   * during an effort to reproject the other FeatureSet to match this feature set's projection.
   *
   */
  public final FeatureSet intersectionOld(FeatureSet other,
      ProjectedBuffer bufferOther, ProjectedBuffer bufferSelf, IntersectDelegate delegate) throws ProjectionException {
    FeatureSet result = new FeatureSet(getFeatureType());
    result.setDateFormat(dateFormat);
    result.setDefaultShapeType(defaultShapeType);
    result.setName(name + "_other");
    result.setProjectionFrom(projection);
    if (other == null) {
      return result;
    }
    result.setName(name + "_" + other.name);
    result.addFields(this.getFields());
    result.addMissingFields(other.getFields());
    if (delegate == null) {
      delegate = new IntersectDelegate(this) {
        @Override
        public void onIntersect(Feature feature, Feature otherFeature, Feature resultFeature) {
          try {
            resultFeature.addAttributes(feature.getAttributes());
            resultFeature.copyMissingAttributes(otherFeature);
          } catch (Exception ex) {
            ex.printStackTrace();
          }
        }
      };
    }
    delegate.setFeatureSet(this).setOtherSet(other);
    FeatureSet reproj = other;
    if (!this.getProjection().equals(other.getProjection())) {
      reproj = other.copy();
      reproj.reproject(this.getProjection());
    }
    double scale = this.getScale(this.getPrecision());
    for (Feature feature : getFeatures()) {
      if (bufferSelf != null) {
        feature = feature.buffer(bufferSelf.getBufferProjected());
        feature.setParent(this);
      }

      Shape shape = feature.getShape();
//      shape = shape.round(scale);
      for (Feature otherFeature : reproj.getFeatures()) {
        try {
          Shape otherShape = otherFeature.getShape();
//          otherShape = otherShape.round(scale);
          if (!shape.getEnvelope().intersects(otherShape.getEnvelope())) {
            continue; // skip buffering in most cases.
          }
          Coord center = otherShape.getEnvelope().getCenter();
          Shape bufferShape = otherShape;
          if (bufferOther != null) {
            bufferShape = otherShape.buffer(bufferOther.getBufferProjected());
          }
          if (bufferShape.getShapeType() == ShapeType.NullShape) {
            bufferShape = new Shape(ShapeType.Point);
            bufferShape.setX(center.getX());
            bufferShape.setY(center.getY());
            bufferShape.calculateBounds();
          }
//          bufferShape = bufferShape.round(scale);
          if (bufferShape.intersects(shape)) {
            Optional<Shape> maybeShape = shape.intersection(otherShape);
            if (maybeShape.isPresent()) {
              Feature resultFeature = new Feature();
              resultFeature.setShape(maybeShape.get());
              resultFeature.getShape().calculateBounds();
              resultFeature.setParent(result);
              result.getFeatures().add(resultFeature);

              delegate.onIntersect(feature, otherFeature, resultFeature);
            }
          }
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    }
    return result;
  }

  private final Shape fixNullShape(Shape s) {
    Shape result = null;
    if (s.getShapeType() == ShapeType.NullShape) {
      Coord center = s.getEnvelope().getCenter();
      result = new Shape(ShapeType.Point);
      result.setX(center.getX());
      result.setY(center.getY());
      result.calculateBounds();
    } else {
      result = s;
    }
    return result;
  }

  /**
   * Creates a FeatureSet from the specified shapefile or zip file.
   *
   * @param shpOrZipFile The path to the .shp file, or a .zip file containing all the necessary .shp, .shx, .dbf and
   * .prj files. A default precision of .001 foot is applied. foot is applied.
   * @return The FeatureSet to create.
   * @throws ShapefileException If there was an error reading the file.
   */
  public static FeatureSet from(String shpOrZipFile) throws ShapefileException {
    try {
      ShapefileReader reader = new ShapefileReader();
      reader.open(shpOrZipFile);
      FeatureSet fs = reader.getFeatures();
      return fs;
    } catch (FileNotFoundException ex) {
      throw new ShapefileException(ex);
    } catch (IOException ex) {
      throw new ShapefileException(ex);
    }
  }

  /**
   * Creates a FeatureSet from the specified shapefile or zip file.
   *
   * @param shpOrZipFile The path to the .shp file, or a .zip file containing all the necessary .shp, .shx, .dbf and
   * .prj files.
   * @param roundingInFeet This should be a positive double error in feet to round digits to. This will then estimate
   * the number of decimals in the projected coordinate system need to be and crop off digits accordingly.
   * @return The FeatureSet to create.
   * @throws ShapefileException if the file could not be read.
   */
  public static FeatureSet from(String shpOrZipFile, double roundingInFeet)
      throws ShapefileException {
    try {
      ShapefileReader reader = new ShapefileReader();
      reader.open(shpOrZipFile);
      FeatureSet fs = reader.getFeatures();
      if (roundingInFeet != 0) {
        fs.roundToFeet(roundingInFeet);
      }
      return fs;
    } catch (IOException ex) {
      throw new ShapefileException(ex);
    }
  }

  /**
   * Parses the InputStream.
   *
   * @param zipStream The InputStream that is from the zipped content of a shapefile.
   * @return The FeatureSet.
   * @throws ShapefileException If there was an error reading the stream.
   */
  public static FeatureSet from(InputStream zipStream) throws ShapefileException {
    try {
      ShapefileReader reader = new ShapefileReader();
      reader.open(zipStream);
      FeatureSet fs = reader.getFeatures();
      return fs;
    } catch (FileNotFoundException ex) {
      throw new ShapefileException(ex);
    } catch (IOException ex) {
      throw new ShapefileException(ex);
    }
  }

  /**
   * Parses the InputStream.
   *
   * @param zipStream The InputStream that is from the zipped content of a shapefile.
   * @param roundingInFeet This should be a positive double error in feet to round digits to. This will then estimate
   * the number of decimals in the projected coordinate system need to be and crop off digits accordingly.
   * @return The FeatureSet.
   * @throws ShapefileException If there was an error reading the stream.
   */
  public static FeatureSet from(InputStream zipStream, double roundingInFeet)
      throws ShapefileException {
    try {
      ShapefileReader reader = new ShapefileReader();
      reader.open(zipStream);
      FeatureSet fs = reader.getFeatures();
      fs.roundToFeet(roundingInFeet);
      return fs;
    } catch (IOException ex) {
      throw new ShapefileException(ex);
    }
  }

  /**
   * The binary from the zipped shapefile.
   *
   * @param binaryZip The binary data to be read as if it were a zipped shapefile.
   * @return The FeatureSet read from the data.
   * @throws ShapefileException if there is an error parsing the binaryZip data.
   */
  public static FeatureSet from(byte[] binaryZip) throws ShapefileException {
    try {
      ByteArrayInputStream bs = new ByteArrayInputStream(binaryZip);
      ShapefileReader reader = new ShapefileReader();
      reader.open(bs);
      FeatureSet fs = reader.getFeatures();
      return fs;
    } catch (FileNotFoundException ex) {
      throw new ShapefileException(ex);
    } catch (IOException ex) {
      throw new ShapefileException(ex);
    }
  }

  /**
   * The binary from the zipped shapefile.
   *
   * @param binaryZip The binary data to be read as if it were a zipped shapefile.
   * @param roundingInFeet This should be a positive double error in feet to round digits to. This will then estimate
   * the number of decimals in the projected coordinate system need to be and crop off digits accordingly.
   * @return The FeatureSet read from the data.
   * @throws ShapefileException If there was an error parsing the binary data.
   */
  public static FeatureSet from(byte[] binaryZip, double roundingInFeet)
      throws ShapefileException {
    try {
      ByteArrayInputStream bs = new ByteArrayInputStream(binaryZip);
      ShapefileReader reader = new ShapefileReader();
      reader.open(bs);
      FeatureSet fs = reader.getFeatures();
      fs.roundToFeet(roundingInFeet);
      return fs;
    } catch (FileNotFoundException ex) {
      throw new ShapefileException(ex);
    } catch (IOException ex) {
      throw new ShapefileException(ex);
    }
  }

  /**
   * Creates a FeatureSet from the specified shapefile or zip file.
   *
   * @param shpOrZipFile The path to the .shp file, or a .zip file containing all the necessary .shp, .shx, .dbf and
   * .prj files.
   * @return The FeatureSet to create.
   * @throws ShapefileException if the file could not be read.
   */
  public static FeatureSet from(File shpOrZipFile) throws ShapefileException {
    try {
      ShapefileReader reader = new ShapefileReader();
      reader.open(shpOrZipFile.getAbsolutePath());
      FeatureSet fs = reader.getFeatures();
      return fs;
    } catch (FileNotFoundException ex) {
      throw new ShapefileException(ex);
    } catch (IOException ex) {
      throw new ShapefileException(ex);
    }
  }

  /**
   * Creates a FeatureSet from the specified shapefile or zip file.
   *
   * @param shpOrZipFile The path to the .shp file, or a .zip file containing all the necessary .shp, .shx, .dbf and
   * .prj files.
   * @param roundingInFeet This should be a positive double error in feet to round digits to. This will then estimate
   * the number of decimals in the projected coordinate system need to be and crop off digits accordingly.
   * @return The FeatureSet to create.
   * @throws ShapefileException if the file could not be read.
   */
  public static FeatureSet from(File shpOrZipFile, double roundingInFeet)
      throws ShapefileException {
    try {
      ShapefileReader reader = new ShapefileReader();
      reader.open(shpOrZipFile.getAbsolutePath());
      FeatureSet fs = reader.getFeatures();
      fs.roundToFeet(roundingInFeet);
      return fs;
    } catch (IOException ex) {
      throw new ShapefileException(ex);
    }
  }

  public final FeatureSet intersection(FeatureSet other,
      ProjectedBuffer bufferOther, ProjectedBuffer bufferSelf, IntersectDelegate delegate) throws ProjectionException {
    FeatureSet result = new FeatureSet(getFeatureType());
    try {
      result.setDateFormat(dateFormat);
      result.setDefaultShapeType(defaultShapeType);
      result.setName(name + "_other");
      result.setProjectionFrom(projection);
      if (other == null) {
        throw new Exception("The other feature set cannot be unassigned!");
      }
      if (!this.getProjection().equals(other.getProjection())) {
        throw new Exception("The projection are not the same!");
      }
      result.setName(name + "_" + other.name);
      result.addFields(this.getFields());
      result.addMissingFields(other.getFields());
      if (delegate == null) {
        delegate = new IntersectDelegate(this) {
          @Override
          public void onIntersect(Feature feature, Feature otherFeature, Feature resultFeature) {
            try {
              resultFeature.addAttributes(feature.getAttributes());
              resultFeature.copyMissingAttributes(otherFeature);
            } catch (Exception ex) {
              ex.printStackTrace();
            }
          }
        };
      }
      delegate.setFeatureSet(this).setOtherSet(other);
      Feature featureCopied = null;
      Shape bufferedShape = null;
      Shape bufferedOtherShape = null;
      Optional<Shape> intersectedShape = null;
      Feature resultFeature = null;
      for (Feature feature : this.getFeatures()) {
        feature.setParent(this);
        if (this.needsRounding()) {
          feature.setShape(feature.getShape().round(this.getRounding()));
        }
        featureCopied = feature.copy();
        if (bufferSelf != null) {
          featureCopied = featureCopied.buffer(bufferSelf.getBufferProjected());
        }
        featureCopied.setParent(this);
        bufferedShape = this.fixNullShape(featureCopied.getShape());

        for (Feature otherFeature : other.getFeatures()) {
          try {
            otherFeature.setParent(other);
            if (other.needsRounding()) {
              otherFeature.setShape(otherFeature.getShape().round(other.getRounding()));
            }
            featureCopied = otherFeature.copy();
            if (bufferOther != null) {
              featureCopied = featureCopied.buffer(bufferOther.getBufferProjected());
            }
            featureCopied.setParent(other);
            bufferedOtherShape = this.fixNullShape(featureCopied.getShape());

            if (!bufferedShape.intersects(bufferedOtherShape)) {
              continue;
            }
            if (((intersectedShape = feature.getShape().intersection(otherFeature.getShape())) != null)
                && (intersectedShape.isPresent())) {
              resultFeature = new Feature();
              resultFeature.setShape(intersectedShape.get());
              resultFeature.getShape().calculateBounds();
              resultFeature.setParent(result);
              result.addSafely(resultFeature);

              delegate.onIntersect(feature, otherFeature, resultFeature);
            }
          } catch (Exception ex) {
            ex.printStackTrace();
            Logger
                .getLogger(this.getClass().getName())
                .log(Level.SEVERE, "feature => " + feature.getAttributes().toString()
                    + "\n" + "other feature => " + otherFeature.getAttributes().toString());
          }
        }
      }
      return result;
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return result;
  }

  /**
   * Gets the row attributes as an array of strings.
   *
   * @param row The integer row index of the attributes to get.
   * @return The String[] array.
   */
  public final String[] getAttributes(int row) {
    String[] result = new String[fields.size()];
    HashMap<String, String> atts = features.get(row).getAttributes();
    int iField = 0;
    for (Field field : fields) {
      if (!atts.containsKey(field.getName())) {
        continue;
      }
      String fullval = atts.get(field.getName());
      if (fullval.length() > field.getLength()) {
        fullval = fullval.substring(0, field.getLength());

      }
      result[iField] = padRight(fullval, field.getLength());
      iField++;
    }

    return result;
  }

  /**
   * Gets the text for the entire row (essentially all the fields in sequence) for the specified row index.
   *
   * @param row The integer row index of the field to get.
   * @return A String representing an entire row of fields appended together.
   */
  public final String getAttributeRow(int row) {
    String[] allFields = getAttributes(row);
    String result = " ";
    for (String field : allFields) {
      result += field;
    }
    return result;
  }

  /**
   * This generates a FeatureSet where all the shapes are unioned into a single shape.
   *
   * @return A single FeatureSet.
   */
  public final FeatureSet union() {
    FeatureSet result = null;
    try {
      if (this.getFeatures().size() < 2) {
        if (this.getFeatures().size() == 1) {
          this.getFeatures().get(0).getShape().calculateBounds();
        }
        return this;
      }
      //    Geometry union = null;
      List<Geometry> geometries = new ArrayList<>();
      Shape s = null;
      for (Feature f : getFeatures()) {
        s = f.getShape();
        if (this.needsRounding()) {
          s = s.round(this.getRounding());
        }
        Optional<Geometry> maybeGeom = Adapter.getGeometry(s);
        if (maybeGeom.isPresent()) {
          Geometry geom = maybeGeom.get();
          if (!geom.isValid()) {
            geom = JTSUtils.fix(geom);
          }
          geometries.add(geom);
        }
      }

      GeometryCollection geocollection = new GeometryCollection(geometries.toArray(new Geometry[0]), new GeometryFactory());
      //    Geometry union = CascadedPolygonUnion.union(geometries);
      //    Geometry union = geocollection.buffer(0);
      Geometry union = geocollection.union();
      result = new FeatureSet(getFeatureType());
      result.copyProperties(this, false);
      Feature resultFeature = new Feature();
      Shape shape = Adapter.getShape(union, hasM(), hasZ());
      resultFeature.setShape(shape);
      result.getFeatures().add(resultFeature);
//      result.setDefaultShapeType(defaultShapeType);
//      result.setProjectionFrom(projection);
//      result.setName(name);
//      result.setDateFormat(dateFormat);
      return result;
    } catch (Exception ex) {
      System.out.println(this.getFeatures().get(0).getAttributes());
      ex.printStackTrace();
    }
    return result;
  }

  /**
   * Rather than rounding to a scale, this allows the user to specify a distance in feet that values should be accurate
   * within, and uses the projection to calculate an appropriate scale to preserve that level of precision.
   *
   * @param distanceInFeet The distance in feet to be accurate to.
   */
  public final void roundToFeet(double distanceInFeet) {
    double scale = getScale(distanceInFeet);
    for (Feature feature : this.getFeatures()) {
      feature.setShape(feature.getShape().round(scale));
    }
    this.setPrecision(distanceInFeet);
  }

  /**
   * Rounds the shapes on all the features of this feature set to the specified scale.
   *
   * @param scale The scale to round to.
   */
  public final void round(double scale) {
    double multiplier = Math.pow(BASE_10, scale);

    for (Feature feature : this.getFeatures()) {
      feature.setShape(feature.getShape().round(multiplier));
    }
  }

  /**
   * Gets a double scale equivalent for the specified distance in feet.
   *
   * @param distanceInFeet The double distance in feet to convert into a scale multiplier to be used based on the
   * projection of this featureSet.
   * @return The scale multiplier.
   */
  public final double getScale(double distanceInFeet) {
    double projDistance = this.projection.feetToProj(distanceInFeet);
    if (projDistance <= 0) {
      return COURSE_PRECISION;
    }
    double lg = Math.log10(projDistance);
    int ilg = (int) lg;
    double mult = -ilg;
    return Math.pow(BASE_10, mult);
  }

  /**
   * This method dynamically calculates the bounds for the all the individual features.
   *
   * @return The Envelope bounds. This can be an empty envelope if there are no features, but will never be null.
   */
  @NonNull
  public final Envelope getEnvelope() {
    Envelope result = new DefaultEnvelope(hasM(), hasZ());
    for (Feature f : this.features) {
      result.expandToInclude(f.getEnvelope());
    }
    return result;
  }

  /**
   * Gets a feature category, like point, line, polygon or multipoint.
   *
   * @return The ShapeCategory for the shapes in this featureset.
   */
  public final ShapeCategory getFeatureCategory() {
    if (this.features.isEmpty()) {
      return defaultShapeType.getCategory();
    }
    Feature f = features.get(0);
    Shape shp = f.getShape();
    return shp.getShapeType().getCategory();
  }

  /**
   * Gets a feature type, describing both whether the features are points, lines, or polygons, and whether or not they
   * have M or Z coordinates. This overrides the default feature type.
   *
   * @return The ShapeType.
   */
  public final ShapeType getFeatureType() {
    for (Feature f : features) {
      if (f.getShape().getShapeType() == ShapeType.NullShape) {
        continue;
      }
      return f.getShape().getShapeType();
    }
    return ShapeType.NullShape;
  }

  /**
   * True if any of the shapes have Z values.
   *
   * @return Boolean, true if any shapes have Z.
   */
  public final boolean hasZ() {
    for (Feature f : this.features) {
      if (f.getShape().hasZ()) {
        return true;
      }
    }
    return false;
  }

  /**
   * True if any of the shapes have Z values.
   *
   * @return Boolean, true if any shapes have Z.
   */
  public final boolean hasM() {
    for (Feature f : this.features) {
      if (f.getShape().hasM()) {
        return true;
      }
    }
    return false;
  }

  /**
   * Combines multiple features into a single, multi-part feature. This preserves the boundaries, even if they overlap.
   * The attributes from the very first feature are preserved, but may no longer be an accurate representation of the
   * whole.
   *
   * @return FeatureSet The FeatureSet with the combined features.
   */
  public final FeatureSet merge() {
    FeatureSet result = new FeatureSet();
    result.setDefaultShapeType(defaultShapeType);
    result.fields.addAll(fields);
    result.setProjectionFrom(projection);
    result.name = name;
    Feature resultFeature = null;
    for (Feature feature : features) {
      if (resultFeature == null) {
        resultFeature = feature.copy();
      } else {
        for (Part part : feature.getShape().getParts()) {
          resultFeature.getShape().getParts().add(part);
        }
      }
    }
    result.getFeatures().add(resultFeature);
    return result;
  }

  /**
   * Combines multiple features with matching attributes into single features. The Field name of the attributes should
   * be specified.
   *
   * @param attribute The single string to use to merge attributes.
   * @return FeatureSet The FeatureSet with the combined features.
   */
  public final FeatureSet mergeByAttribute(String attribute) {
    List<String> attributes = new ArrayList<>();
    attributes.add(attribute);
    return mergeByAttribute(attributes);
  }

  /**
   * Combines multiple features with matching attributes into single features. The Field name of the attributes should
   * be specified. Any attributes not specified in the list will retain only the information from the first instance.
   *
   * @param attributes The list of field names that will be considered for building a "unique" id. To count as the same,
   * all of the specified attributes must be the same.
   * @return FeatureSet The FeatureSet with the combined features.
   */
  public final FeatureSet mergeByAttribute(List<String> attributes) {
    FeatureSet result = new FeatureSet();
    result.setDefaultShapeType(defaultShapeType);
    result.fields.addAll(fields);
    result.setProjectionFrom(projection);
    result.name = name;
    HashMap<String, Feature> lookup = new HashMap<>();
    for (Feature feature : features) {
      String key = "";
      for (String field : attributes) {
        if (!"".equals(key)) {
          field += "~";
        }
        if (feature.containsAttribute(field)) {
          field += feature.getAttributes().get(field);
        }
      }
      if (lookup.containsKey(key)) {
        Feature master = lookup.get(key);
        for (Part part : feature.getShape().getParts()) {
          master.getShape().getParts().add(part);
        }
      } else {
        lookup.put(key, feature.copy());
        result.getFeatures().add(feature);
      }
    }
    return result;
  }

  /**
   * Formats a string with a fixed number of spaces so that the text will be the correct2D length for the field.
   *
   * @param s the string value without padding.
   * @param n the integer number of spaces that should exist in the string.
   * @return A padded string of length n equivalent to the specified string, but with trailing spaces.
   */
  public final static String padRight(String s, int n) {
    return String.format("%1$-" + n + "s", s);
  }

  /**
   * If the current projection is null, this will throw an exception.
   *
   * @param targetProjection The destination projection.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If there was an error reprojecting.
   */
  public final void reproject(String targetProjection)
      throws gov.ca.water.shapelite.projection.ProjectionException {
    ProjectionInfo source = projection;
    ProjectionInfo dest = ProjectionInfo.fromEsriString(targetProjection);
    for (Feature f : features) {
      f.setShape(Reproject.reprojectShape(f.getShape(), source, dest));
    }
  }

  /**
   * If the current projection is null, this will throw an exception.
   *
   * @param targetProjection The destination projection.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If there was an error reprojecting.
   */
  public final void reproject(ProjectionInfo targetProjection) throws
      gov.ca.water.shapelite.projection.ProjectionException {
    if (this.projection == null) {
      throw new gov.ca.water.shapelite.projection.ProjectionException(
          "Cannot reproject if the current projection is not "
          + "defined.");
    }
    if (this.projection.equals(targetProjection)) {
      return;
    }
    ProjectionInfo source = projection;
    ProjectionInfo dest = targetProjection;
    for (Feature f : features) {
      f.setShape(Reproject.reprojectShape(f.getShape(), source, dest));
    }
    projection.copyProperties(targetProjection);
  }

  /**
   * Rather than reprojecting this FeatureSet, this method will create a copy of the FeatureSet with the new projection.
   *
   * @param targetProjection The ProjectionInfo to use to create a projection.
   * @return The new, reprojected featureset.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If there was an error reprojecting the coordinates.
   */
  public final FeatureSet reprojectCopy(ProjectionInfo targetProjection) throws ProjectionException {
    FeatureSet result = this.copy();
    result.reproject(targetProjection);
    return result;
  }

  /**
   * Get feature based on attribute name an value. Returns null if no match found. Caution: Will only return first found
   * match.
   *
   * @param fieldName The string field name to use to identify the feature.
   * @param value The String value to match in the string field.
   * @return An optional feature that is empty if a match is not found.
   */
  public final Optional<Feature> getFeature(String fieldName, String value) {
    if (fieldName != null) {
      for (Feature feature : this.features) {
        if (feature != null && feature.getAttributes() != null) {
          if (feature.getAttributes().containsKey(fieldName)) {
            if (feature.getAttributes().get(fieldName).equals(value)) {
              return Optional.of(feature);
            }
          }
        }
      }
    }
    return Optional.empty();
  }

  /**
   * Gets features based on attribute name an value. Returns empty list if no match found.
   *
   * @param fieldName The string field name to use to identify the feature.
   * @param value The String value to match in the string field.
   * @return An optional feature that is empty if a match is not found.
   */
  public final List<Feature> getFeatures(String fieldName, String value) {
    List<Feature> result = new ArrayList<>();
    for (Feature feature : this.features) {
      if (feature.getAttributes().get(fieldName).equals(value)) {
        result.add(feature);
      }
    }
    return result;
  }

  /**
   * Gets features based on attribute name an value. Returns empty list if no match found.
   *
   * @param fieldName The string field name to use to identify the feature.
   * @param values The list of String values to match in the string field.
   * @return An optional feature that is empty if a match is not found.
   */
  public final List<Feature> getFeatures(String fieldName, List<String> values) {
    List<Feature> result = new ArrayList<>();
    for (Feature feature : this.features) {
      for (String value : values) {
        if (feature.getAttributes().get(fieldName).equals(value)) {
          result.add(feature);
        }
      }

    }
    return result;
  }

  /**
   * Get feature based on attribute name an value. Returns empty list if no match found. Applies trimming and ignores
   * case on equality check.
   *
   * @param fieldName The string field name to use to identify the feature.
   * @param value The String value to match in the string field.
   * @return An optional feature that is empty if a match is not found.
   */
  public final List<Feature> getFeaturesCleanField(String fieldName, String value) {
    List<Feature> result = new ArrayList<>();
    for (Feature feature : this.features) {
      if (feature.getAttributes().get(fieldName).trim().equalsIgnoreCase(value)) {
        result.add(feature);
      }
    }
    return result;
  }

  /**
   * Attempts to find the specified field.
   *
   * @param name The String name of the field to return.
   * @return Optional with the field with the specified name, or an empty optional if the field is not found..
   */
  public final Optional<Field> findField(String name) {
    for (Field field : this.getFields()) {
      if (Objects.equals(field.getName(), name)) {
        return Optional.of(field);
      }
    }
    return Optional.empty();
  }

  /**
   * Converts this FeatureSet to the specified feature type. *Note. Converting to a point feature type will create many
   * more shapes, as each vertex from the source will be converted to a separate shape in the result.
   *
   * @param type The target ShapeType to create a new FeatureSet from this feature set.
   * @return The newly created FeatureSet of the specified shape type.
   */
  public final FeatureSet toShapeType(ShapeType type) {
    return FeaturesetTypeConverter.convert(this, type);
  }

  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * @return the dateFormat
   */
  public final SimpleDateFormat getDateFormat() {
    return dateFormat;
  }

  /**
   * @param dateFormat the dateFormat to set
   */
  public final void setDateFormat(SimpleDateFormat dateFormat) {
    this.dateFormat = dateFormat;
  }

  /**
   * Gets the shape type to use if the feature set is empty.
   *
   * @return the defaultShapeType
   */
  public final ShapeType getDefaultShapeType() {
    return defaultShapeType;
  }

  /**
   * Sets the shape type to use if the feature set is empty.
   *
   * @param aDefaultShapeType the defaultShapeType to set
   */
  public final void setDefaultShapeType(ShapeType aDefaultShapeType) {
    defaultShapeType = aDefaultShapeType;
  }

  /**
   * Gets the list of Fields that are in the attributes of all the features.
   *
   * @return the fields.
   */
  public final List<Field> getFields() {
    return fields;
  }

  /**
   * Sets the list of Fields that are in the attributes of all the features.
   *
   * @param fields the fields to set.
   */
  public final void setFields(List<Field> fields) {
    this.fields.clear();
    this.fields.addAll(fields);
  }

  /**
   * Gets the list of Features in this FeatureSet.
   *
   * @return the features.
   */
  public final List<Feature> getFeatures() {
    return features;
  }

  /**
   * Sets the list of Features in this FeatureSet.
   *
   * @param features the features to set.
   */
  public final void setFeatures(List<Feature> features) {
    this.features.clear();
    this.features.addAll(features);
  }

  /**
   * @return the fieldName
   */
  public final String getName() {
    return name;
  }

  /**
   * @param name the fieldName to set
   */
  public final void setName(String name) {
    this.name = name;
  }

  /**
   * @return the projection
   */
  public final String getProjectionESRI() {
    return projection.toEsriString();
  }

  /**
   * @param projection the ESRI projection String to set. This is nullable. If a null value is specified, then the
   * projection will be set to the default, which is WGS84.
   */
  public final void setProjectionESRI(@Nullable String projection) {
    if (projection == null) {
      this.projection.copyProperties(ProjectionInfo.getDefault());
    } else {
      ProjectionInfo source = ProjectionInfo.fromEsriString(projection);
      this.projection.copyProperties(source);
    }

  }

  /**
   * Gets the final projection object for this class. This will never be null.
   *
   * @return the projection
   */
  @NonNull
  public final ProjectionInfo getProjection() {
    return projection;
  }

  /**
   * Sets the projection by copying all the attributes from the source to this projection.
   *
   * @param sourceProjection The projection to copy.
   */
  public final void setProjectionFrom(ProjectionInfo sourceProjection) {
    projection.copyProperties(sourceProjection);
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Add Safely">
  public FeatureSet addSafely(FeatureSet fs) {
    try {
      Boolean reprojected = false;
      Shape reprojectedShape = null;
      this.getFields().addAll(fs.getFields());
      Feature copiedFeature = null;
      for (Feature f : fs.getFeatures()) {
        copiedFeature = f.copy();

        //<editor-fold defaultstate="collapsed" desc="Reprojection Logic As Needed">
        if (!this.getProjection().equals(fs.getProjection())) {
          reprojectedShape = Reproject.reprojectShape(copiedFeature.getShape(), fs.getProjection(), this.getProjection());

          if (reprojectedShape == null) {
            throw new NullPointerException("The shape projection returns null!");
          }

          copiedFeature.setShape(reprojectedShape);
          reprojected = true;
        }
        //</editor-fold>
        this.addSafely(copiedFeature);
      }

      //<editor-fold defaultstate="collapsed" desc="Finalize Reprojection Logics">
      if (reprojected) {
        if (this.getProjection() != null && fs.getProjection() != null) {
          fs.getProjection().copyProperties(this.getProjection());
        }
      }
      //</editor-fold>
    } catch (Exception e) {
      throw new IllegalStateException(
          String.format("%s.%s %s:\n%s", this.getClass().getName(), "addSafely(FeatureSet)", e.getClass().getName(), e.getMessage()
          )
      );
    }

    return this;
  }

  public FeatureSet addSafely(Feature feature) {
    try {
      if (!this.getDefaultShapeType().equals(feature.getShape().getShapeType())) {
        FeaturesetTypeConverter.convert(feature, this.getDefaultShapeType());
      }
      this.getFeatures().add(feature);
    } catch (Exception e) {
      throw new IllegalStateException(
          String.format("%s.%s %s:\n%s", this.getClass().getName(), "addSafely(Feature)", e.getClass().getName(), e.getMessage()
          )
      );
    }

    return this;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Get Area in Acre Unit">
  /**
   * Return the shape area in Acre units.
   *
   * @return Double
   */
  public Double getArea() {
    Double result = 0.0;
    List<Part> parts = null;
    Shape sh = null;
    FeatureSet fs = this.union();
    for (Feature f : fs.getFeatures()) {
      if ((sh = f.getShape()) == null
          || ((parts = sh.getParts()) == null)
          || parts.isEmpty()) {
        continue;//skip this
      }
      for (Part p : parts) {
        result += p.area();
      }
    }

    return result;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Needs Rounding">
  public Boolean needsRounding() {
    return this.rounding != null;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Set Rounding">
  public FeatureSet setRounding(Integer decimalplaces) {
    if (decimalplaces == null) {
      this.rounding = null;
    } else {
      this.rounding = Math.pow(BASE_10, decimalplaces);
    }
    return this;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Get Rounding">
  public Double getRounding() {
    return this.rounding;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="List Implementations">
  /*
  @Override
  public int size() {
    return this.features.size();
  }

  @Override
  public boolean contains(Object o) {
    return this.features.contains(o);
  }

  @Override
  public Iterator<Feature> iterator() {
    return this.features.iterator();
  }

  @Override
  public Object[] toArray() {
    return this.features.toArray();
  }

  @Override
  public <T> T[] toArray(T[] a) {
    return this.features.toArray(a);
  }

  @Override
  public boolean add(Feature e) {
    return this.features.add(e);
  }

  @Override
  public boolean remove(Object o) {
    return this.features.remove(o);
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return this.features.containsAll(c);
  }

  @Override
  public boolean addAll(Collection<? extends Feature> c) {
    return this.features.addAll(c);
  }

  @Override
  public boolean addAll(int index, Collection<? extends Feature> c) {
    return this.features.addAll(index, c);
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    return this.features.removeAll(c);
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    return this.features.retainAll(c);
  }

  @Override
  public void clear() {
    this.features.clear();
  }

  @Override
  public Feature get(int index) {
    return this.features.get(index);
  }

  @Override
  public Feature set(int index, Feature element) {
    return this.features.set(index, element);
  }

  @Override
  public void add(int index, Feature element) {
    this.features.add(index, element);
  }

  @Override
  public Feature remove(int index) {
    return this.features.remove(index);
  }

  @Override
  public int indexOf(Object o) {
    return this.features.indexOf(o);
  }

  @Override
  public int lastIndexOf(Object o) {
    return this.features.lastIndexOf(o);
  }

  @Override
  public ListIterator<Feature> listIterator() {
    return this.features.listIterator();
  }

  @Override
  public ListIterator<Feature> listIterator(int index) {
    return this.features.listIterator(index);
  }

  @Override
  public List<Feature> subList(int fromIndex, int toIndex) {
    return this.features.subList(fromIndex, toIndex);
  }/**/
  //</editor-fold>
}
