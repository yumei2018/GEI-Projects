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

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.ShapefileException;
import gov.ca.water.shapelite.compare.ShapeComparer;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Projections;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ShapefileChannelScannerTest {

  @Rule
  public TemporaryFolder folder = new TemporaryFolder();

  /**
   * File.
   */
  private File file;
  /**
   * A file to store MultiPoints.
   */
  private File multiPointFile;
  /**
   * A file to store lines.
   */
  private File lineFile;
  /**
   * A file to store polygons.
   */
  private File polygonFile;

  public ShapefileChannelScannerTest() {
  }

  /**
   * Test of getShapes method, of class ShapefileChannelScanner.
   *
   * @throws java.io.IOException
   * @throws gov.ca.water.shapelite.ShapefileException
   * @throws java.io.FileNotFoundException
   * @throws gov.ca.water.shapelite.io.ShapefileScannerException
   */
  @Test
  public void readPointXY() throws IOException, ShapefileException,
      FileNotFoundException, ShapefileScannerException {
    testWriteRead(ShapeType.Point);
  }

  /**
   * Test of getShapes method, of class ShapefileChannelScanner.
   *
   * @throws java.io.IOException
   * @throws gov.ca.water.shapelite.ShapefileException
   * @throws java.io.FileNotFoundException
   * @throws gov.ca.water.shapelite.io.ShapefileScannerException
   */
  @Test
  public void readPointM() throws IOException, ShapefileException,
      FileNotFoundException, ShapefileScannerException {
    testWriteRead(ShapeType.PointM);
  }

  /**
   * Test of getShapes method, of class ShapefileChannelScanner.
   *
   * @throws java.io.IOException
   * @throws gov.ca.water.shapelite.ShapefileException
   * @throws java.io.FileNotFoundException
   * @throws gov.ca.water.shapelite.io.ShapefileScannerException
   */
  @Test
  public void readPointZ() throws IOException, ShapefileException,
      FileNotFoundException, ShapefileScannerException {
    testWriteRead(ShapeType.PointZ);
  }

  /**
   * Test of getShapes method, of class ShapefileChannelScanner.
   *
   * @throws java.io.IOException
   * @throws gov.ca.water.shapelite.ShapefileException
   * @throws java.io.FileNotFoundException
   * @throws gov.ca.water.shapelite.io.ShapefileScannerException
   */
  @Test
  public void readMultiPointXY() throws IOException, ShapefileException,
      FileNotFoundException, ShapefileScannerException {
    testWriteRead(ShapeType.MultiPoint);
  }

  /**
   * Test of getShapes method, of class ShapefileChannelScanner.
   *
   * @throws java.io.IOException
   * @throws gov.ca.water.shapelite.ShapefileException
   * @throws java.io.FileNotFoundException
   * @throws gov.ca.water.shapelite.io.ShapefileScannerException
   */
  @Test
  public void readMultiPointM() throws IOException, ShapefileException,
      FileNotFoundException, ShapefileScannerException {
    testWriteRead(ShapeType.MultiPointM);
  }

  /**
   * Test of getShapes method, of class ShapefileChannelScanner.
   *
   * @throws java.io.IOException
   * @throws gov.ca.water.shapelite.ShapefileException
   * @throws java.io.FileNotFoundException
   * @throws gov.ca.water.shapelite.io.ShapefileScannerException
   */
  @Test
  public void readMultiPointZ() throws IOException, ShapefileException,
      FileNotFoundException, ShapefileScannerException {
    testWriteRead(ShapeType.MultiPointZ);
  }

  /**
   * Test of getShapes method, of class ShapefileChannelScanner.
   *
   * @throws java.io.IOException
   * @throws gov.ca.water.shapelite.ShapefileException
   * @throws java.io.FileNotFoundException
   * @throws gov.ca.water.shapelite.io.ShapefileScannerException
   */
  @Test
  public void readPolyLineXY() throws IOException, ShapefileException,
      FileNotFoundException, ShapefileScannerException {
    testWriteRead(ShapeType.PolyLine);
  }

  /**
   * Test of getShapes method, of class ShapefileChannelScanner.
   *
   * @throws java.io.IOException
   * @throws gov.ca.water.shapelite.ShapefileException
   * @throws java.io.FileNotFoundException
   * @throws gov.ca.water.shapelite.io.ShapefileScannerException
   */
  @Test
  public void readPolyLineM() throws IOException, ShapefileException,
      FileNotFoundException, ShapefileScannerException {
    testWriteRead(ShapeType.PolyLineM);
  }

  /**
   * Test of getShapes method, of class ShapefileChannelScanner.
   *
   * @throws java.io.IOException
   * @throws gov.ca.water.shapelite.ShapefileException
   * @throws java.io.FileNotFoundException
   * @throws gov.ca.water.shapelite.io.ShapefileScannerException
   */
  @Test
  public void readPolyLineZ() throws IOException, ShapefileException,
      FileNotFoundException, ShapefileScannerException {
    testWriteRead(ShapeType.PolyLineZ);
  }

  /**
   * Test of getShapes method, of class ShapefileChannelScanner.
   *
   * @throws java.io.IOException
   * @throws gov.ca.water.shapelite.ShapefileException
   * @throws java.io.FileNotFoundException
   * @throws gov.ca.water.shapelite.io.ShapefileScannerException
   */
  @Test
  public void readPolygonXY() throws IOException, ShapefileException,
      FileNotFoundException, ShapefileScannerException {
    testWriteRead(ShapeType.Polygon);
  }

  /**
   * Test of getShapes method, of class ShapefileChannelScanner.
   *
   * @throws java.io.IOException
   * @throws gov.ca.water.shapelite.ShapefileException
   * @throws java.io.FileNotFoundException
   * @throws gov.ca.water.shapelite.io.ShapefileScannerException
   */
  @Test
  public void readPolygonM() throws IOException, ShapefileException,
      FileNotFoundException, ShapefileScannerException {
    testWriteRead(ShapeType.PolygonM);
  }

  /**
   * Test of getShapes method, of class ShapefileChannelScanner.
   *
   * @throws java.io.IOException
   * @throws gov.ca.water.shapelite.ShapefileException
   * @throws java.io.FileNotFoundException
   * @throws gov.ca.water.shapelite.io.ShapefileScannerException
   */
  @Test
  public void readPolygonZ() throws IOException, ShapefileException,
      FileNotFoundException, ShapefileScannerException {
    testWriteRead(ShapeType.PolygonZ);
  }

  /**
   * Test of getShapes method, of class ShapefileChannelScanner.
   *
   * @throws java.io.IOException
   * @throws gov.ca.water.shapelite.ShapefileException
   * @throws java.io.FileNotFoundException
   * @throws gov.ca.water.shapelite.io.ShapefileScannerException
   */
  @Test
  public void readPointXYIndex() throws IOException, ShapefileException,
      FileNotFoundException, ShapefileScannerException {
    testWriteReadIndex(ShapeType.Point);
  }

  /**
   * Test of getShapes method, of class ShapefileChannelScanner.
   *
   * @throws java.io.IOException
   * @throws gov.ca.water.shapelite.ShapefileException
   * @throws java.io.FileNotFoundException
   * @throws gov.ca.water.shapelite.io.ShapefileScannerException
   */
  @Test
  public void readPointMIndex() throws IOException, ShapefileException,
      FileNotFoundException, ShapefileScannerException {
    testWriteReadIndex(ShapeType.PointM);
  }

  /**
   * Test of getShapes method, of class ShapefileChannelScanner.
   *
   * @throws java.io.IOException
   * @throws gov.ca.water.shapelite.ShapefileException
   * @throws java.io.FileNotFoundException
   * @throws gov.ca.water.shapelite.io.ShapefileScannerException
   */
  @Test
  public void readPointZIndex() throws IOException, ShapefileException,
      FileNotFoundException, ShapefileScannerException {
    testWriteReadIndex(ShapeType.PointZ);
  }

  /**
   * Test of getShapes method, of class ShapefileChannelScanner.
   *
   * @throws java.io.IOException
   * @throws gov.ca.water.shapelite.ShapefileException
   * @throws java.io.FileNotFoundException
   * @throws gov.ca.water.shapelite.io.ShapefileScannerException
   */
  @Test
  public void readMultiPointXYIndex() throws IOException, ShapefileException,
      FileNotFoundException, ShapefileScannerException {
    testWriteReadIndex(ShapeType.MultiPoint);
  }

  /**
   * Test of getShapes method, of class ShapefileChannelScanner.
   *
   * @throws java.io.IOException
   * @throws gov.ca.water.shapelite.ShapefileException
   * @throws java.io.FileNotFoundException
   * @throws gov.ca.water.shapelite.io.ShapefileScannerException
   */
  @Test
  public void readMultiPointMIndex() throws IOException, ShapefileException,
      FileNotFoundException, ShapefileScannerException {
    testWriteReadIndex(ShapeType.MultiPointM);
  }

  /**
   * Test of getShapes method, of class ShapefileChannelScanner.
   *
   * @throws java.io.IOException
   * @throws gov.ca.water.shapelite.ShapefileException
   * @throws java.io.FileNotFoundException
   * @throws gov.ca.water.shapelite.io.ShapefileScannerException
   */
  @Test
  public void readMultiPointZIndex() throws IOException, ShapefileException,
      FileNotFoundException, ShapefileScannerException {
    testWriteReadIndex(ShapeType.MultiPointZ);
  }

  /**
   * Test of getShapes method, of class ShapefileChannelScanner.
   *
   * @throws java.io.IOException
   * @throws gov.ca.water.shapelite.ShapefileException
   * @throws java.io.FileNotFoundException
   * @throws gov.ca.water.shapelite.io.ShapefileScannerException
   */
  @Test
  public void readPolyLineXYIndex() throws IOException, ShapefileException,
      FileNotFoundException, ShapefileScannerException {
    testWriteReadIndex(ShapeType.PolyLine);
  }

  /**
   * Test of getShapes method, of class ShapefileChannelScanner.
   *
   * @throws java.io.IOException
   * @throws gov.ca.water.shapelite.ShapefileException
   * @throws java.io.FileNotFoundException
   * @throws gov.ca.water.shapelite.io.ShapefileScannerException
   */
  @Test
  public void readPolyLineMIndex() throws IOException, ShapefileException,
      FileNotFoundException, ShapefileScannerException {
    testWriteReadIndex(ShapeType.PolyLineM);
  }

  /**
   * Test of getShapes method, of class ShapefileChannelScanner.
   *
   * @throws java.io.IOException
   * @throws gov.ca.water.shapelite.ShapefileException
   * @throws java.io.FileNotFoundException
   * @throws gov.ca.water.shapelite.io.ShapefileScannerException
   */
  @Test
  public void readPolyLineZIndex() throws IOException, ShapefileException,
      FileNotFoundException, ShapefileScannerException {
    testWriteReadIndex(ShapeType.PolyLineZ);
  }

  /**
   * Test of getShapes method, of class ShapefileChannelScanner.
   *
   * @throws java.io.IOException
   * @throws gov.ca.water.shapelite.ShapefileException
   * @throws java.io.FileNotFoundException
   * @throws gov.ca.water.shapelite.io.ShapefileScannerException
   */
  @Test
  public void readPolygonXYIndex() throws IOException, ShapefileException,
      FileNotFoundException, ShapefileScannerException {
    testWriteReadIndex(ShapeType.Polygon);
  }

  /**
   * Test of getShapes method, of class ShapefileChannelScanner.
   *
   * @throws java.io.IOException
   * @throws gov.ca.water.shapelite.ShapefileException
   * @throws java.io.FileNotFoundException
   * @throws gov.ca.water.shapelite.io.ShapefileScannerException
   */
  @Test
  public void readPolygonMIndex() throws IOException, ShapefileException,
      FileNotFoundException, ShapefileScannerException {
    testWriteReadIndex(ShapeType.PolygonM);
  }

  /**
   * Test of getShapes method, of class ShapefileChannelScanner.
   *
   * @throws java.io.IOException
   * @throws gov.ca.water.shapelite.ShapefileException
   * @throws java.io.FileNotFoundException
   * @throws gov.ca.water.shapelite.io.ShapefileScannerException
   */
  @Test
  public void readPolygonZIndex() throws IOException, ShapefileException,
      FileNotFoundException, ShapefileScannerException {
    testWriteReadIndex(ShapeType.PolygonZ);
  }

  public void testWriteRead(ShapeType type) throws IOException,
      ShapefileException, FileNotFoundException, ShapefileScannerException {
    file = folder.newFile("shape" + type.toString() + ".shp");
    FeatureSet fs = TestShapefileFactory.createFeatureSet(type);
    ShapefileWriter writer = new ShapefileWriter();
    writer.write(fs, file.getAbsolutePath());
    ShapefileChannelScanner instance = new ShapefileChannelScanner(
        file.getAbsolutePath());
    List<Shape> resultShapes = instance.getShapes();
    ShapeComparer shapes = new ShapeComparer();
    assertEquals(resultShapes.size(), fs.getFeatures().size());
    for (int i = 0; i < resultShapes.size(); i++) {
      boolean equals = shapes.equivalent(resultShapes.get(i),
          fs.getFeatures().get(i).getShape());
      assertTrue(equals);
    }
  }

  public void testWriteReadIndex(ShapeType type) throws IOException,
      ShapefileException, FileNotFoundException, ShapefileScannerException {
    file = folder.newFile("shape" + type.toString() + "Index.shp");
    FeatureSet fs = TestShapefileFactory.createFeatureSet(type);
    ShapefileWriter writer = new ShapefileWriter();
    writer.write(fs, file.getAbsolutePath());
    ShapefileChannelScanner instance = new ShapefileChannelScanner(
        file.getAbsolutePath());
    List<Shape> resultShapes = instance.getShapes(10, 50);
    ShapeComparer shapes = new ShapeComparer();
    assertEquals(50, resultShapes.size());
    for (int i = 0; i < resultShapes.size(); i++) {
      boolean equals = shapes.equivalent(resultShapes.get(i),
          fs.getFeatures().get(i + 10).getShape());
      assertTrue(equals);
    }
  }

  /**
   * Test of readEnvelopeXY method, of class ShapefileChannelScanner.
   *
   * @throws java.lang.Exception
   */
  @Test
  public void testReadEnvelopeXY() throws Exception {
    file = folder.newFile("shapeEnv.shp");
    FeatureSet fs = TestShapefileFactory.createFeatureSet(ShapeType.PolyLine);
    ShapefileWriter writer = new ShapefileWriter();
    writer.write(fs, file.getAbsolutePath());
    ShapefileChannelScanner instance = new ShapefileChannelScanner(
        file.getAbsolutePath());
    assertEquals(fs.getFeatures().get(10).getEnvelope(), instance.readEnvelopeXY(10));

  }

  /**
   * Test of getClosestShape method, of class ShapefileChannelScanner.
   * @throws java.lang.Exception
   */
  @Test
  public void testGetClosestShape() throws Exception {
    file = folder.newFile("shapePoints.shp");
    FeatureSet fs = TestShapefileFactory.createFeatureSet(ShapeType.Point);
    ShapefileWriter writer = new ShapefileWriter();
    writer.write(fs, file.getAbsolutePath());
    CoordXY testPoint = new CoordXY(0, 0);
    double minDist = Double.MAX_VALUE;
    Coord closest = null;
    for (Feature f : fs.getFeatures()) {
      Coord c = f.getShape().first();
      double distance = testPoint.distance(c);
      if (distance < minDist) {
        minDist = distance;
        closest = c;
      }
    }

    ShapefileChannelScanner instance = new ShapefileChannelScanner(
        file.getAbsolutePath());
    Optional<Shape> maybeShape = instance.getClosestShape(testPoint);
    assertEquals(closest, maybeShape.get().first());

  }

  /**
   * Test of setExtension method, of class ShapefileChannelScanner.
   */
  @Test
  public void testSetExtension() {
    System.out.println("setExtension");
    String filename = "Folder\\Foo.dbf";
    String extension = ".shp";
    String expResult = "Folder\\Foo.shp";
    String result = ShapefileChannelScanner.setExtension(filename, extension);
    assertEquals(expResult, result);
  }

  /**
   * Test of getFilename method, of class ShapefileChannelScanner.
   * @throws java.lang.Exception
   */
  @Test
  public void testGetFilename() throws Exception {
    file = folder.newFile("shapePoints.shp");
    FeatureSet fs = TestShapefileFactory.createFeatureSet(ShapeType.Point);
    ShapefileWriter writer = new ShapefileWriter();
    writer.write(fs, file.getAbsolutePath());
    String expResult = file.getAbsolutePath();
    ShapefileChannelScanner instance = new ShapefileChannelScanner(
        file.getAbsolutePath());
    String result = instance.getFilename();
    assertEquals(expResult, result);
  }

  /**
   * Test of setFilename method, of class ShapefileChannelScanner.
   * @throws java.lang.Exception
   */
  @Test
  public void testSetFilename() throws Exception {
    file = folder.newFile("shapePoints.shp");
    FeatureSet fs = TestShapefileFactory.createFeatureSet(ShapeType.Point);
    ShapefileWriter writer = new ShapefileWriter();
    writer.write(fs, file.getAbsolutePath());
    String expResult = folder.newFile("testShape.shp").getAbsolutePath();
    ShapefileChannelScanner instance = new ShapefileChannelScanner(
        file.getAbsolutePath());
    instance.setFilename(expResult);
    String result = instance.getFilename();
    assertEquals(expResult, result);
  }

  /**
   * Test of getNumShapes method, of class ShapefileChannelScanner.
   * @throws java.lang.Exception
   */
  @Test
  public void testGetNumShapes() throws Exception {
    file = folder.newFile("shapeNumShapes.shp");
    FeatureSet fs = TestShapefileFactory.createFeatureSet(ShapeType.PolyLine);
    ShapefileWriter writer = new ShapefileWriter();
    writer.write(fs, file.getAbsolutePath());
    ShapefileChannelScanner instance = new ShapefileChannelScanner(
        file.getAbsolutePath());
    assertEquals(100, instance.getNumShapes());
  }

  /**
   * Test of getProjection method, of class ShapefileChannelScanner.
   * @throws java.lang.Exception
   */
  @Test
  public void testGetProjection() throws Exception {
    file = folder.newFile("shapeProjection.shp");
    FeatureSet fs = TestShapefileFactory.createFeatureSet(ShapeType.PolyLine);
    fs.setProjectionFrom(Projections.getNad83UTMZone10Foot());
    ShapefileWriter writer = new ShapefileWriter();
    writer.write(fs, file.getAbsolutePath());
    ShapefileChannelScanner instance = new ShapefileChannelScanner(
        file.getAbsolutePath());
    ProjectionInfo proj = ProjectionInfo.fromEsriString(instance.getProjection());
    assertEquals(proj, Projections.getNad83UTMZone10Foot());
  }

}
