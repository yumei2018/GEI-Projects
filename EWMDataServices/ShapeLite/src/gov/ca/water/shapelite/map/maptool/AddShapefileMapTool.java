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
package gov.ca.water.shapelite.map.maptool;

import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.events.FileDropEvent;
import gov.ca.water.shapelite.events.MapEventComponent;
import gov.ca.water.shapelite.events.MapEventMouse;
import gov.ca.water.shapelite.map.Images;
import gov.ca.water.shapelite.map.MapButtonListener;
import gov.ca.water.shapelite.map.MapPanel;
import gov.ca.water.shapelite.map.element.ButtonBorderedMapElement;
import gov.ca.water.shapelite.utils.FileDropUtils;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;

/**
 * This MapTool simply is a container for the MapElementPanButtons that do the
 * actual work of panning.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class AddShapefileMapTool extends MapTool {

  /**
   * Gets the number of pixels from the right side of the map that the right
   * side of the button should be placed.
   */
  private static final int RIGHT_OFFSET = 50;

  /**
   * Gets the original positioning. This is not really used because it will be
   * quickly re-positioned by the componentResized method.
   */
  private static final Rectangle BOUNDS = new Rectangle(1000, 125, 40, 40);

  /**
   * The MapElement with the button that appears on the map.
   */
  private final ButtonBorderedMapElement button;

  /**
   * True if the user should be able to drag shapefiles onto the map.
   */
  private boolean dragDropEnabled;

  /**
   * A boolean that is true if the drag drop listener was added to the map.
   */
  private boolean dragDropAdded;

  /**
   * The map to add the add shapefile handler to.
   */
  private MapPanel map;

  /**
   * The File Drop handler control.
   */
  private FileDropUtils drop;

  /**
   * creates a new instance of the MapToolPanButtons class.
   */
  public AddShapefileMapTool() {
    button = new ButtonBorderedMapElement();
    button.setBoundsFrom(BOUNDS);
    button.setToolTipText("Add a shapefile.");
    Optional<Image> img = Images.getInstance().get("AddShape32.png");
    if (img.isPresent()) {
      button.setImage(img.get());
    }
    button.addMapButtonListener(new MapButtonListener() {
      @Override
      public void buttonClicked(MapEventMouse e) {
        addShapefile(e);
      }
    }
    );
    button.setEnabled(true);
    super.getElements().add(button);
    super.setEnabled(true);
    dragDropEnabled = true;

  }

  /**
   * Allows the user to browse for a .shp file and add it.
   *
   * @param e The map event mouse.
   */
  public final void addShapefile(MapEventMouse e) {
    e.getMap().getContent().openShapefiles();
  }

  /**
   * Repositions the button button when the component is resized.
   *
   * @param e The MapEventComponent.
   */
  @Override
  public final void componentResized(MapEventComponent e) {
    button.getBounds().x = e.getMap().getWidth() - RIGHT_OFFSET;
  }

  /**
   * When mouse moved
   *
   * @param e
   */
  @Override
  public void mouseMoved(MapEventMouse e) {
    super.mouseMoved(e);
    if (dragDropEnabled && !dragDropAdded) {
      addDragDrop(e.getMap());
      dragDropAdded = true;
    }
  }

  /**
   * Adds the drag and drop behavior to the map.
   *
   * @param mapPanel
   */
  private void addDragDrop(MapPanel mapPanel) {
    map = mapPanel;

    FileDropUtils.addListener(map, new FileDropEvent.Listener() {
      @Override
      public void filesDropped(FileDropEvent e) {
        for (File file : e.getFiles()) {
          if (file.getAbsolutePath().endsWith(".shp")) {
            map.getContent().openShapefiles(file);
          }
        }
      }
    });


  }

  @Override
  protected void onEnabled() {
    if(dragDropEnabled && !dragDropAdded && map != null){
      addDragDrop(map);
    }
    super.onEnabled(); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  protected void onDisabled() {
    if(dragDropAdded){
      FileDropUtils.removeListener(map);
      dragDropAdded = false;
    }
    super.onDisabled(); //To change body of generated methods, choose Tools | Templates.
  }





  /**
   * Gets the MapElement with the button that appears on the map.
   *
   * @return the button
   */
  public final ButtonBorderedMapElement getButton() {
    return button;
  }

  /**
   * @return the dragDropEnabled
   */
  public final boolean isDragDropEnabled() {
    return dragDropEnabled;
  }

  /**
   * @param dragDropEnabled the dragDropEnabled to set
   */
  public final void setDragDropEnabled(boolean dragDropEnabled) {
    this.dragDropEnabled = dragDropEnabled;
  }

}
