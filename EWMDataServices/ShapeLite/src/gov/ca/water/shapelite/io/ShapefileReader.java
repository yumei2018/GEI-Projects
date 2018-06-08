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
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.ReadBytes;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.ShapefileHeader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * This class performs the business logic of reading shapes from a shapefile.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ShapefileReader {

  /**
   * A 2D envelope has 4 doubles.
   */
  private static final int SIZE_ENVELOPE_2D = 4;

  /**
   * The size to use for data buffers.
   */
  private static final int BUFFER_SIZE = 1024;

  /**
   * The size of the shp and shx header in bytes.
   */
  private static final int HEADER_BYTES = 100;

  /**
   * The size of a single shx file entry.
   */
  private static final int ENTRY_SIZE = 8;

  /**
   * The Z index of a coordinate.
   */
  private static final int Z = Coord.Index.Z;

  /**
   * THe M index of a coordinate.
   */
  private static final int M = Coord.Index.M;

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The attributes from the shapefile's dbf table.
   */
  private AttributeTableReader attributes;

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
  private Reader shapeReader;

  /**
   * The resulting list of shapes from the shapefile.
   */
  public List<Shape> shapes;

  /**
   * The raw byte values of the shape file.
   */
  private byte[] rawShpFile;

  /**
   * The raw byte values from the raw ShxFile.
   */
  private byte[] rawShxFile;

  /**
   * The coordinate reference system.
   */
  private String projection;
  /**
   * The String name.
   */
  private String name;

  /**
   * Logger for this class.
   */
  private static final Logger LOGGER = Logger.getLogger(
      ShapefileReader.class.getName());

  //</editor-fold>
  /**
   * Gets all the features for the shapefile.
   *
   * @return FeatureSet with features.
   * @throws FileNotFoundException if the file is not found.
   */
  public final FeatureSet getFeatures() throws FileNotFoundException {
    return getFeatures(0, numShapes);
  }

  /**
   * Gets the specified features, loading them into a feature set.
   *
   * @param start The integer start index of the features to grab.
   * @param numFeatures The integer number of features to read.
   * @return A FeatureSet made up of the specified features.
   * @throws FileNotFoundException if the file is not found.
   */
  public final FeatureSet getFeatures(int start, int numFeatures)
      throws FileNotFoundException {
    FeatureSet result = new FeatureSet();
    List<Shape> myShapes = getShapes(start, numFeatures);
    result.getFields().addAll(attributes.getFields());
    String[][] attText = attributes.getRows(start, numFeatures);
    for (int row = 0; row < myShapes.size(); row++) {
      Feature f = new Feature();
      f.setShape(myShapes.get(row));
      for (int iField = 0; iField < result.getFields().size(); iField++) {
        Field field = result.getFields().get(iField);
        f.getAttributes().put(field.getName(), attText[row][iField]);
      }
      f.setParent(result);
      result.getFeatures().add(f);
    }
    result.setName(name);
    result.setDefaultShapeType(header.getShapeType());
    result.setProjectionESRI(projection);
    return result;
  }

  /**
   * Gets all the shapes from the shapefile in a single list.
   *
   * @return The list of shapes.
   */
  public final List<Shape> getShapes() {
    return this.getShapes(0, numShapes);
  }

  /**
   * Rather than reading the entire shapefile, this reads from the specified
   * start index to the specified numShapes after that.
   *
   * @param start The start index of the first shape to return.
   * @param numShapes The number of shapes to return.
   * @return A List of shapes.
   */
  public final List<Shape> getShapes(int start, int numShapes) {
    shapeReader = new Reader(rawShpFile);
    List<Shape> result = new ArrayList<>();
    for (int i = start; i < numShapes; i++) {
      Shape shape = doReadShape(i);
      result.add(shape);
    }
    shapeReader.close();
    return result;
  }

  /**
   * Opens the specified filename.
   *
   * @param filename The filename to open.
   * @throws FileNotFoundException A FileNotFoundException is thrown if the file
   * is missing.
   * @throws IOException This is thrown if there was a problem reading the file.
   */
  public final void open(String filename) throws FileNotFoundException, IOException {
    if (filename.endsWith(".zip")) {
      this.openZip(filename);
      return;
    }
    this.setFilename(filename);
    String indexFile = filename.replace(".shp", ".shx");
    File f = new File(indexFile);
    if (!f.exists()) {
      throw new FileNotFoundException("The index file " + indexFile
          + " could not be found.");
    }
    rawShxFile = ReadBytes.readAll(new FileInputStream(indexFile));
    rawShpFile = ReadBytes.readAll(new FileInputStream(filename));
    header = new ShapefileHeader();
    header.read(rawShpFile);
    int p = filename.lastIndexOf(".");
    if (p > -1) {
      String first = filename.substring(0, p);
      name = new File(first).getName();
    } else {
      name = new File(filename).getName();
    }

    readIndex();
    attributes = new AttributeTableReader();
    String dbf = filename.replace(".shp", ".dbf");
    try {
      getAttributes().open(dbf);
    } catch (IllegalArgumentException ex) {
      LOGGER.log(Level.SEVERE, "Attributes could not be read.", ex);
    }
    String prjFilename = FileHelper.setExtension(filename, ".prj");
    File prjFile = new File(prjFilename);
    if (prjFile.exists()) {
      projection = FileHelper.readAll(prjFilename);
    }

  }

  /**
   * Opens a zipped shapefile with shp, shx and dbf. If a prj is available it
   * will be read as well.
   *
   * @param zipFile The Zipfile containing the shapefile contents.
   * @throws IOException exception if there was an error reading one of the file
   * elements.
   */
  private void openZip(String zipFile) throws IOException {
    /*
    ZipFile zf = new ZipFile(zipFile);
    Enumeration<? extends ZipEntry> entries = zf.entries();
    while (entries.hasMoreElements()) {
      ZipEntry entry = entries.nextElement();
      if (entry.getName().endsWith(".shp")) {
        rawShpFile = ReadBytes.readAll(zf.getInputStream(entry));
        name = FileHelper.getBaseName(entry.getName());
      }
      if (entry.getName().endsWith(".shx")) {
        rawShxFile = ReadBytes.readAll(zf.getInputStream(entry));
      }

      if (entry.getName().endsWith(".dbf")) {
        attributes = new AttributeTableReader();
        attributes.open(zf.getInputStream(entry));
      }
      if (entry.getName().endsWith(".prj")) {
        projection = FileHelper.readAll(zf.getInputStream(entry));
      }
    }
    if (rawShpFile != null) {
      header = new ShapefileHeader();
      header.read(rawShpFile);
      readIndex();
    }
    /**/
    this.openZip(new FileInputStream(new File(zipFile)));
  }

  /**
   * Opens a zipped shapefile as an inputstream with shp, shx and dbf. If a prj
   * is available it will be read as well. This creates a temporary file in the
   * process for ease of API use.   Closes the input stream if successful.
   *
   * @param zipFile The inputstream for Zipfile containing the shapefile
   * contents.
   * @throws IOException exception if there was an error reading one of the file
   * elements.
   */
  private void openZip(InputStream zipFile) throws IOException {
//    File tempFile = File.createTempFile("temp", "zip");
//    tempFile.deleteOnExit();
//    try (FileOutputStream out = new FileOutputStream(tempFile)) {
//      int read;
//      byte[] bytes = new byte[BUFFER_SIZE];
//
//      while ((read = zipFile.read(bytes)) != -1) {
//        out.write(bytes, 0, read);
//      }
    try (ZipInputStream zf = new ZipInputStream(zipFile)) {
//      ZipFile zf = new ZipFile(tempFile);
//      Enumeration<? extends ZipEntry> entries = zf.entries();
//      while (entries.hasMoreElements()) {
//        ZipEntry entry = entries.nextElement();
      ZipEntry entry = null;
      while ((entry = zf.getNextEntry()) != null) {
        if (entry.getName().endsWith(".shp")) {
          rawShpFile = ReadBytes.readAll(zf);//.getInputStream(entry));
          name = entry.getName();
        }
        if (entry.getName().endsWith(".shx")) {
          rawShxFile = ReadBytes.readAll(zf);//.getInputStream(entry));
        }

        if (entry.getName().endsWith(".dbf")) {
          attributes = new AttributeTableReader();
          attributes.open(zf);//.getInputStream(entry));
        }
        if (entry.getName().endsWith(".prj")) {
          projection = FileHelper.readAll(zf);//.getInputStream(entry));
        }
      }
      if (rawShpFile != null) {
        header = new ShapefileHeader();
        header.read(rawShpFile);
        readIndex();
      }
    }
    catch(Exception ex) {
      throw new IllegalStateException(ex.getMessage());
    }
    zipFile.close();
  }

  /**
   * Reads the text from the associated projection.
   *
   * @param shapefile The string file. This can end in .shp or .prj.
   * @return The optional string text from the projection file, which can be
   * empty if there was no text or the file was missing.
   * @throws IOException If there was an error reading the file.
   */
  public final Optional<String> readProjection(String shapefile) throws IOException {
    String result = null;
    String prjFilename = FileHelper.setExtension(shapefile, ".prj");
    File prjFile = new File(prjFilename);

    if (prjFile.exists()) {
      result = FileHelper.readAll(prjFilename);
      if (result.isEmpty()) {
        result = null;
      }
    }
    return Optional.ofNullable(result);
  }

  /**
   * This version of open will attempt to open the various embedded resources,
   * assuming that the shx, dbf and prj resource files have been stored in the
   * same package as the specified shpPath.
   * <p>
   * Example Usage 1:   new ShapefileReader().open(GISResources.class, "ShapeFileBaseName.shp");
   * </p>
   * <p>
   * Example Usage 2:   new ShapefileReader().open(GISResources.class, "childpath/ShapeFileBaseName.shp");
   * </p>
   * @param clazz The class to use to locate the embedded resources.
   * @param shpPath The string path of the embedded resource, either relative to
   * the package containing clazz or absolute.
   * @throws IOException if there was an error reading the streams.
   */
  public final void open(Class<?> clazz, String shpPath) throws IOException {
    InputStream shp = clazz.getResourceAsStream(shpPath);
    String shxPath = ShapefileWriter.setExtension(shpPath, ".shx");
    InputStream shx = clazz.getResourceAsStream(shxPath);
    String dbfPath = ShapefileWriter.setExtension(shpPath, ".dbf");
    InputStream dbf = clazz.getResourceAsStream(dbfPath);
    String prjPath = ShapefileWriter.setExtension(shpPath, ".prj");
    InputStream prj = clazz.getResourceAsStream(prjPath);
    open(shp, shx, dbf, prj);

  }

  /**
   * Opens the specified streams as if they were files on disk.
   *
   * @param shapefile The shapefile stream.
   * @param shx The index file stream.
   * @param dbf The attribute dbf file stream.
   * @throws IOException An IO Exception will be thrown if there is a problem
   * reading the streams.
   */
  public final void open(InputStream shapefile, InputStream shx, InputStream dbf)
      throws IOException {
    rawShpFile = ReadBytes.readAll(shapefile);
    rawShxFile = ReadBytes.readAll(shx);
    header = new ShapefileHeader();
    header.read(rawShpFile);
    readIndex();
    setAttributes(new AttributeTableReader());
    if (dbf != null) {
      try {
        getAttributes().open(dbf);
      } catch (IllegalArgumentException ex) {
        LOGGER.log(Level.SEVERE, "Attributes could not be read.", ex);
      }
    }
  }

  /**
   * Opens the specified streams as if they were files on disk.
   *
   * @param zipfile The zipped shapefile stream with shp, shx, and dbf files.
   * @throws IOException An IO Exception will be thrown if there is a problem
   * reading the streams.
   */
  public final void open(InputStream zipfile) throws IOException {
    this.openZip(zipfile);
  }

  /**
   * Opens using the specified shp, shx, dbf, prj streams.
   *
   * @param shapefile The .shp file input stream
   * @param shx the .shx input stream
   * @param dbf the .dbf input stream
   * @param prj the .prj input stream
   * @throws IOException If there was an error reading a required stream.
   */
  public final void open(InputStream shapefile, InputStream shx,
      InputStream dbf, InputStream prj) throws IOException {
    open(shapefile, shx, dbf);
    if (prj != null) {
      java.util.Scanner s = new java.util.Scanner(prj).useDelimiter("\\A");
      if (s.hasNext()) {
        projection = s.next();
      }
    }

  }

  /**
   * Reads the offset and length values from the index file. These are stored in
   * memory, even if the raw data points for the shapefile itself are not.
   */
  private void readIndex() {
    Reader r = new Reader(rawShxFile);

    numShapes = (rawShxFile.length - HEADER_BYTES) / ENTRY_SIZE;
    lengths = new int[numShapes];
    offsets = new int[numShapes];
    r.advance(HEADER_BYTES);

    for (int i = 0; i < numShapes; i++) {
      offsets[i] = r.readBigInt();
      lengths[i] = r.readBigInt();
    }
  }

  /**
   * Reads the shape wtih the specified index value from the shapefile.
   *
   * @param index The integer index.
   * @return The Shape.
   */
  public final Shape readShape(int index) {
    shapeReader = new Reader(rawShpFile);
    Shape result = doReadShape(index);
    shapeReader.close();
    return result;
  }

  /**
   * This is the actual internal business logic to read a single shape.
   *
   * @param index The integer index value of the shape to read.
   * @return The Shape at the specified index.
   */
  private Shape doReadShape(int index) {
    shapeReader.advanceTo(offsets[index] * 2);

    int fid = shapeReader.readBigInt();
    int length = shapeReader.readBigInt();
    int shapetype = shapeReader.readInt();
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
    return result;
  }

  /**
   * Reads point data from the shapeReader into the specified shape.
   *
   * @param result The Shape to populate with data from the file.
   * @throws IllegalArgumentException if result is null.
   */
  private void readPoint(Shape result) {
    if (result == null) {
      throw new IllegalArgumentException("result cannot be null.");
    }
    result.setX(shapeReader.readDouble());
    result.setY(shapeReader.readDouble());
    result.calculateBounds();
  }

  /**
   * Reads pointM data from the shapeReader into the specified shape.
   *
   * @param result The Shape to populate with data from the file.
   * @throws IllegalArgumentException if result is null.
   */
  private void readPointM(Shape result) {
    if (result == null) {
      throw new IllegalArgumentException("Parameter result cannot be null.");
    }
    result.setX(shapeReader.readDouble());
    result.setY(shapeReader.readDouble());
    result.setM(shapeReader.readDouble());
    result.calculateBounds();
  }

  /**
   * Reads point z data from the shapeReader into the specified shape.
   *
   * @param result The Shape to populate with data from the file.
   * @throws IllegalArgumentException if result is null.
   */
  private void readPointZ(Shape result) {
    if (result == null) {
      throw new IllegalArgumentException("Parameter result cannot be null.");
    }
    result.setX(shapeReader.readDouble());
    result.setY(shapeReader.readDouble());
    result.setZ(shapeReader.readDouble());
    result.setM(shapeReader.readDouble());
    result.calculateBounds();
  }

  /**
   * Reads point data from the shapeReader into the specified shape.
   *
   * @param result The Shape to populate with data from the file.
   * @throws IllegalArgumentException if result is null.
   */
  private void readMultiPoint(Shape result) {
    if (result == null) {
      throw new IllegalArgumentException("Parameter result cannot be null.");
    }
    Envelope bounds = new DefaultEnvelope(shapeReader.readDouble(SIZE_ENVELOPE_2D));
    result.getEnvelope().copyProperties(bounds);
    int numPoints = shapeReader.readInt();
    Part part = new Part();
    for (int i = 0; i < numPoints; i++) {
      double x = shapeReader.readDouble();
      double y = shapeReader.readDouble();
      Coord pt = new CoordXY(x, y);
      part.getCoordinates().add(pt);
    }
    result.getParts().add(part);
  }

  /**
   * Reads point data from the shapeReader into the specified shape.
   *
   * @param result The Shape to populate with data from the file.
   * @throws IllegalArgumentException if result is null.
   */
  private void readMultiPointM(Shape result) {
    if (result == null) {
      throw new IllegalArgumentException("Parameter result cannot be null.");
    }
    double[] vals = shapeReader.readDouble(SIZE_ENVELOPE_2D);
    Envelope bounds = DefaultEnvelope.getM(vals);
    result.getEnvelope().copyProperties(bounds);
    int numPoints = shapeReader.readInt();
    Part part = new Part();
    for (int i = 0; i < numPoints; i++) {
      double x = shapeReader.readDouble();
      double y = shapeReader.readDouble();
      Coord pt = new CoordM(x, y, 0);
      part.getCoordinates().add(pt);
    }
    result.getParts().add(part);
    if (result.getContentLength() > (20 + numPoints * 8)) {
      result.getEnvelope().getMin().set(M, shapeReader.readDouble());
      result.getEnvelope().getMax().set(M, shapeReader.readDouble());
      result.setArrayM(shapeReader.readDouble(numPoints));
    }

  }

  /**
   * Reads point Z data from the shapeReader into the specified shape.
   *
   * @param result The Shape to populate with data from the file.
   * @throws IllegalArgumentException if result is null.
   */
  private void readMultiPointZ(Shape result) {
    if (result == null) {
      throw new IllegalArgumentException("Parameter result cannot be null.");
    }
    double[] vals = shapeReader.readDouble(SIZE_ENVELOPE_2D);
    Envelope bounds = DefaultEnvelope.getZ(vals);
    result.getEnvelope().copyProperties(bounds);
    int numPoints = shapeReader.readInt();
    Part part = new Part();
    for (int i = 0; i < numPoints; i++) {
      double x = shapeReader.readDouble();
      double y = shapeReader.readDouble();
      Coord pt = new CoordZ(x, y, 0, 0);
      part.getCoordinates().add(pt);
    }
    result.getParts().add(part);
    result.getEnvelope().getMin().set(Z, shapeReader.readDouble());
    result.getEnvelope().getMax().set(Z, shapeReader.readDouble());
    result.setArrayZ(shapeReader.readDouble(numPoints));
    if (result.getContentLength() > (28 + numPoints * 12)) {
      result.getEnvelope().getMin().set(M, shapeReader.readDouble());
      result.getEnvelope().getMax().set(M, shapeReader.readDouble());
      result.setArrayM(shapeReader.readDouble(numPoints));
    }

  }

  /**
   * Reads polyline data from the shapeReader into the specified shape.
   *
   * @param result The Shape to populate with data from the file.
   * @throws IllegalArgumentException if result is null.
   */
  private void readPolyline(Shape result) {
    if (result == null) {
      throw new IllegalArgumentException("Parameter result cannot be null.");
    }
    Envelope bounds = new DefaultEnvelope(shapeReader.readDouble(SIZE_ENVELOPE_2D));
    result.getEnvelope().copyProperties(bounds);
    int numParts = shapeReader.readInt();
    if (numParts == 0) {
      result.setShapeType(ShapeType.NullShape);
      return;
    }
    int numPoints = shapeReader.readInt();
    int[] rawParts = shapeReader.readInt(numParts);
    if (rawParts == null) {
      return;
    }
    int end = numPoints;
    double[] points = shapeReader.readDouble(numPoints * 2);
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
   * Reads polyline M data from the shapeReader into the specified shape.
   *
   * @param result The Shape to populate with data from the file.
   * @throws IllegalArgumentException if result is null.
   */
  private void readPolylineM(Shape result) {
    if (result == null) {
      throw new IllegalArgumentException("Parameter result cannot be null.");
    }
    double[] vals = shapeReader.readDouble(SIZE_ENVELOPE_2D);
    Envelope bounds = DefaultEnvelope.getM(vals);
    result.getEnvelope().copyProperties(bounds);
    int numParts = shapeReader.readInt();
    int numPoints = shapeReader.readInt();
    int[] rawParts = shapeReader.readInt(numParts);
    int end = numPoints;
    double[] points = shapeReader.readDouble(numPoints * 2);
    for (int prt = rawParts.length - 1; prt >= 0; prt--) {
      Part part = new Part();
      for (int i = rawParts[prt]; i < end; i++) {
        part.getCoordinates().add(new CoordM(points[i * 2], points[i * 2 + 1], 0));
      }
      result.getParts().add(0, part);
      end = rawParts[prt];
    }
    if (result.getContentLength() > 22 + 2 * numParts + 8 * numPoints) {
      result.getEnvelope().getMin().set(M, shapeReader.readDouble());
      result.getEnvelope().getMax().set(M, shapeReader.readDouble());
      result.setArrayM(shapeReader.readDouble(numPoints));
    }
  }

  /**
   * Reads polyline z data from the shapeReader into the specified shape.
   *
   * @param result The Shape to populate with data from the file.
   * @throws IllegalArgumentException if result is null.
   */
  private void readPolylineZ(Shape result) {
    if (result == null) {
      throw new IllegalArgumentException("Parameter result cannot be null.");
    }
    double[] vals = shapeReader.readDouble(SIZE_ENVELOPE_2D);
    Envelope bounds = DefaultEnvelope.getZ(vals);
    result.getEnvelope().copyProperties(bounds);
    int numParts = shapeReader.readInt();
    int numPoints = shapeReader.readInt();
    int[] rawParts = shapeReader.readInt(numParts);
    int end = numPoints;
    double[] points = shapeReader.readDouble(numPoints * 2);
    for (int prt = rawParts.length - 1; prt >= 0; prt--) {
      Part part = new Part();
      for (int i = rawParts[prt]; i < end; i++) {
        CoordZ coord = new CoordZ(points[i * 2], points[i * 2 + 1], 0, 0);
        part.getCoordinates().add(coord);
      }
      result.getParts().add(0, part);
      end = rawParts[prt];
    }
    result.getEnvelope().getMin().set(Z, shapeReader.readDouble());
    result.getEnvelope().getMax().set(Z, shapeReader.readDouble());
    result.setArrayZ(shapeReader.readDouble(numPoints));
    if (result.getContentLength() > 22 + 2 * numParts + 8 * numPoints
        + 8 + 4 * numPoints) {
      result.getEnvelope().getMin().set(M, shapeReader.readDouble());
      result.getEnvelope().getMax().set(M, shapeReader.readDouble());
      result.setArrayM(shapeReader.readDouble(numPoints));
    }
  }

  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the attributes from the shapefile's dbf table.
   *
   * @return the attributes
   */
  public final AttributeTableReader getAttributes() {
    return attributes;
  }

  /**
   * Sets the attributes from the shapefile's dbf table.
   *
   * @param attributes the attributes to set
   */
  public final void setAttributes(AttributeTableReader attributes) {
    this.attributes = attributes;
  }

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
   * @return the shapeReader
   */
  public final Reader getShapeReader() {
    return shapeReader;
  }

  /**
   * Sets the binary reader that will parse bytes into doubles and integers.
   *
   * @param shapeReader the shapeReader to set
   */
  public final void setShapeReader(Reader shapeReader) {
    this.shapeReader = shapeReader;
  }

  //</editor-fold>
  /**
   * Gets the coordinate system for this shapefile.
   *
   * @return the projection
   */
  public final String getProjection() {
    return projection;
  }

  /**
   * Sets the coordinate system.
   *
   * @param projection the projection to set
   */
  public final void setProjection(String projection) {
    this.projection = projection;
  }

  /**
   * Gets the name of the shapefile being read.
   *
   * @return the name
   */
  public final String getName() {
    return name;
  }

  /**
   * Sets the name of the shapefile.
   *
   * @param name the name to set
   */
  public final void setName(String name) {
    this.name = name;
  }

  /**
   * Checks whether all files in a shapefile exists including: .shp, .dbf, .prj,
   * .shx.
   *
   * @param shapeFile The base shape file to inspect (with .shp extension).
   * @return boolean, true if the shp, dbf, prj and shx files all exist.
   */
  public static boolean exists(File shapeFile) {
    String geoFilename = shapeFile.getAbsolutePath();
    String ext = FileHelper.getExtension(geoFilename).orElse(null);
    if ("shp".equals(ext)) {
      geoFilename = FileHelper.removeExtension(geoFilename);
    }
    boolean shpExists = new File(geoFilename + ".shp").exists();
    boolean dbfExists = new File(geoFilename + ".dbf").exists();
    boolean prjExists = new File(geoFilename + ".prj").exists();
    boolean shxExists = new File(geoFilename + ".shx").exists();
    return shpExists && dbfExists && prjExists && shxExists;
  }

}
