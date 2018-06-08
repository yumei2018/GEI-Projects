/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.ca.water.shapelite.trace;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;

/**
 *
 * @author hdunsford
 */
public class ShapeToJson {

  /**
   * M.
   */
  private static final int M = Coord.Index.M;

  /**
   * Z.
   */
  private static final int Z = Coord.Index.Z;

    //<editor-fold defaultstate="collapsed" desc="Fields">
    //</editor-fold>
    /**
     * Creates a new instance of the JsonAdapter class.
     */
    public ShapeToJson() {

    }

    //<editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * This assumes that the shape is in the WGS1984 geographic projection.
     *
     * @param shape
     * @return
     */
    public static String getGeometry(Shape shape) {
        return getGeometry(shape, 4326);
    }

    /**
     * Currently this is only designed to work for line strings as created by
     * line extractor.
     *
     * @param shape
     * @param wkid the well known id for spatial reference, e.g. 4326 is WGS84.
     * @return
     */
    public static String getGeometry(Shape shape, int wkid) {
        switch (shape.getShapeType()) {
            case Point:
                return getPoint(shape, wkid);
            case PointM:
                return getPointM(shape, wkid);
            case PointZ:
                return getPointZ(shape, wkid);
            case MultiPoint:
                return getMultipoint(shape, wkid);
            case MultiPointM:
                return getMultipointM(shape, wkid);
            case MultiPointZ:
                return getMultipointZ(shape, wkid);
            case PolyLine:
                return getLine(shape, wkid);
            case PolyLineM:
                return getLineM(shape, wkid);
            case PolyLineZ:
                return getLineZ(shape, wkid);
            case Polygon:
                return getPolygon(shape, wkid);
            case PolygonM:
                return getPolygonM(shape, wkid);
            case PolygonZ:
                return getPolygonZ(shape, wkid);

        }
        return null;

    }

    private static String getPoint(Shape shape, int wkid) {
        return "{\"x\" : " + shape.getX() + ", \"y\" : " + shape.getY() + ", \"spatialReference\" : {\"wkid\" : " + wkid + "}}";
    }

    private static String getPointM(Shape shape, int wkid) {
        return "{\"x\" : " + shape.getX() + ", \"y\" : " + shape.getY()
                + "\"m\" : " + shape.getM() + ", \"spatialReference\" : {\"wkid\" : " + wkid + "}}";
    }

    private static String getPointZ(Shape shape, int wkid) {
        return "{\"x\" : " + shape.getX() + ", \"y\" : " + shape.getY() + ", \"z\" : "
                + shape.getZ() + "\"m\" : " + shape.getM() + ", \"spatialReference\" : {\"wkid\" : " + wkid + "}}";
    }

    private static String getMultipoint(Shape shape, int wkid) {
        String result = "{\"points\" : [";
        boolean firstCoord = true;
        for (Coord c : shape.getCoordinates()) {
            if (!firstCoord) {
                result += ", ";
            }
            result += "[";
            result += c.getX();
            result += ",";
            result += c.getY();
            result += "]";
            firstCoord = false;

        }
        result += "],\"spatialReference\" : {\"wkid\" : " + wkid + "}}";
        return result;
    }

    private static String getMultipointM(Shape shape, int wkid) {
        String result = "{\"hasM : true, "
                + "\"points\" : [";
        boolean firstCoord = true;
        for (Coord c : shape.getCoordinates()) {
            if (!firstCoord) {
                result += ", ";
            }
            result += "[";
            result += c.getX();
            result += ",";
            result += c.getY();
            result += ",";
            result += c.get(M);
            result += "]";
            firstCoord = false;
        }
        result += "],\"spatialReference\" : {\"wkid\" : " + wkid + "}}";
        return result;
    }

    private static String getMultipointZ(Shape shape, int wkid) {
        String result = "{\"hasZ\" : true, \"hasM\" : true, \"points\" : [";
        boolean firstCoord = true;
        for (Coord c : shape.getCoordinates()) {
            if (!firstCoord) {
                result += ", ";
            }
            result += "[";
            result += c.getX();
            result += ",";
            result += c.getY();
            result += ",";
            result += c.get(Z);
            result += ",";
            result += c.get(M);
            result += "]";
            firstCoord = false;
        }
        result += "],\"spatialReference\" : {\"wkid\" : " + wkid + "}}";
        return result;
    }

    private static String getLine(Shape shape, int wkid) {
        String result = "{\"paths\" : [";
        boolean firstPart = true;
        for (Part part : shape.getParts()) {
            if (!firstPart) {
                result += ",";
            }
            firstPart = false;
            result += "[";
            boolean firstCoord = true;
            for (Coord c : part.getCoordinates()) {
                if (!firstCoord) {
                    result += ", ";
                }
                result += "[";
                result += c.getX();
                result += ",";
                result += c.getY();
                result += "]";
                firstCoord = false;
            }
            result += "]";
        }
        result += "],\"spatialReference\" : {\"wkid\" : " + wkid + "}}";
        return result;
    }

    private static String getLineM(Shape shape, int wkid) {
        String result = "{\"hasM : true, "
                + "\"paths\" : [";
        boolean firstPart = true;
        for (Part part : shape.getParts()) {
            if (!firstPart) {
                result += ",";
            }
            firstPart = false;
            result += "[";
            boolean firstCoord = true;
            for (Coord c : part.getCoordinates()) {
                if (!firstCoord) {
                    result += ", ";
                }
                result += "[";
                result += c.getX();
                result += ",";
                result += c.getY();
                result += ",";
                result += c.get(M);
                result += "]";
                firstCoord = false;
            }
            result += "]";
        }
        result += "],\"spatialReference\" : {\"wkid\" : " + wkid + "}}";
        return result;
    }

    private static String getLineZ(Shape shape, int wkid) {
        String result = "{\"hasZ\" : true, \"hasM\" : true, \"paths\" : [";
        boolean firstPart = true;
        for (Part part : shape.getParts()) {
            if (!firstPart) {
                result += ",";
            }
            firstPart = false;
            result += "[";
            boolean firstCoord = true;
            for (Coord c : part.getCoordinates()) {
                if (!firstCoord) {
                    result += ", ";
                }
                result += "[";
                result += c.getX();
                result += ",";
                result += c.getY();
                result += ",";
                result += c.get(Z);
                result += ",";
                result += c.get(M);
                result += "]";
                firstCoord = false;
            }
            result += "]";
        }
        result += "],\"spatialReference\" : {\"wkid\" : " + wkid + "}}";
        return result;
    }

    private static String getPolygon(Shape shape, int wkid) {
        String result = "{\"rings\" : [";
        boolean firstPart = true;
        for (Part part : shape.getParts()) {
            if (!firstPart) {
                result += ",";
            }
            firstPart = false;
            result += "[";
            boolean firstCoord = true;
            for (Coord c : part.getCoordinates()) {
                if (!firstCoord) {
                    result += ", ";
                }
                result += "[";
                result += c.getX();
                result += ",";
                result += c.getY();
                result += "]";
                firstCoord = false;
            }
            result += "]";
        }
        result += "],\"spatialReference\" : {\"wkid\" : " + wkid + "}}";
        return result;
    }

    private static String getPolygonM(Shape shape, int wkid) {
        String result = "{\"hasM : true, \"rings\" : [";
        boolean firstPart = true;
        for (Part part : shape.getParts()) {
            if (!firstPart) {
                result += ",";
            }
            firstPart = false;
            result += "[";
            boolean firstCoord = true;
            for (Coord c : part.getCoordinates()) {
                if (!firstCoord) {
                    result += ", ";
                }
                result += "[";
                result += c.getX();
                result += ",";
                result += c.getY();
                result += ",";
                result += c.get(M);
                result += "]";
                firstCoord = false;
            }
            result += "]";
        }
        result += "],\"spatialReference\" : {\"wkid\" : " + wkid + "}}";
        return result;
    }

    private static String getPolygonZ(Shape shape, int wkid) {
        String result = "{\"hasZ\" : true, \"hasM\" : true, \"rings\" : [";
        boolean firstPart = true;
        for (Part part : shape.getParts()) {
            if (!firstPart) {
                result += ",";
            }
            firstPart = false;
            result += "[";
            boolean firstCoord = true;
            for (Coord c : part.getCoordinates()) {
                if (!firstCoord) {
                    result += ", ";
                }
                result += "[";
                result += c.getX();
                result += ",";
                result += c.getY();
                result += ",";
                result += c.get(Z);
                result += ",";
                result += c.get(M);
                result += "]";
                firstCoord = false;
            }
            result += "]";
        }
        result += "],\"spatialReference\" : {\"wkid\" : " + wkid + "}}";
        return result;
    }

    /**
     * Gets the latitude and longitude formatted as JSON with the SRID needed
     * for javascript geometry addition of the string.
     *
     * @param longitude
     * @param latitude
     * @return
     */
    public static String getPointGeometry(double longitude, double latitude) {
        return "{\"x\" : " + longitude + ", \"y\" : " + latitude + ", \"spatialReference\" : {\"wkid\" : 4326}}";
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Properties">
    //</editor-fold>
}
