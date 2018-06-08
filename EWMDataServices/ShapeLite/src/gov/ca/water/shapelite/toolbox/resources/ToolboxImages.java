/*
 * The MIT License
 *
 * Copyright 2015 hdunsford.
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
package gov.ca.water.shapelite.toolbox.resources;

import gov.ca.water.shapelite.map.Images;
import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ToolboxImages {
    
    /**
   * A HashMap where images are stored by string name keys.
   */
  private final HashMap<String, Image> imagecache;
    
    private ToolboxImages() {
        imagecache = new HashMap<>();
    }
    
    public static ToolboxImages getInstance() {
        return ToolboxImagesHolder.INSTANCE;
    }
    
    private static class ToolboxImagesHolder {

        private static final ToolboxImages INSTANCE = new ToolboxImages();
    }
    
    /**
   * Gets an in memory Image given the image name, and loads it in the image cache.
   * @param name The string name of the image to get.
   * @return The Image loaded by this method.
   */
  public Image get(String name) {
    if (imagecache.containsKey(name)) {
      return imagecache.get(name);
    }
    Image img;
    try {
      img = ImageIO.read(this.getClass().getResourceAsStream(name));
      imagecache.put(name, img);
      return img;
    } catch (IOException ex) {
      Logger.getLogger(Images.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }
}
