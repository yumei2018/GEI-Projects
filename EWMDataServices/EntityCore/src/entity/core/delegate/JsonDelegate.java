package entity.core.delegate;

import org.json.JSONObject;

/**
 *
 * @author clay
 */
public abstract class JsonDelegate extends DelegateBase {
  
  //<editor-fold defaultstate="collapsed" desc="Constructor(s)">
  public JsonDelegate() {
    super(null);
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Set Listener">
  public abstract void handle(JSONObject json) throws Exception;
  //</editor-fold>
}