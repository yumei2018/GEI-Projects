/*
 * The MIT License
 *
 * Copyright 2014 hdunsford.
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

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.Optional;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class DirectionalPointGrid {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The width of the grid.
   */
  private int width;
  /**
   * The height of the grid.
   */
  private int height;
  /**
   * The center of the top left cell of the grid.
   */
  private Coord topLeftCenter;
  /**
   * The width and height of a square cell.
   */
  private double cellSize;
  /**
   * The jagged array of cells in row major like [y][x].
   */
  private DirectionalPointCell[][] cells;

  //</editor-fold>

  /**
   * Creates a new instance of a DirectionalPointGrid.
   */
  public DirectionalPointGrid() {

  }

  /**
   * Creates a new DirectionalPointGrid by specifying all the major parameters.
   *
   * @param topLeftCenter The center of the top left cell of the grid.
   * @param cellSize The width and height of a square cell.
   * @param width The width of the grid.
   * @param height The height of the grid.
   */
  public DirectionalPointGrid(Coord topLeftCenter, double cellSize, int width, int height) {
    this.width = width;
    this.height = height;
    this.topLeftCenter = topLeftCenter;
    this.cellSize = cellSize;
    cells = new DirectionalPointCell[height][width];
  }

  /**
   * Adds the specified point to the grid.
   *
   * @param point The DirectionalPoint to add.
   */
  public final void add(DirectionalPoint point) {
    int col = (int) ((point.getX() - this.topLeftCenter.getX()) / cellSize);
    int row = (int) ((this.topLeftCenter.getY() - point.getY()) / cellSize);
    if (col < 0) {
      col = 0;
    }
    if (row < 0) {
      row = 0;
    }
    if (col >= width) {
      col = width - 1;
    }
    if (row >= height) {
      row = height - 1;
    }

    DirectionalPointCell cell;
    if (cells[row][col] == null) {
      cell = new DirectionalPointCell(getCellCenter(row, col));
      cells[row][col] = cell;
    } else {
      cell = cells[row][col];
    }
    cell.getPoints().add(point);
  }

  /**
   * Given the row and column, this will return a DirectionalPoint, where the X
   * and Y values represent the cell center, and the dx and dy values are the
   * average of the dx and dy values from all the points in the cell.
   *
   * @param row The integer row index.
   * @param col The integer column index.
   * @return The DirectionalPoint associated with the specified location.
   */
  public final Optional<DirectionalPoint> getAverage(int row, int col) {
    DirectionalPointCell cell = cells[row][col];
    if (cell == null) {
      return Optional.empty();
    }
    double tx = 0;
    double ty = 0;
    int count = cell.getPoints().size();
    for (DirectionalPoint point : cell.getPoints()) {
      tx += point.getDx();
      ty += point.getDy();
    }
    DirectionalPoint result = new DirectionalPoint(cell.getLocation());
    result.setDx(tx / count);
    result.setDy(ty / count);
    return Optional.of(result);
  }

  /**
   * Cycles over the entire grid and returns a list of the averages for each
   * cell. Each cell returns either nothing or one DirectionalPoint.
   *
   * @return The list of Directional point averages for each cell.
   */
  public final List<DirectionalPoint> getAverages() {
    List<DirectionalPoint> result = new ArrayList<>();
    for (int row = 0; row < this.height; row++) {
      for (int col = 0; col < this.width; col++) {
        Optional<DirectionalPoint> dp = getAverage(row, col);
        if (dp.isPresent()) {
          result.add(dp.get());
        }
      }
    }
    return result;
  }

  /**
   * Gets a coordinate representing the center of the cell at the specified
   * row and column index.
   * @param row The zero based row index.
   * @param col The zero based column index.
   * @return The Coord representing the cell center.
   */
  public final Coord getCellCenter(int row, int col) {
    return new CoordXY(this.topLeftCenter.getX() + col * cellSize,
            this.topLeftCenter.getY() - row * cellSize);
  }

  //<editor-fold defaultstate="collapsed" desc="Properties">

  /**
   * Gets the width of the grid.
   * @return the width
   */
  public final int getWidth() {
    return width;
  }

  /**
   * Sets the width of the grid.
   * @param width the width to set
   */
  public final void setWidth(int width) {
    this.width = width;
  }

  /**
   * Gets the height of the grid.
   * @return the height
   */
  public final int getHeight() {
    return height;
  }

  /**
   * Sets the height of the grid.
   * @param height the height to set
   */
  public final void setHeight(int height) {
    this.height = height;
  }

  /**
   * Gets the center of the top left cell of the grid.
   * @return the topLeftCenter
   */
  public final Coord getTopLeftCenter() {
    return topLeftCenter;
  }

  /**
   * Sets the center of the top left cell of the grid.
   * @param topLeftCenter the topLeftCenter to set
   */
  public final void setTopLeftCenter(Coord topLeftCenter) {
    this.topLeftCenter = topLeftCenter;
  }

  /**
   * Gets the width and height of a square cell.
   * @return the cellSize
   */
  public final double getCellSize() {
    return cellSize;
  }

  /**
   * Sets the width and height of a square cell.
   * @param cellSize the cellSize to set
   */
  public final void setCellSize(double cellSize) {
    this.cellSize = cellSize;
  }

  //</editor-fold>
}
