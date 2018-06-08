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
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.io.ShapefileReader;
import gov.ca.water.shapelite.projection.ProjectionException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Intersects a GSA feature set with a shapefile of basin features. A separate
 * output FeatureSet will be created for each basin. Only one feature will be in
 * each output featureset. The output featureSets will be named for the basin.
 * The feature will be the union of all the intersections with a particular
 * basin.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class SplitIntersectingUtils {

  /**
   * The basin ID.
   */
  public static final String DEFAULT_ID = "ID";

  /**
   * The default buffer distance is 100 feet.
   */
  public static final double DEFAULT_BUFFER_DISTANCE = -100;

  /**
   * Creates a new instance of the GSA Polygon Intersector. Private for utils
   */
  private SplitIntersectingUtils() {

  }

  // <editor-fold defaultstate="collapsed" desc="findIntersectingBasinIds">
  /**
   *
   * @param target An InputStream containing a single zipped .shp, .shx, .dbf
   * and .prj file.
   * @param base An InputStream containing a single zipped .shp, .shx, .dbf and
   * .prj file.
   * @return The list of String values from the Basin_ID column where
   * intersection occurs or else an empty list.
   * @throws java.io.IOException Thrown if there is an error parsing the input
   * streams.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If the two
   * projections are not the same, but reprojection fails.
   */
  public static List<String> findIntersectingIds(InputStream target,
      InputStream base) throws IOException, ProjectionException {
    return findIntersectingIds(target, base, DEFAULT_ID);
  }

  /**
   *
   * @param target An InputStream containing a single zipped .shp, .shx, .dbf
   * and .prj file.
   * @param base An InputStream containing a single zipped .shp, .shx, .dbf and
   * .prj file.
   * @param bufferDistance The distance around the basin in US Feet to use to
   * count the shape as intersecting it. If this is positive, intersecting
   * becomes easier, if this is negative, intersecting becomes harder.
   * @return The list of String values from the Basin_ID column where
   * intersection occurs or else an empty list.
   * @throws java.io.IOException Thrown if there is an error parsing the input
   * streams.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If the two
   * projections are not the same, but reprojection fails.
   */
  public static List<String> findIntersectingIds(InputStream target,
      InputStream base, double bufferDistance) throws IOException,
      ProjectionException {
    return findIntersectingIds(target, base, DEFAULT_ID, bufferDistance);
  }

  /**
   *
   * @param target An InputStream containing a single zipped .shp, .shx, .dbf
   * and .prj file.
   * @param base An InputStream containing a single zipped .shp, .shx, .dbf and
   * .prj file.
   * @param idField The String fieldName of the values to return.
   * @return The list of String values from the specified basinField column
   * where intersection occurs or else an empty list.
   * @throws java.io.IOException Thrown if there is an error parsing the input
   * streams.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If the two
   * projections are not the same, but reprojection fails.
   */
  public static List<String> findIntersectingIds(InputStream target,
      InputStream base, String idField) throws IOException, ProjectionException {
    ShapefileReader reader = new ShapefileReader();
    reader.open(target);
    FeatureSet targetFeatures = reader.getFeatures();
    reader.open(base);
    FeatureSet basinFeatures = reader.getFeatures();
    return findIntersectingIds(targetFeatures, basinFeatures, idField,
        DEFAULT_BUFFER_DISTANCE);

  }

  /**
   *
   * @param target An InputStream containing a single zipped .shp, .shx, .dbf
   * and .prj file.
   * @param base An InputStream containing a single zipped .shp, .shx, .dbf and
   * .prj file.
   * @param idField The String fieldName of the values to return.
   * @param bufferDistance The distance around the basin in US Feet to use to
   * count the shape as intersecting it. If this is positive, intersecting
   * becomes easier, if this is negative, intersecting becomes harder.
   * @return The list of String values from the specified basinField column
   * where intersection occurs or else an empty list.
   * @throws java.io.IOException Thrown if there is an error parsing the input
   * streams.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If the two
   * projections are not the same, but reprojection fails.
   */
  public static List<String> findIntersectingIds(InputStream target,
      InputStream base, String idField, double bufferDistance) throws IOException,
      ProjectionException {
    ShapefileReader reader = new ShapefileReader();
    reader.open(target);
    FeatureSet targetFeatures = reader.getFeatures();
    reader.open(base);
    FeatureSet basinFeatures = reader.getFeatures();
    return findIntersectingIds(targetFeatures, basinFeatures, idField, bufferDistance);

  }

  /**
   * Find Intersecting Basin Ids.
   *
   * @param target The FeatureSet containing the shapes to find intersections
   * with basins.
   * @param base The FeatureSet containing the shapes to intersect with.
   * @return The list of String values from the other ID column where
   * intersection occurs or else an empty list.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If the two
   * projections are not the same, but reprojection fails.
   */
  public static List<String> findIntersectingIds(FeatureSet target,
      FeatureSet base) throws ProjectionException {
    return findIntersectingIds(target, base, DEFAULT_ID, DEFAULT_BUFFER_DISTANCE);
  }

  /**
   * Find Intersecting Basin Ids.
   *
   * @param target The FeatureSet containing the shapes to find intersections
   * with basins.
   * @param base The FeatureSet containing the basins to intersect with.
   * @param idField The String fieldName of the values to return.
   * @return The list of String values from the ID column where intersection
   * occurs or else an empty list.
   * @param bufferDistance The distance around the basin in US Feet to use to
   * count the shape as intersecting it. If this is positive, intersecting
   * becomes easier, if this is negative, intersecting becomes harder.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If the two
   * projections are not the same, but reprojection fails.
   */
  public static List<String> findIntersectingIds(FeatureSet target,
      FeatureSet base, String idField, double bufferDistance) throws ProjectionException {
    //FeatureSet intersect = target.intersection(target);
    List<String> distinctValues = new ArrayList<>();
    FeatureSet projTarget = target;
    if (!target.getProjection().equals(base.getProjection())) {
      projTarget = target.copy();
      projTarget.reproject(base.getProjection());
    }
    for (Feature baseFeature : base.getFeatures()) {
      String id = null;
      if (baseFeature.getAttributes().containsKey(idField)) {
        id = baseFeature.getAttributes().get(idField);
      }
      for (Feature item : projTarget.getFeatures()) {
        if (bufferDistance != 0) {
          double dist = base.getProjection().feetToProj(bufferDistance);
          Feature buffer = baseFeature.buffer(dist);
          if (!buffer.getShape().isEmpty()) {
            if (buffer.intersects(item)) {
              if (!distinctValues.contains(id)) {
                distinctValues.add(id);
              }
            }
          }
        } else if (baseFeature.intersects(item)) {
          if (!distinctValues.contains(id)) {
            distinctValues.add(id);
          }
        }
      }
    }
    return distinctValues;
  }

  // </editor-fold>
  // <editor-fold defaultstate="collapsed" desc="Split by Intersecting">
  /**
   * This will perform the intersection of the base shapes with the clip shapes.
   * Any and All GSA shape intersections will be unioned into a single feature.
   *
   * @param baseShapefile The String full path of the .shp file, or a .zip file
   * that contains the .shp, .shx, .dbf and .prj files representing the basin.
   * @param clipShapefile The String full path of the .shp file, or a .zip file
   * that contains the .shp, .shx, .dbf and .prj files representing the GSA
   * shapes.
   * @param baseIdFieldName The Field name to use to name the output
   * featuresets.
   * @return A list of separated basin shapes.
   * @throws java.io.IOException If there was an error reading the file.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if the two
   * projections could not be reconciled as being equal or adjusted to match by
   * reprojection.
   */
  public static List<FeatureSet> splitIntersecting(String baseShapefile,
      String clipShapefile, String baseIdFieldName) throws IOException,
      ProjectionException {
    ShapefileReader reader = new ShapefileReader();
    reader.open(clipShapefile);
    FeatureSet gsa = reader.getFeatures();
    reader.open(baseShapefile);
    FeatureSet basins = reader.getFeatures();
    return splitIntersecting(basins, gsa, baseIdFieldName,
        DEFAULT_BUFFER_DISTANCE, null);
  }

  /**
   * This will perform the intersection of the base shapes with the clip shapes.
   * Any and All GSA shape intersections will be unioned into a single feature.
   *
   * @param baseShapefile The String full path of the .shp file, or a .zip file
   * that contains the .shp, .shx, .dbf and .prj files representing the basin.
   * @param clipShapefile The String full path of the .shp file, or a .zip file
   * that contains the .shp, .shx, .dbf and .prj files representing the GSA
   * shapes.
   * @param baseIdFieldName The Field name to use to name the output
   * featuresets.
   * @param bufferDistance The distance around the basin in US Feet to use to
   * count the shape as intersecting it. If this is positive, intersecting
   * becomes easier, if this is negative, intersecting becomes harder. The
   * buffered shape is not used for the actual geometry result, but only removes
   * potential features that are not significantly overlapping.
   * @return A list of separated basin shapes.
   * @throws java.io.IOException If there was an error reading the file.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if the two
   * projections could not be reconciled as being equal or adjusted to match by
   * reprojection.
   */
  public static List<FeatureSet> splitIntersecting(String baseShapefile,
      String clipShapefile, String baseIdFieldName, double bufferDistance)
      throws IOException,
      ProjectionException {
    ShapefileReader reader = new ShapefileReader();
    reader.open(clipShapefile);
    FeatureSet gsa = reader.getFeatures();
    reader.open(baseShapefile);
    FeatureSet basins = reader.getFeatures();
    return splitIntersecting(basins, gsa, baseIdFieldName, bufferDistance, null);
  }

  /**
   * This will perform the intersection of the base shapes with the clip shapes.
   * Any and All GSA shape intersections will be unioned into a single feature.
   *
   * @param baseShapefile The String full path of the .shp file, or a .zip file
   * that contains the .shp, .shx, .dbf and .prj files representing the basin.
   * @param clipShapefile The String full path of the .shp file, or a .zip file
   * that contains the .shp, .shx, .dbf and .prj files representing the GSA
   * shapes.
   * @param baseIdFieldName The Field name to use to name the output
   * featuresets.
   * @param bufferDistance The distance around the basin in US Feet to use to
   * count the shape as intersecting it. If this is positive, intersecting
   * becomes easier, if this is negative, intersecting becomes harder. The
   * buffered shape is not used for the actual geometry result, but only removes
   * potential features that are not significantly overlapping.
   * @param outputBaseName The String output base name.
   * @return A list of separated basin shapes.
   * @throws java.io.IOException If there was an error reading the file.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if the two
   * projections could not be reconciled as being equal or adjusted to match by
   * reprojection.
   */
  public static List<FeatureSet> splitIntersecting(String baseShapefile,
      String clipShapefile, String baseIdFieldName, double bufferDistance,
      String outputBaseName) throws IOException,
      ProjectionException {
    ShapefileReader reader = new ShapefileReader();
    reader.open(clipShapefile);
    FeatureSet gsa = reader.getFeatures();
    reader.open(baseShapefile);
    FeatureSet basins = reader.getFeatures();
    return splitIntersecting(basins, gsa, baseIdFieldName, bufferDistance,
        outputBaseName);
  }

  /**
   * This will perform the intersection of the base shapes with the clip shapes.
   * Any and All clip shape intersections will be unioned into a single feature.
   *
   * @param baseZipStream A zip stream of a zipped .shp, .shx, .dbf, and .prj
   * file for the basin shapes.
   * @param clipZipStream A zip stream of a zipped .shp, .shx, .dbf, and .prj
   * file for the GSA shapes.
   * @param baseIdFieldName The Field name to use to name the output
   * featuresets.
   * @return A list of separated base shapes.
   * @throws java.io.IOException If there was an error reading the file.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if the two
   * projections could not be reconciled as being equal or adjusted to match by
   * reprojection.
   */
  public static List<FeatureSet> splitIntersecting(InputStream baseZipStream,
      InputStream clipZipStream, String baseIdFieldName) throws IOException,
      ProjectionException {
    return splitIntersecting(baseZipStream, clipZipStream, baseIdFieldName,
        DEFAULT_BUFFER_DISTANCE, null);
  }

  /**
   * This will perform the intersection of the base shapes with the clip shapes.
   * Any and All clip shape intersections will be unioned into a single feature.
   *
   * @param baseZipStream A zip stream of a zipped .shp, .shx, .dbf, and .prj
   * file for the basin shapes.
   * @param clipZipStream A zip stream of a zipped .shp, .shx, .dbf, and .prj
   * file for the GSA shapes.
   * @param bufferDistance The distance around the basin in US Feet to use to
   * count the shape as intersecting it. If this is positive, intersecting
   * becomes easier, if this is negative, intersecting becomes harder. The
   * buffered shape is not used for the actual geometry result, but only removes
   * potential features that are not significantly overlapping.
   * @param baseIdFieldName The Field name to use to name the output
   * featuresets.
   * @return A list of separated base shapes.
   * @throws java.io.IOException If there was an error reading the file.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if the two
   * projections could not be reconciled as being equal or adjusted to match by
   * reprojection.
   */
  public static List<FeatureSet> splitIntersecting(InputStream baseZipStream,
      InputStream clipZipStream, String baseIdFieldName, double bufferDistance)
      throws IOException,
      ProjectionException {
    return splitIntersecting(baseZipStream, clipZipStream, baseIdFieldName,
        bufferDistance, null);
  }

  /**
   * This will perform the intersection of the base shapes with the clip shapes.
   * Any and All clip shape intersections will be unioned into a single feature.
   *
   * @param baseZipStream A zip stream of a zipped .shp, .shx, .dbf, and .prj
   * file for the basin shapes.
   * @param clipZipStream A zip stream of a zipped .shp, .shx, .dbf, and .prj
   * file for the GSA shapes.
   * @param baseIdFieldName The Field name to use to name the output
   * featuresets.
   * @param bufferDistance The distance around the basin in US Feet to use to
   * count the shape as intersecting it. If this is positive, intersecting
   * becomes easier, if this is negative, intersecting becomes harder. The
   * buffered shape is not used for the actual geometry result, but only removes
   * potential features that are not significantly overlapping.
   *
   * @param outputBaseName The String name to use to prefix each of the output
   * files. If the baseIdFieldName is found, this is applied to the end of the
   * base name. If this is null, then the name of the base feature is used.
   * @return A list of separated base shapes.
   * @throws java.io.IOException If there was an error reading the file.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if the two
   * projections could not be reconciled as being equal or adjusted to match by
   * reprojection.
   */
  public static List<FeatureSet> splitIntersecting(InputStream baseZipStream,
      InputStream clipZipStream, String baseIdFieldName, double bufferDistance,
      String outputBaseName) throws IOException,
      ProjectionException {

    ShapefileReader reader = new ShapefileReader();
    reader.open(clipZipStream);
    FeatureSet gsa = reader.getFeatures();
    reader.open(baseZipStream);
    FeatureSet basins = reader.getFeatures();
    return splitIntersecting(basins, gsa, baseIdFieldName, bufferDistance,
        outputBaseName);
  }

  /**
   * This will perform the intersection of the basins shapes with the gsa
   * shapes. Any and All GSA shape intersections will be unioned into a single
   * feature.
   *
   * @param base FeatureSet that contains the base shapes.
   * @param clip A FeatureSet that contains the clip shapes.
   * @param baseIdFieldName The Field name to use to name the output
   * featuresets.
   * @param bufferDistance The distance around the basin in US Feet to use to
   * count the shape as intersecting it. If this is positive, intersecting
   * becomes easier, if this is negative, intersecting becomes harder. The
   * buffered shape is not used for the actual geometry result, but only removes
   * potential features that are not significantly overlapping.
   * @param outputBaseName The String name to use to prefix each of the output
   * files. If the baseIdFieldName is found, this is applied to the end of the
   * base name. If this is null, then the name of the base feature is used.
   * @return A list of separated base shapes.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if the two
   * projections could not be reconciled as being equal or adjusted to match by
   * reprojection.
   */
  public static List<FeatureSet> splitIntersecting(FeatureSet base,
      FeatureSet clip, String baseIdFieldName, double bufferDistance,
      String outputBaseName) throws ProjectionException {
    List<FeatureSet> result = new ArrayList<>();
    boolean hasNameField = base.containsFieldNamed(baseIdFieldName);
    int i = 0;
    FeatureSet clipCopy = clip.copy();
    if (!clipCopy.getProjection().equals(base.getProjection())) {
      clipCopy.reproject(base.getProjection());
    }
    for (Feature baseFeature : base.getFeatures()) {
      if (!baseFeature.getEnvelope().intersects(clipCopy.getEnvelope())) {
        continue;
      }
      FeatureSet baseResult = new FeatureSet();
      baseResult.addFields(base.getFields());
      String baseName = outputBaseName;
      if (outputBaseName == null) {
        baseName = base.getName();
      }
      if (baseName == null || baseName.isEmpty()) {
        baseName = "Base";
      }
      baseResult.setName(baseName + "_" + i);
      baseResult.setProjectionFrom(base.getProjection());
      baseResult.setDefaultShapeType(clipCopy.getDefaultShapeType());
      Feature buffer = baseFeature;
      if (bufferDistance != 0) {
        double projDist = base.getProjection().feetToProj(bufferDistance);
        buffer = baseFeature.buffer(projDist);
      }
      Shape bufferShape = buffer.getShape();
      Shape resultShape = null;
      if (hasNameField) {
        String name = baseFeature.getAttributes().get(baseIdFieldName);
        if (name != null || !name.isEmpty()) {
          baseResult.setName(baseName + "_" + name);
        }
      }
      for (Feature clipFeature : clipCopy.getFeatures()) {
        boolean intersectsWithBuffer = bufferShape.intersects(clipFeature.getShape());
        if (intersectsWithBuffer) {
          Shape baseShape = baseFeature.getShape();
          Optional<Shape> maybeShape = baseShape.intersection(clipFeature.getShape());
          if (maybeShape.isPresent()) {
            // once the buffer test is passed, don't use the buffer shape.
            Shape shape = maybeShape.get();
            if (resultShape == null) {
              resultShape = shape;
            } else {
              resultShape = resultShape.union(shape);
            }
          }

        }
      }
      if (resultShape != null) {
        Feature unionedFeature = new Feature(resultShape);
        unionedFeature.setAttributesFrom(baseFeature.getAttributes());
        baseResult.getFeatures().add(unionedFeature);
        result.add(baseResult);
      }
      i++;
    }
    return result;
  }

  // </editor-fold>
}
