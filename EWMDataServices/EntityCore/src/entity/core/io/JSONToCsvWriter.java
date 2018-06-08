/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.core.io;

import com.opencsv.CSVWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author aleshchuk
 */
public class JSONToCsvWriter extends CSVWriter {

  public JSONToCsvWriter(Writer writer) {
    super(writer, CSVWriter.DEFAULT_SEPARATOR, CSVWriter.DEFAULT_QUOTE_CHARACTER);
  }

  public void writeAll(JSONArray data) {
    if (data == null || data.length() == 0){
      return;
    }
    Set<String> headersSet = getHeaders(data);
    String[] header = headersSet.toArray(new String[headersSet.size()]);
    List<String[]> body = getBody(data, header);
    writeContent(header, body);
  }

  protected Set<String> getHeaders(JSONArray data) {
    Set<String> headers = new HashSet<>();
    if (isJsonArray(data.get(0))){
      int len = data.getJSONArray(0).length();
      for (int i=0; i<len; i++){
        headers.add("Field_" + (i+1));
      }
    } else {
      for (int i=0; i<data.length(); i++){
        JSONObject jsonRow = data.getJSONObject(i);
        Iterator<String> iter = jsonRow.keys();
        String headerVal;
        for (; iter.hasNext();){
          headerVal = iter.next();
          if (headerVal.compareToIgnoreCase("geometry") != 0){
            headers.add(headerVal);
          }
        }
      }
    }
    return headers;
  }

  protected List<String[]> getBody(JSONArray data, String[] headers) {
    List<String[]> body = new ArrayList<>();
    boolean isJsonArray = isJsonArray(data.get(0)); // test if row data are JSONArrays
    if (isJsonArray){
      for (int i=0; i<data.length(); i++){
        JSONArray jsonRow = data.getJSONArray(i);
        String[] row = new String[headers.length];
        for (int h=0; h<headers.length; h++){
          row[h] = jsonRow.get(h).toString();
        }
        body.add(row);
      }
    } else {
      // treat as JSONObjects
      for (int i=0; i<data.length(); i++){
        JSONObject jsonRow = data.getJSONObject(i);
        String[] row = new String[headers.length];
        for (int h=0; h<headers.length; h++){
          if (headers[h].compareToIgnoreCase("geometry") != 0){
            row[h] = jsonRow.get(headers[h]).toString();
          }
        }
        body.add(row);
      }
    }
    
    return body;
  }
  
  protected boolean isJsonArray(Object json){
    boolean res = false;
    try {
      if (json instanceof JSONArray){
        res = true;
      }
    } catch (Exception e) {
    }
    
    return res;
  }

  protected void writeContent(String[] header, List<String[]> body) {
    this.writeNext(header);
    this.writeAll(body);
  }
  
}
