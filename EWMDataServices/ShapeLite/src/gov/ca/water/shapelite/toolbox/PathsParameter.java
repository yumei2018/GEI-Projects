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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This supports the ability to convert the parameter into a list of paths.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PathsParameter extends Parameter {

    public List<String> getPaths() {
        String text = this.getParameterText();
        List<String> result = new ArrayList<>();
        if (text != null && !text.isEmpty()) {
            String[] paths = text.split(";");
            result.addAll(Arrays.asList(paths));
        }
        return result;

    }

    public void setPaths(List<String> paths) {
        String result = "";
        if (paths != null) {
            for (int i = 0; i < paths.size(); i++) {
                if (i > 0) {
                    result += ";";
                }
                result += paths.get(i);
            }
        }
        if(!result.isEmpty()){
            this.setParameterText(result);
        }
        else{
            this.setParameterText(null);
        }
    }

    public void add(String path) {
        List<String> paths = getPaths();
        paths.add(path);
        this.setPaths(paths);
    }

    public void removeAt(int index) {
        List<String> paths = getPaths();
        paths.remove(index);
        this.setPaths(paths);
    }

    public ParameterStatus getStatus(String path) {
        ParameterStatus result = ParameterStatus.Good;
        setValidationMessage(getDescription());
        if (path == null || path.isEmpty()) {
            setValidationMessage("A folder name for " + getParameterName() + " is null.");
            result = ParameterStatus.Error;
            return result;
        }
        return result;
    }
}
