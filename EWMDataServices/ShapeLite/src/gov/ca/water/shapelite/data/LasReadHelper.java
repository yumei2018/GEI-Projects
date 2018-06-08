/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.ca.water.shapelite.data;

import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class LasReadHelper {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The stream variable to read from.
   */
  private final DataInputStream stream;

  //</editor-fold>
  /**
   * Creates a new instance of the ReadHelper class, passing in the
   * DataInputStream that is doing the actual reading.
   */
  public LasReadHelper(DataInputStream stream) {
    this.stream = stream;
  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Read the number of characters specified into a string. The specification
   * requires 1 byte per character, so this does not read as unicode characters.
   *
   * @param numChars
   * @return
   * @throws IOException
   */
  public String readChars(int numChars) throws IOException {
    byte[] data = new byte[numChars];
    stream.read(data);
    return new String(data);
  }

  public char readChar() throws IOException {
    return readChars(1).charAt(0);
  }

  public int readUShort() throws IOException {
    byte[] bytes = new byte[2];
    stream.read(bytes);
    int result = (bytes[0] & 0xFF) + 256 * (bytes[1] & 0xFF);
    return result;
  }

  /**
   * Reads a single unsigned 4-byte integer from the stream, and returns it in a
   * long variable.
   *
   * @return the long holding a 4-byte unsigned integer.
   * @throws IOException
   */
  public long readUInt() throws IOException {
    byte[] bytes = new byte[4];
    stream.read(bytes);
    long result = (bytes[0] & 0xFF) + 256 * (bytes[1] & 0xFF) + 65536 * (bytes[2] & 0xFF) + 16777216 * (bytes[3] & 0xFF);
    return result;
  }

  /**
   * This will read count 4-byte unsigned integer values into the array of
   * longs.
   *
   * @param count The number of Uint values to read.
   * @return The array of long values.
   * @throws IOException
   */
  public long[] readUInt(int count) throws IOException {
    long[] result = new long[count];
    for (int i = 0; i < count; i++) {
      result[i] = readUInt();
    }
    return result;
  }

  public Double readDouble() throws IOException {
    byte[] bytes = new byte[8];
    stream.read(bytes);
    ByteBuffer bb = ByteBuffer.wrap(bytes);
    bb.order(ByteOrder.LITTLE_ENDIAN);
    return bb.getDouble();
  }

  /**
   * Because java does not support an unsigned primitive long, the BigInteger
   * class will work to hold the correct value.
   *
   * @return a BigInteger value containing the unsigned long number.
   * @throws IOException
   */
  public BigInteger readULong() throws IOException {
    byte[] vals = new byte[8];
    stream.read(vals);
    ByteBuffer bb = ByteBuffer.wrap(vals);
    bb.order(ByteOrder.LITTLE_ENDIAN);
    byte[] reversed = new byte[8];
    for (int i = 0; i < 8; i++) {
      reversed[i] = vals[7 - i];
    }
    return new BigInteger(1, reversed);
  }

  /**
   * Reads the signed 4-byte little endian integer value
   *
   * @return Integer value
   * @throws IOException
   */
  public int readInt() throws IOException {
    byte[] vals = new byte[4];
    stream.read(vals);
    ByteBuffer bb = ByteBuffer.wrap(vals);
    bb.order(ByteOrder.LITTLE_ENDIAN);
    return bb.getInt();
  }

    //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  //</editor-fold>
}
