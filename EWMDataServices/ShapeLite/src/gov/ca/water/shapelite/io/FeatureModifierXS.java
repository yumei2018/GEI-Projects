/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.ca.water.shapelite.io;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.coordinate.CoordZ;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.Field;
import gov.ca.water.shapelite.FieldType;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class FeatureModifierXS implements FeatureModifier {

  /**
   * The number of characters in the string name field.
   */
  private static final int FIELD_SIZE = 50;

  //<editor-fold defaultstate="collapsed" desc="Fields">
  //</editor-fold>
  /**
   * Creates a new instance of the XSHandler class.
   */
  public FeatureModifierXS() {

  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  //</editor-fold>
  /**
   * Modifies the feature.
   *
   * @param f The feature to modify.
   * @return The feature object that is passed in, but with some changes.
   */
  @Override
  public final Feature modifyFeature(Feature f) {
    String xsName = get(f, "River");
    xsName += "_" + get(f, "Reach");
    xsName += "_" + get(f, "RiverStation");
    f.getAttributes().put("XS_NAME", xsName);

    if (f.getShape().getShapeType().equals(ShapeType.PolyLine)) {
      // we did not get Z values from the XS attributes.
      // read the profile instead.
      Shape lineZ = new Shape(ShapeType.PolyLineZ);
      Part prt = new Part();
      lineZ.getParts().add(prt);
      if (f.getAttributes().containsKey("Profile")) {
        String data = f.getAttributes().get("Profile");
        String[] coords = data.split(" ");
        for (String coordText : coords) {
          String[] ordinates = coordText.split(",");
          Double dist = Double.parseDouble(ordinates[0]);
          Optional<Coord> c = f.getShape().getParts().get(0)
              .getCoordinateAtDistance(dist);
          if (c.isPresent()) {
            Double elev = Double.parseDouble(ordinates[1]);
            CoordZ coord = new CoordZ(c.get().getX(), c.get().getY(), elev);
            prt.getCoordinates().add(coord);
          }

        }
      }
      lineZ.calculateBounds();
      f.setShape(lineZ);
    }

    return f;
  }

  /**
   * If f contains the specified attribute, this will return that attribute.
   *
   * @param f The feature to get an attribute for.
   * @param attribute The String field name of the attribute value to get a
   * string representation of.
   * @return A String representation of the specified attribute or an empty
   * string.
   */
  private String get(Feature f, String attribute) {
    if (f.getAttributes().containsKey(attribute)) {
      return f.getAttributes().get(attribute);
    }
    return "";
  }

  /**
   * Modifies the list of fields, in this case by adding an XS_NAME field.
   *
   * @param fields The list of fields to modify.
   * @return A modified field list.
   */
  @Override
  public final List<Field> modifyFields(List<Field> fields) {
    if (fields == null) {
      fields = new ArrayList<>();
    }
    fields.add(new Field("XS_NAME", FieldType.Character, FIELD_SIZE));
    return fields;
  }

}
