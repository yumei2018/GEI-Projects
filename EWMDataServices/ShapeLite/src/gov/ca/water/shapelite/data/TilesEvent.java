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
import java.util.Collection;
import java.util.EventObject;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class TilesEvent extends EventObject {

  /**
   * The list of tile events, or objects that extend tile TileEvents.
   */
  private List<? extends TileEvent> tiles;

  /**
   * Creates a new instance of the TilesEvent object.
   *
   * @param source The object source of the event.
   * @param tiles The collection of TileEvent objects.
   */
  public TilesEvent(Object source, Collection<? extends TileEvent> tiles) {
    super(source);
    this.tiles = new ArrayList<>(tiles);
  }

  /**
   * Gets the list of tiles.
   * @return the tiles.
   */
  public final List<? extends TileEvent> getTiles() {
    return tiles;
  }

  /**
   * @param tiles the tiles to set
   */
  public final void setTiles(List<? extends TileEvent> tiles) {
    this.tiles = tiles;
  }

}
