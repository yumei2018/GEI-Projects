/*
 * The MIT License
 *
 * Copyright 2014 J.G. "Koos" Prins, D.Eng. PE..
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
package gov.ca.water.shapelite.data;

import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.map.Mercator;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Projections;
import gov.ca.water.shapelite.projection.Reproject;
import java.awt.Rectangle;

/**
 * A DataFrame for drawing Map Content to a output canvas (e.g. a pdf file or
 * image)
 *
 * @author J.G. "Koos" Prins, D.Eng. PE.
 */
public class CanvasFrame implements DataFrame {

  /**
   * The total number of degrees in longitude is 360.
   */
  private static final int LONGITUDE_SPAN = 360;
  /**
   * Each tile is 256 pixels wide and tall.
   */
  private static final int PIXEL_COUNT = 256;

  /**
   * Display Pixels Per Inch, assumed to be 96.
   */
  private static final double PPI = 96;

  /**
   * Meters per decimal degree (at the equator).
   */
  private static final double METERS_PER_DD = 111319.9;

  /**
   * Inches per meter.
   */
  private static final double INCHES_PER_METER = 39.3701;

  /**
   * WGS84 geographic projection.
   */
  private static final ProjectionInfo WGS84
      = Projections.getGeographic().getWorld().getWGS1984();

  //<editor-fold defaultstate="collapsed" desc="Private Fields">
  /**
   * The geographic envelope.
   */
  private Envelope envelope;

  /**
   * The rectangular view.
   */
  private Rectangle view;

  /**
   * The projection info defining the geographic projection for this
   * GeoFrame.  By default this is WGS84.
   */
  private final ProjectionInfo projection;
  //</editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Public Constructor.
   */
  public CanvasFrame() {
    this.envelope = null;
    this.view = null;
    projection = ProjectionInfo.getDefault();
  }

  /**
   * Creates a new instance of a CanvasFrame based on the specified envelope and
   * view.
   *
   * @param mercatorBounds The bounding envelope in geographic (Mercator) coordinates.
   * @param view The output rectangular view in pixels.
   */
  public CanvasFrame(Envelope mercatorBounds, Rectangle view) {
    this.envelope = mercatorBounds.copy();
    this.view = (Rectangle) view.clone();
    projection = ProjectionInfo.getDefault();
  }

  /**
   * Copies the characteristics of the existing data frame.
   *
   * @param source The DataFrame source.
   */
  public CanvasFrame(DataFrame source) {
    this.envelope = source.getEnvelopeMercator().copy();
    this.view = (Rectangle) source.getView().clone();
    projection = ProjectionInfo.getDefault();
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Public Methods">
  /**
   * Sets the geographic envelope in the Mercator projection.
   *
   * @param env The geographic envelope in Mercator coordinates to set.
   */
  public final void setEnvelope(Envelope env) {
    this.envelope = env;
  }

  /**
   * Sets the rectangular output view in pixels.
   *
   * @param rect The rectangular view in pixels to set.
   */
  public final void setView(Rectangle rect) {
    this.view = rect;
  }



  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Implements DataFrame">
  /**
   * {@inheritDoc}.
   * <p>
   * OVERRIDE: Return the assigned envelope</p>
   */
  @Override
  public final Envelope getEnvelopeMercator() {
    return this.envelope;
  }

  /**
   * {@inheritDoc}.
   * <p>
   * OVERRIDE: Return the assigned output view</p>
   */
  @Override
  public final Rectangle getView() {
    return this.view;
  }

  /**
   * {@inheritDoc}.
   * <p>
   * OVERRIDE: </p>
   */
  @Override
  public final double getScale() {
    double worldInch = envelope.getWidth()
            * METERS_PER_DD * INCHES_PER_METER;
    double displayInch = view.width / PPI;
    return worldInch / displayInch;
  }

  /**
   * {@inheritDoc}.
   * <p>
   * OVERRIDE: </p>
   */
  @Override
  public final int getClosestLevel() {
    double target = this.getEnvelopeMercator().getWidth() / getView().getWidth();
    /* from the longitude per pixel, estimate how many 256x256 tiles are
     required */
    double n = LONGITUDE_SPAN / (target * PIXEL_COUNT);

    /* using the number of tiles, estimate the tile level that has
     approximately that many. */
    double level = Math.log(n) / Math.log(2);

    return (int) Math.round(level);
  }

  /**
   * {@inheritDoc}.
   * <p>
   * OVERRIDE: return new {@linkplain Projector Projector(this)}</p>
   */
  @Override
  public final Projector getProjector() {
    return new Projector(this);
  }

  /**
   * Gets the geographic envelope in the native projection for this dataset.
   *
   * @return The geographic envelope.
   */
  @Override
  public final Envelope getEnvelope() {
    Envelope env = this.getEnvelopeMercator().copy();
    Envelope wgs84 = Mercator.fromMerc(env);
    Envelope result = wgs84;
    if (!projection.equals(WGS84)) {
      try {
        result = Reproject.reprojectEnvelope(wgs84, WGS84, projection);
      } catch (ProjectionException ex) {
        System.out.println("GeoFrame.getEnvelope(): Failed to reproject "
            + projection.getName() + " to WGS84.");
      }
    }
    return result;
  }

  /**
   * The projection that defines the geographic envelope. This does not have any
   * effect on EnvelopeMercator, which is always in the Ted Mercator projection
   * that the map panel uses.
   *
   * @return the projection
   */
  public final ProjectionInfo getProjection() {
    return projection;
  }
  // </editor-fold>
}
