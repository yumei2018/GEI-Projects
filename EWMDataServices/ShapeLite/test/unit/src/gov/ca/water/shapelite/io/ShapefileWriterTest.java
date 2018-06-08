/*
 * The MIT License
 *
 * Copyright 2013 hdunsford.
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
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Field;
import gov.ca.water.shapelite.FieldType;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.ShapefileException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;


/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ShapefileWriterTest {

  public ShapefileWriterTest() {
  }

 

  @Test
  public void testFileExtension() {
    String dbf = ShapefileWriter.setExtension("Testing.shp", ".dbf");
    if (!"Testing.dbf".equals(dbf)) {
      Assert.fail("The value " + dbf + " did not match the expected Testing.dbf format.");
    }
  }

  @Test
  public void testWriteReadDouble() throws IOException {
    byte[] data = new byte[8];
    Writer writer = new Writer(data);
    double original = 123.45;
    writer.write(original);
    writer.flush();
    writer.close();
    Reader reader = new Reader(data);
    double result = reader.readDouble();
    if (result != original) {
      Assert.fail("Writing double expected 123.45, but got: " + result);
    }
  }

  @Test
  public void testWriteReadDoubleFile() throws IOException {
    Path path = Files.createTempDirectory("ShapefileWriterTests");
    String file = path.toString() + "\\testDouble.bin";
    Writer writer = new Writer(file);
    double original = 123.45;
    writer.write(original);
    writer.flush();
    writer.close();
    Reader reader = new Reader(file);
    double result = reader.readDouble();
    if (result != original) {
      Assert.fail("Writing double expected 123.45, but got: " + result);
    }
  }

  @Test
  public void testSamplePoints() throws ShapefileException, IOException {
    FeatureSet fs = new FeatureSet();
    double[] xCoords = new double[]{-78.948229, -10.250681, -41.182561,
      -82.544959, -106.643052, -76.790191, -0.53951, 25.716621, 50.174387,
      55.209809};
    double[] yCoords = new double[]{56.108992, 42.081744, -2.517711, -3.237057,
      -25.536785, -52.871935, -62.942779, -32.370572, 9.351499, 77.689373};
    for (int i = 0; i < 10; i++) {
      Feature f = new Feature();
      Shape shp = new Shape(new CoordXY(xCoords[i], yCoords[i]));
      f.setShape(shp);
      f.getAttributes().put("Index", Integer.toString(i));
      fs.getFeatures().add(f);
    }
    fs.getFields().add(new Field("Index", FieldType.Character, 10));
    ShapefileWriter writer = new ShapefileWriter();
    Path path = Files.createTempDirectory("ShapefileWriterTests");
    String filename = path.toString() + "\\samplePoints.shp";
    writer.write(fs, filename);
    ShapefileReader reader = new ShapefileReader();
    reader.open(filename);
    List<Shape> shapes = reader.getShapes();
    AttributeTableReader table = new AttributeTableReader();
    table.open(path.toString() + "\\samplePoints.dbf");
    Optional<String[]> strings = table.getFieldStrings("Index");
    for (int i = 0; i < fs.getFeatures().size(); i++) {
      Shape dest = shapes.get(i);
      Shape source = fs.getFeatures().get(i).getShape();
      if (dest.getX() != source.getX()) {
        Assert.fail("X did not match: expected " + source.getX()
            + " but got " + dest.getX());
      }
      if (dest.getY() != source.getY()) {
        Assert.fail("X did not match: expected " + source.getY()
            + " but got " + dest.getY());
      }
      if (!Integer.toString(i).equals(strings.get()[i])) {
        Assert.fail("Expected " + i + " but got " + strings.get()[i]);
      }
    }

  }

  @Test
  public void testRandomLines() throws ShapefileException, IOException {
    FeatureSet fs = new FeatureSet();
    fs.getFields().add(new Field("Index", FieldType.Character, 10));
    Random rnd = new Random();

    for (int i = 0; i < 10; i++) {
      Feature f = new Feature();
      Shape shape = new Shape(ShapeType.PolyLine);
      int numParts = rnd.nextInt(5) + 1;
      for (int iPart = 0; iPart < numParts; iPart++) {
        List<Coord> coords = new ArrayList<>();
        for (int iCoord = 0; iCoord < rnd.nextInt(25); iCoord++) {
          double x = rnd.nextDouble() * 2000 - 1000;
          double y = rnd.nextDouble() * 2000 - 1000;
          coords.add(new CoordXY(x, y));
        }
        Part part = new Part(coords);
        shape.getParts().add(part);
      }
      shape.calculateBounds();
      f.setShape(shape);
      f.getAttributes().put("Index", Integer.toString(i));
      fs.getFeatures().add(f);
    }

    ShapefileWriter writer = new ShapefileWriter();
    Path path = Files.createTempDirectory("ShapefileWriterTests");
    String filename = path.toString() + "\\randomLine.shp";
    writer.write(fs, filename);
    ShapefileReader reader = new ShapefileReader();
    reader.open(filename);
    List<Shape> shapes = reader.getShapes();
    AttributeTableReader table = new AttributeTableReader();
    table.open(path.toString() + "\\randomLine.dbf");
    Optional<String[]> strings = table.getFieldStrings("Index");
    for (int i = 0; i < fs.getFeatures().size(); i++) {
      Shape dest = shapes.get(i);
      Shape source = fs.getFeatures().get(i).getShape();
      for (int iPart = 0; iPart < source.getParts().size(); iPart++) {
        Part sourcePart = source.getParts().get(iPart);
        Part destPart = dest.getParts().get(iPart);
        for (int iCoord = 0; iCoord < sourcePart.getCoordinates().size(); iCoord++) {
          Coord src = sourcePart.getCoordinates().get(iCoord);
          Coord dst = destPart.getCoordinates().get(iCoord);
          if (src.getX() != dst.getX()) {
            Assert.fail("X did not match: expected " + src + " but got " + dst);
          }
          if (src.getY() != dst.getY()) {
            Assert.fail("X did not match: expected " + src + " but got " + dst);
          }
        }
      }
      if (!Integer.toString(i).equals(strings.get()[i])) {
        Assert.fail("Expected " + i + " but got " + strings.get()[i]);
      }
    }

  }

}
