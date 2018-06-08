package gov.ca.water.shapelite.labels.grahics;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class provides access to a single shared instance of FontManager.
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class FontManager {

  //<editor-fold defaultstate="collapsed" desc="Private Fields">
  private String helvetica = null;
  private String helveticaBold = null;
  private static final Logger LOGGER = Logger.getLogger(FontManager.class.getName());
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Singleton">
  /**
   * Creates a new instance of the FontManager class. This is private as part of the
   * singleton pattern.
   */
  private FontManager() {
  }

  /**
   * Gets the single shared static instance of the FontManager class.
   */
  public static FontManager getInstance() {
    return FontManagerHolder.INSTANCE;
  }

  /**
   * Gets the static internal class that is part of the Singleton Pattern in Java.
   */
  private static class FontManagerHolder {

    private static final FontManager INSTANCE = new FontManager();
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Public Methods">
  /**
   * Get the lazy-initiated Helvetica font
   * @return the Helvetica-Narrow font name or "Arial" is the custom font cannot be loaded
   */
  public String helveticaNarrow() {
    if (this.helvetica == null) {
      this.helvetica = "Arial";
      try (InputStream stream = this.getClass().getResourceAsStream(
                                        "fonts/HelveticaNarrow/Helvetica-Narrow.otf")) {
        if (stream != null) {
          Font f = Font.createFont(Font.TRUETYPE_FONT, stream);
          GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
          ge.registerFont(f);
          this.helvetica = "Helvetica-Narrow";
        }
      } catch (FontFormatException | IOException ex) {
        LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      }
    }
    return this.helvetica;
  }
  
  /**
   * Get the lazy-initiated Helvetica-Bold font
   * @return the Helvetica-Bold font name or "Arial" is the custom font cannot be loaded.
   */
  public String helveticaBold() {
    if (this.helveticaBold == null) {
      this.helveticaBold = "Arial";
      try (InputStream stream =
              this.getClass().getResourceAsStream("fonts/Helvetica-Bold.otf")) {
        if (stream != null) {
          Font f = Font.createFont(Font.TRUETYPE_FONT, stream);
          GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
          ge.registerFont(f);
          this.helveticaBold = "Helvetica-Bold";
        }
      } catch (FontFormatException | IOException ex) {
        LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      }
    }
    return this.helveticaBold;
  }
  
  /**
   * Called by {@linkplain #getFont(java.awt.Graphics2D, java.lang.String, int, int) 
   * this.getFont()} to resolve the fontName. If fontName is "Helvetica-Bold" or
   * "Helvetica-Narrow", call {@linkplain #helveticaBold() this.helveticaBold()} or
   * {@linkplain #helveticaNarrow() this.helveticaNarrow()}, respectively, to resolve the
   * fontName. Else, return <tt>fontName</tt>
   * @param fontName the font name to resolve
   * @return the resolved font name
   */
  private String getFontName(String fontName) {
    String result = (fontName == null)? null: fontName.trim();
    if ("Helvetica-Bold".equalsIgnoreCase(result)) {
      result = this.helveticaBold();
    } else if ("Helvetica-Narrow".equalsIgnoreCase(result)) {
      result = this.helveticaNarrow();
    }
    return result;
  }
  
  /**
   * Gets the smallest available font that is larger than the specified height.
   * @param graphics the current graphics
   * @param fontName the font name
   * @param style the required font style
   * @param height the required font height
   * @return the font or null if not found
   */
  public Font getFont(Graphics2D graphics, String fontName, int style, int height) {
    Font result = null;
    int size = height;
    boolean found = false;
    fontName = this.getFontName(fontName);
    if (fontName != null) {
      while (!found) {
        Font font = new Font(fontName, style, size);
        if (font == null) {
          throw new IllegalArgumentException("Unable to locate Font[" + fontName + "].");
        }
        
        int testHeight = graphics.getFontMetrics(font).getHeight();
        if (testHeight < height) {
          size++;
        } else if (testHeight >= height) {
          result = new Font(fontName, style, size);
          break;
        }
      }
    }
    return result;
  }
  //</editor-fold>
}
