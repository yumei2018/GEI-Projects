/*
 * The MIT License
 *
 * Copyright 2014 hdunsford.
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
package gov.ca.water.shapelite.toolbox;

import gov.ca.water.shapelite.events.ComboOptionsChangedEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ComboParameter extends Parameter {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The list of options.
   */
  private final List<Object> options;

  /**
   * The selected option.
   */
  private Object selectedOption;

  //</editor-fold>
  /**
   * Creates a new instance of the DoubleParameter class.
   */
  public ComboParameter() {
    options = new ArrayList<>();
  }

  //<editor-fold defaultstate="collapsed" desc="ComboOptionsChangedEvent">
  /**
   * The list of listeners.
   */
  private final List<ComboOptionsChangedEvent.Listener> optionsChangedListeners
      = new ArrayList<>();

  /**
   * Adds the specified listener to the list of listeners to be notified during
   * an event. If the item is already in the list, it will not be added a second
   * time.
   *
   * @param listener The ComboOptionsChangedEvent.Listener to connect.
   */
  public final void addComboOptionsChangedListener(ComboOptionsChangedEvent.Listener listener) {
    if (!optionsChangedListeners.contains(listener)) {
      optionsChangedListeners.add(listener);
    }
  }

  /**
   * Removes the specified listener from the list if it is in the list.
   *
   * @param listener The ComboOptionsChangedEvent.Listener to disconnect.
   */
  public final void removeComboOptionsChangedListener(ComboOptionsChangedEvent.Listener listener) {
    optionsChangedListeners.remove(listener);
  }

  /**
   * Fires the ComboOptionsChanged event and notifies each of the listeners.
   *
   * @param e A {EventType}Event with the source object and any properties
   * associated with this event.
   */
  public final void fireComboOptionsChanged(ComboOptionsChangedEvent e) {
    for (ComboOptionsChangedEvent.Listener listener : optionsChangedListeners) {
      listener.optionsChanged(e);
    }
  }

  /**
   * Fires the combo options changed with the current options.
   */
  public final void fireComboOptionsChanged() {
    List<String> items = new ArrayList<>();
    for (Object item : this.options) {
      items.add(item.toString());
    }
    fireComboOptionsChanged(new ComboOptionsChangedEvent(this, items));
  }

  //</editor-fold>
  /**
   * Creates a new panel based on the options in this parameter.
   *
   * @return A ComboParameterPanel.
   */
  @Override
  public final ComboParameterPanel createPanel() {

    return new ComboParameterPanel(this);
  }

  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * @return the options
   */
  public final List<Object> getOptions() {
    return options;
  }

  /**
   * @return the selectedOption
   */
  public final Object getSelectedOption() {
    return selectedOption;
  }

  /**
   * @param selectedOption the selectedOption to set
   */
  public final void setSelectedOption(Object selectedOption) {
    this.selectedOption = selectedOption;
    if (selectedOption == null) {
      setParameterText(null);
    } else {
      setParameterText(selectedOption.toString());
    }

  }

  //</editor-fold>
}
