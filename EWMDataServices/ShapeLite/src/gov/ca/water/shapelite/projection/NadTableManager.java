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

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class provides access to a single shared instance of NadTableManager.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class NadTableManager {

  /**
   * The length of the prefix characters in the jar URI before the jar content.
   */
  private static final int PREFIX = 5;

  /**
   * The Hash map of tables.
   */
  private final HashMap<String, NadTable> tables;

  /**
   * The Error logger for this class.
   */
  private static final Logger LOGGER = Logger.getLogger(
      NadTableManager.class.getName());
  //<editor-fold defaultstate="collapsed" desc="Singleton">

  /**
   * Creates a new instance of the NadTableManager class. This is private as
   * part of the singleton pattern.
   */
  private NadTableManager() {

    tables = new HashMap<>();
    try {

      String[] files = getResourceListing();

      for (String s : files) {
        File f = new File(s);
        String fname = f.getName();
        String name = fname;
        int p = fname.lastIndexOf(".");
        String ext = "";
        if (p > 0) {
          name = fname.substring(0, p);
          ext = fname.substring(p);
        }
        if (ext.contains("lla")) {
          NadTable nt = NadTable.fromSourceName(fname);
          if (nt != null) {
            tables.put(name, nt);
          }
        }
      }

    } catch (URISyntaxException | IOException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
    }

  }

  /**
   * Gets the single shared static instance of the NadTableManager class.
   *
   * @return NatTableManager instance.
   */
  public static NadTableManager getInstance() {
    return NadTableManagerHolder.INSTANCE;
  }

  /**
   * Gets the static internal class that is part of the Singleton Pattern in
   * Java.
   */
  private static class NadTableManagerHolder {

    /**
     * The single instance for this class.
     */
    private static final NadTableManager INSTANCE = new NadTableManager();
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * @return the tables
   */
  public HashMap<String, NadTable> getTables() {
    return tables;
  }

  /**
   * List directory contents for a resource folder. Not recursive. This is
   * basically a brute-force implementation. Works for regular files and also
   * JARs.
   *
   * @return Just the name of each member item, not the full paths.
   * @throws URISyntaxException if there is a problem parsing the path.
   * @throws IOException If there is a problem opening a file.
   */
  private String[] getResourceListing() throws IOException, URISyntaxException {
    URL dirURL = NadTableManager.class.getResource("resources/");
    if (dirURL != null && "file".equals(dirURL.getProtocol())) {
      /* A file path: easy enough */
      return Paths.get(dirURL.toURI()).toFile().list();
    }

    if (dirURL == null) {
      /*
             * In case of a jar file, we can't actually find a directory.
             * Have to assume the same jar as clazz.
       */
      String me = NadTableManager.class.getName().replace(".", "/") + ".class";
      dirURL = NadTableManager.class.getClassLoader().getResource(me);
    }

    if ("jar".equals(dirURL.getProtocol())) {
      /* A JAR path */
      //strip out only the JAR file
      String jarPath = dirURL.getPath().substring(PREFIX, dirURL.getPath().indexOf("!"));
      JarFile jar = new JarFile(URLDecoder.decode(jarPath, "UTF-8"));
      Enumeration<JarEntry> entries = jar.entries(); //gives ALL entries in jar
      //avoid duplicates in case it is a subdirectory
      Set<String> result = new HashSet<>();
      while (entries.hasMoreElements()) {
        String name = entries.nextElement().getName();
        String base = "gov/ca/water/shapelite/projection/resources/";
        int start = name.indexOf(base);
        if (start > -1) { //filter according to the path
          String entry = name.substring(start
              + "gov/ca/water/shapelite/projection/resources/".length());
          int checkSubdir = entry.indexOf("/");
          if (checkSubdir >= 0) {
            // if it is a subdirectory, we just return the directory name
            entry = entry.substring(0, checkSubdir);
          }
          result.add(entry);
        }
      }
      return result.toArray(new String[result.size()]);
    }

    throw new UnsupportedOperationException("Cannot list files for URL " + dirURL);
  }

  //</editor-fold>
}
