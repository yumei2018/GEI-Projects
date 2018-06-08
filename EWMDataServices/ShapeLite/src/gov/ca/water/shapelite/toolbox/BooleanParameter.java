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
package gov.ca.water.shapelite.toolbox;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class BooleanParameter extends Parameter {

  /**
   * The boolean value for the parameter.
   */
  private boolean selected;

  /**
   * Creates a new default instance of a boolean parameter.
   */
  public BooleanParameter() {

  }

  /**
   * Creates a BooleanParameterPanel for the tool forms.
   *
   * @return THe ParameterPanel.
   */
  @Override
  public final ParameterPanel createPanel() {
    return new BooleanParameterPanel(this);
  }

  /**
   * Gets the Parameter status is always Good for boolean.
   *
   * @return ParameterStatus.Good.
   */
  @Override
  public final ParameterStatus getStatus() {
    return ParameterStatus.Good; // boolean parameters are always ok.
  }

  /**
   * Gets the boolean value for the parameter.
   *
   * @return the selected
   */
  public final boolean isSelected() {
    return selected;
  }

  /**
   * Sets the boolean value for the parameter.
   *
   * @param selected the selected to set
   */
  public final void setSelected(boolean selected) {
    this.selected = selected;
  }

}
