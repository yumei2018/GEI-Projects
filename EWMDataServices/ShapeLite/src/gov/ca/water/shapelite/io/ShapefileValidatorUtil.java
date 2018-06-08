/*
 * The MIT License
 *
 * Copyright 2016 zwaller.
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

import com.google.gson.Gson;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.ShapeCategory;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 *
 * @author zwaller
 */
public final class ShapefileValidatorUtil {

  /**
   * private constructor for utils class.
   */
  private ShapefileValidatorUtil() {

  }
//<editor-fold desc="">

  /**
   * Passes in the String filelocation of zip file, geometry type then tests to
   * see if the file can be opened, and correct geometry type If expected
   * projection or geometry are set to null then the validation test for them
   * will be skipped.
   *
   * @param pathfile Location of .zip file.
   * @return string - json format {success: boolean, message: error_string}
   * @throws Exception Error containing message with what broke or failed to
   * validate.
   */
//</editor-fold>
  static public String validateForWeb(String pathfile) throws Exception {
    JSON json = new JSON();
    try {
      json.setSuccess(validate(pathfile, null, null));
    } catch (Exception e) {
      json.setMessage(e.getMessage());
    }
    return json.toString();
  }

//<editor-fold desc="">
  /**
   * Passes in the String filelocation of zip file, geometry type then tests to
   * see if the file can be opened, and correct geometry type If expected
   * projection or geometry are set to null then the validation test for them
   * will be skipped.
   *
   * @param inputstream Inputstream of .zip file.
   * @return string - json format {success: boolean, message: error_string}
   * @throws Exception Error containing message with what broke or failed to
   * validate.
   */
  //</editor-fold>
  static public String validateForWeb(InputStream inputstream) throws Exception {
    JSON json = new JSON();
    try {
      json.setSuccess(validate(inputstream, null, null));
    } catch (Exception e) {
      json.setMessage(e.getMessage());
    }
    return json.toString();
  }

  //<editor-fold desc="">
  /**
   * Passes in the String filelocation of zip file, the epsg code , and geometry
   * type then tests to see if the file can be opened, is the expected
   * projection, and correct geometry type If expected projection or geometry
   * are set to null then the validation test for them will be skipped.
   *
   * @param pathfile Location of .zip file. Cannot be null.
   * @param epsgcode EPSG code for expected projection.
   * @param shapeCategoryName String for the shape category type, can be:
   * <ul>
   * <li>Polygon</li>
   * <li>Point</li>
   * <li>PolyLine</li>
   * <li>Null</li>
   * <li>MultiPoint</li>
   *
   * </ul>
   * @return string - json format {success: boolean, message: error_string}
   */
  //</editor-fold>
  static public String validateForWeb(String pathfile, int epsgcode,
      String shapeCategoryName) {
    JSON json = new JSON();
    try {
      json.setSuccess(validate(pathfile, epsgcode, shapeCategoryName));
    } catch (Exception e) {
      json.setMessage(e.getMessage());
    }
    return json.toString();
  }

  //<editor-fold desc="">
  /**
   * Passes in the String filelocation of zip file, the epsg code , and geometry
   * type then tests to see if the file can be opened, is the expected
   * projection, and correct geometry type If expected projection or geometry
   * are set to null then the validation test for them will be skipped.
   *
   * @param pathfile Location of .zip file. Cannot be null.
   * @param projdatum Full string for expected projection. Can be null.
   * @param shapeCategoryName String for the shape category type, can be:
   * <ul>
   * <li>Polygon</li>
   * <li>Point</li>
   * <li>PolyLine</li>
   * <li>Null</li>
   * <li>MultiPoint</li>
   *
   * </ul>
   * @return string - json format {success: boolean, message: error_string}
   * @throws Exception Error containing message with what broke or failed to
   * validate.
   */
  //</editor-fold>
  static public String validateForWeb(String pathfile, String projdatum,
      String shapeCategoryName) throws Exception {
    JSON json = new JSON();
    try {
      json.setSuccess(validate(pathfile, projdatum, shapeCategoryName));
    } catch (Exception e) {
      json.setMessage(e.getMessage());
    }
    return json.toString();
  }

  //<editor-fold desc="">
  /**
   * Passes in the String filelocation of zip file, the epsg code , and geometry
   * type then tests to see if the file can be opened, is the expected
   * projection, and correct geometry type If expected projection or geometry
   * are set to null then the validation test for them will be skipped.
   *
   * @param is Inputstream for .zip file. Cannot be null.
   * @param epsgcode EPSG code for expected projection.
   * @param shapeCategoryName String for the shape category type, can be:
   * <ul>
   * <li>Polygon</li>
   * <li>Point</li>
   * <li>PolyLine</li>
   * <li>Null</li>
   * <li>MultiPoint</li>
   *
   * </ul>
   * @return string - json format {success: boolean, message: error_string}
   */
  //</editor-fold>
  static public String validateForWeb(InputStream is, int epsgcode,
      String shapeCategoryName) {
    JSON json = new JSON();
    try {
      json.setSuccess(validate(is, epsgcode, shapeCategoryName));
    } catch (Exception e) {
      json.setMessage(e.getMessage());
    }
    return json.toString();
  }

  //<editor-fold desc="">
  /**
   * Passes in the String filelocation of zip file, the epsg code , and geometry
   * type then tests to see if the file can be opened, is the expected
   * projection, and correct geometry type If expected projection or geometry
   * are set to null then the validation test for them will be skipped.
   *
   * @param is Inputstream for .zip file. Cannot be null.
   * @param projdatum Full string for expected projection. Can be null.
   * @param shapeCategoryName String for the shape category type, can be:
   * <ul>
   * <li>Polygon</li>
   * <li>Point</li>
   * <li>PolyLine</li>
   * <li>Null</li>
   * <li>MultiPoint</li>
   *
   * </ul>
   * @return string - json format {success: boolean, message: error_string}
   * @throws Exception Error containing message with what broke or failed to
   * validate.
   */
  //</editor-fold>
  static public String validateForWeb(InputStream is, String projdatum,
      String shapeCategoryName) throws Exception {
    JSON json = new JSON();
    try {
      json.setSuccess(validate(is, projdatum, shapeCategoryName));
    } catch (Exception e) {
      json.setMessage(e.getMessage());
    }
    return json.toString();
  }

//<editor-fold desc="">
  /**
   * Passes in the String filelocation of zip file, the projection string , and
   * geometry type then tests to see if the file can be opened, is the expected
   * projection, and correct geometry type If expected projection or geometry
   * are set to null then the validation test for them will be skipped.
   *
   * @param pathfile Location of .zip file.
   * @return boolean Was the validation successful?
   * @throws Exception Error containing message with what broke or failed to
   * validate.
   */
  //</editor-fold>
  static public boolean validate(String pathfile) throws Exception {
    return validate(pathfile, null, null);
  }

  //<editor-fold desc="">
  /**
   * Passes in the String filelocation of zip file, the projection string , and
   * geometry type then tests to see if the file can be opened, is the expected
   * projection, and correct geometry type If expected projection or geometry
   * are set to null then the validation test for them will be skipped.
   *
   * @param inputstream Inputstream of .zip file.
   * @return boolean Was the validation successful?
   * @throws Exception Error containing message with what broke or failed to
   * validate.
   */
  //</editor-fold>
  static public boolean validate(InputStream inputstream) throws Exception {
    return validate(inputstream, null, null);
  }

  //<editor-fold desc="">
  /**
   * Passes in the String filelocation of zip file, the epsg code , and geometry
   * type then tests to see if the file can be opened, is the expected
   * projection, and correct geometry type If expected projection or geometry
   * are set to null then the validation test for them will be skipped.
   *
   * @param pathfile Location of .zip file. Cannot be null.
   * @param epsgcode EPSG code for expected projection.
   * @param shapeCategoryName String for the shape category type, can be:
   * <ul>
   * <li>Polygon</li>
   * <li>Point</li>
   * <li>PolyLine</li>
   * <li>Null</li>
   * <li>MultiPoint</li>
   *
   * </ul>
   * @return boolean Was the validation successful?
   * @throws Exception Error containing message with what broke or failed to
   * validate.
   */
  //</editor-fold>
  static public boolean validate(String pathfile, int epsgcode,
      String shapeCategoryName) throws Exception {
    Optional<ProjectionInfo> maybeProjection = ProjectionInfo.fromEpsgCode(epsgcode);
    String projString = null;

    if (maybeProjection.isPresent()) {
      ProjectionInfo info = maybeProjection.get();
      projString = info.toEsriString();
    } else {
      throw new Exception("Unable to resove EPSG code to projection string.");
    }

    return validate(pathfile, projString, shapeCategoryName);
  }

  //<editor-fold desc="">
  /**
   * Passes in the String filelocation of zip file, the epsg code , and geometry
   * type then tests to see if the file can be opened, is the expected
   * projection, and correct geometry type If expected projection or geometry
   * are set to null then the validation test for them will be skipped.
   *
   * @param inputstream Inputstream for .zip file. Cannot be null.
   * @param epsgcode EPSG code for expected projection.
   * @param shapeCategoryName String for the shape category type, can be:
   * <ul>
   * <li>Polygon</li>
   * <li>Point</li>
   * <li>PolyLine</li>
   * <li>Null</li>
   * <li>MultiPoint</li>
   *
   * </ul>
   * @return boolean Was the validation successful?
   * @throws Exception Error containing message with what broke or failed to
   * validate.
   */
  //</editor-fold>
  static public boolean validate(InputStream inputstream, int epsgcode,
      String shapeCategoryName) throws Exception {
    Optional<ProjectionInfo> maybeProjection = ProjectionInfo.fromEpsgCode(epsgcode);
    String projString = null;

    if (maybeProjection.isPresent()) {
      ProjectionInfo info = maybeProjection.get();
      projString = info.toEsriString();
    } else {
      throw new Exception("Unable to resove EPSG code to projection string.");
    }

    return validate(inputstream, projString, shapeCategoryName);
  }

  //<editor-fold desc="">
  /**
   * Passes in the String filelocation of zip file, the projection string , and
   * geometry type then tests to see if the file can be opened, is the expected
   * projection, and correct geometry type If expected projection or geometry
   * are set to null then the validation test for them will be skipped.
   *
   * @param pathfile Location of .zip file. Cannot be null.
   * @param projdatum Full string for expected projection. Can be null.
   * @param shapeCategoryName String for the shape category type, can be:
   * <ul>
   * <li>Polygon</li>
   * <li>Point</li>
   * <li>PolyLine</li>
   * <li>Null</li>
   * <li>MultiPoint</li>
   *
   * </ul>
   * @return boolean Was the validation successful?
   * @throws Exception Error containing message with what broke or failed to
   * validate.
   */
  //</editor-fold>
  static public boolean validate(String pathfile, String projdatum,
      String shapeCategoryName) throws Exception {
    ShapefileReader sr = new ShapefileReader();
    /*Check here if file is a zip and if it can be opened*/
    try {
      if (!isZipped(pathfile)) {
        throw new Exception("Please upload a zip file that compresses ALL of "
            + "the files associated with the shapefile (at minimum, .dbf, "
            + ".prj, .shp, .shx)");
      }
      if (!hasAllFiles(pathfile)) {
        throw new Exception("The .ZIP file is missing the required files for a"
            + " shape file which includes: .dbf, .prj, .shp, .shx.");
      }
      sr.open(pathfile); //Can the file be opened. If not, exception thrown
    } catch (Exception e) {
      throw e;
    }
    /*End file open check*/

 /*Check here if the projection matches*/
    if (projdatum != null) {
      if (sr.getProjection().compareTo(projdatum) != 0) {
        throw new Exception("Please upload a shape in the specified projection "
            + "and datum.");
      }
    }
    /*End projection check*/

 /*Check here if shapefile matches the expected type*/
    if (!isSameType(sr, shapeCategoryName)) {
      throw new Exception("Please check the geometry type of the shape "
          + "file. Specified: " + shapeCategoryName + ".  Found: "
          + sr.getFeatures().getFeatureType().getCategory().name()
          + ".");
    }
    /*End type check*/

    return true;
  }

  //<editor-fold desc="">
  /**
   * Passes in the String filelocation of zip file, the projection string , and
   * geometry type then tests to see if the file can be opened, is the expected
   * projection, and correct geometry type If expected projection or geometry
   * are set to null then the validation test for them will be skipped.
   *
   * @param inputstream Inputstream for .zip file. Cannot be null.
   * @param projdatum Full string for expected projection. Can be null.
   * @param shapeCategoryName String for the shape category type, can be:
   * <ul>
   * <li>Polygon</li>
   * <li>Point</li>
   * <li>PolyLine</li>
   * <li>Null</li>
   * <li>MultiPoint</li>
   *
   * </ul>
   * @return boolean Was the validation successful?
   * @throws Exception Error containing message with what broke or failed to
   * validate.
   */
  //</editor-fold>
  static public boolean validate(InputStream inputstream, String projdatum,
      String shapeCategoryName) throws Exception {
    ShapefileReader sr = new ShapefileReader();
    /*Check here if file is a zip and if it can be opened*/
    try {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();

      byte[] buffer = new byte[1024];
      int len;
      while ((len = inputstream.read(buffer)) > -1) {
        baos.write(buffer, 0, len);
      }
      baos.flush();

      if (!isZipped(new ByteArrayInputStream(baos.toByteArray()))) {
        throw new Exception("Please upload a zip file that compresses ALL of "
            + "the files associated with the shapefile (at minimum, .dbf, "
            + ".prj, .shp, .shx)");
      }
      if (!hasAllFiles(new ByteArrayInputStream(baos.toByteArray()))) {
        throw new Exception("The .ZIP file is missing the required files for a"
            + " shape file which includes: .dbf, .prj, .shp, .shx.");
      }
      sr.open(new ByteArrayInputStream(baos.toByteArray())); //Can the file be opened. If not, exception thrown
    } catch (Exception e) {
      throw e;
    }
    /*End file open check*/

 /*Check here if the projection matches*/
    if (projdatum != null) {
      if (sr.getProjection().compareTo(projdatum) != 0) {
        throw new Exception("Please upload a shape in the specified projection "
            + "and datum.");
      }
    }
    /*End projection check*/

 /*Check here if shapefile matches the expected type*/
    if (!isSameType(sr, shapeCategoryName)) {
      throw new Exception("Please check the geometry type of the shape "
          + "file. Specified: " + shapeCategoryName + ".  Found: "
          + sr.getFeatures().getFeatureType().getCategory().name()
          + ".");
    }
    /*End type check*/

    return true;
  }

  //<editor-fold desc="">
  /**
   * Tests to if filepath is a zip file
   *
   * @param pathfile Location of .zip file.
   * @return boolean Is zipped or not
   * @throws Exception
   */
  //</editor-fold>
  static private boolean isZipped(String pathfile) throws Exception {
    InputStream is = new FileInputStream(pathfile);
    return isZipped(is);
  }

  //<editor-fold desc="">
  /**
   * Tests to if filepath is a zip file
   *
   * @param is Zip file
   * @return boolean Is zipped or not
   * @throws Exception
   */
  //</editor-fold>
  static private boolean isZipped(InputStream is) throws Exception {
    try {
      ZipInputStream zis = new ZipInputStream(is);
      boolean isZipped = zis.getNextEntry() != null;
      zis.close();
      return isZipped;
    } catch (Exception e) {
      throw e;
    }
  }

  //<editor-fold desc="">
  /**
   * Tests to if the shapefile shape type matches the shapeCategoryName
   *
   * @param sr ShapefileReader used to check the current .
   * @param shapeCategoryName String for the shape category type, can be:
   * <ul>
   * <li>Polygon</li>
   * <li>Point</li>
   * <li>PolyLine</li>
   * <li>Null</li>
   * <li>MultiPoint</li>
   * </ul>
   * @return boolean Is zipped or not
   * @throws Exception
   */
  //</editor-fold>
  static private boolean isSameType(ShapefileReader sr,
      String shapeCategoryName) throws Exception {
    if (shapeCategoryName != null) {
      ShapeCategory shp_test = null;
      try {
        for (ShapeCategory category : ShapeCategory.values()) {
          if (category.name().equalsIgnoreCase(shapeCategoryName)) {
            shp_test = category;
          }
        }
        if (sr.getFeatures().getFeatureType().getCategory().equals(shp_test)) {
          return true;
        } else {
          return false;
        }
      } catch (Exception e) {
        throw e;
      }
    }
    return true;
  }

  //<editor-fold desc="">
  /**
   * Tests to see if zip file contains required files requires:
   *
   * * @param shapeCategoryName String for the shape category type, can be:
   * <ul>
   * <li>.dbf</li>
   * <li>.shx</li>
   * <li>.shp</li>
   * <li>.prj</li>
   * </ul>
   *
   * @param pathfile Location of .zip file.
   * @return boolean Are all files present
   * @throws Exception
   */
  //</editor-fold>
  static private boolean hasAllFiles(String pathfile) throws Exception {

    return hasAllFiles(new FileInputStream(pathfile));
  }

  //<editor-fold desc="">
  /**
   * Tests to see if zip file contains required files requires:
   *
   * * @param shapeCategoryName String for the shape category type, can be:
   * <ul>
   * <li>.dbf</li>
   * <li>.shx</li>
   * <li>.shp</li>
   * <li>.prj</li>
   * </ul>
   *
   * @param is Location of .zip file.
   * @return boolean Are all files present
   * @throws Exception
   */
  //</editor-fold>
  static private boolean hasAllFiles(InputStream is) throws Exception {

    try {
      ZipInputStream zis = new ZipInputStream(is);
      boolean hasFiles = false;
      boolean dbf = false;
      boolean prj = false;
      boolean shp = false;
      boolean shx = false;
      ZipEntry entry = zis.getNextEntry();
      while (entry != null) {
        String name = entry.getName().toLowerCase();
        if (name.lastIndexOf('.') != -1 && name.indexOf('/') == -1) {
          String ext = name.substring(name.lastIndexOf('.'), name.length());
          switch (ext) {
            case ".dbf":
              dbf = true;
              break;
            case ".prj":
              prj = true;
              break;
            case ".shp":
              shp = true;
              break;
            case ".shx":
              shx = true;
              break;
          }
        }
        entry = zis.getNextEntry();
      }
      zis.close();
      return dbf && prj && shp && shx;
    } catch (Exception e) {
      throw e;
    }
  }

  static private class JSON {

    private boolean success = false;
    private String message = "";

    public void JSON() {
    }

    public void setSuccess(boolean success) {
      this.success = success;
    }

    public void setMessage(String message) {
      this.message = message;
    }

    public boolean isSuccess() {
      return success;
    }

    public String getMessage() {
      return message;
    }

    public String toString() {
      Gson result = new Gson();
      return result.toJson(this);
    }
  }

}
