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

import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.data.marker.PolygonMarker;
import gov.ca.water.shapelite.map.rendering.DefaultPolygonRenderer;
import gov.ca.water.shapelite.symbology.PolygonSymbolizer;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.GeneralPath;
import javax.swing.JPanel;
import gov.ca.water.shapelite.map.rendering.PolygonRenderer;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PolygonPreviewPanel extends JPanel {

  /**
   * The distance in pixels to indent before drawing the sample polygon rectangle.
   */
  private static final int PADDING = 20;

  /**
   * The marker, which currently just controls the selection state.
   */
  private PolygonMarker marker;

  /**
   * The renderer for drawing the point.
   */
  private PolygonRenderer renderer;

  /**
   * The symbolizer that controls the symbology in the preview.
   */
  private PolygonSymbolizer symbolizer;

  /**
   * Creates a new instance of the MarkerPreviewPanel.
   */
  public PolygonPreviewPanel() {
    renderer = new DefaultPolygonRenderer();
    marker = new PolygonMarker(Shape.getNullShape(), "none");
    symbolizer = new PolygonSymbolizer();
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
      GeneralPath gp = new GeneralPath();
      gp.moveTo(PADDING, PADDING);
      gp.lineTo(this.getWidth() - PADDING, PADDING);
      gp.lineTo(this.getWidth() - PADDING, this.getHeight() - PADDING);
      gp.lineTo(PADDING, this.getHeight() - PADDING);
      gp.lineTo(PADDING, PADDING);
      gp.closePath();
      renderer.paint(g2, gp, marker, symbolizer,
          new Rectangle(0, 0, this.getWidth(), this.getHeight()));
    }
  }



  /**
   * Gets the marker, which currently just controls the selection state.
   *
   * @return the marker
   */
  public final PolygonMarker getMarker() {
    return marker;
  }

  /**
   * Sets the marker, which currently just controls the selection state.
   *
   * @param marker the marker to set
   */
  public final void setMarker(PolygonMarker marker) {
    this.marker = marker;
  }

  /**
   * Gets the renderer for drawing the point.
   *
   * @return the renderer
   */
  public final PolygonRenderer getRenderer() {
    return renderer;
  }

  /**
   * Sets the renderer for drawing the point.
   *
   * @param renderer the renderer to set
   */
  public final void setRenderer(PolygonRenderer renderer) {
    this.renderer = renderer;
  }

  /**
   * Gets the symbolizer that controls the symbology in the preview.
   *
   * @return the symbolizer
   */
  public final PolygonSymbolizer getSymbolizer() {
    return symbolizer;
  }

  /**
   * Sets the symbolizer that controls the symbology in the preview.
   *
   * @param symbolizer the symbolizer to set
   */
  public final void setSymbolizer(PolygonSymbolizer symbolizer) {
    this.symbolizer = symbolizer;
  }

}
