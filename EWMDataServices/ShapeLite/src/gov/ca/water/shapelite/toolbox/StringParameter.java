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
public class StringParameter extends Parameter {

  /**
   * Creates the swing panel with the string parameter inside.
   *
   * @return The ParameterPanel.
   */
  @Override
  public ParameterPanel createPanel() {
    return new StringParameterPanel(this);
  }

  /**
   * Gets the String parameterText as a value.
   *
   * @return The String value.
   */
  public final String getValue() {
    return getParameterText();
  }

  /**
   * Sets the string value for this parameter.
   * @param value The String value to assign to the parameter.
   */
  public final void setValue(String value) {
    super.setParameterText(value);
  }
}
