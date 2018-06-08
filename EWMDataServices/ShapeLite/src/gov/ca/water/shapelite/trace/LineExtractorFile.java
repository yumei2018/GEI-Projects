/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.ca.water.shapelite.trace;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.DefaultEnvelope;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.io.ShapefileChannelScanner;
import gov.ca.water.shapelite.io.ShapefileScannerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hdunsford
 */
public class LineExtractorFile implements LineExtractor {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  private final String shapefile;
  private int numShapes;

  //</editor-fold>
  /**
   * Creates a new instance of the LineExtractor class.
   *
   * @param shapefile
   */
  public LineExtractorFile(String shapefile) {
    this.shapefile = shapefile;
  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Returns an array of interleaved X, Y values.
   *
   * @param startX The x ordinate of the start point
   * @param startY the y ordinate of the start point
   * @param endX the x ordinate of the end point
   * @param endY the y ordinate of the end point
   * @param tolerance The distance by which to extend the envelope when
   * searching for possible intersecting levee segments.
   * @return A double array of points.
   */
  @Override
  public double[] extract(double startX, double startY, double endX, double endY, double tolerance) {
    Shape line = extract(new CoordXY(startX, startY), new CoordXY(endX, endY), tolerance);
    if (line == null) {
      return null;
    }
    double[] result = new double[line.getCoordinates().size() * 2];
    int i = 0;
    for (Coord c : line.getCoordinates()) {
      result[i] = c.getX();
      i++;
      result[i] = c.getY();
      i++;
    }
    return result;
  }

  /**
   * Given a line shapefile, this will attempt to find a path between the start
   * and end points.
   *
   * @param start
   * @param end
   * @param tolerance
   * @return A polyline shape made from as many parts or shapes as necessary to
   * create a path from the start to the end, if possible.
   */
  @Override
  public Shape extract(Coord start, Coord end, double tolerance) {
    try {
      ShapefileChannelScanner scanner = new ShapefileChannelScanner(shapefile);
      // Map only the shapes that are needed, instead of mapping the whole shapefile.
      scanner.close();
      numShapes = scanner.getNumShapes();
      Envelope aoi = new DefaultEnvelope(start); // area of interest
      aoi.expandToInclude(end);

      aoi = new DefaultEnvelope(aoi.getMin().getX() - tolerance, aoi.getMin().getY()
          - tolerance, aoi.getMax().getX() + tolerance, aoi.getMax().getY()
          + tolerance);
      List<Shape> aoiShapes = new ArrayList<>();
      for (int i = 0; i < numShapes; i++) {
        // scanner envelopes are all in memory, even though the shapes are not.
        if (scanner.getEnvelopeXY(i).intersects2D(aoi)) {
          aoiShapes.add(scanner.readShape(i));
        }
      }
      Shape result = new Shape(ShapeType.PolyLine);
      Part prt = new Part();
      if (aoiShapes.isEmpty()) {
        prt.getCoordinates().add(start);
        prt.getCoordinates().add(end);
      } else {
        PartTree tree = new PartTree(start, end);
        tree.addShapes(aoiShapes);
        List<Coord> coords = tree.getPath();
        prt.getCoordinates().addAll(coords);
      }

      result.getParts().add(prt);
      result.calculateBounds();
      return result;

    } catch (IOException | ShapefileScannerException ex) {
      Logger.getLogger(LineExtractorFile.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  //</editor-fold>
}
