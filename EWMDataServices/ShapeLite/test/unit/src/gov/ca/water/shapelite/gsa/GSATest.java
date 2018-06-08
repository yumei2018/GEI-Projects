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
package gov.ca.water.shapelite.gsa;

import com.vividsolutions.jts.util.Assert;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.ShapefileException;
import gov.ca.water.shapelite.gsa.resources.Polygons;
import gov.ca.water.shapelite.io.ShapefileReader;
import gov.ca.water.shapelite.io.ShapefileWriter;
import gov.ca.water.shapelite.projection.ProjectionException;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class GSATest {

  @Rule
  public TemporaryFolder testFolder = new TemporaryFolder();

  /**
   * List of the five string polygon shapefile names, including the temp folder.
   */
  private String[] polygon;

  /**
   * The path to the combined GSA shapefile.
   */
  private String masterGSA;

  /**
   * The path to the output folder where intersections are written.
   */
  private String outputFolder;

  /**
   * The manager.
   */
  private GSAPolygonManager manager;

  /**
   * Create the test folder with the zip files polygon 1 - 5 from the embedded
   * files.
   */
  @Before
  public void setup() {

    try {

      outputFolder = testFolder.getRoot().getAbsolutePath();
      File folder = new File(outputFolder);
      if (!folder.exists()) {
        folder.mkdirs();
      }

      System.out.println("Folder: " + outputFolder);
      polygon = new String[6];
      ShapefileWriter writer = new ShapefileWriter();
      for (int i = 1; i < 6; i++) {
        polygon[i] = outputFolder + File.separator + "polygon" + i + ".zip";
        Optional<FeatureSet> maybePolygon = Polygons.get("Polygon " + i + ".zip");
        if (maybePolygon.isPresent()) {
          FeatureSet poly = maybePolygon.get();
          writer.write(poly, polygon[i]);
        }
      }

      masterGSA = outputFolder + File.separator + "GSA_Master.zip";
      File f = new File(masterGSA);
      if (f.exists()) {
        f.delete();
      }
      manager = new GSAPolygonManager();
      manager.setGsaAreasShapefile(null);
      manager.setAgencyServiceAreasShapefile(null);
      manager.setServiceAreas(new FeatureSet(ShapeType.Polygon));
      manager.setGsaAreas(new FeatureSet(ShapeType.Polygon));


    } catch (ShapefileException ex) {
      Logger.getLogger(GSATest.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @After
  public void remove(){
    testFolder.delete();
  }

  @Test
  public void test() throws IOException, ShapefileException, ProjectionException {
    String intersections = addGSA(1);
    Assert.equals("{}", intersections);
  }

  @Test
  public void test2() throws IOException, ShapefileException, ProjectionException {
    addGSA(1);
    String intersections = addGSA(2);
    Assert.equals("{1}", intersections);
    Assert.equals(1, numShapesInMaster("1"));
    Assert.equals(1, numShapesInIntersect("2","1"));
  }

  @Test
  public void test3() throws IOException, ShapefileException, ProjectionException {
    addGSA(1);
    addGSA(2);
    removeGSA(2);
    Assert.equals(1, numShapesInMaster("1"));
  }

  @Test
  public void test4() throws IOException, ShapefileException, ProjectionException {
    addGSA(1);
    addGSA(2);
    removeGSA(2);
    String intersections = addGSA(3);
    Assert.equals("{1}", intersections);
    Assert.equals(1, numShapesInMaster("1"));
    Assert.equals(0, numShapesInMaster("2"));
    Assert.equals(1, numShapesInIntersect("3","1"));
  }

  @Test
  public void test5() throws IOException, ShapefileException, ProjectionException {
    addGSA(1);
    addGSA(2);
    removeGSA(2);
    addGSA(3);
    removeGSA(3);
    Assert.equals(1, numShapesInMaster("1"));
  }

  @Test
  public void test6() throws IOException, ShapefileException, ProjectionException {
    addGSA(1);
    addGSA(2);
    removeGSA(2);
    addGSA(3);
    removeGSA(1);
    addGSA(2);
    Assert.equals(1, numShapesInMaster("3"));
    Assert.equals(1, numShapesInIntersect("2","3"));
  }

  @Test
  public void test7() throws IOException, ShapefileException, ProjectionException {
    addGSA(1);
    addGSA(2);
    removeGSA(2);
    addGSA(3);
    removeGSA(1);
    addGSA(2);
    removeGSA(3);
    Assert.equals(1, numShapesInMaster("2"));
  }

  @Test
  public void test8() throws IOException, ShapefileException, ProjectionException {
    addGSA(1);
    addGSA(2);
    removeGSA(2);
    addGSA(3);
    removeGSA(1);
    addGSA(2);
    removeGSA(3);
    removeGSA(1);
  }

  @Test
  public void test9() throws IOException, ShapefileException, ProjectionException {
    addGSA(1);
    addGSA(2);
    removeGSA(2);
    addGSA(3);
    removeGSA(1);
    addGSA(2);
    removeGSA(3);
    removeGSA(1); // already removed, should simply not crash.
  }

  @Test
  public void test10() throws IOException, ShapefileException, ProjectionException {
    addGSA(1);
    addGSA(2);
    removeGSA(2);
    addGSA(3);
    removeGSA(1);
    addGSA(2);
    removeGSA(3);
    removeGSA(1); // already removed, should simply not crash.

    String intersections = addGSA(3);
    Assert.equals("{2}", intersections);
    intersections = addGSA(4);
    Assert.equals("{2,3}", intersections);
    Assert.equals(1, numShapesInIntersect("4","2"));
    Assert.equals(1, numShapesInIntersect("4","3"));
    intersections = addGSA(1);
    Assert.equals("{2,3,4}", intersections);
    Assert.equals(1, numShapesInIntersect("1","2"));
    Assert.equals(1, numShapesInIntersect("1","3"));
    Assert.equals(1, numShapesInIntersect("1","4"));
  }

  /**
   *
   * @param gsaId The String GSA ID to add.
   * @return
   * @throws IOException
   * @throws ShapefileException
   * @throws ProjectionException
   */
  public String addGSA(int gsaId) throws IOException, ShapefileException, ProjectionException {
    String gsaText = Integer.toString(gsaId);
    String intersections = manager.addGSAPolygonLayer(gsaText, outputFolder,
        masterGSA, polygon[gsaId], polygon[gsaId]);
    File f = new File(masterGSA);
    Assert.isTrue(f.exists());
    Assert.equals(1, numShapesInMaster(gsaText));
    return intersections;
  }

  /**
   * Removes the specified GSA polygon from the GSA master shapefile, and
   * verifies that the shape is no longer in the master.
   *
   * @param gsaId The GSA ID to remove, and then test to ensure that the polygon
   * no longer exists in the master GSA file.
   * @throws IOException
   * @throws ShapefileException
   */
  public void removeGSA(int gsaId) throws IOException, ShapefileException {
    String gsaText = Integer.toString(gsaId);
    manager.removeGSAPolygonLayer(gsaText, masterGSA);
    Assert.equals(0, numShapesInMaster(gsaText));
  }

  /**
   * Cycles through the features in the Master GSA shapefile to ensure that
   * there is one and only one shape with the specified id.
   *
   * @param gsaID The String GSA ID to find.
   * @return boolean, true if there is one and only one shape.
   * @throws IOException if there is an error parsing the shapefile.
   */
  private int numShapesInMaster(String gsaID) throws IOException {
    ShapefileReader reader = new ShapefileReader();
    reader.open(masterGSA);
    FeatureSet fs = reader.getFeatures();
    String gsaIDField = manager.getGsaAreasIdField();
    int total = 0;
    for (Feature f : fs.getFeatures()) {
      if (f.getAttributes().containsKey(gsaIDField)) {
        if (gsaID.equals(f.getAttributes().get(gsaIDField))) {
          total++;
        }
      }
    }
    return total;
  }

  /**
   * Cycles through the features in the Master GSA shapefile to ensure that
   * there is one and only one shape with the specified id.
   *
   * @param gsaID The String GSA ID to find.
   * @return boolean, true if there is one and only one shape.
   * @throws IOException if there is an error parsing the shapefile.
   */
  private int numShapesInIntersect(String currentID, String gsaID) throws IOException {
    String intersect = outputFolder + File.separator
        + "GSA_Intersect_" + currentID + "_" + gsaID + ".zip";
    File file = new File(intersect);
    if (!file.exists()) {
      return 0;
    }
    ShapefileReader reader = new ShapefileReader();
    reader.open(intersect);
    FeatureSet fs = reader.getFeatures();
    String gsaIDField = manager.getGsaAreasIdField();
    int total = 0;
    for (Feature f : fs.getFeatures()) {
      if (f.getAttributes().containsKey(gsaIDField)) {
        if (gsaID.equals(f.getAttributes().get(gsaIDField))) {
          total++;
        }
      }
    }
    return total;
  }

  @Test
  public void testDate(){
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.MONTH, -1);
    Date date = cal.getTime();
    boolean stop = true;
  }




}
