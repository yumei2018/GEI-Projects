/*
 * The MIT License
 *
 * Copyright 2014 hdunsford.
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
package gov.ca.water.shapelite.projection;

import gov.ca.water.shapelite.Optional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipInputStream;

/**
 * This class provides access to a single shared instance of
 * AuthorityCodeHandler.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class AuthorityCodeHandler {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * A HashMap of authority code projections.
   */
  private final HashMap<String, String> authorityCodeToProj4 = new HashMap<>();

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Singleton">
  /**
   * Creates a new instance of the AuthorityCodeHandler class. This is private
   * as part of the singleton pattern.
   */
  private AuthorityCodeHandler() {
    readDefault();
    // readCustom();
  }

  /**
   * Gets the single shared static instance of the AuthorityCodeHandler class.
   *
   * @return An AthorityCodeHandler instance.
   */
  public static AuthorityCodeHandler getInstance() {
    return AuthorityCodeHandlerHolder.INSTANCE;
  }

  /**
   * Gets the static internal class that is part of the Singleton Pattern in
   * Java.
   */
  private static class AuthorityCodeHandlerHolder {

    /**
     * The single instance of the Authority Code Handler.
     */
    private static final AuthorityCodeHandler INSTANCE
        = new AuthorityCodeHandler();
  }

  //</editor-fold>
  /**
   *
   * @param authorityCodeOrName The string athority code or projection name.
   * @return The ProjectionInfo or an empty optional if this was not found.
   * @throws ProjectionException If there was an error parsing the serialized
   * projection information.
   */
  public Optional<ProjectionInfo> getProjectionInfo(String authorityCodeOrName)
      throws ProjectionException {
    if (authorityCodeToProj4.containsKey(authorityCodeOrName)) {
      Optional<ProjectionInfo> result = ProjectionInfo.fromProj4String(
          authorityCodeToProj4.get(authorityCodeOrName));
      if (result.isPresent()) {
        result.get().setAuthority(authorityCodeOrName);
      }
      return result;
    }
    return Optional.empty();
  }

  /**
   * Reads the default handler.
   */
  private void readDefault() {
    try {
      InputStream stream = getClass().getResourceAsStream(
          "resources/AuthorityCodetoProj4.zip");
      ZipInputStream zipStream = new ZipInputStream(stream);
      String line;
      BufferedReader reader = new BufferedReader(new InputStreamReader(zipStream));
      while ((line = reader.readLine()) != null) {
        line = line.trim();
        if (line.isEmpty() || line.startsWith("#")) {
          continue; // skip remarks, indicated with # symbols
        }
        String[] parts = line.split("\t");
        if (parts.length > 1) {
          String code = parts[0];
          String proj4 = parts[1];
          authorityCodeToProj4.put(code, proj4);

        }
      }
    } catch (IOException ex) {
      Logger.getLogger(AuthorityCodeHandler.class.getName()).log(
              Level.SEVERE, ex.getMessage(), ex);
    }
  }

}
