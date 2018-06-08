package com.gei.contour;

import com.vividsolutions.jts.geom.GeometryCollection;
import gov.ca.water.contours.ContourBuilder;
import gov.ca.water.contours.ContourGrid;
import gov.ca.water.math.interpolate.Interpolator;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.io.ShapefileReader;
import gov.ca.water.utils.Conversion;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author J.G. "Koos" Prins, D.Eng. PE.
 */
public class CaStateContourBuilder extends ContourBuilder {  
  //<editor-fold defaultstate="collapsed" desc="Private Static Methods">
  /**
   * Initiate the CA ContourGrid using the CA_Small and a 100x250 grid.
   * @return the ContourGrid instance
   * @throws IllegalArgumentException if initiating the Area-of-Interest or the Grid
   * failed.
   */
  private static ContourGrid CAContourGrid(String path) throws IllegalArgumentException {
    ContourGrid result = null;
    String shpS, shxS, dbfS;
    
    
    try {
      ShapefileReader shapeReader = new ShapefileReader();
      InputStream shp =
              CaStateContourBuilder.class.getResourceAsStream("resources/CntyBnds_ESRI.shp");
      InputStream shx =
              CaStateContourBuilder.class.getResourceAsStream("resources/CntyBnds_ESRI.shx");
      InputStream dbf =
              CaStateContourBuilder.class.getResourceAsStream("resources/CntyBnds_ESRI.dbf");
      
        if(path != null){
            shp = new FileInputStream(path + ".shp");
            shx = new FileInputStream(path + ".shx");
            dbf = new FileInputStream(path + ".dbf");
        } 
      shapeReader.open(shp, shx, dbf);
      List<Shape> areaShapes = shapeReader.getShapes();
      if ((areaShapes == null) || (areaShapes.isEmpty())) {
        throw new Exception("Reading the California boundary shape file failed.");
      }
      
      GeometryCollection areaOfInterest = Conversion.toJtsPolygons(areaShapes);
      if (areaOfInterest == null) {
        throw new Exception("Building the Area of Interest for the California boundary "
                + "failed.");
      }
      
      result = new ContourGrid();
      result.initMatrix(areaOfInterest, 100, 250);
    } catch (Exception exp) {
      logger.log(Level.WARNING, "CaStateContourBuilder.newContourGrid Error:\n {0}",
              exp.getMessage());
      throw new IllegalArgumentException(exp.getCause());
    }
    return result;
  }
  //</editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Public Constructor
   */
  public CaStateContourBuilder(Interpolator interpolator, String path) {      
    super(CaStateContourBuilder.CAContourGrid(path), interpolator);
  }
  // </editor-fold>
}
