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

import gov.ca.water.shapelite.data.dataset.ImageDataset;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * This class represents the background thread that loops through the addition
 * of tiles.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class TileDownload extends Thread {

  /**
   * Maximum number of requests that can occur at once.
   */
  public static final int MAX_REQUESTS = 50;

  /**
   * The number of requests that will be added between sleep cycles.
   */
  public static final int BATCH_SIZE = 25;

  /**
   * The HashMap of TilePath objects and the Future&lt;TileResponse&gt; objects.
   */
  private final HashMap<TilePath, Future<TileResponse>> requests = new HashMap<>();

  /**
   * The list of TilePath objects that are low priority, and can be canceled or
   * postponed if the user is navigating.
   */
  private final List<TilePath> myLowPriority;

  /**
   * The stack of TilePath objects that are high priority and are already
   * requested.
   */
  private final Stack<TilePath> myPending;

  /**
   * Creates a new instance of a TileDownload object.
   */
  public TileDownload() {
    myPending = new Stack<>();
    myLowPriority = new ArrayList<>();
  }

  /**
   * This reads the list of requested tiles from the main thread and updates the
   * list that is on this background thread. This immediately requests tiles in
   * the list, but adds tiles from the myLowPriority list as download slots
   * become available.
   */
  private void getPending() {
    List<TilePath> pending;
    List<TilePath> lowPriority;
    boolean lowPriorityCanceled;
    synchronized (TileDownloader.getInstance()) {
      pending = TileDownloader.getInstance().takePending();
      lowPriority = TileDownloader.getInstance().takeLowPriority();
      lowPriorityCanceled = TileDownloader.getInstance().isLowPriorityCanceled();
    }
    myPending.addAll(pending);
    myLowPriority.addAll(lowPriority);
    if (myPending != null && myPending.size() > 0) {
      for (int i = 0; i < BATCH_SIZE; i++) {
        if (requests.size() > MAX_REQUESTS || myPending.isEmpty()) {
          break;
        }
        TilePath path = myPending.pop();
        try {
          synchronized (TileDownloader.getInstance()) {
            Future<TileResponse> response = TileDownloader.getInstance()
                    .getExecutor().submit(new TileRequest(
                            new URL(path.getUrl())));
            requests.put(path, response);
          }
        } catch (MalformedURLException ex) {
          Logger.getLogger(TileDownloader.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
    // Only add members from low priority if we are not busy
    if (lowPriorityCanceled) {
      myLowPriority.clear();
    } else {
      if (myLowPriority != null && myLowPriority.size() > 0) {
        for (int i = 0; i < BATCH_SIZE; i++) {
          if (requests.size() > MAX_REQUESTS || myLowPriority.isEmpty()) {
            break;
          }
          TilePath path = myLowPriority.get(0);
          myLowPriority.remove(0);
          path.setLowPriority(true);
          try {
            synchronized (TileDownloader.getInstance()) {
              Future<TileResponse> response = TileDownloader.getInstance()
                      .getExecutor().submit(new TileRequest(
                                      new URL(path.getUrl())));
              requests.put(path, response);
            }
          } catch (MalformedURLException ex) {
            Logger.getLogger(TileDownloader.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
      }
    }
  }

  /**
   * This is the actual method that is running on a background thread to request
   * and manage downloads. It operates in a loop and copies tiles from the
   * myLowPriority list if there are slots available.
   */
  @Override
  public final void run() {

    getPending();
    if (requests.isEmpty()) {
      TileDownloader.getInstance().finished();
      return;
    }
    boolean canceled;
    synchronized (TileDownloader.getInstance()) {
      canceled = TileDownloader.getInstance().isCanceled();
    }
    while ((!myPending.isEmpty() || !requests.isEmpty()
            || !myLowPriority.isEmpty()) && !canceled) {
      getPending();
      int failcount = 0;
      try {
        Thread.sleep(1000);
      } catch (InterruptedException ex) {
        Logger.getLogger(TileDownload.class.getName()).log(Level.SEVERE, null, ex);
      }
      List<TilePath> doneList = new ArrayList<>();
      System.out.println("requests: " + requests.size());
      List<TileEvent> results = new ArrayList<>();
      for (Map.Entry<TilePath, Future<TileResponse>> pair : requests.entrySet()) {
        TilePath tp = pair.getKey();
        Future<TileResponse> response = pair.getValue();
        if (response.isDone()) {
          TileEvent evt;
          doneList.add(tp);
          try {

            BufferedImage result = ImageIO.read(response.get().getBody());
            File f = new File(tp.getFilePath());
            if (f.exists()) {
              System.out.println("Duplicated: " + tp.getFilePath());
            } else {
              ImageIO.write(result, tp.getFormat(), new File(tp.getFilePath()));
            }

            ImageDataset img = new ImageDataset();
            img.setImage(result);
            img.getEnvelopeMercator().copyProperties(pair.getKey().getBounds());
            evt = new TileEvent(this, tp, img);
            results.add(evt);

          } catch (InterruptedException | ExecutionException | IOException ex) {
            evt = new TileEvent(this, tp, null);
            results.add(evt);
            // event failed, so add it back to the bottom of the list to try later.
            myPending.add(tp);
            failcount++;
          }

        }
      }
      TilesEvent set = new TilesEvent(this, results);
      TileDownloader.getInstance().fireTileDownloaded(set);
      System.out.println("failed: " + failcount);
      for (TilePath path : doneList) {
        requests.remove(path);
      }
    }
  }
}
