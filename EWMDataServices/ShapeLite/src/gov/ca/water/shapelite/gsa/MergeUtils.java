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
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeCategory;
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
public final class MergeUtils {

  /**
   * The ID field length.
   */
  private static final int ID_LENGTH = 50;

  /**
   * Maximum field length in dbf.
   */
  private static final int FIELD_LENGTH = 255;

  /**
   * Private constructor for utils class.
   */
  private MergeUtils() {

  }

  /**
   * This treats each input stream as a separate zipped shapefile and writes the
   * content of a zipped shapefile to the mergedOutput.
   *
   * @param inputStreams The input streams. If this list is empty, then an empty
   * featureset will be written.
   * @param mergedOutput The output stream to write to.
   * @param gsaID The GSA Id value to assign to all of the shapes.
   * @param gsaName The GSA Name value to assign to all of the shapes.
   * @throws java.io.IOException if there was an error reading or writing to
   * streams.
   * @throws gov.ca.water.shapelite.ShapefileException if there is an error in
   * the shapefile formation of the output file to be created.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If some of
   * the input streams are in a different projection from the first in the list
   * and they can't correctly be reprojected.
   */
  public static void merge(List<InputStream> inputStreams, OutputStream mergedOutput,
      String gsaID, String gsaName) throws IOException, ShapefileException,
      ProjectionException {
    ShapefileReader reader = new ShapefileReader();
    List<FeatureSet> featureSets = new ArrayList<>();
    for (InputStream stream : inputStreams) {
      reader.open(stream);
      FeatureSet fs = reader.getFeatures();
      featureSets.add(fs);
    }
    FeatureSet resultFs = merge(featureSets, gsaID, gsaName);
    ShapefileWriter writer = new ShapefileWriter();
    writer.write(resultFs, mergedOutput);
  }

  /**
   * This will merge the shapes from the separate feature sets into a single
   * feature set. The attributes from the original feature sets are discarded.
   * The GSA_ID, GSA_Name, and Ag_Name are added, where the GSA_ID and GSA_NAME
   * are from the specified parameters, while the Ag_Name is the source name. If
   * the Shape categories match, but M/Z properties don't, then if Z exists in
   * any, the output will be Z type. Otherwise, if M exists in any input
   * featureSets, M will be included in the output feature type.
   *
   * @param featureSets The original feature Sets.
   * @param gsaID The GSA Id value to assign to all of the shapes.
   * @param gsaName The GSA Name value to assign to all of the shapes.
   * @return The FeatureSet of the combined shapes.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If some of
   * the input streams are in a different projection from the first in the list
   * and they can't correctly be reprojected.
   */
  public static FeatureSet merge(List<FeatureSet> featureSets,
      String gsaID, String gsaName) throws ProjectionException {

    FeatureSet result = new FeatureSet();
    result.getFields().add(new Field("GSA_ID", FieldType.Character, ID_LENGTH));
    result.getFields().add(new Field("GSA_Name", FieldType.Character, FIELD_LENGTH));
    result.getFields().add(new Field("Ag_Name", FieldType.Character, FIELD_LENGTH));
    boolean hasZ = false;
    boolean hasM = false;
    if (!featureSets.isEmpty()) {
      result.setProjectionFrom(featureSets.get(0).getProjection());
      ShapeCategory category = null;
      for (FeatureSet fs : featureSets) {
        if (fs.getFeatureType().hasZ()) {
          hasZ = true;
        }
        if (fs.getFeatureType().hasM()) {
          hasM = true;
        }
        if (category == null) {
          category = fs.getFeatureCategory();
        } else if (!category.equals(fs.getFeatureCategory())
            && !ShapeCategory.Null.equals(fs.getFeatureCategory())) {
          throw new IllegalArgumentException("Cannot merge type " + category
              + " with type " + fs.getFeatureCategory()
              + ".  Shape categories must match.");
        }
      }
      ShapeType type = ShapeType.fromCategory(category, hasZ, hasM);
      result.setDefaultShapeType(type);
      for (FeatureSet originalFeatureSet : featureSets) {
        FeatureSet projFeatureSet = originalFeatureSet;
        if (!originalFeatureSet.getProjection().equals(result.getProjection())) {
          projFeatureSet = originalFeatureSet.copy();
          projFeatureSet.reproject(result.getProjection());
        }
        for (Feature originalFeature : projFeatureSet.getFeatures()) {
          Shape shape = originalFeature.getShape().copy(type.getCoordType());
          Feature resultFeature = new Feature(shape);
          resultFeature.getAttributes().put("GSA_ID", gsaID);
          resultFeature.getAttributes().put("GSA_Name", gsaName);
          resultFeature.getAttributes().put("Ag_Name", originalFeatureSet.getName());
          result.getFeatures().add(resultFeature);
        }
      }
    }

    return result;
  }

  /**
   * Merges all the features that have the same value in the attribute field
   * into a single feature, where the combined result will have the attributes
   * listed, and the values for any other attributes will be from the first
   * shape found.
   *
   * @param stream The input stream with a zipped shapefile dataset.
   * @param merged The output stream for the merged zipped shapefile.
   * @param attribute The String attribute name to use as a unique identifier.
   * If the attribute is not found in the FeatureSet, the result will be a copy
   * of the original.
   * @throws IOException If there was an error reading or writing to the
   * streams.
   * @throws ShapefileException If there was an error writing the output
   * shapefile format.
   */
  public static void mergeFeaturesByAttribute(InputStream stream,
      OutputStream merged, String attribute) throws IOException, ShapefileException {
    List<String> attributes = new ArrayList<>();
    attributes.add(attribute);
    mergeFeaturesByAttribute(stream, merged, attributes);
  }

  /**
   * Merges all the features that have the same value in the attribute field
   * into a single feature, where the combined result will have the attributes
   * listed, and the values for any other attributes will be from the first
   * shape found.
   *
   * @param stream The input stream with a zipped shapefile dataset.
   * @param merged The output stream for the merged zipped shapefile.
   * @param attributes The List of string attribute names to include in the
   * unique identifier. Attributes that are not found in the FeatureSet are
   * ignored.
   * @throws IOException If there was an error reading or writing to the
   * streams.
   * @throws ShapefileException If there was an error writing the output
   * shapefile format.
   */
  public static void mergeFeaturesByAttribute(InputStream stream,
      OutputStream merged, List<String> attributes) throws IOException, ShapefileException {
    ShapefileReader reader = new ShapefileReader();
    reader.open(stream);
    FeatureSet fs = reader.getFeatures();
    fs.mergeByAttribute(attributes);
    ShapefileWriter writer = new ShapefileWriter();
    writer.write(fs, merged);

  }

}
