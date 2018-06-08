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

import java.io.Serializable;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class LegendState implements Serializable {

  /**
   * Boolean true if this legend item is expanded.
   */
  private boolean expanded;

  /**
   * Boolean true if this item has no children.
   */
  private boolean leaf;

  /**
   * Boolean true if this item should be shown as selected.
   */
  private boolean selected;

  /**
   * Boolean true if this item should be drawn with focus.
   */
  private boolean hasFocus;

  /**
   * Creates a new instance of the LegendState.
   */
  public LegendState() {
    expanded = true;
    leaf = true;
    selected = false;
    hasFocus = false;
  }

  /**
   * Gets a deep copy of this object.
   *
   * @return A LegendState duplicate of this item.
   */
  public final LegendState copy() {
    LegendState copy = new LegendState();
    copy.copyProperties(this);
    return copy;
  }

  /**
   * Copies the properties of the other object to this object.
   *
   * @param other The other LegendState to copy properties from.
   */
  public final void copyProperties(LegendState other) {
    expanded = other.isExpanded();
    leaf = other.isLeaf();
    selected = other.isSelected();
    hasFocus = other.hasFocus();
  }

  /**
   * Gets a boolean that is true if this legend item is expanded.
   *
   * @return the expanded
   */
  public final boolean isExpanded() {
    return expanded;
  }

  /**
   * Sets a boolean that is true if this legend item is expanded.
   *
   * @param expanded the expanded to set
   */
  public final void setExpanded(boolean expanded) {
    this.expanded = expanded;
  }

  /**
   * Gets a boolean that is true if this item has no children.
   *
   * @return the leaf
   */
  public final boolean isLeaf() {
    return leaf;
  }

  /**
   * Sets a boolean that is true if this item has no children.
   *
   * @param leaf the leaf to set
   */
  public final void setLeaf(boolean leaf) {
    this.leaf = leaf;
  }

  /**
   * Gets a boolean that is true if this item should be shown as selected.
   *
   * @return the selected
   */
  public final boolean isSelected() {
    return selected;
  }

  /**
   * Sets a boolean that is true if this item should be shown as selected.
   *
   * @param selected the selected to set
   */
  public final void setSelected(boolean selected) {
    this.selected = selected;
  }

  /**
   * Gets a boolean that is true if this item should be drawn with focus.
   *
   * @return the hasFocus
   */
  public final boolean hasFocus() {
    return hasFocus;
  }

  /**
   * Sets a boolean that is true if this item should be drawn with focus.
   *
   * @param hasFocus the hasFocus to set
   */
  public final void setHasFocus(boolean hasFocus) {
    this.hasFocus = hasFocus;
  }
}
