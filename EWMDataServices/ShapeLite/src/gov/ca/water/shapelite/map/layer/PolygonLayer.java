/*
 * The MIT License
 *
 * Copyright 2012 hdunsford.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package gov.ca.water.shapelite.map.layer;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.events.MapEventMouse;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.ShapeCategory;
import gov.ca.water.shapelite.data.dataset.PolygonDataset;
import gov.ca.water.shapelite.data.marker.PolygonMarker;
import gov.ca.water.shapelite.events.PolygonMarkerEvent;
import gov.ca.water.shapelite.data.Projector;
import gov.ca.water.shapelite.map.Mercator;
import gov.ca.water.shapelite.map.PaintArgs;
import gov.ca.water.shapelite.map.rendering.DefaultPolygonRenderer;
import gov.ca.water.shapelite.symbology.PolygonSymbolizer;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.event.EventListenerList;
import gov.ca.water.shapelite.map.rendering.PolygonRenderer;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.Projections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is the in-ram version that loads all the markers from the disk to draw
 * them.
 *
 * @author Harold A. Dunsford Jr. Ph.D./kprins
 */
public class PolygonLayer extends
    FeatureLayer<PolygonDataset, PolygonMarker, PolygonSymbolizer> {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The list of event listeners that listen for when the selection state
   * changes.
   */
  private final EventListenerList listeners;

  /**
   * The renderer to use for drawing the content.
   */
  private PolygonRenderer renderer;

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Constructors">
  /**
   * Creates a new layer using a new dataset that is built from the list of
   * MarkerPath objects.
   *
   * @param markers The list of markers to add.
   */
  public PolygonLayer(List<PolygonMarker> markers) {
    super();
    PolygonDataset ds = new PolygonDataset(markers);
    this.setDataset(ds);
    this.setDefaultSymbolizer(new PolygonSymbolizer());
    this.listeners = new EventListenerList();
    renderer = new DefaultPolygonRenderer();
  }

  /**
   * Creates a new MaplayerMarkerPath to render the specified dataset on the
   * map.
   *
   * @param ds The dataset.
   */
  public PolygonLayer(PolygonDataset ds) {
    super();
    this.setDataset(ds);
    this.setDefaultSymbolizer(new PolygonSymbolizer());
    this.listeners = new EventListenerList();
    renderer = new DefaultPolygonRenderer();
  }

  /**
   * Creates a new instance of a MapLayerMakerPath that is empty and has no
   * lines to draw.
   */
  public PolygonLayer() {
    super();
    this.setDataset(new PolygonDataset());
    this.setDefaultSymbolizer(new PolygonSymbolizer());
    this.listeners = new EventListenerList();
    renderer = new DefaultPolygonRenderer();
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Events">
  /**
   * Adds the specified listener so that when a path is selected or unselected,
   * the listener will be updated with the event. Duplicates will not be added.
   *
   * @param listener a new MarkerPolygonSelectListener
   */
  public void addMarkerSelectListener(PolygonMarkerEvent.Listener listener) {
    PolygonMarkerEvent.Listener[] array
        = this.listeners.getListeners(PolygonMarkerEvent.Listener.class);
    List<PolygonMarkerEvent.Listener> list = Arrays.asList(array);
    if (!list.contains(listener)) {
      this.listeners.add(PolygonMarkerEvent.Listener.class, listener);
    }
  }

  /**
   * Removes the specified selection listener from the list of selection
   * listeners.
   *
   * @param listener The PolygonMarkerEvent.Listener to remove.
   */
  public void removeMarkerSelectListener(PolygonMarkerEvent.Listener listener) {
    this.listeners.remove(PolygonMarkerEvent.Listener.class, listener);
  }

  /**
   * Fires an event notifying other classes when a MarkerPath has been selected
   * or unselected by the user.
   *
   * @param event The MarkerPathEvent contains information about which marker
   * paths were selected or unselected.
   */
  public void fireMarkersSelected(PolygonMarkerEvent event) {
    PolygonMarkerEvent.Listener[] list
        = this.listeners.getListeners(PolygonMarkerEvent.Listener.class);
    for (PolygonMarkerEvent.Listener listener : list) {
      listener.markerPolygonSelectionChanged(event);
    }
  }
  //</editor-fold>

  /**
   * Adds all the features from the specified FeatureSet. This also adds any
   * fields contained in fs that are not yet in the dataset.
   *
   * @param fs The FeatureSet that contains the features and fields to add..
   * @param nameField The String name field.
   */
  public final void addFeatures(FeatureSet fs, String nameField) {
    super.getDataset().addMissingFields(fs);
    if (fs.getFeatureCategory() != ShapeCategory.Polygon) {
      throw new IllegalArgumentException("Cannot add shapes of type "
          + fs.getFeatureCategory() + " to a polygon layer.");
    }
    for (Feature f : fs.getFeatures()) {
      PolygonMarker marker = new PolygonMarker(f.copy());
      if (f.getAttributes().containsKey(nameField)) {
        String name = f.getAttributes().get(nameField);
        marker.setPopupText(name);
        marker.setName(name);
      }

      super.getDataset().getMarkers().add(marker);
    }
    super.getDataset().calculateEnvelope();
  }

  /**
   * @param nameField The String name field. This method is responsible for the
   * actual rendering of the lines on the screen.
   *
   * @param args PaintArgs with information about the drawing.
   */
  @Override
  public void paint(PaintArgs args) {
    if (this.getDataset() == null) {
      return;
    }
    List<PolygonMarker> markers = this.getDataset().getMarkers();
    Graphics2D graphics = args.getGraphics();
    Envelope mercBounds = args.getFrame().getEnvelopeMercator();
    Envelope geoBounds = Mercator.fromMerc(mercBounds);
    AffineTransform original = graphics.getTransform();

    Projector proj = new Projector(args.getFrame());

    PolygonSymbolizer defSymbolizer = this.getDefaultSymbolizer();
    for (PolygonMarker marker : markers) {
      PolygonSymbolizer symbolizer = marker.getSymbolizer();
      if (symbolizer == null) {
        symbolizer = defSymbolizer;
      }

      // Skip this line if it is not in the geographic envelope.  The frame
      // is in mercator coordinates so it has to be translated to use the geographic shape.
      if (!geoBounds.intersects2D(marker.getShape().getEnvelope())) {
        continue;
      }
      graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
          RenderingHints.VALUE_ANTIALIAS_ON);
      Optional<GeneralPath> path = marker.getPath(proj);
      if (path.isPresent()) {
        renderer.paint(args.getGraphics(), path.get(), marker, symbolizer, args.getFrame().getView());
      }
    }
    graphics.setTransform(original);
  }

  /**
   * @param queryPoint The point to test for intersection with.
   * @param proj The Projector to help find intersecting markers.
   * @return The <code>ArrayList&lt;MarkerPath&gt;</code> that may be empty but
   * won't be null.
   */
  public List<PolygonMarker> getIntersectingMarkers(Point queryPoint, Projector proj) {
    List<PolygonMarker> result = new ArrayList<>();
    List<PolygonMarker> markers = null;
    PolygonDataset dataset = this.getDataset();
    Coord queryPointCoord = Mercator.fromMerc(proj.getCoordinate(queryPoint));
    if ((dataset != null) && ((markers = dataset.getMarkers()) != null)
        && (!markers.isEmpty())) {
      for (PolygonMarker marker : this.getDataset().getMarkers()) {
        if (enclosesCoord2D(marker, queryPointCoord)) {
          result.add(marker);
        }
      }
    }
    return result;
  }

  /**
   * @param queryPoint The point to test for intersection with.
   * @param proj The Projector to help find intersecting markers.
   * @return The <code>ArrayList&lt;MarkerPath&gt;</code> that may be empty but
   * won't be null.
   */
  public List<PolygonMarker> getIntersectingMarkers(Coord queryPointWGS84) {
    List<PolygonMarker> result = new ArrayList<>();
    List<PolygonMarker> markers = null;
    PolygonDataset dataset = this.getDataset();
    if ((dataset != null) && ((markers = dataset.getMarkers()) != null)
        && (!markers.isEmpty())) {
      for (PolygonMarker marker : this.getDataset().getMarkers()) {
        if (enclosesCoord2D(marker, queryPointWGS84)) {
          result.add(marker);
        }
      }
    }
    return result;
  }

  /**
   * Tests to see if the test point will intersect with this specified marker
   * line. The tolerance is based on the symbol size for the line.
   *
   * @param marker The MarkerPath is a line from the dataset.
   * @param test The test point is a pixel based <code>java.awt.Point</code> to
   * test.
   * @param proj The Projector that translates between screen and pixel
   * coordinates.
   * @return Boolean that is true if the point intersects with the line on the
   * current display screen within a symbol size tolerance.
   */
  public static boolean intersects(PolygonMarker marker, Point test, Projector proj) {
    boolean result = false;
    if ((marker != null) && (test != null)) {
      // Get a specific symbolizer for the particular MarkerPath if one is defined.
      PolygonSymbolizer symbolizer = marker.getSymbolizer();
      // Get the layer symbolizer if there is not a specific symbolizer for the path.
      if (marker.getSymbolizer() != null) {
        symbolizer = marker.getSymbolizer();
      }

      // Get the width of the line in pixels.
      int width = (int) symbolizer.getBorderWidth();

      // Use the line width to construct a tolerance rectangle around the test point.
      Rectangle rect = new Rectangle(test.x - width / 2,
          test.y - width / 2, width, width);

      // Find the geographic (Mercator) coordinates for the point.
      Envelope bounds = proj.getEnvelope(rect);

      // Convert the point from Mercator to latitude, longitude coordinates.
      Envelope env = Mercator.fromMerc(bounds);

      Optional<GeneralPath> path = marker.getPath(proj);
      if (path.isPresent()) {
        // Test for intersection between the linear path and the lat/long test envelope.
        result = path.get().intersects(env.toRectangle2D());
      }
    }
    return result;
  }

  /**
   * Test whether point is enclosed by polygon based on the envelope
   *
   * @param marker The MarkerPath is a line from the dataset.
   * @param test The test point is a pixel based <code>Coord</code> to test. The
   * coordinate must be in WGS84.
   * @return Boolean that is true if the point intersects with the line on the
   * current display screen within a symbol size tolerance.
   */
  public static boolean enclosesCoord2D(PolygonMarker marker, Coord test) {
    Envelope envelope = marker.getFeature().getEnvelope();
    return envelope.containsPoint2D(test);
  }

  /**
   * Test whether point is enclosed by polygon.
   *
   * @param marker The MarkerPath is a line from the dataset.
   * @param test The test point is a pixel based <code>java.awt.Point</code> to
   * test.
   * @param proj The Projector that translates between screen and pixel
   * coordinates.
   * @return Boolean that is true if the point intersects with the line on the
   * current display screen within a symbol size tolerance.
   */
  @Deprecated
  public static boolean encloses(PolygonMarker marker, Point test, Projector proj) {
    Envelope envelope = marker.getFeature().getEnvelope();
    envelope = Mercator.toMerc(envelope);
    return envelope.containsPoint2D(proj.getCoordinate(test));
  }

  /**
   * Selects the marker paths that intersect with the specified Mercator
   * envelope. Each MarkerPath is in memory, so each one tracks whether or not
   * it is selected. This will expand the envelope by half the line width in
   * case the lines are wide.
   *
   * @param e The mapEventMouse arguments that have information about the mouse
   * state and the Map control where selection is taking place.
   * @param env The geographic Envelope (Mercator) to select.
   * @return
   */
  @Override
  public boolean select(MapEventMouse e, Envelope env) {
    boolean changed = false;

    // Get the list of all MarkerPaths currently in the layer.
    List<PolygonMarker> result = new ArrayList<>();
    List<PolygonMarker> markers = null;
    PolygonDataset dataset = this.getDataset();
    if ((dataset != null) && ((markers = dataset.getMarkers()) != null)
        && (!markers.isEmpty())) {
      // Define pixel rectangle on the map based on the specified env (Mercator)
      Rectangle region = e.getMap().getContent().getProjector().getRectangle(env);

      // Expand the rectangle by half of the line width in case the lines are wide.
      region.grow((int) this.getDefaultSymbolizer().getBorderWidth() / 2,
          (int) this.getDefaultSymbolizer().getBorderWidth() / 2);

      // Get the geographic envelope that is the equivalent of the expanded
      // pixel rectangle.
      Envelope expanded = e.getMap().getContent().getProjector().getEnvelope(region);

      // Get the lat, long envelope coordinates from the Mercator map projection.
      // This works because Y is distorted equally for all X in Mercator,
      // and X is not distorted, so a rectangle will always transform to
      // another rectangle.
      Envelope geoExt = Mercator.fromMerc(expanded);

      // Cycle through the paths, and test for intersection against the geoExt envelope.
      for (PolygonMarker marker : markers) {
        Optional<GeneralPath> path = marker.getPath(e.getProj());
        if ((path.isPresent()
            && path.get().intersects(geoExt.toRectangle2D()))) {
          /// If the marker intersects, and it is not selected, select it.
          if (!marker.isSelected()) {
            changed = true;
            marker.setSelected(true);
            result.add(marker);
          }
        } else // Since we effectively "clear" the existing selection, if any markers
        // were selected before, but won't be selected now, then we need to
        // show that these were changed as well in the event.
        {
          if (marker.isSelected()) {
            changed = true;
            marker.setSelected(false);
            result.add(marker);
          }
        }
      }
    }

    // Fire the event if any marker paths have been newly selected or unselected.
    if (changed) {
      PolygonMarkerEvent event = new PolygonMarkerEvent(this, result);
      fireMarkersSelected(event);
    }
    return changed;
  }

  /**
   * Inverts the selection. Since the MarkerPath objects are in memory, we just
   * track the selection property on the marker object itself.
   *
   * @param e A MapEventMouse argument that has information about the Map
   * control and the mouse condition.
   * @param env the Geographic envelope to invert.
   * @return
   */
  @Override
  public final boolean invertSelection(MapEventMouse e, Envelope env) {

    // Gets the list of all the MarkerPaths on the layer.
    List<PolygonMarker> result = new ArrayList<>();
    List<PolygonMarker> markers = null;
    PolygonDataset dataset = this.getDataset();
    if ((dataset != null) && ((markers = dataset.getMarkers()) != null)
        && (!markers.isEmpty())) {

      // Gets a rectangle in pixel coordinates on the screen using the map.
      Rectangle region = e.getMap().getContent().getProjector().getRectangle(env);

      // Expands the region by half the width of the lines, in case the lines are wide.
      region.grow((int) this.getDefaultSymbolizer().getBorderWidth() / 2,
          (int) this.getDefaultSymbolizer().getBorderWidth() / 2);

      // Gets the expanded envelope in geographic coordinates (Mercator).
      Envelope expanded = e.getMap().getContent().getProjector().getEnvelope(region);

      // Gets the extent in latitude, longitude.  This works because Y is distorted equally
      // for all x and x is not distorted, so an unrotated rectangle will always translate
      // to another rectangle.
      Envelope geoExt = Mercator.fromMerc(expanded);

      // Store the paths that are updated in a list for the event.
      for (PolygonMarker marker : markers) {
        Optional<GeneralPath> path = marker.getPath(e.getProj());
        if ((path.isPresent())
            && (path.get().intersects(geoExt.toRectangle2D()))) {
          // Invert the selection and add the result to the event.
          marker.setSelected(!marker.isSelected());
          result.add(marker);
        }
      }
    }

    if (!result.isEmpty()) {
      // Only fire the event if something changed.
      PolygonMarkerEvent event = new PolygonMarkerEvent(this, result);
      fireMarkersSelected(event);
    }
    return (!result.isEmpty());
  }

  // <editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the renderer to use for drawing the content.
   *
   * @return the renderer
   */
  public final PolygonRenderer getRenderer() {
    return renderer;
  }

  /**
   * Sets the renderer to use for drawing the content.
   *
   * @param renderer the renderer to set
   */
  public final void setRenderer(PolygonRenderer renderer) {
    this.renderer = renderer;
  }

  // </editor-fold>
  /**
   * This will update the markers in the dataset based on the specified
   * FeatureSet.
   *
   * @param featureSet The FeatureSet.
   * @param nameField The String name field to use to name each feature.
   */
  @Override
  public final void setFeaturesFrom(FeatureSet featureSet, String nameField) {
    this.getDataset().getMarkers().clear();
    if (featureSet == null || featureSet.getFeatures().isEmpty()
        || featureSet.getFeatureCategory() == ShapeCategory.Null) {
      return;
    }
    if (featureSet.getFeatureCategory() != ShapeCategory.Polygon) {
      throw new IllegalArgumentException("The ShapeCategory of featureSet"
          + " must be Polygon.");
    }
    FeatureSet reproj = featureSet;
    if (featureSet.getProjection() != null && !featureSet.getProjection().isWGS84()) {
      try {
        reproj = featureSet.copy();
        reproj.reproject(Projections.getWGS84());
      } catch (ProjectionException ex) {
        Logger.getLogger(PolygonLayer.class.getName()).log(Level.SEVERE, null, ex);
        reproj = featureSet;
      }
    }
    for (Feature feature : reproj.getFeatures()) {
        String name = null;
        if (feature.getAttributes().containsKey(nameField)) {
          name = feature.getAttributes().get(nameField);
        }
        PolygonMarker marker = new PolygonMarker(feature, name);
        this.getDataset().getMarkers().add(marker);
      }

  }
}
