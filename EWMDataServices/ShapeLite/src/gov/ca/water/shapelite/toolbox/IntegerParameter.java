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

import gov.ca.water.shapelite.Optional;
import java.text.DecimalFormat;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class IntegerParameter extends Parameter {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  private Integer min;
  private Integer max;
  private DecimalFormat decimalFormat;

  //</editor-fold>
  /**
   * Creates a new instance of the IntegerParameter class.
   */
  public IntegerParameter() {
    decimalFormat = new DecimalFormat("#");
  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Tests whether the parameter text can be parsed as an integer and if it can,
   * if the value is greater than or equal to the minimum and less than or equal
   * to the max.
   *
   * @return
   */
  @Override
  public ParameterStatus getStatus() {
    ParameterStatus result = ParameterStatus.Good;
    this.setValidationMessage(getDescription());
    if (this.getParameterText() == null || this.getParameterText().isEmpty()) {
      if (this.isRequired()) {
        result = ParameterStatus.Error;
        this.setValidationMessage(getParameterName() + " is null.");
      }
    } else {
      try {
        Integer value = Integer.parseInt(this.getParameterText());
        if (min != null) {
          if (value < min) {
            result = ParameterStatus.Warning;
            this.setValidationMessage(getParameterName()
                    + " is below the expected min of "
                    + decimalFormat.format(min) + ".");
          }
        }
        if (max != null) {
          if (value > max) {
            result = ParameterStatus.Warning;
            this.setValidationMessage(getParameterName()
                    + " is above the expected max of "
                    + decimalFormat.format(max) + ".");
          }
        }
      } catch (NumberFormatException ex) {
        result = ParameterStatus.Error;
        this.setValidationMessage(getParameterName()
                + " cannot be parsed as a double.");
      }
    }
    return result;
  }

  /**
   * Gets the integer value, or null if the status is invalid.
   *
   * @return The optional Integer value.  This will either have a value
   * or be empty, but never be null.
   */
  public final Optional<Integer> getValue() {
    if (this.getParameterText() == null) {
      return Optional.empty();
    }
    if (!this.getStatus().equals(ParameterStatus.Error)) {
      try{
        int result = Integer.parseInt(this.getParameterText());
        return Optional.of(result);
      }catch(Exception ex){
        // parse error. return empty.
      }
    }
    return Optional.empty();
  }

  //</editor-fold>
  /**
   * Creates a new IntegerParameterPanel that is appropriate for the integer
   * case.
   *
   * @return The ParameterPanel representing this integer parameter.
   */
  @Override
  public final ParameterPanel createPanel() {
    return new IntegerParameterPanel(this);
  }

  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * @return the min
   */
  public Integer getMin() {
    return min;
  }

  /**
   * @param min the min to set
   */
  public void setMin(Integer min) {
    this.min = min;
  }

  /**
   * @return the max
   */
  public Integer getMax() {
    return max;
  }

  /**
   * @param max the max to set
   */
  public void setMax(Integer max) {
    this.max = max;
  }

  /**
   * @return the decimalFormat
   */
  public DecimalFormat getDecimalFormat() {
    return decimalFormat;
  }

  /**
   * @param decimalFormat the decimalFormat to set
   */
  public void setDecimalFormat(DecimalFormat decimalFormat) {
    this.decimalFormat = decimalFormat;
  }

    //</editor-fold>
}
