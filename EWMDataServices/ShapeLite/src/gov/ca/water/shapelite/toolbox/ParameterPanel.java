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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public abstract class ParameterPanel extends JPanel {

  /**
   * The list of listeners.
   */
    private final List<HelpListener> helpListeners = new ArrayList<>();

    /**
     * Click listener.
     */
    protected final MouseListener helpClickListener = new MouseAdapter() {

        @Override
        public void mouseClicked(MouseEvent me) {
            showHelp();
        }

        @Override
        public void mousePressed(MouseEvent me) {
            showHelp();
        }
    };

    /**
     * Creates a new ParameterPanel instance.
     */
    public ParameterPanel() {
        super.addMouseListener(helpClickListener);
    }

    /**
     * Ensures the specified listener will receive the help event.
     *
     * @param listener The listener to connect.
     */
    public final void addHelpListener(HelpListener listener) {
        helpListeners.add(listener);
    }

    /**
     * Removes the specified listener form the list if it is in the list.
     *
     * @param listener The listener to disconnect.
     */
    public final void removeHelpListener(HelpListener listener) {
        helpListeners.remove(listener);
    }

    /**
     * Fires showHelp using the paramter from this class.
     */
    protected final void showHelp() {
        showHelp(new HelpEvent(ParameterPanel.this, getParameter().getHelpImage(),
            getParameter().getHelpText(), getParameter().getParameterName()));
    }

    /**
     * Fires the showHelp event.
     *
     * @param e An EventArgument with the help content to show.
     */
    protected final void showHelp(HelpEvent e) {
        for (HelpListener listener : helpListeners) {
            listener.showHelp(e);
        }
    }

    /**
     * Gets the parameter used by this panel.
     *
     * @return A Parameter object.
     */
    abstract Parameter getParameter();

    /**
     * Sets the parameter being used by this panel.
     *
     * @param param The Parameter object to set.
     */
    abstract void setParameter(Parameter param);

}
