/*
 * The MIT License
 *
 * Copyright 2012 hdunsford.
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
import gov.ca.water.shapelite.DefaultEnvelope;
import gov.ca.water.shapelite.coordinate.CoordM;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.coordinate.CoordZ;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Field;
import gov.ca.water.shapelite.FileHelper;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.ReadBytes;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.ShapefileHeader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * The ShapefileScanner reads the entire index file into memory, but will scan
 * through the .shp file to find records. If no index file is passed, then
 * scanning will not be as fast since the offset for each shape will not be
 * known in advance, but it will still work. Features can only be returned if
 * the dbf is not null. The dbf is not required unless full features are
 * requested. The scanner only provides forward reading, and does not provide
 * random access to shapes.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ShapefileScanner implements AutoCloseable {

  /**
   * A 2D envelope has 4 doubles.
   */
  private static final int SIZE_ENVELOPE_2D = 4;

  /**
   * The Z index of a coordinate.
   */
  private static final int Z = Coord.Index.Z;

  /**
   * THe M index of a coordinate.
   */
  private static final int M = Coord.Index.M;

  /**
   * The size in bytes of the file header for .shp and .shx files.
   */
  private static final int HEADER_SIZE = 100;

  /**
   * Each record in the index file is represented by a 4 byte big integer for
   * the record offset and a 4 byte big integer representing the record length,
   * for a total of 8 bytes.
   */
  private static final int INDEX_RECORD_SIZE = 8;

  /**
   * The size of a double X and a double Y coordinate, when represented
   * as 16 bit words.
   */
  private static final int XY_SIZE = 8;


  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The string filename to read.
   */
  private String filename;

  /**
   * A ShapefileHeader class representing the header content for this shapefile.
   */
  private ShapefileHeader header;

  /**
   * The integer lengths of the shapes from the index file. The lengths are in
   * words, which are 4 bytes each.
   */
  private int[] lengths;

  /**
   * The integer offsets of the shapes from the offset file.
   */
  private int[] offsets;

  /**
   * The integer number of shapes in the shapefile.
   */
  private int numShapes;

  /**
   * The binary reader that will parse bytes into doubles and integers.
   */
  private Scanner shapeScanner;

  /**
   * The resulting list of shapes from the shapefile.
   */
  private List<Shape> shapes;

  /**
   * The raw byte values from the raw ShxFile.
   */
  private byte[] rawShxFile;

  /**
   * A scanner to read the attributes from the dbf file.
   */
  private AttributeTableScanner attributes;

  /**
   * The string projection.
   */
  private String projection;

  /**
   * The string name of the shapefile.
   */
  private String name;

  /**
   * The zero based integer index of the current shape.
   */
  private int currentShape;

  /**
   * The logger.
   */
  private static final Logger LOGGER = Logger.getLogger(ShapefileScanner.class.getName());

  //</editor-fold>
  /**
   * Opens the shapefile for scanning, expecting the other shapefile helper
   * files.
   *
   * @param filename The string filename of the shp file.
   * @throws IOException if there is an error reading the file stream.
   * @throws java.io.FileNotFoundException If the file doesn't exist.
   * @throws gov.ca.water.shapelite.io.ShapefileScannerException If the file
   * cannot be read correctly.
   */
  public ShapefileScanner(String filename) throws FileNotFoundException,
      IOException, ShapefileScannerException {
    InputStream shp = new FileInputStream(filename);
    InputStream shx = new FileInputStream(ShapefileWriter.setExtension(filename, ".shx"));
    InputStream dbf = new FileInputStream(ShapefileWriter.setExtension(filename, ".dbf"));
    InputStream prj = new FileInputStream(ShapefileWriter.setExtension(filename, ".prj"));
    open(shp, shx, dbf, prj);
  }

  /**
   * This initializes the scanner, reading the index file into memory, and
   * automatically scanning the header content of the shp to populate the
   * properties of this class. The actual shapes remain unread, and can be read
   * in the forward direction only. The close method should be called on this
   * resource.
   *
   * @param shp The shp stream.
   * @param shx The index file stream. This is required.
   * @param dbf The attribute dbf file stream. Can be null.
   * @param prj the .prj input stream. Can be null.
   * @throws java.io.IOException If the streams cannot be read.
   * @throws gov.ca.water.shapelite.io.ShapefileScannerException If the file
   * cannot be read correctly.
   */
  public ShapefileScanner(InputStream shp, InputStream shx,
      InputStream dbf, InputStream prj) throws IOException, ShapefileScannerException {
    open(shp, shx, dbf, prj);
  }

  /**
   * Gets all the features for the shapefile.
   *
   * @return FeatureSet with features.
   * @throws FileNotFoundException If the file cannot be found.
   * @throws gov.ca.water.shapelite.io.ShapefileScannerException if the file
   * can't be correctly parsed into features.
   */
  public final FeatureSet getFeatures() throws FileNotFoundException,
      ShapefileScannerException {
    return getFeatures(0, numShapes);
  }

  /**
   * Gets the specified features, loading them into a feature set.
   *
   * @param start The integer start index of the features to grab.
   * @param numFeatures The integer number of features to read.
   * @return A FeatureSet made up of the specified features.
   * @throws FileNotFoundException If the specified file cannot be found.
   * @throws gov.ca.water.shapelite.io.ShapefileScannerException if there is an
   * error reading the shapefile content.
   */
  public final FeatureSet getFeatures(int start, int numFeatures)
      throws FileNotFoundException, ShapefileScannerException {
    FeatureSet result = new FeatureSet();
    List<Shape> myShapes = getShapes(start, numFeatures);
    result.getFields().addAll(attributes.getFields());
    List<String[]> attText = attributes.getRows(start, numFeatures);
    for (int row = 0; row < myShapes.size(); row++) {
      Feature f = new Feature();
      f.setShape(myShapes.get(row));
      for (int iField = 0; iField < result.getFields().size(); iField++) {
        Field field = result.getFields().get(iField);
        f.getAttributes().put(field.getName(), attText.get(row)[iField]);
      }
      result.getFeatures().add(f);
    }
    result.setName(name);
    result.setProjectionESRI(projection);
    return result;
  }

  /**
   * Gets all the shapes from the shapefile in a single list.
   *
   * @return The list of shapes.
   * @throws gov.ca.water.shapelite.io.ShapefileScannerException If there is an
   * error reading the shapes.
   */
  public final List<Shape> getShapes() throws ShapefileScannerException {
    return this.getShapes(0, numShapes);
  }

  /**
   * Rather than reading the entire shapefile, this reads from the specified
   * start index to the specified numShapes after that.
   *
   * @param start The start index of the first shape to return.
   * @param numShapes The number of shapes to return.
   * @return A List of shapes.
   * @throws gov.ca.water.shapelite.io.ShapefileScannerException If there is an
   * error reading the shapes.
   */
  public final List<Shape> getShapes(int start, int numShapes)
      throws ShapefileScannerException {
    List<Shape> result = new ArrayList<>();
    int end = start + numShapes;
    if (end > this.numShapes) {
      end = this.numShapes;
    }
    for (int i = start; i < end; i++) {
      Shape shape = doReadShape(i);
      result.add(shape);
    }
    return result;
  }

  /**
   * Closes the shape reader if it is open.
   */
  @Override
  public final void close() {
    if (attributes != null) {
      attributes.close();
    }
    if (shapeScanner != null) {
      shapeScanner.close();
    }
  }

  /**
   * Opens the specified streams as if they were files on disk. This will also
   * read the 100 header bytes into the header for this scanner.
   *
   * @param shapefile The shapefile stream.
   * @param shx The index file stream. This is required.
   * @param dbf The attribute dbf file stream. Can be null.
   * @param prj the .prj input stream. Can be null.
   * @throws IOException If there was an error reading the streams.
   * @throws gov.ca.water.shapelite.io.ShapefileScannerException If the file
   * stream headers could not be correctly parsed.
   */
  private void open(InputStream shapefile, InputStream shx, InputStream dbf,
      InputStream prj) throws IOException, ShapefileScannerException {
    shapeScanner = new Scanner(shapefile);
    if (shx != null) {
      rawShxFile = ReadBytes.readAll(shx);
      readIndex();
    } else {
      throw new ShapefileScannerException("The index file input stream cannot be null.");
    }
    header = new ShapefileHeader();
    header.read(shapeScanner);
    currentShape = 0;
    if (dbf != null) {
      attributes = new AttributeTableScanner(dbf);
    }
    if (prj != null) {
      projection = FileHelper.readAll(prj);
    }

  }

  /**
   * Reads the offset and length values from the index file. These are stored in
   * memory, even if the raw data points for the shapefile itself are not.
   */
  private void readIndex() {
    Reader r = new Reader(rawShxFile);

    numShapes = (rawShxFile.length - HEADER_SIZE) / INDEX_RECORD_SIZE;
    lengths = new int[numShapes];
    offsets = new int[numShapes];
    r.advance(HEADER_SIZE);

    for (int i = 0; i < numShapes; i++) {
      offsets[i] = r.readBigInt();
      lengths[i] = r.readBigInt();
    }
  }

  /**
   * Reads the shape with the specified index value from the shapefile.
   *
   * @param index The integer index of the shape to read.
   * @return The Shape for the specified index.
   * @throws gov.ca.water.shapelite.io.ShapefileScannerException If there was an
   * error reading the shape, or if the index was out of bounds.
   */
  public final Shape readShape(int index) throws ShapefileScannerException {
    Shape result = doReadShape(index);
    return result;
  }

  /**
   * This is the actual internal business logic to read a single shape.
   *
   * @param index The integer index value of the shape to read.
   * @return The Shape at the specified index.
   * @throws ShapefileScannerException if there was an error reading the
   * shape, or if the index was out of bounds.
   */
  private Shape doReadShape(int index) throws ShapefileScannerException {
    if (index < currentShape) {
      throw new ShapefileScannerException("foward scan only.  If random"
          + " access is desired, use a ShapefileReader instead.");
    }
    shapeScanner.advanceTo(offsets[index] * 2);
    int fid = shapeScanner.readBigInt();
    int length = shapeScanner.readBigInt();
    int shapetype = shapeScanner.readInt();
    Shape result = new Shape(ShapeType.find(shapetype));
    result.setFid(fid);
    result.setContentLength(length);
    switch (result.getShapeType()) {
      case NullShape:
        return result;
      case Point:
        readPoint(result);
        break;
      case PointM:
        readPointM(result);
        break;
      case PointZ:
        readPointZ(result);
        break;
      case MultiPoint:
        readMultiPoint(result);
        break;
      case MultiPointM:
        readMultiPointM(result);
        break;
      case MultiPointZ:
        readMultiPointZ(result);
        break;
      case PolyLine:
        readPolyline(result);
        break;
      case PolyLineM:
        readPolylineM(result);
        break;
      case PolyLineZ:
        readPolylineZ(result);
        break;
      case Polygon:
        readPolyline(result);
        break;
      case PolygonM:
        readPolylineM(result);
        break;
      case PolygonZ:
        readPolylineZ(result);
        break;
      default:
        return result;
    }
    if (result.getShapeType() == ShapeType.Polygon
        || result.getShapeType() == ShapeType.PolygonM
        || result.getShapeType() == ShapeType.PolygonZ) {
      for (Part prt : result.getParts()) {
        prt.setClosed(true);
      }
    }
    currentShape = index + 1;
    return result;
  }

  /**
   * Reads point data from the shapeScanner into the specified shape.
   *
   * @param result The Shape to populate with data from the file.
   */
  private void readPoint(Shape result) {
    result.setX(shapeScanner.readDouble());
    result.setY(shapeScanner.readDouble());
    result.calculateBounds();
  }

  /**
   * Reads pointM data from the shapeScanner into the specified shape.
   *
   * @param result The Shape to populate with data from the file.
   */
  private void readPointM(Shape result) {
    result.setX(shapeScanner.readDouble());
    result.setY(shapeScanner.readDouble());
    result.setM(shapeScanner.readDouble());
    result.calculateBounds();
  }

  /**
   * Reads point z data from the shapeScanner into the specified shape.
   *
   * @param result The Shape to populate with data from the file.
   */
  private void readPointZ(Shape result) {
    result.setX(shapeScanner.readDouble());
    result.setY(shapeScanner.readDouble());
    result.setZ(shapeScanner.readDouble());
    result.setM(shapeScanner.readDouble());
    result.calculateBounds();
  }

  /**
   * Reads point data from the shapeScanner into the specified shape.
   *
   * @param result The Shape to populate with data from the file.
   */
  private void readMultiPoint(Shape result) {
    double[] vals = shapeScanner.readDouble(SIZE_ENVELOPE_2D);
    Envelope bounds = new DefaultEnvelope(vals);
    result.getEnvelope().copyProperties(bounds);
    int numPoints = shapeScanner.readInt();
    Part part = new Part();
    for (int i = 0; i < numPoints; i++) {
      double x = shapeScanner.readDouble();
      double y = shapeScanner.readDouble();
      Coord pt = new CoordXY(x, y);
      part.getCoordinates().add(pt);
    }
    result.getParts().add(part);
  }

  /**
   * Reads point data from the shapeScanner into the specified shape.
   *
   * @param result The Shape to populate with data from the file.
   */
  private void readMultiPointM(Shape result) {
    double[] vals = shapeScanner.readDouble(SIZE_ENVELOPE_2D);
    Envelope bounds = DefaultEnvelope.getM(vals);
    result.getEnvelope().copyProperties(bounds);
    int numPoints = shapeScanner.readInt();
    Part part = new Part();
    for (int i = 0; i < numPoints; i++) {
      double x = shapeScanner.readDouble();
      double y = shapeScanner.readDouble();
      Coord pt = new CoordM(x, y, 0);
      part.getCoordinates().add(pt);
    }
    result.getParts().add(part);
    if (result.getContentLength() > (20 + numPoints * XY_SIZE)) {
      result.getEnvelope().getMin().set(M, shapeScanner.readDouble());
      result.getEnvelope().getMax().set(M, shapeScanner.readDouble());
      result.setArrayM(shapeScanner.readDouble(numPoints));
    }

  }

  /**
   * Reads point Z data from the shapeScanner into the specified shape.
   *
   * @param result The Shape to populate with data from the file.
   */
  private void readMultiPointZ(Shape result) {
    double[] vals = shapeScanner.readDouble(SIZE_ENVELOPE_2D);
    Envelope bounds = DefaultEnvelope.getZ(vals);
    result.getEnvelope().copyProperties(bounds);
    int numPoints = shapeScanner.readInt();
    Part part = new Part();
    for (int i = 0; i < numPoints; i++) {
      double x = shapeScanner.readDouble();
      double y = shapeScanner.readDouble();
      Coord pt = new CoordZ(x, y, 0, 0);
      part.getCoordinates().add(pt);
    }
    result.getParts().add(part);
    result.getEnvelope().getMin().set(Z, shapeScanner.readDouble());
    result.getEnvelope().getMax().set(Z, shapeScanner.readDouble());
    result.setArrayZ(shapeScanner.readDouble(numPoints));
    if (result.getContentLength() > (28 + numPoints * 12)) {
      result.getEnvelope().getMin().set(M, shapeScanner.readDouble());
      result.getEnvelope().getMax().set(M, shapeScanner.readDouble());
      result.setArrayM(shapeScanner.readDouble(numPoints));
    }

  }

  /**
   * Reads polyline data from the shapeScanner into the specified shape.
   *
   * @param result The Shape to populate with data from the file.
   */
  public final void readPolyline(Shape result) {
    double[] vals = shapeScanner.readDouble(SIZE_ENVELOPE_2D);
    Envelope bounds = DefaultEnvelope.getXY(vals);
    result.getEnvelope().copyProperties(bounds);
    int numParts = shapeScanner.readInt();
    int numPoints = shapeScanner.readInt();
    int[] rawParts = shapeScanner.readInt(numParts);
    int end = numPoints;
    double[] points = shapeScanner.readDouble(numPoints * 2);
    for (int prt = rawParts.length - 1; prt >= 0; prt--) {
      Part part = new Part();
      for (int i = rawParts[prt]; i < end; i++) {
        part.getCoordinates().add(new CoordXY(points[i * 2], points[i * 2 + 1]));
      }
      result.getParts().add(0, part);
      end = rawParts[prt];
    }
  }

  /**
   * Reads polyline M data from the shapeScanner into the specified shape.
   *
   * @param result The Shape to populate with data from the file.
   */
  public final void readPolylineM(Shape result) {
    double[] vals = shapeScanner.readDouble(SIZE_ENVELOPE_2D);
    Envelope bounds = DefaultEnvelope.getM(vals);
    result.getEnvelope().copyProperties(bounds);
    int numParts = shapeScanner.readInt();
    int numPoints = shapeScanner.readInt();
    int[] rawParts = shapeScanner.readInt(numParts);
    int end = numPoints;
    double[] points = shapeScanner.readDouble(numPoints * 2);
    for (int prt = rawParts.length - 1; prt >= 0; prt--) {
      Part part = new Part();
      for (int i = rawParts[prt]; i < end; i++) {
        part.getCoordinates().add(new CoordM(points[i * 2], points[i * 2 + 1], 0));
      }
      result.getParts().add(0, part);
      end = rawParts[prt];
    }
    if (result.getContentLength() > 22 + 2 * numParts + XY_SIZE * numPoints) {
      result.getEnvelope().getMin().set(M, shapeScanner.readDouble());
      result.getEnvelope().getMax().set(M, shapeScanner.readDouble());
      result.setArrayM(shapeScanner.readDouble(numPoints));
    }
  }

  /**
   * Reads polyline z data from the shapeScanner into the specified shape.
   *
   * @param result The Shape to populate with data from the file.
   */
  public final void readPolylineZ(Shape result) {
    double[] vals = shapeScanner.readDouble(SIZE_ENVELOPE_2D);
    Envelope bounds = DefaultEnvelope.getZ(vals);
    result.getEnvelope().copyProperties(bounds);
    int numParts = shapeScanner.readInt();
    int numPoints = shapeScanner.readInt();
    int[] rawParts = shapeScanner.readInt(numParts);
    int end = numPoints;
    double[] points = shapeScanner.readDouble(numPoints * 2);
    for (int prt = rawParts.length - 1; prt >= 0; prt--) {
      Part part = new Part();
      for (int i = rawParts[prt]; i < end; i++) {
        part.getCoordinates().add(new CoordZ(points[i * 2], points[i * 2 + 1], 0, 0));
      }
      result.getParts().add(0, part);
      end = rawParts[prt];
    }
    result.getEnvelope().getMin().set(Z, shapeScanner.readDouble());
    result.getEnvelope().getMax().set(Z, shapeScanner.readDouble());
    result.setArrayZ(shapeScanner.readDouble(numPoints));
    if (result.getContentLength() > 22 + 2 * numParts
        + XY_SIZE * numPoints + 8 + 4 * numPoints) {
      result.getEnvelope().getMin().set(M, shapeScanner.readDouble());
      result.getEnvelope().getMax().set(M, shapeScanner.readDouble());
      result.setArrayM(shapeScanner.readDouble(numPoints));
    }
  }

  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the string filename to read.
   *
   * @return the filename
   */
  public final String getFilename() {
    return filename;
  }

  /**
   * Sets the string filename to read.
   *
   * @param filename the filename to set
   */
  public final void setFilename(String filename) {
    this.filename = filename;
  }

  /**
   * Gets the integer lengths of the shapes from the index file. The lengths are
   * in words, which are 4 bytes each.
   *
   * @return the lengths
   */
  public final int[] getLengths() {
    return lengths;
  }

  /**
   * Sets the integer lengths of the shapes from the index file. The lengths are
   * in words, which are 4 bytes each.
   *
   * @param lengths the lengths to set
   */
  public final void setLengths(int[] lengths) {
    this.lengths = lengths;
  }

  /**
   * Gets the integer number of shapes in the shapefile.
   *
   * @return the numShapes
   */
  public final int getNumShapes() {
    return numShapes;
  }

  /**
   * Sets the integer number of shapes in the shapefile.
   *
   * @param numShapes the numShapes to set
   */
  public final void setNumShapes(int numShapes) {
    this.numShapes = numShapes;
  }

  /**
   * Gets the binary reader that will parse bytes into doubles and integers.
   *
   * @return the shapeScanner
   */
  public final Scanner getShapeReader() {
    return shapeScanner;
  }

  /**
   * Sets the binary reader that will parse bytes into doubles and integers.
   *
   * @param shapeReader the shapeScanner to set
   */
  public final void setShapeReader(Scanner shapeReader) {
    this.shapeScanner = shapeReader;
  }

  /**
   * @return the projection
   */
  public final String getProjection() {
    return projection;
  }

  /**
   * @param projection the projection to set
   */
  public final void setProjection(String projection) {
    this.projection = projection;
  }

  /**
   * @return the name
   */
  public final String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public final void setName(String name) {
    this.name = name;
  }

  /**
   * @return the attributes
   */
  public final AttributeTableScanner getAttributes() {
    return attributes;
  }

  /**
   * @param attributes the attributes to set
   */
  public final void setAttributes(AttributeTableScanner attributes) {
    this.attributes = attributes;
  }

  //</editor-fold>
}
