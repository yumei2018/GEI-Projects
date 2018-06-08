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
package gov.ca.water.shapelite.projection.transforms;

import gov.ca.water.shapelite.projection.Factors;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public interface Transformable extends Cloneable {

    //<editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * Initializes the parameters from the projection info
     *
     * @param proj The projection information used to control this transform
     */
    public void init(ProjectionInfo proj) throws ProjectionException;

    /**
     * Transformables all the coordinates by cycling through them in a loop, transforming
     * each one. Only the 0 and 1 values of each coordinate will be transformed, higher
     * dimensional values will be copied.
     *
     * @param xy
     * @param startIndex
     * @param numPoints
     */
    public void forward(double[] xy, int startIndex, int numPoints);

    /**
     * Transformables all the coordinates by cycling through them in a loop, transforming
     * each one. Only the 0 and 1 values of each coordinate will be transformed, higher
     * dimensional values will be copied.
     *
     * @param xy
     * @param startIndex
     * @param numPoints
     */
    public void inverse(double[] xy, int startIndex, int numPoints);

    /**
     * Special factor calculations for a factors calculation
     *
     * @param lp
     * @param p
     * @param fac
     */
    public void special(double[] lp, ProjectionInfo p, Factors fac);

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Properties">
    /**
     * Gets the string name of this projection. This should uniquely define the
     * projection, and controls what appears in the .prj files. This name is required.
     *
     * @return
     */
    public String getEsriName();

    /**
     * Sets the string name of this projection. This should uniquely define the
     * projection, and controls what appears in the .prj files. This name is required.
     *
     * @return
     */
    public void setEsriName(String name);

    /**
     * Gets the string name of this projection as it is known to proj4, or should appear
     * in a proj4 string. This name is required to read and write to proj4 strings.
     */
    public String getProj4Name();

    /**
     * Sets the string name of this projection as it is known to proj4, or should appear
     * in a proj4 string. This name is required to read and write to proj4 strings.
     *
     * @param proj4Name
     */
    public void setProj4Name(String proj4Name);

    /**
     * This is the list of alternate names to check besides the Proj4Name. This will not
     * be used for writing Proj4 strings, but may be helpful for reading them.
     *
     * @return
     */
    String[] getProj4Aliases();

    /**
     * This is a list of alternate names that can be retrieved for a projection, but will
     * not be used when saving the projection to a string.
     *
     * @return
     */
    String[] getEsriAliases();

    /**
     * Gets a copy of this transformable object.
     *
     * @return
     */
    public Transformable copy();

    //</editor-fold>
}
