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
package gov.ca.water.shapelite.symbology.gui;

import gov.ca.water.shapelite.events.AlphaChangedEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.GroupLayout;
import static javax.swing.GroupLayout.Alignment.LEADING;
import static javax.swing.GroupLayout.Alignment.TRAILING;
import static javax.swing.GroupLayout.DEFAULT_SIZE;
import static javax.swing.GroupLayout.PREFERRED_SIZE;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import static javax.swing.LayoutStyle.ComponentPlacement.RELATED;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * This class is a panel with an associated slider and text representation that
 * are designed for showing transparency in a sliding control.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class TransparencySlider extends JPanel {

  /**
   * The maximum alpha is the maximum byte value.
   */
  private static final int MAX_ALPHA = 255;

  /**
   * The integer width of the slider control.
   */
  private static final int SLIDER_WIDTH = 345;

  /**
   * The slider that controls transparency.
   */
  private JSlider sliderTransparency;
  /**
   * The text field representation of the transparency.
   */
  private JTextField textTransparency;
  /**
   * Boolean, true if the text needs to be updated, and slider changes should be
   * ignored.
   */
  private boolean ignoreTextChanges;
  /**
   * Boolean, true if the slider needs to be updated, and the text should be
   * ignored.
   */
  private boolean ignoreSliderChanges;
  /**
   * The integer alpha value for the transparency, from 0 to 255, where 0 is
   * transparent and 255 is opaque.
   */
  private int alpha;

  /**
   * Scroll pane allows textbox to be stretchable vertically.
   */
  private javax.swing.JScrollPane textScrollPane;

  /**
   * Creates a new instance of the Transparency Panel.
   */
  public TransparencySlider() {
    initComponents();
  }

  //<editor-fold defaultstate="collapsed" desc="AlphaChangedEvent">
  /**
   * The list of listeners for the AlphaChanged event.
   */
  private final List<AlphaChangedEvent.Listener> alphaChangedListeners
      = new ArrayList<>();

  /**
   * Adds the specified listener to the list of listeners to be notified during
   * an event. If the item is already in the list, it will not be added a second
   * time.
   *
   * @param listener The AlphaChangedEvent.Listener to connect.
   */
  public final void addAlphaChangedListener(AlphaChangedEvent.Listener listener) {
    if (!alphaChangedListeners.contains(listener)) {
      alphaChangedListeners.add(listener);
    }
  }

  /**
   * Removes the specified listener from the list if it is in the list.
   *
   * @param listener The AlphaChangedEvent.Listener to disconnect.
   */
  public final void removeAlphaChangedListener(AlphaChangedEvent.Listener listener) {
    alphaChangedListeners.remove(listener);
  }

  /**
   * Fires the AlphaChanged event and notifies each of the listeners.
   *
   * @param e A {EventType}Event with the source object and any properties
   * associated with this event.
   */
  public final void fireAlphaChanged(AlphaChangedEvent e) {
    for (AlphaChangedEvent.Listener listener : alphaChangedListeners) {
      listener.alphaChanged(e);
    }
  }

  //</editor-fold>
  /**
   * initializes the position of the components in the transparency panel.
   */
  private void initComponents() {
    sliderTransparency = new JSlider();
    sliderTransparency.setValue(MAX_ALPHA);
    textTransparency = new JTextField();
    textTransparency.setText("255");
    alpha = MAX_ALPHA;
    sliderTransparency.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        if (ignoreSliderChanges) {
          return;
        }
        ignoreTextChanges = true;
        alpha = sliderTransparency.getValue();
        textTransparency.setText(Integer.toString(alpha));
        ignoreTextChanges = false;
        fireAlphaChanged(new AlphaChangedEvent(TransparencySlider.this, alpha));
      }
    });

    textTransparency.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent e) {
        updateSlider();
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        updateSlider();
      }

      @Override
      public void changedUpdate(DocumentEvent e) {
        updateSlider();
      }
    });
    textTransparency.addFocusListener(new FocusAdapter() {
      @Override
      public void focusLost(FocusEvent e) {
        // go ahead and ensure the text reverts to a working value if invalid.
        ignoreTextChanges = true;
        textTransparency.setText(Integer.toString(sliderTransparency.getValue()));
        ignoreTextChanges = false;
      }
    });

    textScrollPane = new javax.swing.JScrollPane();
    textScrollPane.setViewportView(textTransparency);

    GroupLayout layout = new GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(LEADING)
        .addGroup(TRAILING, layout.createSequentialGroup()
            .addComponent(sliderTransparency, DEFAULT_SIZE, 278, Short.MAX_VALUE)
            .addPreferredGap(RELATED)
            .addComponent(textScrollPane, PREFERRED_SIZE, 34, PREFERRED_SIZE))
    );
    layout.setVerticalGroup(layout.createParallelGroup(LEADING)
        .addComponent(textScrollPane)
        .addComponent(sliderTransparency, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
    );
  }

  /**
   * Updates the slider position based on a typed text value.
   */
  private void updateSlider() {
    if (ignoreTextChanges) {
      return;
    }
    String text = textTransparency.getText();
    try {
      Integer val = Integer.parseInt(text);
      if (val > MAX_ALPHA) {
        return;
      }
      if (val < 0) {
        return;
      }
      ignoreSliderChanges = true;
      alpha = val;
      sliderTransparency.setValue(alpha);
      ignoreSliderChanges = false;
      fireAlphaChanged(new AlphaChangedEvent(this, alpha));

    } catch (NumberFormatException ex) {
      // do nothing.
    }

  }

  /**
   * Gets the integer alpha value for the transparency, from 0 to 255, where 0
   * is transparent and 255 is opaque.
   *
   * @return the alpha
   */
  public final int getAlpha() {
    return alpha;
  }

  /**
   * Sets the integer alpha value for the transparency, from 0 to 255, where 0
   * is transparent and 255 is opaque.
   *
   * @param alpha the alpha to set
   */
  public final void setAlpha(int alpha) {
    if (alpha < 0 || alpha > MAX_ALPHA) {
      throw new IllegalArgumentException("The alpha value of " + alpha
          + " was invalid and should be between 0 and " + MAX_ALPHA + ".");
    }
    ignoreSliderChanges = true;
    ignoreTextChanges = true;
    this.alpha = alpha;
    this.textTransparency.setText(Integer.toString(alpha));
    this.sliderTransparency.setValue(alpha);
    ignoreSliderChanges = false;
    ignoreTextChanges = false;
  }

}
