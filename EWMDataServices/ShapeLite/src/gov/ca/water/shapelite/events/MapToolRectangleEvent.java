/*
 * The MIT License
 *
 * Copyright 2015 hdunsford.
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
package gov.ca.water.shapelite.events;

import gov.ca.water.shapelite.Envelope;
import java.awt.Rectangle;
import java.util.EventObject;

/**
 * This event handles the case when a rectangle has been drawn, showing the
 * envelope, the rectangle layer.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class MapToolRectangleEvent extends EventObject {

  /**
   * The envelope in geographic coordinates.
   */
  private Envelope envelope;

  /**
   * The pixel rectangle in client coordinates.
   */
  private Rectangle pixelRectangle;

  /**
   * Creates a new instance of a MapToolRectangle event.
   *
   * @param sender The sender.
   * @param envelope The geographic bounds of the envelope.
   * @param rect The rectangle in pixels on the current map view.
   */
  public MapToolRectangleEvent(Object sender, Envelope envelope, Rectangle rect) {
    super(sender);
    this.envelope = envelope;
    this.pixelRectangle = rect;

  }

  /**
   * Gets the envelope in geographic coordinates.
   * @return the envelope
   */
  public final Envelope getEnvelope() {
    return envelope;
  }

  /**
   * Sets the envelope in geographic coordinates.
   * @param envelope the envelope to set
   */
  protected final void setEnvelope(Envelope envelope) {
    this.envelope = envelope;
  }

  /**
   * Gets the pixel rectangle in client coordinates.
   * @return the pixelRectangle
   */
  public final Rectangle getPixelRectangle() {
    return pixelRectangle;
  }

  /**
   * Sets the pixel rectangle in client coordinates.
   * @param pixelRectangle the pixelRectangle to set
   */
  public final void setPixelRectangle(Rectangle pixelRectangle) {
    this.pixelRectangle = pixelRectangle;
  }

}
