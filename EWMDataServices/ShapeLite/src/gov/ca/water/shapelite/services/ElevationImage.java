/*
 * The MIT License
 *
 * Copyright 2017 Harold A. Dunsford Jr. Ph.D..
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
package gov.ca.water.shapelite.services;

import gov.ca.water.shapelite.DefaultEnvelope;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.data.CanvasFrame;
import gov.ca.water.shapelite.data.DataSourceTilesRaster;
import gov.ca.water.shapelite.map.Mercator;
import gov.ca.water.shapelite.map.PaintArgs;
import gov.ca.water.shapelite.map.layer.RasterTileLayer;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Projections;
import gov.ca.water.shapelite.projection.Reproject;
import gov.ca.water.shapelite.symbology.RasterSymbolizer;
import gov.ca.water.shapelite.symbology.RasterSymbolizerLevel;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class ElevationImage {

  private String path;

  /**
   * The RasterSymbolizer to define how the raster should appear.
   */
  private RasterSymbolizer symbolizer;

  ProjectionInfo projection;

  /**
   * Gets a symbolized depth image based on the extents by using tiles.
   */
  public ElevationImage() {
    path = "\\\\devapp\\e\\Tiles\\Elevation";
    symbolizer = new RasterSymbolizerLevel();
    projection = Projections.getProjected().getWorld().getWebMercator();
  }

  /**
   * Gets an image representing the elevation values for the specified width,
   * height, and extent values.
   *
   * @param width The width in pixels.
   * @param height The height in pixels.
   * @param xmin the minimum longitude.
   * @param xmax the maximum longitude.
   * @param ymin the minimum latitude.
   * @param ymax the maximum latitude.
   * @return A BufferedImage showing the elevation.
   */
  public BufferedImage getImage(int width, int height, double xmin, double xmax, double ymin, double ymax) {
    DataSourceTilesRaster datasource = new DataSourceTilesRaster();
    datasource.setPathAbsolute("\\\\devapp\\e\\Tiles\\Elevation");
    RasterTileLayer layer = new RasterTileLayer(datasource);
    layer.setDefaultSymbolizer(symbolizer);

    CanvasFrame frame = new CanvasFrame();
    Envelope projected = new DefaultEnvelope(xmin, ymin, xmax, ymax);
    Envelope wgs84 = projected;
    try {
      wgs84 = Reproject.reprojectEnvelope(projected, projection,
          Projections.getWGS84());
    } catch (ProjectionException ex) {
      Logger.getLogger(ElevationImage.class.getName()).log(Level.SEVERE, null, ex);
    }
    Envelope merc = Mercator.toMerc(wgs84);
    frame.setEnvelope(merc);
    frame.setView(new Rectangle(0, 0, width, height));
    BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g = img.createGraphics();
    PaintArgs args = new PaintArgs(this, g, frame);
    layer.paint(args);
    g.dispose();
    return img;
  }

  /**
   * @return the path
   */
  public String getPath() {
    return path;
  }

  /**
   * @param path the path to set
   */
  public void setPath(String path) {
    this.path = path;
  }

  /**
   * The RasterSymbolizer to define how the raster should appear.
   *
   * @return the symbolizer
   */
  public RasterSymbolizer getSymbolizer() {
    return symbolizer;
  }

  /**
   * The RasterSymbolizer to define how the raster should appear.
   *
   * @param symbolizer the symbolizer to set
   */
  public void setSymbolizer(RasterSymbolizer symbolizer) {
    this.symbolizer = symbolizer;
  }

}
