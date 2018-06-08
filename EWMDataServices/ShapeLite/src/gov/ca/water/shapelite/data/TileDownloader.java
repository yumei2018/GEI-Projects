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
package gov.ca.water.shapelite.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.event.EventListenerList;
import gov.ca.water.shapelite.map.TileDownloadListener;

/**
 * This class is a singleton that is responsible for providing a convenient
 * accessible interface for requesting tile downloads. This makes use of the
 * TileDownload class to actually download the tiles.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class TileDownloader {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * A boolean that, if true, will cause the TileDownload thread to abort
   * downloading.
   */
  private boolean canceled;

  /**
   * The object that is actually responsible for hosting the list of tiles to
   * download and downloading those tiles on a background thread.
   */
  private TileDownload download;

  /**
   * The executor is responsible for ensuring only a few background threads are
   * used.
   */
  private final ExecutorService executor = Executors.newFixedThreadPool(10);

  /**
   * The list of event listeners for the tiles downloaded event.
   */
  private final EventListenerList listeners = new EventListenerList();

  /**
   * This is the list of lowPriority tile paths before they have been transfered
   * to the TileDownload class.
   */
  private List<TilePath> lowPriority;

  /**
   * A boolean that is true if only the low priority tiles have been canceled.
   */
  private boolean lowPriorityCanceled;

  /**
   * The list of newly added tile paths to download.
   */
  private List<TilePath> pending;

  /**
   * The requested list is comprehensive and simply prevents downloading tiles a
   * second time that have already been requested but have not been downloaded.
   */
  private List<String> requested;

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Constructor & Instance">
  /**
   * Creates a new instance of the TileDownloader. This is private as part of
   * the singleton pattern.
   */
  private TileDownloader() {
    pending = new ArrayList<>();
    requested = new ArrayList<>();
    lowPriority = new ArrayList<>();
  }

  /**
   * The static singleton holder that is part of the singleton pattern in java.
   */
  private static class SingletonHolder {

    /**
     * The singleton instance.
     */
    public static final TileDownloader INSTANCE = new TileDownloader();
  }

  /**
   * The single shared instance of the TileDownloader.
   *
   * @return The TileDownloader instance.
   */
  public static TileDownloader getInstance() {
    return SingletonHolder.INSTANCE;
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Events">
  /**
   * Adds a new listener for the tile downloaded event.
   *
   * @param listener The lister to add.
   */
  public void addTileDownloadListener(TileDownloadListener listener) {
    if (!Arrays.asList(listeners.getListeners(TileDownloadListener.class)).
            contains(listener)) {
      listeners.add(TileDownloadListener.class, listener);
    }
  }

  /**
   * Removes the specified listener for the tile download event.
   *
   * @param listener The TileDownloadListener interface to remove.
   */
  public void removeTileDownloadListener(TileDownloadListener listener) {
    listeners.remove(TileDownloadListener.class, listener);
  }

  /**
   * Fires the tile downloaded event.
   *
   * @param e The TileEvent object that contains information about the source of
   * the event, and what tiles were downloaded.
   */
  public void fireTileDownloaded(TilesEvent e) {
    TileDownloadListener[] listenerSet =
            listeners.getListeners(TileDownloadListener.class);
    for (TileDownloadListener listener : listenerSet) {
      listener.tileDownloaded(e);
    }
  }

  /**
   * Fires the tile downloaded event, but only if the list of tiles in the event
   * object is not null or empty.
   *
   * @param e The TilesEvent object with information about the source of the
   * event and what tiles were downloaded.
   */
  public void tileDownloaded(TilesEvent e) {
    if (e != null) {
      if (e.getTiles() != null && !e.getTiles().isEmpty()) {
        this.fireTileDownloaded(e);
      }
    }
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Adds the list of TilePaths to the tile requests.
   *
   * @param paths The list of TilePath objects that define which tiles to
   * download.
   */
  public synchronized void add(List<TilePath> paths) {
    for (TilePath path : paths) {
      if (!requested.contains(path.getUrl())) {
        pending.add(path);
         // basically requested keeps track of any requests so that we don't duplicate
        // tiles.
        requested.add(path.getUrl());
      }
    }
    if (download == null || !download.isAlive()) {
      download = new TileDownload();
      download.setName("Download Thread");
      download.start();
    }
  }

  /**
   * Adds the single TilePath to the tile requests.
   *
   * @param path The TilePath object that defines which tile to download.
   */
  public synchronized void add(TilePath path) {
    if (!requested.contains(path.getUrl())) {
      pending.add(path);
         // basically requested keeps track of any requests so that we don't duplicate
      // tiles.
      requested.add(path.getUrl());
    }
    if (download == null || !download.isAlive()) {
      download = new TileDownload();
      download.start();
    }
  }

  /**
   * Adds the list of paths to the list of low priority tiles to download. These
   * will be downloaded only once all the high priority tiles have been
   * requested.
   *
   * @param paths The list of TilePath objects that define which tiles to
   * download.
   */
  public synchronized void addLowPriority(List<TilePath> paths) {
    for (TilePath path : paths) {
      if (!requested.contains(path.getUrl())) {
        lowPriority.add(path);
         // basically requested keeps track of any requests so that we don't duplicate
        // tiles.
        requested.add(path.getUrl());
      }
    }
    if (download == null || !download.isAlive()) {
      download = new TileDownload();
      download.start();
    }
  }

  /**
   * Adds the TilePath object to the list of low priority tiles. These will be
   * downloaded only once all the high priority tiles have been requested.
   *
   * @param path The TilePath to add to the low priority downloads.
   */
  public synchronized void addLowPriority(TilePath path) {
    if (!requested.contains(path.getUrl())) {
      lowPriority.add(path);
         // basically requested keeps track of any requests so that we don't duplicate
      // tiles.
      requested.add(path.getUrl());
    }
    if (download == null || !download.isAlive()) {
      download = new TileDownload();
      download.start();
    }
  }

  /**
   * Once all downloading is finished, we should be able to clear the requested
   * list.
   */
  public void finished() {
    download = null;
    canceled = false;
    requested = new ArrayList<>();
  }

  /**
   * This method not only gets the list, but exchanges it for a new one, so that
   * concurrency conflicts won't happen.
   *
   * @return The list of TilePaths to download.
   */
  public List<TilePath> takePending() {
    List<TilePath> result;
    synchronized (this) {
      result = pending;
      pending = new ArrayList<>();
    }
    return result;
  }

  /**
   * This method not only gets the low priority list, but exchanges it for a new
   * one, so that concurrency conflicts won't happen.
   *
   * @return The list of low priority TielPaths to download.
   */
  public List<TilePath> takeLowPriority() {
    List<TilePath> result;
    synchronized (this) {
      result = lowPriority;
      lowPriority = new ArrayList<>();
    }
    return result;
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets a boolean that is true if tile downloading is canceled.
   *
   * @return the canceled state.
   */
  public synchronized boolean isCanceled() {
    return canceled;
  }

  /**
   * Sets a boolean that is true if tile downloading has been canceled.
   *
   * @param canceled the canceled state to set.
   */
  public synchronized void setCanceled(boolean canceled) {
    this.canceled = canceled;
  }

  /**
   * Gets the executor is responsible for ensuring only a few background threads
   * are used.
   *
   * @return the executor
   */
  public synchronized ExecutorService getExecutor() {
    return executor;
  }

  /**
   * Gets whether or not the lower priority tile download requests should be
   * canceled. Low priority tiles are any tiles that do not come from the most
   * recent direct navigation action. The TileDownload tool uses nothing but low
   * priority tiles. Since it may be necessary to stop a long running download
   * routine, this gives a tool to do that without affecting download requests
   * that come from a layer tied to the current view.
   *
   * @return the lowPriorityCanceled
   */
  public synchronized boolean isLowPriorityCanceled() {
    return lowPriorityCanceled;
  }

  /**
   * Sets whether or not the lower priority tile download requests should be
   * canceled.
   *
   * @param lowPriorityCanceled the lowPriorityCanceled to set
   */
  public synchronized void setLowPriorityCanceled(boolean lowPriorityCanceled) {
    this.lowPriorityCanceled = lowPriorityCanceled;
  }

  //</editor-fold>
}
