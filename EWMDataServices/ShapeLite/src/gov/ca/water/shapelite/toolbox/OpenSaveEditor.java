/*
 * The MIT License
 *
 * Copyright 2012 hdunsford.
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

import java.beans.PropertyEditorSupport;
import java.util.Arrays;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class OpenSaveEditor extends PropertyEditorSupport {

  private final String[] tags = new String[]{"OPEN", "SAVE"};

  @Override
  public String getAsText() {
    Integer val = (Integer) this.getValue();
    if (val < 0 || val > tags.length) {
      return "INVALID";
    }
    return tags[val];

  }

  @Override
  public void setAsText(String text) throws IllegalArgumentException {
    int index = Arrays.asList(tags).indexOf(text);
    if (index > -1) {
      this.setValue(index);
    }
    firePropertyChange();
  }

  @Override
  public String[] getTags() {
    return tags;
  }

  @Override
  public String getJavaInitializationString() {
    switch (((Number) getValue()).intValue()) {
      default:
      case FileInputPanel.OPEN:
        return "gov.ca.water.file.FileInputPanel.OPEN";
      case FileInputPanel.SAVE:
        return "gov.ca.water.file.FileInputPanel.SAVE";
    }
  }
}
