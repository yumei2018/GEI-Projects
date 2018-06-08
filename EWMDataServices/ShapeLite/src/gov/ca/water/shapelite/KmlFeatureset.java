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

import java.util.List;

/**
 * A feature set that contains all the necessary elements to create a KML
 * feature set.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class KmlFeatureset {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The string directory of the output path for the KmzWriter.
   */
  private String directory;

  /**
   * The string name identifying the created document and the feature set in
   * Google Earth.
   */
  private String documentName;

  /**
   * The array of strings that represent the filenames of the icons for each
   * shape in the feature set. All the icon files should be made available in
   * the output directory.
   */
  private String[] iconNames;

  /**
   * The array of strings that represent the names for each shape in the feature
   * set.
   */
  private String[] names;

  /**
   * The list of Shape objects that define the geometries of each of the
   * features in the feature set.
   */
  private List<Shape> shapes;

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the string directory of the output path for the KmzWriter.
   *
   * @return the directory
   */
  public final String getDirectory() {
    return directory;
  }

  /**
   * Sets the string directory of the output path for the KmzWriter.
   *
   * @param directory the directory to set
   */
  public final void setDirectory(String directory) {
    this.directory = directory;
  }

  /**
   * Gets the String name identifying the created document and the feature set
   * in Google Earth.
   *
   * @return the String documentName.
   */
  public final String getDocumentName() {
    return documentName;
  }

  /**
   * Sets the string name identifying the created document and the feature set
   * in Google Earth.
   *
   * @param documentName the String documentName to set.
   */
  public final void setDocumentName(String documentName) {
    this.documentName = documentName;
  }

  /**
   * Gets the array of strings that represent the filenames of the icons for
   * each shape in the feature set. All the icon files should be made available
   * in the output directory.
   *
   * @return the array of String iconNames.
   */
  public final String[] getIcons() {
    return iconNames;
  }

  /**
   * Sets the array of strings that represent the filenames of the icons for
   * each shape in the feature set. All the icon files should be made available
   * in the output directory.
   *
   * @param icons the array of String iconNames to set.
   */
  public final void setIcons(String[] icons) {
    this.iconNames = icons;
  }

  /**
   * Gets the array of strings that represent the names for each shape in the
   * feature set.
   *
   * @return the array of String names.
   */
  public final String[] getNames() {
    return names;
  }

  /**
   * Sets the array of strings that represent the names for each shape in the
   * feature set.
   *
   * @param names the array of String names to set.
   */
  public final void setNames(String[] names) {
    this.names = names;
  }

  /**
   * Gets the list of Shape objects that define the geometries of each of the
   * features in the feature set.
   *
   * @return the list of Shape shapes.
   */
  public final List<Shape> getShapes() {
    return shapes;
  }

  /**
   * Sets the list of Shape objects that define the geometries of each of the
   * features in the feature set.
   *
   * @param shapes the shapes to set.
   */
  public final void setShapes(List<Shape> shapes) {
    this.shapes = shapes;
  }

  //</editor-fold>
}
