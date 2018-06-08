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
import gov.ca.water.shapelite.events.PointMarkerEvent;
import gov.ca.water.shapelite.data.DataSourceMarkerOracle;
import gov.ca.water.shapelite.data.dataset.PointDataset;
import gov.ca.water.shapelite.symbology.PointSymbolizer;
import gov.ca.water.shapelite.data.marker.PointMarker;
import java.awt.BasicStroke;
import gov.ca.water.shapelite.data.Projector;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.map.Mercator;
import gov.ca.water.shapelite.map.PaintArgs;

/**
 * This is the in-ram version that loads all the markers from the disk to draw
 * them. For future consideration, there is some redundancy in the rendering
 * between this layer and MapLayerMarker. Only selection is different. It may be
 * possible to refactor a shared marker renderer instead.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class OraclePointLayer extends DataSourceLayer<DataSourceMarkerOracle, PointSymbolizer> {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * A list of envelopes that represent currently selected regions. Since the
   * dataset is controlled by a data source that we have no control over, rather
   * than storing the selection as a property of each point that might or might
   * not be available from the dataset, we instead preserve the geographic
   * regions that have been selected. If a point falls within one of the
   * selected regions, we draw it as a selected point.
   */
  private List<Envelope> selectedRegions;

  //</editor-fold>
  /**
   * Creates a new instance of the MapLayerMarkerOracle class.
   *
   * @param source The data source to use for this layer.
   */
  public OraclePointLayer(DataSourceMarkerOracle source) {
    super(source);
    this.setDefaultSymbolizer(new PointSymbolizer());
    selectedRegions = new ArrayList<>();
  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * This is where rendering is controlled. This will cycle through the markers
   * and
   *
   * @param args
   */
  @Override
  public void paint(PaintArgs args) {

    // antialiasing enables smooth curves by modifying pixel colors.
    args.getGraphics().setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);

    // Determine the an extent in latitude-longitude coordinates like WGS84.
    Envelope wgsExtent = Mercator.fromMerc(args.getFrame().getEnvelopeMercator());

    // The list returned here is not cached anywhere, so we don't need to worry
    // about the list being altered as we cycle through the list.  It should only
    // contain points that are in the current view.
    Optional<PointDataset> ds = this.getDataSource().getDataset(wgsExtent);
    if (!ds.isPresent()) {
      return;
    }
    List<PointMarker> markers = ds.get().getMarkers();

    // define the drawing variables that are used by all markers.
    Rectangle view = args.getFrame().getView();
    PointSymbolizer layerSymbol = this.getDefaultSymbolizer();
    Graphics2D g = args.getGraphics();
    Projector proj = new Projector(args.getFrame());

    // cycle through the markers in the current view.
    for (PointMarker marker : markers) {

      // Translate the points to the Mercator projection.
      Coord p = Mercator.toMerc(marker.getCoordinate());

      // Use the projector to find the pixel location of the center of the marker.
      Point center = proj.getPoint(p);

      // For Oracle markers, this is always null.
      PointSymbolizer m = marker.getSymbolizer();
      if (m == null) {
        m = layerSymbol;
      }

      // Find the rectangular box surrounding the marker.
      Rectangle box = new Rectangle(center.x - m.getWidth() / 2, center.y
          - m.getHeight() / 2, m.getWidth(), m.getHeight());

      // This is a formality since all the points should intersect.
      if (view.intersects(box)) {

        boolean selected = isSelected(marker.getCoordinate());
        if (selected && m.getSelectedIcon() != null) {
          g.drawImage(m.getSelectedIcon(), box.x, box.y, box.width, box.height, null);
        } else if (selected && m.getIcon() != null) {
          g.drawImage(m.getIcon(), box.x, box.y, box.width, box.height, null);
          g.setColor(m.getSelectedBorderColor());
          g.setStroke(new BasicStroke(3));
          g.drawRect(box.x, box.y, box.width, box.height);
        } else if (m.getIcon() != null) {
          g.drawImage(m.getIcon(), box.x, box.y, box.width, box.height, null);
        } else if (m.getShape() != null) {
          // If the icon is null, but the shape is defined, draw the shape instead.

          // move the shape to the position of the marker.
          AffineTransform original = g.getTransform();
          g.translate(center.x, center.y);

          // Antialiasing for smooth curves, not pixelated curves.
          g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
              RenderingHints.VALUE_ANTIALIAS_ON);

          // Fill the shape first, so the border is not hidden by the fill.
          g.setColor(m.getFillColor());
          g.fill(m.getShape());

          // Highlight selected points in a thick cyan border.
          if (selected) {
            g.setStroke(new BasicStroke(3));
            g.setColor(m.getSelectedBorderColor());
          } else {
            g.setStroke(new BasicStroke(1));
            g.setColor(m.getBorderColor());
          }

          g.draw(m.getShape());

          // Reset the transform
          g.setTransform(original);
        } else {
          // The default circle shape

          // Antialiasing for smooth curves, not pixelated curves.
          g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
              RenderingHints.VALUE_ANTIALIAS_ON);

          // Fill the shape first so the fill doesn't hide the border
          g.setColor(m.getFillColor());
          g.fillOval(box.x, box.y, box.width, box.height);

          // If the marker is selected, draw a thick border in the selectedBorderColor.
          if (selected) {
            g.setStroke(new BasicStroke(3));
            g.setColor(m.getSelectedBorderColor());
          } else {
            g.setStroke(new BasicStroke(1));
            g.setColor(m.getBorderColor());
          }

          // draw the border to the graphics surface.
          g.drawOval(box.x, box.y, box.width, box.height);
        }
      }
    }
  }

  /**
   * Selects the markers that intersect with the specified Mercator envelope. In
   * the <code>MapLayerMarkerOracle</code> class, selection is tracked as a set
   * of rectangular envelopes. If a marker falls within an odd number of
   * envelopes, it is counted as selected. In this way, selection can be
   * separated from the marker points themselves.
   *
   * @param e A MapEventMouse that has information about the mouse condition and
   * the Map control.
   * @param env An Envelope in the Mercator geographic coordinates to select.
   */
  @Override
  public boolean select(MapEventMouse e, Envelope env) {

    // Dragging a normal selection rectangle first clears any other selection
    getSelectedRegions().clear();

    // First, find the pixel extents for the geographic (Mercator) env.
    Rectangle region = e.getMap().getContent().getProjector().getRectangle(env);

    // Expand the rectangle to include the centers of any markers that touch the rectangle.
    region.grow(this.getDefaultSymbolizer().getWidth() / 2, this.getDefaultSymbolizer().getHeight() / 2);

    // Get the Mercator geographic envelope for the selected rectangle.
    Envelope expanded = e.getMap().getContent().getProjector().getEnvelope(region);

    // Translate from Mercator coordinates to latitude, longitude.
    Envelope geoExtent = Mercator.fromMerc(expanded);

    // Store the latitude/longitude region to test against each time the layer is drawn.
    getSelectedRegions().add(geoExtent);

    // In order to fire the event, we query the marker locations from the database.
    Optional<PointDataset> marks = this.getDataSource().getDataset(geoExtent);

    // Fire an event showing which markers should be modified.
    if (marks.isPresent()) {
      PointMarkerEvent event = new PointMarkerEvent(this, marks.get().getMarkers());
      this.getDataSource().fireMarkersSelected(event);
    }

    return true;
  }

  /**
   * Reverses the the "selected" characteristics for the points in the
   * rectangle. This is done by adding another selection region in the list of
   * regions. If the point is in an even number of regions, it is not selected.
   * This way, selection can be separated from the points, which are not stored
   * in memory.
   *
   * @param e The MapEventMouse argument containing information about the mouse
   * and the Map control.
   * @param env The Geographic envelope just drawn.
   */
  @Override
  public boolean invertSelection(MapEventMouse e, Envelope env) {
    // the only way to get more than one envelope is to invert it.
    // selecting again clears all the regions.  Therefore, if an
    // item is in an odd number, it is selected, if it is in an even
    // number it is unselected.

    // Get the pixel coordinates on the map control for the specified envelope.
    Rectangle region = e.getMap().getContent().getProjector().getRectangle(env);

    // Expand the rectangle to include the centers of any markers it touches.
    region.grow(this.getDefaultSymbolizer().getWidth() / 2, this.getDefaultSymbolizer().getHeight() / 2);

    // Convert the expanded rectangle back into a Mercator geographic envelope.
    Envelope expanded = e.getMap().getContent().getProjector().getEnvelope(region);

    // Convert the Mercator envelope to a lat/long envelope.  This works because
    // the Y axis is distorted the same way across X, so the transform never
    // creates anything other than anohter rectangular envelope.
    Envelope geoExtent = Mercator.fromMerc(expanded);

    // Add the selected extent to the list of regions for future selection testing.
    // A point in an even number of regions is considered to be unselected.
    getSelectedRegions().add(geoExtent);

    // In order to fire the event about markers that have changed, we need
    // to now query for the markers that are within the selection extent.
    Optional<PointDataset> marks = this.getDataSource().getDataset(geoExtent);

    // Fire the event showing the selection has changed.
    if (marks.isPresent()) {
      PointMarkerEvent event = new PointMarkerEvent(this, marks.get().getMarkers());
      this.getDataSource().fireSelectionInverted(event);
    }

    return true;
  }

  /**
   * Gets a boolean that determines if the specified coordinate (in lat/long)
   * falls within an odd number of cached selection regions.
   *
   * @param coord The latitude longitude pair to test.
   * @return Boolean, true if the coordinate should be counted as "selected".
   */
  public boolean isSelected(Coord coord) {
    boolean selected = false;
    for (Envelope env : getSelectedRegions()) {
      if (env.intersects(coord)) {
        selected = !selected;
      }
    }
    return selected;
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * @return the selectedRegions
   */
  protected List<Envelope> getSelectedRegions() {
    return selectedRegions;
  }

  /**
   * @param selectedRegions the selectedRegions to set
   */
  protected void setSelectedRegions(List<Envelope> selectedRegions) {
    this.selectedRegions = selectedRegions;
  }

  //</editor-fold>
}
