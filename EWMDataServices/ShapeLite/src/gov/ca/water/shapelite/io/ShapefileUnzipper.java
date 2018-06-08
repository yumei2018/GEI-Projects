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
package gov.ca.water.shapelite.io;

import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.FileHelper;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * This class unzips a zip library that contains one or more shapefiles, and
 * returns all the shapefiles within the library as featuresets.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class ShapefileUnzipper {

  /**
   * Private constructor for utility class.
   */
  private ShapefileUnzipper() {

  }

  /**
   * The File to unzip.
   *
   * @param zipFile The zipFile.
   * @return The List of FeatureSet objects that were unzipped.
   * @throws FileNotFoundException if the file cannot be found.
   * @throws IOException if there is an error reading the zip stream.
   */
  public static List<FeatureSet> unzip(File zipFile) throws FileNotFoundException,
      IOException {
    FileInputStream fInput = new FileInputStream(zipFile);
    return unzip(fInput);
  }

  /**
   * The File to unzip.
   *
   * @param inputStream The zipFile as an input stream.
   * @return The List of FeatureSet objects that were unzipped.
   * @throws FileNotFoundException if the file cannot be found.
   * @throws IOException if there is an error reading the zip stream.
   */
  public static List<FeatureSet> unzip(InputStream inputStream)
      throws FileNotFoundException,
      IOException {
    List<FeatureSet> result = new ArrayList<>();
    HashMap<String, byte[]> files = new HashMap<>();
    ZipInputStream zipInput = new ZipInputStream(inputStream);
    ZipEntry ze = zipInput.getNextEntry();
    List<String> names = new ArrayList<>();
    while (ze != null) {
      byte[] data = ZipUtils.readAll(zipInput, ze.getSize());
      files.put(ze.getName(), data);
      if (ze.getName().endsWith(".shp")) {
        names.add(ze.getName());
      }
      ze = zipInput.getNextEntry();
    }
    for (String shp : names) {
      String base = FileHelper.removeExtension(shp);
      String shx = base + ".shx";
      String dbf = base + ".dbf";
      String prj = base + ".prj";
      if (files.containsKey(shx) && files.containsKey(dbf)) {
        InputStream shpStream = new ByteArrayInputStream(files.get(shp));
        InputStream shxStream = new ByteArrayInputStream(files.get(shx));
        InputStream dbfStream = new ByteArrayInputStream(files.get(dbf));
        InputStream prjStream = null;
        if (files.containsKey(prj)) {
          prjStream = new ByteArrayInputStream(files.get(prj));
        }
        ShapefileReader reader = new ShapefileReader();
        reader.open(shpStream, shxStream, dbfStream, prjStream);
        FeatureSet fs = reader.getFeatures();
        fs.setName(base);
        result.add(fs);
      }
    }

    return result;
  }

}
