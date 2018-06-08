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
import gov.ca.water.shapelite.NonNull;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.data.DataFrame;
import gov.ca.water.shapelite.data.DataSourceTilesPath;
import gov.ca.water.shapelite.data.dataset.MarkerPathRangeDataset;
import gov.ca.water.shapelite.data.marker.PathRangeMarker;
import gov.ca.water.shapelite.data.Projector;
import gov.ca.water.shapelite.map.Mercator;
import gov.ca.water.shapelite.map.PaintArgs;
import gov.ca.water.shapelite.symbology.Pen;
import gov.ca.water.shapelite.symbology.LineSymbolizer;
import gov.ca.water.shapelite.symbology.ImageSymbolizer;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.util.List;

/**
 *
 * @author rmarquez
 */
public class TileLineLayer extends DataSourceLayer<DataSourceTilesPath, ImageSymbolizer> {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * A boolean, that if true will allow this layer to attempt to download tiles
   * from the Internet if the cached tiles are not found.
   */
  private boolean downloadEnabled;

  //</editor-fold>
  /**
   * The MapLayerTiles constructor.
   *
   * @param datasource
   */
  public TileLineLayer(DataSourceTilesPath datasource) {
    super(datasource);
    this.setDefaultSymbolizer(new ImageSymbolizer());
    downloadEnabled = true;
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
    double dx = 360 / n;
    int xmin = (int) ((args.getFrame().getEnvelopeMercator().getMin().getX() + 180) / dx);
    if (xmin < 0) {
      xmin = 0;
    }
    int ymin = (int) ((180 - args.getFrame().getEnvelopeMercator().getMax().getY()) / dx);
    if (ymin < 0) {
      ymin = 0;
    }
    int xmax = (int) ((args.getFrame().getEnvelopeMercator().getMax().getX() + 180) / dx);
    if (xmax > n - 1) {
      xmax = (int) n - 1;
    }
    int ymax = (int) ((180 - args.getFrame().getEnvelopeMercator().getMin().getY()) / dx);
    if (ymax > n - 1) {
      ymax = (int) n - 1;
    }

    // the source provides tiles for the current viewing resolution
    DataSourceTilesPath source = this.getDataSource();

    // upsource has all the same information, but is set to be one level
    // lower resolution than our current scale.
    DataSourceTilesPath upsource = this.getDataSource().copy();
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
        source.setExt(".shp");
        if (source.cachedTileExists()) {
          Optional<MarkerPathRangeDataset> img = source.getDataset();
          if (img.isPresent()) {
            paintTile(args, img.get());
          }

        } else {

          // The tile does not exist, so
          Envelope env = source.getEnvelopeMercator();
          Projector viewProj = new Projector(args.getFrame());
          Rectangle r = viewProj.getRectangle(env);
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
            Optional<MarkerPathRangeDataset> img = upsource.getDataset();
            if (img.isPresent()) {
              paintQuad(args, img.get(), r);
            }
          }

          // Draw a yellow rectangle to indicate that tiles were missing.
          args.getGraphics().setColor(Color.yellow);
          args.getGraphics().drawRect(r.x, r.y, r.width, r.height);
          // regardless of whether we could paint a backup or not, mark the tile as downloading
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
   * @param frame
   * @return
   */
  public int getScale(DataFrame frame) {
    // mapZoom in longitude per pixel
    double w = frame.getView().getWidth();
    double mapZoom = frame.getEnvelopeMercator().getWidth() / w;
    // degrees/pixel

    // from the longitude per pixel, estimate how many 256x256 tiles are required
    double n = 360 / (mapZoom * 256);

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
   * @param args
   */
  @Override
  public void paint(PaintArgs args) {
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
  protected void paintQuad(PaintArgs e, MarkerPathRangeDataset img, Rectangle clip) {

    Envelope bounds = img.getEnvelopeMercator();
    Projector viewProj = new Projector(e.getFrame());
    Envelope view = viewProj.getEnvelope(clip);
    if (!view.intersects(bounds)) {
      return;
    }
    /*
     Envelope intersection = view.intersect(bounds);
     Projector imageProj = new Projector(img);
     Rectangle source = imageProj.getRectangle(intersection);
     Rectangle dest = viewProj.getRectangle(intersection);

     e.getGraphics().drawImage(img.getImage(),
     dest.x, dest.y, dest.x + dest.width, dest.y + dest.height,
     source.x, source.y, source.x + source.width, source.y + source.height, null);
     */
  }

  /**
   * Even though the paintWeb permits downloading, this layer may be disabled.
   * So pass in the downloadEnabled value to control download.
   *
   * @param args
   */
  @Override
  public void paintWeb(@NonNull PaintArgs args) {
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
  public boolean isDownloadEnabled() {
    return downloadEnabled;
  }

  /**
   * Sets a boolean, that if true will allow this layer to attempt to download
   * tiles from the Internet if the cached tiles are not found.
   *
   * @param downloadEnabled the downloadEnabled to set
   */
  public void setDownloadEnabled(boolean downloadEnabled) {
    this.downloadEnabled = downloadEnabled;
  }

  //</editor-fold>
  /**
   *
   * @param args
   * @param markerPathRange
   */
  public void paintTile(@NonNull PaintArgs args,
      @NonNull MarkerPathRangeDataset markerPathRange) {
    List<PathRangeMarker> markers = markerPathRange.getMarkers();
    Graphics2D g = args.getGraphics();
    Envelope mercBounds = args.getFrame().getEnvelopeMercator();
    Envelope geoBounds = Mercator.fromMerc(mercBounds);
    AffineTransform original = g.getTransform();

    Projector proj = new Projector(args.getFrame());
    g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
        RenderingHints.VALUE_FRACTIONALMETRICS_ON);
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
    g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

    for (PathRangeMarker marker : markers) {
      LineSymbolizer symbolizer = marker.getSymbolizer();
      if (symbolizer == null) {
        //symbolizer = getDefaultSymbolizer();
        symbolizer = new LineSymbolizer();
        //System.out.println("Error no symbolizer defined.");
      }

      // Skip this line if it is not in the geographic envelope.  The frame
      // is in mercator coordinates so it has to be translated to use the geographic shape.
      if (marker.getShape() == null) {
        continue;
      }

      /*
       if (!geoBounds.intersects2D(marker.getShape().getBounds())) {
       continue;
       }
       */
      Optional<GeneralPath> path = marker.getPath(proj);
      if (path.isPresent()) {
        if (!marker.isSelected()) {
          for (Pen pen : symbolizer.getPens()) {
            g.setStroke(pen.getStroke());
            g.setColor(pen.getColor());
            g.draw(path.get());
          }
        } else {
          // Selected uses the width of the wider of the two strokes.
          g.setStroke(new BasicStroke(symbolizer.getSelectedWidth()));
          g.setColor(symbolizer.getSelectedColor());
          g.draw(path.get());
        }
      }

    }
    g.setTransform(original);
  }
}
