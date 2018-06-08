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

import gov.ca.water.shapelite.data.dataset.Dataset;
import gov.ca.water.shapelite.DefaultEnvelope;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.NonNull;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.map.Mercator;

/**
 * A data source is not the information itself, but rather an instrument for
 * creating data necessary. Handling in ram memory is a balance.
 *
 * @author Harold A. Dunsford Jr. Ph.D./kprins
 * @param <T> The Dataset type.
 */
public abstract class DataSource<T extends Dataset> {

  /**
   * The minimum latitude, longitude in Ted Mercator.
   */
  public static final double MIN_LL = -180;

  /**
   * The minimum latitude, longitude in Ted Mercator.
   */
  public static final double MAX_LL = 180;

  /**
   * Gets the envelope for the entire study area.
   */
  public static final Envelope envelopeMercator
      = new DefaultEnvelope(MIN_LL, MIN_LL, MAX_LL, MAX_LL);



  //<editor-fold defaultstate="collapsed" desc="Private Fields">
  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Public Properties">


  /**
   * Gets the envelope containing all the data in this data source.  This
   * should be in Ted Mercator coordinates, not world coordinates.
   *
   * @return this.envelope.copy if assign, this.dataset.envelope if this.dataset
   * is assign and if still null, return Envelope(-180,-180,180,180) (the whole
   * world).
   */
  public final Envelope getEnvelopeMercator() {
    return envelopeMercator;
  }
  
  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Abstract Methods">
  /**
   * Gets the dataset as optional output.  
   * @see Dataset
   * @return The dataset.
   */
  @NonNull
  public abstract Optional<T> getDataset();
  //</editor-fold>
}
