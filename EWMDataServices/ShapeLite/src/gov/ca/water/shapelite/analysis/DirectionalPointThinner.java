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

import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.DefaultEnvelope;
import java.util.ArrayList;
import java.util.List;

/**
 * The point thinner takes a cell size. It creates a rectangular grid that
 * covers the extent using the cell size parameter. Then it averages the points
 * found within the cell, producing a single point at the cell center with the
 * average dx and dy value.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class DirectionalPointThinner {

  /**
   * 100 percent.
   */
  public static final int MAX_PERCENT = 100;

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The double cell width and height in geographic coordinates.
   */
  private Double cellSize;
  /**
   * If the cell size should be specified as a percentage of the entire area of
   * interest, rather than an explicit size, this is that percent.
   */
  private Double percent;
  /**
   * The width in pixels (number of cells) of the entire area.
   */
  private Integer width;
  /**
   * The geographic clip envelope for the operation.
   */
  private Envelope clipBounds;

  //</editor-fold>
  /**
   * Creates a new instance of the PointThinner class.
   */
  public DirectionalPointThinner() {

  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Thins the list of directional points by defining a grid, and then averaging
   * the "directions" for each of the directional points found within a grid
   * cell.
   *
   * @param points The list of DirectionalPoint objects to thin.
   * @return The thinned list of points.
   */
  public final List<DirectionalPoint> thin(List<DirectionalPoint> points) {
    List<DirectionalPoint> result = new ArrayList<>();
    if (points == null || points.isEmpty()) {
      return result;
    }
    if (clipBounds == null) {
      Envelope env = new DefaultEnvelope();
      for (DirectionalPoint point : points) {
        env.expandToInclude(point);
      }
      clipBounds = env;
    }
    int nx = MAX_PERCENT;
    if (cellSize == null) {
      if (width != null) {
        cellSize = (clipBounds.getMax().getX()
                - clipBounds.getMin().getX()) / width;
        nx = width;
      } else if (percent != null) {
        cellSize = (clipBounds.getMax().getX()
                - clipBounds.getMin().getX()) * percent / MAX_PERCENT;
        nx = (int) (Math.ceil((clipBounds.getMax().getX()
                - clipBounds.getMin().getX()) / cellSize));
      } else {
        cellSize = (clipBounds.getMax().getX()
                - clipBounds.getMin().getX()) / MAX_PERCENT;
        // default to 1 percent
      }
    }
    double x = clipBounds.getMin().getX() + cellSize / 2;
    double y = clipBounds.getMax().getY() - cellSize / 2;

    int ny = (int) (Math.ceil((clipBounds.getMax().getY()
            - clipBounds.getMin().getY()) / cellSize));
    DirectionalPointGrid grid
            = new DirectionalPointGrid(new CoordXY(x, y), cellSize, nx, ny);
    for (DirectionalPoint point : points) {
      grid.add(point);
    }
    result = grid.getAverages();
    return result;
  }

    //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * If no cell size is specified the default will be 1/100th of the clip
   * bounds.
   *
   * @return the cellSize
   */
  public final Double getCellSize() {
    return cellSize;
  }

  /**
   * @param cellSize the cellSize to set
   */
  public final void setCellSize(Double cellSize) {
    this.cellSize = cellSize;
  }

  //</editor-fold>
  /**
   * If no bounds are specified, the clip bounds will be expanded to contain all
   * the points.
   *
   * @return the clipBounds
   */
  public final Envelope getClipBounds() {
    return clipBounds;
  }

  /**
   * @param clipBounds the clipBounds to set
   */
  public final void setClipBounds(Envelope clipBounds) {
    this.clipBounds = clipBounds;
  }

  /**
   * Gets the percent. If the cell size should be specified as a percentage of
   * the entire area of interest, rather than an explicit size, this is that
   * percent.
   *
   * @return the percent
   */
  public final Double getPercent() {
    return percent;
  }

  /**
   * Sets the percent. If the cell size should be specified as a percentage of
   * the entire area of interest, rather than an explicit size, this is that
   * percent.
   *
   * @param percent the percent to set
   */
  public final void setPercent(Double percent) {
    this.percent = percent;
  }

  /**
   * @return the width
   */
  public final Integer getWidth() {
    return width;
  }

  /**
   * @param width the width to set
   */
  public final void setWidth(Integer width) {
    this.width = width;
  }

}
