package gov.ca.water.shapelite.symbology;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.labels.BoxLabel;
import gov.ca.water.shapelite.labels.BoxStyleLabel;
import gov.ca.water.shapelite.labels.PlainLabel;
import gov.ca.water.shapelite.labels.LabelStyle;
import gov.ca.water.shapelite.labels.grahics.MapCanvas;
import gov.ca.water.shapelite.labels.grahics.CanvasFont;
import gov.ca.water.shapelite.labels.grahics.CanvasPoint;
import gov.ca.water.shapelite.labels.grahics.FillStyle;
import gov.ca.water.shapelite.labels.grahics.LineStyle;
import gov.ca.water.shapelite.labels.grahics.TextStyle;
import gov.ca.water.shapelite.map.Mercator;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author J.G. "Koos" Prins, D.Eng. PE.
 * @param <TLabel>
 */
public class LabelSymbolizer<TLabel extends PlainLabel>
    extends TextSymbolizer<TLabel> implements Serializable {

  /**
   * The width and height of the icon in the legend.
   */
  private static final int ICON_SIZE = 16;

  /**
   * Protected Static Logger object for logging errors, warnings, and info
   * messages.
   */
  protected static final Logger LOGGER
      = Logger.getLogger(LabelSymbolizer.class.getName());

  // <editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The lableStyle to use in generating the labels.
   */
  private LabelStyle labelStyle;
  /**
   * The Current Canvas' FontMetrics.
   */
  private transient FontMetrics fontMetrics;
  /**
   * The Current Canvas' labeling TextStyle.
   */
  private transient TextStyle textStyle;

  // </editor-fold>
  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Creates a new instance of the SymbolizerLabelPlain.
   */
  public LabelSymbolizer() {
    super();
    this.labelStyle = null;
  }

  /**
   * Public Constructor with a preset LabelStyle.
   *
   * @param labelStyle the set label style
   */
  public LabelSymbolizer(LabelStyle labelStyle) {
    super();
    this.labelStyle = labelStyle;
  }
  // </editor-fold>

  /**
   * Get a reference to the Symbolizer's LabelStyle.
   * <p>
   * <b>NOTE:</b> can call this method to get and initiate the LabelStyle</p>
   *
   * @return the reference to the symbolizer - lazy initiated if unassigned
   */
  public final LabelStyle getLabelStyle() {
    if (this.labelStyle == null) {
      this.labelStyle = new LabelStyle();
    }
    return this.labelStyle;
  }


  /**
   * {@inheritDoc}
   * <p>
   * OVERRIDE: Initiate the Canvas Font, this.fontMetrix, and this.textStyle</p>
   *
   * @throws java.lang.Exception
   */
  @Override
  public void prepareCanvas(MapCanvas canvas) throws Exception {
    LabelStyle style = this.getLabelStyle();
    CanvasFont canvasFont = style.getFont();
    this.fontMetrics = canvasFont.setCanvas(canvas);
    this.textStyle = style.getTextStyle();
  }

  /**
   * {@inheritDoc}
   * <p>
   * OVERRIDE: Reset this.fontMetrix, and call
   * this.labelStyle.canvasFont.resetCanvas</p>
   */
  @Override
  public void resetCanvas() {
    LabelStyle style = this.getLabelStyle();
    CanvasFont canvasFont = style.getFont();
    this.fontMetrics = null;
    canvasFont.resetCanvas();
  }

  /**
   * {@inheritDoc}
   * <p>
   * OVERRIDE: Called to paint the label</p>
   */
  @Override
  public void paint(MapCanvas canvas, TLabel label) {
    String text = null;
    Graphics2D graphics = null;
    Coord labelPoint = null;
    CanvasPoint insertPoint = null;
    LabelStyle style = this.getLabelStyle();
    CanvasFont canvasFont = style.getFont();
    if ((canvas == null) || ((graphics = canvas.graphics) == null)
        || (label == null) || ((text = label.getText()) == null)
        || ((text = text.trim()).length() == 0)
        || ((labelPoint = Mercator.toMerc(label.getPoint())) == null)
        || ((insertPoint = canvas.projToPixel(labelPoint)) == null)
        || (this.textStyle == null) || (this.fontMetrics == null)) {
      return;
    }

    AffineTransform curTransForm = graphics.getTransform();
    try {
      float textHeight = canvasFont.getTextHeight();
      float textWidth = canvasFont.getTextWidth(text);
      float x = insertPoint.getX();
      float y = insertPoint.getY();

      float rotate = label.getAngle();
      if (rotate != 0.0f) {
        AffineTransform transform = new AffineTransform();
        transform.translate(0.0d, 0.0d);
        transform.rotate(rotate, insertPoint.getX(), insertPoint.getY());
        graphics.setTransform(transform);
      }

      BoxStyleLabel boxStyle = null;
      BoxLabel box
          = new BoxLabel(insertPoint, textWidth, textHeight, canvas, style);
      if (style.hasLabelBoxStyle()) {
        boxStyle = style.getLabelBoxStyle();
        if (boxStyle.hasFill()) {
          FillStyle fill = boxStyle.getFillStyle();
          fill.paint(canvas, box.bounds);
        }
      }

      canvasFont.paint(graphics, text, box.textInsert, box.textStyle);

      if ((boxStyle != null) && (boxStyle.hasBorders())) {
        LineStyle borders = boxStyle.getBorderStyle();
        borders.paint(canvas, box.bounds);
      }
    } catch (Exception exp) {
      LOGGER.log(Level.WARNING, "{0}.paint Error:\n {1}",
          new Object[]{this.getClass().getSimpleName(), exp.getMessage()});
    } finally {
      graphics.setTransform(curTransForm);
    }
  }


  /**
   * Get Legend Icon.
   *
   * @return A buffered image representing this element if shown in a legend.
   */
  @Override
  public final BufferedImage getLegendIcon() {
    BufferedImage img = new BufferedImage(ICON_SIZE, ICON_SIZE,
        BufferedImage.TYPE_INT_ARGB);
    Graphics2D g = img.createGraphics();
    g.drawString("T", 0, ICON_SIZE);
    g.dispose();
    return img;
  }

}
