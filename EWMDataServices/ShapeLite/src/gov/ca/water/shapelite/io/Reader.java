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

import gov.ca.water.shapelite.ReadBytes;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is used to make it easier to read certain data types from a binary
 * stream.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class Reader {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The long index offset of the current byte location where reading is
   * occurring.
   */
  private long position;

  private ByteArrayInputStream core;

  /**
   * The stream with the raw byte values.
   */
  private final ByteArrayInputStream stream;

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Constructors">
  /**
   * Creates a new reader using the specified filename. The bytes will be read
   * from the file.
   *
   * @param filename The string filename to read from.
   * @throws FileNotFoundException Occurs if the file cannot be found.
   */
  public Reader(String filename) throws FileNotFoundException, IOException {
    //stream = new BufferedInputStream(new FileInputStream(filename), 10000 * 1024);
    byte[] memorySource = ReadBytes.readAll(filename);
    this.stream = new ByteArrayInputStream(memorySource);
    position = 0;
    stream.mark(10000 * 1024);
  }

  /**
   * Creates a new reader using the specified input stream. The bytes will be
   * read directly from the stream.
   *
   * @param source The stream to read values from.
   * @throws java.io.IOException
   */
  public Reader(InputStream source) throws IOException {
    //stream = new BufferedInputStream(source, 10000 * 1024);
    byte[] memorySource = ReadBytes.readAll(source);
    this.stream = new ByteArrayInputStream(memorySource);
    position = 0;
    stream.mark(10000 * 1024);
  }

  /**
   * Creates a new reader from an array of bytes. This allows an in memory
   * collection of bytes to be read using the same logic as a file.
   *
   * @param memorysource
   */
  public Reader(byte[] memorysource) {
    core = new ByteArrayInputStream(memorysource);
    //stream = new BufferedInputStream(core, memorysource.length);
    this.stream = new ByteArrayInputStream(memorysource);
    position = 0;
    stream.mark(memorysource.length);
  }

   //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Advances the buffer by the specified offset without actually reading
   * anything.
   *
   * @param offset the integer number of byte values to skip.
   */
  public void advance(int offset) {
    position += offset;
    stream.skip(offset);
  }

  /**
   * Moves the current position to the specified offset.
   *
   * @param offset The read position to move to in the stream.
   */
  public void advanceTo(long offset) {

    long diff = offset - getPosition();
    if (diff > 0) {
      long remainder = diff;
      while (remainder > 0) {
                    // A single skip instruction will skip at most to the end of the buffer.
        // Skip multiple times to skip to the true desired file location.
        long actualSkipped = stream.skip(remainder);
        remainder -= actualSkipped;
      }
      //stream.skip(diff);
    } else {
      stream.reset();
      stream.skip(offset);
    }
    position = offset;

  }

  /**
   * Closes the stream.
   */
  public void close() {
    try {
      stream.close();
    } catch (IOException ex) {
      Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Reads the specified number of bytes from the current position and returns
   * them as a byte array.
   *
   * @param count The number of bytes to read.
   * @return The array of bytes read.
   */
  public byte[] read(int count) {
    byte[] result = new byte[count];

    stream.read(result, 0, count);
    position += count;

    return result;
  }

  /**
   * Reads an integer in big Endian order. This is the default read order for
   * the ByteBuffer in java regardless of platform, so the byte order does not
   * have to be reversed.
   *
   * @return the integer value read from the next 4 byte in big Endian order.
   */
  public int readBigInt() {
    byte[] b = new byte[4];

    stream.read(b, 0, 4);
    position += 4;
    return ByteBuffer.wrap(b).getInt();

  }

  /**
   * Java uses a byte range from -128 to 127 instead of 0 to 255. So if the full
   * range is needed, we need to upcast to int. If -1 is returned, then reading
   * was not successful.
   *
   * @return the int read from the data.
   */
  public int readByte() {
    int result = -1;

    position += 1;
    result = stream.read();
    if (result < 0) {
      result += 256;
    }

    return result;
  }

  /**
   * Reads the double value from the next 8 bytes and advances the position 8
   * bytes. Since the java reader always works as big-endian, this reverses it
   * so that the bytes are in little Endian order.
   *
   * @return The double created from the next 8 bytes in the stream.
   */
  public double readDouble() {
    byte[] b = new byte[8];

    stream.read(b, 0, 8);
    position += 8;
    reverse(b);
    return ByteBuffer.wrap(b).getDouble();

  }

  /**
   * Reads the specified number of double values into an array.
   *
   * @param count The number of doubles to read.
   * @return An array of double values read from the stream.
   */
  public double[] readDouble(int count) {
    double[] result = new double[count];
    for (int i = 0; i < count; i++) {
      result[i] = readDouble();
    }
    return result;
  }

  /**
   * Reads the next 4 bytes into an integer and advances the read position by 4
   * bytes.
   *
   * @return The integer read.
   */
  public int readInt() {
    byte[] b = new byte[4];

    stream.read(b, 0, 4);
    position += 4;
    reverse(b);
    return ByteBuffer.wrap(b).getInt();

  }

  /**
   * Reads the next 4*count bytes as an array of integers.
   *
   * @param count The number of integers to read.
   * @return The array of int.
   * @throws IllegalArgumentException if count is less than 1.
   */
  public int[] readInt(int count) {
    if (count < 1) {
      throw new IllegalArgumentException("Parameter count must be at least 1.");
    }
    int[] result = new int[count];
    for (int i = 0; i < count; i++) {
      result[i] = readInt();
    }
    return result;
  }

  /**
   * Reads the next short value from the next 2 bytes, and advances the position
   * 2 bytes.
   *
   * @return The short value read from the stream.
   */
  public short readShort() {
    byte[] b = new byte[2];

    stream.read(b, 0, 2);
    position += 2;
    reverse(b);
    return ByteBuffer.wrap(b).getShort();

  }

  /**
   * Reads a string with a specific length specified by count.
   *
   * @param count the integer count of the number of bytes in the string to
   * read.
   * @return A String read from the next number of bytes specified by count.
   */
  public String readString(int count) {
    byte[] buffer = new byte[count];

    stream.read(buffer, 0, count);
    position += count;

    String result = new String(buffer);
    int nullPoint = result.indexOf((char) 0);
    if (nullPoint != -1) {
      result = result.substring(0, nullPoint);
    }
    return result.trim();
  }

  /**
   * Reverses the bytes in the array.
   *
   * @param array The array of values to reverse.
   */
  public static void reverse(byte[] array) {
    int l = array.length;
    for (int i = 0; i < l / 2; i++) {
      byte t = array[i];
      array[i] = array[l - i - 1];
      array[l - i - 1] = t;
    }
  }

   //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * The current long read position in the stream.
   *
   * @return the position
   */
  public long getPosition() {
    return position;
  }

  //</editor-fold>
}
