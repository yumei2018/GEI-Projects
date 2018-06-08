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
package gov.ca.water.shapelite.symbology.gui.color;

import gov.ca.water.shapelite.symbology.gui.color.ColorPanel;
import gov.ca.water.shapelite.events.ColorChangedEvent;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JDialog;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ColorPanelDialog extends JDialog {

  /**
   * The color panel to display on this frame.
   */
  private final ColorPanel colorPanel;

  /**
   * An adapter to track the parents movements.
   */
  private OwnerAdapter ownerAdapter;

  /**
   * Creates a new ColorPanelFrame instance.
   *
   * @param owner The dialog owner in case the control is on a modal dialog.
   * @param parentControl The component that this should stay anchored to.
   * @param x The x position relative to the owner.
   * @param y The y position relative to the owner.
   */
  public ColorPanelDialog(Window owner, Component parentControl, int x, int y) {
    super(owner);
    super.setUndecorated(true);
    super.setAlwaysOnTop(true);
    super.setFocusable(true);
    colorPanel = new ColorPanel();
    super.getContentPane().add(colorPanel);
    colorPanel.addColorChangedListener(new ColorChangedEvent.Listener() {
      @Override
      public void colorChanged(ColorChangedEvent e) {
        //ownerAdapter.disconnect();
        setVisible(false);
      }
    });
    super.setSize(colorPanel.getSize());
    //ownerAdapter = new OwnerAdapter(owner, x, y);
    super.setLocation(parentControl.getLocationOnScreen().x + x,
        parentControl.getLocationOnScreen().y + y);
    //owner.addComponentListener(ownerAdapter);
    Component cont = parentControl.getParent();
    while (cont != null) {
      //cont.addComponentListener(ownerAdapter);
      cont = cont.getParent();
    }
    super.addFocusListener(new FocusAdapter() {
      @Override
      public void focusLost(FocusEvent e) {
        //ownerAdapter.disconnect();
        if (colorPanel.isShowingDialog()) {
          return;
        }
        setVisible(false);
      }

    });

  }

  /**
   * Gets the color panel displayed on this frame.
   *
   * @return the colorPanel
   */
  public ColorPanel getColorPanel() {
    return colorPanel;
  }

  /**
   * Owner Adapter
   */
  private class OwnerAdapter extends ComponentAdapter {

    /**
     * The x offset relative to the parent.
     */
    private int x;
    /**
     * The y offset relative to the parent.
     */
    private int y;
    /**
     * The owner.
     */
    private Component owner;

    /**
     * The Owner Adapter.
     *
     * @param owner The owner component that this should stay anchored to.
     * @param x The x position relative to the owner.
     * @param y The y position relative to the owner.
     */
    public OwnerAdapter(Component owner, int x, int y) {
      this.owner = owner;
      this.x = x;
      this.y = y;
    }

    public OwnerAdapter() {
    }

    @Override
    public void componentShown(ComponentEvent e) {
      updateLocation(e.getComponent());
    }

    @Override
    public void componentResized(ComponentEvent e) {
      updateLocation(e.getComponent());
    }

    @Override
    public void componentHidden(ComponentEvent e) {
      ColorPanelDialog.this.setVisible(false);
    }

    @Override
    public void componentMoved(ComponentEvent e) {
      updateLocation(e.getComponent());
    }

    private void updateLocation(Component comp) {
      Point topLeft = owner.getLocationOnScreen();
      ColorPanelDialog.this.setLocation(topLeft.x + x, topLeft.y + y);
    }

    /**
     * Disconnects this listener from its owner.
     */
    private void disconnect() {
      this.owner.removeComponentListener(this);
    }
  }

}
