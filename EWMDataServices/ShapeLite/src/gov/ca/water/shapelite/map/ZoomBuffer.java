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
package gov.ca.water.shapelite.map;

import gov.ca.water.shapelite.map.layer.ImageLayer;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import gov.ca.water.shapelite.data.dataset.ImageDataset;

/**
 * This class holds some images at different scales so that while zooming we can stretch
 * the image closest to that scale for effect.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ZoomBuffer {

  //<editor-fold defaultstate="collapsed" desc="Fields">
    /**
     * The MapContent is the collection of layers to be drawn.
     */
    private MapContent content;

    /**
     * The array of images that make up the buffer, each image representing a different
     * level.
     */
    private final ImageLayer[] images;

    /**
     * The GeoFrame that controls the geographic rectangle and the height, and width of
     * the control.
     */
    private GeoFrame frame;

    /**
     * The integer level of the current level shown on the map as a result of the sliding
     * of the track bar.
     */
    private int level;

    /**
     * This thread controls the filling of the zoom buffer.
     */
    private ZoomBufferThread thread;

  //</editor-fold>
    /**
     * Creates a new instance of a ZoomBuffer.
     */
    public ZoomBuffer() {
        images = new ImageLayer[19];
    }

  //<editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * This method causes the buffer image to be drawn for the specified level.
     *
     * @param zoomLevel the integer zoom level from 0 to 18 that should be created and
     * added to the array of cached images.
     */
    public void createBuffer(int zoomLevel) {
        int w = frame.getView().width;
        int h = frame.getView().height;
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, w, h);
        g.dispose();
        frame.setLevel(zoomLevel);
        PaintArgs args = new PaintArgs(this, g, frame);
        getContent().paintContent(args);
        ImageDataset ds = new ImageDataset();
        ds.setImage(img);
        ds.getEnvelopeMercator().copyProperties(frame.getEnvelopeMercator());
        images[zoomLevel] = new ImageLayer(ds);
    }

    /**
     * This method uses the renderer to cycle through the different levels as a background
     * process, rendering each of the levels.
     *
     * @param content The MapContent that has the layers to be drawn.
     */
    public void fillBuffer(MapContent content) {
        frame = new GeoFrame(content);
        this.content = content;
        if (thread == null || !thread.isAlive()) {
            ZoomBufferRenderer renderer = new ZoomBufferRenderer(this);
            thread = new ZoomBufferThread(renderer);
            thread.start();
        } else {
            thread.restart();
        }
    }

    /**
     * Checks to ensure that the specified level is not either less than zero or greater
     * than 18, which are outside the bounds for tile levels.
     *
     * @param level The integer level to constrain.
     * @return Either the original level, or the closest level in the range from 0 - 18.
     */
    private int boundCheckLevel(int level) {
        if (level < 0) {
            level = 0;
        }
        if (level > 18) {
            level = 18;
        }
        return level;
    }

    /**
     * Draws the appropriate buffer image. If the buffer image is null, it first creates
     * the buffer image and then renders it. This on demand strategy can have better
     * performance than attempting to fill the whole buffer.
     *
     * @param e The MapPaintArgs argument with information about the Map control and the
     * Graphics2D drawing surface.
     */
    public void paint(MapPaintArgs e) {
        frame = new GeoFrame(e.getFrame());
        int bLevel = boundCheckLevel(level);
        setContent(e.getMap().getContent());
        if (images[bLevel] == null) {
            createBuffer(bLevel);
        }
        if (images[bLevel] != null) {
            e.getGraphics().drawImage(images[bLevel].getDataset().getImage(), 0, 0, null);
        }
    }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
    /**
     * Gets the MapContent that is the collection of layers to be drawn.
     *
     * @return the content
     */
    public MapContent getContent() {
        return content;
    }

    /**
     * Sets the MapContent that is the collection of layers to be drawn.
     *
     * @param content the content to set
     */
    public void setContent(MapContent content) {
        this.content = content;
    }

    /**
     * Gets the integer level of the current level shown on the map as a result of the
     * sliding of the track bar.
     *
     * @return the integer level.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets the integer level of the current level shown on the map as a result of the
     * sliding of the track bar.
     *
     * @param level the level to set
     */
    public void setLevel(int level) {
        this.level = level;
    }

  //</editor-fold>
}
