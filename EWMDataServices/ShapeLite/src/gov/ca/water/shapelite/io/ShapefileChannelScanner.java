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
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.NonNull;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.ReadBytes;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.ShapefileHeader;
import gov.ca.water.shapelite.coordinate.CoordM;
import gov.ca.water.shapelite.coordinate.CoordZ;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
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
public class ShapefileChannelScanner implements AutoCloseable {

  /**
   * A 2D envelope has 4 doubles.
   */
  private static final int SIZE_ENVELOPE_2D = 4;

  /**
   * Gets the M index for the coordinate.
   */
  private static final int M = Coord.Index.M;

  /**
   * Gets the Z index for the coordinate.
   */
  private static final int Z = Coord.Index.Z;

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The string filename to read.
   */
  private String filename;

  /**
   * A ShapefileHeader class representing the header content for this shapefile.
   */
  public ShapefileHeader header;

  /**
   * The integer lengths of the shapes from the index file. The lengths are in
   * words, which are 4 bytes each.
   */
  private int[] lengths;

  /**
   * The integer offsets of the shapes from the offset file.
   */
  public int[] offsets;

  /**
   * The integer number of shapes in the shapefile.
   */
  private int numShapes;

  /**
   * The binary reader that will parse bytes into doubles and integers.
   */
  private ChannelScanner shapeScanner;

  /**
   * The resulting list of shapes from the shapefile.
   */
  public List<Shape> shapes;

  /**
   * The raw byte values from the raw ShxFile.
   */
  byte[] rawShxFile;

  private String projection;
  private String name;

  private int currentShape;

  Envelope[] envelopes;

  private boolean allOpen;

  private static final Logger LOGGER = Logger.getLogger(ShapefileChannelScanner.class.getName());

  //</editor-fold>
  /**
   * Opens the shapefile for scanning, expecting the other shapefile helper
   * files to exist in the same directory. This will leave the underlying file
   * open for further random access reading until this is explicitly closed.
   * Leaving a channel scanner open is a bad idea however, as it will cause
   * memory exceptions if any other channel scanner is used at the same time.
   *
   *
   * @param filename
   * @throws java.io.FileNotFoundException
   * @throws ShapefileScannerException
   */
  public ShapefileChannelScanner(String filename) throws FileNotFoundException, IOException, ShapefileScannerException {

    open(filename);
  }

  /**
   * Gets all the shapes from the shapefile in a single list.
   *
   * @return The list of shapes.
   * @throws ShapefileScannerException
   */
  public List<Shape> getShapes() throws ShapefileScannerException {
    return this.getShapes(0, numShapes);
  }

  /**
   * Rather than reading the entire shapefile, this reads from the specified
   * start index to the specified numShapes after that.
   *
   * @param start The start index of the first shape to return.
   * @param numShapes The number of shapes to return.
   * @return A List of shapes.
   * @throws ShapefileScannerException
   */
  public List<Shape> getShapes(int start, int numShapes) throws ShapefileScannerException {
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
  public void close() {

    if (shapeScanner != null) {
      shapeScanner.close();
      allOpen = false;
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
   * @throws IOException
   * @throws gov.ca.water.shapelite.io.ShapefileScannerException
   */
  private void open(String shapefile) throws IOException, ShapefileScannerException {
    shapeScanner = new ChannelScanner(shapefile);
    allOpen = true; // the channel scanner is opened and the whole file has been mapped.
    filename = shapefile;
    InputStream shx = new FileInputStream(setExtension(filename, ".shx"));
    rawShxFile = ReadBytes.readAll(shx);
    readIndex();
    header = new ShapefileHeader();
    header.read(shapeScanner);
    currentShape = 0;
    envelopes = new Envelope[numShapes];
    for (int i = 0; i < numShapes; i++) {
      try {
        envelopes[i] = readEnvelopeXY(i);
      } catch (Exception ex) {
        boolean stop = true;
      }
    }

    this.projection = new Scanner(new File(setExtension(filename, ".prj"))).useDelimiter("\\Z").next();
  }

  /**
   * Reads the offset and length values from the index file. These are stored in
   * memory, even if the raw data points for the shapefile itself are not.
   */
  private void readIndex() {
    Reader r = new Reader(rawShxFile);

    numShapes = (rawShxFile.length - 100) / 8;
    lengths = new int[numShapes];
    offsets = new int[numShapes];
    r.advance(100);

    for (int i = 0; i < numShapes; i++) {
      offsets[i] = r.readBigInt();
      lengths[i] = r.readBigInt();
    }
  }

  /**
   * This method will set up the channel scanner to read a buffer for a single
   * shape. This should not be used for processes that need to scan the whole
   * file.
   *
   * @param index
   * @throws java.io.IOException
   */
  protected void mapShape(int index) throws IOException {
    long offset = offsets[index] * 2;
    long len = lengths[index] * 2 + 8; // lengths don't include shape header
    shapeScanner.map(offset, len);
  }

  /**
   * Reads only the XY envelope from the file. This assumes that the mapping has
   * taken place for the whole file.
   *
   * @param index
   * @return
   * @throws java.io.IOException
   */
  protected Envelope readEnvelopeXY(int index) throws IOException {
    long offset = offsets[index] * 2 + 8;
    Envelope result;
    if (this.allOpen) {

      shapeScanner.advanceTo(offset);
      result = readCurrentEnvelopeXY();
    } else {
      mapShape(index);
      shapeScanner.advanceTo(8);
      result = readCurrentEnvelopeXY();
      shapeScanner.close();
    }
    return result;
  }

  /**
   * Reads an envelope, assuming that the buffer position has already been set,
   * and the mapping and positioning has already been taken care of.
   *
   * @return The Envelope read.
   */
  protected final Envelope readCurrentEnvelopeXY() {

    Envelope env = new DefaultEnvelope();
    ShapeType shapetype = ShapeType.find(shapeScanner.readInt());
    if (shapetype == ShapeType.NullShape || shapetype == ShapeType.MultiPatch) {
      return env;
    }
    if (shapetype == ShapeType.Point || shapetype == ShapeType.PointM
        || shapetype == ShapeType.PointZ) {
      double x = shapeScanner.readDouble();
      env.getMax().setX(x);
      env.getMin().setX(x);
      double y = shapeScanner.readDouble();
      env.getMax().setY(y);
      env.getMin().setY(y);
      return env;
    }
    Envelope result = new DefaultEnvelope(shapeScanner.readDouble(4));

    return result;
  }

  /**
   * Gets the envelope for the specified index. If the envelope has not already
   * been cached, then this will attempt to read the envelope.
   *
   * @param index
   * @return
   * @throws java.io.IOException
   */
  public Envelope getEnvelopeXY(int index) throws IOException {

    if (envelopes[index] == null) {

      envelopes[index] = readEnvelopeXY(index);
    }
    return envelopes[index];

  }

  /**
   * Scans through the envelopes looking for the closest envelope, and if the
   * coord is inside more than one envelope, it tests those shapes in order to
   * return the closest shape.
   *
   * @param coord The Coordinate to use to find the closest shape for.
   * @return Optionally returns a shape that is closest to the coordinate.
   * @throws IllegalArgumentException if coord is null.
   */
  public Optional<Shape> getClosestShape(@NonNull Coord coord) {
    if (coord == null) {
      throw new IllegalArgumentException("Coord coord cannot be null.");
    }
    Integer closestIndex = null;
    Double minDist = null;
    List<Integer> intersecting = new ArrayList<>();
    for (int i = 0; i < numShapes; i++) {
      Envelope env = null;
      try {
        env = getEnvelopeXY(i);
      } catch (IOException ex) {
        Logger.getLogger(ShapefileChannelScanner.class.getName()).log(
            Level.SEVERE, ex.getMessage(), ex);
      }
      if (env == null) {
        boolean stop = true;
      } else {
        double d = env.distance(coord);
        if (d == 0) {
          intersecting.add(i);
        }
        if (intersecting.isEmpty()) {
          if (minDist == null || d < minDist) {
            minDist = d;
            closestIndex = i;
          }
        }
      }
    }
    if (intersecting.isEmpty()) {
      if (closestIndex == null) {
        return Optional.empty();
      }
      try {
        return Optional.of(readShape(closestIndex));
      } catch (ShapefileScannerException ex) {
        Logger.getLogger(ShapefileChannelScanner.class.getName()).log(
            Level.SEVERE, ex.getMessage(), ex);
      }
    }
    if (intersecting.size() == 1) {
      try {
        return Optional.of(readShape(intersecting.get(0)));
      } catch (ShapefileScannerException ex) {
        Logger.getLogger(ShapefileChannelScanner.class.getName()).log(
            Level.SEVERE, ex.getMessage(), ex);
      }
    }

    Shape closest = null;
    Double closestDist = null;
    for (Integer index : intersecting) {
      try {
        Shape shp = readShape(index);
        Optional<Double> dist = shp.distance(coord);
        if (dist.isPresent()) {
          if (closestDist == null || dist.get() < closestDist) {
            closestDist = dist.get();
            closest = shp;
          }
        }

      } catch (ShapefileScannerException ex) {
        Logger.getLogger(ShapefileChannelScanner.class.getName()).log(
            Level.SEVERE, ex.getMessage(), ex);
      }

    }
    return Optional.ofNullable(closest);
  }

  /**
   * Reads the shape wtih the specified index value from the shapefile.
   *
   * @param index
   * @return
   * @throws ShapefileScannerException
   */
  public Shape readShape(int index) throws ShapefileScannerException {
    Shape result = doReadShape(index);
    return result;
  }

  /**
   * This is the actual internal business logic to read a single shape. If
   * 'allOpen' is true, this simply positions the scanner and reads. Otherwise
   * this will map just the content necessary to read the shape, read the shape,
   * and then close the scanner.
   *
   * @param index The integer index value of the shape to read.
   * @return The Shape at the specified index.
   */
  private Shape doReadShape(int index) throws ShapefileScannerException {
    if (allOpen) {
      shapeScanner.advanceTo(offsets[index] * 2);
    } else {
      try {
        mapShape(index);
      } catch (IOException ex) {
        throw new ShapefileScannerException(ex);
      }
    }

    //shapeScanner.buffer = null;
    int fid = shapeScanner.readBigInt();
    int len = shapeScanner.readBigInt();
    int shapetype = shapeScanner.readInt();
    Shape result = new Shape(ShapeType.find(shapetype));
    result.setFid(fid);
    result.setContentLength(len);
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
    }
    if (result.getShapeType() == ShapeType.Polygon
        || result.getShapeType() == ShapeType.PolygonM
        || result.getShapeType() == ShapeType.PolygonZ) {
      for (Part prt : result.getParts()) {
        prt.setClosed(true);
      }
    }
    if (allOpen) {
      currentShape = index + 1;
    } else {
      shapeScanner.close();
    }

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
    if (result == null) {
      throw new IllegalArgumentException("Parameter result cannot be null.");
    }
    Envelope bounds = new DefaultEnvelope(shapeScanner.readDouble(SIZE_ENVELOPE_2D));
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
    if (result == null) {
      throw new IllegalArgumentException("Parameter result cannot be null.");
    }
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
    if (result.getContentLength() > (20 + numPoints * 8)) {
      result.getEnvelope().getMin().set(M, shapeScanner.readDouble());
      result.getEnvelope().getMax().set(M, shapeScanner.readDouble());
      result.setArrayM(shapeScanner.readDouble(numPoints));
    }

  }

  /**
   * Reads point Z data from the shapeScanner into the specified shape.
   *
   * @param result The Shape to populate with data from the file.
   * @throws IllegalArgumentException if result is null.
   */
  private void readMultiPointZ(Shape result) {
    if (result == null) {
      throw new IllegalArgumentException("Parameter result cannot be null.");
    }
    double[] vals = shapeScanner.readDouble(SIZE_ENVELOPE_2D);
    Envelope bounds = DefaultEnvelope.getZ(vals);
    result.getEnvelope().copyProperties(bounds);
    int numPoints = shapeScanner.readInt();
    Part part = new Part();
    for (int i = 0; i < numPoints; i++) {
      double x = shapeScanner.readDouble();
      double y = shapeScanner.readDouble();
      Coord pt = new CoordZ(x, y, 0);
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
   * @throws IllegalArgumentException if result is null.
   */
  private void readPolyline(Shape result) {
    if (result == null) {
      throw new IllegalArgumentException("Parameter result cannot be null.");
    }
    Envelope bounds = new DefaultEnvelope(shapeScanner.readDouble(SIZE_ENVELOPE_2D));
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
   * @throws IllegalArgumentException if result is null.
   */
  private void readPolylineM(Shape result) {
    if (result == null) {
      throw new IllegalArgumentException("Parameter result cannot be null.");
    }
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
    if (result.getContentLength() > 22 + 2 * numParts + 8 * numPoints) {
      result.getEnvelope().getMin().set(M, shapeScanner.readDouble());
      result.getEnvelope().getMax().set(M, shapeScanner.readDouble());
      result.setArrayM(shapeScanner.readDouble(numPoints));
    }
  }

  /**
   * Reads polyline z data from the shapeScanner into the specified shape.
   *
   * @param result The Shape to populate with data from the file.
   * @throws IllegalArgumentException if result is null.
   */
  private void readPolylineZ(Shape result) {
    if (result == null) {
      throw new IllegalArgumentException("Parameter result cannot be null.");
    }
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
        part.getCoordinates().add(new CoordZ(points[i * 2], points[i * 2 + 1], 0));
      }
      result.getParts().add(0, part);
      end = rawParts[prt];
    }
    result.getEnvelope().getMin().set(Z, shapeScanner.readDouble());
    result.getEnvelope().getMax().set(Z, shapeScanner.readDouble());
    result.setArrayZ(shapeScanner.readDouble(numPoints));
    if (result.getContentLength() > 22 + 2 * numParts + 8 * numPoints + 8 + 4 * numPoints) {
      result.getEnvelope().getMin().set(M, shapeScanner.readDouble());
      result.getEnvelope().getMax().set(M, shapeScanner.readDouble());
      result.setArrayM(shapeScanner.readDouble(numPoints));
    }
  }

  /**
   * This method is specific to shapefiles and allows quickly changing one
   * extension to another. If the filename starts without one of the shapefile
   * extensions, then, this will append the specified extension to the end of
   * the filename.
   *
   * @param filename The full path of the shapefile. This can end in any of the
   * valid shapefile extensions or no extension at all.
   * @param extension The extension with leading period like ".shp"
   * @return the String shapefile with extension.
   */
  public static String setExtension(String filename, String extension) {
    if (filename.endsWith(".shp") || filename.endsWith(".shx") || filename.endsWith(".dbf")) {
      filename = filename.substring(0, filename.lastIndexOf("."));
    }
    if(!extension.startsWith(".")){
      extension = "." + extension;
    }
    return filename.concat(extension);

  }

  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the string filename to read.
   *
   * @return the filename
   */
  public String getFilename() {
    return filename;
  }

  /**
   * Sets the string filename to read.
   *
   * @param filename the filename to set
   */
  public void setFilename(String filename) {
    this.filename = filename;
  }

  /**
   * Gets the integer lengths of the shapes from the index file. The lengths are
   * in words, which are 4 bytes each.
   *
   * @return the lengths
   */
  public int[] getLengths() {
    return lengths;
  }

  /**
   * Sets the integer lengths of the shapes from the index file. The lengths are
   * in words, which are 4 bytes each.
   *
   * @param lengths the lengths to set
   */
  public void setLengths(int[] lengths) {
    this.lengths = lengths;
  }

  /**
   * Gets the integer number of shapes in the shapefile.
   *
   * @return the numShapes
   */
  public int getNumShapes() {
    return numShapes;
  }

  /**
   * Sets the integer number of shapes in the shapefile.
   *
   * @param numShapes the numShapes to set
   */
  public void setNumShapes(int numShapes) {
    this.numShapes = numShapes;
  }

  /**
   * Gets the binary reader that will parse bytes into doubles and integers.
   *
   * @return the shapeScanner
   */
  public ChannelScanner getShapeReader() {
    return shapeScanner;
  }

  /**
   * Sets the binary reader that will parse bytes into doubles and integers.
   *
   * @param shapeReader the shapeScanner to set
   */
  public void setShapeReader(ChannelScanner shapeReader) {
    this.shapeScanner = shapeReader;
  }

  //</editor-fold>
  /**
   * @return the projection
   */
  public String getProjection() {
    return projection;
  }

  /**
   * @param projection the projection to set
   */
  public void setProjection(String projection) {
    this.projection = projection;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the allOpen
   */
  public boolean isAllOpen() {
    return allOpen;
  }

  /**
   * @param allOpen the allOpen to set
   */
  public void setAllOpen(boolean allOpen) {
    this.allOpen = allOpen;
  }

}
