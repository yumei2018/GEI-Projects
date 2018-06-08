package gov.ca.water.shapelite.labels.grahics;

/**
 * A base interface for any renderable component that can be called to paint the 
 * component to the specified 2D canvas. It call {@linkplain #prepareCanvas(
 * gov.ca.water.chart.ChartCanvas) this.prepareCanvas} to prepare the canvas for 
 * rendering and if this method returns true, its {@linkplain #paint(g
 * ov.ca.water.chart.ChartCanvas) this.paint} will be called.
 * @author Harold A. Dunsford Jr. Ph.D./kprins
 */
public interface Renderable {
  
  /**
   * Called before the paint method to prepare the Canvas for rendering the element (e.g.,
   * set the font before paint a {@linkplain ChartText}
   * @param canvas the canvas to prepare
   * @return true if preparing the canvas was successful and the element is ready to be 
   * painted
   */
  public boolean prepareCanvas(MapCanvas canvas);

  /**
   * Called to paint the Renderable to the canvas
   * @param canvas the canvas to draw on.
   */
  public void paint(MapCanvas canvas);
}
