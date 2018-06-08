/*
 * The MIT License
 *
 * Copyright 2015 Harold A. Dunsford Jr. Ph.D..
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
package gov.ca.water.shapelite.analysis;

/**
 * This class is scale agnostic because it just holds the integers that can
 * represent any scale.
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class TileLocation {

  /**
   * The tile size.
   */
  private static final int TILE_SIZE = 256;

  /**
   * Large size.
   */
  private static final int LARGE_SIZE = 20000;

  /**
   * The integer raster row, usually from 0 to 256 for tiles.
   */
  private int row;
  /**
   * The integer raster column, usually from 0 to 256 for tiles.
   */
  private int col;
  /**
   * The tileX.
   */
  private int tileX;
  /**
   * the tileY.
   */
  private int tileY;


  /**
   * Creates a new instance of the TileHashkey.
   */
  public TileLocation(){
  }

  /**
   * Creates a new instance of the TileHashkey.
   * @param tileX
   * @param tileY
   * @param row
   * @param col
   */
  public TileLocation(int tileX, int tileY, int row, int col){
    this.tileX = tileX;
    this.tileY = tileY;
    this.row = row;
    this.col = col;
  }

  /**
   * Handles equality testing by attributes.
   *
   * @param obj The other object to test.
   * @return boolean, true if the properties are equal.
   */
  @Override
  public final boolean equals(Object obj) {
    if (obj instanceof TileLocation) {
      TileLocation other = (TileLocation) obj;
      return this.row == other.row
          && this.col == other.col
          && this.tileX == other.tileX
          && this.tileY == other.tileY;
    }
    return super.equals(obj);
  }

  /**
   * Gets a hash code that should spread values out.
   *
   * @return
   */
  @Override
  public final int hashCode() {
    int hash = this.row;
    hash = hash + TILE_SIZE * this.col;
    hash = hash + TILE_SIZE * TILE_SIZE * this.tileY;
    hash = hash + TILE_SIZE * TILE_SIZE * LARGE_SIZE + tileX;
    return hash;
  }

  /**
   * @return the row
   */
  public int getRow() {
    return row;
  }

  /**
   * @param row the row to set
   */
  public void setRow(int row) {
    this.row = row;
  }

  /**
   * @return the col
   */
  public int getCol() {
    return col;
  }

  /**
   * @param col the col to set
   */
  public void setCol(int col) {
    this.col = col;
  }

  /**
   * @return the tileX
   */
  public int getTileX() {
    return tileX;
  }

  /**
   * @param tileX the tileX to set
   */
  public void setTileX(int tileX) {
    this.tileX = tileX;
  }

  /**
   * @return the tileY
   */
  public int getTileY() {
    return tileY;
  }

  /**
   * @param tileY the tileY to set
   */
  public void setTileY(int tileY) {
    this.tileY = tileY;
  }



}
