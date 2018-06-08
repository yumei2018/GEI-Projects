/*
 * The MIT License
 *
 * Copyright 2014 hdunsford.
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
package gov.ca.water.shapelite.toolbox;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class StatusIcon {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  private static Icon good;
  private static Icon warning;
  private static Icon error;

  //</editor-fold>
  /**
   * Creates a new instance of the StatusIcon class.
   */
  public StatusIcon() {

  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  public static Icon getGood() {
    if (good == null) {
      try {
        good = new ImageIcon(ImageIO.read(StatusIcon.class.getResourceAsStream("resources/StatusGood.png")));
      } catch (IOException ex) {
        Logger.getLogger(StatusIcon.class.getName()).log(
                Level.SEVERE, ex.getMessage(), ex);
      }
    }
    return good;
  }

  public static Icon getWarning() {
    if (warning == null) {
      try {
        warning = new ImageIcon(ImageIO.read(StatusIcon.class.getResourceAsStream("resources/StatusWarning.png")));
      } catch (IOException ex) {
        Logger.getLogger(StatusIcon.class.getName()).log(
                Level.SEVERE, ex.getMessage(), ex);
      }
    }
    return warning;
  }

  public static Icon getError() {
    if (error == null) {
      try {
        error = new ImageIcon(ImageIO.read(StatusIcon.class.getResourceAsStream("resources/StatusError.png")));
      } catch (IOException ex) {
        Logger.getLogger(StatusIcon.class.getName()).log(
                Level.SEVERE, ex.getMessage(), ex);
      }
    }
    return error;
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  //</editor-fold>
}
