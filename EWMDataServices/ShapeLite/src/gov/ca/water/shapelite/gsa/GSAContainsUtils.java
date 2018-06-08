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
public final class GSAContainsUtils {

  /**
   * Private constructor for utils class.
   */
  private GSAContainsUtils() {

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
  public static boolean contains(InputStream serviceAreaStream, InputStream gsaAreaStream)
      throws IOException, ProjectionException {
    ShapefileReader reader = new ShapefileReader();
    reader.open(gsaAreaStream);
    FeatureSet gsaAreas = reader.getFeatures();
    reader.open(serviceAreaStream);
    FeatureSet serviceAreas = reader.getFeatures();
    return contains(serviceAreas, gsaAreas);
  }

  /**
   * This method effectively treats the two as multi-geometries and performs a
   * contains test.
   *
   * @param gsaAreas The FeatureSet to
   * @param serviceAreas The FeatureSet to treat as an outside boundary to see
   * if
   * @return boolena, true if the service areas contain the gsa areas.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If the
   * projections do not match and reproject fails.
   */
  public static boolean contains(FeatureSet serviceAreas, FeatureSet gsaAreas) throws ProjectionException {
    if (gsaAreas == null || gsaAreas.isEmpty() || gsaAreas.getFeatureType()
        == ShapeType.NullShape) {
      throw new IllegalArgumentException("Invalid argument for gsaAreas.");
    }
    if (serviceAreas == null || serviceAreas.isEmpty()
        || serviceAreas.getFeatureType() == ShapeType.NullShape) {
      throw new IllegalArgumentException("Invalid argument for serviceAreas.");
    }
    return serviceAreas.contains(gsaAreas);
  }

}
