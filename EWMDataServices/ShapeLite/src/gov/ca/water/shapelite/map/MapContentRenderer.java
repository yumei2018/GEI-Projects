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

import gov.ca.water.shapelite.map.layer.Layer;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import gov.ca.water.shapelite.Envelope;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D./kprins
 */
public class MapContentRenderer implements Runnable {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The List of Layer objects that are being rendered. This is cycled independently
   * from the actual map layer list, in order to reduce the chance of concurrent list
   * modification exceptions.
   */
  private List<Layer<?>> layers;
  /**
   * The boolean that indicates that this renderer should start over.
   */
  private boolean reset;
  /**
   * The MapContent that is being rendered by this control.
   */
  private final MapContent content;
  /**
   * The rectangular view. This is not necessarily the client rectangle, as it may in fact
   * be smaller than the client rectangle. It is the pixel rectangle that the map extent
   * will be drawn to.
   */
  private Rectangle view;
  /**
   * The geographic extent to be rendered. This is in Mercator coordinates that range from
   * -180 to 180 in both x and y coordinates. X corresponds to longitude, but Y does not
   * correspond to Latitude. Use the Mercator class to translate.
   */
  private Envelope envelope;
  //</editor-fold>

  /**
   * Creates a new instance of the MapContentRenderer, providing the MapContent to render
   * as a parameter.
   *
   * @param content the MapContent containing the set of Layer object to render.
   */
  public MapContentRenderer(MapContent content) {
    this.content = content;
  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * The runnable method used by the render thread to actually render content, but with a
   * slight delay, enabling "updates" to occur if someone is actively moving the mouse
   * during a zoom operation.
   */
  @Override
  public void run() {
    BufferedImage background;
    do {
      try {
        // this background thread version doesn't want to get called too quickly or else
        // the processing of this step will interfere with resizing the control.
        Thread.sleep(300);
      } catch (InterruptedException ex) {
        Logger.getLogger(MapContentRenderer.class.getName()).log(Level.SEVERE,
                null, ex);
      }

      reset = false;
      view = content.getView();
      envelope = content.getEnvelopeMercator();
      layers = new ArrayList<>(content.getLayers());
      if (view == null || view.width == 0 || view.height == 0) {
        return;
      }
      background = new BufferedImage(view.width, view.height,
              BufferedImage.TYPE_INT_ARGB);
      Graphics2D graphics = background.createGraphics();
      PaintArgs args = new PaintArgs(this, graphics,
              new GeoFrame(this.envelope, this.view));

      graphics.setColor(Color.WHITE);
      graphics.fillRect(view.x, view.y, view.width, view.height);
      double scale = args.getFrame().getScale();
      for (Layer<?> layer : layers) {
        if (!layer.isVisible()) {
          continue;
        }
        if (layer.getMaxScale() < 0 || layer.getMaxScale() > scale) {
          if (layer.getMinScale() < 0 || layer.getMinScale() < scale) {
            layer.paintWeb(args);
          }
        }
        if (reset) {
          // break the for loop of layers, not the do loop instructing us to reset.
          break;
        }
      }
      graphics.dispose();

    } while (reset);

    content.setDisplay(background);
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the boolean that indicates that this renderer should start over.
   *
   * @return the reset
   */
  public boolean isReset() {
    return reset;
  }

  /**
   * Sets the boolean that indicates that this renderer should start over.
   *
   * @param canceled
   */
  public void setReset(boolean canceled) {
    this.reset = canceled;
  }
  //</editor-fold>
}
