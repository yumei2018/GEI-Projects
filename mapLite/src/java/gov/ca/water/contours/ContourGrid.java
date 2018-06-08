package gov.ca.water.contours;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.PrecisionModel;
import gov.ca.water.common.io.DataEntry;
import gov.ca.water.contours.intervals.ContourIntervals;
import gov.ca.water.math.interpolate.Interpolator;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.data.Marker;
import gov.ca.water.shapelite.data.SymbolizerMarker;
import gov.ca.water.utils.Conversion;
import java.awt.Color;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author J.G. "Koos" Prins, D.Eng. PE.
 */
public class ContourGrid implements Serializable {
  
  //<editor-fold defaultstate="collapsed" desc="Static Logger">
  /**
   * Protected Static Logger object for logging errors, warnings, and info messages.
   */
  protected static final Logger logger = Logger.getLogger(ContourGrid.class.getName());
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Static GeometryFactory">
  /**
   * Placeholder for a cached GeometryFactory
   */
  private static GeometryFactory geoFactory;
  /**
   * Get the cached GeometryFactory - lazy initiate if unassigned
   * @return the cached GeometryFactory
   */
  private static GeometryFactory getGeometryFactory() {
    if (ContourGrid.geoFactory == null) {
      PrecisionModel precMod = new PrecisionModel(PrecisionModel.FLOATING);
      ContourGrid.geoFactory = new GeometryFactory(precMod);
    }
    return ContourGrid.geoFactory;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Public Static Settings">
  /**
   * The Maximum level of sub-grids that can be generated. Default=4.
   */
  public static int MaxGridLevel = 2;
  /**
   * The number of Sub-GridPoint Columns (i.e. GridRect.count + 1)
   */
  public static int SubGridCols = 3;
  /**
   * The number of Sub-GridPoint Rows (i.e. GridRect.count + 1)
   */
  public static int SubGridRows = 3;
  /**
   * The the maximum allowed InetrsectPoint z-value error tolerance that will trigger
   * a SubGrid (typ. range 0.05 - 0.01)) Default = 0.025
   */
  public static double MaxIntersectError = 0.03d;
  //</editor-fold>  
  
  protected class IntersectPoint extends GridPoint {
    
    //<editor-fold defaultstate="collapsed" desc="Public Final Fields">
    /**
     * The Maximum Range of Intersect
     */
    public final double maxZ;
    /**
     * The Minimum Range of Intersect
     */
    public final double minZ;
    /**
     * The the Contour xValue
     */
    public final double contourZ;
    /**
     * The the Contour xValue
     */
    public final int maxIdx;
    /**
     * The the Contour xValue
     */
    public final int minIdx;
    //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Public Constructor
     */
    public IntersectPoint(Coordinate other, double z1, double z2,  double contourZ, 
            int contourIdx1, int contourIdx2) {
      super(other);      
      this.maxZ = Math.max(z1, z2);
      this.minZ = Math.min(z1, z2);
      this.contourZ = contourZ;
      this.minIdx = Math.min(contourIdx1, contourIdx2);
      this.maxIdx = Math.max(contourIdx1, contourIdx2);
    }
    // </editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Public Method">
    /**
     * Get whether this point is a point on the contour (i.e., the contourId of the two 
     * interpolation points are not the same. If this test return false this 
     * IntersectPoint represents a mid-point on a GridRect Border that are on or above
     * the contour).
     * @return true if this.minIdx=this.maxIdx.
     */
    public boolean isContourIntersect() {
      return ((this.minIdx != this.maxIdx));
    }
    
    /**
     * Check if this this new interpolated loCotourIdx is above the maximum contoiurIdx 
     * of the triangle's interpolation points. Only meaningful is 
     * (!this.isContourIntersect)
     * @return true if (this.loIndex &ge; this.minIdx) and (this.loIndex &le; this.maxIdx)
     */
    public boolean inContourInterval() {
      return ((this.getLoIndex() >= this.minIdx) && (this.getLoIndex() <= this.maxIdx));
    }
    
    /**
     * Check if this.Z is within the Intersect Range - called after interpolating the
     * intersectPoint's z-value.
     * @return true is mazZ &ge; zValue &ge; minZ
     */
    public boolean inIntersectRange() {
      double zValue = this.getZ();
      return ((!Double.isNaN(zValue)) && (DataEntry.isLE(zValue, this.maxZ)) &&
              (DataEntry.isGE(zValue, this.minZ)));
    }
    
    /**
     * Check if the intersectErr = |(this.contourZ - this.Z)/(this.maxZ - this.minZ)| &le;
     * {@linkplain ContourGrid#MaxIntersectError}.
     * @return true if the test pass.
     */
    public boolean inToleranceRange() {
      boolean result = true;
      double zValue = this.getZ();
      if ((!DataEntry.isEq(this.maxZ, this.minZ)) && (!Double.isNaN(this.contourZ)) &&
              (!Double.isNaN(zValue))) {
        double err = Math.abs((this.contourZ - zValue)/(this.maxZ - this.minZ));
        result = ((!Double.isNaN(err)) && 
                  (DataEntry.isLE(err, ContourGrid.MaxIntersectError)));
      }      
      return result;
    }
    //</editor-fold>
  }
  
  //<editor-fold defaultstate="collapsed" desc="Internal GridTriangle class">
  /**
   * A Triangle representing a quadrant of the GridRect. Point Pi[0] is always the
   * GridRect's MidPoint.
   */
  public class GridTriangle implements Serializable {
    
    //<editor-fold defaultstate="collapsed" desc="Public Final Field">
    /**
     * GridPoints values of the triangle's corners. Point Pi[0] is always the
     * GridRect's MidPoint.
     */
    public final GridPoint[] Pi;
    /**
     * Flag set to true if this triangle has at least one contour. It is set to false if
     * the one or more of the points are set to {@linkplain GridPoint#LO_UNDEFNED} or if
     * the one or more of the points are set to {@linkplain GridPoint#HI_UNDEFNED}.
     */
    public final boolean hasContours;
    //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Public Constructor
     */
    public GridTriangle(GridPoint p0, GridPoint p1, GridPoint p2) {
      this.Pi = new GridPoint[] {p0, p1 ,p2};
      boolean isBelow = ((p0.getLoIndex() == GridPoint.LO_UNDEFNED) ||
              (p1.getLoIndex() == GridPoint.LO_UNDEFNED) ||
              (p2.getLoIndex() == GridPoint.LO_UNDEFNED));
      boolean isAbove = ((p0.getHiIndex() == GridPoint.HI_UNDEFNED) ||
              (p1.getHiIndex() == GridPoint.HI_UNDEFNED) ||
              (p2.getHiIndex() == GridPoint.HI_UNDEFNED));
      this.hasContours = ((!isBelow) && (!isAbove));
    }
    // </editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public methods">
    /**
     * Check if all the triangle's vertices are on or above the specified contour index
     * @param contourIdx the contour index
     * @return true if all coordinates.loIndex &ge; contourIdx.
     */
    public boolean isOnOrAbove(int contourIdx) {
      return ((this.Pi[0].getLoIndex() >= contourIdx) &&
                (this.Pi[1].getLoIndex() >= contourIdx) &&
                (this.Pi[2].getLoIndex() >= contourIdx));
    }
    
    /**
     * Check if all the triangle's vertices are on the specified contour index
     * @param contourIdx the contour index
     * @return true if all coordinates.loIndex == contourIdx.
     */
    public boolean isOn(int contourIdx) {
      return ((this.Pi[0].getLoIndex() == contourIdx) &&
                (this.Pi[1].getLoIndex() == contourIdx) &&
                (this.Pi[2].getLoIndex() == contourIdx));
    }
    
    /**
     * Check if this triangle is below the specified contour index
     * @param contourIdx the contour index
     * @return true if all coordinates.loIndex &lt; contourIdx.
     */
    public boolean isBelow(int contourIdx) {
      return ((this.Pi[0].getLoIndex() < contourIdx) &&
                (this.Pi[1].getLoIndex() < contourIdx) &&
                (this.Pi[2].getLoIndex() < contourIdx));
    }
    
    /**
     * Called to return a Polygon that represents the plane of Contour<sub>i</sub> (i.e.,
     * with z-value &ge; the contour's Z-value.
     * @param contourIdx the index of the contour
     * @param contourZ the contour's Z-value
     * @return the polygon or null if this.intersectsContour(contourIdx)=false.
     * @throws Exception if an polygon construct error occurs.
     */
    protected List<IntersectPoint> getContour(int contourIdx, double contourZ, 
                           List<Polygon> contourPolys, int gridLevel) throws Exception {
      List<IntersectPoint> result = new ArrayList<>();
      Polygon triPoly = null;
      if (this.isOnOrAbove(contourIdx)) {
        List<Coordinate> coords = new ArrayList<>();
        coords.add(this.Pi[0].asCoord());
        coords.add(this.Pi[1].asCoord());
        coords.add(this.Pi[2].asCoord());
        coords.add(this.Pi[0].asCoord());
        triPoly = Conversion.buildJtsPolygon(coords);
        /**
         * If the GridRect is on the contour, add the midpoint on the border as a 
         * IntersectPoint to check if theGridRect should be sub-divide - ignore if
         * contourIdx = 0.
         */
        if ((gridLevel < ContourGrid.MaxGridLevel) && 
                (this.Pi[1].getLoIndex() == contourIdx) &&
                (this.Pi[2].getLoIndex() == contourIdx)) {
          int numPnts = (2*(ContourGrid.MaxGridLevel - gridLevel) - 1);
          for (int iPnt = 1; iPnt <= numPnts; iPnt++) {
            double dZ = (1.0d/(numPnts + 1)) * iPnt;
            double pZ = this.Pi[1].getZ() +
                    ((this.Pi[2].getZ() - this.Pi[1].getZ()) * dZ);
            double pX = this.Pi[1].getX() +
                    ((this.Pi[2].getX() - this.Pi[1].getX()) * dZ);
            double pY = this.Pi[1].getY() +
                    ((this.Pi[2].getY() - this.Pi[1].getY()) * dZ);
            Coordinate coord = new Coordinate(pX, pY, contourZ);
            IntersectPoint point = new IntersectPoint(coord, this.Pi[1].getZ(), 
                             this.Pi[2].getZ(), pZ,
                             this.Pi[1].getLoIndex(), this.Pi[2].getLoIndex());
            result.add(point);
          }
        }
      } else if (!this.isBelow(contourIdx))  {
        List<Coordinate> coords = new ArrayList<>();
        Coordinate coord = null;
        for (int i = 0; i < 3; i++) {
          int j = (i == 2)? 0: i+1;
          if (this.Pi[i].isOnOrAboveContour(contourIdx)) {
            coord = this.Pi[i].asCoord();
            coord.setOrdinate(Coordinate.Z, contourZ);
            coords.add(coord);
            if ((!this.Pi[i].isOnContour(contourIdx)) &&
                    (this.Pi[j].isBelowContour(contourIdx))) {
              double dZ = (contourZ - this.Pi[i].getZ())/(this.Pi[j].getZ() -
                      this.Pi[i].getZ());
              double pX = this.Pi[i].getX() +
                      ((this.Pi[j].getX() - this.Pi[i].getX()) * dZ);
              double pY = this.Pi[i].getY() +
                      ((this.Pi[j].getY() - this.Pi[i].getY()) * dZ);
              coord = new Coordinate(pX, pY, contourZ);
              coords.add(coord);              
              IntersectPoint point = new IntersectPoint(coord, this.Pi[i].getZ(), 
                           this.Pi[j].getZ(), contourZ,
                           this.Pi[i].getLoIndex(), this.Pi[j].getLoIndex());
              result.add(point);
            }
          } else if ((this.Pi[i].isBelowContour(contourIdx)) &&
                  (this.Pi[j].isOnOrAboveContour(contourIdx))) {
            double dZ = (contourZ - this.Pi[i].getZ())/(this.Pi[j].getZ() -
                    this.Pi[i].getZ());
            double pX = this.Pi[i].getX() +
                    ((this.Pi[j].getX() - this.Pi[i].getX()) * dZ);
            double pY = this.Pi[i].getY() +
                    ((this.Pi[j].getY() - this.Pi[i].getY()) * dZ);
            coord = new Coordinate(pX, pY, contourZ);
            coords.add(coord);
            IntersectPoint point = new IntersectPoint(coord, this.Pi[i].getZ(), 
                           this.Pi[j].getZ(), contourZ,
                           this.Pi[i].getLoIndex(), this.Pi[j].getLoIndex());
            result.add(point);
          }
        }
        if (coords.size() >= 3) {
          /** close the polygon **/          
          Coordinate coord0 = coords.get(0);
          Coordinate lastCoord = new Coordinate(coord0.getOrdinate(Coordinate.X), 
                                          coord0.getOrdinate(Coordinate.Y), contourZ);
          coords.add(lastCoord);
          triPoly = Conversion.buildJtsPolygon(coords);
        }
      }
      
      if ((triPoly != null) && (!triPoly.isEmpty())) {
        contourPolys.add(triPoly);
      }
      return result;
    }
    //</editor-fold>
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Internal GridRect Class">
  /**
   * <p>A GridRect represents the Rectangle between four neighboring GridPoints with its
   * associated MidPoint as the center of the GridRect - thus it has five (5) vertices,
   * with this.gridPoints[0] as the midPoint. For building the contour polygons, the
   * GridRect is sub-divided into four {@linkplain GridTriangle GridTriangles} (T1..T4) in
   * clock-wise order.</p> 
   * <p>If this GridRect does not define the contours adequately, it can be sub-divided
   * into smaller RectGrid's using a Sub ContourGrid (i.e. {@linkplain #getSubGrid(
   * gov.ca.water.contours.intervals.ContourIntervals) this.subGrid}.</p>
   * @author J.G. "Koos" Prins, D.Eng. PE.
   */
  public class GridRect implements Serializable {
    
    //<editor-fold defaultstate="collapsed" desc="Vertex Indices">
    /**
     * Index of the MidPoint
     */
    public static final int P0 = 0;
    /**
     * Index of Top-Left Corner
     */
    public static final int P1 = 1;
    /**
     * Index of Top-Right Corner
     */
    public static final int P2 = 2;
    /**
     * Index of Bottom-Right Corner
     */
    public static final int P3 = 3;
    /**
     * Index of Bottom-Left Corner
     */
    public static final int P4 = 4;
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Fields">
    /**
     * Placeholder for a SubGrid that is contained by this Grid
     */
    private ContourGrid subGrid;
    /**
     * Placeholder for the GridRect's cached Envelope
     */
    private Envelope envelope = null;
    /**
     * Flag for the rectangle isOutSide Area-of-Interest state
     */
    private Boolean isOutside;
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public Final Fields">
    /**
     * The GridRect's Owner Contour Grid
     */
    public final ContourGrid ownerGrid;
    /**
     * The GridRect's Grid Column Index
     */
    public final int colIndex;
    /**
     * The GridRect's Grid Row Index
     */
    public final int rowIndex;
    //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Public Constructor with a ownerGrid reference, a colIndex and rowIndex. The
     * ownerGrid and indices are not validate because it is internally used.
     */
    private GridRect(ContourGrid ownerGrid, int colIndex, int rowIndex) {
      this.ownerGrid = ownerGrid;
      this.colIndex = colIndex;
      this.rowIndex = rowIndex;
      this.envelope = null;
      this.isOutside = null;
      this.reset();
    }

    /**
     * {@inheritDoc}
     * <p>OVERRIDE: Call the super method before calling this.reset to dispose local
     * resources.</p>
     */
    @Override
    protected void finalize() throws Throwable {
      super.finalize(); 
      this.reset();
    }
    // </editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public Methods">
    /**
     * Reset the SubGrid.
     */
    public final void reset() {
      if (this.subGrid != null) {
        this.subGrid.reset();
      }
      this.subGrid = null;
    }
    
    /**
     * Called to get the GridPoint by its vertex Index
     * @param vertexIndex the vertex index to search for [0..4], 0 = {@linkplain #P0
     * midpoint}.
     * @return the GridPoint reference or null if index if out of bounds.
     */
    public GridPoint getGridPoint(int vertexIndex) {
      GridPoint result = null;
      if (this.ownerGrid != null) {
        switch (vertexIndex) {
          case GridRect.P0:
            result = this.ownerGrid.midPoints[this.colIndex][this.rowIndex];
            break;
          case GridRect.P1:
            result = this.ownerGrid.gridPoints[this.colIndex][this.rowIndex];
            break;
          case GridRect.P2:
            result = this.ownerGrid.gridPoints[this.colIndex+1][this.rowIndex];
            break;
          case GridRect.P3:
            result = this.ownerGrid.gridPoints[this.colIndex+1][this.rowIndex+1];
            break;
          case GridRect.P4:
            result = this.ownerGrid.gridPoints[this.colIndex][this.rowIndex+1];
            break;
        }
      }
      return result;
    }
    
    /**
     * Call to get the GridRect's cached Envelope
     * @return the cache reference
     */
    public Envelope getEnvelope() {
      if (this.envelope == null) {
        double minX = Math.min(this.getGridPoint(1).getX(), this.getGridPoint(3).getX());
        double maxX = Math.max(this.getGridPoint(1).getX(), this.getGridPoint(3).getX());
        double minY = Math.min(this.getGridPoint(1).getY(), this.getGridPoint(3).getY());
        double maxY = Math.max(this.getGridPoint(1).getY(), this.getGridPoint(3).getY());
        this.envelope = new Envelope(minX, maxX, minY , maxY);
      }
      return envelope;
    }
    
    /**
     * Call to check if the GridPoint is contained within the envelop if the GridRect
     * @param point the GridPoint to evaluate
     * @return true if inside GridRect
     */
    public boolean contains(GridPoint point) {
      boolean result = false;
      double ptX = Double.NaN;
      double ptY = Double.NaN;
      Envelope rectEnev = null;
      if ((point != null) && (!point.isEmpty()) &&
               ((rectEnev = this.getEnvelope()) != null)){
        result = rectEnev.contains(point);
      }
      return result;
    }
    
    /**
     * Get whether this rectangle is outside the Area-of-Interest
     * @return true is at least one point is contained (inside) the  Area-of-Interest
     */
    public boolean isOutsideArea() {
      boolean result = (this.isOutside == null)? true: this.isOutside;
      if (this.isOutside == null) {
        GridPoint[] gridPoints = new GridPoint[5];
        for (int iPoint = 0; iPoint < 5; iPoint++) {
          gridPoints[iPoint] = this.getGridPoint(iPoint);
        }
        result = this.outsideArea(gridPoints);
      }
      return result;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Building the RectGrid Contour Polygons">
    /**
     * <p>Called by the {@linkplain ContourGrid#buildContours(
     * gov.ca.water.math.interpolate.Interpolator, gov.ca.water.contours.ContourMap)
     * ContourGrid.buildContours()} to build the contour polygons for each Contour
     * Interval and add it to the contourShapes collection. This process includes the
     * following steps:</p><ul>
     * <li><b>Step 1:</b> - check if this GridRect is inside the Area-of-Interest. If 
     * not return to caller.</li>
     * <li><b>Step 2:</b> - Call {@linkplain #doSubGridTest1(
     * gov.ca.water.contours.GridPoint, int, int) doSubGridTest#1} to check if this 
     * GridRect requires a SubGrid. if true, go to Step ??</li>
     * <li><b>Step 3:</b> - Retrieve the Interpolated GridPoints for Observed Data Points
     * contained within this GridRect - calling {@linkplain 
     * Interpolator#getObsGridPointByRect(gov.ca.water.contours.ContourGrid.GridRect) 
     * Interpolator.getObsGridPointByRect(this)}. If no Observed Data Point fall within
     * continue to step 4. Else call {@linkplain #doSubGridTest2(java.util.List, int, int)
     * doSubGridTest2} to test whether a subGrid is needed, based on the interpolated 
     * observed values. If the test returns true, proceed to Step ??.</li>
     * <li><b>Step 4:</b> - {@linkplain #getTriangles(gov.ca.water.contours.GridPoint[]) 
     * Initiate Triangles} and build the GridRect's contour Polygon
     * for each intersecting contour. This process has the following sub-steps:<ol>
     *   <li><b>Step 4a:</b> Check is any of the Triangle has contours - is not return 
     *      to the caller.</li>
     *   <li><b>Step 4b:</b> Using the triangles, build the GridRect's contour Polygon 
     *      for each contour level - adding it to a temporary hashMap.</li>
     *   <li><b>Step 4c:</b> Call doSubGridTest3 is the observed data is consistent with
     *      the contour and if not set doSubGrid=true and stop the contour building for 
     *      this GridRect.</li>
     * </ol></li>
     * <li><b>Step 5:</b> Update the ContourMap - using the following sub-steps depending 
     * on doSubGrid:<ol>
     *   <li><b>Step 5a:</b> If (doSubGrid), initiate {@linkplain #getSubGrid(
     *      gov.ca.water.contours.intervals.ContourIntervals) this.subGrid} and call its 
     *      buildContours to update the contourMap.</li>
     *   <li><b>Step 5a:</b> Else, update the ContourMap by adding the GridRect's contour 
     *      polygons for each intersecting contour.</li>
     * </ol></li>
     * </ul>
     * @param interpolator the interpolator (and associated ContourInetrvals) to use to
     * generate interpolated observed (z-) values for subGrid and to get the required 
     * Contour Intervals for generating the contour polygons.
     * @param contourMap  The ContourMap to update with this GridRect's (or its subGrid's)
     * contour Polygons.
     * @throws Exception is this process fails.
     */
    protected void buildContours(Interpolator interpolator, ContourMap contourMap) 
                                                                        throws Exception{
      GridPoint[] gridPoints = new GridPoint[5];
      for (int iPoint = 0; iPoint < 5; iPoint++) {
        gridPoints[iPoint] = this.getGridPoint(iPoint);
      }
      
      /** Step 1: Return control if not in Area-of-Interest **/
      ContourIntervals contours = null;                                                      ;
      if ((this.outsideArea(gridPoints)) || (interpolator == null) ||
              ((contours = interpolator.getContourIntervals()) == null)) {
       //logger.log(Level.INFO, "GridRect[{0},{1}]: Outside", 
       //                     new Object[]{this.colIndex, this.rowIndex});
        return;
      }
      
      int maxCornerIdx = DataEntry.Max(gridPoints[1].getHiIndex(),
                                        gridPoints[2].getHiIndex(),
                                        gridPoints[3].getHiIndex(),
                                        gridPoints[4].getHiIndex());
      int minCornerIdx = DataEntry.Min(gridPoints[1].getLoIndex(),
                                        gridPoints[2].getLoIndex(),
                                        gridPoints[3].getLoIndex(),
                                        gridPoints[4].getLoIndex());
      
      /** Step 2: Call get Observed Points and doSubGridTest1 **/
      boolean doSubGrid = this.doSubGridTest1(gridPoints[0], maxCornerIdx, minCornerIdx);
      if (doSubGrid) {
        //logger.log(Level.INFO, "GridRect[{0},{1}]: DoSubGrid - Test#1", 
        //                    new Object[]{this.colIndex, this.rowIndex});
      }

      /** if (doSubGridTest1 = false) continue with Steps 3 **/
      /** Step 3: Call get Observed Points and doSubGridTest2 **/
      List<GridPoint> obsPoints = null;
      boolean hasObsData = false;
      if ((!doSubGrid) &&
              ((obsPoints = interpolator.getObsGridPointByRect(this)) !=null) &&
              (!(hasObsData = obsPoints.isEmpty()))) {
        doSubGrid = this.doSubGridTest2(obsPoints, maxCornerIdx, minCornerIdx);
        if (doSubGrid) {
          //logger.log(Level.INFO, "GridRect[{0},{1}]: DoSubGrid - Test#2", 
          //                    new Object[]{this.colIndex, this.rowIndex});
        }
      }
      
      /** if (doSubGrid = false)) continue with Steps 4 **/
      /** Step 4: Initiate Triangles and build the GridRect's contour Polygon for each
       * intersecting contour. Return to caller if none of the Triangle has any contours 
       */
      HashMap<Integer, Geometry> rectContours = new HashMap<>();
      if (!doSubGrid) {
        GridTriangle[] triangles = this.getTriangles(gridPoints);

        /**
         * Step 4a: Check is any of the Triangle has contours - is not return to caller
         */
        boolean hasContours = false;
        for (GridTriangle triangle : triangles) {
          if (triangle.hasContours) {
            hasContours = true;
            break;
          }
        }
        
        if (!hasContours) {
          return;
        }
        
        /**
         * Step 4b: Using the triangles, build the GridRect's contour Polygon for each
         * contour level.
         */
        Polygon lastPoly = null;
        for (int contourIdx = 0; contourIdx < contours.getNumContours(); contourIdx++) {
          List<Polygon> contourPolys = new ArrayList<>();
          for (GridTriangle triangle : triangles) {
            if (triangle.hasContours) {
              List<IntersectPoint> interectPoints = triangle.getContour(contourIdx, 
                contours.getContour(contourIdx), contourPolys, this.ownerGrid.gridLevel);
              if ((doSubGrid = this.doSubGridTest3(interectPoints, interpolator))) {
                //logger.log(Level.INFO, "GridRect[{0},{1}]: DoSubGrid - Test#3", 
                //                        new Object[]{this.colIndex, this.rowIndex});
                break;
              }
            }
          }
          
          if (doSubGrid) {
            break;
          }
          
          if (!contourPolys.isEmpty()) {
            Geometry rectPoly = null;
            if (contourPolys.size() == 1) {
              rectPoly = contourPolys.get(0);
            } else {
              Polygon[] polyArr = new Polygon[]{};
              polyArr = contourPolys.toArray(polyArr);
              rectPoly = Conversion.mergeJtsPolygons(polyArr);
            }
            
            if ((rectPoly != null) && (!rectPoly.isEmpty())) {
              rectContours.put(contourIdx, rectPoly);
              if (hasObsData) {
                /** Step 4c: Call doSubGridTest3 is the observed data is consistent with 
                 * the contour and if not set doSubGrid=true and stop the contour 
                 * building for this GridRect**/
                doSubGrid =
                      this.doSubGridTest4(contourIdx, obsPoints, rectPoly, lastPoly);
                if (doSubGrid) {
                  if (doSubGrid) {
                    //logger.log(Level.INFO, "GridRect[{0},{1}]: DoSubGrid - Test#4", 
                    //                    new Object[]{this.colIndex, this.rowIndex});
                  }
                  /* if doSubGrid - quite the process for this grid level - 
                   * it going to use the subgrid;s contour polygons */
                  break;
                }
              }
            }
          }
        }
      }
      
      /**
       * Step 5: Update the ContourMap - depending on doSubGrid
       */
      if (doSubGrid) {
        /**
         * Step 5a: If (doSubGrid), initiate the subGrid and call its buildContours to
         * update the contourMap.
         */
        ContourGrid grid = this.getSubGrid();
        if (grid == null) {
          throw new Exception("Initiating the SubGrid failed.");
        }
        
        grid.buildContours(interpolator, contourMap);
      } else {
        /**
         * Step 5a: Update the ContourMap by adding the GridRect's contour polygons for 
         * each intersecting contour.
         */
        for (Integer contourIdx : rectContours.keySet()) {
          Geometry rectPoly = rectContours.get(contourIdx);
          if ((rectPoly != null) && (!rectPoly.isEmpty())) {
            contourMap.add(contourIdx, rectPoly);
          }
        }
      }
      //logger.log(Level.INFO, "GridRect[{0},{1}]: Completed", 
      //                      new Object[]{this.colIndex, this.rowIndex});
    }
    
    /**
     * <p>Called to initiate this.subGrid. It creates the sub-grid using this.Bounds as
     * the Area-of-interest and subdivide the area in two columns and 2 rows. It increment
     * she subGrid's Level (i.e, ownerGrid.gridLevel+1) and assign the SubGrid boundary
     * conditions as the linear interpolated z-values along the borders of this rectangle
     * using this GridRect's corner z-values.</p>
     * @return the cached subGrid.
     * @throws Exception if a polygon build error occurred.
     */
    private ContourGrid getSubGrid() throws Exception {
      if (this.subGrid == null) {
        List<Coordinate> corners = new ArrayList<>();
        for (int iPoint = 1; iPoint < 5; iPoint++) {
          corners.add(this.getGridPoint(iPoint).asCoord());
        }
        Polygon bounds = Conversion.buildJtsPolygon(corners);
        GeometryFactory factory = ContourGrid.getGeometryFactory();
        MultiPolygon subArea = factory.createMultiPolygon(new Polygon[]{bounds});
        
        int numCols = ContourGrid.SubGridCols;
        if (numCols <= 1) {
          throw new Exception("The ContourGrid.SubGridCols is invalid. It should be "
                  + "greater than 2.");
        }
        int numRows = ContourGrid.SubGridRows;
        if (numRows <= 1) {
          throw new Exception("The ContourGrid.SubGridRows is invalid. It should be "
                  + "greater than 1.");
        }
        
        this.subGrid = new ContourGrid();
        this.subGrid.parentGridRect = this;
        this.subGrid.gridLevel = (this.ownerGrid.gridLevel + 1);
        this.subGrid.initMatrix(subArea, numCols, numRows);
      }
      return this.subGrid;
    }
    
    /**
     * Called to construct the four triangles that represents a quadrant of the rectangle
     * with point Pi[0] = this.midPoint.
     * @param gridPoints and array of GridPoint representing the midpoint and the four
     * corners.
     * @return an array of four triangle in the order top, right, bottom, and left.
     */
    private GridTriangle[] getTriangles(GridPoint[] gridPoints) {
      GridTriangle[] result = new GridTriangle[4];
      for (int i = 1; i <= 4; i++) {
        int j = (i == 4)? 1: i+1;
        result[i-1] = new GridTriangle(gridPoints[0], gridPoints[i], gridPoints[j]);
      }
      return result;
    }
    
    /**
     * <p><b>SUBGRID TEST # 1:</b> It test whether the HiIndex of the midPoint exceeds
     * the Max[hiIndex] of the corner point, or the LoIndex of the midPoint is less than
     * the Min[LoIndex] of the corner points.  If the test returns true, it implies that
     * the contour is internal to the GridRect. The all indices must </p>
     * @param midPoints the GridRect's midPoint
     * @param maxCornerIdx the maximum Index of all this four corner HiIndices
     * @param minCornerIdx the minimum Index of all this four corner LoIndices
     * @return true if the GridRect must be sub-divided; false if not OR if
     * this.ownerGrid.gridLevel &ge; ContourGrid.MaxGridLevel.
     */
    private boolean doSubGridTest1(GridPoint midPoints, int maxCornerIdx,
            int minCornerIdx) {
      return ((this.ownerGrid.gridLevel < ContourGrid.MaxGridLevel) &&
              ((midPoints.getHiIndex() < GridPoint.HI_UNDEFNED) &&
              (midPoints.getHiIndex() > maxCornerIdx)) ||
              ((midPoints.getLoIndex() > GridPoint.LO_UNDEFNED) &&
              (midPoints.getLoIndex() < minCornerIdx)));
    }
    
    /**
     * <p><b>SUBGRID TEST # 2:</b> It test whether the HiIndex of any obsPoint exceeds
     * the Max[hiIndex] of the corner point, or the LoIndex any the obsPoint is less than
     * the Min[LoIndex] of the corner points.  If the test returns true, it implies that
     * the gridRect's corner and midpoint, which is in the IndexRange[minCornerIdx ..
     * maxCornerIdx], does not represents all observed points inside the GridRect </p>
     * @param obsPoints contained inside the GridRect
     * @param maxCornerIdx the maximum Index of all this four corner HiIndices
     * @param minCornerIdx the minimum Index of all this four corner LoIndices
     * @return true if the GridRect must be sub-divided; false if not OR if
     * this.ownerGrid.gridLevel &ge; ContourGrid.MaxGridLevel.
     */
    private boolean doSubGridTest2(List<GridPoint> obsPoints, int maxCornerIdx,
            int minCornerIdx) {
      boolean result = false;
      if ((this.ownerGrid.gridLevel < ContourGrid.MaxGridLevel) &&
              (obsPoints != null) && (!obsPoints.isEmpty())) {
        for (GridPoint obsPnt : obsPoints) {
          result = ((obsPnt.getHiIndex() > maxCornerIdx) ||
                  (obsPnt.getLoIndex() < minCornerIdx));
          if (result) {
            break;
          }
        }
      }
      return result;
    }
    
    /**
     * <p><b>SUBGRID TEST # 3:</b> It test whether the interpolated z-values of the listed
     * intersectPoints are within their intersect range and will return true if at least
     * one IntersectPoint failed that test.</p>
     * @param intersectPoints the list of interpolated values
     * @param interpolator the interpolator to use in the test.
     * @return true if the test fails or false if not or intersectPoints = null|empty.
     */
    private boolean doSubGridTest3(List<IntersectPoint> intersectPoints, 
                                                            Interpolator interpolator) {
      boolean result = false;
      if ((this.ownerGrid.gridLevel < ContourGrid.MaxGridLevel) && 
                      (intersectPoints != null) && (!intersectPoints.isEmpty())) {
        IntersectPoint[] pointArr = new IntersectPoint[]{};
        pointArr = intersectPoints.toArray(pointArr);
        
        interpolator.execute(pointArr);
        for (IntersectPoint point : pointArr) {
          if (!point.isContourIntersect()) {
            if (!point.inContourInterval()) {
              result = true;
              break;
            }
          } else 
            if ((!point.inIntersectRange()) || (!point.inToleranceRange())) {
            result = true;
            break;
          }
        }
      }
      return result;
    }
    
    /**
     * <p><b>SUBGRID TEST # 4:</b> It test whether any of interpolated-observed value
     * that are contained in the contour's polygon (but not is not contained in the prior
     * - lower - contour) are within the interval of the contour. If not, the grid should
     * be subdivided to better define contours around the higher or lower observed value.
     * </p>
     * @param contourIdx the index of the current contour
     * @param obsPoints contained inside the GridRect
     * @param contourPoly the contour polygon for the contourIdx
     * @param lastPoly the contour polygon for the prior contour level.
     * @return true if the GridRect must be sub-divided; false if not OR if
     * this.ownerGrid.gridLevel &ge; ContourGrid.MaxGridLevel.
     */
    private boolean doSubGridTest4(int contourIdx, List<GridPoint> obsPoints,
            Geometry contourPoly,  Geometry lastPoly) {
      boolean result = false;
      if ((this.ownerGrid.gridLevel < ContourGrid.MaxGridLevel) &&
              (contourPoly != null) && (obsPoints != null) && (!obsPoints.isEmpty())) {
        for (GridPoint obsPnt : obsPoints) {
          if (obsPnt.inInterval(contourIdx)) {
            continue;
          }
          
          Point pt = ContourGrid.getGeometryFactory().createPoint(obsPnt);
          result = ((contourPoly.contains(pt)) &&
                  ((lastPoly == null) || (!lastPoly.contains(pt))));
          if (result) {
            break;
          }
        }
      }
      return result;
    }
    
    /**
     * Check if the GrdiRect is outside the Area-of-interest can can be ignored.
     * @param gridPoints the GirdRect's GridPoints
     * @return true if 0|1 points are insides the Area-of-interest
     */
    private boolean outsideArea(GridPoint[] gridPoints) {
      if ((this.isOutside == null) && (this.ownerGrid != null) && 
                                      (gridPoints != null) && (gridPoints.length >= 5)) {
        boolean result = this.ownerGrid.isInArea(gridPoints[0]);
        if (!result) {
          for (int i = 1; i < gridPoints.length; i++) {
            if ((result = this.ownerGrid.isInArea(gridPoints[i]))) {
              break;
            }
          }
        }
        this.isOutside = (!result);
      }
      return (this.isOutside == null)? true: this.isOutside;
    }
    //</editor-fold>
  }
  //</editor-fold>
    
  //<editor-fold defaultstate="collapsed" desc="Private Fields">
  /**
   * The ParentGrid of a subGrid
   */
  private GridRect parentGridRect;
  /**
   * The to generate the grid for (a JTS MultiPolygon)
   */
  private Geometry area;
  /**
   * The grid size in the x-direction
   */
  private Double colSize;
  /**
   * The grid size in the y-direction
   */
  private Double rowSize;
  /**
   * The 2-D array of grid points that defines the ContourGrid - Size(n,m).
   */
  private GridPoint[][] gridPoints;
  /**
   * The 2-D array of grid points that defines the ContourGrid - Size(n-1,m-1).
   */
  private GridPoint[][] midPoints;
  /**
   * The 2-D array of grid Rectangles that defines the ContourGrid - Size(n-1,m-1).
   */
  private GridRect[][] gridRects;
  /**
   * The number of GridPoint rows .
   */
  private Integer rowCount;
  /**
   * The number of GridPoint columns.
   */
  private Integer colCount; 
  /**
   * Get the GridLevel (top Level = 0)
   */
  private Integer gridLevel;
  /**
   * Placeholder for an error message
   */
  private String errorMsg;
  //</editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Cashed resources">  
  
  /**
   * Placeholder for a cached Grid Envelope
   */
  private Envelope gridEnvelope;
  /**
   * Get the cached Grid Envelope - lazy initiate if unassigned
   * @return the cached envelope
   */
  public Envelope getEnvelope() {
    if ((this.gridEnvelope == null) && (!this.isEmpty())) {
      int iN = (this.colCount - 1);
      int iM = (this.rowCount - 1);
      Double xMin = Math.min(this.gridPoints[0][0].getX(),this.gridPoints[iN][iM].getX());
      Double xMax = Math.max(this.gridPoints[0][0].getX(),this.gridPoints[iN][iM].getX());
      Double yMin = Math.min(this.gridPoints[0][0].getY(),this.gridPoints[iN][iM].getY());
      Double yMax = Math.max(this.gridPoints[0][0].getY(),this.gridPoints[iN][iM].getY());
      this.gridEnvelope = new Envelope(xMin, yMin, xMax, yMax);
    }
    return this.gridEnvelope;
  }
  //</editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Public Constructor
   */
  public ContourGrid() {    
    this.reset();     
    this.parentGridRect = null;
    this.gridLevel = 0;
  }
  
  /**
   * {@inheritDoc}
   * <p>OVERRIDE: Call the super method before calling this.reset() to dispose local
   * resources</p>
   */
  @Override
  protected void finalize() throws Throwable {
    super.finalize();
    this.parentGridRect = null;
    this.reset();            
  }
  
  /**
   * Reset a fields to their default values
   */
  private void reset() {
    this.area = null;
    this.gridPoints = null;
    this.colCount = null;
    this.colSize = null;
    this.rowSize = null;
    this.rowCount = null;
    this.errorMsg = null;
  }
  // </editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Private Methods">  
  /**
   * Check if the coordinate is contained within the Area-of-Interest
   * @param coordinate coordinate to test
   * @return true is the area and coordinate is assigned and area contains the area.
   */
  protected boolean isInArea(Coordinate coordinate) {
    boolean result = false;
    GeometryFactory factory = null;
    Point point = null;
    if ((this.area != null) && (!this.area.isEmpty()) && (coordinate != null) &&
            ((factory = ContourGrid.getGeometryFactory()) != null) &&
            (( point = factory.createPoint(coordinate)) != null) && (!point.isEmpty())) {
      for (int iGeom = 0; iGeom < this.area.getNumGeometries(); iGeom++) {
        Geometry geometry = this.area.getGeometryN(iGeom);
        if ((geometry instanceof Polygon) || (geometry instanceof MultiPolygon)) {
          if (geometry.covers(point)) {
            result = true;
            break;
          }
        }
      }
    }
    return result;
  }
  //</editor-fold>
    
  //<editor-fold defaultstate="collapsed" desc="Error handling">
  /**
   * Check is a error message is assigned
   * @return (this.errorMsg != null)
   */
  public boolean hasError() {
    return (this.errorMsg != null);
  }
  
  /**
   * Clear previously set errors
   */
  public void clearErrors() {
    this.errorMsg = null;
  }
  
  /**
   * Get the current error message
   * @return the assigned value (can be null)
   */
  public String getErrorMsg() {
    return this.errorMsg;
  }
  
  /**
   * Assigned a new error message. Ignored if ""|null. if a prior message exist, the new
   * message will be appended with a "\n" separator.
   * @param errMsg the new error message
   */
  public void setErrorMsg(String errMsg) {
    errMsg = DataEntry.cleanString(errMsg);
    if (errMsg == null) {
      return;
    }
    if (this.errorMsg == null) {
      this.errorMsg = errMsg;
    } else {
      this.errorMsg += "\n " + errMsg;
    }
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Properties">
  /**
   * Get the CountourGrid's Area of Interest
   * @return 
   */
  public Geometry getArea() {
    return this.area;
  }
  
  /**
   * Get the GridLevel (i.e. the number of sub-grids from the top). The Top-Level=0.
   * @return the assigned value.
   */
  public Integer getGridLevel() {
    return this.gridLevel;
  }
  
  /**
   * Check if the grisMatrix is empty (no GridPoints)
   * @return true if this.gridPoints is empty or undefined.
   */
  public boolean isEmpty() {
    return (this.gridPoints == null);
  }
  //</editor-fold>
    
  //<editor-fold defaultstate="collapsed" desc="Public Methods">
  /**
   * Called to initiate the ContourGrid. It generates a grid that spans the Bounds of the
   * specified area. It add points based on the specified column and row size. the start
   * point will depend on whether the column and row sizes are positive or negative. If
   * positive it will start a the smallest of the X- or Y dimensions of the bounds and
   * add the specified size to this start value. If negative it will use the largest X- or
   * Y-dimensions of the bounds and deduct the specified size to this start value.
   * @param area the area of interest
   * @param numCols the number of gridPoint columns (i.e. the number of GridRects + 1).
   * Must be &ge 3.
   * @param numRows the number of gridPoint rows (i.e. the number of GridRects + 1).
   * Must be &ge 3.
   * @throws Exception if the area is undefined or empty, the numCol or numRows is
   * &lt; 3, or the column- or row-count exceed Integer.MAXVALUE.
   */
  public void initMatrix(Geometry area, int numCols, int numRows) throws Exception {
    try {
      logger.log(Level.INFO, "Start Initiating ContourGrid.");
      if ((area == null) || (area.isEmpty())) {
        throw new Exception("The Area Polygon is undefined or empty.");
      }
      
      Geometry areaEnvelope = area.getEnvelope();
      if (!(areaEnvelope instanceof Polygon)) {
        throw new Exception("The Area-of-Interest is invalid. Its Evelope is a not an "
                + "polygon.");
      }
      
      if (numCols < 3) {
        throw new Exception("The number of columns["+ numCols + "] is invalid, "
                + "expected 3 or more columns.");
      }
      if (numRows < 3) {
        throw new Exception("The number of columns["+ numRows + "] is invalid, "
                + "expected 3 or more columns.");
      }
      
      //TODO: Convert AreaEnvelope to and Envelope[bounds].
      Envelope bounds = areaEnvelope.getEnvelopeInternal();
      this.gridEnvelope = null;
      this.colCount = numCols;
      this.rowCount = numRows;
      this.colSize = (bounds.getMaxX() - bounds.getMinX())/(1.0d * (numCols-1));
      this.rowSize = (bounds.getMaxY() - bounds.getMinY())/(1.0d * (numRows-1));      
      this.area = area;
      this.gridPoints = new GridPoint[this.colCount][this.rowCount];
      this.midPoints = new GridPoint[this.colCount-1][this.rowCount-1];
      this.gridRects = new GridRect[this.colCount-1][this.rowCount-1];
      
      double startX = (this.colSize < 0.0d)? Math.max(bounds.getMaxX(), bounds.getMinX()):
              Math.min(bounds.getMaxX(), bounds.getMinX());
      double startY = (colSize < 0.0d)? Math.max(bounds.getMaxY(), bounds.getMinY()):
              Math.min(bounds.getMaxY(), bounds.getMinY());
      
      //Integer numOutside = 0;
      for (int iRow = 0; iRow < this.rowCount; iRow++) {
        for (int iCol = 0; iCol < this.colCount; iCol++) {
          double xCoord = startX + (iCol * this.colSize);
          double yCoord = startY + (iRow * this.rowSize);
          Coordinate coordinate = new Coordinate(xCoord, yCoord);
          //boolean isOutside = (!this.isInArea(coordinate));
          //GridPoint gridPoint = new GridPoint(coordinate, isOutside);
          GridPoint gridPoint = new GridPoint(coordinate);
          this.gridPoints[iCol][iRow] = gridPoint;
//          if (isOutside) {
//            numOutside++;
//          }
          
          if ((iRow > 0) && (iCol > 0)) {
            xCoord = (this.gridPoints[iCol-1][iRow-1].getX() + 
                      this.gridPoints[iCol][iRow].getX())/2.0d;
            yCoord = (this.gridPoints[iCol-1][iRow-1].getY() + 
                      this.gridPoints[iCol][iRow].getY())/2.0d;
            coordinate = new Coordinate(xCoord, yCoord);
//            isOutside = (!this.isInArea(coordinate));
//            GridPoint midPoint = new GridPoint(coordinate, isOutside);
            GridPoint midPoint = new GridPoint(coordinate);
            this.midPoints[iCol-1][iRow-1] = midPoint;
            
            GridRect rect= new GridRect(this, iCol-1, iRow-1);
            this.gridRects[iCol-1][iRow-1] = rect;
          }
        }
      }
      logger.log(Level.INFO, "Completed ContourGrid Initiation.");
      
//      Long numPoint = (this.colCount.longValue() * this.rowCount.longValue());
//      logger.log(Level.INFO, "Grid: Level={0}; Size={1}; Num Outside Area={2}",
//              new Object[]{this.gridLevel, numPoint, numOutside});
    } catch (Exception exp) {
      logger.log(Level.INFO, "Initiating ContourGrid Failed.");
      logger.log(Level.WARNING, "{0}.initMatrix Error:\n {1}",
              new Object[]{this.getClass().getSimpleName(), exp.getMessage()});
    }
  }
  
  /**
   * Called to reset All prior contour related settings.
   */
  public void resetContours() {
    this.clearErrors();
    if (this.isEmpty()) {
      return;
    }
    
    try {
      for (int iRow = 0; iRow < this.rowCount; iRow++) {
        for (int iCol = 0; iCol < this.colCount; iCol++) {
          this.gridPoints[iCol][iRow].reset();
          if ((iRow > 0) && (iCol > 0)) {
            this.midPoints[iCol-1][iRow-1].reset();
            this.gridRects[iCol-1][iRow-1].reset();
          }
        }
      }
    } catch (Exception exp) {
    }
  }
  
  /**
   * <p>The method is called to update the ContourMap using this grid's GrdiPoints and
   * MidPoints and the associated GridRect.  This process includes the following steps:
   * </p> <ul>
   * <li><b>Step 1:</b> Calling the interpolator's {@linkplain Interpolator#execute(
   * gov.ca.water.contours.GridPoint[][], int, int) execute} method to interpolate the 
   * z-values of each Grid- and MidPoint.</li>
   * <li><b>Step 2:</b> Calling {@linkplain #updateBoundaryConditions(
   * gov.ca.water.contours.intervals.ContourIntervals) this.updateBoundaryConditions} to
   * update the border GridPoint's z-value to maintain boundary conditions with 
   * neighboring GridRect - ONLY IF GridLevel &gt; 0.</li>
   * <li><b>Step 3:</b> Call each GridRect's {@linkplain GridRect#buildContours(
   * gov.ca.water.math.interpolate.Interpolator, gov.ca.water.contours.ContourMap) 
   * buildContours method to add its contour polygons to the contourMap.</li>
   * </ul>
   * <p>All Exceptions are trapped, logged, and assign as this.errorMsg.</p>
   * @param interpolator the interpolator (and associated ContourInetrvals) to use to
   * generate interpolated observed (z-) values for subGrid and to get the required
   * Contour Intervals for generating the contour polygons.
   * @param contourMap The ContourMap to update with this CountourGrid's contour Polygons.
   * @return return true if this.hasError = false
   * @see <a href="package_summary.html" >Package Summary</a> for more information.
   */
  public boolean buildContours(Interpolator interpolator, ContourMap contourShapes) {
    this.resetContours();
    try {
      if (this.gridLevel == 0) {
        logger.log(Level.INFO, "Start Building Contours.");
      }

      if ((interpolator == null) || (interpolator.isEmpty())) {
        throw new Exception("The Observed Value Interpolator is unassigned or is empty"
                + "(no assigend observed data points).");
      }
      
      ContourIntervals contours = interpolator.getContourIntervals();
      if ((contours == null) || (contours.isEmpty())) {
        throw new Exception("The Contour Intervals is undefined or empty.");
      }
      
      /**
       * Step 1: Execute the Interpolation on the grid- and midPoints.
       */
      if (!interpolator.execute(this.gridPoints, this.colCount, this.rowCount)) {
        throw new Exception(interpolator.getErrorMsg());
      }
      if (!interpolator.execute(this.midPoints, this.colCount-1, this.rowCount-1)) {
        throw new Exception(interpolator.getErrorMsg());
      }
      
      /**
       * Step 2: If (gridLevel>0), update the boundary conditions
       */
      //this.updateBoundaryConditions(contours);
      
      /**
       * Step 3: Call each GridRect's buildContours to update the contourMap
       */
      for (int iRow = 0; iRow < this.rowCount-1; iRow++) {
        for (int iCol = 0; iCol < this.colCount-1; iCol++) {
          GridRect rect = this.gridRects[iCol][iRow];
          rect.buildContours(interpolator, contourShapes);
        }
      }
      if (this.gridLevel == 0) {
        logger.log(Level.INFO, "Completed Contours.");
      }
    } catch (Exception exp) {
      logger.log(Level.WARNING, "{0}.buildContours Error:\n {1}",
              new Object[]{this.getClass().getSimpleName(), exp.getMessage()});
      this.resetContours();
    }
    return (!this.hasError());
  }
  
  /**
   * Called by {@linkplain #buildContours(gov.ca.water.math.interpolate.Interpolator, 
   * gov.ca.water.contours.ContourMap) this.buildContours} after assign interpolated 
   * values to all GridPoints to update the z-value of border GridPoint to maintain 
   * boundary conditions with neighboring GridRect
   * @param contours the ContourInetrvals used for interpolation
   * @throws Exception 
   */
  private void updateBoundaryConditions(ContourIntervals contours) throws Exception{
    if ((this.gridLevel == 0) || (this.parentGridRect == null)) {
      return;
    }
    
    try {
      /* Set first Rows Z-Values as that of GridRect - Top Boundary Conditions */
      double z0 = this.parentGridRect.getGridPoint(1).getZ();
      double zN = this.parentGridRect.getGridPoint(2).getZ();
      double dZ = (zN - z0) / (this.colCount - 1);
      for (int iCol = 0; iCol < this.colCount; iCol++) {
        double zVal = z0 + (iCol * dZ);
        this.gridPoints[iCol][0].setZ(zVal, contours);
      }

      /* Set Last Columns Z-Values as that of GridRect - Right Boundary Conditions */
      z0 = this.parentGridRect.getGridPoint(2).getZ();
      zN = this.parentGridRect.getGridPoint(3).getZ();
      dZ = (zN - z0) / (this.rowCount - 1);
      for (int iRow = 0; iRow < this.rowCount; iRow++) {
        double zVal = z0 + (iRow * dZ);
        this.gridPoints[this.colCount - 1][iRow].setZ(zVal, contours);
      }
      /* Set last Rows Z-Values as that of GridRect - Bottom Boundary Conditions */
      z0 = this.parentGridRect.getGridPoint(4).getZ();
      zN = this.parentGridRect.getGridPoint(3).getZ();
      dZ = (zN - z0) / (this.colCount - 1);
      for (int iCol = 0; iCol < this.colCount; iCol++) {
        double zVal = z0 + (iCol * dZ);
        this.gridPoints[iCol][this.rowCount - 1].setZ(zVal, contours);
      }

      /* Set First Columns Z-Values as that of GridRect - Left Boundary Conditions */
      z0 = this.parentGridRect.getGridPoint(1).getZ();
      zN = this.parentGridRect.getGridPoint(4).getZ();
      dZ = (zN - z0) / (this.rowCount - 1);
      for (int iRow = 0; iRow < this.rowCount; iRow++) {
        double zVal = z0 + (iRow * dZ);
        this.gridPoints[0][iRow].setZ(zVal, contours);
      }
    } catch (Exception exp) {
      throw new Exception(this.getClass().getSimpleName()
              + ".updateBoundaryConditions Error:\n " + exp.getMessage());
    }
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Map Support">
  /**
   * Called to get the ContourGrid's GrdiPoint as a list of {@linkplain Marker Markers}
   * @return the list or an empty list if this.isEmpty
   */
  public List<Marker> getGridPointMarkers() {
    List<Marker> result = new ArrayList<>();
    if (!this.isEmpty()) {
      int priorRowStart = this.rowCount-1; //(this.gridLevel > 0)? 0: this.rowCount-1;
      int priorRowEnd = 0; //(this.gridLevel > 0)? this.rowCount-1: 0;
      for (int iRow = 0; iRow < this.rowCount-1; iRow++) {
        boolean isFirstCol = true;
        int rowStart = 0;
        int rowEnd = 0;
        for (int iCol = 0; iCol < this.colCount-1; iCol++) {
          GridRect rect = this.gridRects[iCol][iRow];
          if (rect.isOutsideArea()) {
            continue;
          }
          
          /* Add MidPoint */
          result.add(this.getMarker(rect.getGridPoint(0)));
          
          boolean isLastCol = (iCol == (this.colCount-2));
          rowEnd = iCol;
          if (isFirstCol) {
            rowStart = iCol;
            if (((iRow == 0) || (rowStart < priorRowStart)) && 
                                            ((iCol > 0) || (this.gridLevel == 0))) {
              /* Add TopLeftPoint only when isFirstCol and rowStart < priorRowStart*/
              result.add(this.getMarker(rect.getGridPoint(1)));
            }
            /*
             * Add BottomLeftPoint only when isFirstCol - do not add this point if
             * gridlevel > 0 and this is the last row
             */
            if ((iRow < this.rowCount-2) || (this.gridLevel == 0)) {
              result.add(this.getMarker(rect.getGridPoint(4)));
            }
          }
          
          if (((iRow == 0) || (rowStart < (priorRowStart - 1)) || (iCol > priorRowEnd)) &&
                  ((!isLastCol) || (this.gridLevel == 0))) {
            /* Add TopRightPoint when rowStarte < (priorRowStart -1) or if
             * this.gridLevel=0 and iCol > priorRowEnd */
            result.add(this.getMarker(rect.getGridPoint(2)));
          }
          
          /**
           * Always add Point 3 - unless gridLevel > 0 and this is the last row
           */
          if ((iRow < this.rowCount-2) || (!isLastCol) || (this.gridLevel == 0)) {
            result.add(this.getMarker(rect.getGridPoint(3)));
          }
          
          if (rect.subGrid != null) {
            List<Marker> subMarkers = rect.subGrid.getGridPointMarkers();
            if ((subMarkers != null) && (!subMarkers.isEmpty())) {
              result.addAll(subMarkers);
            }
          }
        }
        priorRowEnd = rowEnd;
        priorRowStart = rowStart;
      }
    }
    return result;
  }
  
  /**
   * Get a marker for the specified point
   * @param point
   */
  private Marker getMarker(GridPoint point) {
    Marker result = new Marker();
    Coord location = new Coord(point.getX(), point.getY());
    String name = "[" + point.getLoIndex() + "," 
            + DataEntry.toString(point.getZ(), 2, true, null, null, "-") + "]";
    result.setCoordinate(location);
    result.setName(name);
    SymbolizerMarker symbolizer = new SymbolizerMarker(Color.BLACK);
    symbolizer.setHeight(3);
    symbolizer.setWidth(3);
    symbolizer.setPopupEnabled(true);
    result.setSymbolizer(symbolizer);
    return result;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Object Overrides">
  /**
   * {@inheritDoc}
   * <p>OVERRIDE: Returns a string with key properties of the ContourGrid for reporting
   * purposes.</p>
   */
  @Override
  public String toString() {
    String result = null;
    if (this.isEmpty()) {
      result = "Empty";
    } else {
      result = "ContourGrid[Level = " + this.getGridLevel() + ";\n" ;
      result += "\t" + this.getEnvelope().toString() + ";\n";
      result += "\tColumn Count = " + this.colCount +"; Row Count = " + this.rowCount
              + ";\n" ;
      result += "\tGridPoints:" + ";\n" ;
      for (int iCol = 0; iCol < this.colCount; iCol++) {
        for (int iRow = 0; iRow < this.rowCount; iRow++) {
          result += "\t - GridPoint[" + iCol + "," + iRow + ": "
                  + this.gridPoints[iCol][iRow].toString() + ";\n";
        }
      }
      result += "]";
    }
    return result;
  }
  //</editor-fold>
}
