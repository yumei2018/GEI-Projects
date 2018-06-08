package entity.core.util;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author clay
 */
public class JsonUtil {
  
  //<editor-fold defaultstate="collapsed" desc="Merge">
  public final static JSONObject merge(JSONObject json1, JSONObject json2) {
    return JsonUtil.merge(json1, json2, true);
  }
  
  public final static JSONObject merge(JSONObject json1, JSONObject json2, boolean overrite) {
    JSONObject result = new JSONObject();
    try {
      JsonUtil.append(result, json1, overrite);
      JsonUtil.append(result, json2, overrite);
    } 
    catch (Exception e) {
      throw new IllegalStateException(
        String.format("%s.%s %s:\n%s"
          ,JsonUtil.class.getName()
          ,"merge(JSONObject, JSONObject, boolean)"
          ,e.getClass().getName()
          ,e.getMessage()
        )
      );
    }
    
    return result;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Append">
  public final static void append(JSONObject result, JSONObject json2) {
    JsonUtil.append(result, json2, false);
  }
  
  public final static void append(JSONObject result, JSONObject json, boolean overrite) {
    JSONArray keys = json.names();
    String key = null;
    for (int i =0;i < keys.length(); ++i) {
      key = keys.getString(i);
      if (!result.has(key) || (result.has(key) && overrite)) {
        result.put(key, json.opt(key));
      }
    }
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Clone">
  public final static JSONObject clone(JSONObject json) {
    JSONObject result = new JSONObject();
    return JsonUtil.merge(result, json);
  }
  //</editor-fold>
}
