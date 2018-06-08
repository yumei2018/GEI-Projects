package gov.ca.water.shapelite.map.layer;

import gov.ca.water.shapelite.data.marker.Marker;
import gov.ca.water.shapelite.symbology.Symbolizer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rmarquez
 * @param <T> extends Marker<? extends Symbolizer>
 */
public class MarkerSelectedEventManager<T extends Marker<? extends Symbolizer>> {

  List<MarkerSelectedEvent.Listener<T>> listeners = new ArrayList<>();

  public MarkerSelectedEventManager() {
  }

  public void addListener(MarkerSelectedEvent.Listener<T> listener) {
    this.listeners.add(listener);
  }

  public void removeListener(MarkerSelectedEvent.Listener<T> listener) {
    this.listeners.remove(listener);
  }

  boolean contains(MarkerSelectedEvent.Listener<T> listener) {
    return this.listeners.contains(listener);
  }

  public void triggerSelected(MarkerSelectedEvent<T> e) {
    for (MarkerSelectedEvent.Listener<T> listener : listeners) {
      listener.onMarkerSelected(e);
    }
  }

  public void removeListeners() {
    listeners.clear();
  }

}
