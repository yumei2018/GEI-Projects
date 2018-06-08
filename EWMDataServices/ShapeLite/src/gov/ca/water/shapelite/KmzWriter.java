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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * This class supports the raw writer methods for creating KMZ files. It does
 * not actually zip the files, so the images can be added after the fact. The
 * output folder can then be zipped and the extension changed to kmz.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class KmzWriter {

  /**
   * Hide constructor.
   */
  private KmzWriter() {

  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Exports a KMZ file where the style is specified for each point, so that
   * each point can look different. Each unique icon will be wrapped into a
   * distinct style.
   *
   * @param pointData The KmlFeatureset of points to to turn into a KMZ output
   * folder.
   * @throws IOException This can throw an IOException if the folder can't be
   * found, or if there is a problem opening the doc.kml file.
   */
  public static void exportMultistylePoints(KmlFeatureset pointData)
          throws IOException {
    PrintWriter output = null;
    try {
      output = openWriter(pointData.getDirectory() + File.separator
              + "doc.kml");
      output.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
      output.println("<kml xmlns=\"http://earth.google.com/kml/2.2\">\n");
      output.println("<Document id=\"" + pointData.getDocumentName()
              + "\">\n");
      output.println("  <name>" + pointData.getDocumentName()
              + "</name>\n");
      output.println("  <Snippet></Snippet>");
      output.println("  <Folder id=\"FeatureLayer0\">\n");
      output.println("    <name>" + pointData.getDocumentName()
              + "</name>\n");
      output.println("  <Snippet></Snippet>\n");
      List<String> uniqueStyles = new ArrayList<>();
      for (int i = 0; i < pointData.getShapes().size(); i++) {
        Coord c = pointData.getShapes().get(i).first();
        String x = Double.toString(c.getX());
        String y = Double.toString(c.getY());
        String z = Double.toString(c.get(Coord.Index.Z));
        String style = pointData.getIcons()[i];
        if (!uniqueStyles.contains(style)) {
          uniqueStyles.add(style);
        }
        output.println("    <Placemark>\n");
        output.println("      <name>" + pointData.getNames()[i]
                + "</name>\n");
        output.println("      <styleUrl>#IconStyle"
                + Integer.toString(uniqueStyles.indexOf(style))
                + "</styleUrl>\n");
        output.println("      <Snippet></Snippet>\n");
        output.println("      <Point>\n");
        output.println("        <extrude>0</extrude>\n");
        output.println("        <altitudeMode>absolute"
                + "</altitudeMode>\n");
        output.println("        <coordinates> " + x + "," + y + ","
                + z + "</coordinates>\n");
        output.println("      </Point>\n");
        output.println("    </Placemark>\n");
      }
      output.println(" </Folder>\n");
      for (String style : uniqueStyles) {
        output.println("   <Style id=\"IconStyle"
                + Integer.toString(uniqueStyles.indexOf(style))
                + "\">\n");
        output.println("       <IconStyle>\n");
        output.println("         <Icon><href>" + style
                + ".png</href></Icon>\n");
        output.println("         <scale>1.000000</scale>\n");
        output.println("       </IconStyle>\n");
        output.println("       <LabelStyle>\n");
        output.println("         <color>ff000000</color>\n");
        output.println("         <scale>1</scale>\n");
        output.println("       </LabelStyle>\n");
        output.println("   </Style>\n");
      }
      output.println("</Document>\n");
      output.println("</kml>\n");

    } finally {
      if (output != null) {
        try {
          output.flush();
          output.close();
        } catch (Exception ex) {
          ex.printStackTrace(System.out);
        }
      }
    }
  }

  /**
   * Exports a KMZ directory of files where all the points have the same style.
   *
   * @param pointData The point data to export.
   */
  public static void exportPoints(KmlFeatureset pointData) {
    StringBuilder sb = new StringBuilder();
    sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
    sb.append("<kml xmlns=\"http://earth.google.com/kml/2.2\">\n");
    sb.append("Document id=\"").append(pointData.getDocumentName()).
            append("\">\n");
    sb.append("  <name>").append(pointData.getDocumentName()).
            append("</name>\n");
    sb.append("  <Snippet></Snippet>");
    sb.append("  <Folder id=\"FeatureLayer0\">\n");
    sb.append("    <name>").append(pointData.getDocumentName()).
            append("</name>\n");
    sb.append("  <Snippet></Snippet>\n");
    for (int i = 0; i < pointData.getShapes().size(); i++) {
      Coord c = pointData.getShapes().get(i).first();
      String x = Double.toString(c.getX());
      String y = Double.toString(c.getY());
      String z = Double.toString(c.get(Coord.Index.Z));
      sb.append("    <Placemark>\n");
      sb.append("      <name>").append(pointData.getNames()[i]).
              append("</name>\n");
      sb.append("      <styleUrl>#IconStyle00</styleUrl>\n");
      sb.append("      <Snippet></Snippet>\n");
      sb.append("      <Point>\n");
      sb.append("        <extrude>0</extrude>\n");
      sb.append("        <altitudeMode>absolute</altitudeMode>\n");
      sb.append("        <coordinates> ").append(x).append(",").
              append(y).append(",").append(z).append("</coordinates>\n");
      sb.append("      </Point>\n");
      sb.append("    </Placemark>\n");
    }
    sb.append(" </Folder>");
    sb.append("   <Style id=\"IconStyle00\">");
    sb.append("       <IconStyle>");
    sb.append("         <Icon><href>000000.png</href></Icon>");
    sb.append("         <scale>1.000000</scale>");
    sb.append("       </IconStyle>");
    sb.append("       <LabelStyle>");
    sb.append("         <color>ff000000</color>");
    sb.append("         <scale>0.666667</scale>");
    sb.append("       </LabelStyle>");
    sb.append("   </Style>");
    sb.append("</Document>");
    sb.append("</kml>");
  }

  /**
   * Creates a PrintWriter configured to write to the specified filename.
   *
   * @param filename The string doc.kml file to write to.
   * @return a PrintWriter class for the specified file.
   * @throws IOException Occurs if there is a problem opening the file for
   * writing.
   */
  private static PrintWriter openWriter(String filename) throws IOException {
    File f = new File(filename);
    File dir = f.getParentFile();
    if (!dir.exists()) {
      dir.mkdirs();
    }
    if (f.exists()) {
      f.delete();
    }
    return new PrintWriter(new BufferedWriter(new FileWriter(f)));
  }

  //</editor-fold>
}
