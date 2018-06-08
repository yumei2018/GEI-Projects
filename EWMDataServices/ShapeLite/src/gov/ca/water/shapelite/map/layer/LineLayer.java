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

import com.vividsolutions.jts.geom.Geometry;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.events.MapEventMouse;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.ShapeCategory;
import gov.ca.water.shapelite.data.dataset.LineDataset;
import gov.ca.water.shapelite.data.marker.LineMarker;
import gov.ca.water.shapelite.events.LineMarkerEvent;
import gov.ca.water.shapelite.data.Projector;
import gov.ca.water.shapelite.data.marker.PolygonMarker;
import gov.ca.water.shapelite.map.rendering.DefaultLineRenderer;
import gov.ca.water.shapelite.symbology.LineSymbolizer;
import gov.ca.water.shapelite.projection.ProjectionException;
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
import gov.ca.water.shapelite.map.Mercator;
import gov.ca.water.shapelite.map.PaintArgs;
import gov.ca.water.shapelite.map.rendering.TextRenderer;
import gov.ca.water.shapelite.topology.Adapter;
import java.awt.geom.Path2D;

/**
 * This is the in-ram version that loads all the markers from the disk to draw
 * them.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class LineLayer extends
    FeatureLayer<LineDataset, LineMarker, LineSymbolizer> {

  /**
   * The list of event listeners that listen for when the selection state
   * changes.
   */
  private final EventListenerList listeners;

  /**
   * The renderer that will be used for drawing individual shapes.
   */
  private TextRenderer renderer;

  /**
   * Creates a new layer using a new dataset that is built from the list of
   * MarkerPath objects.
   *
   * @param fs The FeatureSet to add.
   * @param nameField The String field name of the column to use as names for
   * the marker paths.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if there was
   * an error projecting the featureset into map coordinates.
   */
  public LineLayer(FeatureSet fs, String nameField)
      throws ProjectionException {
    LineDataset ds = new LineDataset(fs, nameField);
    this.setDataset(ds);
    this.setDefaultSymbolizer(new LineSymbolizer());
    listeners = new EventListenerList();
    renderer = new DefaultLineRenderer();
    this.setVisible(true);
  }

  /**
   * Creates a new layer using a new dataset that is built from the list of
   * MarkerPath objects.
   *
   * @param markers The list of marker path objects to use to construct a new
   * marker path layer.
   */
  public LineLayer(List<LineMarker> markers) {
    LineDataset ds = new LineDataset(markers);
    this.setDataset(ds);
    this.setDefaultSymbolizer(new LineSymbolizer());
    listeners = new EventListenerList();
    renderer = new DefaultLineRenderer();
    this.setVisible(true);
  }

  /**
   * Creates a new MaplayerMarkerPath to render the specified dataset on the
   * map.
   *
   * @param ds The DatasetMarkerPath to use to build this layer.
   */
  public LineLayer(LineDataset ds) {
    this.setDataset(ds);
    this.setDefaultSymbolizer(new LineSymbolizer());
    listeners = new EventListenerList();
    renderer = new DefaultLineRenderer();
  }

  /**
   * Creates a new instance of a MapLayerMakerPath that is empty and has no
   * lines to draw.
   */
  public LineLayer() {
    this.setDataset(new LineDataset());
    this.setDefaultSymbolizer(new LineSymbolizer());
    listeners = new EventListenerList();
    renderer = new DefaultLineRenderer();
  }

  //<editor-fold defaultstate="collapsed" desc="Events">
  /**
   * Adds the specified listener so that when a path is selected or unselected,
   * the listener will be updated with the event. Duplicates will not be added.
   *
   * @param listener The listener to add.
   */
  public final void addMarkerSelectListener(LineMarkerEvent.Listener listener) {

    LineMarkerEvent.Listener[] array
        = listeners.getListeners(LineMarkerEvent.Listener.class);
    List<LineMarkerEvent.Listener> list = Arrays.asList(array);
    if (!list.contains(listener)) {
      this.listeners.add(LineMarkerEvent.Listener.class, listener);
    }
  }

  /**
   * Removes the specified selection listener from the list of selection
   * listeners.
   *
   * @param listener The MarkerPathSelectListener to remove.
   */
  public final void removeMarkerSelectListener(LineMarkerEvent.Listener listener) {
    this.listeners.remove(LineMarkerEvent.Listener.class, listener);
  }

  /**
   * Fires an event notifying other classes when a MarkerPath has been selected
   * or unselected by the user.
   *
   * @param event The MarkerPathEvent contains information about which marker
   * paths were selected or unselected.
   */
  public final void fireMarkersSelected(LineMarkerEvent event) {
    LineMarkerEvent.Listener[] list
        = listeners.getListeners(LineMarkerEvent.Listener.class);
    for (LineMarkerEvent.Listener listener : list) {
      listener.lineSelectionChanged(event);
    }
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * This method is responsible for the actual rendering of the lines on the
   * screen.
   *
   * @param args PaintArgs with information about the drawing.
   */
  @Override
  public final void paint(PaintArgs args) {
    List<LineMarker> markers = this.getDataset().getMarkers();
    Graphics2D g = args.getGraphics();
    Envelope mercBounds = args.getFrame().getEnvelopeMercator();
    Envelope geoBounds = Mercator.fromMerc(mercBounds);
    AffineTransform original = g.getTransform();

    Projector proj = new Projector(args.getFrame());
    g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
        RenderingHints.VALUE_FRACTIONALMETRICS_ON);
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
    g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

    for (LineMarker marker : markers) {

      LineSymbolizer symbolizer = marker.getSymbolizer();
      if (symbolizer == null) {
        symbolizer = getDefaultSymbolizer();
      }

      // Skip this line if it is not in the geographic envelope.  The frame
      // is in mercator coordinates so it has to be translated to use
      // the geographic shape.
      if (marker.getShape() == null) {
        continue;
      }
      if (!geoBounds.intersects2D(marker.getShape().getEnvelope())) {
        continue;
      }
      Optional<GeneralPath> path = marker.getPath(proj);
      if (!path.isPresent()) {
        continue;
      }
      renderer.paint(g, path.get(), marker, symbolizer);
    }
    g.setTransform(original);
  }

  /**
   * @param test The point to test for intersection with.
   * @param proj The Projector to help find intersecting markers.
   * @return The <code>ArrayList&lt;MarkerPath&gt;</code> that may be empty but
   * won't be null.
   */
  public final List<LineMarker> getIntersectingMarkers(Point test,
      Projector proj) {
    List<LineMarker> result = new ArrayList<>();
    List<LineMarker> markers;
    LineDataset dataset = this.getDataset();

    Coord testCoord = Mercator.fromMerc(proj.getCoordinate(test));

    if (dataset != null) {
      markers = dataset.getMarkers();

      if ((markers != null) && (!markers.isEmpty())) {
        for (LineMarker m : markers) {
          Envelope markerBounds = m.getShape().getEnvelope();
          if (!markerBounds.containsPoint2D(testCoord)) {
            continue;
          }
          if (intersects(m, test, proj)) {
            result.add(m);
          }
        }
      }
    }
    return result;
  }

  /**
   * Tests whether test point intersects with this specified marker
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
  public final boolean intersects(LineMarker marker, Point test, Projector proj) {
    boolean result = false;
    if ((marker != null) && (test != null)) {
      // Get a specific symbolizer for the particular MarkerPath if one is defined.
      LineSymbolizer symbolizer = getDefaultSymbolizer();
      // Get the layer symbolizer if there is not a specific symbolizer for the path.
      if (marker.getSymbolizer() != null) {
        symbolizer = marker.getSymbolizer();
      }

      // Get the width of the line in pixels.
      int w = (int) (2.0*symbolizer.getWidth());

      // Use the line width to construct a tolerance rectangle around the test
      // point.
      Rectangle testRect = new Rectangle(test.x - w / 2, test.y - w / 2, w, w);

      // Find the geographic (Mercator) coordinates for the point.
      Envelope bounds = proj.getEnvelope(testRect);

      // Convert the point from Mercator to latitude, longitude coordinates.
      Envelope env = Mercator.fromMerc(bounds);

      Optional<Path2D> markerPathOptional = marker.getPolyline(proj);

      if (markerPathOptional.isPresent()) {
        // Test for intersection between the linear path and the lat/long test
        // envelope.
        Optional<Geometry> g = Adapter.getGeometry(marker.getShape());
        Geometry p = Adapter.getGeometry(env.getBorder()).get();
        Geometry p2 = g.get();
        result = p2.intersects(p);
      }
    }
    return result;
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
  public final boolean select(MapEventMouse e, Envelope env) {
    boolean changed = false;

    // Get the list of all MarkerPaths currently in the layer.
    List<LineMarker> result = new ArrayList<>();
    List<LineMarker> markers;
    LineDataset dataset = this.getDataset();
    if (dataset != null) {
      markers = dataset.getMarkers();
      if ((markers != null) && (!markers.isEmpty())) {

        // Define pixel rectangle on the map based on the specified env (Mercator)
        Rectangle region = e.getMap().getContent().getProjector().
            getRectangle(env);

        // Expand the rectangle by half of the line width in case the
        // lines are wide.
        region.grow((int) this.getDefaultSymbolizer().getWidth() / 2,
            (int) this.getDefaultSymbolizer().getWidth() / 2);

        // Get the geographic envelope that is the equivalent of the expanded
        // pixel rectangle.
        Envelope expanded = e.getMap().getContent().getProjector().
            getEnvelope(region);

        // Get the lat, long envelope coordinates from the Mercator map
        // projection.  This works because Y is distorted equally for all X
        // in Mercator, and X is not distorted, so a rectangle will always
        // transform to another rectangle.
        Envelope geoExt = Mercator.fromMerc(expanded);

        // Cycle through the paths, and test for intersection against the
        // geoExt envelope.
        for (LineMarker marker : markers) {
          if (marker.getShape() == null) {
            continue;
          }
          if (geoExt.intersects2D(marker.getShape().getEnvelope())) {
            Optional<GeneralPath> path = marker.getPath(e.getProj());
            if (path.isPresent() && path.get().intersects(region)) {
              /// If the marker intersects, and it is not selected, select it.
              if (!marker.isSelected()) {
                changed = true;
                marker.setSelected(true);
                result.add(marker);
              }
            } else // Since we effectively "clear" the existing selection, if any
            // markers were selected before, but won't be selected now, then
            // we need to show that these were changed as well in the event.
            if (marker.isSelected()) {
              changed = true;
              marker.setSelected(false);
              result.add(marker);
            }
          } else if (marker.isSelected()) {
            changed = true;
            marker.setSelected(false);
            result.add(marker);
          }

        }
      }
    }

    // Fire the event if any marker paths have been newly selected or unselected.
    if (changed) {
      LineMarkerEvent event = new LineMarkerEvent(this, result);
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
   * @return boolean, true if the inversion was successful.
   */
  @Override
  public final boolean invertSelection(MapEventMouse e, Envelope env) {

    // Gets the list of all the MarkerPaths on the layer.
    List<LineMarker> result = new ArrayList<>();
    List<LineMarker> markers;
    LineDataset dataset = this.getDataset();
    if (dataset != null) {
      markers = dataset.getMarkers();
      if ((markers != null) && (!markers.isEmpty())) {
        // Gets a rectangle in pixel coordinates on the screen using the map.
        Rectangle region = e.getMap().getContent().getProjector().
            getRectangle(env);

        // Expands the region by half the width of the lines, in case the
        // lines are wide.
        region.grow((int) this.getDefaultSymbolizer().getWidth() / 2,
            (int) this.getDefaultSymbolizer().getWidth() / 2);

        // Gets the expanded envelope in geographic coordinates (Mercator).
        Envelope expanded = e.getMap().getContent().getProjector().
            getEnvelope(region);

        // Gets the extent in latitude, longitude.  This works because Y is
        // distorted equally
        // for all x and x is not distorted, so an unrotated rectangle will
        // always translate to another rectangle.
        Envelope geoExt = Mercator.fromMerc(expanded);

        // Store the paths that are updated in a list for the event.
        for (LineMarker marker : markers) {

          Optional<GeneralPath> path = marker.getPath(e.getProj());
          if ((path.isPresent())
              && (path.get().intersects(geoExt.
                  toRectangle2D()))) {
            // Invert the selection and add the result to the event.
            marker.setSelected(!marker.isSelected());
            result.add(marker);
          }
        }
      }
    }

    if (!result.isEmpty()) {
      // Only fire the event if something changed.
      LineMarkerEvent event = new LineMarkerEvent(this, result);
      fireMarkersSelected(event);
    }
    return ((!result.isEmpty()));
  }
  //</editor-fold>

  /**
   * Gets the renderer that will be used for drawing individual shapes.
   *
   * @return the renderer
   */
  public final TextRenderer getRenderer() {
    return renderer;
  }

  /**
   * Sets the renderer that will be used for drawing individual shapes.
   *
   * @param renderer the renderer to set
   */
  public final void setRenderer(TextRenderer renderer) {
    this.renderer = renderer;
  }

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
    if (featureSet.getFeatureCategory() != ShapeCategory.PolyLine) {
      throw new IllegalArgumentException("The ShapeCategory of featureSet"
          + " must be PolyLine.");
    }
    for (Feature feature : featureSet.getFeatures()) {
      String name = null;
      if (feature.getAttributes().containsKey(nameField)) {
        name = feature.getAttributes().get(nameField);
      }
      LineMarker marker = new LineMarker(feature, name);
      this.getDataset().getMarkers().add(marker);
    }
  }


}
