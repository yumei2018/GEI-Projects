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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class OpenProjectionTest {

    private String test = "PROJCS[\"NAD_1983_UTM_Zone_10N\",GEOGCS[\"GCS_North_American_1983\",DATUM[\"D_North_American_1983\",SPHEROID[\"GRS_1980\",6378137.0,298.257222101]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Transverse_Mercator\"],PARAMETER[\"False_Easting\",1640416.666666667],PARAMETER[\"False_Northing\",0.0],PARAMETER[\"Central_Meridian\",-123.0],PARAMETER[\"Scale_Factor\",0.9996],PARAMETER[\"Latitude_Of_Origin\",0.0],UNIT[\"Foot_US\",0.3048006096012192]],VERTCS[\"NAVD_1988\",VDATUM[\"North_American_Vertical_Datum_1988\"],PARAMETER[\"Vertical_Shift\",0.0],PARAMETER[\"Direction\",1.0],UNIT[\"Foot_US\",0.3048006096012192]]";
    private String testOld = "PROJCS[\"NAD_1983_UTM_Zone_10N_USFeet\",GEOGCS[\"GCS_North_American_1983\",DATUM[\"D_North_American_1983\",SPHEROID[\"GRS_1980\",6378137.0,298.257222101]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Transverse_Mercator\"],PARAMETER[\"False_Easting\",1640416.666666667],PARAMETER[\"False_Northing\",0.0],PARAMETER[\"Central_Meridian\",-123.0],PARAMETER[\"Scale_Factor\",0.9996],PARAMETER[\"Latitude_Of_Origin\",0.0],UNIT[\"Foot_US\",0.3048006096012192]],VERTCS[\"NAVD_1988_USFeet\",VDATUM[\"North_American_Vertical_Datum_1988\"],PARAMETER[\"Vertical_Shift\",0.0],PARAMETER[\"Direction\",1.0],UNIT[\"Foot_US\",0.3048006096012192]]";
    public OpenProjectionTest() {


    }

    @Test
    public void testReadEsri(){
        ProjectionInfo info = ProjectionInfo.fromEsriString(testOld);
        boolean stop = true;
    }

    /**
     *
     * @throws IOException
     */
    @Test
    public void testSaveLoad() throws IOException{
      ProjectionInfo original = Projections.getProjected().getWorld().getWebMercator();
      Path path = Files.createTempDirectory("ProjectionTests");
      String file = path.toString() + "\\SaveLoad.prj";
      original.saveAs(file);
      ProjectionInfo loaded = new ProjectionInfo();
      loaded.openESRI(file);
      boolean expResult = true;
      boolean equals = loaded.equals(original);
      assertEquals(equals, expResult);
    }

}
