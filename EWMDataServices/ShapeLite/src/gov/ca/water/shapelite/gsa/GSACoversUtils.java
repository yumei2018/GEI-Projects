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
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.io.ShapefileReader;
import gov.ca.water.shapelite.projection.ProjectionException;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class GSACoversUtils {

  /**
   * Private constructor for utils class.
   */
  private GSACoversUtils() {

  }

  /**
   * Tests if the service areas contain the gsa Areas.
   *
   * @param serviceAreaStream The input stream of the zip file of the service
   * areas.
   * @param gsaAreaStream The input stream of the zip file of the gsa areas.
   * @return boolean true if the service areas contain the gsa areas.
   * @throws java.io.IOException if there is an error reading the stream.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If the
   * projections do not match and reproject fails.
   */
  public static boolean covers(InputStream serviceAreaStream,
      InputStream gsaAreaStream)
      throws IOException, ProjectionException {
    return covers(serviceAreaStream, gsaAreaStream, 0);
  }

  /**
   * Tests if the service areas contain the gsa Areas.
   *
   * @param serviceAreaStream The input stream of the zip file of the service
   * areas.
   * @param gsaAreaStream The input stream of the zip file of the gsa areas.
   * @param buffer The boolean buffer.
   * @return boolean true if the service areas contain the gsa areas.
   * @throws java.io.IOException if there is an error reading the stream.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If the
   * projections do not match and reproject fails.
   */
  public static boolean covers(InputStream serviceAreaStream,
      InputStream gsaAreaStream, double buffer)
      throws IOException, ProjectionException {
    ShapefileReader reader = new ShapefileReader();
    reader.open(gsaAreaStream);
    FeatureSet gsaAreas = reader.getFeatures();
    gsaAreas.setRounding(6);
    gsaAreas = gsaAreas.union();
    reader.open(serviceAreaStream);
    FeatureSet serviceAreas = reader.getFeatures();
    serviceAreas.setRounding(6);
    serviceAreas = serviceAreas.union();
    return covers(serviceAreas, gsaAreas, buffer);
  }

  /**
   * This method effectively treats the two as multi-geometries and performs a
   * covers test.
   *
   * @param gsaAreas The FeatureSet to
   * @param serviceAreas The FeatureSet to treat as an outside boundary to see
   * if
   * @return boolena, true if the service areas contain the gsa areas.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If the
   * projections do not match and reproject fails.
   */
  public static boolean covers(FeatureSet serviceAreas, FeatureSet gsaAreas)
      throws ProjectionException {
    return covers(serviceAreas, gsaAreas, 0);
  }

  /**
   * This method effectively treats the two as multi-geometries and performs a
   * covers test.
   *
   * @param gsaAreas The FeatureSet to
   * @param serviceAreas The FeatureSet to treat as an outside boundary to see
   * if
   * @param buffer THe double buffer distance in US Feet. If this is positive,
   * the covers test becomes easier, if it is negative, it becomes harder.
   * @return boolena, true if the service areas contain the gsa areas.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If the
   * projections do not match and reproject fails.
   */
  public static boolean covers(FeatureSet serviceAreas, FeatureSet gsaAreas,
      double buffer) throws ProjectionException {
    if (gsaAreas == null || gsaAreas.isEmpty() || gsaAreas.getFeatureType()
        == ShapeType.NullShape) {
      throw new IllegalArgumentException("Invalid argument for gsaAreas.");
    }
    if (serviceAreas == null || serviceAreas.isEmpty()
        || serviceAreas.getFeatureType() == ShapeType.NullShape) {
      throw new IllegalArgumentException("Invalid argument for serviceAreas.");
    }
    return serviceAreas.covers(gsaAreas, buffer);
  }

}
