package com.gei.contour;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import gov.ca.water.common.events.EventHandler;
import gov.ca.water.common.events.EventInfo;
import gov.ca.water.contours.ContourBuilder;
import gov.ca.water.contours.ContourGrid;
import gov.ca.water.contours.ContourMap;
import gov.ca.water.contours.intervals.ContourIntervals;
import gov.ca.water.math.interpolate.IDWInterpolator;
import gov.ca.water.math.interpolate.Interpolator;
import gov.ca.water.precipcontour.intervals.PrecipContourIntervals;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapefileException;
import gov.ca.water.shapelite.data.Marker;
import gov.ca.water.shapelite.io.ShapefileReader;
import gov.ca.water.shapelite.io.ShapefileWriter;
import gov.ca.water.shapelite.map.MapLayerMarker;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.Projections;
import gov.ca.water.shapelite.projection.Reproject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openide.util.Exceptions;

/**
 *
 * @author hdunsford
 */
public class ContourManager {
    
  //<editor-fold defaultstate="collapsed" desc="Static Logger">
  /**
   * Protected Static Logger object for logging errors, warnings, and info messages.
   */
  protected static final Logger logger = Logger.getLogger(ContourManager.class.getName());
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="ContourManager Singleton">
  /**
   * Static Class for Holding and initiating the Singleton In
   *
   */
  private static class ContourManagerHolder {

    private static final ContourManager INSTANCE = new ContourManager();
  }

  /**
   * Static method for accessing the Singleton
   *
   * @return PinchPointManagerHolder.INSTANCE
   */
  public static ContourManager getInstance() {
    return ContourManagerHolder.INSTANCE;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Private Fields">
  /**
   * Placeholder for the ContourBuilder.
   */
  private ContourBuilder contourBuilder;  
  /**
   * Placeholder for the Contours FeatureSet
   */
  private FeatureSet contours;  
  /**
   * Placeholder for the MapLayer to draw the Pinch Points along CA border
   */
  private MapLayerMarker boundaryLayer;
  /**
   * Cached list of shape of CA Boundaries
   */
  private List<Shape> boundaryShapes;
  /**
   * Flag controlling whether the Boundary Layer is enabled (visible) (default=null|false)
   */
  private Boolean boundaryEnabled;
  /**
   * Flag controlling whether a spline method should be used in generating the contours
   * (default = null|false
   */
  private Boolean splineEnabled;
  /**
   * Placeholder the current date for which Observed Data has been contoured.
   */
  
  private HttpServletResponse response;
  
  private String path;
//  private Day currentDay;
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="EventHandlers">
//  /**
//   * Private Static ContourCreated Event Listener
//   */
//  private final EventListenerList contourListeners = new EventListenerList();
//  
//  /**
//   * Allows a new listener to be added to this object.
//   * @param listener the listener to add.
//   */
//  public void addContourListener(ContourCreatedListener listener) {
//    ContourCreatedListener[] listeners = 
//                          contourListeners.getListeners(ContourCreatedListener.class);
//    if (!Arrays.asList(listeners).contains(listener)) {
//      contourListeners.add(ContourCreatedListener.class, listener);
//    }
//  }
//  
//  /**
//   * Removes an existing ContourCreated listener
//   * @param listener the listener to remove.
//   */
//  public void removeContourListener(ContourCreatedListener listener) {
//    contourListeners.remove(ContourCreatedListener.class, listener);
//  }
//  
//  /**
//   * Fires the event.
//   * @param e the EventObject containing information about the source of this
//   * event.
//   */
//  public void fireContourCreated(ContourCreatedEvent e) {
//    ContourCreatedListener[] listeners =
//            contourListeners.getListeners(ContourCreatedListener.class);
//    for (ContourCreatedListener listener : listeners) {
//      listener.contourCreated(e);
//    }
//  }
  
  /**
   * The EventHandler that fires the Contours Updated Event.
   */
  public final EventHandler ContoursUpdated;

  /**
   * Called to fire the Contours Updated Event.
   *
   *
   * @param eventInfo the event info
   */
  protected final void fireContoursUpdated() {
    this.ContoursUpdated.fireEvent(this, new EventInfo());
  }
  //</editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Private Constructor for Singleton
   */
  public ContourManager() {
    super();    
    this.ContoursUpdated = new EventHandler();
//    this.currentDay = null;
    this.contourBuilder = null;
    this.contours = null;
    this.boundaryShapes = null;
    this.boundaryLayer = null;
    this.boundaryEnabled = null;
    this.splineEnabled = null;
  }

  /**
   * {@inheritDoc}
   * <p>OVERRIDE: Dispose local resources and clear EventListeners</p>
   */
  @Override
  protected void finalize() throws Throwable {
    super.finalize();
    this.ContoursUpdated.clear();
    if (this.contourBuilder != null) {
      this.contourBuilder.ContoursChanged.clear();
    }
    this.contourBuilder = null;
    this.boundaryShapes = null;
    this.contours = null;
  }
  // </editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Private/Protected Methods">
  /**
   * Get the Top Map Component to draw on.
   * @return WindowManager.getDefault().findTopComponent("MapTopComponent").
   */
//  private MapTopComponent getMap() {
//    MapTopComponent result = 
//       (MapTopComponent) WindowManager.getDefault().findTopComponent("MapTopComponent");
//    return result;
//  }
  
  /**
   * Get the cached contourBuilder - initiate it if null.
   * @return this.contourBuilder
   */
  private ContourBuilder getContourBuilder() {
//    if (this.contourBuilder == null) {
      PrecipContourIntervals contourInt = new PrecipContourIntervals();
      Interpolator interpolator = new IDWInterpolator(contourInt);     
      this.contourBuilder = new CaStateContourBuilder(interpolator, this.path);
      this.contourBuilder.ContoursChanged.add(this, "onContourBuilderContoursChanged");
//    }
    return this.contourBuilder;
  }
  
  /**
   * Get the cached list of BoudnaryShapes
   * @return the list of shapes
   */
  private List<Shape> getBoundaryShapes() {
    if (this.boundaryShapes == null) {
      try {
        ShapefileReader shapeReader = new ShapefileReader();
        InputStream shp = 
                    ContourManager.class.getResourceAsStream("resources/CntyBnds_ESRI.shp");
        InputStream shx = 
                    ContourManager.class.getResourceAsStream("resources/CntyBnds_ESRI.shx");
        InputStream dbf = 
                    ContourManager.class.getResourceAsStream("resources/CntyBnds_ESRI.dbf");
        shapeReader.open(shp, shx, dbf);
        this.boundaryShapes = shapeReader.getShapes();
      } catch (Exception exp) {
        logger.log(Level.WARNING, "{0}.getBoundaryShapes Error:\n {1}",
                new Object[]{this.getClass().getSimpleName(), exp.getMessage()});
      }
    }
    return this.boundaryShapes;
  }
  
  /**
   * Called to initiates the PinchPoint Layer's Boundary - Currently, the boundary of 
   * California as defined in the "resources/CA-Small.*" shape files.
   */
  private List<Coord> getBoundaryPoints() {
    List<Coord> result = null;
    try {
      List<Shape> caShapes = this.getBoundaryShapes();
      result = caShapes.get(0).getCoordinates();
    } catch (Exception exp) {
      logger.log(Level.WARNING, "{0}.getBoundaryPoints Error:\n {1}",
              new Object[]{this.getClass().getSimpleName(), exp.getMessage()});
    } 
    return result;
  }
  
  /**
   * Called to update the map's BoudnaryLayer
   */
//  private void updateBoundaryLayer() {
//    try {
//      MapTopComponent map = this.getMap();
//      MapPanel mapPanel = null;
//      MapContent mapContent = null;
//      if ((map == null) || ((mapPanel = map.getMapPanel()) == null)
//              || ((mapContent = mapPanel.getContent()) == null)) {
//        throw new Exception("The Map Component, the Map Panel, or the Map Content"
//                + " is not accessible");
//      }
//
//      if (this.boundaryLayer != null) {
//        mapContent.removeLayer(this.boundaryLayer);
//      }
//
//      List<Coord> coords = null;;
//      if ((this.isBoundaryEnabled()) &&
//              ((coords = this.getBoundaryPoints()) != null) && (!coords.isEmpty())) {
//        List<Marker> markerList = new ArrayList<>();
//        for (Coord coord : coords) {
//          Marker marker = new Marker();
//          marker.setCoordinate(coord);
//          marker.getSymbolizer().setFillColor(Color.GREEN);
//          marker.getSymbolizer().setWidth(4);
//          marker.getSymbolizer().setHeight(4);
//          markerList.add(marker);
//        }
//       
//        this.boundaryLayer = mapContent.addMarkers(markerList);
//        this.boundaryLayer.setName("California Border");
//      }
//      
//      mapContent.paintImmediately();
//      mapPanel.repaint();
//    } catch (Exception exp) {
//      logger.log(Level.WARNING, "{0}.updateBoundaryLayer Error:\n {1}",
//              new Object[]{this.getClass().getSimpleName(), exp.getMessage()});
//    }
//  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Public Properties">
  /**
   * Get whether the Draw Boundary tool is enabled.
   * @return true if enabled
   */
  public boolean isBoundaryEnabled() {
    return ((this.boundaryEnabled != null) && (this.boundaryEnabled));
  }
  
  /**
   * Called whether the Draw Boundary tool is enabled/disabled (default=disabled)
   * @param enabled true to set Draw Boundary enabled
   */
//  public void setBoundaryEnabled(boolean enabled) {
//    this.boundaryEnabled = (!enabled)? null: enabled;
//    this.updateBoundaryLayer();
//  }
  
  /**
   * Get whether the Draw Spline tool is enabled.
   * @return true if enabled
   */
  public boolean isSplineEnabled() {
    return ((this.splineEnabled != null) && (this.splineEnabled));
  }
  
  /**
   * Called to set the Spline Draw enabled/disabled (default=disabled)
   * @param splineEnabled true to set Spline Draw Enabled 
   */
  public void setSplineEnabled(boolean splineEnabled) {
    this.splineEnabled = (!splineEnabled)? null: splineEnabled;
  }
  
  /**
   * Get the current contours.
   * @return the contours FeatureSet
   */
  public FeatureSet getContours() {    
    return contours;
  }
  
  /**
   * Get the ContourManager's ContourInetrvals
   * @return this.contourBuilder.contourIntervals or null if this.contourBuilder=null.
   */
  public ContourIntervals getContourIntervals() {
    ContourBuilder builder = this.getContourBuilder();
    return (builder == null)? null: builder.getContourIntervals();
  }
  
  /**
   * Get the ContourManager's ContourInetrvals
   * @return this.contourBuilder.contourIntervals or null if this.contourBuilder=null.
   */
  public List<Marker> getGridPointMarkers() {
    List<Marker> result = null;
    ContourGrid grid = null;
    if ((this.contourBuilder != null) && 
                                    ((grid = this.contourBuilder.contourGrid) != null)) {
      result = grid.getGridPointMarkers();
    }
    return result;
  }
  
  /**
   * Check if the ContourManager has Contour Data for the currently selected day. It 
   * checks if the currentDay is assigned, if the selectdDay has changed, or 
   * if ContourBuilder is assigned whether is has a set of Contours.
   * @return true if this.contourBuilder != null and this.contourBuilder.hasContours 
   */
//  public boolean hasContours() {
//    boolean result = false;
//    Date selectedDate = Settings.getInstance().getSelectedDate();
//    Day newDay = (selectedDate == null)? null: new Day(selectedDate);
//    result = ((this.currentDay != null) && (DataEntry.isEq(this.currentDay, newDay)));
//    if (result) {
//      result = ((this.contourBuilder != null) && (this.contourBuilder.hasContours()));
//    }
//    return result;
//  }  
  
  /**
   * Called to reset the current contours.  Skipped if (!this.contourBuild.hasContours)
   */
  public void resetContours() {
    try {
//      this.currentDay = null;
      if ((this.contourBuilder != null) && (this.contourBuilder.hasContours())) {
        this.contourBuilder.resetObsData();
      }
    } catch (Exception exp) {
    }
  }
  
  /**
   * <p>The action method - called to create a new set of contours using the current user
   * selected data. If the contourBuilder or its interpolator is  unassigned the process 
   * is skipped without any events. If {@linkplain #hasContours() this.hasContours} = 
   * true, or {@linkplain Settings#getShowContours()} = false - no need to re-building
   * the contours - its fires the ContoursUpdated event - to render the layer - and skip 
   * the rest of the process.</p>
   * <p>It retrieved the observed data and the associated sensor's station location to
   * create a list of XYZ coordinates (with z = the observed data value). It the update
   * the ContourBuilder's Interpolation by {@linkplain Interpolator#setObsData(
   * java.util.List) setting it Observed data}.</p>
   * <p>The Contour is listening to the ContourBuilder's {@linkplain 
   * ContourBuilder#ContoursChanged ContoursChanged} event, and if the vent is fire, it 
   * will it's own event to notify listeners that the contour FeatureSet have been 
   * updated</p>
     * @param response
   */
  public void updateContours(HttpServletResponse response, List<GWLTest> data, String path) {
    this.response = response;
    this.path = path;
    ContourBuilder builder = this.getContourBuilder();
    Interpolator interpolator = null;
    if ((builder == null) || ((interpolator = builder.getInterpolator()) == null)) {
      return;
    }
    try {    
        List<Coordinate> obsData = new ArrayList<>();        

        for (GWLTest entry : data) {

            double x = entry.getX();
            double y = entry.getY();
            if (x == 0 && y == 0) {
                continue;
            }
            double z = entry.getWse();
            Coordinate c = new Coordinate(x, y, z);
            obsData.add(c);
        }        
        interpolator.setObsData(obsData);

    } catch (Exception exp) {
      this.resetContours();
      logger.log(Level.WARNING, "{0}.createContour Error:\n {1}",
              new Object[]{this.getClass().getSimpleName(), exp.getMessage()});
    }
    
    //<editor-fold defaultstate="collapsed" desc="Old Code by Ted - using Tins">
    //    /**
    //     * If the PinchPoints was created, use them instead of the raw
    //     */
    //    List<Coord> pinches = PinchPointManager.getInstance().getCoordinates();
    //    if (pinches != null && !pinches.isEmpty()) {
    //      coords.addAll(pinches);
    //    }
    //
    //    try {
    //      FeatureSet fs = ContourLineBuilder.createEqualContours(coords, 6, splineEnabled);
    //      setContours(fs);
    //    } catch (ContourException ex) {
    //      Exceptions.printStackTrace(ex);
    //    }
    //</editor-fold>
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Event Listener Methods">
  /**
   * <p>EventHandler: Handles the contours Changed for ContourBuilder</p>
   * <p>If (sender = this.contourBuilder), get newContours = this.contourBuilder's
   * contourMap.{@linkplain ContourMap#getFeatureSet() featureSet} and call {@linkplain
   * #updateContours(gov.ca.water.shapelite.FeatureSet) this.updateContours()} to assign
   * the internal reference and fire the update event.</p>
   * @param sender the event sender
   * @param eventInfo the event info
   */
  public final void onContourBuilderContoursChanged(Object sender, EventInfo eventInfo) {
    if ((this.contourBuilder != null) && (this.contourBuilder == sender)) {
        ContourGrid grid = this.contourBuilder.getContourGrid();
        Geometry areaOfInterest = (grid == null)? null: grid.getArea();
        ContourMap contourMap = this.contourBuilder.getContourMap();
        this.contours = contourMap.getFeatureSet(areaOfInterest);
        try {
            JSONObject jsonObject = new JSONObject();
            String allShapes = "";
            JSONArray jsonArray = new JSONArray();
            for(Feature f : this.contours.getFeatures()) {                   
                JSONObject contourIdx = new JSONObject();
                contourIdx.put("elevation",Double.valueOf(f.getAttributes().get("Contour")));
                contourIdx.put("index",Integer.valueOf(f.getAttributes().get("ContourIdx")));                
                jsonArray.put(contourIdx);
                String test = "";
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();                 
                for(Part part : f.getShape().getParts()){                        
                    for(Coord coord : part.getCoordinates()){                        
                        test = test + "[" + coord.X + "," + coord.Y + "],";
                    }                    
                      test = test + "~";
                }
                if(test != "") {
                    test = test.substring(1, test.length()-3);
                    allShapes = allShapes + test + "--";
                }                
            }                   
            jsonObject.put("contour", allShapes);            
            jsonObject.put("indices", jsonArray);
            this.response.getWriter().print(jsonObject);
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
//      ShapefileWriter writer = new ShapefileWriter();
//        try {
//            writer.write(this.contours,"C:\\Users\\ileung\\Documents\\Test2\\GWLDATA\\contour\\contourSample.shp");
//        } catch (ShapefileException ex) {
//            Exceptions.printStackTrace(ex);
//        }
         this.fireContoursUpdated();
    }
  }
  //</editor-fold>
}
  
  //<editor-fold defaultstate="collapsed" desc="Old Code by Ted">
  //public void init
  
  //    public void getInterpolationPoints() {
  //        if(this.layer != null){
  //            getMap().getMapPanel().getContent().getLayers().remove(layer);
  //            getMap().getMapPanel().getContent().paintImmediately();
  //            getMap().getMapPanel().repaint();
  //            this.coordinates.clear();
  //            return;
  //        }
  //
  //        Day day = new Day(Settings.getInstance().getSelectedDate());
  //        HashMap<Integer, DailyData> data = CdecData.getInstance().getPrecipValues().get(day);
  //        HashMap<Integer, Sensor> sensors = CdecData.getInstance().getPrecipSensors();
  //        List<Coord> coords = new ArrayList<>();
  //        for (DailyData entry : data.values()) {
  //            Sensor sensor = sensors.get(entry.getSensorId());
  //            if (sensor == null) {
  //                logger.log(Level.SEVERE, "Did find a sensor {0}", entry.getSensorId());
  //                continue;
  //            }
  //            if (sensor.getStation() == null) {
  //                logger.log(Level.SEVERE, "Did find a station object for {0}", sensor.getStationId());
  //                continue;
  //            }
  //            double x = -sensor.getStation().getLongitude();
  //            double y = sensor.getStation().getLatitude();
  //            if (x == 0 && y == 0) {
  //                continue;
  //            }
  //            double z = entry.getValue();
  //            Coord c = new Coord(x, y, z);
  //            coords.add(c);
  //        }
  //        this.coordinates = new ArrayList<>();
  //        for (int i = 0; i < 100; i++) {
  //            for (int j = 0; j < 100; j++) {
  //                double x = -124 + i / 10.0;
  //                double y = 42 - j / 10.0;
  //                Coord c = new Coord(x, y);
  //                boolean tooClose = false;
  //                List<Coord> neighbors = new ArrayList<>();
  //                for (Coord other : coords) {
  //                    if (c.distance(other) < .01) {
  //                        tooClose = true;
  //                        break;
  //                    }
  //                    if (c.distance(other) < .5) {
  //                        neighbors.add(other);
  //                    }
  //                }
  //                if (tooClose) {
  //                    continue;
  //                }
  //                int count = neighbors.size();
  //                double totalWeight = 0;
  //                for (int k = 0; k < count; k++) {
  //                    totalWeight += 1 / neighbors.get(k).distance(c);
  //                }
  //                double value = 0;
  //                for (int k = 0; k < count; k++) {
  //                    Coord item = neighbors.get(k);
  //                    double weight = 1 / item.distance(c);
  //                    value += (weight * item.Z) / totalWeight;
  //                }
  //                c.Z = value;
  //                this.coordinates.add(c);
  //            }
  //        }
  //    }
  
  //</editor-fold>