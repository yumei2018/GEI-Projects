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
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.ShapefileException;
import gov.ca.water.shapelite.io.ShapefileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.zip.ZipOutputStream;
import org.json.JSONObject;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class EsriJsonUtils {

  /**
   * Private constructor for utilities class.
   */
  private EsriJsonUtils() {

  }

  /**
   * Given an input JSON string, this will attempt to parse the string and write
   * the output content to the given OutputStream.
   *
   * @param esriJsonString the JsonString for a FeatureCollection.
   * @param stream the stream to write the resulting zipped shapefile files to.
   * @param shapefileName the filename without extension for the shapefiles.
   * @throws IOException if there was an error writing to the stream.
   * @throws ShapefileException if there was an error building the shapefile.
   */
  public static void esriFeatureCollectionToShapefile(String esriJsonString,
      OutputStream stream, String shapefileName) throws ShapefileException, IOException {

    Gson gson = new Gson();
    JsonFeatureSet<?> jfs = null;

    if (esriJsonString.contains("esriGeometryPoint")) {
      Type pointFeatureType = new TypeToken<JsonFeatureSet<JsonPoint>>() {
      }.getType();
      jfs = gson.fromJson(esriJsonString, pointFeatureType);
    } else if (esriJsonString.contains("esriGeometryPolyline")) {
      Type lineType = new TypeToken<JsonFeatureSet<JsonLine>>() {
      }.getType();
      jfs = gson.fromJson(esriJsonString, lineType);
    } else if (esriJsonString.contains("esriGeometryPolygon")) {
      Type polygonType = new TypeToken<JsonFeatureSet<JsonPolygon>>() {
      }.getType();
      jfs = gson.fromJson(esriJsonString, polygonType);
    }
    if (jfs != null) {
      FeatureSet fs = jfs.toFeatureSet(false, false);
      fs.setName(shapefileName);
      try (ZipOutputStream zip = new ZipOutputStream(stream)) {
        ShapefileWriter out = new ShapefileWriter();
        out.write(fs, zip);
      } catch (IOException ex) {
        throw ex;
      }
    }

  }
  
  public static FeatureSet esriFeatureCollectionToFeatureSet(String esriJsonString) throws ShapefileException, IOException {

    Gson gson = new Gson();
    JsonFeatureSet<?> jfs = null;
    FeatureSet fs = null;
    
    if (esriJsonString.contains("esriGeometryPoint") || esriJsonString.contains("\"x\":")) {
      Type pointFeatureType = new TypeToken<JsonFeatureSet<JsonPoint>>() {
      }.getType();
      jfs = gson.fromJson(esriJsonString, pointFeatureType);
    } else if (esriJsonString.contains("esriGeometryPolyline") || esriJsonString.contains("\"path\":")) {
      Type lineType = new TypeToken<JsonFeatureSet<JsonLine>>() {
      }.getType();
      jfs = gson.fromJson(esriJsonString, lineType);
    } else if (esriJsonString.contains("esriGeometryPolygon") || esriJsonString.contains("\"rings\":")) {
      Type polygonType = new TypeToken<JsonFeatureSet<JsonPolygon>>() {
      }.getType();
      jfs = gson.fromJson(esriJsonString, polygonType);
    }
    if (jfs != null) {
      fs = jfs.toFeatureSet(false, false);
    }
    
    return fs;
  }
  
  public static Feature esriFeatureToFeature(String esriGeometryJson) 
      throws ShapefileException, IOException {

    Gson gson = new Gson();
    JsonFeature<?> jFeature = null;
    ShapeType shapeType = null;
    if (esriGeometryJson.contains("point") || esriGeometryJson.contains("esri.geometry.Point")) {
      Type pointFeatureType = new TypeToken<JsonFeature<JsonPoint>>() {
      }.getType();
      jFeature = gson.fromJson(esriGeometryJson, pointFeatureType);
      shapeType = ShapeType.Point;
    } else if (esriGeometryJson.contains("polyline") || esriGeometryJson.contains("esri.geometry.Polyline")) {
      Type lineType = new TypeToken<JsonFeature<JsonLine>>() {
      }.getType();
      jFeature = gson.fromJson(esriGeometryJson, lineType);
      shapeType = ShapeType.PolyLine;
    } else if (esriGeometryJson.contains("polygon") || esriGeometryJson.contains("esri.geometry.Polygon")) {
      Type polygonType = new TypeToken<JsonFeature<JsonPolygon>>() {
      }.getType();
      jFeature = gson.fromJson(esriGeometryJson, polygonType);
      shapeType = ShapeType.Polygon;
    }
    return jFeature.toFeature(shapeType, false, false);
  }
  
  public static Feature esriGeometryToFeature(JSONObject geometry) throws ShapefileException, IOException {
    JSONObject jsonFeature = new JSONObject();
    jsonFeature.put("attributes", new JSONObject());
    jsonFeature.put("geometry",geometry);
    return EsriJsonUtils.esriFeatureToFeature(jsonFeature.toString());
  }

}
