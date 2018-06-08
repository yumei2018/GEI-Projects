/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.ca.water.shapelite.io;

import java.io.InputStream;
import org.json.JSONArray;

/**
 *
 * @author zchapman
 */
public interface EgisImportJson {
    public boolean hasExtension(String ext);
    public String toJson(InputStream stream);
    public JSONArray toJsonObj(InputStream stream);
}
