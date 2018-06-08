package gov.ca.water.tin;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.LineString;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Field;
import gov.ca.water.shapelite.Segment;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.analysis.CatmullRom;
import gov.ca.water.shapelite.analysis.CatmullRomException;
import gov.ca.water.shapelite.analysis.CatmullRomType;
import gov.ca.water.utils.Conversion;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hdunsford
 */
public class ContourLineBuilder {
    
  //<editor-fold defaultstate="collapsed" desc="Static Logger">
  /**
   * Protected Static Logger object for logging errors, warnings, and info messages.
   */
  protected static final Logger logger =
          Logger.getLogger(ContourLineBuilder.class.getName());
  //</editor-fold>
    
  //<editor-fold defaultstate="collapsed" desc="Static Methods">
  /**
   * This method takes the input coordinates with x,y and z values in order
   * to create contour lines.  It will use the integer number of contours
   * specified in contour count.  It will uniformly subdivide the z range
   * into contourCount equal divisions.
   * @param xyzCoordinates Coordinates that have x, y and z values where z is the
   * measured value.
   * @param contourCount The integer count of the equal contours to create.
   * @param catmullRom Boolean, which if true will cause the straight line segments
   * to be interpolated using catmullRom, creating slightly smoother shapes.
   * @return A FeatureSet containing all the contours.
   * @throws gov.ca.water.tin.ContourException if the contourCount is &lt; 1
   */
  public static FeatureSet createEqualContours(List<Coord> xyzCoordinates,
          int contourCount, boolean catmullRom) throws ContourException {
    if(contourCount < 1){
      throw new ContourException("contourCount must be greater than 1.");
    }
    double zMin = Double.POSITIVE_INFINITY;
    double zMax = Double.NEGATIVE_INFINITY;
    for(Coord c : xyzCoordinates){
      if(c.Z < zMin){
        zMin = c.Z;
      }
      if(c.Z > zMax){
        zMax = c.Z;
      }
    }
    double dz = (zMax - zMin)/contourCount;
    TriangleGroup tg = new TriangleGroup();
    tg.setVertices(xyzCoordinates);
    
    FeatureSet result = new FeatureSet();
    result.getFields().add(new Field("Elevation", 10, 2));
    DecimalFormat df = new DecimalFormat("0000000.00");
    double z = zMin;
    Analysis analysis = new Analysis();
    for(int i = 0; i < contourCount-1; i++){
      z += dz; // don't actually allow a 0 contour to be used.
      List<Segment> segs = analysis.getContourSegments(tg, z, null);
      List<LineString> lines = analysis.getContourLines(segs);
      Shape contour = Conversion.toMultiPartShapeZ(lines);
      if(catmullRom){
        try {
          contour = CatmullRom.interpolate(contour, 10, CatmullRomType.Centripetal);
        } catch (CatmullRomException exp) {
          logger.log(Level.WARNING, "ContourLineBuilder.createEqualContours Error:\n {1}",
                  exp.getMessage());
        }
      }
      Feature f = new Feature();
      f.setShape(contour);
      f.getAttributes().put("Elevation", df.format(z));
      result.getFeatures().add(f);
    }
    
    return result;
  }
  //</editor-fold>
}
