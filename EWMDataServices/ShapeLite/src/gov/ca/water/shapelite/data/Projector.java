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

import java.awt.Point;
import java.awt.Rectangle;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.DefaultEnvelope;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.NonNull;

/**
 * Use Projector for content that has been pre-warped into the Mercator
 * projection using external tools.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class Projector {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The DataFrame that stores the rectangular view and the geographic Envelope
   * extents.
   */
  private final DataFrame mapFrame;

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Constructors">


  /**
   * Creates a new projector to work with a specific frame.
   *
   * @param frame The DataFrame to use for this projector.
   */
  public Projector(DataFrame frame) {
    this.mapFrame = frame;
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Reprojects a point to it's location in geographic coordinates.
   *
   * @param pt The point in client pixel coordinates to convert into geographic
   * coordinates.
   * @return The Geographic point, probably in the Mercator projection.
   */
  public final Coord getCoordinate(@NonNull Point pt) {
    if (pt == null) {
      throw new IllegalArgumentException("Parameter pt cannot be null.");
    }
    Envelope env = mapFrame.getEnvelopeMercator();
    Rectangle rect = mapFrame.getView();

    // Since mercator doesn't distort x, we can tranlsate it linearly
    double x = (pt.x - rect.x) * env.getWidth() / rect.width + env.getMin().getX();

    // Since mercator distorts y, we need to first find the mercator extent from
    // the geographic envelope.  Then, using the mercator extent, we can get a
    // proportional y for the point on the screen.  Finally, using the mercator
    // y, we can back-project to get what the geographic world coordinates would be.
    double y = env.getMax().getY() - (pt.y - rect.y) * (env.getHeight()) / rect.height;
    return new CoordXY(x, y);
  }

  /**
   * Give the specified rectangle, this reprojects into a corresponding
   * rectangle in image coordinates.
   *
   * @param rect The rectangular area on the view to convert into a geographic
   * extent.
   * @return An Envelope in geographic coordinates, probably Mercator.
   */
  public final Envelope getEnvelope(Rectangle rect) {
    Point bottomLeft = new Point(rect.x, rect.y + rect.height);
    Point topRight = new Point(rect.x + rect.width, rect.y);
    Coord min = getCoordinate(bottomLeft);
    Coord max = getCoordinate(topRight);
    return new DefaultEnvelope(min, max);
  }

  /**
   * Reprojects a coordinate from geographic space into client space. This might
   * not be contained within the rect.
   *
   * @param coordinate A geographic coordinate, probably in the Mercator
   * projection.  Coordinate cannot be null.
   * @return A Point in client pixel coordinates relative to the view rectangle.
   *
   */
  public final Point getPoint(@NonNull Coord coordinate) {
    if (coordinate == null) {
      throw new IllegalArgumentException("Parameter coordinate cannot be null.");
    }
    Envelope env = mapFrame.getEnvelopeMercator();
    Rectangle rect = mapFrame.getView();
    double x = (coordinate.getX() - env.getMin().getX()) * rect.width
            / env.getWidth() + rect.x;
    double y = (env.getMax().getY() - coordinate.getY()) * rect.height
            / env.getHeight() + rect.y;
    Point result = new Point((int) Math.round(x), (int) Math.round(y));
    return result;
  }

  /**
   * Reprojects the specified geographic envelope into a rectangular box.
   *
   * @param envelope The geographic envelope to convert into a client pixel
   * rectangle.  This cannot be null.
   * @return A rectangle representing the same area on the rectangular view.
   */
  public final Rectangle getRectangle(@NonNull Envelope envelope) {
    if (envelope == null) {
      throw new IllegalArgumentException("Parameter envelope cannot be null.");
    }
    Point bottomLeft = getPoint(envelope.getMin());
    Point topRight = getPoint(envelope.getMax());
    int x = bottomLeft.x;
    int y = topRight.y;
    int w = topRight.x - x;
    int h = bottomLeft.y - y;
    return new Rectangle(x, y, w, h);
  }

   //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the DataFrame that stores the rectangular view and the geographic
   * Envelope extents.
   *
   * @return the mapFrame.
   */
  public final DataFrame getDataFrame() {
    return mapFrame;
  }


  //</editor-fold>
}
