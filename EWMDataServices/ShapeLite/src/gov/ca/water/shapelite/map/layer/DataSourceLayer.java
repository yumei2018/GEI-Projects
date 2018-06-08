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
import gov.ca.water.shapelite.NonNull;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.data.DataSource;
import gov.ca.water.shapelite.symbology.Symbolizer;

/**
 * Unlike a DataSet, which is loaded into memory, a DataSource simply points to
 * an external source of data, like a database table or a file, where data is
 * read each time the layer is drawn. Tiles are more frequently drawn from
 * files, rather than trying to cache the huge, memory consuming data set into
 * memory.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public abstract class DataSourceLayer<T extends DataSource<?>, S extends Symbolizer>
    extends Layer<S> {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The data source that provides information for this layer on demand, rather
   * than storing the data in memory.
   */
  @NonNull
  private final T dataSource;
  //</editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Public Constructor.
   *
   * @param dataSource A DataSource which should never be null.
   * @throws IllegalArgumentException if dataSource is null.
   */
  public DataSourceLayer(@NonNull T dataSource) {
    super();
    if (dataSource == null) {
      throw new IllegalArgumentException("Parameter dataSource cannot be null.");
    }
    this.dataSource = dataSource;
  }
  // </editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the bounding envelope for this layer. This overrides the underlying
   * envelope behavior because the data source controls the bounding region.
   *
   * @return The Envelope.
   */
  @Override
  public Envelope getEnvelope() {
    return this.dataSource.getEnvelopeMercator();
  }

  /**
   * Gets the data source that provides information for this layer on demand,
   * rather than storing the data in memory.
   *
   * @return the dataset
   */
  public T getDataSource() {
    return dataSource;
  }

  //</editor-fold>
}
