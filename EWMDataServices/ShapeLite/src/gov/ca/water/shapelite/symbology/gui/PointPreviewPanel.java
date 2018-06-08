/*
 * The MIT License
 *
 * Copyright 2016 Harold A. Dunsford Jr. Ph.D..
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
package gov.ca.water.shapelite.symbology.gui;

import gov.ca.water.shapelite.data.marker.PointMarker;
import gov.ca.water.shapelite.map.rendering.DefaultPointRenderer;
import gov.ca.water.shapelite.symbology.PointSymbolizer;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JPanel;
import gov.ca.water.shapelite.map.rendering.PointRenderer;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PointPreviewPanel extends JPanel {

  /**
   * The marker, which basically controls the selection state.
   */
  private PointMarker marker;

  /**
   * The renderer for drawing the point.
   */
  private PointRenderer renderer;

  /**
   * The symbolizer that controls the symbology in the preview.
   */
  private PointSymbolizer symbolizer;

  /**
   * Creates a new instance of the MarkerPreviewPanel.
   */
  public PointPreviewPanel() {
    renderer = new DefaultPointRenderer();
    marker = new PointMarker();
    symbolizer = new PointSymbolizer();
  }

  /**
   *
   * @param g
   */
  @Override
  protected final void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (g instanceof Graphics2D) {
      Graphics2D g2 = (Graphics2D) g;
      Point center = new Point(this.getWidth() / 2, this.getHeight() / 2);
      renderer.paint(g2, center, marker, symbolizer);
    }
  }

  /**
   * @return the marker
   */
  public final PointMarker getMarker() {
    return marker;
  }

  /**
   * @param marker the marker to set
   */
  public final void setMarker(PointMarker marker) {
    this.marker = marker;
  }

  /**
   * Gets the renderer for drawing the point.
   * @return the renderer
   */
  public final PointRenderer getRenderer() {
    return renderer;
  }

  /**
   * Set the renderer for drawing the point.
   * @param renderer the renderer to set
   */
  public final void setRenderer(PointRenderer renderer) {
    this.renderer = renderer;
  }

  /**
   * Gets the symbolizer that controls the symbology in the preview.
   * @return the symbolizer
   */
  public final PointSymbolizer getSymbolizer() {
    return symbolizer;
  }

  /**
   * Sets the symbolizer that controls the symbology in the preview.
   * @param symbolizer the symbolizer to set
   */
  public final void setSymbolizer(PointSymbolizer symbolizer) {
    this.symbolizer = symbolizer;
  }

}
