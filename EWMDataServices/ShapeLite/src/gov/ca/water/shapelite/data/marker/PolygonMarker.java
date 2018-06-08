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

import gov.ca.water.shapelite.symbology.PolygonSymbolizer;
import java.awt.Point;
import java.awt.geom.GeneralPath;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.NonNull;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.data.Projector;
import gov.ca.water.shapelite.map.Mercator;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PolygonMarker extends FeatureMarker<PolygonSymbolizer> {

  /**
   * Creates a new instance of a MarkerPath based on a shape and a name.
   *
   * @param shape The Polygon shape object to use for this marker.
   */
  public PolygonMarker(@NonNull Shape shape) {
    super(new Feature(shape));
    if (shape == null) {
      throw new IllegalArgumentException("Parameter shape cannot be null.");
    }
    super.setName("Polygon");
  }

  /**
   * Creates a new instance of a MarkerPath based on a shape and a name.
   *
   * @param shape The Polygon shape object to use for this marker.
   * @param name The String name to assign to this polygon marker.
   */
  public PolygonMarker(@NonNull Shape shape, String name) {
    super(new Feature(shape));
    if (shape == null) {
      throw new IllegalArgumentException("Parameter shape cannot be null.");
    }
    super.setName(name);
    super.setPopupText(name);
  }

  /**
   * Creates a new instance of a MarkerPath based on a shape and a name.
   *
   * @param feature The Polygon feature object to use for this marker. This
   * should not be null.
   */
  public PolygonMarker(@NonNull Feature feature) {
    super(feature);
    super.setName("Polygon");
  }

  /**
   * Creates a new instance of a MarkerPath based on a shape and a name.
   *
   * @param feature The Polygon feature object to use for this marker. This
   * should not be null.
   * @param name The String name to assign to this polygon marker.
   */
  public PolygonMarker(@NonNull Feature feature, String name) {
    super(feature);
    super.setName(name);
    super.setPopupText(name);
  }

  /**
   * Creates a new instance of a MarkerPath based on a shape and a name.
   *
   * @param feature The Polygon feature object to use for this marker. This
   * should not be null.
   * @param symbolizer The symbolizer to use for this marker.
   */
  public PolygonMarker(@NonNull Feature feature, PolygonSymbolizer symbolizer) {
    super(feature, symbolizer);
    super.setName("Polygon");
  }

  /**
   * Creates a new instance of a MarkerPath based on a shape and a name.
   *
   * @param feature The Polygon feature object to use for this marker. This
   * should not be null.
   * @param name The String name to assign to this polygon marker.
   * @param symbolizer The symbolizer to use for this marker.
   */
  public PolygonMarker(@NonNull Feature feature, String name, PolygonSymbolizer symbolizer) {
    super(feature, symbolizer);
    super.setName(name);
    super.setPopupText(name);
  }

  /**
   * Gets the "GeneralPath" which can be useful for rendering the path.
   *
   * @param proj the Projector to use in generating the Path
   * @return the path of the Polygon.
   */
  public final Optional<GeneralPath> getPath(Projector proj) {
    Shape polyShp = this.getShape();
    if (polyShp.getShapeType() == ShapeType.NullShape) {
      return Optional.empty();
    }
    GeneralPath path = new GeneralPath();
    if (!polyShp.isEmpty()) {
      for (Part part : polyShp.getParts()) {
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
