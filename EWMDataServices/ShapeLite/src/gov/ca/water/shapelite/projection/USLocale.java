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

package gov.ca.water.shapelite.projection;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class USLocale {

    //<editor-fold defaultstate="collapsed" desc="Fields">


    private static final DecimalFormat US_Double = new DecimalFormat("#0.################",new DecimalFormatSymbols(Locale.US));
    private static final DecimalFormat US_Integer = new DecimalFormat("#", new DecimalFormatSymbols(Locale.US));
   
    //</editor-fold>

    /**
     * Creates a new instance of the USLocale class.
     */
    public USLocale(){
  
    }

    //<editor-fold defaultstate="collapsed" desc="Methods">
  
    public static String format(Double value){
        return US_Double.format(value);
    }
    /**
     * The double value will be written, where the decimal indicates the number of
     * zeros to keep after the decimal.
     * @param value
     * @param decimal
     * @return 
     */
    public static String format(Double value, int decimal){
        DecimalFormat df = new DecimalFormat(getFormatString(decimal), new DecimalFormatSymbols(Locale.US));
        return df.format(value);
        
    }
    private static String getFormatString(int decimal){
        String result = "#0.";
        for(int i = 0; i < decimal; i++){
            result += "0";
        }
        return result;
    }
    
    public static String format(Integer value){
        return US_Integer.format(value);
    }
    
    public static double parseDoubleVal(String number) throws ParseException{
        double result = 0;
        if(number != null && !number.isEmpty()){
             result = US_Double.parse(number).doubleValue();
        }
        return result;
    }
    
    public static Double parseDouble(String number) throws ParseException{
      try {
        Number val = Double.parseDouble(number);//US_Double.parse(number.trim());
        if(val != null){
           return val.doubleValue(); 
        }
        return 0.0;
      }
      catch(Exception ex) {
        System.err.println("USLocale.parseDouble(String) => " + number);
        throw (ParseException)ex;
      }
    }
    
    public static int parseIntegerVal(String number) throws ParseException{
        int result = 0;
        if(number != null && !number.isEmpty()){
             Number val = US_Integer.parse(number);
             if(val != null){
                 return val.intValue();
             }
        }
        return result;
    }
    
    public static Integer parseInteger(String number) throws ParseException{
        Number val = US_Integer.parse(number);
        if(val != null){
            return val.intValue();
        }
        return null;
    }

    public static Long parseLong(String number) throws ParseException{
        Number val = US_Integer.parse(number);
        if(val != null){
            return val.longValue();
        }
        return null;
    }


    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
  


    //</editor-fold>

}
