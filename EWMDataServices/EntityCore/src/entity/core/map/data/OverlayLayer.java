package entity.core.map.data;

import entity.core.map.util.ImageUtil;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

public class OverlayLayer implements Overlayable {

  //<editor-fold defaultstate="collapsed" desc="Static Logger">
  public static final Logger logger = Logger.getLogger(OverlayLayer.class.getName());
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Private Properties">
  protected URL url;
  protected BufferedImage image;
  protected float transparency;

  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Override Methods">
  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    this.url = null;
    this.image = null;
  }
  //</editor-fold>
    
  //<editor-fold defaultstate="collapsed" desc="Constructor">
  public OverlayLayer(URL imageUrl, float transparency) {
    this.url = imageUrl;
    this.transparency = transparency;
    try {
      this.image = ImageUtil.readImage(this.url);
    } catch (Exception e) {
      logger.log(Level.SEVERE, e.getMessage());
      throw new IllegalStateException(
              String.format("%s.%s Error(s):\n%s"
                      ,this.getClass().getName()
                      ,"OverlayLayer"
                      ,e.getMessage()
              )
      );
    }
  }
//</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Methods">
  public URL getImageUrl() {
    return url;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Overlayable Implementation Methods">
  @Override
  public float getTransparency() {
    return transparency;
  }
  
  @Override
  public BufferedImage getImage() {
    return image;
  }
  
  @Override
  public int getHeight() {
    return image != null ? image.getHeight() : 0;
  }
  
  @Override
  public int getWidth() {
    return image != null ? image.getWidth() : 0;
  }
  
  @Override
  public int getType() {
    return image.getType();
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Static Methods">
  /**
   * 
   * @param json
   * @return
   * @throws Exception 
   */
  public static OverlayLayer fromJson(JSONObject json) throws Exception {
    OverlayLayer layer = null;
    try {
      String url = json.getString("url");
      float transparency = (float) json.getDouble("transparency");
      layer = new OverlayLayer(new URL(url), transparency);
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
  public static List<OverlayLayer> fromJson(JSONArray json) throws Exception {
    List<OverlayLayer> layers = new ArrayList<OverlayLayer>();
    try {
      for (int i = 0; i < json.length(); i++) {
        OverlayLayer layer = fromJson(json.getJSONObject(i));
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
