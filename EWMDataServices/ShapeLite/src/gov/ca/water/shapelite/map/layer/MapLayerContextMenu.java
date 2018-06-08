/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.ca.water.shapelite.map.layer;

import gov.ca.water.shapelite.events.MapEventMouse;
import java.util.List;
import javax.swing.JMenuItem;

/**
 *
 * @author rmarquez
 */
public class MapLayerContextMenu {

  private final List<JMenuItem> menuItems;
  private final MapEventMouse mapEventMouse;

  public MapLayerContextMenu(MapEventMouse e, List<JMenuItem> menuItems) {
    this.mapEventMouse = e;
    this.menuItems = menuItems;
  }

  public List<JMenuItem> getMenuItems() {
    return menuItems;
  }

  public MapEventMouse getMapEventMouse() {
    return mapEventMouse;
  }

}
