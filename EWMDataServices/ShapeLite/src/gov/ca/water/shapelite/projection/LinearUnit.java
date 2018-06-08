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
public class LinearUnit {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    private double meters;
    private String name;
    private static final Logger LOGGER = Logger.getLogger(LinearUnit.class.getName());

    //</editor-fold>
    /**
     * Creates a new instance of the LinearUnit class.
     */
    public LinearUnit() {
        meters = 1;
        name = "Meter";
    }
    
    public LinearUnit(String name, double value){
        
    }

    //<editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * Generates the part of the Esri well known text for this linear unit
     *
     * @return A string that contains the name and conversion factor to meters
     */
    public String ToEsriString() {
        return "UNIT[\"" + getName() + "\"," + USLocale.format(getMeters()) + "]";
    }

    /**
     * Parses the UNIT member of Esri well known text into a linear unit
     *
     * @param esriString
     */
    public void ParseEsriString(String esriString) throws ProjectionException {
        if (esriString.contains("UNIT") == false) {
            return;
        }
        int iStart = esriString.indexOf("UNIT") + 5;
        int iEnd = esriString.indexOf("]", iStart);
        if (iEnd < iStart) {
            return;
        }
        String extracted = esriString.substring(iStart, iEnd);
        String[] terms = extracted.split(",");
        String unitName = terms[0];
        unitName = unitName.substring(1, unitName.length()-1);
        setName(unitName);
        try {
            setMeters(USLocale.parseDouble(terms[1]));
        } catch (ParseException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new ProjectionException("Linear Unit Parse exception.", ex);
        }
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Properties">
    //</editor-fold>
        /// <summary>
    /// Interprets a UOM code.  For instance 9001 = meters.
    /// These codes and ratios were from GDAL unit_of_measure.csv, and I'm not
    /// sure if the numeric code is used outside of GDAL internal references.
    /// </summary>
    /// <param name="uomCode"></param>
    public void ReadCode(int uomCode) {
        switch (uomCode) {
            case 9001:
                setName("Metre");
                setMeters(1);
                break;
            case 9002:
                setName("Foot");
                setMeters(0.3048);
                break;
            case 9003:
                setName("Us Survey Foot");
                setMeters(0.304800609601219);
                break;
            case 9005:
                setName("Clarke'S Foot");
                setMeters(0.3047972654);
                break;
            case 9014:
                setName("Fathom");
                setMeters(1.8288);
                break;
            case 9030:
                setName("Nautical Mile");
                setMeters(1852);
                break;
            case 9031:
                setName("German Legal Metre");
                setMeters(1.0000135965);
                break;
            case 9033:
                setName("Us Survey Chain");
                setMeters(20.1168402336805);
                break;
            case 9034:
                setName("Us Survey Link");
                setMeters(0.201168402336805);
                break;
            case 9035:
                setName("Us Survey Mile");
                setMeters(1609.34721869444);
                break;
            case 9036:
                setName("Kilometre");
                setMeters(1000);
                break;
            case 9037:
                setName("Clarke'S Yard");
                setMeters(0.9143917962);
                break;
            case 9038:
                setName("Clarke'S Chain");
                setMeters(20.1166195164);
                break;
            case 9039:
                setName("Clarke'S Link");
                setMeters(0.201166195164);
                break;
            case 9040:
                setName("British Yard (Sears 1922)");
                setMeters(0.914398414616029);
                break;
            case 9041:
                setName("British Foot (Sears 1922)");
                setMeters(0.304799471538676);
                break;
            case 9042:
                setName("British Chain (Sears 1922)");
                setMeters(20.1167651215526);
                break;
            case 9043:
                setName("British Link (Sears 1922)");
                setMeters(0.201167651215526);
                break;
            case 9050:
                setName("British Yard (Benoit 1895 A)");
                setMeters(0.9143992);
                break;
            case 9051:
                setName("British Foot (Benoit 1895 A)");
                setMeters(0.304799733333333);
                break;
            case 9052:
                setName("British Chain (Benoit 1895 A)");
                setMeters(20.1167824);
                break;
            case 9053:
                setName("British Link (Benoit 1895 A)");
                setMeters(0.201167824);
                break;
            case 9060:
                setName("British Yard (Benoit 1895 B)");
                setMeters(0.914399204289812);
                break;
            case 9061:
                setName("British Foot (Benoit 1895 B)");
                setMeters(0.304799734763271);
                break;
            case 9062:
                setName("British Chain (Benoit 1895 B)");
                setMeters(20.1167824943759);
                break;
            case 9063:
                setName("British Link (Benoit 1895 B)");
                setMeters(0.201167824943759);
                break;
            case 9070:
                setName("British Foot (1865)");
                setMeters(0.304800833333333);
                break;
            case 9080:
                setName("Indian Foot");
                setMeters(0.304799510248147);
                break;
            case 9081:
                setName("Indian Foot (1937)");
                setMeters(0.30479841);
                break;
            case 9082:
                setName("Indian Foot (1962)");
                setMeters(0.3047996);
                break;
            case 9083:
                setName("Indian Foot (1975)");
                setMeters(0.3047995);
                break;
            case 9084:
                setName("Indian Yard");
                setMeters(0.914398530744441);
                break;
            case 9085:
                setName("Indian Yard (1937)");
                setMeters(0.91439523);
                break;
            case 9086:
                setName("Indian Yard (1962)");
                setMeters(0.9143988);
                break;
            case 9087:
                setName("Indian Yard (1975)");
                setMeters(0.9143985);
                break;
            case 9093:
                setName("Statute Mile");
                setMeters(1609.344);
                break;
            case 9094:
                setName("Gold Coast Foot");
                setMeters(0.304799710181509);
                break;
            case 9095:
                setName("British Foot (1936)");
                setMeters(0.3048007491);
                break;
            case 9096:
                setName("Yard");
                setMeters(0.9144);
                break;
            case 9097:
                setName("Chain");
                setMeters(20.1168);
                break;
            case 9098:
                setName("Link");
                setMeters(0.201168);
                break;
            case 9099:
                setName("British Yard (Sears 1922 Truncated)");
                setMeters(0.914398);
                break;
            case 9204:
                setName("Bin Width 330 Us Survey Foot");
                setMeters(100.584201168402);
                break;
            case 9205:
                setName("Bin Width 165 Us Survey Foot");
                setMeters(50.2921005842012);
                break;
            case 9206:
                setName("Bin Width 82.5 Us Survey Foot");
                setMeters(25.1460502921006);
                break;
            case 9207:
                setName("Bin Width 37.5 Metre");
                setMeters(37.5);
                break;
            case 9208:
                setName("Bin Width 25 Metre");
                setMeters(25);
                break;
            case 9209:
                setName("Bin Width 12.5 Metre");
                setMeters(12.5);
                break;
            case 9210:
                setName("Bin Width 6.25 Metre");
                setMeters(6.25);
                break;
            case 9211:
                setName("Bin Width 3.125 Metre");
                setMeters(3.125);
                break;
            case 9300:
                setName("British Foot (Sears 1922 Truncated)");
                setMeters(0.304799333333333);
                break;
            case 9301:
                setName("British Chain (Sears 1922 Truncated)");
                setMeters(20.116756);
                break;
            case 9302:
                setName("British Link (Sears 1922 Truncated)");
                setMeters(0.20116756);
                break;
        }
    }

    /**
     * @return the meters
     */
    public double getMeters() {
        return meters;
    }

    /**
     * @param meters the meters to set
     */
    public void setMeters(double meters) {
        this.meters = meters;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
