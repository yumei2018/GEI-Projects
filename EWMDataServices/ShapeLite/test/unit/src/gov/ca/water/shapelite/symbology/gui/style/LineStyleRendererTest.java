/*
 * The MIT License
 *
 * Copyright 2016 Harold A. Dunsford Jr. Ph.D..
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
package gov.ca.water.shapelite.symbology.gui.style;

import gov.ca.water.shapelite.symbology.LineStyle;
import gov.ca.water.shapelite.symbology.gui.style.resources.StyleImages;
import gov.ca.water.shapelite.testutils.ImageCompare;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class LineStyleRendererTest {

  public LineStyleRendererTest() {
  }

  /**
   * Test of getListCellRendererComponent method, of class LineStyleRenderer.
   * @throws java.io.IOException
   */
  @Test
  public void testGetListCellRendererComponent() throws IOException {
    System.out.println("getListCellRendererComponent - solid");
    JList list = new JList();
    LineStyle value = LineStyle.solid;
    int index = 0;
    boolean isSelected = false;
    boolean cellHasFocus = false;
    LineStyleRenderer instance = new LineStyleRenderer();
    Component result = instance.getListCellRendererComponent(list, value, index,
        isSelected, cellHasFocus);
    JLabel label = (JLabel) result;
    ImageIcon icon = (ImageIcon) label.getIcon();
    BufferedImage img = (BufferedImage) icon.getImage();
    assertEquals(100, img.getWidth());
    assertEquals(16, img.getHeight());

    assertTrue(ImageCompare.areEqual(img, StyleImages.get("testSolid.png").get()));


  }

  /**
   * Test of getListCellRendererComponent method, of class LineStyleRenderer.
   * @throws java.io.IOException
   */
  @Test
  public void testGetListCellRendererComponentDash() throws IOException {
    System.out.println("getListCellRendererComponent - dash");
    JList list = new JList();
    LineStyle value = LineStyle.dashed;
    int index = 0;
    boolean isSelected = false;
    boolean cellHasFocus = false;
    LineStyleRenderer instance = new LineStyleRenderer();
    Component result = instance.getListCellRendererComponent(list, value, index,
        isSelected, cellHasFocus);
    JLabel label = (JLabel) result;
    ImageIcon icon = (ImageIcon) label.getIcon();
    BufferedImage img = (BufferedImage) icon.getImage();
    assertEquals(100, img.getWidth());
    assertEquals(16, img.getHeight());
     assertTrue(ImageCompare.areEqual(img, StyleImages.get("testDash.png").get()));

  }

  /**
   * Test of getListCellRendererComponent method, of class LineStyleRenderer.
   * @throws java.io.IOException
   */
  @Test
  public void testGetListCellRendererComponentDot() throws IOException {
    System.out.println("getListCellRendererComponent - dot");
    JList list = new JList();
    LineStyle value = LineStyle.dotted;
    int index = 0;
    boolean isSelected = false;
    boolean cellHasFocus = false;
    LineStyleRenderer instance = new LineStyleRenderer();
    Component result = instance.getListCellRendererComponent(list, value, index,
        isSelected, cellHasFocus);
    JLabel label = (JLabel) result;
    ImageIcon icon = (ImageIcon) label.getIcon();
    BufferedImage img = (BufferedImage) icon.getImage();
    assertEquals(100, img.getWidth());
    assertEquals(16, img.getHeight());
     assertTrue(ImageCompare.areEqual(img, StyleImages.get("testDot.png").get()));

  }

  /**
   * Test of getListCellRendererComponent method, of class LineStyleRenderer.
   * @throws java.io.IOException
   */
  @Test
  public void testGetListCellRendererComponentNotLinestyle() throws IOException {
    System.out.println("getListCellRendererComponent - dot");
    JList list = new JList();
    Object value = "NotStyle";
    int index = 0;
    boolean isSelected = false;
    boolean cellHasFocus = false;
    LineStyleRenderer instance = new LineStyleRenderer();
    Component result = instance.getListCellRendererComponent(list, value, index,
        isSelected, cellHasFocus);
    JLabel label = (JLabel) result;

    ImageIcon expResult = null;
    assertEquals(expResult, (ImageIcon) label.getIcon());
  }

  /**
   * Test of getListCellRendererComponent method, of class LineStyleRenderer.
   * @throws java.io.IOException
   */
  @Test
  public void testGetListCellRendererComponentSelected() throws IOException {
    System.out.println("getListCellRendererComponent - selected");
    JList list = new JList();
    LineStyle value = LineStyle.solid;
    int index = 0;
    boolean isSelected = true;
    boolean cellHasFocus = false;
    LineStyleRenderer instance = new LineStyleRenderer();
    Component result = instance.getListCellRendererComponent(list, value, index,
        isSelected, cellHasFocus);
    JLabel label = (JLabel) result;
    ImageIcon icon = (ImageIcon) label.getIcon();
    BufferedImage img = (BufferedImage) icon.getImage();
    assertEquals(100, img.getWidth());
    assertEquals(16, img.getHeight());
     assertTrue(ImageCompare.areEqual(img, StyleImages.get("testSelected.png").get()));


  }


}
