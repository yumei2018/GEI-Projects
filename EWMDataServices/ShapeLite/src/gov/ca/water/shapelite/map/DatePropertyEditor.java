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
package gov.ca.water.shapelite.map;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This is a property editor for dates.  This can be used by property grids.
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class DatePropertyEditor extends PropertyEditorSupport {

  /**
   * Translates a numeric date into a date text expression.
   * @return The string date text.
   */
  @Override
  public String getAsText() {
    Date d = (Date) getValue();
    if (d == null) {
        return "No Date Set";
    }
    return new SimpleDateFormat("MM/dd/yy HH:mm:ss").format(d);
  }

  /**
   * Potentially converts a string into a numeric date, presuming that the date is 
   * in the MM/dd/yy HH:mm:ss format.
   * @param s The string to parse.
   * @throws IllegalArgumentException If the date could not be parsed.
   */
  @Override
  public void setAsText(String s) throws IllegalArgumentException {
    try {
        setValue (new SimpleDateFormat("MM/dd/yy HH:mm:ss").parse(s));
    } catch (ParseException pe) {
        IllegalArgumentException iae = new IllegalArgumentException ("Could not parse date");
        throw iae;
    }
  }
  
}
