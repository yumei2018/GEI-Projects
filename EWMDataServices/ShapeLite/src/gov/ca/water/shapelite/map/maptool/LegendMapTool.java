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
package gov.ca.water.shapelite.map.maptool;

import gov.ca.water.shapelite.legend.internal.LegendPanel;
import gov.ca.water.shapelite.events.MapElementClosedEvent;
import gov.ca.water.shapelite.map.element.MovableComponentMapElement;
import gov.ca.water.shapelite.map.MapPanel;
import gov.ca.water.shapelite.map.element.AnchorDirection;
import java.awt.Rectangle;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class LegendMapTool extends MapTool {

  /**
   * The default Y anchor location.
   */
  private static final int DEFAULT_Y = 250;

  /**
   * Default Bounds.
   */
  public static final Rectangle DEFAULT_BOUNDS
          = new Rectangle(1000, DEFAULT_Y, 100, 200);

  /**
   * Movable legend.
   */
  private MovableComponentMapElement movableLegend;

  /**
   * The actual legend panel.
   */
  private LegendPanel legend;

  /**
   * Create a new instance of the Map Tool Legend.
   *
   * @param map The map with layers to be reflected in the legend content.
   */
  public LegendMapTool(MapPanel map) {
    super.setName("legend");
    movableLegend = new MovableComponentMapElement();
    movableLegend.setBoundsFrom(DEFAULT_BOUNDS);
    movableLegend.setVisible(true);
    movableLegend.setAnchorRight(true);
    movableLegend.setTitle("Legend");
    legend = new LegendPanel();
    legend.setMap(map);
    movableLegend.setMap(map);
    movableLegend.setComponent(legend);
    movableLegend.addMapElementClosedListener(new MapElementClosedEvent.Listener() {
      @Override
      public void mapElementClosed(MapElementClosedEvent e) {
        setVisible(false);
      }
    });
    super.getElements().add(movableLegend);

  }

  @Override
  public void setVisible(boolean visible) {
    legend.setVisible(visible);
    movableLegend.setVisible(visible);
    super.setVisible(visible); //To change body of generated methods, choose Tools | Templates.
  }

  public void setTitle(String title) {
    if (this.movableLegend != null) {
      this.movableLegend.setTitle(title);
    }
  }

  /**
   * Resets the legend's x and y to the original right anchored configuration.
   */
  public final void resetLocation() {
    movableLegend.setRight(null);
    movableLegend.setAnchorRight(true);
    movableLegend.setAnchor(AnchorDirection.TopRight);
    movableLegend.resetBounds();
  }

  /**
   * @return the movableLegend
   */
  public final MovableComponentMapElement getMovableLegend() {
    return movableLegend;
  }

  /**
   * @param movableLegend the movableLegend to set
   */
  public final void setMovableLegend(MovableComponentMapElement movableLegend) {
    this.movableLegend = movableLegend;
  }

  /**
   * @return the legend
   */
  public final LegendPanel getLegend() {
    return legend;
  }

  /**
   * @param legend the legend to set
   */
  public final void setLegend(LegendPanel legend) {
    this.legend = legend;
  }

}
