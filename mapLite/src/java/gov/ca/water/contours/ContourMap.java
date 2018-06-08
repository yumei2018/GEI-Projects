package gov.ca.water.contours;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.GeometryFactory;
import static gov.ca.water.contours.ContourGrid.logger;
import gov.ca.water.contours.intervals.ContourIntervals;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Field;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.utils.Conversion;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author J.G. "Koos" Prins, D.Eng. PE.
 */
public class ContourMap implements Serializable {
  
  //<editor-fold defaultstate="collapsed" desc="Static Constants">
  /**
   * FeatureSet Field name of for the contour z-Value
   */
  public final static String ContourFld = "Contour";
  /**
   * FeatureSet Field name of for the contour Index
   */
  public final static String ContourIdxFld = "ContourIdx";
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Static Logger">
  /**
   * Protected Static Logger object for logging errors, warnings, and info messages.
   */
  protected static final Logger logger = Logger.getLogger(ContourMap.class.getName());
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Private Fields">
  /**
   * A reference to the Interpolator's ContourIntervals
   */
  private ContourIntervals contours;
  /**
   * The HashMap of Polygon/MultiPolygon Geometry for each contour intervals
   */
  private HashMap<Integer, List<Geometry>> geometryMap;
  /**
   * A Cashed FeatureSet generated with each update of the GeomteryMap
   */
  private FeatureSet featureSet;
  //</editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Public Constructor
   */
  public ContourMap(ContourIntervals contours) {
    if (contours == null) {
      throw new NullPointerException("The Contour Intervals cannot be unassigned.");
    }
    this.contours = contours;
    this.clearGeometry();
  }

  /**
   * {@inheritDoc}
   * <p>OVERRIDE: Call super method before setting this.contours = null and call 
   * this.clearGeometry</p>
   */
  @Override
  protected void finalize() throws Throwable {
    super.finalize(); 
    this.contours = null;
    this.clearGeometry();
  }
  // </editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Private Methods">
  /**
   * Called to initiate a new FeatureSet with attributes[Contour(10,4) and
   * ContourIdx(10,0)].
   * @return a new FeatureSet instance
   */
  private FeatureSet newFeatureSet() {
    FeatureSet result = new FeatureSet();
    result.getFields().add(new Field("Contour", 10, 4));
    result.getFields().add(new Field("ContourIdx", 10, 0));
    return result;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Public Methods">
  /**
   * Get the number of Contours Intervals
   * @return this.contours.numContours
   */
  public int getNumContours() {
    return this.contours.getNumContours();
  }
  
  /**
   * Check is the contour Geometry Map is empty
   * @return true if no contour Geometry are defined.
   */
  public boolean isEmpty() {
    return (this.geometryMap == null)? true:geometryMap.isEmpty();
  }
  
  /**
   * Get the number of defined contour Geometry
   * @return 0 is empty or the number of contours for which Geometry are defined.
   */
  public int getNumGeometry() {
    return (this.geometryMap == null)? 0:geometryMap.size();
  }
  
  /**
   * Clear all the contour Geometry
   */
  public final void clearGeometry() {
    this.featureSet = null;
    if (this.geometryMap != null) {
      this.geometryMap.clear();
    }
    this.geometryMap = null;
  }
  
  /**
   * Call to add a new Contour Geometry element to the List of Geometry that define the
   * Geometry for the specified contour Index. The Geometry elements are not union at
   * when added.
   * @param contourIdx the specified contour Index.
   * @param contourGeometry the Polygon|MultiPolgon Geometry (ignored if null|empty)
   * @throws Exception if contourIdx is out of bounds.
   */
  public void add(Integer contourIdx, Geometry contourGeometry) throws Exception {
    try {
      if ((contourGeometry != null) && (!contourGeometry.isEmpty())) {
        if ((contourIdx < 0) || (contourIdx >= this.contours.getNumContours())) {
          throw new ArrayIndexOutOfBoundsException("Contour Index out of Bound[0.." +
                  Integer.toString((this.getNumContours()-1)) + "].");
        }
        
        if (this.geometryMap == null) {
          this.geometryMap = new HashMap<>();
        }
        
        List<Geometry> geometryList = (!this.geometryMap.containsKey(contourIdx))? null:
                this.geometryMap.get(contourIdx);
        if (geometryList == null) {
          geometryList = new ArrayList<>();
        }
        geometryList.add(contourGeometry);
        this.geometryMap.put(contourIdx, geometryList);
      }
    } catch (Exception exp) {
      logger.log(Level.WARNING, "{0}.add Error:\n {1}",
              new Object[]{this.getClass().getSimpleName(), exp.getMessage()});
      throw exp;
    }
  }
  
  /**
   * Get the ContourMap's Cached FeatureSet. If not initiated and this.geometryMap is
   * defined and not empty, it will initiate a new Feature Set with Attributes[Contour,
   * ContoruIdx]. For each contour index, it merges the Polygon/MultiPolygon geometries
   * in the this.geometryMap into a single ShapeLite PolygonZ shape (using
   * {@linkplain Conversion#toShapeLitePolygon(com.vividsolutions.jts.geom.Geometry,
   * gov.ca.water.shapelite.ShapeType) Conversion.toShapeLitePolygon}) an assign it as a
   * feature of the resulting FeatureSet
   * @return the cached FeatureSet.
   */
  public FeatureSet getFeatureSet(Geometry areaOfInterest) {
    if ((this.featureSet == null) && (this.contours != null) &&
            (this.geometryMap != null) && (!this.geometryMap.isEmpty())) {
      try {
        logger.log(Level.INFO, "Start Building Contour FeatureSet.");

        DecimalFormat df = new DecimalFormat("0000000.0000");
        this.featureSet = this.newFeatureSet();
        
        for(int contourIdx = 0; contourIdx < this.contours.getNumContours();
                contourIdx++) {
          if (((contourIdx == 0) && (this.contours.getHideLoContour())) ||
                  (!this.geometryMap.containsKey(contourIdx))){
            continue;
          }
          
          List<Geometry> geometryList = this.geometryMap.get(contourIdx);
          if ((geometryList == null) || (geometryList.isEmpty())) {
            continue;
          }
          
          Geometry[] geomArr = GeometryFactory.toGeometryArray(geometryList);
          Geometry mergeGeometry = Conversion.mergeJtsPolygons(geomArr);
          if (((mergeGeometry != null) && (!mergeGeometry.isEmpty())) && 
                  (areaOfInterest != null)) {
            mergeGeometry = areaOfInterest.intersection(mergeGeometry);
            if ((mergeGeometry == null) || (mergeGeometry.isEmpty())) {
              mergeGeometry = null;
            } else if (GeometryCollection.class.equals(mergeGeometry.getClass())) {
              GeometryCollection geomCol = (GeometryCollection) mergeGeometry;
              if (geomCol.getNumGeometries() == 1) {
                mergeGeometry = geomCol.getGeometryN(0);
              } else {
                mergeGeometry = geomCol.buffer(0.0d);
              }
            }
          }
          
          Shape contour =
                  Conversion.toShapeLitePolygon(mergeGeometry, ShapeType.PolygonZ);
          Double zValue = this.contours.getContour(contourIdx);
          Feature feature = new Feature();
          feature.setShape(contour);
          feature.getAttributes().put(ContourMap.ContourFld, df.format(zValue));
          feature.getAttributes().put(ContourMap.ContourIdxFld, 
                                      Integer.toString(contourIdx));
          
          this.featureSet.getFeatures().add(feature);
        }
        logger.log(Level.INFO, "Completed Contour FeatureSet.");
      } catch (Exception exp) {
        logger.log(Level.WARNING, "{0}.getFeatureSet Error:\n {1}",
                new Object[]{this.getClass().getSimpleName(), exp.getMessage()});
      }
    }
    return this.featureSet;
  }
  //</editor-fold>
}
