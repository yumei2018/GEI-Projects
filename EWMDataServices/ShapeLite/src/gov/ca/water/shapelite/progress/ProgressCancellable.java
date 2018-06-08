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
package gov.ca.water.shapelite.progress;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public interface ProgressCancellable extends Progress {

  /**
   * Creates a progress utility that allows for cancellation from the GUI.
   * @param taskName The String task name that will appear in the GUI.
   * @param canBeCanceled True if the user has a way to try to cancel this.
   * This should control the "isCanceled" property, even if a Cancellable
   * is not specified.
   */
  void create(String taskName, boolean canBeCanceled);

  /**
   * Creates a progress utility that allows for cancellation from the GUI.
   * @param taskName The String task name that will appear in the GUI.
   * @param cancellable The Cancellable interface that handles what happens
   * when the user tries to cancel.
   */
  void create(String taskName, Cancellable cancellable);

  /**
   * A boolean that is true if the action has been canceled.
   * @return Boolean, true if canceled.
   */
  boolean isCanceled();

}


