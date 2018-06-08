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
public class ProgressCountableConsole extends ProgressConsole
        implements ProgressCountable {

  /**
   * The integer total number of work units.
   */
  private int unitCount;

  /**
   * Start the unit count.
   *
   * @param unitCount Integer count.
   */
  @Override
  public final void start(int unitCount) {
    super.start();
    System.out.println(super.getCurrentTask() + " 0/" + unitCount);
  }

  /**
   * Updates the progress using the specified unit count.
   *
   * @param currentUnit The integer work unit at the current time.
   */
  @Override
  public final void progress(int currentUnit) {
    System.out.println(super.getCurrentTask() + " " + currentUnit + "/" + unitCount);
  }

}
