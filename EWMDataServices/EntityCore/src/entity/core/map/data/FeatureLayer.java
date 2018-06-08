package entity.core.map.data;

import entity.core.map.util.ImageUtil;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

public class FeatureLayer implements Overlayable {

  //<editor-fold defaultstate="collapsed" desc="Static Logger">
  public static final Logger logger = Logger.getLogger(FeatureLayer.class.getName());
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Private Properties">
  //shapes
  protected List<Shape> shapes;
  //image properties
  protected BufferedImage image;
  protected int width;
  protected int height;
  //symbology
  List<Color> colors;
  List<String> imageTypes;
  List<Double> transparentList;
  float transparency;
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Constructor">
  public FeatureLayer(List<Shape> shapes, List<Color> colors, float transparency, int width, int height, List<String> imagetypes, List<Double> transparentList) {
    this.shapes = shapes;
    this.width = width;
    this.height = height;
    this.transparency = transparency;
    this.colors = colors;
    this.imageTypes = imagetypes;
    this.transparentList = transparentList;
    //based on the shapes generate the buffered image
    try {
      image = ImageUtil.generateFeatureRepresentation(this);
    } catch (Exception e) {
      logger.log(Level.SEVERE, e.getMessage());
      throw new IllegalStateException(
              String.format("%s.%s Error(s):\n%s"
                      ,this.getClass().getName()
                      ,"FeatureLayer"
                      ,e.getMessage()
              )
      );
    }
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Override Methods">
  @Override
  protected void finalize() throws Throwable {
    super.finalize();
    this.shapes = null;
    this.image = null;
    this.colors = null;
    this.imageTypes = null;
    this.transparentList = null;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Overlayable Implementation Methods">
  @Override
  public BufferedImage getImage() {
    return image;
  }
  
  @Override
  public int getWidth() {
    return width;
  }
  
  @Override
  public int getHeight() {
    return height;
  }
  
  @Override
  public float getTransparency() {
    return transparency;
  }
  
  @Override
  public int getType() {
    return image.getType();
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Methods">
  public List<Color> getColors() {
    return colors;
  }
  
  public List<Shape> getShapes() {
    return shapes;
  }
  
  public List<String> getImageTypes() {
    return imageTypes;
  }
  
  public List<Double> getTransparentList() {
    return transparentList;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Public Static Methods">
  /**
   * 
   * @param json
   * @return
   * @throws Exception 
   */
  public static FeatureLayer fromJson(JSONObject json) throws Exception {
    return fromJson(json, false);
  }
  
  /**
   * geometries supported are points and polygons (simple with one ring)
   * 
   * @param json
   * @param outline
   * @return
   * @throws Exception 
   */
  public static FeatureLayer fromJson(JSONObject json, boolean outline) throws Exception {
    FeatureLayer layer = null;
    List<Shape> shapes = new ArrayList<>();
    List<Color> colors = new ArrayList<>();
    List<String> imageTypes = new ArrayList<>();
    List<Double> transparentList = new ArrayList<>();
    String type = "outline";
    try {
      JSONArray jshapes = json.getJSONArray("geometries");
      JSONArray jcolors = json.getJSONArray("colors");
      if (jshapes == null || jcolors == null || !(jshapes.length() == jcolors.length())) {
        throw new Exception("Invalid Input.");
      }
      //get color info
      JSONObject jcolor;
      for (int i = 0; i < jshapes.length(); i++) {
        //get geometry info
        JSONObject jshape = jshapes.getJSONObject(i);
        String geometryType = jshape.getString("geometryType");
        if ("esriGeometryPoint".equalsIgnoreCase(geometryType)) {
          double x = jshape.getDouble("x");
          double y = jshape.getDouble("y");
          double trans = json.getDouble("transparency");
          Ellipse2D.Double circle = new Ellipse2D.Double(x, y, 10, 10);
          shapes.add(circle);
          jcolor = jcolors.getJSONObject(i);
          int r = jcolor.getInt("r");
          int g = jcolor.getInt("g");
          int b = jcolor.getInt("b");
          Color c = new Color(r, g, b);
          colors.add(c);
          transparentList.add(trans);
          if (!outline) {
            type = "circle";
          }
          imageTypes.add(type);
        } else if ("esriGeometryPolygon".equalsIgnoreCase(geometryType)) {
          JSONArray rings = jshape.getJSONArray("rings");
          double trans = json.getDouble("transparency");
          if (rings.length() > 0) {
            //take the first ring
            JSONArray fRing = rings.getJSONArray(0);
            int noPoints = fRing.length();
            int[] xcoords = new int[noPoints];
            int[] ycoords = new int[noPoints];
            for (int j = 0; j < noPoints; j++) {
              JSONArray point = fRing.getJSONArray(j);
              if (point.length() == 2) {
                xcoords[j] = Math.round((float) point.getDouble(0));
                ycoords[j] = Math.round((float) point.getDouble(1));
              } else {
                throw new Exception("Invalid Input Geometry");
              }
            }
            Polygon polygon = new Polygon(xcoords, ycoords, noPoints);
            shapes.add(polygon);
            jcolor = jcolors.getJSONObject(i);
            int r = jcolor.getInt("r");
            int g = jcolor.getInt("g");
            int b = jcolor.getInt("b");
            Color c = new Color(r, g, b);
            colors.add(c);
            transparentList.add(trans);
            if (!outline) {
              type = "polygon";
            }
            imageTypes.add(type);
          } else {
            throw new Exception("Invalid Input Geometry");
          }
        } else if ("esriGeometryPolyline".equalsIgnoreCase(geometryType) || "esriGeometryLine".equalsIgnoreCase(geometryType)) {
          JSONArray paths = jshape.getJSONArray("paths");
          double trans = json.getDouble("transparency");
          if (paths.length() > 0) {
            //take the first ring
            JSONArray fPath = paths.getJSONArray(0);
            int noPoints = fPath.length();
            for (int j = 0; j < noPoints - 1; j++) {
              JSONArray point = fPath.getJSONArray(j);
              JSONArray point2 = fPath.getJSONArray(j + 1);
              if (point.length() == 2 && point2.length() == 2) {
                Line2D.Float polyline = new Line2D.Float((float) point.getDouble(0), (float) point.getDouble(1), (float) point2.getDouble(0), (float) point2.getDouble(1));
                shapes.add(polyline);
                jcolor = jcolors.getJSONObject(i);
                int r = jcolor.getInt("r");
                int g = jcolor.getInt("g");
                int b = jcolor.getInt("b");
                Color c = new Color(r, g, b);
                colors.add(c);
                transparentList.add(trans);
                if (!outline) {
                  type = "polyline";
                }
                imageTypes.add(type);
                //xcoords[j] = Math.round((float) point.getDouble(0));
                //ycoords[j] = Math.round((float)point.getDouble(1));
              } else {
                throw new Exception("Invalid Input Geometry");
              }
            }
          } else {
            throw new Exception("Invalid Input Geometry");
          }
        } else {
          throw new Exception("Unsupported feature type.");
        }
      }
      float transparency = (float) json.getDouble("transparency");
      int width = json.getInt("width");
      int height = json.getInt("height");
      layer = new FeatureLayer(shapes, colors, transparency, width, height, imageTypes, transparentList);
    } catch (Exception e) {
      logger.log(Level.SEVERE, "Invalid input json, " + e.getMessage());
      throw e;
    }
    return layer;
  }
  
  /**
   * 
   * @param json
   * @return
   * @throws Exception 
   */
  public static List<FeatureLayer> fromJson(JSONArray json) throws Exception {
    List<FeatureLayer> layers = new ArrayList<FeatureLayer>();
    try {
      for (int i = 0; i < json.length(); i++) {
        FeatureLayer layer = fromJson(json.getJSONObject(i));
        layers.add(layer);
        layer = null;
      }
      //Add outline.
      for (int i = 0; i < json.length(); i++) {
        FeatureLayer layer = fromJson(json.getJSONObject(i), true);
        layers.add(layer);
      }
    } catch (Exception e) {
      logger.log(Level.SEVERE, "Invalid input json, " + e.getMessage());
      throw e;
    }
    return layers;
  }
  //</editor-fold>
}
