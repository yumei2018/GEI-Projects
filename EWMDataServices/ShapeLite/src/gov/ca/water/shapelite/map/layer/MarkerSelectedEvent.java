package gov.ca.water.shapelite.map.layer;

import gov.ca.water.shapelite.data.marker.Marker;
import gov.ca.water.shapelite.events.MapEventMouse;
import gov.ca.water.shapelite.symbology.Symbolizer;

/**
 *
 * @author rmarquez
 * @param <Tmarker> type extending marker
 */
public class MarkerSelectedEvent<Tmarker extends Marker<? extends Symbolizer>> {

  private Tmarker marker;
  private Object source;
  private final MapEventMouse evt;

  public MarkerSelectedEvent(Object source, MapEventMouse e) {
    this.source = source;
    this.evt = e;
  }

  public MapEventMouse getEvt() {
    return evt;
  }

  public MarkerSelectedEvent<Tmarker> setSelectedMarker(Tmarker marker) {
    this.marker = marker;
    return this;
  }

  public Object getSource() {
    return source;
  }

  public void setSource(Object source) {
    this.source = source;
  }

  public Tmarker getMarker() {
    return marker;
  }

  public static interface Listener<TMarker extends Marker<? extends Symbolizer>> {

    public void onMarkerSelected(MarkerSelectedEvent<TMarker> e);

    public void onMarkerHoveredBefore(MarkerSelectedEvent<TMarker> e);

    public void onMarkerHoveredAfter(MarkerSelectedEvent<TMarker> e);
  }

  public static class DefaultListener<TMarker extends Marker<? extends Symbolizer>> implements Listener<TMarker> {
    @Override
    public void onMarkerSelected(MarkerSelectedEvent<TMarker> e) {
    }

    @Override
    public void onMarkerHoveredBefore(MarkerSelectedEvent<TMarker> e) {
    }

    @Override
    public void onMarkerHoveredAfter(MarkerSelectedEvent<TMarker> e) {
    }
  }
}
