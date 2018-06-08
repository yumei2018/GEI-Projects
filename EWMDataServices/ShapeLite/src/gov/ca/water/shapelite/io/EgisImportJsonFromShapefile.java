/*
 * Zachary Chapman
 */
package gov.ca.water.shapelite.io;

//<editor-fold defaultstate="collapsed" desc="Imports">
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.Field;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//</editor-fold>

/**
 * @author zchapman
 */
public class EgisImportJsonFromShapefile extends AbstractEgisImportJson implements EgisImportJson {

    @Override
    public boolean hasExtension(String ext) {
        return ext.equals("zip");
    }

    @Override
    @SuppressWarnings("UseSpecificCatch")
    public String toJson(InputStream stream) {
        try {
            return new EgisImportJsonFromShapefile(stream).buildString();
        }
        catch (Exception ex) {
            return null;
        }
    }

    @Override
    @SuppressWarnings("UseSpecificCatch")
    public JSONArray toJsonObj(InputStream stream) {
        try {
            return new EgisImportJsonFromShapefile(stream).buildJson();
        }
        catch (Exception ex) {
            return null;
        }
    }

    protected List<ShapefileReader> shapefiles;
    protected ProjectionInfo projection;

    public EgisImportJsonFromShapefile() {}

    public EgisImportJsonFromShapefile(InputStream stream) throws IOException {
        try {
          ZipInputStream input = new ZipInputStream(stream);
          InputStream dbf = null;
          InputStream shx = null;
          InputStream shp = null;
          InputStream prj = null;
          ByteArrayOutputStream output = null;
            shapefiles = new ArrayList<>();
//            File dir = new ZipUtil().unzipToFile(stream);
          ZipEntry entry = null;
          while((entry = input.getNextEntry()) != null)
          {
            output = new ByteArrayOutputStream();
            IOUtils.copy(input, output);
            if(entry.getName().endsWith(".dbf"))
              dbf = new ByteArrayInputStream(output.toByteArray());
            if(entry.getName().endsWith(".shx"))
              shx = new ByteArrayInputStream(output.toByteArray());
            if(entry.getName().endsWith(".shp"))
              shp = new ByteArrayInputStream(output.toByteArray());     
            if(entry.getName().endsWith(".prj"))
              prj = new ByteArrayInputStream(output.toByteArray());              
          }      
          ShapefileReader sr =new ShapefileReader();
          sr.open(shp, shx, dbf, prj);
          shapefiles.add(sr);
//            for (String shapefile : ShapefileReader.listShapefiles(dir))
//                shapefiles.add(new ShapefileReader().open(shapefile));
//            dir.delete();
        }
        catch (Exception ex) {
            throw new IOException(ex);
        }
    }

    @Override
    public JSONArray buildJson() {
        JSONArray json = new JSONArray();
        for (ShapefileReader shapefile : shapefiles)
            try {
              concat(json, parseShapefile(shapefile));
            } catch (FileNotFoundException ex) {
              Logger.getLogger(EgisImportJsonFromShapefile.class.getName()).log(Level.SEVERE, null, ex);
            }
        return json;
    }

    protected JSONArray parseShapefile(ShapefileReader shapefile) throws FileNotFoundException {
        JSONArray json = new JSONArray();
        String projText = shapefile.getProjection();
        if (projText != null)
            try {
                projection = ProjectionInfo.fromEsriString(projText);
            }
            catch (Exception ex) {
                projection = null;
            }
        else
            projection = null;

        List<Feature> features = shapefile.getFeatures().getFeatures();
        List<Field> fields = shapefile.getAttributes().getFields();
        for (Feature feature : features)
        {
          JSONObject retJson = parseFeature(feature, fields);
          if(retJson != null)
            json.put(parseFeature(feature, fields));
        }
        return json;
    }

    protected JSONObject parseFeature(Feature feature, List<Field> fields) {
        try {
            Shape shape = feature.getShape();
            ShapeType type = shape.getShapeType();
            HashMap<String, String> attr = feature.getAttributes();
            if (ShapeType.isPoint(type))
                return parsePoint(shape, attr, fields);
            else if (ShapeType.isPolyLine(type))
                return parsePolyLine(shape, attr, fields);
            else if (ShapeType.isPolygon(type))
                return parsePolygon(shape, attr, fields);
            else if (ShapeType.isMultiPoint(type))
                return parseMultiPoint(shape, attr, fields);
            else
                return new JSONObject();
        }
        catch (Exception ex) {
            return null;
        }
    }

    protected JSONObject parsePoint(Shape shape, HashMap<String, String> map, List<Field> fields) throws IOException {
        JSONObject json = new JSONObject();

        JSONObject geometry = newGeometry();
        JSONArray coords = parseParts(shape);
        geometry.put("x", coords.getJSONArray(0).getJSONArray(0).getDouble(0));
        geometry.put("y", coords.getJSONArray(0).getJSONArray(0).getDouble(1));

        JSONObject attr = newAttributes(map, fields);

        JSONObject symbol = defaultPointSymbol();

        json.put("type", "esri.geometry.Point");
        json.put("geometry", geometry);
        json.put("attributes", attr);
        json.put("symbol", symbol);
        return json;
    }

    protected JSONObject parsePolyLine(Shape shape, HashMap<String, String> map, List<Field> fields) throws IOException {
        JSONObject json = new JSONObject();

        JSONObject geometry = newGeometry();
        geometry.put("paths", parseParts(shape));

        JSONObject attr = newAttributes(map, fields);

        JSONObject symbol = defaultLineSymbol();

        json.put("type", "esri.geometry.Polyline");
        json.put("geometry", geometry);
        json.put("attributes", attr);
        json.put("symbol", symbol);
        return json;
    }

    protected JSONObject parsePolygon(Shape shape, HashMap<String, String> map, List<Field> fields) throws IOException {
        JSONObject json = new JSONObject();

        JSONObject geometry = newGeometry();
        geometry.put("rings", parseParts(shape));

        JSONObject attr = newAttributes(map, fields);

        JSONObject symbol = defaultPolygonSymbol();

        json.put("type", "esri.geometry.Polygon");
        json.put("geometry", geometry);
        json.put("attributes", attr);
        json.put("symbol", symbol);

        return json;
    }

    protected JSONObject parseMultiPoint(Shape shape, HashMap<String, String> attr, List<Field> fields) {
        JSONObject json = new JSONObject();
        return json;
    }

    protected JSONArray parseXY(Coord wgs) throws IOException {
        Coord merc;
        if (projection != null)
            merc = parseCoord(wgs, projection);
        else
            merc = parseCoord(wgs);
        JSONArray json = new JSONArray();
        try{
          if(!Double.isInfinite(merc.getX()) && !Double.isNaN(merc.getX()) && !Double.isInfinite(merc.getY()) && !Double.isNaN(merc.getY()))
          {
            json.put(merc.getX());          
            json.put(merc.getY());
          }
          else
          {
            System.out.println("Error in setting merc.X and merc.Y in EgisImportJsonFromShapefile line 195");
          }
        }
        catch(JSONException ex){
          System.out.println(ex.getMessage());
          ex.printStackTrace();
        }
        return json;
    }

    protected JSONArray parseCoords(List<Coord> coords) throws IOException {
        JSONArray json = new JSONArray();
        for (Coord xy : coords)
            json.put(parseXY(xy));

        return json;
    }

    protected JSONArray parseParts(Shape shape) throws IOException {
        return parseParts(shape.getParts());
    }

    protected JSONArray parseParts(List<Part> parts) throws IOException {
        JSONArray json = new JSONArray();
        for (Part part : parts)
            json.put(parseCoords(part.getCoordinates()));

        return json;
    }

    protected String getNotes(HashMap<String, String> attr) {
        if (attr.containsKey("content"))
            return attr.get("content");
        else
            return "";
    }

    protected void putTitle(HashMap<String, String> map, JSONObject attr) {
        if (map.containsKey("title"))
            attr.put("title", map.get("title"));
    }

    protected void putStyle(HashMap<String, String> map, JSONObject attr) {
        if (map.containsKey("style"))
            attr.put("style", map.get("style"));
    }

    protected JSONObject newAttributes(HashMap<String, String> map, List<Field> fields) {
        String notes = getNotes(map);
        JSONObject attr;
        attr = mapToJson(map, fields);
        return attr;
    }
    
    protected JSONObject mapToJson(HashMap<String,String> map, List<Field> fields)
    {
      JSONObject obj = new JSONObject();
      for(int i=0; i<fields.size(); i++)
      {
        obj.put(fields.get(i).getName(), map.get(fields.get(i).getName()));
      }
//      Iterator iter = map.keySet().iterator();
//      while (iter.hasNext())
//      {
//        String key = (String)iter.next();
//        String value = map.get(key);
//        obj.put(key,value);
//      }
      return obj;
    }
}
