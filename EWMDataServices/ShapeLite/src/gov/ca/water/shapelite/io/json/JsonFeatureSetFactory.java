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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class JsonFeatureSetFactory {

  /**
   * The Gson object that controls parsing.
   */
  private Gson gson;

  /**
   * Creates a new instance of a JsonFeatureSetFactory.
   */
  public JsonFeatureSetFactory() {
    gson = new Gson();
  }

  /**
   * Gets the definition from the text.
   *
   * @param jsonText The JsonText to convert into an EsriLayerDef object.
   * @return The EsriLayerDef object parsed from the jsonText.
   */
  public final EsriLayerDef getDef(String jsonText) {
    return gson.fromJson(jsonText, EsriLayerDef.class);
  }

  /**
   * Gets the count from the jsonText.
   *
   * @param jsonText The Json string containing a count key and value.
   * @return The integer count from the jsonText representation.
   */
  public final int getFeatureCount(String jsonText) {
    EsriCount result = gson.fromJson(jsonText, EsriCount.class);
    return result.getCount();
  }

  /**
   * Creates a new JsonFeatureSet from json text.
   *
   * @param jsonText the JsonText containing the definition.
   * @return The JsonFeatureSet of JsonPoint objects.
   */
  public final JsonFeatureSet<JsonPoint> getPoints(String jsonText) {
    JsonFeatureSet<JsonPoint> result;
    Type token = new TypeToken<JsonFeatureSet<JsonPoint>>() {
    }.getType();
    result = gson.fromJson(jsonText, token);
    return result;
  }

  /**
   * Creates a new JsonLine featureset from the text.
   *
   * @param jsonText The JsonText defining the lines.
   * @return The JsonFeatureSet of lines.
   */
  public final JsonFeatureSet<JsonLine> getLines(String jsonText) {
    JsonFeatureSet<JsonLine> result;
    Type token = new TypeToken<JsonFeatureSet<JsonPoint>>() {
    }.getType();
    result = gson.fromJson(jsonText, token);
    return result;
  }

  /**
   * Creates a new JsonLine featureset from the text.
   *
   * @param jsonText The JsonText defining the lines.
   * @return The JsonFeatureSet of lines.
   */
  public final JsonFeatureSet<JsonPolygon> getPolygons(String jsonText) {
    JsonFeatureSet<JsonPolygon> result;
    Type token = new TypeToken<JsonFeatureSet<JsonPolygon>>() {
    }.getType();
    result = gson.fromJson(jsonText, token);
    return result;
  }

  /**
   * Creates a new JsonLine featureset from the text.
   *
   * @param jsonText The JsonText defining the lines.
   * @return The JsonFeatureSet of lines.
   */
  public final JsonFeatureSet<JsonMultiPoint> getMultiPoints(String jsonText) {
    JsonFeatureSet<JsonMultiPoint> result;
    Type token = new TypeToken<JsonFeatureSet<JsonPoint>>() {
    }.getType();
    result = gson.fromJson(jsonText, token);
    return result;
  }

  /**
   * Gets the Gson object that controls parsing.
   *
   * @return the gson object.
   */
  public final Gson getGson() {
    return gson;
  }

  /**
   * Sets the Gson object that controls parsing.
   *
   * @param gson the gson to set
   */
  public final void setGson(Gson gson) {
    this.gson = gson;
  }
}
