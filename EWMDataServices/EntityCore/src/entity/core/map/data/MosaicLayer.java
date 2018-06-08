package entity.core.map.data;

import java.io.Serializable;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

public class MosaicLayer implements Serializable {

  //<editor-fold defaultstate="collapsed" desc="Static Logger">
  public static final Logger logger = Logger.getLogger(MosaicLayer.class.getName());
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Private Properties">
  protected URL url;
  protected int row;
  protected int col;
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Override Methods">
  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    this.url = null;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Methods">
  public URL getUrl() {
    return url;
  }

  public void setUrl(URL url) {
    this.url = url;
  }

  public int getRow() {
    return row;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public int getCol() {
    return col;
  }

  public void setCol(int col) {
    this.col = col;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Static Methods">
  public static MosaicLayer fromJson(JSONObject json) {
    MosaicLayer layer = null;
    try {
      layer = new MosaicLayer();
      layer.setUrl(new URL(json.getString("url")));
      layer.setRow(json.getInt("row"));
      layer.setCol(json.getInt("col"));
    } catch (Exception e) {
      logger.log(Level.CONFIG, "Error reading Json, " + e.getMessage());
      throw new IllegalStateException(
        String.format("%s.%s Error(s):\n%s"
          ,MosaicLayer.class.getName()
          ,"fromJson"
          ,e.getMessage()
        )
      );
    }
    return layer;
  }
  //</editor-fold>
}
