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

import gov.ca.water.shapelite.events.MapEventComponent;
import gov.ca.water.shapelite.map.element.PanSelectButtonMapElement;
import java.awt.Rectangle;

/**
 * This MapTool simply is a container for the MapElementPanButtons that do the actual work
 * of panning.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PanSelectButtonMapTool extends MapTool {

    // The actual button element that appears on the map.
    PanSelectButtonMapElement element;

    /**
     * creates a new instance of the MapToolPanButtons class.
     */
    public PanSelectButtonMapTool() {
        element = new PanSelectButtonMapElement();
        element.setBoundsFrom(new Rectangle(1000, 75, 40, 40));
        this.getElements().add(element);
    }

    @Override
    public void componentResized(MapEventComponent e) {
        element.getBounds().x = e.getMap().getWidth() - 50;
    }

}
