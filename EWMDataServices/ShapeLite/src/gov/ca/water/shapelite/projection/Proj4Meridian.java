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

import gov.ca.water.shapelite.Optional;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public enum Proj4Meridian {

  /**
   * Greenwich, England.
   */
  Greenwich,
  /**
   * Lisbon, Portugal.
   */
  Lisbon,
  /**
   * Paris, France.
   */
  Paris,
  /**
   * Bogota, Colombia.
   */
  Bogota,
  /**
   * Madrid, Spain.
   */
  Madrid,
  /**
   * Rome, Italy.
   */
  Rome,
  /**
   * Berne, Switzerland.
   */
  Bern,
  /**
   * Jakarta, Indonesia.
   */
  Jakarta,
  /**
   * Brasil.
   */
  Ferro,
  /**
   * Brussels, Belgiuum.
   */
  Brussels,
  /**
   * Stockholm, Sweden.
   */
  Stockholm,
  /**
   * Athens, Greece.
   */
  Athens,
  /**
   * Oslo, Norway.
   */
  Oslo;

  /**
   * Parse the name.
   *
   * @param name The String name.
   * @return The Proj4Meridian to parse.
   */
  public static Optional<Proj4Meridian> parse(String name) {
    for (Proj4Meridian meridian : Proj4Meridian.values()) {
      if (meridian.toString().toLowerCase().equals(name.toLowerCase())) {
        return Optional.of(meridian);
      }
    }
    return Optional.empty();
  }

}
