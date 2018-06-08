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

import java.awt.image.BufferedImage;
import java.util.EventObject;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class HelpEvent extends EventObject {
    
    private BufferedImage helpImage;
    private String helpText;
    private String title;
    
    /**
     * Creates a new instance of a HelpEvent clarifying the help content that
     * should be displayed in the dialog.
     * @param sender 
     * @param helpImage
     * @param helpText 
     * @param title 
     */
    public HelpEvent(Object sender, BufferedImage helpImage, String helpText, String title){
        super(sender);
        this.helpImage = helpImage;
        this.helpText = helpText;
        this.title = title;
    }

    /**
     * @return the helpImage
     */
    public BufferedImage getHelpImage() {
        return helpImage;
    }

    /**
     * @param helpImage the helpImage to set
     */
    public void setHelpImage(BufferedImage helpImage) {
        this.helpImage = helpImage;
    }

    /**
     * @return the helpText
     */
    public String getHelpText() {
        return helpText;
    }

    /**
     * @param helpText the helpText to set
     */
    public void setHelpText(String helpText) {
        this.helpText = helpText;
    }

    /**
     * @return the title
     */
    protected String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    protected void setTitle(String title) {
        this.title = title;
    }
}
