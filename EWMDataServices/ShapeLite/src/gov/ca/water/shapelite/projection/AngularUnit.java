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
package gov.ca.water.shapelite.projection;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class AngularUnit extends CopyObject {

    private String name;
    private double radians;
    private static final Logger LOGGER = Logger.getLogger(AngularUnit.class.getName());
    /**
     * Creates a new instance of Unit
     */
    public AngularUnit() {
        radians = 0.017453292519943295; // assume degrees
        name = "Degree";
    }

    @Override
    public AngularUnit copy() {
        return (AngularUnit)super.copy();
    }
    
    
    

    /**
     * Generates the Esri string from the values in this class
     *
     * @return
     */
    public String ToEsriString() {
        return "UNIT[\"" + name + "\"," + USLocale.format(radians) + "]";
    }

    /**
     * Reads an esri string to determine the angular unit
     * @param esriString The esri string to read
     * @throws gov.ca.water.shapelite.projection.ProjectionException
     */
    public void ParseEsriString(String esriString) throws ProjectionException {
        if (esriString == null || esriString.isEmpty()) {
            return;
        }

        int iStart;
        iStart = esriString.indexOf("UNIT");
        if(iStart < 0){
            iStart = esriString.indexOf("unit");
        }
        iStart = iStart + 5;
        int iEnd = esriString.indexOf("]", iStart);
        if (iEnd < iStart) {
            return;
        }
        String extracted = esriString.substring(iStart, iEnd);
        String[] terms = extracted.split(",");
        setName(terms[0]);
        setName(getName().substring(1, getName().length() - 1));
        try {
            setRadians(USLocale.parseDouble(terms[1]));
            
        } catch (ParseException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new ProjectionException("AngularUnit parse exception.", ex);
        }
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param _name the name to set
     */
    public void setName(String _name) {
        this.name = _name;
    }

    /**
     * Gets the constant to multiply against this unit to get radians.
     *
     * @return the radians
     */
    public double getRadians() {
        return radians;
    }

    /**
     * Sets the constant to multiply against this unit to get radians.
     *
     * @param _radians the radians to set
     */
    public void setRadians(double _radians) {
        this.radians = _radians;
    }

}
