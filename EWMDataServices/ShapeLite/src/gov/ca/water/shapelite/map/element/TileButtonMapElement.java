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
package gov.ca.water.shapelite.map.element;

import gov.ca.water.shapelite.map.layer.TileLayer;
import gov.ca.water.shapelite.events.MapEventMouse;
import gov.ca.water.shapelite.map.MapPaintArgs;
import gov.ca.water.shapelite.map.MapTileType;
import gov.ca.water.shapelite.map.ShadowRoundedRectangle;
import gov.ca.water.shapelite.map.maptool.MapTool;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import javax.swing.ToolTipManager;

/**
 * This map element shows directional buttons that allow the user to pan using a
 * click of the mouse.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class TileButtonMapElement extends MapElement {

  /**
   * A padding distance around the image in pixels.
   */
  private static final int PADDING = 4;

  /**
   * Street Map index.
   */
  private static final int STREET = 0;

  /**
   * Satellite Map index.
   */
  private static final int SATELLITE = 1;

  /**
   * Topo Map index.
   */
  private static final int TOPO = 2;

  /**
   * The integer number of layers that this button can work with.
   */
  private static final int NUM_LAYERS = 3;


  /**
   * The duration of the tool tip in milliseconds.
   */
  private static final int TOOL_TIP_SUSTAIN = 3000;
  //<editor-fold defaultstate="collapsed" desc="Fields">


  /**
   * True if the button is pressed.
   */
  private boolean isPressed;

  /**
   * An enum that represents the currently visible map type.
   */
  private MapTileType currentMapType;

  /**
   * The layer on the map that should be made visible in satellite mode.
   */
  private final TileLayer[] layers;

  /**
   * Gets the array of images to use.
   */
  private final Image[] images;

  /**
   * A boolean that is true if the tool tip is showing.
   */
  private boolean showingToolTip;

  //</editor-fold>
  /**
   * Creates a new instance of the map buttons.
   */
  public TileButtonMapElement() {
    layers = new TileLayer[NUM_LAYERS];
    images = new Image[NUM_LAYERS];
    this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    this.currentMapType = MapTileType.StreetMap;
  }

  /**
   * Tests the mouse click at the specified point for intersection with this
   * element.
   *
   * @param pt The Point.
   * @return Boolean, if the point intersected with this element.
   */
  @Override
  public final boolean intersects(Point pt) {
    if (super.getBounds() != null) {
      return super.getBounds().contains(pt);
    }
    return false;
  }

  /**
   * Handles the mouseDragged event on this button.
   *
   * @param e A MapEventMouse argument which includes information about the
   * mouse as well as the Map control itself.
   */
  @Override
  public final void mouseDragged(MapEventMouse e) {
    if (this.getBounds().contains(e.getPoint())) {
      e.setHandled(true);
      return;
    }
    super.mouseDragged(e);
  }

  /**
   * Handles the mouseMoved event on this button. This is mostly used to update
   * the centerX, centerY, differenceX and differenceY
   *
   * @param e A MapEventMouse argument which includes information about the
   * mouse as well as the Map control itself.
   */
  /**
   * Handles the mouseMoved event on this button. This is mostly used to update
   * the centerX, centerY, differenceX and differenceY
   *
   * @param e A MapEventMouse argument which includes information about the
   * mouse as well as the Map control itself.
   */
  @Override
  public final void mouseMoved(MapEventMouse e) {
     MapTileType test = currentMapType.next();

      boolean intersects = this.getBounds().contains(e.getPoint());
      if (intersects && !showingToolTip) {
        e.getMap().setToolTipText("Switch to " + test.toString());
        ToolTipManager.sharedInstance().registerComponent(e.getMap());
        // Normally tool tips show slowly.  For the map, use no delay.
        ToolTipManager.sharedInstance().setDismissDelay(TOOL_TIP_SUSTAIN);
        showingToolTip = true;
      }
      if (showingToolTip && !intersects) {
        ToolTipManager.sharedInstance().setDismissDelay(0);
        e.getMap().setToolTipText(null);
        ToolTipManager.sharedInstance().unregisterComponent(e.getMap());
        showingToolTip = false;
      }


    super.mouseMoved(e);
  }

  /**
   * Handles the mousePressed event to determine if the button intersects with
   * the point being pressed. If true, then the event is considered handled.
   *
   * @param e The MapEventMouse event arguments contain information about the
   * mouse condition and the map.
   */
  @Override
  public final void mousePressed(MapEventMouse e) {
    if (intersects(e.getPoint())) {
      e.setHandled(true);
      isPressed = true;
    }
    super.mousePressed(e);
  }

  /**
   * The mouse is released.
   *
   * @param e The MapEventMouse event arguments contain information about the
   * mouse condition and the map.
   */
  @Override
  public final void mouseReleased(MapEventMouse e) {
    boolean toggleButton = true;
    if (!intersects(e.getPoint())) {
      toggleButton = false;
    }
    if (e.getMap().getActiveElement() != this) {
      toggleButton = false;
    }
    if (!isPressed) {
      toggleButton = false;
    }
    if (!intersects(e.getPoint())) {
      toggleButton = false;
    }

    if (toggleButton) {
      e.setHandled(true);
      isPressed = false;
      this.currentMapType = next();
      setLayer();
      e.getMap().getContent().paintImmediately();
      e.getMap().repaint();
    }
    super.mouseReleased(e);

  }

  /**
   * Sets the layer visibility to match the enumeration.
   */
  final void setLayer() {
    for (int i = 0; i < layers.length; i++) {
      if (layers[i] != null) {
        if (i == currentMapType.getIndex()) {
          layers[i].setVisible(true);
        } else {
          layers[i].setVisible(false);
        }
      }
    }
  }

  /**
   * Gets the next MapTileType. No modifier allows for package visibility for
   * testing.
   *
   * @return a MapTileType that has an associated non-null layer.
   */
  final MapTileType next() {
    int iCheck = layers.length;
    MapTileType test = currentMapType.next();
    while (iCheck > 0) {
      if (layers.length > test.getIndex() && layers[test.getIndex()] != null) {
        break;
      }
      if (test == currentMapType) {
        break;
      }
      test = test.next();
      iCheck--;
    }
    return test;
  }

  /**
   * Paints the button along with shadowing.
   *
   * @param args The MapPaintArgs containing information about the map and the
   * drawing.
   */
  @Override
  public final void paint(MapPaintArgs args) {
    Graphics2D g = args.getGraphics();
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
    ShadowRoundedRectangle rect = new ShadowRoundedRectangle();
    rect.setBounds(this.getBounds());
    rect.paint(g);
    int x = this.getBounds().x;
    int y = this.getBounds().y;
    // the image is reversed from the state on the map

    Image image = images[currentMapType.getIndex()];
    if(image != null){
      g.drawImage(image, x + PADDING, y + PADDING, null);
    }
  }

  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the 32x32 satellite Image icon.
   *
   * @return the satelliteImage
   */
  public final Image getSatelliteImage() {
    return images[SATELLITE];
  }

  /**
   * Sets the 32x32 satellite Image icon.
   *
   * @param satelliteImage the satelliteImage to set
   */
  public final void setSatelliteImage(Image satelliteImage) {
    this.images[SATELLITE] = satelliteImage;
  }

  /**
   * Gets the 32x32 icon representing street map.
   *
   * @return the streetMapImage
   */
  public final Image getStreetMapImage() {
    return images[STREET];
  }

  /**
   * Sets the 32x32 icon representing street map.
   *
   * @param streetMapImage the streetMapImage to set
   */
  public final void setStreetMapImage(Image streetMapImage) {
    this.images[STREET] = streetMapImage;
  }

  /**
   * Gets the layer on the map that should be made visible in satellite mode.
   *
   * @return the satelliteLayer
   */
  public final TileLayer getSatelliteLayer() {
    return layers[SATELLITE];
  }

  /**
   * Sets the layer on the map that should be made visible in satellite mode.
   *
   * @param satelliteLayer the satelliteLayer to set
   */
  public final void setSatelliteLayer(TileLayer satelliteLayer) {
    this.layers[SATELLITE] = satelliteLayer;
  }

  /**
   * Get the layer on the map that should be made visible in streetMap mode.
   *
   * @return the streetLayer
   */
  public final TileLayer getStreetLayer() {
    return layers[STREET];
  }

  /**
   * Set the layer on the map that should be made visible in streetMap mode.
   *
   * @param streetLayer the streetLayer to set
   */
  public final void setStreetLayer(TileLayer streetLayer) {
    this.layers[STREET] = streetLayer;
  }

  /**
   * Gets the 32x32 icon representing a topographic map.
   *
   * @return the topoImage
   */
  public final Image getTopoImage() {
    return images[TOPO];
  }

  /**
   * Sets the 32x32 icon representing a topographic map.
   *
   * @param topoImage the topoImage to set
   */
  public final void setTopoImage(Image topoImage) {
    this.images[TOPO] = topoImage;
  }

  /**
   * Gets the layer on the map that should be made visible in streetMap mode.
   *
   * @return the topoLayer
   */
  public final TileLayer getTopoLayer() {
    return layers[TOPO];
  }

  /**
   * Sets the layer on the map that should be made visible in streetMap mode.
   *
   * @param topoLayer the topoLayer to set
   */
  public final void setTopoLayer(TileLayer topoLayer) {
    this.layers[TOPO] = topoLayer;
  }

  /**
   * Gets an enum that represents the currently visible map type.
   *
   * @return the currentMapType
   */
  public final MapTileType getCurrentMapType() {
    return currentMapType;
  }

  /**
   * Gets an enum that represents the currently visible map type.
   *
   * @param type The MapTileType to set.
   */
  public final void setCurrentMapType(MapTileType type) {
    currentMapType = type;
    setLayer();
  }

  //</editor-fold>
}
