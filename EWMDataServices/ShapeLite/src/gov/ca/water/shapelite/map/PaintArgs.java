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
import gov.ca.water.shapelite.data.DataFrame;
import java.awt.Graphics2D;
import java.util.EventObject;

/**
 * An argument class that hosts a Graphics2D drawing surface, as well as a DataFrame,
 * which describes how to transform a geographic envelope to a rectangle.
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PaintArgs extends EventObject {
  
  //<editor-fold defaultstate="collapsed" desc="Private Fields">
  /**
   * The Graphics2D graphics object that is the rendering interface.
   */
  private Graphics2D graphics;
  
  /**
   * The DataFrame that is used for translating between world coordinates and screen
   * coordinates.
   */
  private DataFrame dataFrame;
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Creates a new instance of the PaintArgs class.
   * @param graphics The Graphics2D drawing surface.
   * @param dataFrame The DataFrame for projecting from pixel coordinates to geographic coordinates.
   */
  public PaintArgs(Object source, Graphics2D graphics, DataFrame dataFrame) {
    super(source);
    this.graphics = graphics;
    this.dataFrame = dataFrame;
  }
  //</editor-fold>  
    
  //<editor-fold defaultstate="collapsed" desc="Protected Methods">
  /**
   * Sets the DataFrame for converting between the rectangular pixel view and
   * the geographic envelope.
   * @param frame the frame to set.
   */
  protected void setFrame(DataFrame frame) {
    this.dataFrame = frame;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Methods">
  /**
   * Gets the drawing surface.
   * @return the graphics object.
   */
  public Graphics2D getGraphics() {
    return graphics;
  }
  
  /**
   * Sets the Graphics2D drawing surface.
   * @param graphics the graphics to set
   */
  protected void setGraphics(Graphics2D graphics) {
    this.graphics = graphics;
  }
    
  /**
   * Gets the DataFrame for converting between the rectangular pixel view and
   * the geographic envelope.
   * @return the frame.
   */
  public DataFrame getFrame() {
    return this.dataFrame;
  }
  //</editor-fold>
}
