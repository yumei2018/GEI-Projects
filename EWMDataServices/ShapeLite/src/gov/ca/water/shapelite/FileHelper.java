/*
 * The MIT License
 *
 * Copyright 2015 hdunsford.
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class FileHelper {

  /**
   * Hide constructor.
   */
  private FileHelper() {

  }

  /**
   * Gets either the string extension of an optional empty value.
   *
   * @param path THe string path to get the extension of.
   * @return The string path.
   */
  public static Optional<String> getExtension(@NonNull String path) {
    if (path == null || path.isEmpty()) {
      throw new IllegalArgumentException("Path should not be null or empty.");
    }
    int p = path.lastIndexOf(".");
    if (p > -1) {
      return Optional.of(path.substring(p));
    }
    return Optional.empty();
  }

  /**
   * Gets the string without the extension and period.
   *
   * @param path The path, this can be either a full path or just a filename.
   * @return The path without the extension.
   */
  public static String removeExtension(@NonNull String path) {
    if (path == null || path.isEmpty()) {
      throw new IllegalArgumentException("Path should not be null or empty.");
    }
    int p = path.lastIndexOf(".");
    if (p > -1) {
      return path.substring(0, p);
    }
    return path;
  }

  /**
   * This returns only the filename without the extension, and without any
   * leading folder information.
   *
   * @param path The fully referenced path to get the filename from.
   * @return The String filename.
   */
  public static String getBaseName(@NonNull String path) {
    if (path == null || path.isEmpty()) {
      throw new IllegalArgumentException("Path should not be null or empty.");
    }
    File f = new File(path);
    String result = removeExtension(f.getName());
    return result;
  }

  /**
   * Changes the extension on the string file path without changing the other
   * aspects of the file.
   *
   * @param path The string path to change.
   * @param ext The string extension (with or without period).
   * @return a String representing the new path.
   */
  public static String setExtension(String path, String ext) {
    if (path.endsWith(ext)) {
      return path;
    }
    String base = path;
    int index = path.lastIndexOf(".");
    if (index > -1) {
      base = path.substring(0, index);
    }
    if (ext.startsWith(".")) {
      return base + ext;
    } else {
      return base + "." + ext;
    }
  }

  /**
   * Reads all the bytes from the specified path into the byte array.
   *
   * @param path The string path to read from.
   * @return The byte array returned.
   * @throws FileNotFoundException If the file is not found.
   * @throws IOException If there was an error reading the file.
   */
  public static byte[] readAllBytes(@NonNull String path)
      throws FileNotFoundException, IOException {
    if (path == null || path.isEmpty()) {
      throw new IllegalArgumentException("Argument path cannot be null.");
    }
    File file = new File(path);
    int length = (int) file.length();
    byte[] array = new byte[length];
    try (InputStream in = new FileInputStream(file)) {
      int offset = 0;
      while (offset < length) {
        int count = in.read(array, offset, (length - offset));
        offset += count;
      }
    }
    return array;
  }

  /**
   * Reads all text from the specified file.
   *
   * @param path The full path of the file to read.
   * @return The String text found within the file.
   * @throws java.io.FileNotFoundException If the file does not exist.
   * @throws IllegalArgumentException if path is null.
   * @throws IOException if there was an error reading the file.
   */
  public static String readAll(@NonNull String path)
      throws FileNotFoundException, IOException {
    if (path == null) {
      throw new IllegalArgumentException("path should not be null.");
    }
    String result;
    try (FileInputStream stream = new FileInputStream(path)) {
      result = readAll(stream);
    }
    return result;
  }

  /**
   * Reads all text from the specified stream.
   *
   * @param stream The InputStream to read.
   * @return A String representing the string text in the file.
   */
  public static String readAll(@NonNull InputStream stream) {
    if (stream == null) {
      throw new IllegalArgumentException("stream should not be null.");
    }
    Scanner s = new Scanner(stream).useDelimiter("\\A");
    String result = "";
    if (s.hasNext()) {
      return s.next();
    }
    return result;
  }



}
