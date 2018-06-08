/*
 * The MIT License
 *
 * Copyright 2015 hdunsford.
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
package gov.ca.water.shapelite.legend;

import java.awt.Rectangle;
import java.io.Serializable;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class LegendItem implements Cloneable, Serializable {

  /**
   * A LegendState that describes focus, selection, and expansion.
   */
  private LegendState state;
  /**
   * A user object that is the actual item being referenced by this item.
   */
  private Object userObject;
  /**
   * A rectangle describing the bounds of the entire legend item.
   */
  private Rectangle bounds;
  /**
   * A rectangle that describes the bounds of the check box.
   */
  private Rectangle checkBox;
  /**
   * A rectangle that describes the bounds of the expand box.
   */
  private Rectangle expandBox;
  /**
   * A rectangle that describes the bounds of the text area.
   */
  private Rectangle textBox;

  /**
   * Copies the current item to a new legend item.  The user object will be
   * a shallow copy, but all other items will be deep copied.
   * @return A duplicate legend item.
   */
  public final LegendItem copy() {
    LegendItem copy = new LegendItem();
    copy.copyProperties(this);
    return copy;
  }

  /**
   * Copies the properties of the other legend item to this item.
   *
   * @param other
   */
  public final void copyProperties(LegendItem other) {
    state = other.getState().copy();
    userObject = other.getUserObject();
    bounds = other.getBounds();
    checkBox = other.getCheckBox();
    expandBox = other.getExpandBox();
    textBox = other.getTextBox();

  }

  /**
   * Gets the LegendState that describes focus, selection, and expansion.
   * @return the state
   */
  public final LegendState getState() {
    return state;
  }

  /**
   * Sets the LegendState that describes focus, selection, and expansion.
   * @param state the state to set
   */
  public final void setState(LegendState state) {
    this.state = state;
  }

  /**
   * Gets the user object that is the actual item being referenced by this item.
   * @return the userObject
   */
  public final Object getUserObject() {
    return userObject;
  }

  /**
   * Sets the user object that is the actual item being referenced by this item.
   * @param userObject the userObject to set
   */
  public final void setUserObject(Object userObject) {
    this.userObject = userObject;
  }

  /**
   * Gets the rectangle describing the bounds of the entire legend item.
   * @return the bounds
   */
  public final Rectangle getBounds() {
    return bounds;
  }

  /**
   * Sets the rectangle describing the bounds of the entire legend item.
   * @param bounds the bounds to set
   */
  public final void setBounds(Rectangle bounds) {
    this.bounds = bounds;
  }

  /**
   * Gets the rectangle that describes the bounds of the check box.
   * @return the checkBox
   */
  public final Rectangle getCheckBox() {
    return checkBox;
  }

  /**
   * Sets the rectangle that describes bounds of the check box.
   * @param checkBox the checkBox to set
   */
  public final void setCheckBox(Rectangle checkBox) {
    this.checkBox = checkBox;
  }

  /**
   * Gets the rectangle that describes the bounds of the expand box.
   * @return the expandBox
   */
  public final Rectangle getExpandBox() {
    return expandBox;
  }

  /**
   * Sets the rectangle that describes the bounds of the expand box.
   * @param expandBox the expandBox to set
   */
  public final void setExpandBox(Rectangle expandBox) {
    this.expandBox = expandBox;
  }

  /**
   * Gets the rectangle that describes the bounds of the text area.
   * @return the textBox
   */
  public final Rectangle getTextBox() {
    return textBox;
  }

  /**
   * Sets the rectangle that describes the bounds of the text area.
   * @param textBox the textBox to set
   */
  public final void setTextBox(Rectangle textBox) {
    this.textBox = textBox;
  }

}
