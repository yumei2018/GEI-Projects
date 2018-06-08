/*
 * The MIT License
 *
 * Copyright 2012 hdunsford.
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
package gov.ca.water.shapelite.symbology;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


/**
 * This class is specific for Images, but currently there are no symbolic
 * settings for images.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ImageSymbolizer implements Symbolizer {

  private float opacity;

  private LineSymbolizer border;

  private static BufferedImage imageIcon;

  static {
    try {
      imageIcon = ImageIO.read(ImageSymbolizer.class.getResourceAsStream("ImageIcon.png"));
    } catch (IOException ex) {
      Logger.getLogger(ImageSymbolizer.class.getName()).log(
              Level.SEVERE, ex.getMessage(), ex);
    }
  }

  public ImageSymbolizer() {
    opacity = 1f;
  }

  /**
   * @return the opacity
   */
  public float getOpacity() {
    return opacity;
  }

  /**
   * The opacity should be a value between 0 and 1.
   * @param opacity the opacity to set
   */
  public void setOpacity(float opacity) {
    this.opacity = opacity;
  }


  @Override
  public BufferedImage getLegendIcon() {
    return imageIcon;
  }

  /**
   * @return the border
   */
  public LineSymbolizer getBorder() {
    return border;
  }

  /**
   * @param border the border to set
   */
  public void setBorder(LineSymbolizer border) {
    this.border = border;
  }
  
  public static ImageSymbolizer Default() {
    return new ImageSymbolizer(); 
  }

}
