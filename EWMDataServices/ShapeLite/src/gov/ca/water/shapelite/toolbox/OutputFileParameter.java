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
public class OutputFileParameter extends Parameter {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    private String extension;
    private String fileTypeDescription;

    //</editor-fold>
    /**
     * Creates a new instance of the ShapefileInputParameter class.
     */
    public OutputFileParameter() {
        this.setParameterType(ParameterType.OutputFile);
    }

    //<editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * Creates the GUI panel that should be added to the tool dialog.
     *
     * @return
     */
    @Override
    public OutputFileParameterPanel createPanel() {
        return new OutputFileParameterPanel(this);
    }

    /**
     * Gets a boolean that determines if the file exists.
     *
     * @return
     */
    public boolean exists() {
        if (this.getParameterText() == null || this.getParameterText().isEmpty()) {
            return false;
        }
        File f = new File(this.getParameterText());
        return f.exists();
    }

    /**
     * Gets status performs validation, and may also do things like attempt to
     * create the parent folders for any output files.
     *
     * @return
     */
    @Override
    public ParameterStatus getStatus() {
        ParameterStatus result = ParameterStatus.Good;
        setValidationMessage(getDescription());
        if (exists()) {
            setValidationMessage("The file " + this.getParameterText() + " exists.  It will be overwritten.");
            result = ParameterStatus.Warning;
        }
        if (this.getParameterText() != null && !this.getParameterText().isEmpty()) {
            if (this.extension != null && !this.extension.isEmpty()) {
                if (!this.getParameterText().endsWith(extension)) {
                    setValidationMessage("The file name for " + this.getParameterName() + " should end with " + extension);
                    result = ParameterStatus.Warning;
                }
            }
        }
        if (this.getParameterText() == null || this.getParameterText().isEmpty()) {
            setValidationMessage("The output file for " + this.getParameterName() + " cannot be null.");
            result = ParameterStatus.Error;
        } else {
            File f = new File(this.getParameterText());
            File parent = f.getParentFile();
            if (parent != null) {
                if (!parent.exists()) {
                    if (!parent.mkdirs()) {
                        setValidationMessage("The output folder for " + this.getParameterName() + " does not exist and could not be created.");
                        result = ParameterStatus.Warning;
                    }
                }
            }

        }
        return result;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Properties">
    /**
     * @return the extension
     */
    public String getExtension() {
        return extension;
    }

    /**
     * @param extension the extension to set
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

    /**
     * @return the fileTypeDescription
     */
    protected String getFileTypeDescription() {
        return fileTypeDescription;
    }

    /**
     * @param fileTypeDescription the fileTypeDescription to set
     */
    protected void setFileTypeDescription(String fileTypeDescription) {
        this.fileTypeDescription = fileTypeDescription;
    }

     //</editor-fold>
}
