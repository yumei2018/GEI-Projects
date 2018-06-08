/*
 * Property of the California Department of Water Resources
 * Daily Full Natural Flow Viewer - 2013
 */
package gov.ca.water.shapelite.map.maptool;

import gov.ca.water.shapelite.events.MapEventMouse;
import java.util.EventListener;

/**
 * This event fires on the {@linkplain MapToolSelect MapToolSelect} class when a
 * region has been selected, but before any of the actual selection happens.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public interface MapToolSelectionListener extends EventListener {

  /**
   * This event is fired immediately before the selection tool selects content.
   *
   * @param e
   */
  void beforeSelect(MapEventMouse e);

  /**
   * This event is fired immediately after the selection tool selects content.
   *
   * @param e
   */
  void afterSelect(MapEventMouse e);
}
