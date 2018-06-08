/*
 * To change this license previousHeader, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.ca.water.shapelite.io;

import gov.ca.water.shapelite.data.LasHeader;
import gov.ca.water.shapelite.data.PointRecord;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This las reader is designed to use exclusively the memory of the virtual
 * machine and does not use any external memory.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class LasReaderVM {
  //<editor-fold defaultstate="collapsed" desc="Fields">

  /**
   * The logger utility for this class.
   */
  private static final Logger LOGGER = Logger.getLogger(
      LasReaderVM.class.getName());
  /**
   * The field representing the header exclusively for this LasReaderVM object.
   */
  private LasHeader header;

  /**
   * The cached static filename for the previous request.
   */
  private static String filename;
  /**
   * The cached hashmap of points that are only the PointRecord objects that
   * were returned from the previous request.
   */
  private static HashMap<Integer, PointRecord> points;

  /**
   * The previous start index.
   */
  private static int startIndex;

  /**
   * The previous end index.
   */
  private static int endIndex;

  /**
   * The long offset representing the point index in the current file that has
   * been read up to, but not yet read.
   */
  private long pointOffset;

  /**
   * the byte size of an integer.
   */
  private static final int INT_BYTE_SIZE = 4;

  //</editor-fold>
  /**
   * Creates a new instance of the LasReader class.
   */
  public LasReaderVM() {

  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Opens the specified file path for reading and reads the previousHeader.
   *
   * @param path The string path of the LAS File to open.
   */
  public final void open(String path) {
    if (filename != null && filename.equals(path) && header != null) {
      return;
    }
    filename = path;
    points = new HashMap<>();

    try (DataInputStream stream = new DataInputStream(new FileInputStream(path))) {
      header = new LasHeader();
      header.readHeader(stream);
    } catch (IOException | LasReadException ex) {
      Logger.getLogger(LasReaderVM.class.getName()).log(Level.SEVERE,
          ex.getMessage(), ex);
      Logger.getLogger(LasReaderVM.class.getName()).log(Level.SEVERE,
          "File: {0}", path);
    }

  }

  /**
   * Attempts to get all the points as a single return.
   *
   * @return a list of PointRecord values that only have x, y and z data.
   * @throws LasReadException If there was a problem reading the LAS file.
   */
  public final List<PointRecord> getPointsOnly() throws LasReadException {
    if (header == null) {
      return new ArrayList<>();
    }
    return getPointsOnly(0, (int) header.getPointRecordCount());
  }

  /**
   * Attempts to get all the points starting with the specified start index.
   *
   * @param startIndex The integer start index.
   * @return A List of PointRecord objects.
   * @throws LasReadException if there was exception reading the points.
   */
  public final List<PointRecord> getPointsOnly(int startIndex)
      throws LasReadException {
    return getPointsOnly(startIndex, (int) header.getPointRecordCount());
  }

  /**
   * Reads just the X, Y and Z values for the points, ignoring the other
   * metadata.
   *
   * @param startIndex start index, inclusive.
   * @param endIndex end index, exclusive.
   * @return The list of PointRecord items.
   * @throws LasReadException if there was exception reading the points.
   */
  public final List<PointRecord> getPointsOnly(int startIndex,
      int endIndex) throws LasReadException {
    return getPointsOnlyTest(startIndex, endIndex);
  }

  /**
   * this is a new method for reading the las file using a file channel and a
   * random access file and a mappedbytebuffer.
   *
   * @param startIndex The integer start index of the point to read.
   * @param endIndex The integer end index of the point to read.
   * @return The List of PointRecord objects representing all the points.
   * @throws LasReadException if there was exception reading the points.
   */
  public final List<PointRecord> getPointsOnlyTest(int startIndex, int endIndex)
      throws LasReadException {
    if (filename == null) {
      throw new LasReadException("The file should be open first before reading.");
    }
    List<PointRecord> results = new ArrayList<>();

    long numPoints = endIndex - startIndex;
    long width = numPoints * header.getPointDataRecordLength();
    File f = new File(filename);
    if (!f.exists()) {
      return null;
    }
    pointOffset = 0;
    try (BufferedInputStream input = new BufferedInputStream(
        new FileInputStream(filename))) {
      input.skip(header.getOffsetToPointData());
      for (int i = 0; i < numPoints; i++) {
        if ((i + 1) * header.getPointDataRecordLength() > width) {
          break;
        }
        PointRecord rec = getPoint((i + startIndex), input);
        if (rec != null) {
          results.add(rec);
        }
      }
    } catch (FileNotFoundException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
    } catch (IOException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
    }
    points.clear();
    for (PointRecord point : results) {
      points.put(point.getId(), point);
    }
    return results;
  }

  /**
   * Gets the point either from the cached points or from the input stream. If
   * the point is not of the appropriate type, this will return null.
   *
   * @param index The integer index of the point to get in the master file.
   * @param input The BufferedInputStream for the file.
   * @return A PointRecord for the file.
   * @throws IOException if there was an error reading the point.
   */
  private PointRecord getPoint(int index, BufferedInputStream input)
      throws IOException {

    // If the point is in the recorded range, return a cached point or null.
    if (points != null && !points.isEmpty()) {
      if (index >= startIndex && index < endIndex) {
        if (points.containsKey(index)) {
          return points.get(index);
        } else {
          return null;
        }
      }
    }

    // If the point is outside the cache range, read the file instead.
    long skipBytes = (index - pointOffset) * header.getPointDataRecordLength();
    input.skip(skipBytes);

    int otherStuff = header.getPointDataRecordLength() - 12 - 3 - 1;
    byte[] uselessMid = new byte[3];
    byte[] classification = new byte[1];
    byte[] useless = new byte[otherStuff];

    Integer x = getInt(input);
    Integer y = getInt(input);
    Integer z = getInt(input);
    input.read(uselessMid, 0, 3);
    input.read(classification, 0, 1);
    input.read(useless, 0, otherStuff);

    Integer type = ((Byte) classification[0]).intValue();
    pointOffset = index + 1;
    if (type == 2 || type > 12 || type == 9) {
      PointRecord rec = new PointRecord();
      rec.setId(index);
      rec.setX(x);
      rec.setY(y);
      rec.setZ(z);
      return rec;
    }

    return null;

  }

  /**
   * Clears the current point list from memory.
   */
  public final void clear() {
    points.clear();
  }

  /**
   * Given an input stream, this returns an integer.
   *
   * @param stream The stream to get an integer from.
   * @return An Integer that was read from the file.
   * @throws IOException if there was an error reading from the file.
   */
  private Integer getInt(BufferedInputStream stream) throws IOException {
    byte[] data = new byte[INT_BYTE_SIZE];
    stream.read(data);
    return ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN).getInt();
  }

  /**
   * Gets the LasHeader most recently opened by the LasReaderVM classes.
   *
   * @return the header opened by this instance.
   */
  public final LasHeader getHeader() {
    return header;
  }

  //</editor-fold>
}
