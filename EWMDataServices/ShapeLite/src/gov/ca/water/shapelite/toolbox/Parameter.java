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

import gov.ca.water.shapelite.events.ParameterChangedEvent;
import gov.ca.water.shapelite.utils.StringUtils;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.imageio.ImageIO;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class Parameter {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  private String parameterName;
  private String parameterText;
  private String description;
  private String validationMessage;
  private ParameterType parameterType;
  private boolean required;
  private String helpImageFilename;
  private BufferedImage helpImage;
  private String helpText;
  private String settingsKey;
  Preferences preferences;

  //</editor-fold>
  /**
   * Creates a new instance of the Parameter class.
   */
  public Parameter() {
    preferences = Preferences.userRoot();
    required = true;
  }

  //<editor-fold defaultstate="collapsed" desc="ParameterChangedEvent">
  /**
   * The list of listeners.
   */
  private final List<ParameterChangedEvent.Listener> parameterChangeListeners
      = new ArrayList<>();

  /**
   * Adds the specified listener to the list of listeners to be notified during
   * an event. If the item is already in the list, it will not be added a second
   * time.
   *
   * @param listener The ParameterChangedEvent.Listener to connect.
   */
  public final void addParameterChangedListener(
      ParameterChangedEvent.Listener listener) {
    if (!parameterChangeListeners.contains(listener)) {
      parameterChangeListeners.add(listener);
    }
  }

  /**
   * Removes the specified listener from the list if it is in the list.
   *
   * @param listener The ParameterChangedEvent.Listener to disconnect.
   */
  public final void removeParameterChangedListener(
      ParameterChangedEvent.Listener listener) {
    parameterChangeListeners.remove(listener);
  }

  /**
   * Fires the ParameterChanged event and notifies each of the listeners.
   *
   * @param e A {EventType}Event with the source object and any properties
   * associated with this event.
   */
  public final void fireParameterChanged(ParameterChangedEvent e) {
    for (ParameterChangedEvent.Listener listener : parameterChangeListeners) {
      listener.parameterChanged(e);
    }
  }

  //</editor-fold>
  /**
   * The array list of event listeners for the status changes.
   */
  private final List<ParameterStatusListener> eventListeners = new ArrayList<>();

  /**
   * Ensures the specified listener will receive the help event.
   *
   * @param listener The listener to connect.
   */
  public final void addStatusListener(ParameterStatusListener listener) {
    eventListeners.add(listener);
  }

  /**
   * Removes the specified listener form the list if it is in the list.
   *
   * @param listener The listener to disconnect.
   */
  public final void removeStatusListener(ParameterStatusListener listener) {
    eventListeners.remove(listener);
  }

  /**
   * Fires the showHelp event.
   *
   * @param e An EventArgument with the help content to show.
   */
  protected final void fireStatusChanged(ParameterStatusEvent e) {
    for (ParameterStatusListener listener : eventListeners) {
      listener.statusChanged(e);
    }
  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * This occurs right before the panel is actually displayed, by which time,
   * settings from the constructor may have been updated.
   */
  public void panelOpened() {

  }

  /**
   * If the parameter text is invalid for some reason, then the GUI should
   * illustrate that and provide access to the validationMessage.
   *
   * @return boolean true if the value is valid.
   */
  public ParameterStatus getStatus() {
    ParameterStatus result;
    if (this.parameterText == null || this.parameterText.isEmpty()) {
      if (this.isRequired()) {
        result = ParameterStatus.Error;
        this.setValidationMessage(getParameterName() + " is null.");
        return result;
      }
    }
    result = ParameterStatus.Good;  // By default, no validation is implemented and the value is always valid.
    this.setValidationMessage(this.description);

    return result;

  }

  /**
   * If this is false, then the process should not be able to run. Optional
   * parameters return true, even if the parameter has some kind of error.
   *
   * @return Boolean, true if this process should run.
   */
  public boolean isValid() {
    if (this.isRequired()) {
      return getStatus() != ParameterStatus.Error;
    } else {
      return true;
    }
  }

  /**
   * This is called by the ToolBase class to force parameters to actually load
   * their image files. This is only used if the helpImageFilename property is
   * not null.
   */
  public void loadImages() {
    if (this.helpImageFilename != null) {
      InputStream stream = this.getClass().getResourceAsStream(this.helpImageFilename);
      if (stream != null) {
        try {
          this.helpImage = ImageIO.read(stream);
        } catch (IOException ex) {
          Logger.getLogger(Parameter.class.getName()).log(
              Level.SEVERE, ex.getMessage(), ex);
        }
      }
    }
  }

  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Implemented in type specific subclasses.
   *
   * @return
   */
  public ParameterPanel createPanel() {
    return new StringParameterPanel();
  }

  /**
   * @return the parameterName
   */
  public String getParameterName() {
    return parameterName;
  }

  /**
   * @param parameterName the parameterName to set
   */
  public void setParameterName(String parameterName) {
    this.parameterName = parameterName;
  }

  /**
   * @return the parameterText
   */
  public String getParameterText() {
    return parameterText;
  }

  /**
   * @param parameterText the parameterText to set.
   */
  public void setParameterText(String parameterText) {
    String oldText = this.parameterText;
    ParameterStatus oldStatus = this.getStatus();
    this.parameterText = parameterText;
    if (this.settingsKey != null) {
      preferences.put("SHAPELITE_" + settingsKey, parameterText);
    }
    ParameterStatus status = this.getStatus();

    if (status != oldStatus) {
      fireStatusChanged(new ParameterStatusEvent(this, status));
    }
    if (!StringUtils.equals(parameterText, oldText)) {
      fireParameterChanged(new ParameterChangedEvent(this, parameterText));
    }
  }

  /**
   * This defines the memory key to load the parameter value as a setting. If
   * the value is not defined in the NbPreferences, then the specified default
   * text will be used instead.
   *
   * @param settingsKey The String key to use for the settings of this
   * parameter.
   * @param defaultText The default text to use for the setting.
   */
  public final void setParameterText(String settingsKey, String defaultText) {
    this.settingsKey = settingsKey;
    setParameterText(preferences.get("SHAPELITE_" + settingsKey, defaultText));

  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @return the parameterType
   */
  public ParameterType getParameterType() {
    return parameterType;
  }

  /**
   * @param parameterType the parameterType to set
   */
  public void setParameterType(ParameterType parameterType) {
    this.parameterType = parameterType;
  }

  /**
   * @return the validationMessage
   */
  public String getValidationMessage() {
    return validationMessage;
  }

  /**
   * @param validationMessage the validationMessage to set
   */
  public void setValidationMessage(String validationMessage) {
    this.validationMessage = validationMessage;
  }

  /**
   * @return the required
   */
  public boolean isRequired() {
    return required;
  }

  /**
   * @param required the required to set
   */
  public void setRequired(boolean required) {
    this.required = required;
  }

  /**
   * @return the helpImage
   */
  public BufferedImage getHelpImage() {
    return helpImage;
  }

  /**
   * @param helpImage the helpImage to set
   */
  public void setHelpImage(BufferedImage helpImage) {
    this.helpImage = helpImage;
  }

  /**
   * @return the helpText
   */
  public String getHelpText() {
    return helpText;
  }

  /**
   * @param helpText the helpText to set
   */
  public void setHelpText(String helpText) {
    this.helpText = helpText;
  }

  /**
   * @return the helpImageFilename
   */
  public String getHelpImageFilename() {
    return helpImageFilename;
  }

  /**
   * @param helpImageFilename the helpImageFilename to set
   */
  public void setHelpImageFilename(String helpImageFilename) {
    this.helpImageFilename = helpImageFilename;
  }

  //</editor-fold>
}
