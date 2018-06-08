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
package gov.ca.water.shapelite.data.dataset;

import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.DefaultEnvelope;
import gov.ca.water.shapelite.NonNull;
import gov.ca.water.shapelite.projection.ProjectionInfo;

/**
 * This abstract class hosts some data values in memory, and acts as the base
 * class. Superclass versions of the Dataset actually clarify what data will be
 * loaded.
 *
 * @author Harold A. Dunsford Jr. Ph.D./kprins
 */
public abstract class Dataset {

  /**
   * The maximum geographic latitutde.
   */
  public static final int MAX_LAT = 180;

  /**
   * The maximum geographic longitude.
   */
  public static final int MAX_LON = 180;

  //<editor-fold defaultstate="collapsed" desc="Private Field">
  /**
   * The geographic envelopeMercator containing all the data in the dataset.
   */
  private final Envelope envelopeMercator;

  /**
   * The ProjectionInfo for this dataset. By default, this will be WGS84. This
   * should never be null.
   */
  @NonNull
  private final ProjectionInfo projection;

  //</editor-fold>
  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Public Constructor - initiates an all inclusive envelop (the hole world).
   */
  public Dataset() {
    this.envelopeMercator = new DefaultEnvelope(-MAX_LON, -MAX_LAT, MAX_LON, MAX_LAT);
    projection = ProjectionInfo.getDefault();
  }

  // </editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Public/Protected methods">
  /**
   * Gets the geographic envelopeMercator containing all the data in the
   * dataset.
   *
   * @return the envelopeMercator.
   */
  public final Envelope getEnvelopeMercator() {
    return envelopeMercator;
  }

  /**
   * This will not set the object reference like a standard setter, but instead
   * will copy the values from the specified envelope to this envelope.
   *
   * @param source The source envelope to set.
   */
  public final void setEnvelopeMercatorFrom(Envelope source) {
    envelopeMercator.copyProperties(source);
  }

  //</editor-fold>
  /**
   * Gets the ProjectionInfo describing the cartographic projection for this
   * dataset.
   *
   * @return the projection. This is option and may frequently be null.
   */
  @NonNull
  public final ProjectionInfo getProjection() {
    return projection;
  }

  /**
   * This will not assign the object instance like a standard setter, but
   * instead will cause the projection on this object to copy the properties
   * of the specified projection.
   * @param sourceProjection The source projection to copy.
   */
  public void setProjectionFrom(ProjectionInfo sourceProjection){
    this.projection.copyProperties(sourceProjection);
  }

  /**
   * Gets the String for the ProjectionInfo for this dataset, represented as an
   * ESRI string.
   *
   * @return The ESRI projection string, or null if the projection is not
   * defined.
   */
  @NonNull
  public final String getProjectionESRI() {
    return projection.toEsriString();
  }
}
