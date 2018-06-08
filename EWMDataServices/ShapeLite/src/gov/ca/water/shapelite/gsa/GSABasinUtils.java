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

import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.projection.ProjectionException;
import java.io.IOException;
import java.io.InputStream;
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
public final class GSABasinUtils {

  /**
   * The basin ID.
   */
  private static final String DEFAULT_BASIN_ID_FIELD = "Basin_ID";

  /**
   * The default buffer distance is 100 feet.
   */
  private static final double DEFAULT_BUFFER_DISTANCE = 100;

  /**
   * Creates a new instance of the GSA Polygon Intersector. Private for utils
   */
  private GSABasinUtils() {

  }

  // <editor-fold defaultstate="collapsed" desc="findIntersectingBasinIds">
  /**
   *
   * @param target An InputStream containing a single zipped .shp, .shx, .dbf
   * and .prj file.
   * @param basins An InputStream containing a single zipped .shp, .shx, .dbf
   * and .prj file.
   * @return The list of String values from the Basin_ID column where
   * intersection occurs or else an empty list.
   * @throws java.io.IOException Thrown if there is an error parsing the input
   * streams.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If the two
   * projections are not the same, but reprojection fails.
   */
  public static List<String> findIntersectingBasinIds(InputStream target,
      InputStream basins) throws IOException, ProjectionException {
    return findIntersectingBasinIds(target, basins, DEFAULT_BASIN_ID_FIELD);
  }

  /**
   *
   * @param target An InputStream containing a single zipped .shp, .shx, .dbf
   * and .prj file.
   * @param basins An InputStream containing a single zipped .shp, .shx, .dbf
   * and .prj file.
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
  public static List<String> findIntersectingBasinIds(InputStream target,
      InputStream basins, double bufferDistance) throws IOException,
      ProjectionException {
    return SplitIntersectingUtils.findIntersectingIds(target, basins,
        DEFAULT_BASIN_ID_FIELD, bufferDistance);
  }

  /**
   *
   * @param target An InputStream containing a single zipped .shp, .shx, .dbf
   * and .prj file.
   * @param basins An InputStream containing a single zipped .shp, .shx, .dbf
   * and .prj file.
   * @param basinField The String fieldName of the values to return.
   * @return The list of String values from the specified basinField column
   * where intersection occurs or else an empty list.
   * @throws java.io.IOException Thrown if there is an error parsing the input
   * streams.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If the two
   * projections are not the same, but reprojection fails.
   */
  public static List<String> findIntersectingBasinIds(InputStream target,
      InputStream basins, String basinField) throws IOException, ProjectionException {
    return SplitIntersectingUtils.findIntersectingIds(target, basins,
        basinField, DEFAULT_BUFFER_DISTANCE);

  }

  /**
   *
   * @param target An InputStream containing a single zipped .shp, .shx, .dbf
   * and .prj file.
   * @param basins An InputStream containing a single zipped .shp, .shx, .dbf
   * and .prj file.
   * @param basinField The String fieldName of the values to return.
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
  public static List<String> findIntersectingBasinIds(InputStream target,
      InputStream basins, String basinField, double bufferDistance)
      throws IOException, ProjectionException {
    return SplitIntersectingUtils.findIntersectingIds(target, basins,
        basinField, bufferDistance);

  }

  /**
   * Find Intersecting Basin Ids.
   *
   * @param target The FeatureSet containing the shapes to find intersections
   * with basins.
   * @param basins The FeatureSet containing the basins to intersect with.
   * @return The list of String values from the Basin_ID column where
   * intersection occurs or else an empty list.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If the two
   * projections are not the same, but reprojection fails.
   */
  public static List<String> findIntersectingBasinIds(FeatureSet target,
      FeatureSet basins) throws ProjectionException {

    return SplitIntersectingUtils.findIntersectingIds(target, basins,
        DEFAULT_BASIN_ID_FIELD, DEFAULT_BUFFER_DISTANCE);
  }

  /**
   * Find Intersecting Basin Ids.
   *
   * @param target The FeatureSet containing the shapes to find intersections
   * with basins.
   * @param basins The FeatureSet containing the basins to intersect with.
   * @param basinField The String fieldName of the values to return.
   * @return The list of String values from the Basin_ID column where
   * intersection occurs or else an empty list.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If the two
   * projections are not the same, but reprojection fails.
   */
  public static List<String> findIntersectingBasinIds(FeatureSet target,
      FeatureSet basins, String basinField) throws ProjectionException {
    return SplitIntersectingUtils.findIntersectingIds(target, basins, basinField,
        DEFAULT_BUFFER_DISTANCE);
  }

  /**
   * Find Intersecting Basin Ids.
   *
   * @param target The FeatureSet containing the shapes to find intersections
   * with basins.
   * @param basins The FeatureSet containing the basins to intersect with.
   * @param basinField The String fieldName of the values to return.
   * @param bufferDistance The distance around the basin in US Feet to use to
   * count the shape as intersecting it. If this is positive, intersecting
   * becomes easier, if this is negative, intersecting becomes harder.
   * @return The list of String values from the Basin_ID column where
   * intersection occurs or else an empty list.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If the two
   * projections are not the same, but reprojection fails.
   */
  public static List<String> findIntersectingBasinIds(FeatureSet target,
      FeatureSet basins, String basinField, double bufferDistance)
      throws ProjectionException {
    return SplitIntersectingUtils.findIntersectingIds(target, basins, basinField,
        bufferDistance);
  }

  // </editor-fold>
  // <editor-fold defaultstate="collapsed" desc="Split by Basin">
  /**
   * This will perform the intersection of the basins shapes with the gsa
   * shapes. Any and All GSA shape intersections will be unioned into a single
   * feature.
   *
   * @param basinShapefile The String full path of the .shp file, or a .zip file
   * that contains the .shp, .shx, .dbf and .prj files representing the basin.
   * @param gsaShapefile The String full path of the .shp file, or a .zip file
   * that contains the .shp, .shx, .dbf and .prj files representing the GSA
   * shapes.
   * @param basinIdFieldName The Field name to use to name the output
   * featuresets.
   * @return A list of separated basin shapes.
   * @throws java.io.IOException If there was an error reading the file.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if the two
   * projections could not be reconciled as being equal or adjusted to match by
   * reprojection.
   */
  public static List<FeatureSet> splitByBasin(String basinShapefile,
      String gsaShapefile, String basinIdFieldName) throws IOException,
      ProjectionException {
    return SplitIntersectingUtils.splitIntersecting(basinShapefile,
        gsaShapefile, basinIdFieldName,
        SplitIntersectingUtils.DEFAULT_BUFFER_DISTANCE, "GSA_Basin");
  }

  /**
   * This will perform the intersection of the basins shapes with the gsa
   * shapes. Any and All GSA shape intersections will be unioned into a single
   * feature.
   *
   * @param basinShapefile The String full path of the .shp file, or a .zip file
   * that contains the .shp, .shx, .dbf and .prj files representing the basin.
   * @param gsaShapefile The String full path of the .shp file, or a .zip file
   * that contains the .shp, .shx, .dbf and .prj files representing the GSA
   * shapes.
   * @param bufferDistance The buffer distance in US feet around the basin that
   * should be considered as intersecting. If this value is positive,
   * intersecting becomes easier, if it is negative, it becomes harder. The
   * buffered shape is not used for the actual geometry result, but only removes
   * potential features that are not significantly overlapping.
   * @param basinIdFieldName The Field name to use to name the output
   * featuresets.
   * @return A list of separated basin shapes.
   * @throws java.io.IOException If there was an error reading the file.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if the two
   * projections could not be reconciled as being equal or adjusted to match by
   * reprojection.
   */
  public static List<FeatureSet> splitByBasin(String basinShapefile,
      String gsaShapefile, String basinIdFieldName, double bufferDistance)
      throws IOException, ProjectionException {
    return SplitIntersectingUtils.splitIntersecting(basinShapefile,
        gsaShapefile, basinIdFieldName, bufferDistance, "GSA_Basin");
  }

  /**
   * This will perform the intersection of the basins shapes with the gsa
   * shapes. Any and All GSA shape intersections will be unioned into a single
   * feature.
   *
   * @param basinZipStream A zip stream of a zipped .shp, .shx, .dbf, and .prj
   * file for the basin shapes.
   * @param gsaZipStream A zip stream of a zipped .shp, .shx, .dbf, and .prj
   * file for the GSA shapes.
   * @param basinIdFieldName The Field name to use to name the output
   * featuresets.
   * @return A list of separated basin shapes.
   * @throws java.io.IOException If there was an error reading the file.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if the two
   * projections could not be reconciled as being equal or adjusted to match by
   * reprojection.
   */
  public static List<FeatureSet> splitByBasin(InputStream basinZipStream,
      InputStream gsaZipStream, String basinIdFieldName) throws IOException,
      ProjectionException {

    return SplitIntersectingUtils.splitIntersecting(basinZipStream,
        gsaZipStream, basinIdFieldName,
        SplitIntersectingUtils.DEFAULT_BUFFER_DISTANCE, "GSA_Basin");

  }

  /**
   * This will perform the intersection of the basins shapes with the gsa
   * shapes. Any and All GSA shape intersections will be unioned into a single
   * feature.
   *
   * @param basinZipStream A zip stream of a zipped .shp, .shx, .dbf, and .prj
   * file for the basin shapes.
   * @param gsaZipStream A zip stream of a zipped .shp, .shx, .dbf, and .prj
   * file for the GSA shapes.
   * @param basinIdFieldName The Field name to use to name the output
   * featuresets.
   * @param bufferDistance The buffer distance in US feet around the basin that
   * should be considered as intersecting. If this value is positive,
   * intersecting becomes easier, if it is negative, it becomes harder. The
   * buffered shape is not used for the actual geometry result, but only removes
   * potential features that are not significantly overlapping.
   * @return A list of separated basin shapes.
   * @throws java.io.IOException If there was an error reading the file.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if the two
   * projections could not be reconciled as being equal or adjusted to match by
   * reprojection.
   */
  public static List<FeatureSet> splitByBasin(InputStream basinZipStream,
      InputStream gsaZipStream, String basinIdFieldName, double bufferDistance)
      throws IOException, ProjectionException {

    return SplitIntersectingUtils.splitIntersecting(basinZipStream,
        gsaZipStream, basinIdFieldName, bufferDistance, "GSA_Basin");

  }

  /**
   * This will perform the intersection of the basins shapes with the gsa
   * shapes. Any and All GSA shape intersections will be unioned into a single
   * feature.
   *
   * @param basins FeatureSet that contains the basin shapes.
   * @param gsa A FeatureSet that contains the GSA shapes.
   * @param basinIdFieldName The Field name to use to name the output
   * featuresets.
   * @return A list of separated basin shapes.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if the two
   * projections could not be reconciled as being equal or adjusted to match by
   * reprojection.
   */
  public static List<FeatureSet> splitByBasin(FeatureSet basins,
      FeatureSet gsa, String basinIdFieldName) throws ProjectionException {
    return splitByBasin(basins, gsa, basinIdFieldName, DEFAULT_BUFFER_DISTANCE);
  }

  /**
   * This will perform the intersection of the basins shapes with the gsa
   * shapes. Any and All GSA shape intersections will be unioned into a single
   * feature.
   *
   * @param basins FeatureSet that contains the basin shapes.
   * @param gsa A FeatureSet that contains the GSA shapes.
   * @param basinIdFieldName The Field name to use to name the output
   * featuresets.
   * @param bufferDistance The buffer distance in US feet around the basin that
   * should be considered as intersecting. If this value is positive,
   * intersecting becomes easier, if it is negative, it becomes harder. The
   * buffered shape is not used for the actual geometry result, but only removes
   * potential features that are not significantly overlapping.
   * @return A list of separated basin shapes.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if the two
   * projections could not be reconciled as being equal or adjusted to match by
   * reprojection.
   */
  public static List<FeatureSet> splitByBasin(FeatureSet basins,
      FeatureSet gsa, String basinIdFieldName, double bufferDistance)
      throws ProjectionException {
    return SplitIntersectingUtils.splitIntersecting(basins,
        gsa, basinIdFieldName, bufferDistance, "GSA_Basin");
  }

  // </editor-fold>
}
