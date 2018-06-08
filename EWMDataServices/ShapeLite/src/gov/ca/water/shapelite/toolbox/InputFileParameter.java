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

import java.io.File;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class InputFileParameter extends Parameter {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The String extension with leading period to use as a file filter for the
   * parameter when browsing.
   */
  private String extension;

  /**
   * The file type description to use to clarify the file types in the dialog
   * when browsing.
   */
  private String fileTypeDescription;

  //</editor-fold>
  /**
   * Creates a new instance of the ShapefileInputParameter class.
   */
  public InputFileParameter() {
    this.setParameterType(ParameterType.InputFile);
  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Gets a boolean that determines if the file exists.
   *
   * @return Boolean, true if the file exists.
   */
  public final boolean exists() {
    if (this.getParameterText() != null && !this.getParameterText().isEmpty()) {
      File f = new File(this.getParameterText());
      return f.exists();
    }
    return false;
  }

  /**
   * Gets a ParameterStatus that indicates whether the current value is
   * acceptable for running the tool or not.
   *
   * @return A ParameterStatus enumeration.
   */
  @Override
  public final ParameterStatus getStatus() {
    ParameterStatus result = ParameterStatus.Good;
    setValidationMessage(getDescription());
    if (this.getParameterText() == null || this.getParameterText().isEmpty()) {
      setValidationMessage("The file name for " + getParameterName()
          + " is null.");
      result = ParameterStatus.Error;
      return result;
    }
    if (!exists()) {
      setValidationMessage("The file " + this.getParameterText()
          + " could not be found.");
      result = ParameterStatus.Error;
      return result;
    }
    if (this.extension != null && !this.extension.isEmpty()) {
      if (!this.getParameterText().endsWith(extension)) {
        setValidationMessage("The file name does not end with " + extension);
        result = ParameterStatus.Error;
        return result;
      }
    }

    return result;
  }

  @Override
  public final InputFileParameterPanel createPanel() {
    return new InputFileParameterPanel(this);
  }

  //</editor-fold>




  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the String extension with leading period to use as a file filter for
   * the parameter when browsing.
   *
   * @return the extension
   */
  public final String getExtension() {
    return extension;
  }

  /**
   * Sets the String extension with leading period to use as a file filter for
   * the parameter when browsing.
   *
   * @param extension the extension to set
   */
  public final void setExtension(String extension) {
    this.extension = extension;

  }

  //</editor-fold>
  /**
   * Gets the file type description to use to clarify the file types in the
   * dialog when browsing.
   *
   * @return the fileTypeDescription
   */
  public final String getFileTypeDescription() {
    return fileTypeDescription;
  }

  /**
   * Sets the file type description to use to clarify the file types in the
   * dialog when browsing.
   *
   * @param fileTypeDescription the fileTypeDescription to set
   */
  public final void setFileTypeDescription(String fileTypeDescription) {
    this.fileTypeDescription = fileTypeDescription;
  }

}