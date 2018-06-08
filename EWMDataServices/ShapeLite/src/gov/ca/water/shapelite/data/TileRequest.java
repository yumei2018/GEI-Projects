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

import java.net.URL;
import java.util.concurrent.Callable;

/**
 * This class hosts a request to the URL for a specified tile.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class TileRequest implements Callable<TileResponse> {

  /**
   * The uniform resource locator address of the tile on the web.
   */
  private final URL url;

  /**
   * Creates a new instance of the TileRequest object with the specified url.
   *
   * @param url The address of the tile to request.
   */
  public TileRequest(URL url) {
    this.url = url;
  }

  /**
   * Gets the TileResponse result when this request is ultimately called.
   *
   * @return The TileResponse result with the downloaded tile.
   * @throws Exception occurs if there is a problem reading the tile from the
   * web.
   */
  @Override
  public final TileResponse call() throws Exception {
    return new TileResponse(url.openStream());
  }

}
