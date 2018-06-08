/*
 * The MIT License
 *
 * Copyright 2017 Harold A. Dunsford Jr. Ph.D..
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

import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.ShapefileException;
import gov.ca.water.shapelite.io.ShapefileReader;
import gov.ca.water.shapelite.projection.ProjectionException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PolygonManagerTest {

  public PolygonManagerTest() {
  }

  public void testContainNormal() throws FileNotFoundException, IOException, ProjectionException, ShapefileException {
    System.out.println("covers");
    String gsaFile = "J:\\For Donghai\\GIS_Test\\overlap_test\\GEI_Test_Data\\Test Polygons\\Polygon 1.shp";
    String areaFile = "J:\\For Donghai\\GIS_Test\\overlap_test\\GEI_Test_Data\\Test Polygons\\Polygon1Container.shp";
    GSAPolygonManager manager = getManager();
    ShapefileReader reader = new ShapefileReader();
    reader.open(gsaFile);
    FeatureSet gsa = reader.getFeatures();
    reader.open(areaFile);
    FeatureSet area = reader.getFeatures();
    UpdateResults results = manager.add("1", area, gsa);
    boolean expResult = true;
    assertEquals(results.getGsaAreasOutsideServiceAreas().isEmpty(), expResult);

  }

  public void testNotContainNormal() throws FileNotFoundException, IOException, ProjectionException, ShapefileException {
    System.out.println("covers");
    String gsaFile = "J:\\For Donghai\\GIS_Test\\overlap_test\\GEI_Test_Data\\Test Polygons\\Polygon 1.shp";
    String areaFile = "J:\\For Donghai\\GIS_Test\\overlap_test\\GEI_Test_Data\\Test Polygons\\Polygon1NotContainer.shp";
    GSAPolygonManager manager = getManager();
    ShapefileReader reader = new ShapefileReader();
    reader.open(gsaFile);
    FeatureSet gsa = reader.getFeatures();
    reader.open(areaFile);
    FeatureSet area = reader.getFeatures();
    UpdateResults results = manager.add("1", area, gsa);
    boolean expResult = false;
    assertEquals(results.getGsaAreasOutsideServiceAreas().isEmpty(), expResult);

  }

  public void testNotContainBuffer() throws FileNotFoundException, IOException, ProjectionException, ShapefileException {
    System.out.println("covers");
    String gsaFile = "J:\\For Donghai\\GIS_Test\\overlap_test\\GEI_Test_Data\\Test Polygons\\Polygon 1.shp";
    String areaFile = "J:\\For Donghai\\GIS_Test\\overlap_test\\GEI_Test_Data\\Test Polygons\\Polygon1NotContainer.shp";

    ShapefileReader reader = new ShapefileReader();
    reader.open(gsaFile);
    FeatureSet gsa = reader.getFeatures();
    reader.open(areaFile);
    FeatureSet area = reader.getFeatures();
    GSAPolygonManager manager = getManager();
    manager.setBufferFeet(-400);
    UpdateResults results = manager.add("1", area, gsa);
    boolean expResult = true;
    assertEquals(results.getGsaAreasOutsideServiceAreas().isEmpty(), expResult);

  }

  public void test() throws IOException, ShapefileException, ProjectionException {
    String gsaFile = "J:\\For Donghai\\GIS_Test\\overlap_test\\GEI_Test_Data\\Test Polygons\\County_of_Imperial.zip";
    ShapefileReader reader = new ShapefileReader();
    reader.open(gsaFile);
    FeatureSet gsa = reader.getFeatures();
    GSAPolygonManager manager = getManager();
    manager.setBasinShapefile("J:\\For Donghai\\GIS_Test\\overlap_test\\GEI_Test_Data\\GWBasins84.shp");
    manager.setCountyShapefile("J:\\For Donghai\\GIS_Test\\overlap_test\\GEI_Test_Data\\Counties_DWR\\Counties.shp");
    manager.setCountyFieldName("NAME_PCASE");
    manager.setBufferFeet(-100);
    UpdateResults results = manager.add("1", null, gsa);
    boolean stop = true;
  }

  private GSAPolygonManager getManager() {
    GSAPolygonManager manager = new GSAPolygonManager();
    manager.setServiceAreas(new FeatureSet(ShapeType.Polygon));
    manager.setGsaAreas(new FeatureSet(ShapeType.Polygon));
    return manager;
  }

  public static FeatureSet toFeatureSet(String file) {
    return toFeatureSet(new File(file));
  }

  public static FeatureSet toFeatureSet(File file) {
    FeatureSet result = null;
    try {
      result = toFeatureSet(new FileInputStream(file));
    } catch (Exception ex) {
      throw new IllegalStateException(
          String.format("%s.toFeatureSet(File) %s:\n%s", PolygonManagerTest.class.getName(), ex.getClass().getName(), ex.getMessage()
          )
      );
    }
    return result;
  }

  public static FeatureSet toFeatureSet(byte[] bytes) {
    FeatureSet result = null;

    try (ByteArrayInputStream is = new ByteArrayInputStream(bytes)) {
      result = toFeatureSet(is);
    } catch (Exception ex) {
      throw new IllegalStateException(
          String.format("%s.toFeatureSet(byte[]) %s:\n%s", PolygonManagerTest.class.getName(), ex.getClass().getName(), ex.getMessage()
          )
      );
    }

    return result;
  }

  public static FeatureSet toFeatureSet(InputStream is) {
    FeatureSet result = null;
    try {
      ShapefileReader sfReader = new ShapefileReader();
      sfReader.open(is);
      result = sfReader.getFeatures();
    } catch (Exception ex) {
      throw new IllegalStateException(
          String.format("%s.toFeatureSet(InputStream) %s:\n%s", PolygonManagerTest.class.getName(), ex.getClass().getName(), ex.getMessage()
          )
      );
    }
    return result;
  }

  @Test
  public void basinCountyIntersectTest() throws Exception {
    String APP_ROOT = "C:/tmp";
    APP_ROOT = "J:\\For Donghai\\GIS_Test\\overlap_test\\GEI_Test_Data\\Test Polygons";
    // String BASIN_FILE = APP_ROOT + "/GWBasinsWGS84.zip";
    String BASIN_FILE = APP_ROOT + "/GWBasinsTealeM.zip";
    String COUNTY_FILE = APP_ROOT + "/CountyTeale.zip";
//    COUNTY_FILE = "C:\\Users\\clay\\Downloads\\Counties.zip";
    String COUNTY_FIELD = "NAME";// column in the dbf file
    COUNTY_FIELD = "NAME_PCASE";
    String BASIN_FIELD = "Basin_Subb";// column in the dbf file

    FeatureSet gsa = null;//toFeatureSet(APP_ROOT + "/30_Stockton_East_WD.zip");
    gsa = toFeatureSet(APP_ROOT + "/County_of_Imperial.zip");
    GSAPolygonManager mngr = new GSAPolygonManager();
    mngr.setBasinShapefile(BASIN_FILE);
    mngr.setCountyShapefile(COUNTY_FILE);
    mngr.setGsaAreasShapefile(null);// setting null to avoid using default shapefile
    mngr.setAgencyServiceAreasShapefile(null);// setting null to avoid using default shapefile
    mngr.setCountyFieldName(COUNTY_FIELD);// maps to the county name
    mngr.setBasinFieldName(BASIN_FIELD);// maps to basin subbasin_number column

    mngr.setBufferFeet(-500);
    System.out.println("With buffer -500ft");
    FeatureSet countyIntersects = mngr.findBasinIntersections(gsa);
    for (Feature f : countyIntersects.getFeatures()) {
      System.out.println(f.getAttributes().get("Basin_Subb")
          + " => " + f.getAttributes().get("Counties"));
    }
  }

  @Test
  public void basinCountyIntersectTest2() throws Exception {
    String APP_ROOT = "C:/tmp";
    APP_ROOT = "J:\\For Donghai\\GIS_Test\\overlap_test\\GEI_Test_Data\\Test Polygons";
    // String BASIN_FILE = APP_ROOT + "/GWBasinsWGS84.zip";
    String BASIN_FILE = APP_ROOT + "/GWBasinsTealeM.zip";
    String COUNTY_FILE = APP_ROOT + "/CountyTeale.zip";
//    COUNTY_FILE = "C:\\Users\\clay\\Downloads\\Counties.zip";
    String COUNTY_FIELD = "NAME";// column in the dbf file
    COUNTY_FIELD = "NAME_PCASE";
    String BASIN_FIELD = "Basin_Subb";// column in the dbf file

    FeatureSet gsa = null;//toFeatureSet(APP_ROOT + "/30_Stockton_East_WD.zip");
    gsa = toFeatureSet(APP_ROOT + "/GreaterKaweah_DWRAdjusted.zip");

    //gsa = toFeatureSet(APP_ROOT + "/County_of_Imperial.zip");
    GSAPolygonManager mngr = new GSAPolygonManager();
    mngr.setBasinShapefile(BASIN_FILE);
    mngr.setCountyShapefile(COUNTY_FILE);
    mngr.setGsaAreasShapefile(null);// setting null to avoid using default shapefile
    mngr.setAgencyServiceAreasShapefile(null);// setting null to avoid using default shapefile
    mngr.setCountyFieldName(COUNTY_FIELD);// maps to the county name
    mngr.setBasinFieldName(BASIN_FIELD);// maps to basin subbasin_number column

    mngr.setBufferFeet(-500);
    System.out.println("With buffer -500ft");
    FeatureSet countyIntersects = mngr.findBasinIntersections(gsa);
    for (Feature f : countyIntersects.getFeatures()) {
      System.out.println(f.getAttributes().get("Basin_Subb")
          + " => " + f.getAttributes().get("Counties"));
    }
  }

  @Test
  public void basinCountyIntersectTest3() throws Exception {
    String APP_ROOT = "C:/tmp";
    APP_ROOT = "J:\\For Donghai\\GIS_Test\\overlap_test\\GEI_Test_Data\\Test Polygons";
    // String BASIN_FILE = APP_ROOT + "/GWBasinsWGS84.zip";
    String BASIN_FILE = APP_ROOT + "/GWBasinsTealeM.zip";
    String COUNTY_FILE = APP_ROOT + "/CountyTeale.zip";
//    COUNTY_FILE = "C:\\Users\\clay\\Downloads\\Counties.zip";
    String COUNTY_FIELD = "NAME";// column in the dbf file
    COUNTY_FIELD = "NAME_PCASE";
    String BASIN_FIELD = "Basin_Subb";// column in the dbf file

    FeatureSet gsa = null;//toFeatureSet(APP_ROOT + "/30_Stockton_East_WD.zip");
    gsa = toFeatureSet(APP_ROOT + "/ExchangeContractors.zip");

    //gsa = toFeatureSet(APP_ROOT + "/County_of_Imperial.zip");
    GSAPolygonManager mngr = new GSAPolygonManager();
    mngr.setBasinShapefile(BASIN_FILE);
    mngr.setCountyShapefile(COUNTY_FILE);
    mngr.setGsaAreasShapefile(null);// setting null to avoid using default shapefile
    mngr.setAgencyServiceAreasShapefile(null);// setting null to avoid using default shapefile
    mngr.setCountyFieldName(COUNTY_FIELD);// maps to the county name
    mngr.setBasinFieldName(BASIN_FIELD);// maps to basin subbasin_number column

    mngr.setBufferFeet(-500);
    System.out.println("With buffer -500ft");
    FeatureSet countyIntersects = mngr.findBasinIntersections(gsa);
    for (Feature f : countyIntersects.getFeatures()) {
      System.out.println(f.getAttributes().get("Basin_Subb")
          + " => " + f.getAttributes().get("Counties"));
    }
  }

  @Test
  public void basinTest4() {
    try {
      FileInputStream is = new FileInputStream("J:/For Donghai/GIS_Test/overlap_test/GEI_Test_Data/Test Polygons/GSA_BaseMaster.zip");
      ShapefileReader sfReader = new ShapefileReader();
      sfReader.open(is);
      FeatureSet masterSet = sfReader.getFeatures();

      is = new FileInputStream("J:/For Donghai/GIS_Test/overlap_test/GEI_Test_Data/Test Polygons/ExchangeContractors.zip");
      sfReader.open(is);
      FeatureSet d4 = sfReader.getFeatures();
      GSAMasterUtils.add("10", d4, masterSet);
      //ShapeLiteContext.syncProjection(masterSet, d4);
      FeatureSet intersects = d4.intersection(masterSet, 0, -500);
    } catch (Exception ex) {
      ex.printStackTrace();
    }

  }

  @Test
  public void shapefile188Test() throws ProjectionException, IOException {

    ShapefileReader reader = new ShapefileReader();
    reader.open("J:\\For Donghai\\GIS_Test\\overlap_test\\GEI_Test_Data\\SubmittedGsaMasterSet.zip");
    FeatureSet basinfs = reader.getFeatures();
    reader.open("J:\\For Donghai\\GIS_Test\\overlap_test\\GEI_Test_Data\\Pioneer_KCWA_GSA-17-0207.zip");
    FeatureSet fs = reader.getFeatures();
    FeatureSet union = null;
    if (fs.getFeatures().size() > 1) {
      union = fs.union();
    } else {
      union = fs;
    }
    union.reproject(basinfs.getProjection());
    //union.roundToFeet(.01);
    FeatureSet result = union.intersection(basinfs, 0, -500);

  }

  @Test
  public void shapefileJVIDTest() throws ProjectionException, IOException {

    ShapefileReader reader = new ShapefileReader();
    reader.open("J:\\For Donghai\\GIS_Test\\overlap_test\\GEI_Test_Data\\Test Polygons\\GWBasinsTealeM.zip");
    FeatureSet basinfs = reader.getFeatures();
    reader.open("J:\\For Donghai\\GIS_Test\\overlap_test\\GEI_Test_Data\\Test Polygons\\JVID_Boundary.zip");
    FeatureSet fs = reader.getFeatures();
    FeatureSet union = null;
    if (fs.getFeatures().size() > 1) {
      union = fs.union();
    } else {
      union = fs;
    }
    FeatureSet result = union.intersection(
        basinfs, 0, -500
    );

  }

}
