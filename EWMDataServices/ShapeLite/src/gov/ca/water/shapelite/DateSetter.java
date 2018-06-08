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
package gov.ca.water.shapelite;

import java.util.Calendar;
import java.util.Date;

/**
 * Creates a new instance of the DateSetter class.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class DateSetter {

  /**
   * Private constructor to hide default constructor.
   */
  private DateSetter() {

  }

//<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * A calendar for date calculations.
   */
  private static Calendar myCalendar;

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Assigns a value of 0 for every unit of time from year to millisecond.
   */
  private static void clear() {
    Calendar c = getCalendar();
    c.set(Calendar.YEAR, 0);
    c.set(Calendar.MONTH, 0);
    c.set(Calendar.DAY_OF_MONTH, 0);
    c.set(Calendar.HOUR_OF_DAY, 0);
    c.set(Calendar.MINUTE, 0);
    c.set(Calendar.SECOND, 0);
    c.set(Calendar.MILLISECOND, 0);
  }

  /**
   * This gets the date for the current time zone where all time units other
   * than the specified units are set to zero.
   *
   * @param year The integer year
   * @param month The integer month from 0 to 11.
   * @param day The integer day starting at 1.
   * @return The Date for the specified year, month and day with no time values.
   */
  public static Date getDate(final int year, final int month, final int day) {
    clear();
    Calendar c = getCalendar();
    c.set(Calendar.DAY_OF_MONTH, day);
    c.set(Calendar.MONTH, month - 1);
    c.set(Calendar.YEAR, year);
    return myCalendar.getTime();
  }

  /**
   * Gets the current calendar.
   *
   * @return a Calendar.
   */
  public static Calendar getCalendar() {
    if (myCalendar == null) {
      myCalendar = (Calendar) Calendar.getInstance().clone();
    }
    return myCalendar;
  }

  //</editor-fold>
}
