/*
 * The MIT License
 *
 * Copyright 2016 Harold A. Dunsford Jr. Ph.D..
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package gov.ca.water.shapelite.io.json;

import gov.ca.water.shapelite.FileHelper;
import gov.ca.water.shapelite.Optional;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class JsonSource {

  /**
   * The base URL.
   */
  private final String address;

  /**
   * Creates a new instance of a JsonSource with the specified addresss.
   *
   * @param address The String base address.
   */
  public JsonSource(String address) {
    this.address = address;
  }

  /**
   * Gets the JSON for the layer definition.
   *
   * @param layerIndex The integer layer index.
   * @return The String JSON containing the layer definition, or an empty
   * optional if there were errors.
   */
  public final Optional<String> getDefinition(int layerIndex) {
    try {
      URL url = new URL(address + "/" + layerIndex + "?f=pjson");
      return Optional.ofNullable(FileHelper.readAll(url.openStream()));
    } catch (IOException ex) {
      Logger.getLogger(JsonSource.class.getName()).log(Level.SEVERE, null, ex);
    }
    return Optional.empty();
  }

  /**
   * Getting the features requires a separate query.
   *
   * @param layerIndex The integer index of the layer to get the count for.
   * @return The Optional string in a Text ESRICount format.
   */
  public final Optional<String> getFeatureCount(int layerIndex) {
    try {
      URL countUrl = new URL(address + "/" + layerIndex
          + "/query?where=1%3E0&returnCountOnly=true&f=pjson");
      return Optional.ofNullable(FileHelper.readAll(countUrl.openStream()));
    } catch (MalformedURLException ex) {
      Logger.getLogger(JsonSource.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(JsonSource.class.getName()).log(Level.SEVERE, null, ex);
    }
    return Optional.empty();
  }

  /**
   * Getting the features based on the specified filter.
   *
   * @param layerIndex Layer index.
   * @param filter The String filter.
   * @return An optional string formatted as a Json representation of a
   * JsonFeatureSet.
   */
  public final Optional<String> getFeatureSet(int layerIndex, String filter) {
    try {
      URL dataUrl = new URL(address + "/" + layerIndex + "/query?where=1%3E0"
          + filter);
      return Optional.ofNullable(FileHelper.readAll(dataUrl.openStream()));
    } catch (MalformedURLException ex) {
      Logger.getLogger(JsonSource.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(JsonSource.class.getName()).log(Level.SEVERE, null, ex);
    }
    return Optional.empty();
  }

  /**
   * Getting the features based on the specified filter. This assumes that the
   * FID field is present.
   *
   * @param layerIndex Layer index.
   * @param filter The String filter.
   * @param startIndex The integer index of the first feature to return
   * inclusive.
   * @param endIndex the Integer index of the last feature to return, inclusive.
   * @return An optional string formatted as a Json representation of a
   * JsonFeatureSet.
   */
  public final Optional<String> getFeatureSet(int layerIndex, String filter,
      int startIndex, int endIndex) {
    try {
      URL dataUrl = new URL(address + "/" + layerIndex + "/query?where=FID+>+"
          + (startIndex - 1) + "+AND+FID+<+" + (endIndex + 1) + filter);
      return Optional.ofNullable(FileHelper.readAll(dataUrl.openStream()));
    } catch (MalformedURLException ex) {
      Logger.getLogger(JsonSource.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(JsonSource.class.getName()).log(Level.SEVERE, null, ex);
    }
    return Optional.empty();
  }
}
