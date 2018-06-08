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
package gov.ca.water.shapelite.gsa;

import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Field;
import gov.ca.water.shapelite.FieldType;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.ShapefileException;
import gov.ca.water.shapelite.io.ShapefileReader;
import gov.ca.water.shapelite.io.ShapefileWriter;
import gov.ca.water.shapelite.projection.ProjectionException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class GSAMasterUtils {

  /**
   * The default GSA Id field.
   */
  private static final String DEFAULT_GSA_ID = "GSA_ID";

  /**
   * The character length of the field.
   */
  private static final int DEFAULT_FIELD_LENGTH = 50;

  /**
   * Private constructor for utils class.
   */
  private GSAMasterUtils() {

  }

  // <editor-fold defaultstate="collapsed" desc="Add">
  /**
   * Add the GSA shapes and assigns the specified id. If there are multiple
   * features in the gsaStream, they will be merged into a single feature before
   * being assigned.
   *
   * @param gsaId The String ID of the GSA ID to update.
   * @param gsaStream The InputStream of the zipped .shp, .shx, .dbf, .prj
   * files., where all the shapes will be given the gsaId value in the
   * gsaIdField attribute and added to the master.
   * @param masterStream The InputStream of the existing combined GSA zipped
   * .shp, .shx, .dbf, .prj files.
   * @param updatedMaster The OutputStream on which a zipped .shp, .shx, .dbf,
   * and .prj file will be written with the updated master shapefile content.
   * @throws java.io.IOException If there was an error parsing the file.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if there was
   * an error reprojecting the shapes for intersection.
   * @throws gov.ca.water.shapelite.ShapefileException If there was an error
   * reading or writing shapefiles.
   */
  public static void add(String gsaId, InputStream gsaStream,
      InputStream masterStream, OutputStream updatedMaster)
      throws IOException, ProjectionException, ShapefileException {
    add(gsaId, gsaStream, masterStream, updatedMaster, DEFAULT_GSA_ID);
  }

  /**
   * Add the GSA shapes and assigns the specified id. If there are multiple
   * features in the gsaStream, they will be merged into a single feature before
   * being assigned.
   *
   * @param gsaId The String ID of the GSA ID to update.
   * @param gsaStream The InputStream of the zipped .shp, .shx, .dbf, .prj
   * files., where all the shapes will be given the gsaId value in the
   * gsaIdField attribute and added to the master.
   * @param masterStream The InputStream of the existing combined GSA zipped
   * .shp, .shx, .dbf, .prj files.
   * @param updatedMaster The OutputStream on which a zipped .shp, .shx, .dbf,
   * and .prj file will be written with the updated master shapefile content.
   * @param gsaIdField The ID field to use to assign the specified gsaID.
   * @throws java.io.IOException If there was an error parsing the file.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if there was
   * an error reprojecting the shapes for intersection.
   * @throws gov.ca.water.shapelite.ShapefileException If there was an error
   * reading or writing shapefiles.
   */
  public static void add(String gsaId, InputStream gsaStream,
      InputStream masterStream, OutputStream updatedMaster, String gsaIdField)
      throws IOException, ProjectionException, ShapefileException {

    ShapefileReader reader = new ShapefileReader();
    reader.open(gsaStream);
    FeatureSet gsa = reader.getFeatures();
    reader.open(masterStream);
    FeatureSet master = reader.getFeatures();
    add(gsaId, gsa, master, gsaIdField);
    ShapefileWriter writer = new ShapefileWriter();
    writer.write(master, updatedMaster);
  }

  /**
   * Add the GSA shapes and assigns the specified id. If there are multiple
   * features in the gsaStream, they will be merged into a single feature before
   * being assigned.
   *
   * @param gsaId The String ID of the GSA ID to update using the default
   * "GSA_ID" field name.
   * @param gsa The FeatureSet to add. If this is null, this just returns
   * whatever was passed in as the existing master. If there is more than one
   * feature in the gsaStream, they will be merged into a single feature before
   * being assigned.
   * @param master The existing combined FeatureSet. This can be null.
   * @return The updated or created master FeatureSet with the added gsa shapes.
   * @throws java.io.IOException If there was an error parsing the file.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if there was
   * an error reprojecting the shapes for intersection.
   * @throws gov.ca.water.shapelite.ShapefileException If there was an error
   * reading or writing shapefiles.
   */
  public static FeatureSet add(String gsaId, FeatureSet gsa, FeatureSet master)
      throws IOException, ProjectionException, ShapefileException {
    return add(gsaId, gsa, master, DEFAULT_GSA_ID);
  }

  /**
   * Add the GSA shapes and assigns the specified id. If there are multiple
   * features in the gsaStream, they will be merged into a single feature before
   * being assigned.
   *
   * @param gsaId The String ID of the GSA ID to update.
   * @param gsa The FeatureSet to add. If this is null, this just returns
   * whatever was passed in as the existing master.
   * @param master The existing combined FeatureSet. This can be null.
   * @param gsaIdField The ID field to use to assign the specified gsaID.
   * @return The updated master shapefile.
   * @throws java.io.IOException If there was an error parsing the file.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if there was
   * an error reprojecting the shapes for intersection.
   * @throws gov.ca.water.shapelite.ShapefileException If there was an error
   * reading or writing shapefiles.
   */
  public static FeatureSet add(String gsaId, FeatureSet gsa, FeatureSet master,
      String gsaIdField)
      throws IOException, ProjectionException, ShapefileException {
    if (master == null) {
      master = new FeatureSet(gsa.getDefaultShapeType());
    }
    if (gsa == null || gsa.isEmpty() || gsa.getFeatureType() == ShapeType.NullShape) {
      return master;
    }
    FeatureSet insert = gsa.copy();
    if (!insert.getProjection().equals(master.getProjection())) {
      insert.reproject(master.getProjection());
    }
    insert = insert.merge();
    if (master.isEmpty()) {
      master.setDefaultShapeType(insert.getDefaultShapeType());
    }
    if (!insert.containsFieldNamed(gsaIdField)) {
      insert.getFields().add(new Field(gsaIdField,
          FieldType.Character, DEFAULT_FIELD_LENGTH));
      for (Feature feature : insert.getFeatures()) {
        feature.getAttributes().put(gsaIdField, gsaId);
      }
    }
    master.addMissingFields(insert.getFields());
    master.getFeatures().addAll(insert.getFeatures());
    return master;
  }

  // </editor-fold>
  // <editor-fold dec="addMultiple">
  /**
   * Add the GSA shapes and assigns the specified id. If multiple features exist
   * in the gsaStream, they will all be added.
   *
   * @param gsaId The String ID of the GSA ID to update.
   * @param gsaStream The InputStream of the zipped .shp, .shx, .dbf, .prj
   * files., where all the shapes will be given the gsaId value in the
   * gsaIdField attribute and added to the master.
   * @param masterStream The InputStream of the existing combined GSA zipped
   * .shp, .shx, .dbf, .prj files.
   * @param updatedMaster The OutputStream on which a zipped .shp, .shx, .dbf,
   * and .prj file will be written with the updated master shapefile content.
   * @throws java.io.IOException If there was an error parsing the file.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if there was
   * an error reprojecting the shapes for intersection.
   * @throws gov.ca.water.shapelite.ShapefileException If there was an error
   * reading or writing shapefiles.
   */
  public static void addMultiple(String gsaId, InputStream gsaStream,
      InputStream masterStream, OutputStream updatedMaster)
      throws IOException, ProjectionException, ShapefileException {
    add(gsaId, gsaStream, masterStream, updatedMaster, DEFAULT_GSA_ID);
  }

  /**
   * Add the GSA shapes and assigns the specified id. If multiple features exist
   * in the gsaStream, they will all be added.
   *
   * @param gsaId The String ID of the GSA ID to update.
   * @param gsaStream The InputStream of the zipped .shp, .shx, .dbf, .prj
   * files., where all the shapes will be given the gsaId value in the
   * gsaIdField attribute and added to the master.
   * @param masterStream The InputStream of the existing combined GSA zipped
   * .shp, .shx, .dbf, .prj files.
   * @param updatedMaster The OutputStream on which a zipped .shp, .shx, .dbf,
   * and .prj file will be written with the updated master shapefile content.
   * @param gsaIdField The ID field to use to assign the specified gsaID.
   * @throws java.io.IOException If there was an error parsing the file.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if there was
   * an error reprojecting the shapes for intersection.
   * @throws gov.ca.water.shapelite.ShapefileException If there was an error
   * reading or writing shapefiles.
   */
  public static void addMultiple(String gsaId, InputStream gsaStream,
      InputStream masterStream, OutputStream updatedMaster, String gsaIdField)
      throws IOException, ProjectionException, ShapefileException {

    ShapefileReader reader = new ShapefileReader();
    reader.open(gsaStream);
    FeatureSet gsa = reader.getFeatures();
    reader.open(masterStream);
    FeatureSet master = reader.getFeatures();
    add(gsaId, gsa, master, gsaIdField);
    ShapefileWriter writer = new ShapefileWriter();
    writer.write(master, updatedMaster);
  }

  /**
   * Add the GSA shapes and assigns the specified id. If multiple features exist
   * in the gsaStream, they will all be added.
   *
   * @param gsaId The String ID of the GSA ID to update using the default
   * "GSA_ID" field name.
   * @param gsa The FeatureSet to add. If this is null, this just returns
   * whatever was passed in as the existing master.
   * @param master The existing combined FeatureSet. This can be null.
   * @return The updated or created master FeatureSet with the added gsa shapes.
   * @throws java.io.IOException If there was an error parsing the file.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if there was
   * an error reprojecting the shapes for intersection.
   * @throws gov.ca.water.shapelite.ShapefileException If there was an error
   * reading or writing shapefiles.
   */
  public static FeatureSet addMultiple(String gsaId, FeatureSet gsa, FeatureSet master)
      throws IOException, ProjectionException, ShapefileException {
    return add(gsaId, gsa, master, DEFAULT_GSA_ID);
  }

  /**
   * Add the GSA shapes and assigns the specified id. If multiple features exist
   * in the gsaStream, they will all be added.
   *
   * @param gsaId The String ID of the GSA ID to update.
   * @param gsa The FeatureSet to add. If this is null, this just returns
   * whatever was passed in as the existing master.
   * @param master The existing combined FeatureSet. This can be null.
   * @param gsaIdField The ID field to use to assign the specified gsaID.
   * @return The updated master shapefile.
   * @throws java.io.IOException If there was an error parsing the file.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if there was
   * an error reprojecting the shapes for intersection.
   * @throws gov.ca.water.shapelite.ShapefileException If there was an error
   * reading or writing shapefiles.
   */
  public static FeatureSet addMultiple(String gsaId, FeatureSet gsa,
      FeatureSet master, String gsaIdField)
      throws IOException, ProjectionException, ShapefileException {
    if (master == null) {
      master = new FeatureSet(gsa.getDefaultShapeType());
    }
    if (gsa == null || gsa.isEmpty() || gsa.getFeatureType() == ShapeType.NullShape) {
      return master;
    }
    FeatureSet insert = gsa.copy();
    if (!insert.getProjection().equals(master.getProjection())) {
      insert.reproject(master.getProjection());
    }
    if (master.isEmpty()) {
      master.setDefaultShapeType(insert.getDefaultShapeType());
    }
    if (!insert.containsFieldNamed(gsaIdField)) {
      insert.getFields().add(new Field(gsaIdField,
          FieldType.Character, DEFAULT_FIELD_LENGTH));
      for (Feature feature : insert.getFeatures()) {
        feature.getAttributes().put(gsaIdField, gsaId);
      }
    }
    master.addMissingFields(insert.getFields());
    master.getFeatures().addAll(insert.getFeatures());
    return master;
  }

  // </editor-fold>
  // <editor-fold defaultstate="collapsed" desc="Remove">
  /**
   * Removes features from the GSA master file that have the specified ID value
   * in the "GSA_ID" field.
   *
   * @param idValue The String value to match for removing features.
   * @param master The master InputStream to remove values from.
   * @param result The OutputStream to write the resulting, updated feature to.
   * @return boolean that is true if the idValue was found and removed.
   * @throws java.io.IOException if there is an error reading or writing to the
   * streams.
   * @throws gov.ca.water.shapelite.ShapefileException if there is a problem
   * with writing the shapefile format.
   */
  public static boolean removeGSA(String idValue, InputStream master,
      OutputStream result)
      throws IOException, ShapefileException {
    return remove(idValue, master, result, DEFAULT_GSA_ID);
  }

  /**
   * Removes features from the GSA master file that have the specified ID value
   * in the "GSA_ID" field.
   *
   * @param idValues The list of String values to remove.
   * @param master The master InputStream to remove values from.
   * @param result The OutputStream to write the resulting, updated feature to.
   * @return The list of String idValeus that were found and removed.
   * @throws java.io.IOException if there is an error reading or writing to the
   * streams.
   * @throws gov.ca.water.shapelite.ShapefileException if there is a problem
   * with writing the shapefile format.
   */
  public static List<String> removeGSA(List<String> idValues, InputStream master,
      OutputStream result)
      throws IOException, ShapefileException {
    return remove(idValues, master, result, DEFAULT_GSA_ID);
  }

  /**
   * Removes features from the GSA master file that have the specified ID value
   * in the "GSA_ID" field.
   *
   * @param idValue the String value to remove.
   * @param masterStream The master InputStream to remove values from.
   * @param updatedMaster The OutputStream to write the resulting, updated
   * feature to.
   * @param idField The Field name to match the idValue from.
   * @return boolean that is true if the idValue was found and removed.
   * @throws java.io.IOException if there is an error reading or writing to the
   * streams.
   * @throws gov.ca.water.shapelite.ShapefileException if there is a problem
   * with writing the shapefile format.
   */
  public static boolean remove(String idValue, InputStream masterStream,
      OutputStream updatedMaster, String idField) throws IOException,
      ShapefileException {
    List<String> idValues = new ArrayList<>();
    idValues.add(idValue);
    return !remove(idValues, masterStream, updatedMaster, idField).isEmpty();
  }

  /**
   * Removes features from the GSA master file that have the specified ID value
   * in the "GSA_ID" field.
   *
   * @param idValues The list of String values to remove.
   * @param masterStream The master InputStream to remove values from.
   * @param updatedMaster The OutputStream to write the resulting, updated
   * feature to.
   * @param idField The Field name to match the idValue from.
   * @return The list of ID values that were found and removed.
   * @throws java.io.IOException if there is an error reading or writing to the
   * streams.
   * @throws gov.ca.water.shapelite.ShapefileException if there is a problem
   * writing the shapefile format.
   */
  public static List<String> remove(List<String> idValues, InputStream masterStream,
      OutputStream updatedMaster, String idField) throws IOException,
      ShapefileException {
    List<String> result = new ArrayList<>();
    ShapefileReader reader = new ShapefileReader();
    reader.open(masterStream);
    FeatureSet master = reader.getFeatures();
    for (String id : idValues) {
      String trim = id.trim(); // remove any white space.
      List<Feature> features = master.remove(idField, trim);
      if (!features.isEmpty()) {
        result.add(trim);
      }
    }
    ShapefileWriter writer = new ShapefileWriter();
    writer.write(master, updatedMaster);
    return result;
  }

  // </editor-fold>
}
