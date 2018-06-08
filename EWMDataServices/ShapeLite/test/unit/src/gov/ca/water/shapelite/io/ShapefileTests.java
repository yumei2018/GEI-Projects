/*
 * The MIT License
 *
 * Copyright 2015 hdunsford.
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

import com.vividsolutions.jts.util.Assert;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.ShapefileException;
import gov.ca.water.shapelite.coordinate.CoordM;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.coordinate.CoordZ;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ShapefileTests {

  public ShapefileTests() {
  }

  @BeforeClass
  public static void setUpClass() {
  }

  @AfterClass
  public static void tearDownClass() {
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  /**
   * Point XY Test.
   *
   * @throws IOException
   * @throws ShapefileException
   */
  @Test
  public void writeReadXYPoints() throws IOException, ShapefileException {
    Shape test = new Shape(new CoordXY(1, 2));
    FeatureSet fs = new FeatureSet();
    fs.getFeatures().add(new Feature(test));
    Path path = Files.createTempDirectory("ShapefileTests");
    ShapefileWriter writer = new ShapefileWriter();
    String file = path.toString() + "\\Point.shp";
    writer.write(fs, file);

    ShapefileReader reader = new ShapefileReader();
    reader.open(file);
    FeatureSet out = reader.getFeatures();
    Assert.equals(test.first(), out.getFeatures().get(0).getShape().first());
  }

  /**
   * Polyline XY Test.
   *
   * @throws IOException
   * @throws ShapefileException
   */
  @Test
  public void writeReadXYLines() throws IOException, ShapefileException {
    Part line = new Part();
    line.getCoordinates().add(new CoordXY(1, 2));
    line.getCoordinates().add(new CoordXY(1, 3));
    Shape test = new Shape(ShapeType.PolyLine);
    test.getParts().add(line);
    FeatureSet fs = new FeatureSet();
    fs.getFeatures().add(new Feature(test));
    Path path = Files.createTempDirectory("ShapefileTests");
    ShapefileWriter writer = new ShapefileWriter();
    String file = path.toString() + "\\Line.shp";
    writer.write(fs, file);

    ShapefileReader reader = new ShapefileReader();
    reader.open(file);
    FeatureSet out = reader.getFeatures();
    Assert.equals(test.first(), out.getFeatures().get(0).getShape().first());
  }

  /**
   * Poylgon XY Test.
   *
   * @throws IOException
   * @throws ShapefileException
   */
  @Test
  public void writeReadXYPolygons() throws IOException, ShapefileException {
    Part line = new Part();
    line.getCoordinates().add(new CoordXY(1, 1));
    line.getCoordinates().add(new CoordXY(5, 5));
    line.getCoordinates().add(new CoordXY(5, 1));
    Shape test = new Shape(ShapeType.Polygon);
    test.getParts().add(line);
    FeatureSet fs = new FeatureSet();
    fs.getFeatures().add(new Feature(test));
    Path path = Files.createTempDirectory("ShapefileTests");
    ShapefileWriter writer = new ShapefileWriter();
    String file = path.toString() + "\\Poly.shp";
    writer.write(fs, file);

    ShapefileReader reader = new ShapefileReader();
    reader.open(file);
    FeatureSet out = reader.getFeatures();
    Assert.equals(test.first(), out.getFeatures().get(0).getShape().first());
  }

  /**
   * Point XY Test.
   *
   * @throws IOException
   * @throws ShapefileException
   */
  @Test
  public void writeReadMPoints() throws IOException, ShapefileException {
    Shape test = new Shape(new CoordM(1, 2, 3));
    FeatureSet fs = new FeatureSet();
    fs.getFeatures().add(new Feature(test));
    Path path = Files.createTempDirectory("ShapefileTests");
    ShapefileWriter writer = new ShapefileWriter();
    String file = path.toString() + "\\PointM.shp";
    writer.write(fs, file);

    ShapefileReader reader = new ShapefileReader();
    reader.open(file);
    FeatureSet out = reader.getFeatures();
    Assert.equals(test.first(), out.getFeatures().get(0).getShape().first());
  }

  /**
   * Polyline XY Test.
   *
   * @throws IOException
   * @throws ShapefileException
   */
  @Test
  public void writeReadMLines() throws IOException, ShapefileException {
    Part line = new Part();
    line.getCoordinates().add(new CoordM(1, 2, 3));
    line.getCoordinates().add(new CoordM(1, 3, 4));
    Shape test = new Shape(ShapeType.PolyLineM);
    test.getParts().add(line);
    FeatureSet fs = new FeatureSet();
    fs.getFeatures().add(new Feature(test));
    Path path = Files.createTempDirectory("ShapefileTests");
    ShapefileWriter writer = new ShapefileWriter();
    String file = path.toString() + "\\LineM.shp";
    writer.write(fs, file);

    ShapefileReader reader = new ShapefileReader();
    reader.open(file);
    FeatureSet out = reader.getFeatures();
    Assert.equals(test.first(), out.getFeatures().get(0).getShape().first());
  }

  /**
   * Poylgon XY Test.
   *
   * @throws IOException
   * @throws ShapefileException
   */
  @Test
  public void writeReadMPolygons() throws IOException, ShapefileException {
    Part line = new Part();
    line.getCoordinates().add(new CoordM(1, 1, 3));
    line.getCoordinates().add(new CoordM(5, 5, 3));
    line.getCoordinates().add(new CoordM(5, 1, 3));
    Shape test = new Shape(ShapeType.PolygonM);
    test.getParts().add(line);
    FeatureSet fs = new FeatureSet();
    fs.getFeatures().add(new Feature(test));
    Path path = Files.createTempDirectory("ShapefileTests");
    ShapefileWriter writer = new ShapefileWriter();
    String file = path.toString() + "\\PolyM.shp";
    writer.write(fs, file);

    ShapefileReader reader = new ShapefileReader();
    reader.open(file);
    FeatureSet out = reader.getFeatures();
    Assert.equals(test.first(), out.getFeatures().get(0).getShape().first());
  }

  /**
   * Point XY Test.
   *
   * @throws IOException
   * @throws ShapefileException
   */
  @Test
  public void writeReadZPoints() throws IOException, ShapefileException {
    Shape test = new Shape(new CoordZ(1, 2, 3, 4));
    FeatureSet fs = new FeatureSet();
    fs.getFeatures().add(new Feature(test));
    Path path = Files.createTempDirectory("ShapefileTests");
    ShapefileWriter writer = new ShapefileWriter();
    String file = path.toString() + "\\Point.shp";
    writer.write(fs, file);

    ShapefileReader reader = new ShapefileReader();
    reader.open(file);
    FeatureSet out = reader.getFeatures();
    Assert.equals(test.first(), out.getFeatures().get(0).getShape().first());
  }

  /**
   * Polyline XY Test.
   *
   * @throws IOException
   * @throws ShapefileException
   */
  @Test
  public void writeReadZLines() throws IOException, ShapefileException {
    Part line = new Part();
    line.getCoordinates().add(new CoordZ(1, 2, 3, 4));
    line.getCoordinates().add(new CoordZ(1, 3, 5, 6));
    Shape test = new Shape(ShapeType.PolyLineZ);
    test.getParts().add(line);
    FeatureSet fs = new FeatureSet();
    fs.getFeatures().add(new Feature(test));
    Path path = Files.createTempDirectory("ShapefileTests");
    ShapefileWriter writer = new ShapefileWriter();
    String file = path.toString() + "\\Line.shp";
    writer.write(fs, file);

    ShapefileReader reader = new ShapefileReader();
    reader.open(file);
    FeatureSet out = reader.getFeatures();
    Assert.equals(test.first(), out.getFeatures().get(0).getShape().first());
  }

  /**
   * Poylgon XY Test.
   *
   * @throws IOException
   * @throws ShapefileException
   */
  @Test
  public void writeReadZPolygons() throws IOException, ShapefileException {
    Part line = new Part();
    line.getCoordinates().add(new CoordZ(1, 1, 1, 1));
    line.getCoordinates().add(new CoordZ(5, 5, 2, 2));
    line.getCoordinates().add(new CoordZ(5, 1, 3, 3));
    Shape test = new Shape(ShapeType.PolygonZ);
    test.getParts().add(line);
    FeatureSet fs = new FeatureSet();
    fs.getFeatures().add(new Feature(test));
    Path path = Files.createTempDirectory("ShapefileTests");
    ShapefileWriter writer = new ShapefileWriter();
    String file = path.toString() + "\\Line.shp";
    writer.write(fs, file);

    ShapefileReader reader = new ShapefileReader();
    reader.open(file);
    FeatureSet out = reader.getFeatures();
    Assert.equals(test.first(), out.getFeatures().get(0).getShape().first());
  }
}
