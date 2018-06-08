package gov.ca.water.shapelite.symbology;

import gov.ca.water.shapelite.labels.LabelBase;
import gov.ca.water.shapelite.labels.grahics.MapCanvas;

/**
 * A Symbolizer to paint Label on a MapCanvas.
 *
 * @author J.G. "Koos" Prins, D.Eng. PE.
 * @param <TLabel> The type of the label.
 */
public abstract class TextSymbolizer<TLabel extends LabelBase> implements Symbolizer {

  /**
   * Public Constructor.
   */
  protected TextSymbolizer() {
    super();
  }

  /**
   * Called to prepare the canvas and internal settings, before start calling {@linkplain
   * #paint(gov.ca.water.shapelite.labels.grahics.Canvas,
   * gov.ca.water.shapelite.labels.LabelBase) this.paint(canvas, label)} to
   * paint every label.
   *
   * @param canvas the new canvas to paint on
   * @throws Exception is an error occurred to stop the process.
   */
  public abstract void prepareCanvas(MapCanvas canvas) throws Exception;

  /**
   * Called after completing the Layer's paint request to reset the canvas
   * settings.
   */
  public abstract void resetCanvas();

  /**
   * Call - after calling {@linkplain #prepareCanvas(
   * gov.ca.water.shapelite.labels.grahics.Canvas) this.prepareCanvas(canvas)} -
   * to paint each label on the canvas.
   *
   * @param canvas the canvas to paint on
   * @param label the Label to paint
   */
  public abstract void paint(MapCanvas canvas, TLabel label);

}
