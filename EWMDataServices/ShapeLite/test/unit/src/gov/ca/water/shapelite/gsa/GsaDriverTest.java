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

import gov.ca.water.shapelite.ShapefileException;
import gov.ca.water.shapelite.projection.ProjectionException;
import java.io.IOException;
import org.junit.Test;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class GsaDriverTest {

  @Test
  public void test() throws IOException, ProjectionException, ShapefileException {

    GsaDriver.addGSAPolyonLayer("5", "D:\\Data\\GSA3",
        "D:\\Data\\GSA3\\GSA_Master.zip", "D:\\Data\\GSA3\\polygon5.zip",
        "D:\\Data\\GSA3\\polygon5.zip");

    GsaDriver.addGSAPolyonLayer("4", "D:\\Data\\GSA3",
        "D:\\Data\\GSA3\\GSA_Master.zip", "D:\\Data\\GSA3\\polygon4.zip",
        "D:\\Data\\GSA3\\polygon4.zip");

    String result = GsaDriver.addGSAPolyonLayer("2", "D:\\Data\\GSA3",
        "D:\\Data\\GSA3\\GSA_Master.zip", "D:\\Data\\GSA3\\polygon2.zip",
        "D:\\Data\\GSA3\\polygon2.zip");

    System.out.println(result);

    result = GsaDriver.addGSAPolyonLayer("3", "D:\\Data\\GSA3",
        "D:\\Data\\GSA3\\GSA_Master.zip", "D:\\Data\\GSA3\\polygon3.zip",
        "D:\\Data\\GSA3\\polygon3.zip");

    System.out.println(result);

    result = GsaDriver.addGSAPolyonLayer("1", "D:\\Data\\GSA",
        "D:\\Data\\GSA\\GSA_Master.zip", "D:\\Data\\GSA\\VCMWD_GSA_Boundary.zip",
        "D:\\Data\\GSA\\VCMWD_GSA_Boundary.zip");

    System.out.println(result);

    GsaDriver.removeGSAPolygonLayer("5", "D:\\Data\\GSA3\\GSA_Master.zip");
    GsaDriver.removeGSAPolygonLayer("4", "D:\\Data\\GSA3\\GSA_Master.zip");
    GsaDriver.removeGSAPolygonLayer("3", "D:\\Data\\GSA3\\GSA_Master.zip");
    GsaDriver.removeGSAPolygonLayer("2", "D:\\Data\\GSA3\\GSA_Master.zip");
    GsaDriver.removeGSAPolygonLayer("1", "D:\\Data\\GSA3\\GSA_Master.zip");

  }

}
