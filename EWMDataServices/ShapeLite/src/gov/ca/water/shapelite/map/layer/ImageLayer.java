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

import gov.ca.water.shapelite.data.Projector;
import java.awt.Rectangle;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.data.dataset.ImageDataset;
import gov.ca.water.shapelite.map.PaintArgs;
import gov.ca.water.shapelite.symbology.Pen;
import gov.ca.water.shapelite.symbology.ImageSymbolizer;
import gov.ca.water.shapelite.symbology.LineSymbolizer;
import java.awt.AlphaComposite;
import java.awt.Composite;

/**
 * This represents a layer on the map which supports an in-memory image. The
 * image should not be too large or too numerous as you can quickly run out of
 * memory using this type of layer.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ImageLayer extends DatasetLayer<ImageDataset, ImageSymbolizer> {

  /**
   * Creates a new MapLayerImage class.
   *
   * @param dataset The ImageDataset that has the image information.
   * @param symbolizer The symbolizer to use to draw the image.
   */
  public ImageLayer(ImageDataset dataset, ImageSymbolizer symbolizer) {
    super(dataset, symbolizer);
  }

  /**
   * Creates a layer with the default symbolizer for this dataset.
   *
   * @param dataset The image to add.
   */
  public ImageLayer(ImageDataset dataset) {
    super(dataset, new ImageSymbolizer());
  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * This handles the business logic of drawing the layer content. In this case,
   * since the layer data is an image, it draws the image based on the specified
   * bounds.
   *
   * @param e PaintArgs that contain information about the drawing surface.
   */
  @Override
  public final void paint(PaintArgs e) {
    if (getDataset().getImage() == null) {
      return;
    }
    Envelope view = e.getFrame().getEnvelopeMercator();
    Envelope bounds = getDataset().getEnvelopeMercator();
    Projector viewProj = new Projector(e.getFrame());
    if (e.getGraphics().getClip() != null) {
      view = viewProj.getEnvelope(e.getGraphics().getClipBounds());
    }
    if (!view.intersects(bounds)) {
      return;
    }
    Envelope intersection = view.intersect(bounds);
    Projector imageProj = new Projector(getDataset());
    LineSymbolizer border = this.getDefaultSymbolizer().getBorder();
    Rectangle source = imageProj.getRectangle(intersection);
    Rectangle imageBounds = viewProj.getRectangle(bounds);
    Envelope rounded = imageProj.getEnvelope(source);
    Rectangle dest = viewProj.getRectangle(rounded);
    if (border != null) {
      for (Pen pen : border.getPens()) {
        e.getGraphics().setStroke(pen.getStroke());
        e.getGraphics().setColor(pen.getColor());
        e.getGraphics().drawRect(imageBounds.x, imageBounds.y,
            imageBounds.width, imageBounds.height);
      }
    }

    AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
        this.getDefaultSymbolizer().getOpacity());
    Composite original = e.getGraphics().getComposite();
    e.getGraphics().setComposite(ac);
    e.getGraphics().drawImage(getDataset().getImage(),
        dest.x, dest.y, dest.x + dest.width, dest.y + dest.height,
        source.x, source.y, source.x + source.width,
        source.y + source.height, null, null);
    e.getGraphics().setComposite(original);
  }

  //</editor-fold>
}
