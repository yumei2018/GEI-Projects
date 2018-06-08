/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.ca.water.shapelite.io;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.simplify.DouglasPeuckerSimplifier;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.Field;
import gov.ca.water.shapelite.FieldType;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class LinestringSimplifier implements FeatureModifier {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    //</editor-fold>
  /**
   * Creates a new instance of the LinestringSimplifier class.
   */
  public LinestringSimplifier() {

  }

    //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Modify Features
   *
   * @param f
   * @return
   */
  @Override
  public Feature modifyFeature(Feature f) {
    String xsName = get(f, "River");
    xsName += "_" + get(f, "Reach");
    xsName += "_" + get(f, "RiverStation");
    f.getAttributes().put("XS_NAME", xsName);

    Feature currentFeature = f;
    Feature result = new Feature();
    result.copyAttributes(f.getAttributes());
    Shape resultShape = new Shape(ShapeType.PolyLine);
    result.setShape(resultShape);

    for (Part part : currentFeature.getShape().getParts()) {
      List<Coord> coords = part.getCoordinates();
      Coordinate[] coordinates = new Coordinate[coords.size()];
      for (int i = 0; i < coordinates.length; i++) {
        Coordinate point = new Coordinate();
        point.x = coords.get(i).getX();
        point.y = coords.get(i).getY();
        coordinates[i] = point;
      }
      GeometryFactory factory = new GeometryFactory();
      LineString linestring = factory.createLineString(coordinates);
      DouglasPeuckerSimplifier simplifier = new DouglasPeuckerSimplifier(linestring);
      simplifier.setDistanceTolerance(100);
      Geometry geom = simplifier.getResultGeometry();
      if (geom instanceof LineString) {
        LineString ls = (LineString) geom;
        List<Coord> resultCoords = new ArrayList<>();
        for (Coordinate c : ls.getCoordinates()) {
          resultCoords.add(new CoordXY(c.x, c.y));
        }
        Part resultPart = new Part(resultCoords);
        resultShape.getParts().add(resultPart);
        resultShape.calculateBounds();
      }
    }
    return result;

  }

  @Override
  public List<Field> modifyFields(List<Field> fields) {
    if (fields == null) {
      fields = new ArrayList<>();
    }
    fields.add(new Field("XS_NAME", FieldType.Character, 50));
    return fields;
  }

  private String get(Feature f, String attribute) {
    if (f.getAttributes().containsKey(attribute)) {
      return f.getAttributes().get(attribute);
    }
    return "";
  }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Properties">
    //</editor-fold>
}
