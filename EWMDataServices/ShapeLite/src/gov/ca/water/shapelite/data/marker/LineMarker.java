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
package gov.ca.water.shapelite.data.marker;

import gov.ca.water.shapelite.symbology.LineSymbolizer;
import java.awt.Point;
import java.awt.geom.GeneralPath;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.NonNull;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.data.Projector;
import gov.ca.water.shapelite.map.Mercator;
import java.awt.Polygon;
import java.awt.geom.Path2D;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class LineMarker extends FeatureMarker<LineSymbolizer> {

  //<editor-fold defaultstate="collapsed" desc="Constructors">
  /**
   * Creates a new instance of a MarkerPath based on a shape and a name.
   *
   */
  public LineMarker() {
    super(new Feature(new Shape(ShapeType.PolyLine)));
    Part part = new Part();
    part.getCoordinates().add(new CoordXY(0, 0));
    part.getCoordinates().add(new CoordXY(1, 1));
    super.getShape().getParts().add(part);
    this.setName("Line");
  }

  /**
   * Creates a new instance of a MarkerPath based on a shape and a name.
   *
   * @param name
   * @param x1
   * @param x2
   */
  public LineMarker(String name, Coord x1, Coord x2) {
    super(new Feature(new Shape(ShapeType.PolyLine)));
    Part part = new Part();
    part.getCoordinates().add(x1);
    part.getCoordinates().add(x2);
    super.getShape().getParts().add(part);
    this.setName(name);
  }

  /**
   * Creates a new instance of a MarkerPath based on a shape and a name.
   *
   * @param shape The linear Shape object to use for the marker path. This
   * should not be null, but can be a NullShape type.
   * @param name The string name to assign to the marker path.
   */
  public LineMarker(@NonNull Shape shape, String name) {
    super(new Feature(shape));
    this.setName(name);
  }

  /**
   * Creates a new instance of a MarkerPath based on a shape and a name.
   *
   * @param shape The linear Shape object to use for the marker path. This
   * should not be null, but can be a NullShape type.
   * @param name The string name to assign to the marker path.
   * @param symbolizer The LineSymbolizer to control line drawing.
   */
  public LineMarker(@NonNull Shape shape, String name, LineSymbolizer symbolizer) {
    super(new Feature(shape), symbolizer);
    this.setName(name);
  }

  /**
   * Creates a new instance of a MarkerPath based on a shape and a name.
   *
   * @param feature The linear Feature object to use for the marker path. This
   * should not be null.
   */
  public LineMarker(@NonNull Feature feature) {
    super(feature);
    this.setName("Line");
  }

  /**
   * Creates a new instance of a MarkerPath based on a shape and a name.
   *
   * @param feature The linear Feature object to use for the marker path. This
   * should not be null.
   * @param name The string name to assign to the marker path.
   */
  public LineMarker(@NonNull Feature feature, String name) {
    super(feature);
    this.setName(name);
  }

  /**
   * Creates a new instance of a MarkerPath based on a shape and a name.
   *
   * @param feature The linear Feature object to use for the marker path. This
   * should not be null.
   * @param name The string name to assign to the marker path.
   * @param symbolizer The symbolizer to use.
   */
  public LineMarker(@NonNull Feature feature, String name, LineSymbolizer symbolizer) {
    super(feature, symbolizer);
    this.setName(name);
  }

  //</editor-fold>
  /**
   * Gets the "GeneralPath" which can be useful for rendering the path.
   *
   * @param proj the Projector to use in generating the Path
   * @return the path of the Polygon.
   */
  public final Optional<GeneralPath> getPath(Projector proj) {
    Shape lineShp = this.getShape();
    if (lineShp == null) {
      return Optional.empty();
    }
    Polygon l = new Polygon();


    GeneralPath path = new GeneralPath();


    if (!lineShp.isEmpty()) {
      for (Part part : lineShp.getParts()) {
        boolean first = true;
        for (Coord coord : part.getCoordinates()) {
          // First project the geographic coordinate into mercator coordinates
          Coord merc = Mercator.toMerc(coord);
          Point p = proj.getPoint(merc);
          if (first) {
            // On the first point of the segment, move the path to the location.
            path.moveTo(p.x, p.y);
            first = false;
          } else {
            path.lineTo(p.x, p.y);
          }
        }
      }
    }
    return Optional.of(path);
  }
  /**
   * Gets the "Path2D" which can be useful for detecting click hits.
   *
   * @param proj the Projector to use in generating the Path
   * @return the path of the Polygon.
   */
  public final Optional<Path2D> getPolyline(Projector proj) {
    Shape lineShp = this.getShape();
    if (lineShp == null) {
      return Optional.empty();
    }
    Path2D path = new Path2D.Double();
    if (!lineShp.isEmpty()) {
      for (Part part : lineShp.getParts()) {
        boolean first = true;
        for (Coord coord : part.getCoordinates()) {
          // First project the geographic coordinate into mercator coordinates
          Coord merc = Mercator.toMerc(coord);
          Point p = proj.getPoint(merc);
          if (first) {
            // On the first point of the segment, move the path to the location.
            path.moveTo(p.x, p.y);
            first = false;
          } else {
            path.lineTo(p.x, p.y);
          }
        }
      }
    }
    return Optional.of(path);
  }

}
