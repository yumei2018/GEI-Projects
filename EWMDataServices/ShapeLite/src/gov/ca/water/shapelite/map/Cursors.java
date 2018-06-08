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
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.HashMap;

/**
 * This class is a shared singleton class that provides rapid access to cursors.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class Cursors {

  /**
   * The private HashMap set of cursors that can be looked up by name.
   */
  private final HashMap<String, Cursor> cursors;

  /**
   * Private constructor prevents instantiation from other classes, and is part
   * of the Singleton pattern.
   */
  private Cursors() {
    cursors = new HashMap<>();
  }

  /**
   * SingletonHolder is loaded on the first execution of Singleton.getInstance()
   * or the first access to SingletonHolder.INSTANCE, not before.
   */
  private static class SingletonHolder {

    /**
     * Cursors instance.
     */
    public static final Cursors INSTANCE = new Cursors();
  }

  /**
   * Gets the single shared instance of the Cursors collection of Cursor
   * objects.
   *
   * @return The Cursors class.
   */
  public static Cursors getInstance() {
    return SingletonHolder.INSTANCE;
  }

  /**
   * Gets an open hand cursor.
   *
   * @return The cursor in the open hand configuration.
   */
  public Optional<Cursor> handCursor() {
    return getCursor("Hand", 15, 17);
  }

  /**
   * Gets a closed hand cursor.
   *
   * @return The cursor in the closed hand configuration.
   */
  public Optional<Cursor> handClosedCursor() {
    return getCursor("HandClosed", 16, 15);
  }

  /**
   * Gets the Cursor for a specified string name, with the hot-spot defined at
   * the x and y locations within the image.
   *
   * @param name The string name of the 32x32 png image to load, without the
   * 32.png ending, which will be appended. So "Hand" becomes the filename:
   * "Hand32.png"
   * @param x The x coordinate of the hot spot.
   * @param y The y coordinate of the hot spot.
   * @return A Cursor object based on the specified string.
   */
  public Optional<Cursor> getCursor(String name, int x, int y) {
    if (!cursors.containsKey(name)) {
      Optional<Image> img = Images.getInstance().get(name + "32.png");
      if (img.isPresent()) {
        Point hotSpot = new Point(x, y);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Cursor cursor = toolkit.createCustomCursor(img.get(), hotSpot, name);
        cursors.put(name, cursor);
      }
    }
    if(cursors.containsKey(name)){
      return Optional.of(cursors.get(name));
    }
    return Optional.empty();
  }

}
