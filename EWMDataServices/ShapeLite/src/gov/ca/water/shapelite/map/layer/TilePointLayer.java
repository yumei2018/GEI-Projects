/*
 * The MIT License
 *
 * Copyright 2015 Harold A. Dunsford Jr. Ph.D..
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
import gov.ca.water.shapelite.analysis.TileLocation;
import gov.ca.water.shapelite.symbology.PointSymbolizer;
import gov.ca.water.shapelite.data.TileUrl;
import gov.ca.water.shapelite.map.PaintArgs;
import gov.ca.water.shapelite.map.PointTileFile;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashMap;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class TilePointLayer extends Layer<PointSymbolizer> {

  /**
   * This caches information about files in memory so that it doesn't need to
   * check the file system.
   */
  private final HashMap<Point, PointTileFile> fileCache;

  /**
   * MapLayerTilePoints constructor.
   */
  public TilePointLayer() {
    fileCache = new HashMap<>();
    setDefaultSymbolizer(new PointSymbolizer());
  }

  /**
   * The base url without scale, tileX or tileY information.
   */
  private String baseUrl;

  /**
   * The zoom scale from 0 to 20.
   */
  private int zoomLevel;

  /**
   * @return the baseUrl
   */
  public final String getBaseUrl() {
    return baseUrl;
  }

  /**
   * @param baseUrl the baseUrl to set
   */
  public final void setBaseUrl(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  /**
   * @return the zoomLevel
   */
  public final int getZoomLevel() {
    return zoomLevel;
  }

  /**
   * @param zoomLevel the zoomLevel to set
   */
  public final void setZoomLevel(int zoomLevel) {
    this.zoomLevel = zoomLevel;
  }

  /**
   * Paints a symbolic representation of this layer.
   *
   * @param args The PaintArgs object with frame and graphics surface info.
   */
  @Override
  public final void paint(PaintArgs args) {
    int level = args.getFrame().getClosestLevel();
    args.getGraphics().setColor(this.getDefaultSymbolizer().getFillColor());
    if (level < zoomLevel) {
      // at smaller zoom levels, just show the scale 16 tile bounds as squares.
      Envelope env = args.getFrame().getEnvelopeMercator();
      TileLocation bl = TileUrl.getTileLocationMercator(zoomLevel, env.getMin());
      TileLocation tr = TileUrl.getTileLocationMercator(zoomLevel, env.getMax());
      for (int tileY = tr.getTileY(); tileY <= bl.getTileY(); tileY++) {
        for (int tileX = bl.getTileX(); tileX <= tr.getTileX(); tileX++) {
          Point pt = new Point(tileX, tileY);
          PointTileFile file;
          if (fileCache.containsKey(pt)) {
            file = fileCache.get(pt);
          } else {
            TileUrl url = new TileUrl(baseUrl, zoomLevel, tileX, tileY, ".pts");
            file = new PointTileFile(url);
            fileCache.put(pt, file);
          }
          if (file != null && file.isPresent()) {
            Rectangle rect = args.getFrame().getProjector().getRectangle(
                file.getEnvelopeMercator());
            args.getGraphics().fillRect(rect.x, rect.y, rect.width, rect.height);
          }
        }
      }
    }
  }

}
