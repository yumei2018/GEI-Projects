package gov.ca.water.shapelite.labels.grahics;

import gov.ca.water.shapelite.NonNull;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.io.Serializable;

/**
 * A Wrapper for {@linkplain Font} specifying the font name, size (in points)
 * and the Font Style. The Font is scaled to fit the {@linkplain ChartCanvas}
 * settings
 *
 * @author J.G. "Koos" Prins, D.Eng. PE.
 */
public class CanvasFont implements Serializable {

  // <editor-fold defaultstate="collapsed" desc="Private Fields">
  /**
   * The Font's name (e.g., "Helvetica") - default = "Helvetica"
   */
  private String fontName;
  /**
   * The font size (in points - default 10pt)
   */
  private Float fontSize;
  /**
   * True to print Bold text
   */
  private Boolean bold;
  /**
   * True to print Italic Text
   */
  private Boolean italic;
  /**
   * The the Font Color (default = Black)
   */
  private Color color;
  /**
   * The selected font Style (default = PLAIN).
   */
  private FontShadowStyle shadowStyle;
  /**
   * The shadow offset use with non-PLAIN FontStyles (default = 1.0 point).
   */
  private Float shadowOffset;
  /**
   * The color for the shadow for non-PLAIN FontStyles (default = black if
   * this.color is light or white is this color is dark).
   */
  private Color shadowColor;

  /**
   * The FontMetrics of the currently active Canvas
   */
  private FontMetrics fontMetrics = null;
  /**
   * The ShadowOffet in pixels after scaling to canvas
   */
  private Float shadowPixelOffset;
  /**
   * The Font use to draw the text
   */
  private Font textFont;
  /**
   * The Font use to draw the shadow text
   */
  private Font shadowFont;
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Public Parameterless Constructor
   */
  public CanvasFont() {
    this(null, -0.1f, null, false, false);
  }

  /**
   * Public Constructor with a fontName and size.
   *
   * @param fontName the font name
   * @param size fond size (ignored is &le; 0)
   */
  public CanvasFont(String fontName, float size, Color color) {
    this(fontName, size, null, false, false);
  }

  /**
   * Public Constructor with a fontName and size and flag to print text bold
   * and/or italic or plain.
   *
   * @param fontName the font name
   * @param size fond size (ignored if &le; 0.0 | NaN)
   * @param bold true to print text bold
   * @param italic true to print text italic
   */
  public CanvasFont(String fontName, float size, Color color,
      boolean bold, boolean italic) {
    super();
    this.fontName = (fontName == null) ? null : fontName.trim();
    this.fontSize = ((size == Float.NaN) || (size <= 0.0f)) ? null : size;
    this.bold = (!bold) ? null : bold;
    this.italic = (!italic) ? null : italic;
    this.shadowColor = null;
    this.shadowOffset = null;
    this.shadowStyle = null;
    this.resetCanvas();
  }
  // </editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
  /**
   * Get the font's Name
   *
   * @return thisfontName or FontManager.getInstance().helveticaNarrow() is
   * unassigned
   */
  public String getFontName() {
    return (this.fontName == null)
        ? FontManager.getInstance().helveticaNarrow() : this.fontName;
  }

  /**
   * Get the font's size (in points)
   *
   * @return this.fondtSize or 10 points is unassigned
   */
  public float getFontSize() {
    return (this.fontSize == null) ? 10.0f : this.fontSize;
  }

  /**
   * Set the Font Size
   *
   * @param fontSize the new font size (ignored is &le 0).
   */
  public void setFontSize(float fontSize) {
    this.fontSize = ((fontSize == Float.NaN) || (fontSize <= 0.0f)) ? null : fontSize;
  }

  /**
   * Get the assigned label color (default = Black)
   *
   * @return the assigned color
   */
  public Color getColor() {
    return (this.color == null) ? Color.BLACK : this.color;
  }

  /**
   * Set the label color
   *
   * @param font the new font (Assume Color.Black if null).
   */
  public void setColor(Color color) {
    this.color = color;
  }

  /**
   * Get whether text will be printed bold
   *
   * @return true for bold
   */
  public boolean isBold() {
    return ((this.bold != null) && (this.bold));
  }

  /**
   * Set whether text will be printed bold
   *
   * @param bold true to print bold
   */
  public void setBold(boolean bold) {
    this.bold = (!bold) ? null : bold;
  }

  /**
   * Get whether text will be printed italic
   *
   * @return true for bold
   */
  public Boolean isItalic() {
    return ((this.italic != null) && (this.italic));
  }

  /**
   * Set whether text will be printed italic
   *
   * @param bold true to print bold
   */
  public void setItalic(Boolean italic) {
    this.italic = (!italic) ? null : italic;
  }

  /**
   * Set the Font's Shadow Style and associated attributes.
   *
   * @param shadowStyle the Font Shadow Style (default = PLAIN)
   * @param shadowOffset the Shadow Offset (default = 1.0 point)
   * @param shadowColor the shadow Color (default = WHITE if this color is dark
   * or BLACK if this.color if light)
   */
  public void setFontShadows(FontShadowStyle shadowStyle, Float shadowOffset,
      Color shadowColor) {
    this.shadowStyle = shadowStyle;
    this.shadowOffset = ((shadowOffset == null) || (shadowOffset.isNaN())
        || (shadowOffset <= 0.0f)) ? null : shadowOffset;
    this.shadowColor = shadowColor;
  }

  /**
   * The FontShadowStyle - the default = PLAIN (no shadows)
   *
   * @return the assigned or default value
   */
  public FontShadowStyle getShadowStyle() {
    return (this.shadowStyle == null) ? FontShadowStyle.PLAIN : this.shadowStyle;
  }

  /**
   * The Shadow Offset (only used for non-PLAIN fontStyles) (default = 1.0
   * point)
   *
   * @return the assigned or default off
   */
  public float getShadowOffset() {
    return (this.shadowOffset == null) ? 1.0f : this.shadowOffset;
  }

  /**
   * Get the Shadow Color (only used for non-PLAIN fontStyles) (default = WHITE
   * if this color is dark or BLACK if this.color if light)
   *
   * @return the assigned or default color
   */
  public Color getShadowColor() {
    Color result = this.shadowColor;
    if (result == null) {
      if ((this.color == null) || (Color.BLACK.equals(this.color))) {
        result = Color.WHITE;
      } else if ((this.color.getRed() > 128) && (this.color.getBlue() > 128)
          && (this.color.getGreen() > 128)) {
        result = Color.WHITE;
      } else {
        result = Color.BLACK;
      }
    }
    return result;
  }

  /**
   * Called to set the Canvas' Font and Color before rendering a text element.
   * It initiate and assign the internal fontMetrics setting and if the
   * ShadowStyle != Plain, it initiates the scaled this.shadowPixelOffset (the
   * shadowOffset in pixels)
   *
   * @param canvas the Canvas to update
   * @return FontMetrics showing the font measurements for the canvas.
   */
  public final FontMetrics setCanvas(@NonNull MapCanvas canvas) {
    if (canvas == null) {
      throw new IllegalArgumentException("Parameter canvas cannot be null.");
    }
    if (canvas.graphics == null) {
      throw new IllegalArgumentException("Canvas parameter cannot have a null graphics.");
    }
    this.resetCanvas();
    Graphics2D graphics = canvas.graphics;
    FontManager fontMngr = FontManager.getInstance();
    int size = canvas.getCanvasSize().getFontSize(this.getFontSize());
    int style = Font.PLAIN;
    if (this.isBold()) {
      style |= Font.BOLD;
    }
    if (this.isItalic()) {
      style |= Font.ITALIC;
    }
    this.textFont = fontMngr.getFont(graphics, this.getFontName(), style, size);
    if (this.textFont != null) {
      graphics.setFont(this.textFont);
    }

    if (!this.isBold()) {
      style |= Font.BOLD;
    }
    this.shadowFont = fontMngr.getFont(graphics, this.getFontName(), style, size);

    this.fontMetrics = graphics.getFontMetrics(this.textFont);
    this.shadowPixelOffset = null;
    switch (this.getShadowStyle()) {
      case PLAIN:
        break;
      case SHADOW:
        float offset = this.getShadowOffset();
        this.shadowPixelOffset = canvas.getStrokeSize(offset);
        break;
      default:
        this.shadowPixelOffset = 1.0f;
        break;
    }
    return this.fontMetrics;
  }

  /**
   * Called on completion of a paint cycle to reset the CnavasFont's canvas
   * settings (i.e. its fontMetric and shadowPixelOffset).
   */
  public void resetCanvas() {
    this.textFont = null;
    this.fontMetrics = null;
    this.shadowFont = null;
    this.shadowPixelOffset = null;
  }

  /**
   * Get the width of the <tt>text</tt> for the specified fontMetric, including
   * the extra width of the text shadow (if applicable)
   *
   * @param canvas the Canvas to paint on
   * @param fontMetrics the current font's metrics
   * @param text the text to render
   * @return the text width or 0.0 if text=null|"" or fontMetrics=null
   */
  public float getTextWidth(String text) {
    float result = 0.0f;
    if ((this.fontMetrics != null) && (text != null) && (text.length() > 0)) {
      result = fontMetrics.stringWidth(text);

      float dW = (this.shadowPixelOffset == null) ? 0.0f : this.shadowPixelOffset;
      CanvasOffset[] offsets = null;
      if ((dW > 0.0f)
          && ((offsets = this.getShadowStyle().shiftArray) != null)
          && (offsets.length > 0)) {
        float left = 0.0f;
        float right = 0.0f;
        for (CanvasOffset offset : offsets) {
          left = Math.min(left, offset.getX());
          right = Math.max(left, offset.getX());
        }
        result += (right * dW) - (left * dW);
      }
    }
    return result;
  }

  /**
   * Get the width of the <tt>text</tt> for the specified fontMetric, including
   * the extra width of the text shadow (if applicable)
   *
   * @param canvas the Canvas to paint on
   * @param fontMetrics the current font's metrics
   * @param text the text to render
   * @return the text width or 0.0 if text=null|"" or fontMetrics=null
   */
  public float getTextHeight() {
    float result = 0.0f;
    if (this.fontMetrics != null) {
      result = fontMetrics.getHeight();

      float dH = (this.shadowPixelOffset == null) ? 0.0f : this.shadowPixelOffset;
      CanvasOffset[] offsets = null;
      if ((dH > 0.0f)
          && ((offsets = this.getShadowStyle().shiftArray) != null)
          && (offsets.length > 0)) {
        float top = 0.0f;
        float bottom = 0.0f;
        for (CanvasOffset offset : offsets) {
          top = Math.min(top, offset.getY());
          bottom = Math.max(top, offset.getY());
        }
        result += (bottom * dH) - (top * dH);
      }
    }
    return result;
  }

  /**
   * Called to paint the <tt>text</tt> to the 2D-Graphic surface, starting at
   * the top-left insert point and using the defined textStyle. If a Font Shadow
   * is set, it will draw the show before drawing the text.
   *
   * @param graphics the 2D-Graphic surface
   * @param text the text to draw
   * @param topLeft the top-left insert point
   * @param textStyle the defined textStyle
   */
  public void paint(Graphics2D graphics, String text, CanvasPoint topLeft,
      TextStyle textStyle) {
    if ((graphics == null) || (this.fontMetrics == null)
        || (text == null) || (text.length() == 0)
        || (topLeft == null) || (topLeft.isEmpty())) {
      return;
    }

    CanvasPoint p0 = topLeft.clone();
    float dH = (this.shadowPixelOffset == null) ? 0.0f : this.shadowPixelOffset;
    CanvasOffset[] offsets = null;
    if ((dH > 0.0f)
        && ((offsets = this.getShadowStyle().shiftArray) != null)
        && (offsets.length > 0)) {
      float top = 0.0f;
      float left = 0.0f;
      for (CanvasOffset offset : offsets) {
        top = Math.min(top, offset.getY());
        left = Math.min(top, offset.getX());
      }
      p0.moveXY((-1 * top * dH), (-1 * left * dH));
    } else {
      offsets = null;
    }

    float textWidth = this.fontMetrics.stringWidth(text);
    float x0 = p0.getX() + (textStyle.hAlign.dX * textWidth);
    float y0 = p0.getY() + (textStyle.vAlign.dY * this.fontMetrics.getAscent());

    Color curColor = graphics.getColor();
    try {
      if (offsets != null) {
        graphics.setColor(this.getShadowColor());
        for (CanvasOffset offset : offsets) {
          graphics.drawString(text, x0 + (offset.getX() * dH), y0 + (offset.getY() * dH));
        }
      }

      /**
       * Finally draw Text in
       */
      graphics.setColor(this.getColor());
      graphics.drawString(text, x0, y0);
    } finally {
      graphics.setColor(curColor);
    }

  }
  //</editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Object Overrides">
  /**
   * {@inheritDoc}
   * <p>
   * OVERRIDE: "CanvasFont[" + this.getFontName() + "; size=" +
   * this.getFontSize() + "; bold=" + this.isBold() + "; italic=" +
   * this.isItalic() +"]"</p>
   */
  @Override
  public String toString() {
    String result = "CanvasFont[" + this.getFontName() + "; size=" + this.getFontSize()
        + "; bold=" + this.isBold() + "; italic=" + this.isItalic()
        + "; color=" + this.getColor() + "; style=";//+ this.
    result += "]";
    return result;
  }
  // </editor-fold>
}
