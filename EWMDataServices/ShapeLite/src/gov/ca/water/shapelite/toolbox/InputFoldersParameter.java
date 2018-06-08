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
public class InputFoldersParameter extends PathsParameter {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    private String extension;

    //</editor-fold>
    /**
     * Creates a new instance of the ShapefileInputParameter class.
     */
    public InputFoldersParameter() {
        this.setParameterType(ParameterType.InputFolders);
    }

    //<editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * Creates the GUI panel that should be added to the tool dialog.
     *
     * @return
     */
    @Override
    public InputFoldersParameterPanel createPanel() {
        return new InputFoldersParameterPanel(this);
    }

    /**
     * Gets a boolean that determines if the file exists.
     *
     * @param path
     * @return
     */
    public boolean exists(String path) {
        if (path != null && !path.isEmpty()) {
            File f = new File(path);
            return f.exists();
        }
        return false;
    }

    @Override
    public ParameterStatus getStatus(String path) {
        ParameterStatus result = ParameterStatus.Good;
        if (path == null || path.isEmpty()) {
            result = ParameterStatus.Error;
            return result;
        }
        if (!exists(path)) {
            result = ParameterStatus.Error;
            return result;
        }
        return result;
    }

    @Override
    public ParameterStatus getStatus() {
        ParameterStatus result = ParameterStatus.Good;
        setValidationMessage(getDescription());
        if (this.getParameterText() == null || this.getParameterText().isEmpty()) {
            setValidationMessage("A folder name for " + getParameterName() + " is null.");
            result = ParameterStatus.Error;
            return result;
        }
        for (String path : this.getPaths()) {
            if (!exists(path)) {
                setValidationMessage("A folder " + path + " could not be found.");
                result = ParameterStatus.Error;
                return result;
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

    //</editor-fold>
}
