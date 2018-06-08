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
package gov.ca.water.shapelite.map;

import gov.ca.water.shapelite.map.element.MapElement;
import gov.ca.water.shapelite.map.maptool.ResizeMapTool;
import gov.ca.water.shapelite.map.maptool.PopupMapTool;
import gov.ca.water.shapelite.map.maptool.TileUpdatingMapTool;
import gov.ca.water.shapelite.map.maptool.KeyPanZoomMapTool;
import gov.ca.water.shapelite.map.maptool.PointClickMapTool;
import gov.ca.water.shapelite.map.maptool.PanMapTool;
import gov.ca.water.shapelite.map.maptool.CoordinateMapTool;
import gov.ca.water.shapelite.map.maptool.MapToolbox;
import gov.ca.water.shapelite.map.maptool.LineMapTool;
import gov.ca.water.shapelite.map.maptool.TrackZoomMapTool;
import gov.ca.water.shapelite.map.maptool.DownloadMapTool;
import gov.ca.water.shapelite.map.maptool.ToolTipMapTool;
import gov.ca.water.shapelite.map.maptool.SelectMapTool;
import gov.ca.water.shapelite.map.maptool.ContentUpdaterMapTool;
import gov.ca.water.shapelite.events.MapEventDisplay;
import gov.ca.water.shapelite.events.MapEventDisplayListener;
import gov.ca.water.shapelite.events.MapEventLayers;
import gov.ca.water.shapelite.events.MapEventTiles;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Envelope;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Arrays;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;
import gov.ca.water.shapelite.data.TileDownloader;
import gov.ca.water.shapelite.data.TilesEvent;
import gov.ca.water.shapelite.dialog.DialogHandler;
import gov.ca.water.shapelite.dialog.DefaultDialogHandler;
import gov.ca.water.shapelite.map.element.PanButtonMapElement;
import gov.ca.water.shapelite.map.maptool.MapTool;
import java.awt.Cursor;
import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class MapPanel extends JPanel {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The element like a button or track slider that is rendered within the map, and
   * currently has focus.
   */
  private MapElement activeElement;

  /**
   * The object that contains all the layers to be rendered on the map. This includes tile
   * datasets like the open street map tiles that usually serve as a background layer for
   * data. This object also has a cached image from when the tiles draw themselves.
   */
  private MapContent content;

  /**
   * A boolean that indicates that one of the elements is being actively manipulated, like
   * a track slider, and therefore the mouse should not necessarily move unrestricted on
   * the map.
   */
  private boolean cursorLocked;

  /**
   * This is the event listener list for events related to the content and layers.
   */
  private final EventListenerList displayListeners;

  /**
   * The list of all the tools that respond to mouse events or add extra content or
   * functionality to the map.
   */
  private MapToolbox toolbox;

  /**
   * A boolean that indicates whether the content for this map is currently being redrawn.
   */
  private boolean updatingContent;

  /**
   * A shared popupMenu that can optionally be launched if PopupMapTool is enabled.
   */
  private JPopupMenu popupMenu;

  /**
   * Gets the geographic location where a right click triggered a pop-up.
   */
  private Coord popupLocation;

  /**
   * The interface that provides access to simple dialog functions.
   */
  private DialogHandler dialogHandler;

  //</editor-fold>
  /**
   * Creates a new instance of the MapPanel and initializes all the tools that are by
   * default added to a map control. The tool list can be customized after the map has
   * been created, but the default toolbox should support basic navigation tools.
   */
  public MapPanel() {
    super();
    displayListeners = new EventListenerList();
    
    content = new MapContent();
    content.setView(new Rectangle(0, 0, super.getWidth(), super.getHeight()));
    
    // Create new instances of each type of tool.
    PanMapTool pan = new PanMapTool();
    ResizeMapTool resize = new ResizeMapTool();
    KeyPanZoomMapTool keyboard = new KeyPanZoomMapTool();
    CoordinateMapTool coord = new CoordinateMapTool();
    TileUpdatingMapTool update = new TileUpdatingMapTool();
    SelectMapTool select = new SelectMapTool();
    select.setEnabled(false);
    PointClickMapTool pointClick = new PointClickMapTool();
    pointClick.setEnabled(false);
    DownloadMapTool download = new DownloadMapTool();
    download.setEnabled(false);
    TrackZoomMapTool track = new TrackZoomMapTool();
    PanButtonMapElement buttons = new PanButtonMapElement();
    ContentUpdaterMapTool updater = new ContentUpdaterMapTool();
    ToolTipMapTool tooltip = new ToolTipMapTool();
    LineMapTool line = new LineMapTool();
    line.setEnabled(false);
    PopupMapTool popup = new PopupMapTool();

    // Add each created tool to the toolbox.
    this.toolbox = new MapToolbox();
    this.toolbox.getTools().add(updater);
    this.toolbox.getTools().add(buttons);
    this.toolbox.getTools().add(resize);
    this.toolbox.getTools().add(track);
    this.toolbox.getTools().add(coord);
    this.toolbox.getTools().add(keyboard);
    this.toolbox.getTools().add(update);
    this.toolbox.getTools().add(pointClick);
    this.toolbox.getTools().add(tooltip);
    this.toolbox.getTools().add(select);
    this.toolbox.getTools().add(pan);
    this.toolbox.getTools().add(download);

    this.toolbox.getTools().add(line);
    this.toolbox.getTools().add(popup);

    // make sure the toolbox is listening to the mouse events for this control.
    super.addMouseListener(toolbox);
    super.addMouseMotionListener(toolbox);
    super.addMouseWheelListener(toolbox);
    super.setFocusable(true);
    super.addKeyListener(toolbox);
    super.addComponentListener(toolbox);
    super.addFocusListener(toolbox);
    this.doAddMapEventDisplayListener(toolbox);

    this.popupMenu = new JPopupMenu();
    

    // Listen to the MapContent, Layers and TileDownloader and fire events.
    forwardEvents();

  }

  //<editor-fold defaultstate="collapsed" desc="Events">
  /**
   * This method handles adding listeners to the MapContent, Layers and TileDownloader
   * objects and then re-packages those events with a Map reference.
   */
  private void forwardEvents() {

    TileDownloader.getInstance().addTileDownloadListener(new TileDownloadListener() {
      /**
       * Occurs after a set of tiles have finished downloading.
       */
      @Override
      public void tileDownloaded(TilesEvent e) {
        fireDownloadComplete(new MapEventTiles(MapPanel.this, e));
      }
    });

    content.addMapContentListener(new MapContentListener() {
      /**
       * Occurs when the content is updated.
       */
      @Override
      public void contentChanged(DisplayEvent e) {
        fireContentChanged(new MapEventDisplay(MapPanel.this,
                e.getSource()));
      }

      /**
       * Occurs when rendering is finished on the MapContent object.
       */
      @Override
      public void renderComplete(DisplayEvent e) {
        fireRenderComplete(new MapEventDisplay(MapPanel.this,
                e.getSource()));
      }
    });

    content.getLayers().addListener(new ObservedListListener() {

      @Override
      public void listChanged(ObservedListEvent<?> evt) {
        fireLayersChanged(new MapEventLayers(MapPanel.this,
                evt.getSource(), content.getLayers()));
      }
    });

  }

  /**
   * Allows a new display listener to be added to this object.
   *
   * @param listener The MapEventDisplay listener to add.
   */
  public final void addMapEventDisplayListener(MapEventDisplayListener listener) {
    doAddMapEventDisplayListener(listener);
  }

  /**
   * Adds a listener for the map display.
   *
   * @param listener
   */
  private void doAddMapEventDisplayListener(MapEventDisplayListener listener) {
    MapEventDisplayListener[] listeners = displayListeners.getListeners(MapEventDisplayListener.class);
    if (!Arrays.asList(listeners).contains(listener)) {
      displayListeners.add(MapEventDisplayListener.class, listener);
    }
  }

  /**
   * Removes an existing MapEventDisplay listener
   *
   * @param listener
   */
  public void removeMapEventDisplayListener(MapEventDisplayListener listener) {
    displayListeners.remove(MapEventDisplayListener.class, listener);
  }

  /**
   * Fires the contentChanged event
   *
   * @param args
   */
  public void fireContentChanged(MapEventDisplay args) {
    MapEventDisplayListener[] listeners = displayListeners.getListeners(MapEventDisplayListener.class);
    for (MapEventDisplayListener listener : listeners) {
      listener.contentChanged(args);
    }
  }

  /**
   * Fires the event renderComplete event.
   *
   * @param args
   */
  public void fireRenderComplete(MapEventDisplay args) {
    MapEventDisplayListener[] listeners = displayListeners.getListeners(MapEventDisplayListener.class);
    for (MapEventDisplayListener listener : listeners) {
      listener.renderComplete(args);
    }
  }

  /**
   * Fires the event.
   *
   * @param args
   */
  public void fireLayersChanged(MapEventLayers args) {
    MapEventDisplayListener[] listeners = displayListeners.getListeners(MapEventDisplayListener.class);
    for (MapEventDisplayListener listener : listeners) {
      listener.layersChanged(args);
    }
  }

  /**
   * Fires the event.
   *
   * @param args
   */
  public void fireDownloadComplete(MapEventTiles args) {
    MapEventDisplayListener[] listeners = displayListeners.getListeners(MapEventDisplayListener.class);
    for (MapEventDisplayListener listener : listeners) {
      listener.tilesDownloaded(args);
    }
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * This will set the extents and then fix the extents to the nearest zoom level.
   *
   * @param envelope The geographic envelope in WGS84 coordinates
   */
  public void setEnvelope(Envelope envelope) {
    Envelope merc = Mercator.toMerc(envelope);
    getContent().setEnvelope(merc);
    getContent().fixEnvelope();
    getContent().paintImmediately();
    repaint();
  }

  /**
   * The default paint implementation for the MapPanel is to first draw a data image from
   * the MapContent which has been cached. Then, after this is rendered, each of the tools
   * is invited to paint "content" on the screen first. This would be anything that can be
   * thought of as "markup" for the data, but that should be hidden by overlapping
   * buttons. Finally, all of the tools are instructed to "paint" themselves, which should
   * hide markup content.
   *
   * @param g The Graphics surface to draw on.
   */
  @Override
  public void paintComponent(Graphics g) {
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, this.getWidth(), this.getHeight());
    if (content.getEnvelopeMercator() == null || content.getEnvelopeMercator().isEmpty()) {
      return;
    }
    Graphics2D name = (Graphics2D) g;
    name.getClipBounds();

    MapPaintArgs args = new MapPaintArgs(this, name, content);
    content.paint(args);
    /**
     * Instructs the tools in the toolbox to do custom drawing that happens before any of
     * the tools themselves are drawn.
     */
    getToolbox().paintContent(args);

    /**
     * Tools render their special content if any after the layers.
     */
    getToolbox().paint(args);
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the element like a button or track slider that is rendered within the map, and
   * currently has focus.
   *
   * @return the activeElement
   */
  public MapElement getActiveElement() {
    return activeElement;
  }

  /**
   * Sets the element like a button or track slider that is rendered within the map, and
   * currently has focus.
   *
   * @param activeElement the activeElement to set
   */
  public void setActiveElement(MapElement activeElement) {
    this.activeElement = activeElement;
  }

  /**
   * Gets the object that contains all the layers to be rendered on the map. This includes
   * tile datasets like the open street map tiles that usually serve as a background layer
   * for data. This object also has a cached image from when the tiles draw themselves.
   *
   * @return the content
   */
  public MapContent getContent() {
    return content;
  }

  /**
   * Sets the object that contains all the layers to be rendered on the map. This includes
   * tile datasets like the open street map tiles that usually serve as a background layer
   * for data. This object also has a cached image from when the tiles draw themselves.
   *
   * @param content the content to set
   */
  public void setContent(MapContent content) {
    this.content = content;
  }

  /**
   * Gets a boolean that indicates that one of the elements is being actively manipulated,
   * like a track slider, and therefore the mouse should not necessarily move unrestricted
   * on the map.
   *
   * @return the cursorLocked
   */
  public boolean isCursorLocked() {
    return cursorLocked;
  }

  /**
   * Sets a boolean that indicates that one of the elements is being actively manipulated,
   * like a track slider, and therefore the mouse should not necessarily move unrestricted
   * on the map.
   *
   * @param cursorLocked the cursorLocked to set
   */
  public void setCursorLocked(boolean cursorLocked) {
    this.cursorLocked = cursorLocked;
  }

  /**
   * Gets the list of all the tools that respond to mouse events or add extra content or
   * functionality to the map.
   *
   * @return the toolbox
   */
  public MapToolbox getToolbox() {
    return toolbox;
  }

  /**
   * Sets the list of all the tools that respond to mouse events or add extra content or
   * functionality to the map.
   *
   * @param toolbox the toolbox to set
   */
  public void setToolbox(MapToolbox toolbox) {
    this.toolbox = toolbox;
  }

  /**
   * Gets a boolean that indicates whether the content for this map is currently being
   * redrawn. This is used by the MapToolCoordinates to determine if the view is stable
   * enough for it to render itself.
   *
   * @return the updatingContent
   */
  public boolean isUpdatingContent() {
    return updatingContent;
  }

  /**
   * Sets a boolean that indicates whether the content for this map is currently being
   * redrawn. This is used by the MapToolCoordinates to determine if the view is stable
   * enough for it to render itself.
   *
   * @param updatingContent the updatingContent to set
   */
  public void setUpdatingContent(boolean updatingContent) {
    this.updatingContent = updatingContent;
  }

  /**
   * Gets a shared PopupMenu that is launched by right clicking on the map if PopupMapTool
   * is enabled.
   *
   * @return the popupMenu
   */
  public JPopupMenu getPopupMenu() {
    return popupMenu;
  }

  /**
   * Sets a shared PopupMenu that is launched by right clicking on the map if PopupMapTool
   * is enabled.
   *
   * @param popupMenu the popupMenu to set
   */
  public void setPopupMenu(JPopupMenu popupMenu) {
    this.popupMenu = popupMenu;
  }

  //</editor-fold>
  /**
   * Handles layout functions for tools that might have elements on top of the map.
   */
  @Override
  public final void doLayout() {
    super.doLayout();
    for (MapTool tool : toolbox.getTools()) {
      tool.doLayout(this);
    }
  }

  /**
   * @return the popupLocation
   */
  public final Coord getPopupLocation() {
    return popupLocation;
  }

  /**
   * @param popupLocation the popupLocation to set
   */
  public final void setPopupLocation(Coord popupLocation) {
    this.popupLocation = popupLocation;
  }

  /**
   * Gets the interface that provides access to simple dialog functions.
   *
   * @return the dialogHandler
   */
  public final DialogHandler getDialogHandler() {
    if (dialogHandler == null) {
      Object anscestor = SwingUtilities.getWindowAncestor(this);
      JFrame window = (JFrame) anscestor;
      dialogHandler = new DefaultDialogHandler(window);
    }
    return dialogHandler;
  }

  /**
   * Sets the interface that provides access to simple dialog functions.
   *
   * @param dialogHandler the dialogHandler to set
   */
  public final void setDialogHandler(DialogHandler dialogHandler) {
    this.dialogHandler = dialogHandler;
  }

  @Override
  public void setCursor(Cursor cursor) {
    if (isCursorLocked()) {
      return;
    }
    super.setCursor(cursor);
  }
}
