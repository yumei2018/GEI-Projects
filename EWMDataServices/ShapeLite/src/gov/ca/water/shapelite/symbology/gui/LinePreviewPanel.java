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

import gov.ca.water.shapelite.data.marker.LineMarker;
import gov.ca.water.shapelite.map.rendering.DefaultLineRenderer;
import gov.ca.water.shapelite.symbology.LineSymbolizer;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.GeneralPath;
import javax.swing.JPanel;
import gov.ca.water.shapelite.map.rendering.TextRenderer;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class LinePreviewPanel extends JPanel {

  /**
   * The pixels of spacing between the edge of the panel and the line shape.
   */
  private static final int PADDING = 10;

  /**
   * The marker, which basically controls the selection state.
   */
  private LineMarker marker;

  /**
   * The renderer for drawing the point.
   */
  private TextRenderer renderer;

  /**
   * The symbolizer that controls the symbology in the preview.
   */
  private LineSymbolizer symbolizer;

  /**
   * Creates a new instance of the MarkerPreviewPanel.
   */
  public LinePreviewPanel() {
    renderer = new DefaultLineRenderer();
    marker = new LineMarker();
    symbolizer = new LineSymbolizer();
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
      GeneralPath gp = new GeneralPath();
      gp.moveTo(PADDING, center.y);
      gp.lineTo(this.getWidth() - PADDING, center.y);
      renderer.paint(g2, gp, marker, symbolizer);
    }
  }

  /**
   * Gets the marker, which currently controls the selection state.
   *
   * @return the marker
   */
  public final LineMarker getMarkerPath() {
    return marker;
  }

  /**
   * Sets the marker, which currently controls the selection state.
   *
   * @param markerPath the marker to set.
   */
  public final void setMarkerPath(LineMarker markerPath) {
    this.marker = markerPath;
  }

  /**
   * Gets the renderer for drawing the point.
   *
   * @return the renderer
   */
  public final TextRenderer getRenderer() {
    return renderer;
  }

  /**
   * Set the renderer for drawing the point.
   *
   * @param renderer the renderer to set
   */
  public final void setRenderer(TextRenderer renderer) {
    this.renderer = renderer;
  }

  /**
   * Gets the symbolizer that controls the symbology in the preview.
   *
   * @return the symbolizer
   */
  public final LineSymbolizer getSymbolizer() {
    return symbolizer;
  }

  /**
   * Sets the symbolizer that controls the symbology in the preview.
   *
   * @param symbolizer the symbolizer to set
   */
  public final void setSymbolizer(LineSymbolizer symbolizer) {
    this.symbolizer = symbolizer;
  }

}
