/*
 * The MIT License
 *
 * Copyright 2015 Harold A. Dunsford Jr. Ph.D..
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
package gov.ca.water.shapelite.legend.internal;

import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.legend.resources.LegendImages;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class LegendPopup extends JPopupMenu {

  /**
   * By default, the backup legend icon is 16x16.
   */
  private static final int ICON_SIZE = 16;

  /**
   * The properties menu item for showing symbology.
   */
  private final JMenuItem properties;

  /**
   * The menu item used to remove a layer from the map.
   */
  private final JMenuItem remove;

  /**
   * Creates a new instance of a Legend Popup Menu.
   */
  public LegendPopup() {
    Optional<BufferedImage> rem = LegendImages.get("deleteRed.png");
    BufferedImage remImage = rem.orElse(new BufferedImage(ICON_SIZE, ICON_SIZE,
        BufferedImage.TYPE_INT_ARGB));
    remove = new JMenuItem("Remove", new ImageIcon(remImage));
    super.add(remove);

    Optional<BufferedImage> img = LegendImages.get("Properties.png");
    BufferedImage propImage = img.orElse(new BufferedImage(ICON_SIZE, ICON_SIZE,
        BufferedImage.TYPE_INT_ARGB));
    properties = new JMenuItem("Properties", new ImageIcon(propImage));
    super.add(properties);


  }

  /**
   * Gets the properties menu item for showing symbology.
   *
   * @return the properties menu item.
   */
  public final JMenuItem getProperties() {
    return properties;
  }

  /**
   * Gets The menu item used to remove a layer from the map.
   *
   * @return the remove menu item.
   */
  public final JMenuItem getRemove() {
    return remove;
  }
}
