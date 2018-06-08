package entity.core.context;

import entity.core.util.WebUtil;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapImageContext implements Serializable {
  
  //<editor-fold defaultstate="collapsed" desc="Private Properties">
  private Map<String,byte[]> mImageBinaries;
  
  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    this.mImageBinaries = null;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Constructor">
  public MapImageContext(){
    this.mImageBinaries = new LinkedHashMap<>();
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Methods">
  public byte[] getImageBinary(String id) {
    return this.mImageBinaries.get(id);
  }
  
  public MapImageContext setImageBinary(String id, byte[] barr) {
    this.mImageBinaries.put(id, barr);
    return this;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Public Static Methods">
  public static MapImageContext getInstance(){
    return WebUtil.getContext(MapImageContext.class);
  }
  //</editor-fold>
}
