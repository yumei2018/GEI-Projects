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

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * This method is used to read all the bytes from an input stream at once.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class ReadBytes {

  /**
   * A buffer size that is reasonably small.
   */
  private static final int BUFFER_SIZE = 16384;

  /**
   * Hide constructor.
   */
  private ReadBytes() {

  }

  /**
   * Reads all the bytes from an input stream.
   *
   * @param stream The stream to read.
   * @return An array of bytes.
   * @throws IOException Occurs if there is an error reading the bytes from the
   * stream.
   */
  public static byte[] readAll(InputStream stream) throws IOException {
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    int nRead;
    byte[] data = new byte[BUFFER_SIZE];
    while ((nRead = stream.read(data, 0, data.length)) != -1) {
      buffer.write(data, 0, nRead);
    }
    buffer.flush();
    return buffer.toByteArray();
  }

  /**
   * ReadAll by directly using a DataInputStream to read Fully.  This
   * only works for files smaller than about 2 gigs.
   *
   * @param fileName The string filename to read.
   * @return The byte array of all the bytes read from the file.
   * @throws IOException if there is an error reading the file.
   */
  public static byte[] readAll(String fileName) throws IOException {
    File f = new File(fileName);
    if (!f.exists()) {
      throw new FileNotFoundException("File " + fileName + " not found.");
    }
    if (f.length() > Integer.MAX_VALUE) {
      throw new IllegalArgumentException("File " + fileName
          + " is too large to read all at once.");
    }
    if (f.exists() && f.length() < Integer.MAX_VALUE) {
      byte[] result = new byte[(int) f.length()];
      DataInputStream stream = new DataInputStream(new FileInputStream(f));
      stream.readFully(result);
      return result;
    }
    return new byte[0];
  }
}
