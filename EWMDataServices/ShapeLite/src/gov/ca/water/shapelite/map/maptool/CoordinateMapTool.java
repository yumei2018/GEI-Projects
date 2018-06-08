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

import gov.ca.water.shapelite.events.MapEventMouse;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.text.DecimalFormat;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.map.MapPaintArgs;
import gov.ca.water.shapelite.map.Mercator;
import java.awt.Point;

/**
 * This class draws a box which shows the X, Y, Scale and Level.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class CoordinateMapTool extends MapTool {

  /**
   * The coordinate position of the mouse.
   */
  Coord c;
  Point tile;

  public CoordinateMapTool() {
    c = new CoordXY(0, 0);
    tile = new Point(0,0);
  }

  /**
   * This is drawn after the map, and all of the tools implement paintContent.
   * The coordinate panel is in a fixed location at the bottom right of the
   * control.
   *
   * @param args
   */
  @Override
  public void paint(MapPaintArgs args) {
    Rectangle view = args.getFrame().getView();
    if (view.width < 450 || view.height < 20) {
      return;
    }
    args.getGraphics().setColor(Color.LIGHT_GRAY);
    args.getGraphics().fillRect(view.width - 450, view.height - 20, 450, 20);
    args.getGraphics().draw3DRect(view.width - 450, view.height - 20, 450, 20, false);
    args.getGraphics().setColor(Color.BLACK);
    DecimalFormat format = new DecimalFormat("#,###");
    DecimalFormat coord = new DecimalFormat("0.00000000");
    String text = "X: " + coord.format(c.getX()) + ", Y: " + coord.format(c.getY())
            + ", Scale: 1:" + format.format(args.getMap().getContent().getScale()) + ","
            + " Level: " + args.getMap().getContent().getClosestLevel()
            + " TX: " + tile.x
            + " TY: " + tile.y;
    FontMetrics fm = args.getGraphics().getFontMetrics(args.getGraphics().getFont());
    java.awt.geom.Rectangle2D rect = fm.getStringBounds(text, args.getGraphics());
    int offset = (int) (view.width - 500 + (500.0 - rect.getWidth()) / 2.0);
    int bottom = view.height - (int) ((20 - rect.getHeight()) / 2.0);
    args.getGraphics().drawString(text, offset, bottom);
  }

  /**
   * Occurs when the mouse moves. This updates the location that provides
   * information for the coordinate panel.
   *
   * @param e the MapEventMouse that provides information about the mouse
   * location and the Map control.
   */
  @Override
  public void mouseMoved(MapEventMouse e) {
    if (e.getMap().isUpdatingContent()) {
      // The view is not stable at the moment because we are zooming, so don't draw.
      return;
    }
    c = e.getLocation();
    int x = e.getMap().getWidth() - 500;
    int y = e.getMap().getHeight() - 20;
    if (x < 0 || y < 0) {
      return;
    }
    int level = e.getMap().getContent().getClosestLevel();
    Coord merc = Mercator.toMerc(c);
    double dx = 360 / Math.pow(2, level);
    tile.x = (int) ((180 + merc.getX()) / dx);
    tile.y = (int) ((180 - merc.getY()) / dx);
    e.getMap().repaint(new Rectangle(x, y, 500, 20));
  }

  /**
   * Occurs when the mouse enters the panel. Don't show the coordinate panel if
   * the mouse is not present.
   *
   * @param e
   */
  @Override
  public void mouseEntered(MapEventMouse e) {
    this.setVisible(true);
  }

  /**
   * Occurs when the mouse exits the panel.
   *
   * @param e
   */
  @Override
  public void mouseExited(MapEventMouse e) {
    this.setVisible(false);
  }

}
