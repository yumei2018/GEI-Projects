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
 * Intersects a GSA feature set with a shapefile of county features. A separate
 * output FeatureSet will be created for each county. Only one feature will be
 * in each output featureset. The output featureSets will be named for the
 * county. The feature will be the union of all the intersections with a
 * particular county.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class GSACountyUtils {

  /**
   * The county ID.
   */
  private static final String DEFAULT_COUNTY_ID_FIELD = "County_ID";

  /**
   * The default prefix to be prepended for output files.
   */
  private static final String COUNTY_PREFIX = "GSA_County";

  /**
   * The default buffer distance to use for County Utils.
   */
  private static final Double DEFAULT_BUFFER_DISTANCE = -100.0;

  /**
   * Creates a new instance of the GSA Polygon Intersector. Private for utils
   */
  private GSACountyUtils() {

  }

  // <editor-fold defaultstate="collapsed" desc="findIntersectingCountyIds">
  /**
   *
   * @param target An InputStream containing a single zipped .shp, .shx, .dbf
   * and .prj file.
   * @param counties An InputStream containing a single zipped .shp, .shx, .dbf
   * and .prj file.
   * @return The list of String values from the County_ID column where
   * intersection occurs or else an empty list.
   * @throws java.io.IOException Thrown if there is an error parsing the input
   * streams.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If the two
   * projections are not the same, but reprojection fails.
   */
  public static List<String> findIntersectingCountyIds(InputStream target,
      InputStream counties) throws IOException, ProjectionException {
    return SplitIntersectingUtils.findIntersectingIds(target, counties,
        DEFAULT_COUNTY_ID_FIELD, DEFAULT_BUFFER_DISTANCE);
  }

  /**
   *
   * @param target An InputStream containing a single zipped .shp, .shx, .dbf
   * and .prj file.
   * @param counties An InputStream containing a single zipped .shp, .shx, .dbf
   * and .prj file.
   * @param countyIdField The String fieldName of the values to return.
   * @return The list of String values from the specified countyField column
   * where intersection occurs or else an empty list.
   * @throws java.io.IOException Thrown if there is an error parsing the input
   * streams.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If the two
   * projections are not the same, but reprojection fails.
   */
  public static List<String> findIntersectingCountyIds(InputStream target,
      InputStream counties, String countyIdField) throws IOException, ProjectionException {
    return SplitIntersectingUtils.findIntersectingIds(target, counties,
        countyIdField, DEFAULT_BUFFER_DISTANCE);

  }

  /**
   * Find Intersecting County Ids.
   *
   * @param target The FeatureSet containing the shapes to find intersections
   * with counties.
   * @param counties The FeatureSet containing the counties to intersect with.
   * @return The list of String values from the County_ID column where
   * intersection occurs or else an empty list.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If the two
   * projections are not the same, but reprojection fails.
   */
  public static List<String> findIntersectingCountyIds(FeatureSet target,
      FeatureSet counties) throws ProjectionException {
    return findIntersectingCountyIds(target, counties, DEFAULT_COUNTY_ID_FIELD);
  }

  /**
   * Find Intersecting County Ids.
   *
   * @param target The FeatureSet containing the shapes to find intersections
   * with counties.
   * @param counties The FeatureSet containing the counties to intersect with.
   * @param countyIdField The String fieldName of the values to return.
   * @return The list of String values from the County_ID column where
   * intersection occurs or else an empty list.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If the two
   * projections are not the same, but reprojection fails.
   */
  public static List<String> findIntersectingCountyIds(FeatureSet target,
      FeatureSet counties, String countyIdField) throws ProjectionException {
    return SplitIntersectingUtils.findIntersectingIds(target, counties,
        countyIdField, DEFAULT_BUFFER_DISTANCE);
  }

  /**
   * Find Intersecting County Ids.
   *
   * @param target The FeatureSet containing the shapes to find intersections
   * with counties.
   * @param counties The FeatureSet containing the counties to intersect with.
   * @param countyIdField The String fieldName of the values to return.
   * @param bufferDistance The distance around the basin in US Feet to use to
   * count the shape as intersecting it. If this is positive, intersecting
   * becomes easier, if this is negative, intersecting becomes harder.
   * @return The list of String values from the County_ID column where
   * intersection occurs or else an empty list.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If the two
   * projections are not the same, but reprojection fails.
   */
  public static List<String> findIntersectingCountyIds(FeatureSet target,
      FeatureSet counties, String countyIdField, double bufferDistance) throws ProjectionException {
    return SplitIntersectingUtils.findIntersectingIds(target, counties,
        countyIdField, bufferDistance);
  }

  // </editor-fold>
  // <editor-fold defaultstate="collapsed" desc="Split by County">
  /**
   * This will perform the intersection of the counties shapes with the gsa
   * shapes. Any and All GSA shape intersections will be unioned into a single
   * feature.
   *
   * @param countyShapefile The String full path of the .shp file, or a .zip
   * file that contains the .shp, .shx, .dbf and .prj files representing the
   * counties.
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
  public static List<FeatureSet> splitByCounty(String countyShapefile,
      String gsaShapefile, String basinIdFieldName) throws IOException,
      ProjectionException {
    return SplitIntersectingUtils.splitIntersecting(countyShapefile,
        gsaShapefile, basinIdFieldName, DEFAULT_BUFFER_DISTANCE, COUNTY_PREFIX);
  }

  /**
   * This will perform the intersection of the counties shapes with the gsa
   * shapes. Any and All GSA shape intersections will be unioned into a single
   * feature.
   *
   * @param countyZipStream A zip stream of a zipped .shp, .shx, .dbf, and .prj
   * file for the county shapes.
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
  public static List<FeatureSet> splitByCounty(InputStream countyZipStream,
      InputStream gsaZipStream, String basinIdFieldName) throws IOException,
      ProjectionException {

    return SplitIntersectingUtils.splitIntersecting(countyZipStream,
        gsaZipStream, basinIdFieldName, DEFAULT_BUFFER_DISTANCE, COUNTY_PREFIX);

  }

  /**
   * This will perform the intersection of the counties shapes with the gsa
   * shapes. Any and All GSA shape intersections will be unioned into a single
   * feature.
   *
   * @param countyZipStream A zip stream of a zipped .shp, .shx, .dbf, and .prj
   * file for the county shapes.
   * @param gsaZipStream A zip stream of a zipped .shp, .shx, .dbf, and .prj
   * file for the GSA shapes.
   * @param countyIdFieldName The Field name to use to name the output
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
  public static List<FeatureSet> splitByCounty(InputStream countyZipStream,
      InputStream gsaZipStream, String countyIdFieldName, double bufferDistance)
      throws IOException,
      ProjectionException {

    return SplitIntersectingUtils.splitIntersecting(countyZipStream,
        gsaZipStream, countyIdFieldName, bufferDistance, COUNTY_PREFIX);

  }

  /**
   * This will perform the intersection of the counties shapes with the gsa
   * shapes. Any and All GSA shape intersections will be unioned into a single
   * feature.
   *
   * @param counties FeatureSet that contains the county shapes.
   * @param gsa A FeatureSet that contains the GSA shapes.
   * @param countyIdFieldName The Field name to use to name the output
   * featuresets.
   * @return A list of separated county shapes.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if the two
   * projections could not be reconciled as being equal or adjusted to match by
   * reprojection.
   */
  public static List<FeatureSet> splitByCounty(FeatureSet counties,
      FeatureSet gsa, String countyIdFieldName) throws ProjectionException {
    return SplitIntersectingUtils.splitIntersecting(counties,
        gsa, countyIdFieldName, DEFAULT_BUFFER_DISTANCE, COUNTY_PREFIX);
  }

  /**
   * This will perform the intersection of the counties shapes with the gsa
   * shapes. Any and All GSA shape intersections will be unioned into a single
   * feature.
   *
   * @param counties FeatureSet that contains the county shapes.
   * @param gsa A FeatureSet that contains the GSA shapes.
   * @param countyIdFieldName The Field name to use to name the output
   * featuresets.
   * @param bufferDistance The distance around the county in US Feet to use to
   * count the shape as intersecting it. If this is positive, intersecting
   * becomes easier, if this is negative, intersecting becomes harder. The
   * buffered shape is not used for the actual geometry result, but only removes
   * potential features that are not significantly overlapping.
   * @return A list of separated county shapes.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if the two
   * projections could not be reconciled as being equal or adjusted to match by
   * reprojection.
   */
  public static List<FeatureSet> splitByCounty(FeatureSet counties,
      FeatureSet gsa, String countyIdFieldName, double bufferDistance)
      throws ProjectionException {
    return SplitIntersectingUtils.splitIntersecting(counties,
        gsa, countyIdFieldName, bufferDistance, COUNTY_PREFIX);
  }

  // </editor-fold>
}
