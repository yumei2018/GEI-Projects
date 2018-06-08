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

import java.util.Date;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ProgressConsole implements Progress {

  /**
   * Milliseconds per hour.
   */
  private static final int MS_PER_HOUR = 3600000;

  /**
   * Milliseconds per minute.
   */
  private static final int MS_PER_MINUTE = 60000;

  /**
   * Milliseconds per second.
   */
  private static final int MS_PER_SECOND = 1000;
  
  /**
   * The date that the process was started.
   */
  private Date startTime;

  /**
   * The String name describing the current task.
   */
  private String currentTask;

  /**
   * Creates a new instance of the task itself.
   *
   * @param taskName the String task name.
   */
  @Override
  public final void create(String taskName) {
    currentTask = taskName;
  }

  /**
   * Starts the process.
   */
  @Override
  public final void start() {
    startTime = new Date();
    System.out.println("Starting " + getCurrentTask() + " at " + startTime.toString());
  }

  /**
   * Optionally gives a status message clarifying the sub progress.
   *
   * @param message The String message to post.
   */
  @Override
  public final void progress(String message) {
    System.out.println(getCurrentTask() + ": " + message);
  }

  /**
   * Finishes the task.
   */
  @Override
  public final void finish() {
    Date endTime = new Date();
    System.out.println("Finished " + getCurrentTask() + " at " + endTime);
    if (startTime != null) {
      long tms = endTime.getTime() - startTime.getTime();
      String elapsed = "";
      if (tms > MS_PER_HOUR) {
        elapsed += tms / MS_PER_HOUR + " hours, ";
        tms = tms % MS_PER_HOUR;
      }
      if (tms > MS_PER_MINUTE) {
        elapsed += tms / MS_PER_MINUTE + " minutes, ";
        tms = tms % MS_PER_MINUTE;
      }
      if (tms > MS_PER_SECOND) {
        elapsed += tms / MS_PER_SECOND + " seconds, ";
        tms = tms % MS_PER_SECOND;
      }
      elapsed += tms + " milliseconds";

      System.out.println("Elapsed time: " + elapsed);
    }
  }

  /**
   * Gets the current task for this progress indicator.
   * @return the currentTask
   */
  public final String getCurrentTask() {
    return currentTask;
  }

}
