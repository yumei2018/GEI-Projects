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
package gov.ca.water.shapelite.progress;

import gov.ca.water.shapelite.events.ProgressChangedEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ProgressTracker implements ProgressCountableCancellable {

  /**
   * The maximum percent.
   */
  private final static int PERCENT = 100;

  /**
   * For a countable currentUnit, this is the total number of work units.
   */
  private int numWorkUnits;

  /**
   * The previous percentage shown.
   */
  private int oldPercent;

  /**
   * A cancellable.
   */
  private Cancellable cancellable;

  /**
   * The integer current currentUnit.
   */
  private int currentUnit;

  /**
   * The task name.
   */
  private String taskName;

  /**
   * Tracks whether the progress is indeterminate.
   */
  private boolean indeterminate;

  /**
   * The string detailed message.
   */
  private String message;

  /**
   * Boolean, true if the task has been finished.
   */
  private boolean finished;

  /**
   * Boolean, true if the user should have the ability to cancel.
   */
  private boolean canBeCanceled;

  /**
   * Boolean, true if the user has tried to cancel this task.
   */
  private boolean canceled;

  /**
   * Starts the progress tool with the specified number of work units.
   *
   * @param workUnits The integer number of items to be handled.
   */
  @Override
  public final void start(int workUnits) {
    numWorkUnits = workUnits;
    currentUnit = 0;
    fireProgressChanged(new ProgressChangedEvent(this, this.getMessage(), getPercent()));
  }

  /**
   * Sets the progress relative the the original workUnits.
   *
   * @param currentUnit The integer current unit.
   */
  @Override
  public final void progress(int currentUnit) {
    this.currentUnit = currentUnit;
    fireProgressChanged(new ProgressChangedEvent(this, this.getMessage(),
        getPercent()));
  }

  /**
   * The String task name for this task. This is the major text.
   *
   * @param taskName The String task name.
   */
  @Override
  public final void create(String taskName) {
    this.taskName = taskName;
  }

  /**
   * Starts the indeterminate progress indicator.
   */
  @Override
  public final void start() {
    indeterminate = true;
    this.finished = false;
    fireProgressChanged(new ProgressChangedEvent(this));
  }

  /**
   * Updates the detailed progress message to show the latest progress.
   *
   * @param message The String message.
   */
  @Override
  public final void progress(String message) {
    this.message = message;
    fireProgressChanged(new ProgressChangedEvent(this, message));
  }

  /**
   * Finishes the progress for this task.
   */
  @Override
  public final void finish() {
    this.finished = true;
    fireProgressChanged(new ProgressChangedEvent(this));
  }

  /**
   * Creates a new instance of a tracker to be tracked.
   *
   * @param taskName
   * @param canBeCanceled
   */
  @Override
  public final void create(String taskName, boolean canBeCanceled) {
    this.taskName = taskName;
    this.canBeCanceled = canBeCanceled;
  }

  /**
   *
   * @param taskName
   * @param cancellable
   */
  @Override
  public final void create(String taskName, Cancellable cancellable) {
    this.taskName = taskName;
    this.canBeCanceled = true;
    this.cancellable = cancellable;
  }

  /**
   * Gets a boolean that is true if the task should be canceled.
   *
   * @return Boolean, true if the user has canceled the task.
   */
  @Override
  public final boolean isCanceled() {
    return this.canceled;
  }

  /**
   * If this is indeterminate, this returns -1. Otherwise, this will be an
   * integer from 0 to 100.
   *
   * @return The integer percent.
   */
  public final int getPercent() {
    if (this.indeterminate || this.numWorkUnits == 0) {
      return -1;
    }
    return (PERCENT * this.currentUnit) / this.numWorkUnits;
  }


  //<editor-fold defaultstate="collapsed" desc="ProgressChangedEvent">
  /**
   * The list of listeners.
   */
  private final List<ProgressChangedEvent.Listener> progressChangedListeners
      = new ArrayList<>();

  /**
   * Adds the specified listener to the list of listeners to be notified during
   * an event. If the item is already in the list, it will not be added a second
   * time.
   *
   * @param listener The ProgressChangedEvent.Listener to connect.
   */
  public final void addProgressChangedListener(
      ProgressChangedEvent.Listener listener) {
    if (!progressChangedListeners.contains(listener)) {
      progressChangedListeners.add(listener);
    }
  }

  /**
   * Removes the specified listener from the list if it is in the list.
   *
   * @param listener The ProgressChangedEvent.Listener to disconnect.
   */
  public final void removeProgressChangedListener(
      ProgressChangedEvent.Listener listener) {
    progressChangedListeners.remove(listener);
  }

  /**
   * Fires the ProgressChanged event and notifies each of the listeners.
   *
   * @param e A {EventType}Event with the source object and any properties
   * associated with this event.
   */
  public final void fireProgressChanged(ProgressChangedEvent e) {
    for (ProgressChangedEvent.Listener listener : progressChangedListeners) {
      listener.progressChanged(e);
    }
  }

  //</editor-fold>
  // <editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the total number of work units for a countable task.
   *
   * @return the numWorkUnits
   */
  public final int getNumWorkUnits() {
    return numWorkUnits;
  }

  /**
   * Sets the total number of work units for a countable task.
   *
   * @param numWorkUnits the numWorkUnits to set
   */
  public final void setNumWorkUnits(int numWorkUnits) {
    this.numWorkUnits = numWorkUnits;
  }

  /**
   * Gets an interface to manage cancellation.
   *
   * @return the cancellable
   */
  public final Cancellable getCancellable() {
    return cancellable;
  }

  /**
   * Sets an interface to manage cancellation.
   *
   * @param cancellable the cancellable to set
   */
  public final void setCancellable(Cancellable cancellable) {
    this.cancellable = cancellable;
  }

  /**
   * Gets the integer work unit that has most recently been processed.
   *
   * @return the currentUnit
   */
  public final int getCurrentUnit() {
    return currentUnit;
  }

  /**
   * Sets the integer work unit that has most recently been processed.
   *
   * @param currentUnit the currentUnit to set
   */
  public final void setCurrentUnit(int currentUnit) {
    this.currentUnit = currentUnit;
  }

  /**
   * Gets the string task name for this progress task.
   *
   * @return the taskName
   */
  public final String getTaskName() {
    return taskName;
  }

  /**
   * Sets the String task name for this progress task.
   *
   * @param taskName the taskName to set
   */
  public final void setTaskName(String taskName) {
    this.taskName = taskName;
  }

  /**
   * Gets a boolean that is true if this is does not have a countable progress.
   *
   * @return the indeterminate
   */
  public final boolean isIndeterminate() {
    return indeterminate;
  }

  /**
   * Sets a boolean that is true if this is does not have a countable progress.
   *
   * @param indeterminate the indeterminate to set
   */
  public final void setIndeterminate(boolean indeterminate) {
    this.indeterminate = indeterminate;
  }

  /**
   * Gets the most recent string detailed message.
   *
   * @return the message
   */
  public final String getMessage() {
    return message;
  }

  /**
   * Sets the most recent string detailed message.
   *
   * @param message the message to set
   */
  public final void setMessage(String message) {
    this.message = message;
  }

  /**
   * Gets a boolean that is true if this task should be finished executing.
   *
   * @return the finished
   */
  public final boolean isFinished() {
    return finished;
  }

  /**
   * Sets a boolean that is true if this task should be finished executing.
   *
   * @param finished the finished to set
   */
  public final void setFinished(boolean finished) {
    this.finished = finished;
  }

  /**
   * Gets a boolean that is true if the user should be able to cancel this task.
   *
   * @return the canBeCanceled
   */
  public final boolean canBeCanceled() {
    return canBeCanceled;
  }

  /**
   * Sets a boolean that is true if the user should be able to cancel this task.
   *
   * @param canBeCanceled the canBeCanceled to set
   */
  public final void setCanBeCanceled(boolean canBeCanceled) {
    this.canBeCanceled = canBeCanceled;
  }

  /**
   * Sets a boolean that is true if this task has been canceled.
   *
   * @param canceled the canceled to set
   */
  public final void setCanceled(boolean canceled) {
    this.canceled = canceled;
  }

  /**
   * Gets the previous percentage shown.
   *
   * @return the oldPercent
   */
  public final int getOldPercent() {
    return oldPercent;
  }

  /**
   * Sets the previous percentage shown.
   *
   * @param oldPercent the oldPercent to set
   */
  public final void setOldPercent(int oldPercent) {
    this.oldPercent = oldPercent;
  }

  // </editor-fold>
}
