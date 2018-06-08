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
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.coordinate.CoordXY;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class JsonFeatureSetTest {

  /**
   * A sample JSON for testing.
   */
  private static final String TEST_POINT_JSON = "{\n"
      + " \"displayFieldName\":\"LMA_Name\",\n"
      + "  \"fieldAliases\": { \n"
      + "    \"OBJECTID_1\":\"OBJECTID_1\",\n"
      + "    \"OBJECTID\":\"OBJECTID\",\n"
      + "    \"LMA_ID\":\"LMA_ID\",\n"
      + "    \"LMA_Name\":\"LMA_Name\",\n"
      + "    \"LMA_Short\":\"LMA_Short\",\n"
      + "    \"Agency_Nm\":\"Agency_Nm\",\n"
      + "    \"LMAUnit_ID\":\"LMAUnit_ID\",\n"
      + "    \"Unit_Code\":\"Unit_Code\",\n"
      + "    \"LMAUnit_Nm\":\"LMAUnit_Nm\",\n"
      + "    \"Bank\":\"Bank\",\n"
      + "    \"County\":\"County\",\n"
      + "    \"KML_ID\":\"KML_ID\",\n"
      + "    \"ID\":\"ID\",\n"
      + "    \"Levee_Mile\":\"Levee_Mile\",\n"
      + "    \"Longitude\":\"Longitude\",\n"
      + "    \"Latitude\":\"Latitude\"\n"
      + "  },\n"
      + "  \"geometryType\":\"esriGeometryPoint\",\n"
      + "  \"spatialReference\":{\"wkid\":3857},\n"
      + "  \"fields\":[{\"name\":\"OBJECTID_1\",\"type\":\"esriFieldTypeOID\","
      + "\"alias\":\"OBJECTID_1\"},\n"
      + "    {\"name\":\"OBJECTID\",\"type\":\"esriFieldTypeInteger\","
      + "\"alias\":\"OBJECTID\"},\n"
      + "    {\"name\":\"LMA_ID\",\"type\":\"esriFieldTypeInteger\","
      + "\"alias\":\"LMA_ID\"},\n"
      + "    {\"name\":\"LMA_Name\",\"type\":\"esriFieldTypeString\","
      + "\"alias\":\"LMA_Name\",\"length\":75},\n"
      + "    {\"name\":\"LMA_Short\",\"type\":\"esriFieldTypeString\","
      + "\"alias\":\"LMA_Short\",\"length\":10},\n"
      + "    {\"name\":\"Agency_Nm\",\"type\":\"esriFieldTypeString\","
      + "\"alias\":\"Agency_Nm\",\"length\":75},\n"
      + "    {\"name\":\"LMAUnit_ID\",\"type\":\"esriFieldTypeInteger\","
      + "\"alias\":\"LMAUnit_ID\"},\n"
      + "    {\"name\":\"Unit_Code\",\"type\":\"esriFieldTypeString\","
      + "\"alias\":\"Unit_Code\",\"length\":10},\n"
      + "    {\"name\":\"LMAUnit_Nm\",\"type\":\"esriFieldTypeString\","
      + "\"alias\":\"LMAUnit_Nm\",\"length\":75},\n"
      + "    {\"name\":\"Bank\",\"type\":\"esriFieldTypeString\","
      + "\"alias\":\"Bank\",\"length\":5},\n"
      + "    {\"name\":\"County\",\"type\":\"esriFieldTypeString\","
      + "\"alias\":\"County\",\"length\":50},\n"
      + "    {\"name\":\"KML_ID\",\"type\":\"esriFieldTypeString\","
      + "\"alias\":\"KML_ID\",\"length\":200},\n"
      + "    {\"name\":\"ID\",\"type\":\"esriFieldTypeInteger\","
      + "\"alias\":\"ID\"},\n"
      + "    {\"name\":\"Levee_Mile\",\"type\":\"esriFieldTypeDouble\","
      + "\"alias\":\"Levee_Mile\"},\n"
      + "    {\"name\":\"Longitude\",\"type\":\"esriFieldTypeDouble\","
      + "\"alias\":\"Longitude\"},\n"
      + "    {\"name\":\"Latitude\",\"type\":\"esriFieldTypeDouble\","
      + "\"alias\":\"Latitude\"}],\n"
      + "  \"features\":[{\n"
      + "    \"attributes\":{\n"
      + "      \"OBJECTID_1\":56,\n"
      + "      \"OBJECTID\":6604,\n"
      + "      \"LMA_ID\":84,\n"
      + "      \"LMA_Name\":\"Butte County Public Works\",\n"
      + "      \"LMA_Short\":\"NA0003\",\n"
      + "      \"Agency_Nm\":\"Butte County Public Works\",\n"
      + "      \"LMAUnit_ID\":64,\n"
      + "      \"Unit_Code\":\"U01\",\n"
      + "      \"LMAUnit_Nm\":\"Unit No. 01 Mud Creek\",\n"
      + "      \"Bank\":\"RB\",\n"
      + "      \"County\":\"Butte\",\n"
      + "      \"KML_ID\":\"NA0003 - Unit No. 01 Mud Creek (6.8 miles)\",\n"
      + "      \"ID\":7,\n"
      + "      \"Levee_Mile\":0,\n"
      + "      \"Longitude\":-121.936280551,\n"
      + "      \"Latitude\":39.7264048499},\n"
      + "      \"geometry\":{\n"
      + "        \"x\":-13573884.660153722,\n"
      + "        \"y\":4826263.4669894828\n"
      + "      }\n"
      + "    }\n"
      + "  ]\n"
      + "}";

  private JsonFeatureSet<JsonPoint> fsPoints;

  /**
   * Test lines.
   */
  private static final String TEST_LINE_JSON = "{\n"
      + " \"displayFieldName\": \"L_TYPE\",\n"
      + " \"fieldAliases\": {\n"
      + "  \"L_TYPE\": \"L_TYPE\"\n"
      + " },\n"
      + " \"geometryType\": \"esriGeometryPolyline\",\n"
      + " \"spatialReference\": {\n"
      + "  \"wkid\": 26910,\n"
      + "  \"latestWkid\": 26910\n"
      + " },\n"
      + " \"fields\": [\n"
      + "  {\n"
      + "   \"name\": \"L_TYPE\",\n"
      + "   \"type\": \"esriFieldTypeString\",\n"
      + "   \"alias\": \"L_TYPE\",\n"
      + "   \"length\": 40\n"
      + "  }\n"
      + " ],\n"
      + " \"features\": [\n"
      + "  {\n"
      + "   \"attributes\": {\n"
      + "    \"L_TYPE\": \"Not-specified\"\n"
      + "   },\n"
      + "   \"geometry\": {\n"
      + "    \"paths\": [\n"
      + "     [\n"
      + "      [\n"
      + "       741907.60900000017,\n"
      + "       4071969.5390000008\n"
      + "      ],\n"
      + "      [\n"
      + "       741908.52479999978,\n"
      + "       4071969.5899\n"
      + "      ],\n"
      + "      [\n"
      + "       741910.19460000005,\n"
      + "       4071969.5390000008\n"
      + "      ]\n"
      + "     ]\n"
      + "    ]\n"
      + "   }\n"
      + "  }]\n"
      + "}";

  private JsonFeatureSet<JsonLine> fsLines;

  private JsonFeatureSet<JsonPolygon> fsPolygons;

  public JsonFeatureSetTest() {

  }

  /**
   * Gets a point featureset.
   *
   * @return
   */
  JsonFeatureSet<JsonPoint> getPointFeatureset() {
    if (fsPoints == null) {
      Gson gson = new Gson();
      Type pointFeatureType = new TypeToken<JsonFeatureSet<JsonPoint>>() {
      }.getType();
      fsPoints = gson.fromJson(TEST_POINT_JSON, pointFeatureType);
    }
    return fsPoints;
  }

  /**
   * Gets a line featureset.
   *
   * @return
   */
  JsonFeatureSet<JsonLine> getLineFeatureset() {
    if (fsLines == null) {
      Gson gson = new Gson();
      Type lineFeatureType = new TypeToken<JsonFeatureSet<JsonLine>>() {
      }.getType();
      fsLines = gson.fromJson(TEST_LINE_JSON, lineFeatureType);
    }
    return fsLines;
  }

  /**
   * Gets a pre-configured polygon from a stored example json.
   *
   * @return
   */
  JsonFeatureSet<JsonPolygon> getPolygonFeatureset() {
    if (fsPolygons == null) {
      InputStream stream = this.getClass().getResourceAsStream(
          "resources/PolygonExample.json");
      Scanner s = new Scanner(stream).useDelimiter("\\A");
      if (s.hasNext()) {
        String json = s.next();
        Gson gson = new Gson();
        Type polygonFeatureType = new TypeToken<JsonFeatureSet<JsonPolygon>>() {
        }.getType();
        fsPolygons = gson.fromJson(json, polygonFeatureType);
      }
    }
    return fsPolygons;
  }

  /**
   * Test of toFeatureSet method, of class JsonFeatureSet.
   */
  @Test
  public void testToPointFeatureSet() {
    System.out.println("toFeatureSet Point");
    JsonFeatureSet<JsonPoint> instance = getPointFeatureset();

    CoordXY expResult = new CoordXY(-13573884.660153722, 4826263.4669894828);
    FeatureSet result = instance.toFeatureSet(false, false);
    assertEquals(expResult, result.getFeatures().get(0).getShape().first());
  }

  @Test
  public void testToLineFeatureSet() {
    System.out.println("toFeatureSet Line");
    JsonFeatureSet<JsonLine> instance = getLineFeatureset();

    CoordXY expResult = new CoordXY(741907.60900000017, 4071969.5390000008);
    FeatureSet result = instance.toFeatureSet(false, false);
    assertEquals(expResult, result.getFeatures().get(0).getShape().first());
  }

  @Test
  public void testToPolygonFeatureSet() {
    System.out.println("toFeatureSet Polygon");
    JsonFeatureSet<JsonPolygon> instance = getPolygonFeatureset();
    CoordXY expResult = new CoordXY(728376.76530000009, 4082696.1467000004);
    FeatureSet result = instance.toFeatureSet(false, false);
    assertEquals(expResult, result.getFeatures().get(0).getShape().first());
  }

  /**
   * Test of getDisplayFieldName method, of class JsonFeatureSet.
   */
  @Test
  public void testDisplayFieldName() {
    System.out.println("getDisplayFieldName");
    JsonFeatureSet<JsonPoint> instance = new JsonFeatureSet<>();
    String expResult = "DisplayFieldName";
    instance.setDisplayFieldName(expResult);
    String result = instance.getDisplayFieldName();
    assertEquals(expResult, result);
  }

  /**
   * Test of getFieldAliases method, of class JsonFeatureSet.
   */
  @Test
  public void testGetFieldAliases() {
    System.out.println("getFieldAliases");
    JsonFeatureSet<JsonPoint> instance = new JsonFeatureSet<>();
    String expResult = "Test1";
    instance.getFieldAliases().put("Alias", expResult);
    String result = instance.getFieldAliases().get("Alias");
    assertEquals(expResult, result);
  }

  /**
   * Test of setFieldAliases method, of class JsonFeatureSet.
   */
  @Test
  public void testSetFieldAliases() {
    System.out.println("setFieldAliases");
    HashMap<String, String> fieldAliases = new HashMap<>();
    String expResult = "Test2";
    fieldAliases.put("Alias", expResult);
    JsonFeatureSet<JsonPoint> instance = new JsonFeatureSet<>();
    instance.setFieldAliases(fieldAliases);
    String result = instance.getFieldAliases().get("Alias");
    assertEquals(expResult, result);
  }

  /**
   * Test of getGeometryType method, of class JsonFeatureSet.
   */
  @Test
  public void testGeometryType() {
    System.out.println("getGeometryType");
    JsonFeatureSet<JsonPoint> instance = new JsonFeatureSet<>();
    EsriGeometryType expResult = EsriGeometryType.esriGeometryPolygon;
    instance.setGeometryType(expResult);
    EsriGeometryType result = instance.getGeometryType();
    assertEquals(expResult, result);
  }

  /**
   * Test of getFields method, of class JsonFeatureSet.
   */
  @Test
  public void testGetFields() {
    System.out.println("getFields");
    JsonFeatureSet<JsonPoint> instance = new JsonFeatureSet<>();
    JsonField expResult = new JsonField();
    instance.getFields().add(expResult);
    JsonField result = instance.getFields().get(0);
    assertEquals(expResult, result);
  }

  /**
   * Test of setFields method, of class JsonFeatureSet.
   */
  @Test
  public void testSetFields() {
    System.out.println("setFields");
    List<JsonField> fields = new ArrayList<>();
    JsonFeatureSet<JsonPoint> instance = new JsonFeatureSet<>();
    instance.setFields(fields);

  }

  /**
   * Test of getFeatures method, of class JsonFeatureSet.
   */
  @Test
  public void testGetFeatures() {
    System.out.println("getFeatures");
    JsonFeatureSet<JsonPoint> instance = new JsonFeatureSet<>();
    JsonFeature<JsonPoint> expResult = new JsonFeature<>();
    expResult.setGeometry(new JsonPoint(10, 20));
    instance.getFeatures().add(expResult);
    JsonFeature<JsonPoint> result = instance.getFeatures().get(0);
    assertEquals(expResult, result);
  }

  /**
   * Test of setFeatures method, of class JsonFeatureSet.
   */
  @Test
  public void testSetFeatures() {
    System.out.println("setFeatures");
    List<JsonFeature<JsonPoint>> features = new ArrayList<>();
    JsonFeature<JsonPoint> expResult = new JsonFeature<>();
    expResult.setGeometry(new JsonPoint(10, 20));
    features.add(expResult);
    JsonFeatureSet<JsonPoint> instance = new JsonFeatureSet<>();
    instance.setFeatures(features);
    JsonFeature<?> result = instance.getFeatures().get(0);
    assertEquals(expResult, result);
  }

}
