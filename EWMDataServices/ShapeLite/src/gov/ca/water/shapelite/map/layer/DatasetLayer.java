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
package gov.ca.water.shapelite.map.layer;

import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.data.dataset.Dataset;
import gov.ca.water.shapelite.symbology.Symbolizer;

/**
 * This class supports an in memory data object, like a list of markers, that
 * serve as the dataset. This is best used for small datasets that can fit
 * entirely and easily into memory.
 *
 * @author Harold A. Dunsford Jr. Ph.D./kprins
 * @param <TDataset> extends Dataset
 * @param <TSym> extends Symbolizer
 */
public abstract class DatasetLayer<TDataset extends Dataset, TSym extends Symbolizer>
    extends Layer<TSym> {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The in memory dataset of geographic features, such as markers or marker
   * paths.
   */
  private TDataset dataset;
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Constructors">
  /**
   * Creates a new, empty instance of the MapLayerDataset. Here, neither the
   * dataset nor the symbolizer are defined in the constructor.
   */
  public DatasetLayer() {
    super();
    this.dataset = null;
  }

  /**
   * Creates a new instance of the MapLayerDataset.
   *
   * @param dataset The dataset object.  This is frequently a FeatureSet.
   * @param symbolizer The symbolizer to use to draw the features.
   */
  public DatasetLayer(TDataset dataset, TSym symbolizer) {
    this.dataset = dataset;
    this.setDefaultSymbolizer(symbolizer);
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the in memory dataset of geographic features, such as markers or
   * marker paths.
   *
   * @return the dataset (can be null is not assigned)
   */
  public TDataset getDataset() {
    return this.dataset;
  }

  /**
   * Sets the in memory dataset of geographic features, such as markers or
   * marker paths.
   *
   * @param dataset the dataset to set
   */
  public void setDataset(TDataset dataset) {
    this.dataset = dataset;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Layer Overrides">
  /**
   * {@inheritDoc}
   * <p>
   * OVERRIDE: Get this.dataset.envelope or super.envelope if this.dataset =
   * null.</p>
   */
  @Override
  public Envelope getEnvelope() {
    if (this.dataset == null) {
      return super.getEnvelope();
    }
    return this.dataset.getEnvelopeMercator();
  }

  //</editor-fold>




}
