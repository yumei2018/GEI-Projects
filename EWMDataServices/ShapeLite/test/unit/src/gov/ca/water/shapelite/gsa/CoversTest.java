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
import gov.ca.water.shapelite.io.ShapefileReader;
import gov.ca.water.shapelite.projection.ProjectionException;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class CoversTest {
  @Test
  public void covers() throws IOException, ProjectionException{
    String bigShape = "J:\\For Donghai\\GIS_Test\\overlap_test\\GEI_Test_Data\\Test Polygons\\OHWD_South_American_shapefile_adjusted_dwr.zip";
    String littleShape = "J:\\For Donghai\\GIS_Test\\overlap_test\\GEI_Test_Data\\Test Polygons\\overlap12.zip";
    boolean expResult = true;
    boolean result = covers(bigShape, littleShape, 100);
    assertEquals(result, expResult);
  }

  public boolean covers(String bigShape, String littleShape, double buffer) throws IOException, ProjectionException{

    ShapefileReader reader = new ShapefileReader();
    reader.open(bigShape);
    FeatureSet big = reader.getFeatures();
    reader.open(littleShape);
    FeatureSet little = reader.getFeatures();
    return big.covers(little, 0);

  }
}
