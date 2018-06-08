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

import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.ShapefileException;
import gov.ca.water.shapelite.io.ShapefileReader;
import gov.ca.water.shapelite.projection.ProjectionException;
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
public class GsaMasterUpdateTest {

  /**
   * GSA Master Update.
   */
  public GsaMasterUpdateTest() {
  }

  // TODO add test methods here.
  // The methods must be annotated with annotation @Test. For example:
  //
  @Test
  public void testUpdate() throws IOException, ProjectionException,
      ShapefileException {

    String master = "J:\\For Donghai\\GIS_Test\\overlap_test\\"
        + "GEI_Test_Data\\Test Polygons\\gsamaster.zip";
    String shape = "J:\\For Donghai\\GIS_Test\\overlap_test\\GEI_Test_Data\\"
        + "Test Polygons\\OHWD_Cosumnes_portion_test.shp.zip";
    update(shape, master);
  }

  @Test
  public void testMergeCovers3() throws FileNotFoundException, IOException,
      ProjectionException, ShapefileException {
    System.out.println("covers");
    String master = "J:\\For Donghai\\GIS_Test\\overlap_test\\GEI_Test_Data\\Test Polygons\\GSA_Boundary_Master_GIS(3).zip";
    String shape = "J:\\For Donghai\\GIS_Test\\overlap_test\\GEI_Test_Data\\Test Polygons\\20_D4.zip";
    update(shape, master);
  }

  public void update(String shape, String master) throws IOException,
      ProjectionException, ShapefileException {
    GSAPolygonManager manager = new GSAPolygonManager();
    manager.setGsaAreasShapefile(master);
    ShapefileReader reader = new ShapefileReader();
    reader.open(shape);
    FeatureSet fs = reader.getFeatures();

    reader.open(master);
    FeatureSet masterFeatures = reader.getFeatures();

    manager.update("44", null, fs);

  }
}
