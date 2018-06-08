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

import java.awt.Rectangle;
import gov.ca.water.shapelite.Envelope;

/**
 * This interface acts as the go-between between an envelope in real world
 * coordinates and a view that is in screen coordinates.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public interface DataFrame {

  /**
   * Gets the real world envelope in projected coordinates.  This depends
   * on the projection that is set for this object, and is usually a
   * derived property of envelope mercator.
   * @return An Envelope in projection coordinates.
   */
  Envelope getEnvelope();

  /**
   * Gets the real world geographic envelope that should appear in the view,
   * in TedMercator coordinates.
   *
   * @return the geographic displayEnvelope
   */
  Envelope getEnvelopeMercator();

  /**
   * Gets the pixel coordinates where the display envelope should end up.
   *
   * @return The Rectangle view.
   */
  Rectangle getView();

  /**
   * Gets the scale like 20000 if 20000 real world inches are displayed as one
   * inch. This assumes 96 pixels per inch.
   *
   * @return The real world / screen ratio.
   */
  double getScale();

  /**
   * Gets the zoom level from 0 to 18 for the open street map tile layers that
   * most closely matches the pixel resolution of the current view for
   * displaying the current extent.
   *
   * @return the minimum zoom Level that can be displayed.
   */
  int getClosestLevel();

  /**
   * Gets the projector object used to handle the math of a transform between
   * the envelope and the view.
   *
   * @return A Projector.
   */
  Projector getProjector();

}
