/*
 * The MIT License
 *
 * Copyright 2015 rmarquez.
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
package gov.ca.water.shapelite.map.layer;

import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.data.DataFrame;
import gov.ca.water.shapelite.data.DataSourceTilesRaster;
import gov.ca.water.shapelite.data.dataset.RasterDataset;
import gov.ca.water.shapelite.data.Projector;
import gov.ca.water.shapelite.map.GeoFrame;
import gov.ca.water.shapelite.map.Mercator;
import gov.ca.water.shapelite.map.PaintArgs;
import gov.ca.water.shapelite.symbology.RasterSymbolizer;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Projections;
import gov.ca.water.shapelite.projection.Reproject;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author rmarquez
 */
public class RasterTileLayer extends
    DataSourceLayer<DataSourceTilesRaster, RasterSymbolizer> {

  /**
   * The latitude/longitude span in Ted Mercator.
   */
  private static final int LL_SPAN = 360;

  /**
   * The latitude/longitude maximum in Ted Mercator.
   */
  private static final int LL_MAX = 180;

  /**
   * The raster tile size in pixels.
   */
  private static final int TILE_SIZE = 256;

  /**
   * The minimum elevation for the valley area in feet.
   */
  private static final double DEFAULT_MIN = -16;

  /**
   * The maximum elevation for the valley area in feet.
   */
  private static final double DEFAULT_MAX = 1000;

  /**
   * The maximum integer byte value.
   */
  private static final int MAX_BYTE = 255;

  /**
   * The integer RGB for transparent colors.
   */
  private static final int TRANSPARENT = new Color(0, 0, 0, 0).getRGB();

  /**
   * WGS84 projection.
   */
  private static final ProjectionInfo WGS84
      = Projections.getGeographic().getWorld().getWGS1984();

//<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * A boolean, that if true will allow this layer to attempt to download tiles
   * from the Internet if the cached tiles are not found.
   */
  private boolean downloadEnabled;

  /**
   * A hash map to store cached image representations of the layers.
   */
  private final HashMap<String, BufferedImage> cachedImages;

  /**
   * A boolean indicating whether to use higher scale tiles when the current
   * scale level tile is not found. Default is true.
   */
  private boolean replacingMissingWithLowScale = true;

  //</editor-fold>
  /**
   * The MapLayerTiles constructor.
   *
   * @param datasource The data source with raster values to draw.
   */
  public RasterTileLayer(DataSourceTilesRaster datasource) {
    super(datasource);
    RasterSymbolizer symbolizer = new RasterSymbolizer();
    symbolizer.setMin(DEFAULT_MIN);
    symbolizer.setMax(DEFAULT_MAX);
    this.setDefaultSymbolizer(symbolizer);
    downloadEnabled = true;
    cachedImages = new HashMap<>();
  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Performs the actual calculations of which tiles to draw, and attempts to
   * load either the tile, or the pertinent region from a lower resolution tile
   * if it can't find the tile.
   *
   * @param args The arguments to use for drawing.
   * @param download If this is true, attempting to draw tiles that don't exist
   * will initiate a download process for the tiles. Once downloaded, the tiles
   * are cached and can be used again by this and other applications.
   */
  private void doPaint(PaintArgs args, boolean download) {

    int scale = getScale(args.getFrame());

    Envelope fe = args.getFrame().getEnvelopeMercator();
    Envelope de = this.getDataSource().getEnvelopeMercator();
    if (!fe.intersects(de)) {
      return;
    }

    // Once the scale is known, it is a simple matter of calculating all the tiles
    // in the current extent and drawing each of the images.
    double n = Math.pow(2, scale);
    double dx = LL_SPAN / n;
    int xmin = (int) ((args.getFrame().getEnvelopeMercator().getMin().getX() + LL_MAX)
        / dx);
    if (xmin < 0) {
      xmin = 0;
    }
    int ymin = (int) ((LL_MAX - args.getFrame().getEnvelopeMercator().getMax().getY())
        / dx);
    if (ymin < 0) {
      ymin = 0;
    }
    int xmax = (int) ((args.getFrame().getEnvelopeMercator().getMax().getX() + LL_MAX)
        / dx);
    if (xmax > n - 1) {
      xmax = (int) n - 1;
    }
    int ymax = (int) ((LL_MAX - args.getFrame().getEnvelopeMercator().getMin().getY())
        / dx);
    if (ymax > n - 1) {
      ymax = (int) n - 1;
    }

    // the source provides tiles for the current viewing resolution
    DataSourceTilesRaster source = this.getDataSource();

    // upsource has all the same information, but is set to be one level
    // lower resolution than our current scale.
    DataSourceTilesRaster upsource = this.getDataSource().copy();
    int upscale = scale;
    source.setLevel(scale);
    if (scale > 0) {
      upscale = scale - 1;
    }
    upsource.setLevel(upscale);

    // move through the tile coordinates, based on the current zoom scale.
    for (int x = xmin; x <= xmax; x++) {
      source.setX(x);
      // the level above only has half the tiles horizontally
      upsource.setX(x / 2);
      for (int y = ymin; y <= ymax; y++) {
        // move through the tile coordinates vertically
        source.setY(y);
        // the level above only has laf the tiles vertically
        upsource.setY(y / 2);

        // the tile file exists on the disk, draw the tile.
        source.setExt(".asc");
        if (source.cachedTileExists()) {
          Optional<RasterDataset> raster = source.getDataset();
          if (raster.isPresent()) {
            paintTile(args, raster.get());
          }
        } else {
          Envelope env = source.getEnvelopeMercator();
          Projector viewProj = new Projector(args.getFrame());
          Rectangle r = viewProj.getRectangle(env);
          if (replacingMissingWithLowScale) {
            // The tile does not exist, so

            int upScale = scale;
            int uX = x;
            int uY = y;

            // walk from the current ups
            do {
              upScale = upScale - 1;
              uX = uX / 2;
              uY = uY / 2;
              upsource.setLevel(upScale);
              upsource.setX(uX);
              upsource.setY(uY);
            } while (!upsource.cachedTileExists() && upScale >= 0);
            if (upsource.cachedTileExists()) {
              if (upsource.cachedTileExists()) {
                Optional<RasterDataset> img = upsource.getDataset();
                if (img.isPresent()) {
                  paintQuad(args, img.get(), r);
                }
              }
            }
          }

          // Draw a yellow rectangle to indicate that tiles were missing.
          args.getGraphics().setColor(Color.yellow);
          args.getGraphics().drawRect(r.x, r.y, r.width, r.height);
          // regardless of whether we could paint a backup or not,
          // mark the tile as downloading
          if (download) {
            source.requestDownload();
          }
        }
      }
    }
  }

  /**
   * This tests the size of the view against the assumption of 256x256 tiles. It
   * attempts to find the scale with the closest match to the geographic zoom.
   *
   * @param frame the DataFrame to use
   * @return The integer scale from 0 to 18
   */
  public final int getScale(DataFrame frame) {
    // mapZoom in longitude per pixel
    double w = frame.getView().getWidth();
    double mapZoom = frame.getEnvelopeMercator().getWidth() / w;
    // degrees/pixel

    // from the longitude per pixel, estimate how many 256x256 tiles are required
    double n = LL_SPAN / (mapZoom * TILE_SIZE);

    // using the number of tiles, estimate the scale that has approximately that many.
    double scale = Math.log(n) / Math.log(2);

    int result = (int) Math.round(scale);
    if (result > this.getDataSource().getMaxLevel()) {
      result = this.getDataSource().getMaxLevel();
    }
    if (result < 0) {
      result = 0;
    }
    return result;
  }

  /**
   * This causes the layer to draw itself purely from tiles found on the disk.
   * No downloading will take place.
   *
   * @param args The PaintArgs for painting the tiles.
   */
  @Override
  public final void paint(PaintArgs args) {
    doPaint(args, false);
  }

  /**
   * This is a helper method that paints part of a lower resolution tile if the
   * tile on the target level is missing.
   *
   * @param e the PaintArgs event carrying information about the drawing
   * surface.
   * @param img The DatasetImage of the low resolution tile.
   * @param clip A clip rectangle defining the bounds of where drawing should
   * occur.
   */
  protected final void paintQuad(PaintArgs e, RasterDataset img,
      Rectangle clip) {

    Envelope bounds = img.getEnvelopeMercator();
    Projector viewProj = new Projector(e.getFrame());
    Envelope view = viewProj.getEnvelope(clip);
    if (!view.intersects(bounds)) {
      return;
    }

    Envelope intersection = view.intersect(bounds);
    Projector imageProj = new Projector(img);
    Rectangle source = imageProj.getRectangle(intersection);
    Rectangle dest = viewProj.getRectangle(intersection);

    e.getGraphics().drawImage(getImage(img),
        dest.x, dest.y, dest.x + dest.width, dest.y + dest.height,
        source.x, source.y, source.x + source.width, source.y + source.height, null);

  }

  /**
   * Even though the paintWeb permits downloading, this layer may be disabled.
   * So pass in the downloadEnabled value to control download.
   *
   * @param args The paint argumet
   */
  @Override
  public final void paintWeb(PaintArgs args) {
    doPaint(args, downloadEnabled);
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets a boolean, that if true will allow this layer to attempt to download
   * tiles from the Internet if the cached tiles are not found.
   *
   * @return the downloadEnabled
   */
  public final boolean isDownloadEnabled() {
    return downloadEnabled;
  }

  /**
   * Sets a boolean, that if true will allow this layer to attempt to download
   * tiles from the Internet if the cached tiles are not found.
   *
   * @param downloadEnabled the downloadEnabled to set
   */
  public final void setDownloadEnabled(boolean downloadEnabled) {
    this.downloadEnabled = downloadEnabled;
  }

  //</editor-fold>
  /**
   * Draws the specified raster.
   *
   * @param args The paint args for painting the tile.
   * @param raster The RasterDataset to paint.
   */
  public final void paintTile(PaintArgs args, RasterDataset raster) {
    Envelope view = args.getFrame().getEnvelopeMercator();
    Envelope bounds = raster.getEnvelope();

    Envelope wgs84;
    try {
      wgs84 = Reproject.reprojectEnvelope(bounds, raster.getProjection(),
          WGS84);
    } catch (ProjectionException ex) {
      System.out.println("Projection exception in MapLayerTilesRaster.paintTile().");
      return;
    }
    Envelope merc = Mercator.toMerc(wgs84);
    Projector viewProj = new Projector(args.getFrame());
    if (args.getGraphics().getClip() != null) {
      view = viewProj.getEnvelope(args.getGraphics().getClipBounds());
    }
    if (!view.intersects(merc)) {
      return;
    }
    Envelope intersection = view.intersect(merc);
    Projector imageProj = new Projector(new GeoFrame(merc, new Rectangle(0, 0,
        TILE_SIZE, TILE_SIZE)));
    Rectangle source = imageProj.getRectangle(intersection);
    Envelope rounded = imageProj.getEnvelope(source);
    Rectangle dest = viewProj.getRectangle(rounded);

    AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
        this.getDefaultSymbolizer().getOpacity());
    Composite original = args.getGraphics().getComposite();
    args.getGraphics().setComposite(ac);
    args.getGraphics().drawImage(getImage(raster),
        dest.x, dest.y, dest.x + dest.width, dest.y + dest.height,
        source.x, source.y, source.x + source.width, source.y
        + source.height, null, null);
    args.getGraphics().setComposite(original);
  }

  static int count = 0;
  /**
   * Gets an image representing the raster given the specified raster. This will
   * first attempt to use a cached image if one exists.
   *
   * @param raster The RasterDataset to create an image for.
   * @return A BufferedImage representing the raster based on the symbology.
   */
  public final BufferedImage getImage(RasterDataset raster) {
    String key = raster.getEnvelopeMercator().toString();
    if (cachedImages.containsKey(key)) {
      return cachedImages.get(key);
    }

    BufferedImage result = new BufferedImage(TILE_SIZE, TILE_SIZE,
        BufferedImage.TYPE_INT_ARGB);

    int[] data = new int[TILE_SIZE * TILE_SIZE];
    for (int row = 0; row < raster.getHeight(); row++) {
      for (int col = 0; col < raster.getWidth(); col++) {
        double value = raster.getData()[row][col];
        if (value == raster.getNoDataValue()) {
          data[row * TILE_SIZE + col] = TRANSPARENT;
          continue;
        }
        Color color = getColor(value);
        data[row * TILE_SIZE + col] = color.getRGB();
      }
    }
    result.setRGB(0, 0, TILE_SIZE, TILE_SIZE, data, 0, TILE_SIZE);
    return result;
  }

  /**
   * Gets an interpolated gradient color between the two specified values.
   *
   * @param value The value of the double.
   * @return The Color based on the minimum and maximum gradient definition.
   */
  public final Color getColor(double value) {
    RasterSymbolizer symb = this.getDefaultSymbolizer();
    return symb.getColor(value);
  }


  /**
   * This method will clear the cached image representations.
   */
  public final void invalidate() {
    cachedImages.clear();
  }

  /**
   * Gets a boolean indicating whether to use higher scale tiles when the
   * current scale level tile is not found. Default is true.
   *
   * @return replacingMissingWithLowScale Boolean, true if up scaling is
   * allowed.
   */
  public final boolean isReplacingMissingWithLowScale() {
    return replacingMissingWithLowScale;
  }

  /**
   * Sets a boolean indicating whether to use higher scale tiles when the
   * current scale level tile is not found. Default is true.
   *
   * @param doUpScaling new value of replacingMissingWithLowScale.
   */
  public final void setReplacingMissingWithLowScale(boolean doUpScaling) {
    this.replacingMissingWithLowScale = doUpScaling;
  }
}
