/*
 * The MIT License
 *
 * Copyright 2016 Harold A. Dunsford Jr. Ph.D..
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
package gov.ca.water.shapelite.io.json;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class EsriFieldType {

  /**
   * A small integer.
   */
  public static final String SMALL_INTEGER = "esriFieldTypeSmallInteger";

  /**
   * A large Integer.
   */
  public static final String INTEGER = "esriFieldTypeInteger";

  /**
   * Single precision floating points.
   */
  public static final String SINGLE = "esriFieldTypeSingle";

  /**
   * Double precision floating points.
   */
  public static final String DOUBLE = "esriFieldTypeDouble";

  /**
   * A String type.
   */
  public static final String STRING = "esriFieldTypeString";

  /**
   * Date types.
   */
  public static final String DATE = "esriFieldTypeDate";

  /**
   * Essentially a long integer.
   */
  public static final String OBJECT_ID = "esriFieldTypeOID";

  /**
   * An entire geometry compressed into a data field. This will likely not be
   * preserved correctly in a shapefile.
   */
  public static final String GEOMETRY = "esriFieldTypeGeometry";

  /**
   * A binary large object. This type is not supported for shapefiles.
   */
  public static final String BLOB = "esriFieldTypeBlob";

  /**
   * A raster data object. This type is not supported for shapefiles.
   */
  public static final String RASTER = "esriFieldTypeRaster";

  /**
   * A globally unique identifier.
   */
  public static final String GUID = "esriFieldTypeGUID";

  /**
   * Another form of unique id.
   */
  public static final String GLOBAL_ID = "esriFieldTypeGlobalID";

  /**
   * private constructor.
   */
  private EsriFieldType() {

  }

  /**
   * Enumerates through all of the field types.
   *
   * @return The List of String field types.
   */
  public static List<String> getAll() {
    List<String> result = new ArrayList<>();
    result.add(SMALL_INTEGER);
    result.add(INTEGER);
    result.add(SINGLE);
    result.add(DOUBLE);
    result.add(STRING);
    result.add(DATE);
    result.add(OBJECT_ID);
    result.add(GEOMETRY);
    result.add(BLOB);
    result.add(RASTER);
    result.add(GUID);
    result.add(GLOBAL_ID);
    return result;
  }
}
