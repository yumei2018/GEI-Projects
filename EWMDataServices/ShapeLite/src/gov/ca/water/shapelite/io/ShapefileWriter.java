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
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.ShapefileException;
import gov.ca.water.shapelite.ShapefileHeader;
import gov.ca.water.shapelite.progress.Progress;
import gov.ca.water.shapelite.progress.ProgressCountableCancellable;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * This class only writes the vector content (SHP, SHX) files. To write attribute content, use the AttributeTableWriter.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ShapefileWriter {

  /**
   * Gets the logger.
   */
  private static final Logger LOGGER = Logger.getLogger("ShapefileWriter");

  /**
   * The Z index of a coordinate.
   */
  private static final int Z = Coord.Index.Z;

  /**
   * THe M index of a coordinate.
   */
  private static final int M = Coord.Index.M;

  /**
   * Gets or sets an optional progress indicator.
   */
  private ProgressCountableCancellable progress;

  /**
   * This method saves the specified featureSet.
   *
   * @param featureSet the FeatureSet to save.
   * @param shapefileName the string filename ending in .shp, or .zip. If the file ends with .zip, then the entire file
   * will be written as a zip stream.
   * @throws ShapefileException If there was an IO error.
   */
  public void write(FeatureSet featureSet, String shapefileName)
          throws ShapefileException {
    validate(featureSet);
    if (shapefileName.endsWith(".zip")) {
      try (FileOutputStream stream = new FileOutputStream(shapefileName)) {
        write(featureSet, stream);
      } catch (FileNotFoundException ex) {
        Logger.getLogger(ShapefileWriter.class.getName()).log(Level.SEVERE, null, ex);
      } catch (IOException ex) {
        Logger.getLogger(ShapefileWriter.class.getName()).log(Level.SEVERE, null, ex);
      }
      return;
    }

    FileOutputStream shpStream = null;
    FileOutputStream shxStream = null;
    FileOutputStream dbfStream = null;
    try {
      String shp = setExtension(shapefileName, ".shp");
      String shx = setExtension(shapefileName, ".shx");
      String dbf = setExtension(shapefileName, ".dbf");
      String prj = setExtension(shapefileName, ".prj");
      File shpFile = new File(shp);
      if (shpFile.exists()) {
        shpFile.delete();
      }
      File shxFile = new File(shx);
      if (shxFile.exists()) {
        shxFile.delete();
      }
      File dbfFile = new File(dbf);
      if (dbfFile.exists()) {
        dbfFile.delete();
      }
      File prjFile = new File(prj);
      if (prjFile.exists()) {
        prjFile.delete();
      }

      if (!shpFile.getParentFile().exists()) {
        shpFile.getParentFile().mkdirs();
      }

      shpStream = new FileOutputStream(shp);
      shxStream = new FileOutputStream(shx);
      dbfStream = new FileOutputStream(dbf);

      // WRITE TO STREAMS
      write(featureSet, shpStream, shxStream, dbfStream);

      if (featureSet.getProjectionESRI() != null) {
        try (OutputStream prjStream = new FileOutputStream(prj)) {
          writePrjFile(featureSet, prjStream);
        }
      }

    } catch (FileNotFoundException ex) {
      Logger.getLogger(ShapefileWriter.class.getName()).log(
              Level.SEVERE, ex.getMessage(), ex);
    } catch (IOException ex) {
      Logger.getLogger(ShapefileWriter.class.getName()).log(
              Level.SEVERE, ex.getMessage(), ex);
    } finally {
      try {
        if (shpStream != null) {
          shpStream.flush();
          shpStream.close();
        }
      } catch (IOException ex) {
        Logger.getLogger(ShapefileWriter.class.getName()).log(
                Level.SEVERE, ex.getMessage(), ex);
      }
      try {
        if (shxStream != null) {
          shxStream.flush();
          shxStream.close();
        }
      } catch (IOException ex) {
        Logger.getLogger(ShapefileWriter.class.getName()).log(
                Level.SEVERE, ex.getMessage(), ex);
      }
      try {
        if (dbfStream != null) {
          dbfStream.flush();
          dbfStream.close();
        }
      } catch (IOException ex) {
        Logger.getLogger(ShapefileWriter.class.getName()).log(
                Level.SEVERE, ex.getMessage(), ex);
      }
    }

  }

  /**
   * Validates the feature set, throwing the exception if needed.
   *
   * @param featureSet The featureSet to be validated.
   */
  private void validate(FeatureSet featureSet) {
    if (featureSet == null) {
      throw new IllegalArgumentException("The featureSet parameter cannot be null.");
    }
    if (!shapesAreCorrectType(featureSet)) {
      throw new IllegalArgumentException("All the shapes should be the same type."
              + "  The first record was " + featureSet.getFeatureType()
              + " but other features did not match this type.");
    }
  }

  /**
   * This will cycle through the features of the feature set and ensure that all the feature shape-types are consistent
   * with the feature type. If not, this returns false.
   *
   * @param featureSet The FeatureSet to validate.
   * @return boolean returns true if all the shapes match the featureset type or are null.
   */
  public final boolean shapesAreCorrectType(FeatureSet featureSet) {
    ShapeType type = featureSet.getFeatureType();
    for (Feature f : featureSet.getFeatures()) {
      if (f.getShape().getShapeType() == ShapeType.NullShape) {
        continue;
      }
      if (f.getShape().getShapeType() != type) {
        return false;
      }
    }
    return true;
  }

  /**
   * This method is specific to shapefiles and allows quickly changing one extension to another. If the filename starts
   * without one of the shapefile extensions, then, this will append the specified extension to the end of the filename.
   *
   * @param filename The full path of the shapefile. This can end in any of the valid shapefile extensions or no
   * extension at all.
   * @param extension The extension with leading period like ".shp"
   * @return the String shapefile with extension.
   */
  public static String setExtension(String filename, String extension) {
    if (filename.endsWith(".shp") || filename.endsWith(".shx")
            || filename.endsWith(".dbf")) {
      filename = filename.substring(0, filename.lastIndexOf("."));
    }
    return filename.concat(extension);

  }

  /**
   * This writes to a single zip stream created from a File, and where closeEntry must be called after each new zip
   * entry.
   *
   * @param featureSet The featureset to write to.
   * @param zipStream The regular output stream to write the zip content to. (For instance this can be a
   * FileOutputStream or else an output stream on the server.)
   * @throws gov.ca.water.shapelite.ShapefileException If there was a problem with the shapefile formatting.
   * @throws java.io.IOException If there was an error writing to the output stream.
   */
  public void write(FeatureSet featureSet, OutputStream zipStream)
          throws ShapefileException, IOException {
    try (ZipOutputStream zip = new ZipOutputStream(zipStream)) {
      write(featureSet, zip);
    }
  }

  /**
   * This allows the user to create a zip stream that might include multiple shapefiles, for instance. The shp, shx,
   * dbf, and prj are added as separate entries, but the stream is not closed.
   *
   * @param featureSet The FeatureSet to write.
   * @param zip The output stream to write to.
   * @throws gov.ca.water.shapelite.ShapefileException If there was a problem with the shapefile formatting.
   * @throws java.io.IOException If there was an error writing to the output stream.
   */
  public void write(FeatureSet featureSet, ZipOutputStream zip)
          throws ShapefileException, IOException {
    validate(featureSet);
    if (featureSet.getName() == null) {
      featureSet.setName("Shapes");
    }

    // clean Feature Name
    String featureName = null;
    if (((featureName = featureSet.getName()) != null)
            && !featureName.isEmpty()) {
      featureName = featureName.replaceAll("(\\.(shp|shx|dbf|prj))*$", "");
    }

    // SHP
    zip.putNextEntry(new ZipEntry(featureName + ".shp"));
    writeShapefile(featureSet, zip);
    zip.closeEntry();

    // SHX
    zip.putNextEntry(new ZipEntry(featureName + ".shx"));
    writeShxFile(featureSet, zip);
    zip.closeEntry();

    // DBF
    zip.putNextEntry(new ZipEntry(featureName + ".dbf"));
    writeDbfFile(featureSet, zip);
    zip.closeEntry();

    // PRJ
    if (featureSet.getProjectionESRI() != null) {
      zip.putNextEntry(new ZipEntry(featureName + ".prj"));
      writePrjFile(featureSet, zip);
      zip.closeEntry();
    }

  }

  /**
   * Writes the specified FeatureSet in shapefile format to the specified output streams.
   *
   * @param featureSet The FeatureSet to write.
   * @param shp The output stream for the .shp file.
   * @param shx The output stream for the .shx file.
   * @param dbf The output stream for the .dbf file.
   * @param prj The output stream for the .prj file.
   * @throws gov.ca.water.shapelite.ShapefileException If there was a problem with the shapefile formatting.
   * @throws java.io.IOException If there was an error writing to the output stream.
   */
  public void write(FeatureSet featureSet, OutputStream shp, OutputStream shx,
          OutputStream dbf, OutputStream prj) throws ShapefileException, IOException {
    validate(featureSet);
    write(featureSet, shp, shx, dbf);
    writePrjFile(featureSet, prj);

  }

  /**
   *
   * @param featureSet The FeatureSet that should be written.
   * @param prj The output stream for the .prj file that stores the projection.
   * @throws java.io.IOException If there was an error writing to the output stream.
   */
  public void writePrjFile(FeatureSet featureSet, OutputStream prj) throws IOException {
    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(prj));
    writer.write(featureSet.getProjectionESRI());
    writer.flush();
    // be sure not to close the stream.
  }

  /**
   * This assumes that rather than writing to files, we are writing to streams instead. There is a separate output file
   * stream for the shp, shx and dbf extensions.
   *
   * @param featureSet The feature set to save.
   * @param shp The output stream for the shp file to be written to.
   * @param shx The output stream for the shx file to be written to.
   * @param dbf The output stream for the dbf file to be written to.
   * @throws ShapefileException if the featureset is empty or null.
   * @throws java.io.IOException if there was a problem writing to the file.
   */
  public void write(FeatureSet featureSet, OutputStream shp, OutputStream shx,
          OutputStream dbf) throws ShapefileException, IOException {
    validate(featureSet);
    writeShxFile(featureSet, shx);
    writeShapefile(featureSet, shp);
    writeDbfFile(featureSet, dbf);

  }

  /**
   * This assumes that rather than writing to files, we are writing to streams instead. There is a separate output file
   * stream for the shp, shx and dbf extensions.
   *
   * @param featureSet The feature set to save.
   * @param shp The output stream for the shp file to be written to.
   * @throws ShapefileException if the featureset is empty or null.
   * @throws java.io.IOException if there was a problem writing to the file.
   */
  public void writeShapefile(FeatureSet featureSet, OutputStream shp)
          throws ShapefileException, IOException {

    try {
      ShapefileHeader header = createShapefileHeader(featureSet);

      // ------------- SHP FILE -------------------
      Writer shpWriter = new Writer(shp);

      // Write the first 100 bytes of header content to each stream.
      shpWriter.write(header, header.getLength());

      int iShape = 0;
      if (progress != null) {
        progress.create("Writing Shapefile " + featureSet.getName(), true);
        progress.start(featureSet.getFeatures().size());
      }
      for (Feature feature : featureSet.getFeatures()) {
        // write record header
        shpWriter.writeBigInt(iShape + 1); // record numbers start at 1
        shpWriter.writeBigInt((header.getByteSizes().getSizes().get(iShape) - 8) / 2);
        shpWriter.write(feature.getShape().getShapeType().getValue());
        writeShape(shpWriter, feature.getShape());
        if (progress != null) {
          progress.progress(iShape);
          if (progress.isCanceled()) {
            return;
          }
        }

        iShape++;
      }
      shpWriter.flush();
      if (progress != null) {
        progress.finish();
      }
    } catch (Exception ex) {
      if (progress != null) {
        progress.finish();
      }
      throw ex;
    }

  }

  /**
   * This assumes that rather than writing to files, we are writing to streams instead. There is a separate output file
   * stream for the shp, shx and dbf extensions.
   *
   * @param featureSet The feature set to save.
   * @param shx The output stream for the shx file to be written to.
   * @throws ShapefileException if the featureset is empty or null.
   * @throws java.io.IOException if there was a problem writing to the file.
   */
  public void writeShxFile(FeatureSet featureSet, OutputStream shx)
          throws ShapefileException, IOException {

    ShapefileHeader header = createShapefileHeader(featureSet);

    //  ---------- SHX FILE --------------------
    // Set up writers for the index file and the shape file.
    Writer shxWriter = new Writer(shx);

    // Write the first 100 bytes of header content to each stream.
    shxWriter.write(header, header.getShxLength());

    // Write the offsets to the index file.
    int offset = 50;
    for (Integer size : header.getByteSizes().getSizes()) {
      shxWriter.writeBigInt(offset);
      shxWriter.writeBigInt((size - 8) / 2);
      offset += size / 2;
    }
    shxWriter.flush();

  }

  /**
   * This assumes that rather than writing to files, we are writing to streams instead. There is a separate output file
   * stream for the shp, shx and dbf extensions.
   *
   * @param featureSet The feature set to save.
   * @param dbf The output stream for the dbf file to be written to.
   * @throws ShapefileException if the featureset is empty or null.
   * @throws java.io.IOException if there was a problem writing to the file.
   */
  public static void writeDbfFile(FeatureSet featureSet, OutputStream dbf)
          throws ShapefileException, IOException {
    AttributeTableWriter dbfWriter = new AttributeTableWriter();
    dbfWriter.write(dbf, featureSet);
  }

  /**
   * Writes the vector content of the specified shape into a binary format that is defined by the ESRI white paper.
   * Note, the MultiPatch format is not supported.
   *
   * @param shpWriter The writer that writes binary content to the stream.
   * @param shape The shape to write to the stream.
   */
  public static void writeShape(Writer shpWriter, Shape shape) {
    switch (shape.getShapeType()) {
      case NullShape:
        return;
      case Point:
        writePoint(shpWriter, shape);
        return;
      case PointM:
        writePointM(shpWriter, shape);
        return;
      case PointZ:
        writePointZ(shpWriter, shape);
        return;
      case MultiPoint:
        writeMultiPoint(shpWriter, shape);
        return;
      case MultiPointM:
        writeMultiPointM(shpWriter, shape);
        return;
      case MultiPointZ:
        writeMultiPointZ(shpWriter, shape);
        return;
      case PolyLine:
        writePoly(shpWriter, shape);
        return;
      case Polygon:
        writePoly(shpWriter, shape);
        return;
      case PolyLineM:
        writePolyM(shpWriter, shape);
        return;
      case PolygonM:
        writePolyM(shpWriter, shape);
        return;
      case PolyLineZ:
        writePolyZ(shpWriter, shape);
        return;
      case PolygonZ:
        writePolyZ(shpWriter, shape);
        return;
      default: // do nothing
        LOGGER.log(Level.SEVERE, "ShapefileWriter.writeShape-Urecognized shape type.");
    }
  }

  /**
   * Writes a point shape with X and Y values to the stream associated with the writer.
   *
   * @param writer The Writer to use to write content to the stream.
   * @param shape The shape to write.
   */
  public static void writePoint(Writer writer, Shape shape) {
    writer.write(shape.getX());
    writer.write(shape.getY());
  }

  /**
   * Writes a point shape with X, Y and M values to the stream associated with the writer.
   *
   * @param writer The Writer to use to write content to the stream.
   * @param shape The shape to write.
   */
  public static void writePointM(Writer writer, Shape shape) {
    writer.write(shape.getX());
    writer.write(shape.getY());
    writer.write(shape.getM());
  }

  /**
   * Writes a point shape with X, Y, M, and Z values to the stream associated with the writer.
   *
   * @param writer The Writer to use to write content to the stream.
   * @param shape The shape to write.
   */
  public static void writePointZ(Writer writer, Shape shape) {
    writer.write(shape.getX());
    writer.write(shape.getY());
    writer.write(shape.getZ());
    writer.write(shape.getM());
  }

  /**
   * Writes a multi-point shape with X and Y values to the stream associated with the writer.
   *
   * @param writer The Writer to use to write content to the stream.
   * @param shape The shape to write.
   */
  public static void writeMultiPoint(Writer writer, Shape shape) {
    writer.writeBox(shape.getEnvelope());
    double[] xy = shape.getArrayXY();
    int numPoints = xy.length / 2;
    writer.write(numPoints);
    writer.write(xy);
  }

  /**
   * Writes a multi-point shape with X, Y and M values to the stream associated with the writer.
   *
   * @param writer The Writer to use to write content to the stream.
   * @param shape The shape to write.
   */
  public static void writeMultiPointM(Writer writer, Shape shape) {
    writer.writeBox(shape.getEnvelope());
    double[] xy = shape.getArrayXY();
    int numPoints = xy.length / 2;
    writer.write(numPoints);
    writer.write(xy);
    if (shape.hasM()) {
      writer.write(shape.getEnvelope().getMin().get(M));
      writer.write(shape.getEnvelope().getMax().get(M));
      writer.write(shape.getArrayM());
    }

  }

  /**
   * Writes a multi-point shape with X, Y, M and Z values to the stream associated with the writer.
   *
   * @param writer The Writer to use to write content to the stream.
   * @param shape The shape to write.
   */
  public static void writeMultiPointZ(Writer writer, Shape shape) {
    writer.writeBox(shape.getEnvelope());
    double[] xy = shape.getArrayXY();
    int numPoints = xy.length / 2;
    writer.write(numPoints);
    writer.write(xy);
    writer.write(shape.getEnvelope().getMin().get(Z));
    writer.write(shape.getEnvelope().getMax().get(Z));
    writer.write(shape.getArrayZ());
    if (shape.hasM()) {
      writer.write(shape.getEnvelope().getMin().get(M));
      writer.write(shape.getEnvelope().getMax().get(M));
      writer.write(shape.getArrayM());
    }
  }

  /**
   * Writes a PolyLine shape with X and Y values to the stream associated with the writer.
   *
   * @param writer The Writer to use to write content to the stream.
   * @param shape The shape to write.
   */
  public static void writePoly(Writer writer, Shape shape) {
    writer.writeBox(shape.getEnvelope());
    int numParts = shape.getParts().size();
    writer.write(numParts);
    double[] xy = shape.getArrayXY();
    int numPoints = xy.length / 2;
    writer.write(numPoints);
    int offset = 0;
    for (Part part : shape.getParts()) {
      writer.write(offset);
      offset += part.getCoordinates().size();
    }
    writer.write(xy);
  }

  /**
   * Writes a PolyLine shape with X, Y and M values to the stream associated with the writer.
   *
   * @param writer The Writer to use to write content to the stream.
   * @param shape The shape to write.
   */
  public static void writePolyM(Writer writer, Shape shape) {
    writer.writeBox(shape.getEnvelope());
    int numParts = shape.getParts().size();
    writer.write(numParts);
    double[] xy = shape.getArrayXY();
    int numPoints = xy.length / 2;
    writer.write(numPoints);
    int offset = 0;
    for (Part part : shape.getParts()) {
      writer.write(offset);
      offset += part.getCoordinates().size();
    }
    writer.write(xy);
    if (shape.hasM()) {
      writer.write(shape.getEnvelope().getMin().get(M));
      writer.write(shape.getEnvelope().getMax().get(M));
      writer.write(shape.getArrayM());
    }
  }

  /**
   * Writes a PolyLine shape with X, Y, M and Z values to the stream associated with the writer.
   *
   * @param writer The Writer to use to write content to the stream.
   * @param shape The shape to write.
   */
  public static void writePolyZ(Writer writer, Shape shape) {
    writer.writeBox(shape.getEnvelope());
    int numParts = shape.getParts().size();
    writer.write(numParts);
    double[] xy = shape.getArrayXY();
    int numPoints = xy.length / 2;
    writer.write(numPoints);
    int offset = 0;
    for (Part part : shape.getParts()) {
      writer.write(offset);
      offset += part.getCoordinates().size();
    }
    writer.write(xy);
    if (shape.getEnvelope().hasZ()) {
      writer.write(shape.getEnvelope().getMin().get(Z));
      writer.write(shape.getEnvelope().getMax().get(Z));
      writer.write(shape.getArrayZ());
    } else {
      writer.write(0);
      writer.write(0);
      double[] z = new double[numPoints];
      writer.write(z);
    }

    
    if (shape.hasM()) {
      writer.write(shape.getEnvelope().getMin().get(M));
      writer.write(shape.getEnvelope().getMax().get(M));
      writer.write(shape.getArrayM());
    }
  }

  /**
   * Creates the shapefile header that is an object representation of the data that is stored in the first 100 bytes of
   * the shapefile.
   *
   * @param featureSet The FeatureSet to use as a reference.
   * @return A ShapefileHeader.
   * @throws ShapefileException If there was an error reading the header.
   */
  public ShapefileHeader createShapefileHeader(FeatureSet featureSet)
          throws ShapefileException {
    ShapefileHeader header = new ShapefileHeader();
    header.setBoundsFrom(featureSet.getEnvelope());
    if (featureSet.getFeatures() == null || featureSet.getFeatures().isEmpty()) {
      header.setShapeType(featureSet.getDefaultShapeType());
    } else {
      header.setShapeType(featureSet.getFeatures().get(0).getShape().getShapeType());
    }
    ByteSizes featureMeasures = calculateSizes(featureSet);

    // Be sure to convert from length in bytes to length in 16-bit words.
    header.setLength(featureMeasures.getTotalLength() / 2);

    header.setShxLength(50 + featureMeasures.getSizes().size() * 4);

    header.setByteSizes(featureMeasures);
    return header;
  }

  /**
   * Determines the size in bytes of a feature based on the number of parts or points.
   *
   * @param feature
   * @return
   */
  public static Integer calculateSize(Feature feature) {
    Shape shp = feature.getShape();
    int size = 8; // record header in bytes
    int points = shp.getArrayXY().length / 2;
    int parts = shp.getParts().size();
    switch (shp.getShapeType()) {
      case Point:
        size += 20;
        break;
      case PointM:
        size += 28;
        break;
      case PointZ:
        size += 36;
        break;
      case MultiPoint:
        size += 40 + 16 * points;
        break;
      case MultiPointM:
        size += 40 + 16 * points;
        if (shp.hasM()) {
          size += 16 + 8 * points;
        }
        break;
      case MultiPointZ:
        size += 56 + 24 * points;
        if (shp.hasM()) {
          size += 16 + 8 * points;
        }
        break;
      case Polygon:
        size += 44 + 4 * parts + 16 * points;
        break;
      case PolyLine:
        size += 44 + 4 * parts + 16 * points;
        break;
      case PolygonM:
        size += 44 + 4 * parts + 16 * points;
        if (shp.hasM()) {
          size += 16 + 8 * points;
        }
        break;
      case PolyLineM:
        size += 44 + 4 * parts + 16 * points;
        if (shp.hasM()) {
          size += 16 + 8 * points;
        }
        break;
      case PolygonZ:
        size += 44 + 4 * parts + 16 * points + 16 + 8 * points;
        if (shp.hasM()) {
          size += 16 + 8 * points;
        }
        break;
      case PolyLineZ:
        size += 44 + 4 * parts + 16 * points + 16 + 8 * points;
        if (shp.hasM()) {
          size += 16 + 8 * points;
        }
        break;
    }

    return size;

  }

  public static ByteSizes calculateSizes(FeatureSet fs) {
    ByteSizes result = new ByteSizes();
    for (Feature feature : fs.getFeatures()) {
      result.getSizes().add(calculateSize(feature));
    }
    return result;
  }

  /**
   * Gets an optional progress indicator.
   *
   * @return the progress
   */
  public final Progress getProgress() {
    return progress;
  }

  /**
   * sets an optional progress indicator.
   *
   * @param progress the progress to set
   */
  public final void setProgress(ProgressCountableCancellable progress) {
    this.progress = progress;
  }
}
