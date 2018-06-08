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
package gov.ca.water.shapelite;

import gov.ca.water.shapelite.io.ChannelScanner;
import gov.ca.water.shapelite.io.ByteSizes;
import gov.ca.water.shapelite.io.Reader;
import gov.ca.water.shapelite.io.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * This class contains the information for the header of a shapefile. All
 * shapefiles share the same header content and format. The header is also
 * shared between the shapefile and the shx file.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ShapefileHeader {

  /**
   * The file code byte signature that starts a shapefile.
   */
  private static final int FILE_CODE = 9994;
  /**
   * The integer shapefile version notation.
   */
  private static final int VERSION = 1000;

  /**
   * There are 20 blank bytes that are a placeholder for future use.
   */
  private static final int EMPTY_BYTES = 20;

  /**
   * The number of doubles that make up the shapefile envelope.
   */
  private static final int ENVELOPE_SIZE = 8;

  /**
   * Header size in bytes.
   */
  private static final int HEADER_SIZE = 100;

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The envelope extent of the entire shapefile.
   */
  private final Envelope bounds;

  /**
   * The constant file designation marking the file as part of a shapefile.
   */
  private int filecode;

  /**
   * The integer length in bytes of the shape file.
   */
  private int length;

  /**
   * The integer length in bytes of the shx file.
   */
  private int shxLength;

  /**
   * The ShapeType enumeration clarifying what shapes exist in the shapefile.
   * Null shapes can appear in any type of shapefile, but should never be the
   * shape type.
   */
  private ShapeType shapeType;

  /**
   * The version of the shapefile.
   */
  private int version;

  /**
   * The byteSizes.
   */
  private ByteSizes byteSizes;

    //</editor-fold>
  /**
   * Creates a new instance of a ShapefileHeader object.
   */
  public ShapefileHeader() {
    filecode = FILE_CODE;
    version = VERSION;
    bounds = new DefaultEnvelope(true, true);
  }

  /**
   * Copy the value.
   *
   * @return a deep copy of this ShapefileHeader object.
   */
  public final ShapefileHeader copy() {
    ShapefileHeader result = new ShapefileHeader();
    result.bounds.copyProperties(bounds.copy());
    result.shapeType = shapeType;
    result.length = length;
    result.filecode = filecode;
    result.version = version;
    return result;
  }

  /**
   * Reads the 100 header bytes from the specified source array.
   *
   * @param source An array of bytes that has the header content.
   */
  public final void read(byte[] source) {
    Reader r = new Reader(source);
    read(r);
    r.close();
  }

  /**
   * Reads the 100 header bytes using the specified reader. This will not close
   * the reader.
   *
   * @param reader the Reader.
   */
  public final void read(Reader reader) {
    filecode = reader.readBigInt();
    reader.advance(EMPTY_BYTES);
    length = reader.readBigInt();
    version = reader.readInt();
    shapeType = ShapeType.find(reader.readInt());
    bounds.copyProperties(new DefaultEnvelope(reader.readDouble(ENVELOPE_SIZE)));
  }

  /**
   * Reads the 100 header bytes using the specified scanner. This will not close
   * the scanner.
   *
   * @param scanner The Scanner to read data from.
   */
  public final void read(Scanner scanner) {
    filecode = scanner.readBigInt();
    scanner.advance(EMPTY_BYTES);
    length = scanner.readBigInt();
    version = scanner.readInt();
    shapeType = ShapeType.find(scanner.readInt());
    bounds.copyProperties(new DefaultEnvelope(scanner.readDouble(ENVELOPE_SIZE)));
  }

  /**
   * Read the header given the specified ChannelScanner.
   *
   * @param scanner The ChannelScanner to read data from..
   */
  public final void read(ChannelScanner scanner) {
    //scanner.map(100);
    filecode = scanner.readBigInt();
    scanner.advance(EMPTY_BYTES);
    length = scanner.readBigInt();
    version = scanner.readInt();
    shapeType = ShapeType.find(scanner.readInt());
    bounds.copyProperties(new DefaultEnvelope(scanner.readDouble(ENVELOPE_SIZE)));
  }

  /**
   * Reads the header content from the specified file.
   *
   * @param filename The string filename to read the header from.
   * @throws FileNotFoundException Occurs if the file could not be found.
   * @throws IOException if the filename cannot be read into bytes.
   */
  public final void read(String filename) throws FileNotFoundException,
          IOException {
    Reader r = new Reader(filename);
    filecode = r.readBigInt();
    r.advance(EMPTY_BYTES);
    length = r.readBigInt();
    version = r.readInt();
    shapeType = ShapeType.find(r.readInt());
    bounds.copyProperties(new DefaultEnvelope(r.readDouble(ENVELOPE_SIZE)));
    r.close();
  }

  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the envelope extent of the entire shapefile.
   *
   * @return the bounds
   */
  public final Envelope getBounds() {
    return bounds;
  }

  /**
   * Sets the envelope extent of the entire shapefile.
   *
   * @param bounds the bounds to set
   */
  public final void setBoundsFrom(Envelope bounds) {
    this.bounds.copyProperties(bounds);
  }

  /**
   * Gets the integer length in bytes of the file.
   *
   * @return the shapeType
   */
  public final ShapeType getShapeType() {
    return shapeType;
  }

  /**
   * Sets the integer length in bytes of the file.
   *
   * @param shapeType the shapeType to set
   */
  public final void setShapeType(ShapeType shapeType) {
    this.shapeType = shapeType;
  }

  /**
   * Gets the integer length in 16-bit words of the file including the 50 word
   * header file.
   *
   * @return the length
   */
  public final int getLength() {
    return length;
  }

  /**
   * Sets the integer length in 16-bit words of the file.
   *
   * @param length the length to set
   */
  public final void setLength(int length) {
    this.length = length;
  }

  /**
   * Gets the constant file designation marking the file as part of a shapefile.
   *
   * @return the filecode
   */
  public final int getFilecode() {
    return filecode;
  }

  /**
   * Sets the constant file designation marking the file as part of a shapefile.
   *
   * @param filecode the filecode to set
   */
  public final void setFilecode(int filecode) {
    this.filecode = filecode;
  }

  /**
   * Gets the version of the shapefile.
   *
   * @return the version
   */
  public final int getVersion() {
    return version;
  }

  /**
   * Sets the version of the shapefile.
   *
   * @param version the version to set
   */
  public final void setVersion(int version) {
    this.version = version;
  }

  //</editor-fold>
  /**
   * @return the shxLength
   */
  public final int getShxLength() {
    return shxLength;
  }

  /**
   * @param shxLength the shxLength to set
   */
  public final void setShxLength(int shxLength) {
    this.shxLength = shxLength;
  }

  /**
   * @return the sizes
   */
  public final ByteSizes getByteSizes() {
    return byteSizes;
  }

  /**
   * @param sizes the sizes to set
   */
  public final void setByteSizes(ByteSizes sizes) {
    this.byteSizes = sizes;
  }

  /**
   * Gets the last byte size.
   *
   * @return the integer byte of the last byte sizes item.
   */
  public final Integer getLastByteSize() {
    return this.byteSizes.getLast();
  }

  /**
   * Gets the SHP header.
   *
   * @return the byte array representing this content for the SHP header.
   */
  public final byte[] getShpHeader() {
    return getHeader(length);
  }

  /**
   * Gets the SHX header.
   *
   * @return the byte array representing this content for the SHX header.
   */
  public final byte[] getShxHeader() {
    return getHeader(shxLength);
  }

  /**
   * Gets a byte representation of this header content.
   *
   * @param size the integer size of the whole file in bytes.
   * @return The byte array.
   */
  private byte[] getHeader(int size) {
    ByteBuffer result = ByteBuffer.allocate(HEADER_SIZE);
    result.order(ByteOrder.BIG_ENDIAN);
    result.putInt(filecode);
    result.put(new byte[EMPTY_BYTES]);
    result.putInt(size);
    result.order(ByteOrder.LITTLE_ENDIAN);
    result.putInt(version);
    result.putInt(shapeType.getValue());
    double[] dbls = bounds.toArray();
    for (double d : dbls) {
      result.putDouble(d);
    }
    return result.array();
  }
}
