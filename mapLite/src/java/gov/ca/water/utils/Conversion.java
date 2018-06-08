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
package gov.ca.water.utils;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.CoordinateSequence;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.PrecisionModel;
import com.vividsolutions.jts.geom.impl.CoordinateArraySequence;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class stores methods that convert utilities to convert between
 * TIN format and other data sources, or else to generate triangles.
 * @author hdunsford
 */
public class Conversion {
  
  //<editor-fold defaultstate="collapsed" desc="Static Logger">
  /**
   * Protected Static Logger object for logging errors, warnings, and info messages.
   */
  private static final Logger logger = Logger.getLogger(Conversion.class.getName());
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Public Static Fields">
  /**
   * The JTS PrecisionModel model to use for all conversions
   */
  public static PrecisionModel.Type JtsPrecModelType = PrecisionModel.FLOATING;
  //</editor-fold>
    
  //<editor-fold defaultstate="collapsed" desc="Coordinate Conversions">
  /**
   * Converts a list of shapeliteCoordinates to a list of JTS coordinates.
   * @param shapeliteCoords
   * @return
   */
  public static List<Coordinate> toJtsCoords(List<Coord> shapeliteCoords){
    List<Coordinate> result = new ArrayList<>();
    if ((shapeliteCoords != null) && (!shapeliteCoords.isEmpty())) {
      for(Coord coordinate : shapeliteCoords){
        result.add(new Coordinate(coordinate.X, coordinate.Y, coordinate.Z));
      }
    }
    return result;
  }
  
  /**
   * Converts a list of shapeliteCoordinates to a list of JTS coordinates.
   * @param shapeliteCoords
   * @return
   */
  public static List<Coordinate> toJtsCoords(Coord...shapeliteCoords){
    List<Coordinate> result = new ArrayList<>();
    if ((shapeliteCoords != null) && (shapeliteCoords.length > 0)) {
      for(Coord coordinate : shapeliteCoords){
        result.add(new Coordinate(coordinate.X, coordinate.Y, coordinate.Z));
      }
    }
    return result;
  }
  
  /**
   * Converts the list of JTS coordinates to a list of ShapeLite coordinates
   * @param jtsCoords
   * @return
   */
  public static List<Coord> toShapeLiteCoords(List<Coordinate> jtsCoords){
    List<Coord> result = new ArrayList<>();
    if ((jtsCoords != null) && (!jtsCoords.isEmpty())) {
      for(Coordinate coordinate : jtsCoords){
        result.add(new Coord(coordinate.x, coordinate.y, coordinate.z));
      }
    }
    return result;
  }
   
  /**
   * Converts the list of JTS coordinates to a list of ShapeLite coordinates
   * @param jtsCoords
   * @return
   */
  public static List<Coord> toShapeLiteCoords(Coordinate...jtsCoords){
    List<Coord> result = new ArrayList<>();
    if ((jtsCoords != null) && (jtsCoords.length > 0)) {
      for(Coordinate coordinate : jtsCoords){
        result.add(new Coord(coordinate.x, coordinate.y, coordinate.z));
      }
    }
    return result;
  }
  
  /**
   * Converts the list of line strings as parts for an individual shape.
   * @param jtsLineStrings
   * @return
   */
  public static Shape toMultiPartShapeZ(List<LineString> jtsLineStrings){
    Shape result = new Shape(ShapeType.PolyLineZ);
    if ((jtsLineStrings != null) && (!jtsLineStrings.isEmpty())) {
      for(LineString ls : jtsLineStrings){
        List<Coordinate> jtsCoords = Arrays.asList(ls.getCoordinates());
        List<Coord> coords = toShapeLiteCoords(jtsCoords);
        Part part = new Part();
        part.setCoordinates(coords);
        result.getParts().add(part);
      }
    }
    result.calculateBounds();
    return result;
  }
  
  /**
   * Converts the list of line strings as parts for an individual shape.
   * @param jtsLineStrings
   * @return
   */
  public static Shape toMultiPartShapeZ(LineString...jtsLineStrings){
    Shape result = new Shape(ShapeType.PolyLineZ);
    if ((jtsLineStrings != null) && (jtsLineStrings.length > 0)) {
      for(LineString ls : jtsLineStrings){
        List<Coordinate> jtsCoords = Arrays.asList(ls.getCoordinates());
        List<Coord> coords = toShapeLiteCoords(jtsCoords);
        Part part = new Part();
        part.setCoordinates(coords);
        result.getParts().add(part);
      }
    }
    result.calculateBounds();
    return result;
  }
      
  /**
   * Convert a ShapeLite Polygon to a JTS MultiPolygon.
   * @param shapeLitePolygon the shapeLite polygon shape
   * @return the JTS MultiPolygon - can be empty if the shapeLite polygon is undefined or 
   * empty
   */
  public static MultiPolygon toJtsPolygon(Shape splPolygon) {
    MultiPolygon result = null;
    try {
      List<Part> parts = null;
      if ((splPolygon != null)
              && ((splPolygon.getShapeType() == ShapeType.Polygon)
              || (splPolygon.getShapeType() == ShapeType.PolygonZ))
              && ((parts = splPolygon.getParts()) != null) && (!parts.isEmpty())) {
        List<Polygon> polygonList = new ArrayList<>();
        PrecisionModel precModel = new PrecisionModel(Conversion.JtsPrecModelType);
        GeometryFactory factory =
                new GeometryFactory(precModel, splPolygon.getFid());
        for (Part shellPart : parts) {
          LinearRing shell = null;
          if ((shellPart.isHole())
                  || ((shell = Conversion.toJtsLinearRing(shellPart)) == null)) {
            continue;
          }

          List<LinearRing> holes = null;
          for (Part holePart : parts) {
            LinearRing jtsHole = null;
            if ((!holePart.isHole()) || (!shellPart.intersects(holePart))
                    || ((jtsHole = toJtsLinearRing(holePart)) == null)) {
              continue;
            }

            if (holes == null) {
              holes = new ArrayList<>();
            }
            holes.add(jtsHole);
          }
          
          LinearRing[] holesArr = 
                        (holes == null)? null: GeometryFactory.toLinearRingArray(holes);
          Polygon polygon = new Polygon(shell, holesArr, factory);
          if (polygon != null) {
            polygonList.add(polygon);
          }
        }           

        Polygon[] jtsPolygons = (polygonList.isEmpty())? null :
                                    GeometryFactory.toPolygonArray(polygonList);
        result = new MultiPolygon(jtsPolygons, factory);
      }
    } catch (Exception exp) {
      logger.log(Level.WARNING, "Conversion.toJtsPolygon Error:\n {0}", exp.getMessage());
    }
    return result;
  }

      
  /**
   * Convert a list of ShapeLite Polygons to a JTS MultiPolygon Collection.
   * @param splPolygonList the list of ShapeLite polygon shapes
   * @return the JTS MultiPolygon collection - can be null if the ShapeLite polygon is 
   * undefined or empty
   */
  public static GeometryCollection toJtsPolygons(List<Shape> splPolygonList) {
    GeometryCollection result = null;
    if ((splPolygonList != null) && (!splPolygonList.isEmpty())) {
      GeometryFactory factory = 
                new GeometryFactory(new PrecisionModel(Conversion.JtsPrecModelType), 0);
      List<MultiPolygon> polygonList = new ArrayList<>();
      for (Shape splPolygon : splPolygonList) {
        MultiPolygon jtsPolygon = Conversion.toJtsPolygon(splPolygon);
        if (jtsPolygon != null) {
          polygonList.add(jtsPolygon);
        }
      }
      
      if (!polygonList.isEmpty()) {
        MultiPolygon[] polygonArr = new MultiPolygon[]{};
        polygonArr = polygonList.toArray(polygonArr);
        result = factory.createGeometryCollection(polygonArr);
      }
    }
    return result;
  }  
  /**
   * Called to convert a JTS MultiPolygon to a single ShapeLite Polygon Shape. On 
   * completion it will calculate the shape's Bounds.
   * @param jtsMultiPolygon the multiPolygon
   * @param shpType the ShapeType of the Polygon (is null or not a polygon ShapeType, it
   * will default to {@linkplain ShapeType#PolygonZ}.
   * @return the new Polygon shape - the shape is empty if jtsMultiPolygon=null or empty
   * or an error occurred.
   */
  @SuppressWarnings("unchecked")
  public static Shape toShapeLitePolygon(Geometry jtsGeometry, ShapeType shpType) {
    Shape result = null;
    if ((shpType == null) || (!ShapeType.isPolygon(shpType))) {
      shpType = ShapeType.PolygonZ;
    }
    result = new Shape(shpType);
    
    try {
      if ((jtsGeometry != null) && (!jtsGeometry.isEmpty())) {
        if (jtsGeometry instanceof Polygon) {
          Polygon jtsPolygon = (Polygon) jtsGeometry;
          Conversion.addJtsPolygonToShape(jtsPolygon, result);
        } else if (jtsGeometry instanceof MultiPolygon) {
          MultiPolygon jtsMultiPolygon = (MultiPolygon) jtsGeometry;
          int numGeom = jtsMultiPolygon.getNumGeometries();
          for (int iGeo = 0; iGeo < numGeom; iGeo++) {
            Polygon jtsPolygon = (Polygon) jtsMultiPolygon.getGeometryN(iGeo);            
            Conversion.addJtsPolygonToShape(jtsPolygon, result);
          }
        } else {
          throw new Exception("Invalid GeometryType[" + jtsGeometry.getGeometryType() 
                  + "]. Expected a Polygon or MultiPolygon.");
        }
      }
    } catch (Exception exp) {
      result.getParts().clear();
      logger.log(Level.WARNING, "Conversion.toShapeLitePolygon Error:\n {0}", 
              exp.getMessage());
    } finally {
      result.calculateBounds();
    }
    return result;
  }
  
  /**
   * Called to add the parts of a JTSPolygon to a ShapeLiteShapre
   * @param jtsPolygon the source Polygon
   * @param splShape the target shape to update
   * @throws Exception if creating the Shape Parts fail.
   */
  public static void addJtsPolygonToShape(Polygon jtsPolygon, Shape splShape) 
                                                                        throws Exception {
    if ((splShape == null) || (jtsPolygon == null) || (jtsPolygon.isEmpty())) {
      return;
    }
    
    LineString shell = jtsPolygon.getExteriorRing();
    if ((shell == null) || (shell.isEmpty())) {
      return;
    }

    Part shellPart = Conversion.toShapeLitePart(shell);
    splShape.getParts().add(shellPart);

    int numInRings = jtsPolygon.getNumInteriorRing();
    if (numInRings > 0) {
      for (int iRing = 0; iRing < numInRings; iRing++) {
        LineString hole = jtsPolygon.getInteriorRingN(iRing);
        if ((hole == null) || (hole.isEmpty())) {
          continue;
        }
        Part holePart = Conversion.toShapeLitePart(hole);
        splShape.getParts().add(holePart);
      }
    }
  }
  
  /**
   * Called to convert a ShapeLite Part to a JTS LineString
   * @param shapeLitePart the ShapeLite Part
   * @return the JTS LineString
   * @throws Exception 
   */
  public static LineString toJtsLineString(Part shapeLitePart) throws Exception {
    LineString result = null;
    try {
      CoordinateSequence coordSeq = null; 
      Coordinate[] ringCoords = null;
      List<Coord> partCoords = null;
      List<Coordinate> jtsCoords = null;
      if ((shapeLitePart != null) &&
          ((partCoords = shapeLitePart.getCoordinates()) != null) && 
              (!partCoords.isEmpty()) &&
           ((jtsCoords = Conversion.toJtsCoords(partCoords)) != null) &&
              (!jtsCoords.isEmpty())) {
        ringCoords = jtsCoords.toArray(ringCoords);
        coordSeq = new CoordinateArraySequence(ringCoords);
      }
      PrecisionModel precModel = new PrecisionModel(Conversion.JtsPrecModelType);
      GeometryFactory factory = new GeometryFactory(precModel,0);
      result = new LineString(coordSeq, factory);
    } catch (Exception exp) {
      logger.log(Level.WARNING, "Conversion.toJtsLineRing Error:\n {0}",
              exp.getMessage());
      throw exp;
    }
    return result;
  }
  
  /**
   * Called to convert a ShapeLit Part to a JTS LinearRing
   * @param shapeLitePart the ShapeLit Part
   * @return the JTS LinearRing
   * @throws Exception 
   */
  public static LinearRing toJtsLinearRing(Part shapeLitePart) throws Exception {
    LinearRing result = null;
    try {
      CoordinateSequence coordSeq = null; 
      Coordinate[] ringCoords = new Coordinate[]{};
      List<Coord> partCoords = null;
      List<Coordinate> jtsCoords = null;
      if ((shapeLitePart != null) &&
          ((partCoords = shapeLitePart.getCoordinates()) != null) && 
              (!partCoords.isEmpty()) &&
           ((jtsCoords = Conversion.toJtsCoords(partCoords)) != null) &&
              (!jtsCoords.isEmpty())) {
        ringCoords = jtsCoords.toArray(ringCoords);
        coordSeq = new CoordinateArraySequence(ringCoords);
      }
      PrecisionModel precModel = new PrecisionModel(Conversion.JtsPrecModelType);
      GeometryFactory factory = new GeometryFactory(precModel,0);
      result = new LinearRing(coordSeq, factory);
    } catch (Exception exp) {
      logger.log(Level.WARNING, "Conversion.toJtsLineRing Error:\n {0}",
              exp.getMessage());
      throw exp;
    }
    return result;
  }
  
  /**
   * Convert a JTS LinearRing to a ShapeLite Part. If will set the part.isClosed if the 
   * LinearRing is closed.
   * @param jtsPart the JTS LinearRing or LineString to convert.
   * @return a new ShapeLite part - can be empty if jtsRing=null|Empty
   * @throws Exception 
   */
  public static Part toShapeLitePart(LineString jtsPart) throws Exception {
    Part result = null;
    try { 
      List<Coord> slCoords = null;
      if ((jtsPart != null) && (!jtsPart.isEmpty()) &&
          ((slCoords = Conversion.toShapeLiteCoords(jtsPart.getCoordinates())) != null) &&
           (!slCoords.isEmpty())) {
        result = new Part(slCoords);
        if ((jtsPart != null) && (jtsPart.isClosed()) && (!result.isClosed())) {
          result.setClosed(true);
        }
      }
      
      if (result == null) {
        result = new Part();
      }
    } catch (Exception exp) {
      logger.log(Level.WARNING, "Conversion.toShapeLitePart Error:\n {0}",
              exp.getMessage());
      throw exp;
    }
    return result;
  }
  //</editor-fold>
  
  /**
   * Build a new Polygon form a list of Coordinates
   * @param coords a list of coordinates
   * @return a polygon or null if the coordinates is undefined or the number of 
   * coordinates &ly; 3
   * @throws Exception if a polygon construction error occur. Errors are logged,
   */
  public static Polygon buildJtsPolygon(List<Coordinate> coords) throws Exception {
    Polygon result = null;
    try {
      if (coords.size() > 2) {
        Coordinate c0 = coords.get(0);
        Coordinate cN = coords.get(coords.size()-1);
        if (!c0.equals2D(cN)) {
          coords.add(new Coordinate(c0));
        }
        
        Coordinate[] coordArr = new Coordinate[]{};
        coordArr = coords.toArray(coordArr);
        PrecisionModel precModel = new PrecisionModel(Conversion.JtsPrecModelType);
        GeometryFactory factory = new GeometryFactory(precModel, 0);
        result = factory.createPolygon(coordArr);
      }
    } catch (Exception exp) {
      logger.log(Level.WARNING, "Conversion.buildJtsPolygon Error:\n {0}",
              exp.getMessage());
      throw exp;
    }
    return result;
  }
  
  /**
   * Merge multiple geometries of type Polygon or MultiPolygon in a single geometry. 
   * The result will be either a Polygon (i.e., if the union results in a single polygon)
   * or a MultiPolygon
   * @param polygonArr one or more Polygon or MultiPolygon geometries
   * @return a Polygon or MultiPolygon
   */
  public static Geometry mergeJtsPolygons(Geometry...polygonArr) {
    Geometry result = null;
    try {
      if ((polygonArr != null) && (polygonArr.length > 0)) {
        if (polygonArr.length == 1) {
          result = polygonArr[0];
        } else {
          PrecisionModel precModel = new PrecisionModel(Conversion.JtsPrecModelType);
          GeometryFactory factory = new GeometryFactory(precModel, 0);
          GeometryCollection polygonCol = factory.createGeometryCollection(polygonArr);
          result = polygonCol.buffer(0);
        }
      }      
    } catch (Exception exp) {
      logger.log(Level.WARNING, "Conversion.mergeJtsPolygons Error:\n {0}",
              exp.getMessage());
      throw exp;
    }
            
    return result;
  }
}
