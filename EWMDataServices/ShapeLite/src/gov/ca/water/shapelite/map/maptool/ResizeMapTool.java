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
package gov.ca.water.shapelite.map.maptool;

import gov.ca.water.shapelite.events.MapEventDisplay;
import gov.ca.water.shapelite.events.MapEventComponent;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.data.Projector;
import gov.ca.water.shapelite.map.MapPaintArgs;
import gov.ca.water.shapelite.map.MapPanel;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ResizeMapTool extends MapTool {

    BufferedImage image;
    private boolean needsUpdate;

    @Override
    public void paint(MapPaintArgs args) {
        if (!needsUpdate) {
            return;
        }
    // The goal is not to simply stretch, but rather to estimate the stretching
        // so that features appear approximately in the correct2D places, aspect ratio wise.
        Projector proj = new Projector(args.getFrame());

    // This should be the proportions for the old extent in the new rectangle
        // in the same basic proportion that will ultimately be assigned to the map
        // once rendering is completed.
        Rectangle view = args.getFrame().getView();
        if (view.width == 0 || view.height == 0) {
            return;
        }
        if (args.getMap().getContent().getDisplayEnvelope() != null) {
            Envelope displayEnv = args.getMap().getContent().getDisplayEnvelope();
            Rectangle dest = proj.getRectangle(displayEnv);
            args.getGraphics().drawImage(image, dest.x, dest.y, dest.width, dest.height, null);
        }
        // redraw the content for real
        args.getMap().getContent().invalidate(view);
    }

    @Override
    public void componentResized(MapEventComponent e) {
        e.getMap().getContent().setVisible(false);
        this.setVisible(true);
        image = e.getMap().getContent().getDisplay();
        Rectangle view = new Rectangle(0, 0, e.getMap().getWidth(),
            e.getMap().getHeight());
        // First, ensure the envelope is the original display envelope
        Envelope old = e.getMap().getContent().getDisplayEnvelope();
        if (!old.isEmpty()) {
            e.getMap().getContent().setEnvelope(old);
        }
        e.getMap().getContent().setView(view);

        // Now resize the envelope to fit the new view.
        resetEnvelope(e.getMap());
        needsUpdate = true;
        e.getMap().setUpdatingContent(true);
        //e.getMap().paintImmediately(view);
        e.getMap().repaint();
    }

    @Override
    public void renderComplete(MapEventDisplay e) {
        if (needsUpdate) {
            e.getMap().getContent().setVisible(true);
            this.setVisible(false);
            e.getMap().paintImmediately(new Rectangle(0, 0, e.getMap().getWidth(),
                    e.getMap().getHeight()));
            e.getMap().setUpdatingContent(false);
        }
        needsUpdate = false;
    }

    /**
     * Adjusts the envelope to account for a new view. This is automatically
     * called each time setView is called.
     *
     * @param map
     */
    public void resetEnvelope(MapPanel map) {
        Envelope bounds = map.getContent().getMaxEnvelope();
        Rectangle view = map.getContent().getView();
        Envelope envelope = map.getContent().getEnvelopeMercator();
     // Regardless of zoom scale, behavior depends on the aspect ratio of the maximum
        // zoom of the content.  If the full content were fit exactly into the view, then
        // we reduced the width, the envelope needs to get taller.

        double geoAspect = bounds.getWidth() / bounds.getHeight();
        if (view.width == 0 || view.height == 0) {
            return;
        }
        double aspect = view.width / (double) view.height;

        if (geoAspect < aspect) {
            // original data is taller than current data, so modify width.
            double half = envelope.getHeight() / 2 * aspect;
            double mid = (envelope.getMin().getX() + envelope.getMax().getX()) / 2;
            double min = mid - half;
            double max = mid + half;
            envelope.getMin().setX(min);
            envelope.getMax().setX(max);
        } else {
            double half = envelope.getWidth() / (aspect * 2);
            double mid = (envelope.getMin().getY() + envelope.getMax().getY()) / 2;
            double min = mid - half;
            double max = mid + half;
            envelope.getMin().setY(min);
            envelope.getMax().setY(max);
        }
    }

}
