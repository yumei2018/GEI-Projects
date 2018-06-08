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

import gov.ca.water.shapelite.Optional;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 * This class is used to provide quick access to the images used in code for
 * cursors and other effects.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class Images {

  /**
   * A HashMap where images are stored by string name keys.
   */
  private final HashMap<String, Image> imagecache;

  /**
   * Private constructor prevents instantiation from other classes, part of the
   * singleton pattern.
   */
  private Images() {
    imagecache = new HashMap<>();
  }

  /**
   * SingletonHolder is loaded on the first execution of Singleton.getInstance()
   * or the first access to SingletonHolder.INSTANCE, not before.
   */
  private static class SingletonHolder {

    /**
     * The singleton instance.
     */
    public static final Images INSTANCE = new Images();
  }

  /**
   * Gets the single shared static instance of this class.
   *
   * @return The shared Images class.
   */
  public static Images getInstance() {
    return SingletonHolder.INSTANCE;
  }

  /**
   * Directions arrows.
   *
   * @return the Image of direction arrows.
   */
  public Optional<Image> directions()  {
    return this.get("Directions.png");
  }

  /**
   * A Plus icon.
   *
   * @return the Image of a plus sign.
   */
  public Optional<Image> plus()  {
    return this.get("plus.png");
  }

  /**
   * I Minus icon.
   *
   * @return the Image of a minus sign.
   */
  public Optional<Image> minus() {
    return this.get("Minus.png");
  }

  /**
   * A hand closed like it's grabbing something.
   *
   * @return The Image of a closed hand.

   */
  public Optional<Image> handClosed() {
    return this.get("HandClosed32.png");
  }

  /**
   * A hand that is opened.
   *
   * @return The Image of an open hand.
   */
  public Optional<Image> hand()  {
    return this.get("Hand32.png");
  }

  /**
   * Background image for a lit blue button.
   *
   * @return The Image for a lit blue button.
   */
  public Optional<Image> sketchLit(){
    return this.get("SketchBlueLit.png");
  }

  /**
   * Background image for a normal blue button.
   *
   * @return The Image for a button in the normal state.
   */
  public Optional<Image> sketchNormal()  {
    return this.get("SketchBlueNormal.png");
  }

  /**
   * Background image for a pressed blue button.
   *
   * @return The Image for a button in the pressed state.
   */
  public Optional<Image> sketchPressed() {
    return this.get("SketchBluePressed.png");
  }

  /**
   * Gets the satellite icon image for the button to toggle between tile layers.
   *
   * @return The Image.
   */
  public Optional<Image> satellite()  {
    return this.get("Satellite32.png");
  }

  /**
   * Gets the streetmap icon image for the button to toggle between tile layers.
   *
   * @return The Image.
   */
  public Optional<Image> streetmap() {
    return this.get("StreetMap32.png");
  }

  /**
   * Gets the topo map icon image for the button to toggle between tile layers.
   * @return The image.
   */
  public Optional<Image> topomap() {
    return this.get("Topo32.png");
  }

  /**
   * Gets an in memory Image given the image name, and loads it in the image
   * cache. If the named resource is not found this returns empty. If the
   * resource is found, but there was an error reading it, an exception will be
   * printed to system.out, and this will return empty.
   *
   * @param name The string name of the image to get.
   * @return The optional Image loaded by this method. The most likely problem
   * is that the named resource is not found, in which case, this returns
   * optional empty.
   */
  public Optional<Image> get(String name){
    if (imagecache.containsKey(name)) {
      return Optional.of(imagecache.get(name));
    }

    Image img = null;
    InputStream stream = this.getClass().getResourceAsStream("resources/" + name);
    if (stream == null) {
      return Optional.empty();
    }
    try {
      img = ImageIO.read(stream);
      imagecache.put(name, img);

    } catch (Exception ex) {
      System.out.println("Failed to load image " + name);
      ex.printStackTrace(System.out);
    }
    return Optional.ofNullable(img);

  }
}
