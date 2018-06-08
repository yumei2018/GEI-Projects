/*
 * The MIT License
 *
 * Copyright 2015 Harold A. Dunsford Jr. Ph.D..
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
package gov.ca.water.shapelite.dialog;

import java.awt.Point;
import javax.swing.JPanel;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public interface DialogHandler {

  /**
   * An interface that allows a framework specific dialog to be shown with the
   * specified panel.
   *
   * @param panel The panel to be shown.
   * @param title The string title to be shown.
   * @param buttons The buttons to appear on the dialog.
   * @param notifier The validation handler that updates the OK enabled status.
   * @return DialogResult.
   */
  DialogResult showDialog(JPanel panel, String title, DialogButtonType buttons,
      ValidationNotifier notifier);

  /**
   * An interface that allows a framework specific dialog to be shown with the
   * specified panel.
   *
   * @param panel The panel to be shown.
   * @param title The string title to be shown.
   * @param buttons The buttons to appear on the dialog.
   * @param notifier The validation handler that updates the OK enabled status.
   * @param location The top left location of the dialog.
   * @return DialogResult.
   */
  DialogResult showDialog(JPanel panel, String title, DialogButtonType buttons,
      ValidationNotifier notifier, Point location);


}
