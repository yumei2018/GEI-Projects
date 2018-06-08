package gov.ca.water.shapelite.labels.grahics;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.Segment;
import gov.ca.water.shapelite.data.DataFrame;
import gov.ca.water.shapelite.map.Mercator;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Projections;
import gov.ca.water.shapelite.projection.Reproject;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author J.G. "Koos" Prins, D.Eng. PE.
 */
public class MapCanvas {

  //<editor-fold defaultstate="collapsed" desc="Static Settings">
  /**
   * The Default Pixels-per-inch ratio [{@value}]
   */
  public static final int DefaultPPI = 96;

  /**
   * Font points are 72.272 points per inch.
   */
  public static final double POINTS_PER_INCH = 72.272;

  /**
   * THe standard WGS84 projection.
   */
  public static final ProjectionInfo WGS84
      = Projections.getGeographic().getWorld().getWGS1984();

  //</editor-fold>
  /**
   * @return the projection
   */
  public ProjectionInfo getProjection() {
    return projection;
  }

  //<editor-fold defaultstate="collapsed" desc="Private MapToView Converter Class">
  private class MapToView {

    // <editor-fold defaultstate="collapsed" desc="Public Final Fields">
    public final float minX;
    public final float maxY;
    public final float dX;
    public final float dY;
    public final Point origin;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Public Constructor
     */
    public MapToView(Point origin, float minX, float minY, float dX, float dY) {
      this.origin = origin;
      this.minX = minX;
      this.maxY = minY;
      this.dX = dX;
      this.dY = dY;
    }
    // </editor-fold>
  }
//</editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Private Fields">
  /**
   * The Map's DataFrame defining the map's Envelope and the View Area's
   * Rectangle
   */
  public final DataFrame dataFrame;
  /**
   * The Graphics component on which to draw
   */
  public final Graphics2D graphics;
  /**
   * The projection for the dataset.
   */
  private final ProjectionInfo projection;
  /**
   * The CanvasSize (by default the size of the dataFrame)
   */
  private CanvasSize size;
  /**
   * The canvas scale (default = CanvasScale(1.0))
   */
  private CanvasScale scale;
  /**
   * The transient/cached MapToView converter
   */
  private transient MapToView mapToView = null;

  //</editor-fold>
  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Public Constructor
   *
   * @param graphics
   * @param dataFrame The data frame to use.
   */
  public MapCanvas(Graphics2D graphics, DataFrame dataFrame) {
    super();
    if (graphics == null) {
      throw new NullPointerException("The Canvas' Graphic component cannot be "
          + "unassigned");
    }
    if (dataFrame == null) {
      throw new NullPointerException("The Canvas' DaatFrame component cannot be "
          + "unassigned");
    }
    this.graphics = graphics;
    this.dataFrame = dataFrame;
    Rectangle rect = dataFrame.getView();
    this.size = new CanvasSize(rect.getWidth(), rect.getHeight());
    this.projection = Projections.getGeographic().getWorld().getWGS1984();
  }

  /**
   * Public Constructor
   *
   * @param graphics
   * @param dataFrame The data frame to use.
   * @param the ProjectionInfo to use.
   */
  public MapCanvas(Graphics2D graphics, DataFrame dataFrame, ProjectionInfo proj) {
    super();
    if (graphics == null) {
      throw new NullPointerException("The Canvas' Graphic component cannot be "
          + "unassigned");
    }
    if (dataFrame == null) {
      throw new NullPointerException("The Canvas' DaatFrame component cannot be "
          + "unassigned");
    }
    this.graphics = graphics;
    this.dataFrame = dataFrame;
    Rectangle rect = dataFrame.getView();
    this.projection = proj;
    this.size = new CanvasSize(rect.getWidth(), rect.getHeight());
  }

  // </editor-fold>
  // <editor-fold defaultstate="collapsed" desc="Private Methods">
  /**
   * Called to lazy initiate the MapToView translator
   *
   * @return the cached MapToView translator
   */
  private MapToView getMapToView() {
    Envelope envelope = null;
    Rectangle rectangle = null;
    if ((this.mapToView == null)
        && ((envelope = this.dataFrame.getEnvelopeMercator()) != null)
        && ((rectangle = this.dataFrame.getView()) != null)) {

      float dX = (float) (rectangle.width / envelope.getWidth());
      float dY = (float) (rectangle.height / envelope.getHeight());
      this.mapToView = new MapToView(rectangle.getLocation(), (float) envelope.getMin().getX(),
          (float) envelope.getMax().getY(),
          dX, dY);
    }
    return this.mapToView;
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Public Properties">
  /**
   * Called to change the Chart's CanvasSize - only if the canvas'
   * Pixels-per-Inch ration changed. If the new <tt>canvasSize</tt> = null, it
   * will use the default CanvasSize with Pixels-per-Inch=72.
   *
   * @param size the new canvas.
   */
  public final void setCanvasSize(CanvasSize size) {
    this.size = (size == null) ? new CanvasSize() : size;
  }

  /**
   * Get the Chart's Canvas Size. Use this method for resizing the canvas.
   *
   * @return the assigned value
   */
  public CanvasSize getCanvasSize() {
    return this.size;
  }

  /**
   * Get he Font-size based on the Canvas ppi
   *
   * @param points the font size in points
   * @return the font size in canvas pixels
   */
  public final int getFontSize(float points) {
    return size.getFontSize(points);
  }

  /**
   * Get he Stroke-size based on the Canvas ppi
   *
   * @param points the font size in points
   * @return the font size in canvas pixels
   */
  public final float getStrokeSize(float points) {
    return size.getStrokeSize(points);
  }

  /**
   * Get the currently assigned scale, or if unassigned the default scale is
   * initiated based on the this.mapToView translator dX and dY settings.
   *
   * @return the assigned scale or CanvasScale(1.0d) is this.mapToView = null
   */
  public CanvasScale getScale() {
    CanvasScale result = null;
    if (this.scale == null) {
      MapToView translator = this.getMapToView();
      if (translator != null) {
        this.scale = new CanvasScale(translator.dX, translator.dY);
      }
    }
    return (this.scale == null ? new CanvasScale(1.0d) : scale);
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Public Scaling Methods">
  /**
   * Scale the X value using this.scale
   *
   * @param x the value to scale
   * @return the scaled value or <tt>x</tt> unchanged if x=NaN or this.scale =
   * Null|Void.
   */
  public double scaleX(double x) {
    double result = x;
    CanvasScale curScale = null;
    if ((!Double.isNaN(x)) && ((curScale = this.getScale()) != null)) {
      result = (x * curScale.getXScale());
    }
    return result;
  }

  /**
   * Scale the Y value using this.scale
   *
   * @param y the value to scale
   * @return the scaled value or <tt>y</tt> unchanged if y=NaN or this.scale =
   * Null|Void.
   */
  public double scaleY(double y) {
    double result = y;
    CanvasScale curScale = null;
    if ((!Double.isNaN(y)) && ((curScale = this.getScale()) != null)) {
      result = (y * curScale.getYScale());
    }
    return result;
  }

  /**
   * Scale the X value using this.scale
   *
   * @param x the value to scale
   * @return the scaled value or <tt>x</tt> unchanged if x=NaN or this.scale =
   * Null|Void.
   */
  public int scaleX(int x) {
    int result = x;
    CanvasScale curScale = null;
    if ((!Double.isNaN(x)) && ((curScale = this.getScale()) != null)) {
      result = (int) (x * curScale.getXScale());
    }
    return result;
  }

  /**
   * Scale the Y value using this.scale
   *
   * @param y the value to scale
   * @return the scaled value or <tt>y</tt> unchanged if y=NaN or this.scale =
   * Null|Void.
   */
  public int scaleY(int y) {
    int result = y;
    CanvasScale curScale = null;
    if ((!Double.isNaN(y)) && ((curScale = this.getScale()) != null)) {
      result = (int) (y * curScale.getYScale());
    }
    return result;
  }

  /**
   * Gets the pixel Point location if the coordinate <tt>coord</tt> were
   * projected from the geographic extent to the MapView rectangle.
   *
   * @param coord the coordinate to project in the MapView
   * @return the projected mapView point
   */
  public CanvasPoint projToPixel(Coord coord) {
    CanvasPoint result = new CanvasPoint();
    MapToView trans = this.getMapToView();
    result.setPoint((((float) trans.origin.x)
        + ((((float) coord.getX()) - trans.minX) * trans.dX)),
        (((float) trans.origin.y)
        + ((trans.maxY - ((float) coord.getY())) * trans.dY)));
    return result;
  }

  /**
   * Takes an integer pixel distance and calculates what it would be in
   * projected world coordinates.
   *
   * @param points the double distance in font points.
   * @return The distance projected in world coordinates.
   */
  public final double projectedLength(int points) {
    Point start = new Point(0, 0);
    double pixels = fontPointToPixel(points);
    Point end = new Point((int) pixels, 0);
    Coord startMerc = this.dataFrame.getProjector().getCoordinate(start);
    Coord endMerc = this.dataFrame.getProjector().getCoordinate(end);
    Coord startWgs84 = Mercator.fromMerc(startMerc);
    Coord endWgs84 = Mercator.fromMerc(endMerc);
    if (!projection.equals(WGS84)) {
      try {
        Coord startProj = Reproject.reprojectCoordinate(startWgs84,
            WGS84, projection);
        Coord endProj = Reproject.reprojectCoordinate(endWgs84,
            WGS84, projection);
        return endProj.getX() - startProj.getX();
      } catch (ProjectionException ex) {
        Logger.getLogger(MapCanvas.class.getName()).log(
                Level.SEVERE, ex.getMessage(), ex);
      }
    }
    return endWgs84.getX() - startWgs84.getX();
  }

  /**
   * Takes an integer pixel distance and calculates what it would be in
   * projected world coordinates.
   *
   * @param pixels the double distance in font points.
   * @return The distance projected in world coordinates.
   */
  public final double projectedLengthFromPixels(int pixels) {
    Point start = new Point(0, 0);
    Point end = new Point(pixels, 0);
    Coord startMerc = this.dataFrame.getProjector().getCoordinate(start);
    Coord endMerc = this.dataFrame.getProjector().getCoordinate(end);
    Coord startWgs84 = Mercator.fromMerc(startMerc);
    Coord endWgs84 = Mercator.fromMerc(endMerc);
    if (!projection.equals(WGS84)) {
      try {
        Coord startProj = Reproject.reprojectCoordinate(startWgs84,
            WGS84, projection);
        Coord endProj = Reproject.reprojectCoordinate(endWgs84,
            WGS84, projection);
        return endProj.getX() - startProj.getX();
      } catch (ProjectionException ex) {
        Logger.getLogger(MapCanvas.class.getName()).log(
                Level.SEVERE, ex.getMessage(), ex);
      }
    }
    return endWgs84.getX() - startWgs84.getX();
  }

  /**
   * Converts a value in font points to a value in pixels, based on the current
   * canvas size.
   *
   * @param points The measurement in points.
   * @return The double measurement in pixels.
   */
  public double fontPointToPixel(double points) {
    return size.getPixelsPerInch() * points / POINTS_PER_INCH;
  }

  /**
   * Gets the length of a segment in pixels - scaled to the map coordinates.
   *
   * @param segment the coordinate to project in the MapView.
   * @return the projected mapView point
   */
  public double getSegmentLengthInPixel(Segment segment) {
    double result = 0.0d;
    Coord p1 = null;
    Coord p2 = null;
    MapToView translator = null;
    if ((segment != null)
        && ((p1 = segment.getP1()) != null)
        && (!Double.isNaN(p1.getX())) && (!Double.isNaN(p1.getY()))
        && ((p2 = segment.getP2()) != null)
        && (!Double.isNaN(p2.getX())) && (!Double.isNaN(p2.getY()))
        && ((translator = this.getMapToView()) != null)) {

      double dx = (p2.getX() - p1.getX()) * translator.dX;
      double dy = (p2.getY() - p1.getY()) * translator.dY;
      result = Math.sqrt((dx * dx) + (dy * dy));
    }
    return result;
  }


  // </editor-fold>
  // <editor-fold defaultstate="collapsed" desc="Object Overrides">
  /**
   * {@inheritDoc}
   * <p>
   * OVERRIDE: </p>
   */
  @Override
  public String toString() {
    return "Canvas[" + this.size.toString() + "; " + this.scale.toString() + "].";
  }
  // </editor-fold>
}
