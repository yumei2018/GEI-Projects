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
package gov.ca.water.shapelite.map.maptool;

import gov.ca.water.shapelite.events.MapEventDisplay;
import gov.ca.water.shapelite.events.MapEventMouseWheel;
import gov.ca.water.shapelite.data.DataFrame;
import gov.ca.water.shapelite.data.Projector;
import java.awt.Rectangle;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.map.GeoFrame;
import gov.ca.water.shapelite.map.MapContent;
import gov.ca.water.shapelite.map.MapPaintArgs;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ScrollZoomMapTool extends MapTool {

  private boolean scrolling;
  private double scaleFactor;

  private boolean needsUpdate;
  private boolean paintingZoomPreview;
  MapContent content;
  Coord location;

  public ScrollZoomMapTool() {

  }

  /**
   * Paints the content.
   *
   * @param e
   */
  @Override
  public void paintContent(MapPaintArgs e) {
    if (!paintingZoomPreview) {
      return;
    }
    if (scrolling) {
      if (Double.isNaN(scaleFactor)) {
        return;
      }
      Envelope env = content.getEnvelopeMercator().zoom(scaleFactor, location);
      Rectangle rect = content.getView();
      GeoFrame frame = new GeoFrame(env, rect);
      Projector proj = new Projector(frame);
      if (content.getDisplayEnvelope() == null) {
        return;
      }
      Rectangle dest = proj.getRectangle(content.getDisplayEnvelope());
      e.getGraphics().drawImage(content.getDisplay(), dest.x, dest.y,
          dest.width, dest.height, null);
      MapPaintArgs args = new MapPaintArgs(e.getMap(), e.getGraphics(), frame);
      content.paintDynamicLayers(args);
      scrolling = false;
      paintingZoomPreview = false;
      content.setEnvelope(env);
      paintingZoomPreview = true;
      // Set to handle the long re-draw, but allow it to be reset by fast scrolling.
      content.invalidate(content.getView());

      e.getMap().setUpdatingContent(true);
      needsUpdate = true;
    }

  }

  /**
   * After the map has drawn a zoomed in view, the stretched preview need no
   * longer be shown.
   *
   * @param e The MapEventDisplay argument.
   */
  @Override
  public void renderComplete(MapEventDisplay e) {
    if (needsUpdate) {
      e.getMap().getContent().setVisible(true);
      paintingZoomPreview = false;
      e.getMap().paintImmediately(new Rectangle(0, 0, e.getMap().getWidth(),
          e.getMap().getHeight()));
      e.getMap().setUpdatingContent(false);
    }
    needsUpdate = false;
  }

  /**
   * Handles the mouse wheel movement and triggers zooming.
   *
   * @param e The MapEventMouseWheel argument.
   */
  @Override
  public void mouseWheelMoved(MapEventMouseWheel e) {
    int count = e.getWheelRotation();
    if (count == 0) {
      return;
    }
    scaleFactor = Math.pow(.9, -count);
    snapScaleFactor(e.getMap().getContent());
    location = e.getLocation();
    scrolling = true;
    e.getMap().repaint();
    content = e.getMap().getContent();
    content.setVisible(false);
    paintingZoomPreview = true;
  }

  public void snapScaleFactor(DataFrame f) {

    double mapScale = f.getEnvelopeMercator().getWidth() / f.getView().getWidth();
    double target = mapScale;

    // from the longitude per pixel, estimate how many 256x256 tiles are required
    double n = 360 / (target * 256);

    // using the number of tiles, estimate the tile level that has approximately that many.
    double level = Math.log(n) / Math.log(2);

    level = Math.round(level);
    if (scaleFactor > 1) {
      level = level - 1;
    } else {
      level = level + 1;
    }
    if (level < 0) {
      // For the top most tile we don't use snapping
      return;
    }

    // using the rounded tile scale, we now need the degrees to pixels of that scale
    double dpp = 360 / (256 * Math.pow(2, level));

    // Adjust the factor so that when we use it, it will result in a new scale
    // that is exactly one of the tile level scales.
    scaleFactor = dpp / mapScale;

  }

  /**
   * @return the paintingZoomPreview
   */
  public boolean isPaintingZoomPreview() {
    return paintingZoomPreview;
  }

}
