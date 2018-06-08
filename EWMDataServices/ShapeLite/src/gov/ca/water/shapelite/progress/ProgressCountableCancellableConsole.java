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
public class ProgressCountableCancellableConsole extends ProgressCountableConsole
    implements ProgressCountableCancellable {

  /**
   * The cancellable.
   */
  private Cancellable cancellable;

  /**
   * boolean tracks cancellation.
   */
  private boolean canceled;

  /**
   * If this can be canceled.
   */
  private boolean canBeCanceled;

  /**
   * Creates a new progress instance with the specified cancel.
   *
   * @param taskName the String task name.
   * @param canBeCanceled True if cancellation is supported, but no special
   * cancelable is defined.
   */
  @Override
  public final void create(String taskName, boolean canBeCanceled) {
    super.create(taskName);
    cancellable = null;
    canceled = false;
    this.canBeCanceled = canBeCanceled;

  }

  /**
   * Creates a new progress instance with the specified cancel.
   *
   * @param taskName the String task name.
   * @param cancel The Cancellable interface to allow cancellation.
   */
  @Override
  public final void create(String taskName, Cancellable cancel) {
    super.create(taskName);
    cancellable = cancel;
    canceled = false;
  }

  /**
   * Gets the interface that can be used to cancel the operation.
   *
   * @return the cancellable interface.
   */
  public final Cancellable getCancellable() {
    return cancellable;
  }

  @Override
  public final boolean isCanceled() {
    return canceled;
  }

  /**
   * Cancels the progress.
   */
  public final void cancel() {

    if (this.cancellable != null) {
      canceled = this.cancellable.cancel();
    } else {
      canceled = true;
    }

  }

}
