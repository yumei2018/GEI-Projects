/*
 * The MIT License
 *
 * Copyright 2013 hdunsford.
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
package gov.ca.water.tin;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.operation.linemerge.LineMerger;
import com.vividsolutions.jts.triangulate.DelaunayTriangulationBuilder;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Progress;
import gov.ca.water.shapelite.Segment;
import gov.ca.water.utils.MissingVertexException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class stores methods that can be used to process TriangleGroup objects.
 *
 * @author hdunsford
 */
public class Analysis {

  //<editor-fold defaultstate="collapsed" desc="Static Logger">
  /**
   * Logger
   */
  private static final Logger logger = Logger.getLogger(Analysis.class.getName());
  //</editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Public Constructor
   */
  public Analysis() {
    super();    
  }
  // </editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Methods">
  /**
   * Gets the contour lines that intersect this segment.
   * @param group
   * @param elevation
   * @param prog
   * @return
   */
  public List<Segment> getContourSegments(TriangleGroup group, double elevation, 
          Progress prog) {
    List<Segment> result = new ArrayList<>();
    if (prog != null) {
      prog.start("Finding segments.", group.getTriangles().size());
    }
    for (Triangle triangle : group.getTriangles()) {
      try {
        if (triangle.intersectsElevation(elevation)) {
          result.addAll(triangle.intersect(elevation));
        }
      } catch (MissingVertexException ex) {
      }
    }
    return result;
  }
  
  /**
   * <p>Convert a list of coordinates into a tin using a {@linkplain
   * DelaunayTriangulationBuilder Delaunay Triangulation Builder}. It converts the polygon
   * geometry generate by the Builder to {@linkplain Triangle Triangles}</p>
   * <p>It returns and empty list if the list of coordinates are undefined or empty. All
   * errors are trapped and logged and a empty list is returned.</p>
   * @param coords the input list of coordinates
   * @return the list of triangles
   */
  public List<Triangle> getTriangles(List<Coordinate> coords) {
    List<Triangle> result = new ArrayList<>();
    try {
      if ((coords != null) && (!coords.isEmpty())) {
        DelaunayTriangulationBuilder builder = new DelaunayTriangulationBuilder();
        GeometryFactory factory = new GeometryFactory();
        builder.setSites(coords);
        Geometry geom = builder.getTriangles(factory);
        for (int iGeom = 0; iGeom < geom.getNumGeometries(); iGeom++) {
          Geometry pg = geom.getGeometryN(iGeom);
          if (pg instanceof Polygon) {
            if ((pg == null) || (pg.isEmpty())) {
              continue;
            }
            Coordinate[] vertices = pg.getCoordinates();
            Coordinate cA = vertices[0];
            Coordinate cB = vertices[1];
            Coordinate cC = vertices[2];
            Coord a = new Coord(cA.x, cA.y, cA.z);
            Coord b = new Coord(cB.x, cB.y, cB.z);
            Coord c = new Coord(cC.x, cC.y, cC.z);
            Triangle triangle = new Triangle(a, b, c);
            result.add(triangle);
          }
        }      
      }
    } catch (Exception exp) {
      result.clear();
      logger.log(Level.WARNING, "{0}.getTriangles Error:\n {1}",
              new Object[]{this.getClass().getSimpleName(), exp.getMessage()});
    }
    return result;
  }
  
  /**
   * <p>Convert a list of line segments to contour lines of type {@linkplain LineString}.
   * </p>
   * <p>It returns an empty list if the list of lien segments are undefined or empty or
   * when an error occurred. All errors are trapepd and logged.</p>
   * @param segs list of line segment
   * @return list of contours
   */
  @SuppressWarnings("rawtypes")
  public List<LineString> getContourLines(List<Segment> segs) {
    List<LineString> result = new ArrayList<>();
    try {
      if ((segs != null) && (!segs.isEmpty())) {
        GeometryFactory gf = new GeometryFactory();
        LineMerger lm = new LineMerger();
        for (Segment seg : segs) {
          LineString ls = gf.createLineString(
                  new Coordinate[]{new Coordinate(seg.P1.X, seg.P1.Y, seg.P1.Z),
                    new Coordinate(seg.P2.X, seg.P2.Y, seg.P2.Z)});
          if ((ls != null) && (!ls.isEmpty())) {
            lm.add(ls);
          }
        }

        Collection col = lm.getMergedLineStrings();
        if ((col != null) && (!col.isEmpty())) {
          for (Object item : col) {
            if (item instanceof LineString) {
              LineString ls = (LineString) item;
              result.add(ls);
            }
          }
        }
      }      
    } catch (Exception exp) {
      result.clear();
      logger.log(Level.WARNING, "{0}.getContourLines Error:\n {1}",
              new Object[]{this.getClass().getSimpleName(), exp.getMessage()});
    }
    return result;
  }
  //</editor-fold>
}
