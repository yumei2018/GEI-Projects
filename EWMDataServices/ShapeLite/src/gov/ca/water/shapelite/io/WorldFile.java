package gov.ca.water.shapelite.io;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.DefaultEnvelope;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.Optional;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class WorldFile {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The X coordinate of the center of the top left pixel of the image. (This assumes DX
   * is positive, which it usually is.)
   */
  private double x;
  /**
   * The Y coordinate of the center of the top left pixel of the image. (This assumes DY
   * is negative, which it usually is.)
   */
  private double y;
  /**
   * The change in X per column index.
   */
  private double dx;
  /**
   * The change in Y per row index.
   */
  private double dy;
  /**
   * The change in x per row index. This is usually 0, unless the image is at an angle.
   */
  private double sx;
  /**
   * The change in y per column index. This is usually 0, unless the image is at an angle.
   */
  private double sy;

  /**
   * An Error logger.
   */
  private static final java.util.logging.Logger LOGGER
          = java.util.logging.Logger.getLogger(WorldFile.class.getName());

  /**
   * The current projection.
   */
  private String currentProjection;

  /**
   * The filename for this world file.
   */
  private String filename;

  //</editor-fold>
  /**
   * Creates a new instance of a world file.
   */
  public WorldFile() {

  }

  /**
   * Creates a new instance of the ProjectionWriter class.
   *
   * @param dx - cell width
   * @param sx - skew x
   * @param sy - skew y
   * @param dy - cell height (usually negative)
   * @param x - x coordinate of the center of the top left cell.
   * @param y - y coordinate of the center of the top left cell.
   *
   */
  public WorldFile(double dx, double sx, double sy, double dy, double x, double y) {
    this.dx = dx;
    this.sx = sx;
    this.sy = sy;
    this.dy = dy;
    this.x = x;
    this.y = y;
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * @return the currentProjection
   */
  public final String getCurrentProjection() {
    return currentProjection;
  }

  /**
   * @param currentProjection the currentProjection to set
   */
  public final void setCurrentProjection(String currentProjection) {
    this.currentProjection = currentProjection;
  }

  /**
   * @return the x
   */
  public final double getX() {
    return x;
  }

  /**
   * @param x the x to set
   */
  public final void setX(double x) {
    this.x = x;
  }

  /**
   * @return the y
   */
  public final double getY() {
    return y;
  }

  /**
   * @param y the y to set
   */
  public final void setY(double y) {
    this.y = y;
  }

  /**
   * @return the dx
   */
  public final double getDx() {
    return dx;
  }

  /**
   * @param dx the dx to set
   */
  public final void setDx(double dx) {
    this.dx = dx;
  }

  /**
   * @return the dy
   */
  public final double getDy() {
    return dy;
  }

  /**
   * @param dy the dy to set
   */
  public final void setDy(double dy) {
    this.dy = dy;
  }

  /**
   * @return the sx
   */
  public final double getSx() {
    return sx;
  }

  /**
   * @param sx the sx to set
   */
  public final void setSx(double sx) {
    this.sx = sx;
  }

  /**
   * @return the sy
   */
  public final double getSy() {
    return sy;
  }

  /**
   * @param sy the sy to set
   */
  public final void setSy(double sy) {
    this.sy = sy;
  }

  /**
   * Gets the filename for this world file.
   *
   * @return the filename
   */
  public final String getFilename() {
    return filename;
  }

  /**
   * Sets the filename for this world file.
   *
   * @param filename the filename to set
   */
  public final void setFilename(String filename) {
    this.filename = filename;
  }

  //</editor-fold>
  /**
   * Currently this ignores skew.
   *
   * @param coordinate The geographic coordinate in unites that match the world file.
   * @return The Image row and column as a Point.
   */
  public final Point getRowCol(Coord coordinate) {
    Point result = new Point();
    result.x = (int) ((coordinate.getX() - x) / dx);
    result.y = (int) ((coordinate.getY() - y) / dy);
    return result;
  }

  /**
   * This currently ignores skew to be consistent with getRowCol.
   *
   * @param point The Point in image coordinates to get the Coordinate for.
   * @return The coordinate of the point.
   */
  public final CoordXY getCoord(Point point) {
    CoordXY result = new CoordXY();
    result.setX(x + dx * point.x);
    result.setY(y + dy * point.y);
    return result;
  }

  /**
   * This negates the rotation terms and only looks at the dx, dy, x and y terms.
   *
   * @param width The integer width.
   * @param height The integer height.
   * @return Envelope.
   */
  public final Envelope getEnvelope(int width, int height) {
    double x1 = x;
    double x2 = x + width * dx;
    double y1 = y;
    double y2 = y + height * dy;
    return new DefaultEnvelope(Math.min(x1, x2), Math.min(y1, y2),
            Math.max(x1, x2), Math.max(y1, y2));
  }

  /**
   * Reads the content from the specified file in order to define a world file.
   *
   * @param filename The string filename to read.
   */
  public final void read(String filename) {
    this.filename = filename;
    Scanner scanner = null;
    try {
      File file = new File(filename);
      if (file.exists()) {
        scanner = new Scanner(file);
        if (scanner.hasNext()) {
          dx = Double.parseDouble(scanner.next());
        }
        if (scanner.hasNext()) {
          sx = Double.parseDouble(scanner.next());
        }
        if (scanner.hasNext()) {
          sy = Double.parseDouble(scanner.next());
        }
        if (scanner.hasNext()) {
          dy = Double.parseDouble(scanner.next());
        }
        if (scanner.hasNext()) {
          x = Double.parseDouble(scanner.next());
        }
        if (scanner.hasNext()) {
          y = Double.parseDouble(scanner.next());
        }
      }
    } catch (FileNotFoundException | NumberFormatException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
    } finally {
      if (scanner != null) {
        try {
          scanner.close();
        } catch (Exception ex) {
          Logger.getLogger(WorldFile.class.getName()).log(Level.WARNING, ex.getMessage(), ex);
        }
      }
    }

  }

  /**
   * Reads auxiliary file to get ESRI projection string.
   *
   * @param auxilaryFile
   * @return
   * @throws Exception
   */
  public final String readAuxFile(String auxilaryFile) throws Exception {
    BufferedReader reader = new BufferedReader(new FileReader(new File(auxilaryFile)));
    StringBuilder text = new StringBuilder();
    try {
      String line;
      while ((line = reader.readLine()) != null) {
        text.append(line);
      }
    } finally {
      reader.close();
    }
    String projectionString = parseAux(text.toString());
    return projectionString;
  }

  /**
   * Parses the XML syntax to create an ESRI syntax string.
   *
   * @param auxText The XML syntax coordinate system text.
   * @return The ESRI WKT format projection text.
   */
  public final String parseAux(String auxText) {
    return auxText.replace("<PAMDataset>", "").replace("</PAMDataset>", "")
            .replace("<SRS>", "").replace("</SRS>", "")
            .replace("&quot;", "\"")
            .trim();
  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Writes the projection content of the current projection to the file.
   *
   * @param filename the filename of the projection file to export.
   * @throws java.io.IOException If there was a problem writing to the world file.
   */
  public final void write(String filename) throws Exception {
    File f = new File(filename);
    if (f.exists()) {
      f.delete();
    }
    File dir = f.getParentFile();
    if (!dir.exists()) {
      dir.mkdirs();
    }
    BufferedWriter out = null;
    try {
      out = new BufferedWriter(new FileWriter(filename));
      out.write(dx + "\n");
      out.write(sx + "\n");
      out.write(sy + "\n");
      out.write(dy + "\n");
      out.write(x + "\n");
      out.write(y + "\n");
      out.flush();
      out.close();
    } catch (IOException ex) {
      LOGGER.log(Level.WARNING, ex.getMessage(), ex);
      if (out != null) {
        out.close();
      }
      throw ex;
    } finally {
      if (out != null) {
        out.close();
      }
    }

    try {
      int lastIndexOf = filename.lastIndexOf(".");
      if (lastIndexOf > -1) {
        String ext = filename.substring(lastIndexOf);
        Optional<String> maybePrj = imageExtension(ext);
        if (maybePrj.isPresent()) {
          writePNGAUX(filename, ext, maybePrj);
        }
      }
    } catch (Exception ex) {
      LOGGER.log(Level.WARNING, ex.getMessage(), ex);
      throw ex;
    }
  }

  /**
   * Writes png auxiliary file which is used for specifying the coordinate system.
   * <p>
   * Example of projection: <PAMDataset>\n"
   * <SRS>GEOGCS[&quot;GCS_WGS_1984&quot;,DATUM[&quot;WGS_
   * 1984&quot;,SPHEROID[&quot;WGS_1984&quot;,6378137.0,
   * 298.257223563]],PRIMEM[&quot;Greenwich&quot;,0.0],
   * UNIT[&quot;Degree&quot;,0.0174532925199433]]</SRS>\n
   * </PAMDataset>\n
   * </p>
   *
   * @param out
   * @param filename1
   * @param ext
   * @param maybePrj
   * @throws Exception
   */
  private void writePNGAUX(String filename1, String ext, Optional<String> maybePrj)
          throws Exception {

    try (BufferedWriter out = new BufferedWriter(new FileWriter(filename1.replace(ext, maybePrj.get() + ".aux.xml")))) {
      out.write("<PAMDataset><SRS>" + this.currentProjection.replace("\"", "&quot;") + "</SRS></PAMDataset>");
      out.flush();
    }
  }

  /**
   * Given a world file extension, this attempts to infer the image filename.
   *
   * @param extension The String extension, including period.
   * @return The string extension including period of the likely image format.
   */
  private static Optional<String> imageExtension(String extension) {
    String result;
    switch (extension) {
      case ".pgw":
        result = ".png";
        break;
      case ".bpw":
        result = ".bmp";
        break;
      case ".jgw":
        result = ".jpg";
        break;
      case ".gfw":
        result = "gif";
        break;
      case ".tfw":
        result = ".tif";
        break;
      default:
        result = null;
    }
    return Optional.ofNullable(result);
  }

  /**
   * Writes the values to the fileanme.
   *
   * @throws java.io.IOException If there was an error writing to the filename.
   */
  public final void save() throws Exception {
    if (filename != null) {
      write(filename);
    } else {
      throw new IOException("The worldfile name was not specified.");
    }
  }

}
