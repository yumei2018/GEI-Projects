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

import gov.ca.water.shapelite.events.MapEventMouse;
import gov.ca.water.shapelite.data.Projector;
import gov.ca.water.shapelite.events.PointMarkerEvent;
import gov.ca.water.shapelite.data.dataset.PointDataset;
import gov.ca.water.shapelite.symbology.PointSymbolizer;
import gov.ca.water.shapelite.data.marker.PointMarker;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.event.EventListenerList;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.NonNull;
import gov.ca.water.shapelite.ShapeCategory;
import gov.ca.water.shapelite.data.marker.PolygonMarker;
import gov.ca.water.shapelite.map.Mercator;
import gov.ca.water.shapelite.map.PaintArgs;
import gov.ca.water.shapelite.map.rendering.DefaultPointRenderer;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import gov.ca.water.shapelite.map.rendering.PointRenderer;

/**
 * This is the in-ram version that loads all the markers from the disk to draw
 * them.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PointLayer extends FeatureLayer<PointDataset, PointMarker, PointSymbolizer> {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The list of event listeners that respond to the markers selected events.
   */
  private final EventListenerList listeners;

  /**
   * The object that takes on the responsibility for drawing an individual
   * marker. This allows for the option of re-using the drawing code for symbol
   * previews or other purposes outside the map, or for changing the rendering
   * code without changing other code.
   */
  private PointRenderer renderer;
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Creates a new instance of the MapLayerMaker class which will have an empty
   * dataset with no markers yet added. The dataset can be updated in the
   * future. If the dataset is updated, the MapContent should be repainted.
   */
  public PointLayer() {
    super();
    this.setDefaultSymbolizer(new PointSymbolizer());
    this.listeners = new EventListenerList();
    this.setDataset(new PointDataset());
    renderer = new DefaultPointRenderer();
  }

  /**
   * Creates a new instance of the MapLayerMarker class, containing a new
   * dataset that has the specified list of markers.
   *
   * @param markers The list of Markers to add to this map layer.
   */
  public PointLayer(List<PointMarker> markers) {
    super();
    this.setDefaultSymbolizer(new PointSymbolizer());
    this.listeners = new EventListenerList();
    PointDataset ds = new PointDataset(markers);
    this.setDataset(ds);
    renderer = new DefaultPointRenderer();
  }

  /**
   * Creates a new instance of the MapLayerMarker class using the specified
   * dataset to specify what markers are on this layer.
   *
   * @param ds The DatasetMarker to host on this layer.
   */
  public PointLayer(PointDataset ds) {
    super();
    this.setDefaultSymbolizer(new PointSymbolizer());
    this.listeners = new EventListenerList();
    this.setDataset(ds);
    renderer = new DefaultPointRenderer();
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Events">
  /**
   * Adds the PointMarkerEvent.Listener to the list of listeners that are
   * notified when the marker selection is updated. Duplicates will not be
   * added.
   *
   * @param listener The listener to add.
   */
  public final void addMarkerSelectListener(PointMarkerEvent.Listener listener) {

    PointMarkerEvent.Listener[] array = listeners.getListeners(
        PointMarkerEvent.Listener.class);
    List<PointMarkerEvent.Listener> list = Arrays.asList(array);
    if (!list.contains(listener)) {
      this.listeners.add(PointMarkerEvent.Listener.class, listener);
    }
  }

  /**
   * Removes the specified PointMarkerEvent.Listener from the list of listeners
   * that are notified when the marker selection is updated.
   *
   * @param listener The listener to remove.
   */
  public final void removeMarkerSelectListener(PointMarkerEvent.Listener listener) {
    this.listeners.remove(PointMarkerEvent.Listener.class, listener);
  }

  /**
   * Fires the specified MarkersSelected event.
   *
   * @param event The PointMarkerEvent listing the affected markers.
   */
  public final void fireMarkersSelected(PointMarkerEvent event) {
    PointMarkerEvent.Listener[] list = listeners.getListeners(
        PointMarkerEvent.Listener.class);
    for (PointMarkerEvent.Listener listener : list) {
      listener.markerSelectionChanged(event);
    }
  }

  /**
   * Fires the event for when a specific marker is clicked on this layer.
   * @param event The PointMarkerEvent object.
   */
  public final void fireMarkersClicked(PointMarkerEvent event) {
    PointMarkerEvent.Listener[] list = listeners.getListeners(
        PointMarkerEvent.Listener.class);
    for (PointMarkerEvent.Listener listener : list) {
      listener.markerClicked(event);
    }
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Gets an <code>ArrayList&lt;PointMarker&gt;</code> that stores all of the
   * markers that have any portion of their drawn symbol intersecting with the
   * specified test point. This might be an empty list, but won't be null.
   *
   * @param test The <code>java.awt.Point</code> in map client pixel coordinates
   * to test.
   * @param proj The Projector that translates from pixel coordinates to
   * geographic coordinates.
   * @return The <code>ArrayList&lt;PointMarker&gt;</code> of all the
   * intersecting markers.
   */
  public final List<PointMarker> getIntersectingMarkers(Point test, Projector proj) {
    List<PointMarker> result = new ArrayList<>();
    for (PointMarker m : this.getDataset().getMarkers()) {
      if (intersects(m, test, proj)) {
        result.add(m);
      }
    }
    return result;
  }

  /**
   * Gets an <code>ArrayList&lt;PointMarker&gt;</code> that stores all of the
   * markers that have any portion of their drawn symbol intersecting with the
   * specified test point. This might be an empty list, but won't be null.
   *
   * @param test The <code>java.awt.Rectanglet</code> in map client pixel coordinates
   * to test.
   * @param proj The Projector that translates from pixel coordinates to
   * geographic coordinates.
   * @return The <code>ArrayList&lt;PointMarker&gt;</code> of all the
   * intersecting markers.
   */
  public final List<PointMarker> getIntersectingMarkers(Rectangle test, Projector proj) {
    List<PointMarker> result = new ArrayList<>();
    for (PointMarker m : this.getDataset().getMarkers()) {
      if (intersects(m, test, proj)) {
        result.add(m);
      }
    }
    return result;
  }

  /**
   * This is a test for intersection between a marker and a test point. This is
   * useful because it takes into account the symbolizer and the size of the
   * symbol as it is currently being drawn on the screen.
   *
   * @param marker The marker to compare with the test point
   * @param test The <code>java.awt.Point</code> representing the position on
   * the map.
   * @param proj The Projector from the map content.
   * @return Boolean, true if the test point is within the symbol bounds of the
   * marker.
   */
  public final boolean intersects(PointMarker marker, Point test, Projector proj) {
    PointSymbolizer s = getDefaultSymbolizer();
    if (marker.getSymbolizer() != null) {
      s = marker.getSymbolizer();
    }
    // Temporarily we will cheat and simply use the easier case for a circle.
    // Since eventually markers might be any shape, we might just use a rectangle.
    // But for now, the circle hit testing should match the actual circles.
    int w = s.getWidth();
    int h = s.getHeight();
    if (proj.getDataFrame().getView().isEmpty()) {
      return false;
    }
    Rectangle rect = new Rectangle(test.x - w / 2, test.y - h / 2, w, h);
    Envelope bounds = proj.getEnvelope(rect);
    if (bounds.isEmpty()) {
      return false;
    }
    Envelope env = Mercator.fromMerc(bounds);
    double dx = env.getCenter().getX() - marker.getCoordinate().getX();
    double dy = env.getCenter().getY() - marker.getCoordinate().getY();
    double d = Math.sqrt(dx * dx + dy * dy);
    return d < env.getWidth() / 2;
  }

  /**
   * This is a test for intersection between a marker and a test point. This is
   * useful because it takes into account the symbolizer and the size of the
   * symbol as it is currently being drawn on the screen.
   *
   * @param marker The marker to compare with the test point
   * @param test The <code>java.awt.Point</code> representing the position on
   * the map.
   * @param proj The Projector from the map content.
   * @return Boolean, true if the test point is within the symbol bounds of the
   * marker.
   */
  public final boolean intersects(PointMarker marker, Rectangle test, Projector proj) {
    PointSymbolizer s = getDefaultSymbolizer();
    if (marker.getSymbolizer() != null) {
      s = marker.getSymbolizer();
    }
    // Temporarily we will cheat and simply use the easier case for a circle.
    // Since eventually markers might be any shape, we might just use a rectangle.
    // But for now, the circle hit testing should match the actual circles.
    int w = s.getWidth();
    int h = s.getHeight();
    if (proj.getDataFrame().getView().isEmpty()) {
      return false;
    }
    Rectangle rect = new Rectangle(test.x - w / 2, test.y - h / 2, test.width + w, test.height + h);
    Envelope bounds = proj.getEnvelope(rect);
    if (bounds.isEmpty()) {
      return false;
    }
    Envelope env = Mercator.fromMerc(bounds);
    double dx = env.getCenter().getX() - marker.getCoordinate().getX();
    double dy = env.getCenter().getY() - marker.getCoordinate().getY();
    double d = Math.sqrt(dx * dx + dy * dy);
    return d < env.getWidth() / 2;
  }

  /**
   * For all of the markers within the selected envelope, this will attempt to
   * reverse their selected state.
   *
   * @param e The MapEventMouse that contains information about the map control
   * and the mouse arguments.
   * @param env The envelope to invert.
   * @return boolean, true if any marker selection was changed.
   */
  @Override
  public boolean invertSelection(MapEventMouse e, Envelope env) {
    List<PointMarker> markers = this.getDataset().getMarkers();
    Rectangle region = e.getMap().getContent().getProjector().getRectangle(env);
    region.grow(this.getDefaultSymbolizer().getWidth() / 2, this.getDefaultSymbolizer().getHeight() / 2);

    // Expand the envelope so that even if the envelope only intersects a piece
    // of the marker, we also affect that marker.
    Envelope expanded = e.getMap().getContent().getProjector().getEnvelope(region);
    Envelope geoExt = Mercator.fromMerc(expanded);
    List<PointMarker> result = new ArrayList<>();

    // Cycle through the selected markers in order to invert their selected state.
    for (PointMarker marker : markers) {
      if (geoExt.intersects(marker.getCoordinate())) {
        marker.setSelected(!marker.isSelected());
        result.add(marker);
      }
    }
    if (!result.isEmpty()) {
      PointMarkerEvent event = new PointMarkerEvent(this, result);
      fireMarkersSelected(event);
    }
    return (!result.isEmpty());
  }

  /**
   * Draws each of the markers in the dataset, so long as they appear within the
   * bounds of the current view. The PointMarker coordinates are assumed to be
   * in lat, long, so this also is responsible for translating the markers to
   * the Mercator projection first.
   *
   * @param args
   */
  @Override
  public void paint(PaintArgs args) {
    // Work with a copy of the list so we know it won't be edited while we are rendering.
    List<PointMarker> markers = new ArrayList<>(this.getDataset().getMarkers());
    Rectangle view = args.getFrame().getView();
    PointSymbolizer layerSymbol = this.getDefaultSymbolizer();
    Graphics2D g = args.getGraphics();
    Projector proj = new Projector(args.getFrame());

    // Cycle through each marker in the list and draw it.
    for (PointMarker marker : markers) {
      Coord c = marker.getCoordinate();
      if (c == null) {
        continue;
      }
      if (c.getX() < -180 || c.getX() > 180
          || c.getY() < -90 || c.getY() > 90) {
        continue; // The coordinate is not valid as latitude longitude.
      }
      Coord p = Mercator.toMerc(marker.getCoordinate());
      Point center = proj.getPoint(p);
      PointSymbolizer symbolizer = marker.getSymbolizer();
      if (symbolizer == null) {
        symbolizer = layerSymbol;
      }
      if (symbolizer.getOffset() != null) {
        center.x += symbolizer.getOffset().x;
        center.y += symbolizer.getOffset().y;
      }

      // A rectangle containing the marker
      Rectangle box = new Rectangle(center.x - symbolizer.getWidth() / 2, center.y
          - symbolizer.getHeight() / 2, symbolizer.getWidth(), symbolizer.getHeight());

      // Draw the marker if any part of the rectangle is in the view
      if (view.intersects(box)) {
        renderer.paint(g, center, marker, symbolizer);
      }
    }
  }

  /**
   * Selects all the markers that intersect with the current envelope, even if
   * the geographic center of the marker is outside the envelope.
   *
   * @param e The MapEventMouse argument that contains information about the
   * map.
   * @param env
   * @return true if any marker selection was changed.
   */
  @Override
  public boolean select(MapEventMouse e, Envelope env) {
    boolean changed = false; // tracks if this action modifies any markers
    List<PointMarker> markers = this.getDataset().getMarkers();

    // Define a pixel rectangle for the specified geographic (Mercator) envelope.
    Rectangle region = e.getMap().getContent().getProjector().getRectangle(env);

    // Expand the pixel rectangle by half of the default symbolizer size.  That
    // way, we ensure the rectangle contains any markers that would intersect the region.
    region.grow(this.getDefaultSymbolizer().getWidth() / 2, this.getDefaultSymbolizer().getHeight() / 2);

    // Then turn the expanded envelope back into a geographic (Mercator) extent for selection.
    Envelope expanded = e.getMap().getContent().getProjector().getEnvelope(region);

    // Then convert the Mercator extent into a WGS84 latitude, longitude extent.
    // This works because the distortions to Y are independent of X and X is not
    // distorted.  Therefore, a rectangle in Mercator will always be a rectangle in
    // latitude, longitude.
    Envelope geoExt = Mercator.fromMerc(expanded);

    // Store the markers that are modified in a list for the selected event.
    List<PointMarker> result = new ArrayList<>();
    for (PointMarker marker : markers) {
      if (marker.isSelected()) {
        changed = true;
        marker.setSelected(false);
        result.add(marker);
      }
      if (geoExt.intersects(marker.getCoordinate())) {
        changed = true;
        marker.setSelected(true);
        result.add(marker);
      }
    }
    if (changed) {
      PointMarkerEvent event = new PointMarkerEvent(this, result);
      fireMarkersSelected(event);
    }
    return changed;
  }
  //</editor-fold>

  /**
   * Gets the object that takes on the responsibility for drawing an individual
   * marker. This allows for the option of re-using the drawing code for symbol
   * previews or other purposes outside the map, or for changing the rendering
   * code without changing other code.
   *
   * @return the renderer
   */
  public final PointRenderer getRenderer() {
    return renderer;
  }

  /**
   * Sets the object that takes on the responsibility for drawing an individual
   * marker. This allows for the option of re-using the drawing code for symbol
   * previews or other purposes outside the map, or for changing the rendering
   * code without changing other code.
   *
   * @param renderer the renderer to set
   */
  public final void setRenderer(@NonNull PointRenderer renderer) {
    if (renderer == null) {
      throw new IllegalArgumentException("Argument renderer cannot be null.");
    }
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
    if (featureSet.getFeatureCategory() != ShapeCategory.Point) {
      throw new IllegalArgumentException("The ShapeCategory of featureSet"
          + " must be Point.");
    }
    for (Feature feature : featureSet.getFeatures()) {
      String name = null;
      if (feature.getAttributes().containsKey(nameField)) {
        name = feature.getAttributes().get(nameField);
      }
      PointMarker marker = new PointMarker(feature, name);
      this.getDataset().getMarkers().add(marker);
    }
  }

}
