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
import gov.ca.water.shapelite.ShapefileException;
import gov.ca.water.shapelite.io.ShapefileReader;
import gov.ca.water.shapelite.io.ShapefileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class FeaturesetIntersectTest {

  public FeaturesetIntersectTest() {
  }

  // TODO add test methods here.
  // The methods must be annotated with annotation @Test. For example:
  //
  @Test
  public void intersect() throws IOException {
    FeatureSet master = get("J:\\For Donghai\\GIS_Test\\overlap_test\\GEI_Test_Data\\Test Polygons\\40_South_San_Joaquin_Irrigation_District.zip");
    FeatureSet gs1 = get("J:\\For Donghai\\GIS_Test\\overlap_test\\GEI_Test_Data\\Test Polygons\\Gsa2_5-022.01.zip");
    FeatureSet gs2 = get("J:\\For Donghai\\GIS_Test\\overlap_test\\GEI_Test_Data\\Test Polygons\\Gsa2_5-022.15.zip");

  }

  @Test
  public void split() throws IOException, ShapefileException {
    FeatureSet source = get("J:\\For Donghai\\GIS_Test\\overlap_test\\GEI_Test_Data\\Test Polygons\\118_Linden_County_Water_District.zip");
    ShapefileWriter writer = new ShapefileWriter();
    for (Feature feature : source.getFeatures()) {
      FeatureSet fs = source.copy();
      fs.getFeatures().clear();
      fs.getFeatures().add(feature);
      String file = "J:\\For Donghai\\GIS_Test\\overlap_test\\GEI_Test_Data\\SplitZip\\";
      file += feature.getAttributes().get("GSA_ID") + "_" + feature.getAttributes().get("Local_Agen") + ".zip";
      writer.write(fs, file);
    }
  }

  private FeatureSet get(String shapefileName) throws FileNotFoundException, IOException {
    ShapefileReader reader = new ShapefileReader();
    reader.open(shapefileName);
    return reader.getFeatures();
  }

}
