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

import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.io.ShapefileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This generates points with arrow symbols that are organized in the direction
 * of the lines in a line shapefile. This can be exported using the KMZWriter to
 * show the result in Google earth.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class LineDirections {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The output folder for creating the KML content.
   */
  private String directory;

  /**
   * The string name of the final dataset in Google Earth.
   */
  private String documentName;

  /**
   * The line shapefile that provides the data for this class to use for
   * determining where the points should be drawn and what directions the arrows
   * should point.
   */
  private String lineShapefile;

  /**
   * The String identifying the column of attributes in the line shapefile that
   * should be used to create the labels for the points in the output KMZ
   * feature set.
   */
  private String nameField;

  /**
   * One eighth of a circle, or 45 degrees.
   */
  private static final int EIGHTH = 45;

  /**
   * 90 Degrees.
   */
  private static final int QUARTER = 90;
  /**
   * 180 Degrees.
   */
  private static final int HALF = 180;
  /**
   * 360 Degrees.
   */
  private static final int FULL = 360;

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * This method actually generates the FeatureSet according to the rules that
   * the points should occur midway along the lines, touching the lines, and
   * with an arrow that is oriented in the direction that the line is moving.
   *
   * @return A KmlFeatureset ready to be written.
   * @throws FileNotFoundException Occurs if the file could not be found for the
   * specified line shapefile that is necessary for building this output feature
   * set.
   * @throws IOException Occurs if there is a problem reading the line
   * shapefile.
   */
  public final KmlFeatureset createFeatureset() throws FileNotFoundException,
          IOException {
    ShapefileReader lineReader = new ShapefileReader();
    lineReader.open(lineShapefile);
    List<Shape> shapes = lineReader.getShapes();
    KmlFeatureset result = new KmlFeatureset();
    result.setDirectory(directory);
    result.setDocumentName(documentName);
    List<String> icons = new ArrayList<>();
    List<String> names = new ArrayList<>();
    List<Shape> points = new ArrayList<>();
    Optional<String[]> labels = lineReader.getAttributes().getFieldStrings(nameField);
    int i = 0;
    for (Shape shape : shapes) {
      PartTree tree = new PartTree();
      tree.add(shape, shape.first());
      for (PartTreeNode node : tree.getConnectedNodes()) {
        int iNode = 0;
        List<Segment> segs = node.getPart().getSegments();
        Segment middle = segs.get(segs.size() / 2);
        Coord center = new CoordXY((middle.getP2().getX() + middle.getP1().getX())
                / 2.0, (middle.getP1().getY()
                + middle.getP2().getY()) / 2.0);
        points.add(new Shape(center));
        Vector v = middle.toVector();
        double heading = QUARTER - HALF * Math.atan2(v.getY(), v.getX())
                / Math.PI;
        if (heading < 0) {
          heading += FULL;
        }
        long head = EIGHTH * Math.round(heading / EIGHTH);
        String headName = Long.toString(head);
        icons.add("Direction" + headName);
        String name = "Part " + Integer.toString(iNode);
        if(labels.isPresent()){
          name = labels.get()[i] + name;
        }
        names.add(name);
        iNode++;
      }
      i++;
    }
    result.setIcons(icons.toArray(new String[0]));
    result.setNames(names.toArray(new String[0]));
    result.setShapes(points);
    return result;
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the output folder for creating the KML content.
   *
   * @return the directory.
   */
  public final String getDirectory() {
    return directory;
  }

  /**
   * Sets the output folder for creating the KML content.
   *
   * @param directory the directory to set.
   */
  public final void setDirectory(String directory) {
    this.directory = directory;
  }

  /**
   * Gets the line shapefile that provides the data for this class to use for
   * determining where the points should be drawn and what directions the arrows
   * should point.
   *
   * @return the lineShapefile.
   */
  public final String getLineShapefile() {
    return lineShapefile;
  }

  /**
   * Sets the line shapefile that provides the data for this class to use for
   * determining where the points should be drawn and what directions the arrows
   * should point.
   *
   * @param lineShapefile the lineShapefile to set.
   */
  public final void setLineShapefile(String lineShapefile) {
    this.lineShapefile = lineShapefile;
  }

  /**
   * Gets the string name of the final dataset in Google Earth.
   *
   * @return the documentName.
   */
  public final String getDocumentName() {
    return documentName;
  }

  /**
   * Sets the string name of the final dataset in Google Earth.
   *
   * @param documentName the documentName to set.
   */
  public final void setDocumentName(String documentName) {
    this.documentName = documentName;
  }

  /**
   * Gets the String identifying the column of attributes in the line shapefile
   * that should be used to create the labels for the points in the output KMZ
   * feature set.
   *
   * @return the nameField.
   */
  public final String getNameField() {
    return nameField;
  }

  /**
   * Sets the String identifying the column of attributes in the line shapefile
   * that should be used to create the labels for the points in the output KMZ
   * feature set.
   *
   * @param nameField the nameField to set.
   */
  public final void setNameField(String nameField) {
    this.nameField = nameField;
  }

  //</editor-fold>
}
