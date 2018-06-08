/*
 * Zachary Chapman
 */
package gov.ca.water.shapelite.io;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Projections;
import gov.ca.water.shapelite.projection.Reproject;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author zchapman
 */
public abstract class AbstractEgisImportJson {

    protected final ProjectionInfo wgs84 = Projections.getWGS84();
    protected final ProjectionInfo webmercator = Projections.fromEPSG(102100).get();

    public abstract JSONArray buildJson();

    public String buildString() {
        return buildJson().toString();
    }

    protected JSONObject newWkid() {
        JSONObject json = new JSONObject();
        json.put("wkid", 102100);
        return json;
    }

    protected JSONObject newGeometry() {
        JSONObject json = new JSONObject();
        json.put("spatialReference", newWkid());
        return json;
    }

    protected JSONObject newAttributes(String notes) {
        JSONObject json = new JSONObject();
        json.put("title", "Map Notes");
        json.put("content", notes);
        json.put("style", "height: 100%; overflow: hidden;");
        return json;
    }

    protected JSONObject newImageSymbol(double width, double height, String url, double scale) {
        JSONObject json = new JSONObject();
        json.put("angle", 0);
        json.put("xoffset", 0);
        json.put("yoffset", 0);
        json.put("type", "esriPMS");
        json.put("url", url);
        json.put("width", width * scale);
        json.put("height", height * scale);
        return json;
    }

    protected JSONObject newPointSymbol(double size, JSONArray fill, double lineWidth, JSONArray stroke) {
        JSONObject json = new JSONObject();
        json.put("angle", 0);
        json.put("xoffset", 0);
        json.put("yoffset", 0);
        json.put("type", "esriSMS");
        json.put("size", size);
        json.put("color", fill);
        json.put("outline", newLineSymbol(stroke, lineWidth));
        json.put("style", "esriSMSCircle");
        return json;
    }

    protected JSONObject newLineSymbol(JSONArray color, double width) {
        JSONObject json = new JSONObject();
        json.put("color", color);
        json.put("width", width);
        json.put("type", "esriSLS");
        json.put("style", "esriSLSSolid");
        return json;
    }

    protected JSONObject newPolygonSymbol(JSONArray fill, JSONArray stroke, double lineWidth) {
        JSONObject json = new JSONObject();
        json.put("color", fill);
        json.put("outline", newLineSymbol(stroke, lineWidth));
        json.put("type", "esriSFS");
        json.put("style", "esriSFSSolid");
        return json;
    }

    protected Coord parseCoord(String x, String y) throws IOException {
        double xWgs = Double.parseDouble(x);
        double yWgs = Double.parseDouble(y);
        Coord wgs = new CoordXY(xWgs, yWgs);
        return parseCoord(wgs);
    }

    protected Coord parseCoord(Coord coord) throws IOException {
        return parseCoord(coord, wgs84);
    }

    protected Coord parseCoord(Coord coord, ProjectionInfo from) throws IOException {
        return parseCoord(coord, from, webmercator);
    }

    protected Coord parseCoord(Coord coord, ProjectionInfo from, ProjectionInfo to) throws IOException {
        try {
            return Reproject.reprojectCoordinate(coord, from, to);
        }
        catch (ProjectionException ex) {
            throw new IOException(ex);
        }
    }

    protected JSONArray toColor(int r, int g, int b) {
        return toColor(r, g, b, 255);
    }

    protected JSONArray toColor(int r, int g, int b, int a) {
        JSONArray json = new JSONArray();
        json.put(r);
        json.put(g);
        json.put(b);
        json.put(a);
        return json;
    }

    protected JSONArray wrap(JSONArray json) {
        JSONArray result = new JSONArray();
        result.put(json);
        return result;
    }

    protected JSONArray concat(JSONArray a, JSONArray b) {
        for (int i = 0; i < b.length(); i++)
            a.put(b.get(i));
        return a;
    }

    protected JSONArray defaultFillColor() {
        return toColor(40, 128, 191, 255);
    }
    
    protected JSONArray defaultStrokeColor() {
        return toColor(40, 128, 191);
    }
    
    protected JSONArray defaultOutlineColor() {
        return toColor(100, 100, 100);
    }

    protected double defaultStrokeWidth() {
        return 0.75;//1.333;
    }

    protected JSONObject defaultPointSymbol() {
        return newPointSymbol(16, defaultFillColor(), defaultStrokeWidth(), defaultOutlineColor());
    }

    protected JSONObject defaultPolygonSymbol() {
        return newPolygonSymbol(defaultFillColor(), defaultOutlineColor(), defaultStrokeWidth());
    }

    protected JSONObject defaultLineSymbol() {
        return newLineSymbol(defaultStrokeColor(), defaultStrokeWidth());
    }
}
