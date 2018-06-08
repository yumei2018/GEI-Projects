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
package gov.ca.water.shapelite.map;

import gov.ca.water.shapelite.data.DataFrame;
import java.awt.Rectangle;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.data.Projector;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Projections;
import gov.ca.water.shapelite.projection.Reproject;

/**
 * This class possesses a geographic envelopeMercator and a rectangle. It is
 * responsible for the transformation between the geographic and display
 * coordinates.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class GeoFrame implements DataFrame {

  /**
   * The integer pixel width and height of tiles in web tile map servers.
   */
  private final static int TILE_SIZE = 256;

  /**
   * Meters per one degree of latitude, or of longitude at the equator.
   */
  private final static double METERS_PER_DEGREE = 111319.9;

  /**
   * The number of inches in a meter.
   */
  private final static double INCHES_PER_METER = 39.3701;

  /**
   * Dots per inch.
   */
  private final static int DPI = 96;
  /**
   * WGS84 geographic projection.
   */
  private static final ProjectionInfo WGS84
      = Projections.getGeographic().getWorld().getWGS1984();

  //<editor-fold defaultstate="collapsed" desc="Private Fields">
  /**
   * The geographic envelopeMercator.
   */
  private Envelope envelopeMercator;

  /**
   * The projection info defining the geographic projection for this
   * GeoFrame.  By default this is WGS84.
   */
  private final ProjectionInfo projection;

  /**
   * The rectangular view.
   */
  private Rectangle view;
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Constructors">
  /**
   * Creates a new instance of a Geoframe.
   */
  public GeoFrame() {
    projection = ProjectionInfo.getDefault();
  }

  /**
   * Copies characteristics of the existing data frame.
   *
   * @param source The DataFrame to copy attributes from.
   */
  public GeoFrame(DataFrame source) {
    this.envelopeMercator = source.getEnvelopeMercator().copy();
    this.view = (Rectangle) source.getView().clone();
    projection = ProjectionInfo.getDefault();
  }

  /**
   * Creates a new instance of a GeoFrame based on the specified
   * envelopeMercator and view.
   *
   * @param env The bounding envelopeMercator in geographic (Mercator)
   * coordinates.
   * @param view The rectangular view in pixels.
   */
  public GeoFrame(Envelope env, Rectangle view) {
    this.envelopeMercator = env.copy();
    this.view = (Rectangle) view.clone();
    projection = ProjectionInfo.getDefault();
  }

  /**
   * Creates a new instance of a GeoFrame based on the specified
   * envelopeMercator and width and height.
   *
   * @param envelopeMercator The TedMercator envelope for this frame.
   * @param width The width of the geo frame in pixels.
   * @param height The hegiht of the geo frame in pixels.
   */
  public GeoFrame(Envelope envelopeMercator, int width, int height) {
    this.view = new Rectangle(0, 0, width, height);
    this.envelopeMercator = envelopeMercator;
    this.projection = ProjectionInfo.getDefault();
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Public Method">


  /**
   * Sets the rectangular view in pixels.
   *
   * @param rect The rectangular view in pixels to set.
   */
  public final void setView(Rectangle rect) {
    view = rect;
  }

  /**
   * Sets the zoom level so that the pixel resolution and map scale size the
   * tiles so that the tiles of the specified level are 256x256 pixels.
   *
   * @param level The integer level.
   */
  public final void setLevel(int level) {
    double mapScale = this.getEnvelopeMercator().getWidth() / getView().getWidth();

    // using the rounded tile scale, we now need the degrees to pixels of that scale
    double dpp = Mercator.LL_SPAN / (TILE_SIZE * Math.pow(2, level));

    // Adjust the factor so that when we use it, it will result in a new scale
    // that is exactly one of the tile level scales.
    Envelope result = this.getEnvelopeMercator();
    double sc = dpp / mapScale;
    result = result.zoom(sc);
    envelopeMercator.copyProperties(result);
  }
  //</editor-fold>

  /**
   * Gets the geographic envelopeMercator.
   *
   * @return the geographic envelopeMercator.
   */
  @Override
  public final Envelope getEnvelopeMercator() {
    return envelopeMercator;
  }

  /**
   * Gets the rectangular view in pixels.
   *
   * @return the rectangular view.
   */
  @Override
  public final Rectangle getView() {
    return view;
  }

  /**
   * Gets the scale factor, which ultimately is the world units / display units.
   *
   * @return The double scale relating the geographic space to the pixel space.
   */
  @Override
  public final double getScale() {
    double worldInch = envelopeMercator.getWidth()
        * METERS_PER_DEGREE * INCHES_PER_METER;
    double displayInch = view.width / DPI;
    return worldInch / displayInch;
  }

  /**
   * Gets the Projector class used for making transformations.
   *
   * @return The Projector class for calculation of projections.
   */
  @Override
  public final Projector getProjector() {
    return new Projector(this);
  }

  /**
   * Gets the zoom level from 0 to 18 for the open street map tile layers that
   * most closely matches the pixel resolution of the current view for
   * displaying the current extent.
   *
   * @return An integer representing the closest integer level to the current
   * one.
   */
  @Override
  public final int getClosestLevel() {

    double target = this.getEnvelopeMercator().getWidth() / getView().getWidth();
    // from the longitude per pixel, estimate how many 256x256 tiles are required
    double n = Mercator.LL_SPAN / (target * TILE_SIZE);

    // using the number of tiles, estimate the tile level that has
    // approximately that many.
    double level = Math.log(n) / Math.log(2);

    return (int) Math.round(level);
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

}
