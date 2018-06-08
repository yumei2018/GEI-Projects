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

import gov.ca.water.shapelite.events.MapEventMouse;
import gov.ca.water.shapelite.events.MapEventTiles;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.DefaultEnvelope;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.data.DataSourceTiles;
import gov.ca.water.shapelite.data.TileDownloader;
import gov.ca.water.shapelite.data.TileEvent;
import gov.ca.water.shapelite.data.TilePath;
import gov.ca.water.shapelite.map.MapPaintArgs;
import gov.ca.water.shapelite.map.Mercator;

/**
 * This tool is not necessary for standard download as a user moves into the view.
 * That is handled directly by the MapTiles layer.  Instead, this tool provides a
 * system to enable the user to specify an area of interest, and have ALL levels
 * of all tiles be downloaded and cached for the specified region.  It shows
 * the status of what has most recently been downloaded by drawing rectangles
 * on the screen.
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class DownloadMapTool extends MapTool {

  //<editor-fold defaultstate="collapsed" desc="Fields">

  /**
   * The list of TilePath tiles that have been downloaded by this tool.
   */
  private List<TilePath> downloaded;

  /**
   * The <code>java.awt.Point</code> that is the end point of the selected area of
   * interest in client pixel coordinates.
   */
  public Point end;

  /**
   * The coordinate that is the start point of the selected area of interest
   */
  private Coord endCoordinate;

  /**
   * A boolean that indicates whether or not the mouse is currently dragging an area
   * of interest.
   */
  public boolean isDragging;

  /**
   * The maximum scale that should be downloaded.  This defaults to 14, but with
   * most tile sets can be set as high as 18.
   */
  private int maxLevel;

  /**
   * The minimum scale that should be downloaded.  This defaults to 6, but with
   * most tile sets can be set as low as 0.  It usually doesn't need to be set that
   * low because unless the user is selecting the whole world, they will likely
   * have already downloaded the lowest resolution tiles just by navigating to
   * the area of interest on the map.
   */
  private int minLevel;

  /**
   * The <code>java.awt.Point</code> representing the start point for the
   * dragged area of interest in client pixel coordinates.
   */
  public Point start;

  /**
   * The start coordinate in geographic map coordinates (Mercator).
   */
  public Coord startCoordinate;


  //</editor-fold>

  /**
   * Creates a new instance of the MapToolDownload.
   */
  public DownloadMapTool() {
    downloaded = new ArrayList<TilePath>();
    this.setName("download");
    minLevel = 6;
    maxLevel = 14;
  }

  //<editor-fold defaultstate="collapsed" desc="Methods">


  /**
   * Paints rectangles on the screen designating the extents of the most recently
   * downloaded tiles.
   * @param args A MapPaintArgs argument that has information about the drawing
   * canvas and the Map control.
   */
  @Override
  public void paint(MapPaintArgs args) {
    if (isDragging && end != null && start != null) {

      // Ensure that the min is smaller than the max for the selected region.
      int xmin = Math.min(end.x, start.x);
      int xmax = Math.max(end.x, start.x);
      int ymin = Math.min(end.y, start.y);
      int ymax = Math.max(end.y, start.y);

      // Set up a rectangle using the
      Rectangle rect = new Rectangle(xmin, ymin, xmax - xmin, ymax - ymin);
      args.getGraphics().setColor(Color.BLACK);
      Stroke original = args.getGraphics().getStroke();
      BasicStroke strk = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{2.0f, 1.0f}, 0);
      args.getGraphics().setStroke(strk);
      args.getGraphics().drawRect(rect.x, rect.y, rect.width, rect.height);
      args.getGraphics().setStroke(original);
    } else {
      if (downloaded != null && !downloaded.isEmpty()) {
        for (TilePath p : downloaded) {
          if (args.getMap() != null) {
            Rectangle r = args.getMap().getContent().getProjector().getRectangle(p.getBounds());
            args.getGraphics().setColor(new Color(0, 255, 0, 64));
            args.getGraphics().fillRect(r.x, r.y, r.width, r.height);
            args.getGraphics().setColor(new Color(0, 128, 0));
            args.getGraphics().drawRect(r.x, r.y, r.width, r.height);
          }
        }
      }
    }
  }

  /**
   * Occurs when the mouse is pressed, and starts tracking the dragging of a selected
   * area of interest if the left mouse button is used.
   * @param e
   */
  @Override
  public void mousePressed(MapEventMouse e) {
    if (SwingUtilities.isLeftMouseButton(e)) {
      start = e.getPoint();
      startCoordinate = e.getLocation();
      isDragging = true;
    }
  }

  /**
   * Occurs when the mouse is dragged with the button down.
   * @param e
   */
  @Override
  public void mouseDragged(MapEventMouse e) {
    if(!isDragging)
    {
      return;
    }
    end = e.getPoint();
    e.getMap().repaint();
  }

  /**
   * Occurs when the mouse is released.  If we have just dragged a selection
   * rectangle, this will trigger the map to begin the tile download process
   * for the selected layer.
   * @param e
   */
  @Override
  public void mouseReleased(MapEventMouse e) {
    if (SwingUtilities.isLeftMouseButton(e) && isDragging) {
      isDragging = false;
      end = e.getPoint();
      endCoordinate = e.getLocation();
      Envelope box = new DefaultEnvelope();
      box.expandToInclude(startCoordinate);
      box.expandToInclude(endCoordinate);
      box = Mercator.toMerc(box);
      /**
       * The default DataSourceTiles is configured to use Open Street Map
       */
      DataSourceTiles tiles = new DataSourceTiles();
      List<TilePath> allPaths = new ArrayList<>();

      // Level 6 to 14 were set for a specific need.  In practice, this might
      // be re-configured to
      for (int level = minLevel; level <= maxLevel; level++) {
        tiles.setLevel(level);

        // This method returns a rectangle in integer tile units.  So x
        // is the count of the tiles from the -180 longitude
        // at the current zoom level.
        Rectangle extent = tiles.getTileBounds(box);

        for (int y = extent.y; y <= extent.y + extent.height; y++) {
          for (int x = extent.x; x <= extent.x + extent.width; x++) {

            tiles.setX(x);
            tiles.setY(y);
            if (!tiles.getEnvelopeMercator().intersects(box)) {
              continue;
            }
            TilePath tp = tiles.getTilePath();
            File f = new File(tp.getFilePath());
            if (f.exists()) {
              continue;
            }
            allPaths.add(tp);
          }
        }
        TileDownloader.getInstance().addLowPriority(allPaths);
        System.out.println("Built Level " + level);
      }
      e.getMap().getContent().paintImmediately();
      e.getMap().repaint();
    }
  }

  /**
   * This method is triggered after the tiles have been downloaded.  The Map automatically
   * packages the event from the TileDownloader, so we don't have to add a listener
   * directly to the TileDownloader class.
   * @param e The MapEventTiles containing information about which tiles were downloaded
   * and the Map control that downloaded them.
   */
  @Override
  public void tilesDownloaded(MapEventTiles e) {
    if(e.getTiles().isEmpty()) {
      return;
    }
    this.downloaded.clear();
    Envelope env = new DefaultEnvelope();
    for(TileEvent evt : e.getTiles()){
      if(evt.getPath().isLowPriority() && evt.getTile() != null && evt.getTile().getEnvelopeMercator() != null && !evt.getTile().getEnvelopeMercator().isEmpty()){
        this.downloaded.add(evt.getPath());
        env.expandToInclude(evt.getTile().getEnvelopeMercator());
      }
    }
    if (e.getMap() != null && !env.isEmpty()) {
      Rectangle rct = e.getMap().getContent().getProjector().getRectangle(env);
       e.getMap().paintImmediately(rct.x, rct.y, rct.width, rct.height);
    }
  }







  //<editor-fold defaultstate="collapsed" desc="Properties">

  /**
   * Occurs when this tool is enabled or disabled.  Disabling the tool also cancels
   * any outstanding tiles requested in the queue
   * @param enabled The boolean that controls whether tile downloading is enabled or
   * disabled.
   */
  @Override
  public void setEnabled(boolean enabled) {
    if(enabled == false){
      TileDownloader.getInstance().setLowPriorityCanceled(true);
    }
    else{
      TileDownloader.getInstance().setLowPriorityCanceled(false);
    }
    super.setEnabled(enabled);
  }

  /**
   * Gets the maximum level that should be downloaded.  This defaults to 14, but with
   * most tile sets can be set as high as 18.
   * @return the maxScale
   */
  public int getMaxLevel() {
    return maxLevel;
  }

  /**
   * Sets the maximum level that should be downloaded.  This defaults to 14, but with
   * most tile sets can be set as high as 18.
   * @param maxScale the maxScale to set
   */
  public void setMaxLevel(int maxScale) {
    this.maxLevel = maxScale;
  }

  /**
   * Gets the minimum level that should be downloaded.  This defaults to 6, but with
   * most tile sets can be set as low as 0.  It usually doesn't need to be set that
   * low because unless the user is selecting the whole world, they will likely
   * have already downloaded the lowest resolution tiles just by navigating to
   * the area of interest on the map.
   * @return the minScale
   */
  public int getMinLevel() {
    return minLevel;
  }

  /**
   * Sets the minimum level that should be downloaded.  This defaults to 6, but with
   * most tile sets can be set as low as 0.  It usually doesn't need to be set that
   * low because unless the user is selecting the whole world, they will likely
   * have already downloaded the lowest resolution tiles just by navigating to
   * the area of interest on the map.
   * @param minScale the minScale to set
   */
  public void setMinLevel(int minScale) {
    this.minLevel = minScale;
  }

   //</editor-fold>
}
