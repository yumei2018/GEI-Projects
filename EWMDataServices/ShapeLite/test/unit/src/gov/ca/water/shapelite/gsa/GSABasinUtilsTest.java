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

import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.FileHelper;
import gov.ca.water.shapelite.ShapefileException;
import gov.ca.water.shapelite.io.ShapefileWriter;
import gov.ca.water.shapelite.projection.ProjectionException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class GSABasinUtilsTest {

  public GSABasinUtilsTest() {
  }

  /**
   * Test of splitByBasin method, of class GSABasinUtils.
   *
   * @throws java.io.IOException if there was an error reading or writing files
   * @throws gov.ca.water.shapelite.ShapefileException if a shapefile format was
   * invalid
   * @throws gov.ca.water.shapelite.projection.ProjectionException if the basins
   * and test polygons could not be reprojected for intersection.
   */
  @Test
  public void testPolygon1() throws IOException, ShapefileException, ProjectionException {
    System.out.println("testPolygon1");
    String gsaShapefile = "J:\\For Donghai\\GIS_Test\\overlap_test\\"
        + "GEI_Test_Data\\Polygon 1.zip";
    testSplit(gsaShapefile);
  }

  /**
   * Test of splitByBasin method, of class GSABasinUtils.
   *
   * @throws java.io.IOException if there was an error reading or writing files
   * @throws gov.ca.water.shapelite.ShapefileException if a shapefile format was
   * invalid
   * @throws gov.ca.water.shapelite.projection.ProjectionException if the basins
   * and test polygons could not be reprojected for intersection.
   */
  @Test
  public void testPolygon2() throws IOException, ShapefileException, ProjectionException {
    System.out.println("testPolygon2");
    String gsaShapefile = "J:\\For Donghai\\GIS_Test\\overlap_test\\"
        + "GEI_Test_Data\\Polygon 2.zip";
    testSplit(gsaShapefile);
  }

  /**
   * Test of splitByBasin method, of class GSABasinUtils.
   *
   * @throws java.io.IOException if there was an error reading or writing files
   * @throws gov.ca.water.shapelite.ShapefileException if a shapefile format was
   * invalid
   * @throws gov.ca.water.shapelite.projection.ProjectionException if the basins
   * and test polygons could not be reprojected for intersection.
   */
  @Test
  public void testPolygon3() throws IOException, ShapefileException, ProjectionException {
    System.out.println("testPolygon3");
    String gsaShapefile = "J:\\For Donghai\\GIS_Test\\overlap_test\\"
        + "GEI_Test_Data\\Polygon 3.zip";
    testSplit(gsaShapefile);
  }

  /**
   * Test of splitByBasin method, of class GSABasinUtils.
   *
   * @throws java.io.IOException if there was an error reading or writing files
   * @throws gov.ca.water.shapelite.ShapefileException if a shapefile format was
   * invalid
   * @throws gov.ca.water.shapelite.projection.ProjectionException if the basins
   * and test polygons could not be reprojected for intersection.
   */
  @Test
  public void testPolygon4() throws IOException, ShapefileException, ProjectionException {
    System.out.println("testPolygon4");
    String gsaShapefile = "J:\\For Donghai\\GIS_Test\\overlap_test\\"
        + "GEI_Test_Data\\Polygon 4.zip";
    testSplit(gsaShapefile);
  }

  /**
   * Test of splitByBasin method, of class GSABasinUtils.
   *
   * @throws java.io.IOException if there was an error reading or writing files
   * @throws gov.ca.water.shapelite.ShapefileException if a shapefile format was
   * invalid
   * @throws gov.ca.water.shapelite.projection.ProjectionException if the basins
   * and test polygons could not be reprojected for intersection.
   */
  @Test
  public void testPolygon5() throws IOException, ShapefileException, ProjectionException {
    System.out.println("testPolygon5");
    String gsaShapefile = "J:\\For Donghai\\GIS_Test\\overlap_test\\"
        + "GEI_Test_Data\\Polygon 5.zip";
    testSplit(gsaShapefile);
  }

  @Test
  public void testPolygon_C() throws IOException, ProjectionException, ShapefileException {
    System.out.println("testPolygon_C");
    String gsaShapefile = "J:\\For Donghai\\GIS_Test\\overlap_test\\"
        + "GEI_Test_Data\\Test Polygons\\OHWD_Cosumnes_portion_test.shp.zip";
    testSplit(gsaShapefile);
  }

  @Test
  public void testPolygon6() throws IOException, ProjectionException, ShapefileException {
    System.out.println("testPolygon 6");
    String gsaShapefile = "J:\\For Donghai\\GIS_Test\\overlap_test\\"
        + "GEI_Test_Data\\Test Polygons\\Polygon 6.zip";
    testSplit(gsaShapefile);
  }

  @Test
  public void testPolygon7() throws IOException, ProjectionException, ShapefileException {
    System.out.println("testPolygon 7");
    String gsaShapefile = "J:\\For Donghai\\GIS_Test\\overlap_test\\"
        + "GEI_Test_Data\\Test Polygons\\Polygon 7.zip";
    testSplit(gsaShapefile);
  }

  @Test
  public void testPolygon8() throws IOException, ProjectionException, ShapefileException {
    System.out.println("testPolygon 7");
    String gsaShapefile = "J:\\For Donghai\\GIS_Test\\overlap_test\\"
        + "GEI_Test_Data\\Test Polygons\\Polygon 7.zip";
    testSplit(gsaShapefile);
  }

  @Test
  public void testSRCD_Area4() throws IOException, ProjectionException, ShapefileException {
    System.out.println("SRCD_Area4_test");
    String gsaShapefile = "J:\\For Donghai\\GIS_Test\\overlap_test\\"
        + "GEI_Test_Data\\Test Polygons\\SRCD_Area4_test.shp.zip";
    testSplit(gsaShapefile, 0);
  }

  /**
   * Performs the test split operation on the specified data files.
   *
   * @param gsaShapefile
   * @throws IOException
   * @throws ProjectionException
   * @throws ShapefileException
   */
  private void testSplit(String gsaShapefile) throws IOException,
      ProjectionException, ShapefileException {
    testSplit(gsaShapefile, 0);
  }

  /**
   * Performs the test split operation on the specified data files.
   *
   * @param gsaShapefile
   * @throws IOException
   * @throws ProjectionException
   * @throws ShapefileException
   */
  private void testSplit(String gsaShapefile, double buffer) throws IOException,
      ProjectionException, ShapefileException {

    String name = FileHelper.getBaseName(gsaShapefile);
    String basinShapefile = "J:\\For Donghai\\GIS_Test\\overlap_test\\"
        + "GEI_Test_Data\\GWBasinsWGS84.zip";
    String basinIdFieldName = "Basin_ID";

    List<FeatureSet> list = GSABasinUtils.splitByBasin(basinShapefile,
        gsaShapefile, basinIdFieldName, buffer);
    ShapefileWriter writer = new ShapefileWriter();
    String folder = "J:\\For Donghai\\GIS_Test\\overlap_test\\GEI_Test_Data"
        + "\\SplitByBasin\\" + name;
    File dir = new File(folder);
    if (!dir.exists()) {
      dir.mkdirs();
    }
    for (FeatureSet basin : list) {
      try (FileOutputStream fileStream = new FileOutputStream(
          folder + "\\" + basin.getName() + "_GSA.zip")) {
        writer.write(basin, fileStream);
      }
    }
  }

}
