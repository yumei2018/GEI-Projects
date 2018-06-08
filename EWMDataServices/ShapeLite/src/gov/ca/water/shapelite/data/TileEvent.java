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
import java.util.EventObject;

/**
 * The TileEvent extends the EventObject with information about a TilePath that
 * was downloaded and the resulting Image.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class TileEvent extends EventObject {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The TilePath that was downloaded, which describes the name, level and
   * location.
   */
  private TilePath path;

  /**
   * The in memory image of the tile that was downloaded.
   */
  private ImageDataset tile;

  //</editor-fold>
  /**
   * Creates a new instance of a TileEvent with the specified source, path and
   * image.
   *
   * @param source The Object source of the event.
   * @param path The name, url and bounds of the downloaded tile.
   * @param image The Image downloaded.
   */
  public TileEvent(Object source, TilePath path, ImageDataset image) {
    super(source);
    this.path = path;
    this.tile = image;
  }

  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the TilePath that was downloaded, which describes the name, level and
   * location.
   *
   * @return the path
   */
  public final TilePath getPath() {
    return path;
  }

  /**
   * Sets the TilePath that was downloaded, which describes the name, level and
   * location.
   *
   * @param path the path to set
   */
  protected final void setPath(TilePath path) {
    this.path = path;
  }

  /**
   * Gets the in memory image of the tile that was downloaded.
   *
   * @return the tile
   */
  public final ImageDataset getTile() {
    return tile;
  }

  /**
   * Sets the in memory image of the tile that was downloaded.
   *
   * @param tile the tile to set
   */
  protected final void setTile(ImageDataset tile) {
    this.tile = tile;
  }

  //</editor-fold>
}
