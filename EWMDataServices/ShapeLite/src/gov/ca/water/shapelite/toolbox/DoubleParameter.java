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

import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class DoubleParameter extends Parameter{

    //<editor-fold defaultstate="collapsed" desc="Fields">

    private Double min;
    private Double max;
    private DecimalFormat decimalFormat;
    private static final Logger LOGGER = Logger.getLogger(DoubleParameter.class.getName());

    //</editor-fold>

    /**
     * Creates a new instance of the DoubleParameter class.
     */
    public DoubleParameter(){
        decimalFormat = new DecimalFormat("#.000");
    }

  
     //<editor-fold defaultstate="collapsed" desc="Methods">
    
    /**
     * Tests double validity
     * @return 
     */
    @Override
    public ParameterStatus getStatus() {
        ParameterStatus result = ParameterStatus.Good;
        this.setValidationMessage(getDescription());
        if(this.getParameterText() == null || this.getParameterText().isEmpty()){
            if(this.isRequired()){
                result = ParameterStatus.Error;
                this.setValidationMessage(getParameterName() + " is null.");
            }
            return result;
        }
        try{
            Double value = Double.parseDouble(this.getParameterText());
            if(min != null){
                if(value < min){
                    result = ParameterStatus.Warning;
                    this.setValidationMessage(getParameterName() + " is below the expected min of " + 
                            decimalFormat.format(min) + ".");
                }
            }
            if(max != null){
                if(value > max){
                    result = ParameterStatus.Warning;
                    this.setValidationMessage(getParameterName() + " is above the expected max of " + 
                            decimalFormat.format(max) + ".");
                }
            }
        }catch(NumberFormatException ex){
            result = ParameterStatus.Error;
            this.setValidationMessage(getParameterName() + " cannot be parsed as a double.");
        }
        return result; 
    }
    
    /**
     * Gets the value as a Double.  This may be null if the parsing fails.
     * @return 
     */
    public Double getValue(){
        try{
            return Double.parseDouble(this.getParameterText());
        } catch(Exception ex){
            LOGGER.log(Level.SEVERE, this.getParameterName() + " could not be parsed as a double.", ex);
        }
        return null;
       
    }


    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">

    @Override
    public DoubleParameterPanel createPanel() {
        return new DoubleParameterPanel(this);
    }
  
    


    //</editor-fold>

    /**
     * @return the min
     */
    public Double getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(Double min) {
        this.min = min;
    }

    /**
     * @return the max
     */
    public Double getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(Double max) {
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

   

}
