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
public class ShapeToSdo {

    //<editor-fold defaultstate="collapsed" desc="Fields">



    //</editor-fold>



    //<editor-fold defaultstate="collapsed" desc="Methods">

    /**
     * This creates the SDO_GEOMETRY text to use as part of an insert statement,
     * or other SQL statement.  This is not the actual database SDO_GEOMETRY object itself,
     * which is created by the oracle.
     * Currently this is only supported for linestrings like the ones created from
     * line extractor.
     * @param shape This must be a 2D linestring.  If not, this will throw a not supported
     * exception.
     * @param srid The SRID from the SDO_COORD_REF_SYS table.  e.g. 8307 is WGS1984.
     * @return A String in the SDO notation needed to insert the shape as an SDO_GEOMETRY.
     * @throws gov.ca.water.lineextract.NotImplementedException
     */
    public static String getGeometry(Shape shape,
        int srid) throws NotImplementedException{
        if(shape.getShapeType() != ShapeType.PolyLine){
            throw new NotImplementedException();
        }
        String result = "";
        result += "    2002,\n";  // two-dimensional linestring
        result += "    " + srid + ",\n";
        result += "    NULL,\n";
        boolean firstPart = true;
        int offset = 1;
        result += "    SDO_ELEM_INFO_ARRAY(";
        for(Part part: shape.getParts()){
            if(!firstPart){
                result += " ";
            }
            else{
                if(shape.getParts().size() > 1){
                    result += "1,4," + shape.getParts().size() + " ";
                }
            }
            result += offset + ",2,1";
            offset += part.getCoordinates().size() *2;
            firstPart = false;
        }
        result += "),\n";
        result += "    SDO_ORDINATE_ARRAY(";
        boolean firstCoord = true;
        for(Coord coord : shape.getCoordinates()){
            if(!firstCoord){
                result += ", ";
            }
            result += coord.getX() + "," + coord.getY();
            firstCoord = false;
        }
        result += ")";
        return result;
    }



    /**
     * Get an SDO_POINT geometry representing the specified longitude latitude in WGS84
     * @param longitude WGS84 longitude value.
     * @param latitude WGS84 latitude value.
     * @return A String that would represent the insert text value to use to create an
     * SDO_POINT geometry in WGS84.
     */
    public static String getPointGeometry(double longitude, double latitude){
        String result = "SDO_GEOMETRY(\n";
        result += "   2001,\n";  // two-dimensional linestring
        result += "   8307,\n";
        result += "   SDO_POINT_TYPE(" + longitude + ", " + latitude + ", NULL),\n";
        result += "   NULL,\n";
        result += "   NULL)\n";
        return result;
    }



    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">



    //</editor-fold>

}
