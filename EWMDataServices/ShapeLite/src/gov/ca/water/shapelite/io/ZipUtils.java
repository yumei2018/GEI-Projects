/*
 * The MIT License
 *
 * Copyright 2016 Harold A. Dunsford Jr. Ph.D..
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipInputStream;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class ZipUtils {

  /**
   * Default buffer size if size is not known.
   */
  private static final int BUFFER = 1024;

  /**
   * Private constructor for utiltiy class.
   */
  private ZipUtils() {

  }

  /**
   * An arbitrary buffer size. Sometimes powers of 2 are more efficient.
   */
  /**
   * Reads the entire byte stream from the zipInput into the result array.
   *
   * @param zipInput The zipInput stream, which may have multiple entries.
   * @param size THe uncompressed size in bytes. If this is not known, -1 can be
   * passed instead.
   * @return The array of bytes.
   * @throws java.io.IOException If there was an error reading the bytes.
   */
  public static byte[] readAll(ZipInputStream zipInput, long size) throws IOException {
    int totalLength = 0;
    int len;
    if (size == -1) {
      byte[] buffer = new byte[BUFFER];
      List<byte[]> buffers = new ArrayList<>();
      while ((len = zipInput.read(buffer)) > 0) {
        totalLength += len;
        buffers.add(buffer);
        buffer = new byte[BUFFER];
      }
      byte[] result = new byte[totalLength];
      int resultIndex = 0;
      for (byte[] b : buffers) {
        for (int i = 0; i < BUFFER; i++) {
          if (resultIndex >= result.length) {
            break;
          }
          result[resultIndex] = b[i];
          resultIndex++;
        }
      }
      return result;
    } else {
      byte[] buffer = new byte[(int) size];
      zipInput.read(buffer);
      return buffer;
    }

  }
}
