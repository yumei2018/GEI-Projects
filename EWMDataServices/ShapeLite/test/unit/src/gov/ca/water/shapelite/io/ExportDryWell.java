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
import gov.ca.water.shapelite.ShapefileException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ExportDryWell {

  @Rule
  public TemporaryFolder folder = new TemporaryFolder();

  public ExportDryWell() {

  }

  @Before
  public void setUp() {
  }

  // TODO add test methods here.
  // The methods must be annotated with annotation @Test. For example:
  //
  @Test
  public void hello() throws ClassNotFoundException, SQLException,
      IOException, ShapefileException {
    Class.forName("oracle.jdbc.driver.OracleDriver");
    Connection connection = DriverManager.getConnection(
        "jdbc:oracle:thin:@SAC1V-QAQCDB.geiconsultants.com:1521:orcl",
        "drywell", "dw#95670");
    Statement stmt = connection.createStatement();
    ResultSet set = stmt.executeQuery("SELECT * FROM DRYWELL_BASE");
    FeatureSet result = new FeatureSet();
    result.addFields(set.getMetaData());
    result.addPoints(set, "LONGITUDE", "LATITUDE");
    ShapefileWriter writer = new ShapefileWriter();
    Path path = Paths.get(System.getProperty("java.io.tmpdir") + "\\ExportDryWell");
    File f = path.toFile();
    if (!f.exists()) {
      f.mkdirs();
    }
    Path tempFile = Files.createTempFile(path, "Points", ".shp");
    writer.write(result, tempFile.toString());
  }
}
