/*
 * The MIT License
 *
 * Copyright 2015 hdunsford.
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
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.map.Mercator;
import javax.swing.SwingUtilities;

/**
 * This tool does nothing unless someone has added menu items to the popup menu.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PopupMapTool extends MapTool {

  @Override
  public void mousePressed(MapEventMouse e) {
    if (e.getMap().getPopupMenu() != null && e.getMap().getPopupMenu().getSubElements().length > 0) {
      if (SwingUtilities.isRightMouseButton(e)) {
        Coord tedMerc = e.getMap().getContent().getProjector().getCoordinate(e.getPoint());
        Coord wgs84 = Mercator.fromMerc(tedMerc);
        e.getMap().setPopupLocation(wgs84);
        e.getMap().getPopupMenu().show(e.getMap(), e.getX(), e.getY());
      }
    }

  }

}
