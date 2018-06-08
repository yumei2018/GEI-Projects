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
package gov.ca.water.shapelite.toolbox;

import gov.ca.water.shapelite.events.ToolFinishedEvent;
import gov.ca.water.shapelite.progress.ProgressCountableCancellable;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public interface Tool extends Runnable {

  /**
   * Adds the specified listener to the list of listeners to be notified during
   * an event. If the item is already in the list, it will not be added a second
   * time.
   *
   * @param listener The ToolFinishedEvent.Listener to connect.
   */
  void addToolFinishedListener(ToolFinishedEvent.Listener listener);

  /**
   * Removes the specified listener from the list if it is in the list.
   *
   * @param listener The ToolFinishedEvent.Listener to disconnect.
   */
  void removeToolFinishedListener(ToolFinishedEvent.Listener listener);

  /**
   * This method is called after the tool is constructed, allowing the tool to
   * load any images.
   */
  void loadImages();

  /**
   * @return the name
   */
  String getName();

  /**
   * @return the description
   */
  String getDescription();

  /**
   * @return the helpText
   */
  String getHelpText();

  /**
   * @return the icon
   */
  BufferedImage getIcon();

  /**
   * @return the paremters
   */
  List<Parameter> getParameters();

  /**
   * @return the category
   */
  String getCategory();

  /**
   * @return the helpImage
   */
  BufferedImage getHelpImage();

  /**
   * Occurs any time the panel is refreshed, allowing an update from settings.
   */
  void panelOpened();

  /**
   * Gets a boolean that if false, means the tool should not be run.
   * @return the enabled
   */
  boolean isEnabled();

  /**
   * Sets a boolean that if false, means the tool should not be run.
   * @param enabled the enabled to set
   */
  void setEnabled(boolean enabled);

  /**
   * Gets boolean that, if true, means that the tool should skip through
   * output files until it finds the latest unfinished output file.
   * @return the resumable
   */
  boolean isResumable();

  /**
   * Sets boolean that, if true, means that the tool should skip through
   * output files until it finds the latest unfinished output file.
   * @param resumable the resumable to set
   */
  void setResumable(boolean resumable);

  /**
   * Gets the progress handler.
   * @return A progress handler for processes.
   */
  ProgressCountableCancellable getProgress();

  /**
   * Sets the progress handler.
   * @param handler The progress handler.
   */
  void setProgress(ProgressCountableCancellable handler);

}
