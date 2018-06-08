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

import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class HammerAitoff extends Transform {

    //<editor-fold defaultstate="collapsed" desc="Fields">

        private double _m;
        private double _rm;
        private double _w;

    //</editor-fold>

    /**
     * Creates a new instance of the HammerAitoff class.
     */
    public HammerAitoff(){
              setProj4Name("hammer");
            setEsriName("Hammer_Aitoff");
    }

    //<editor-fold defaultstate="collapsed" desc="Methods">
  
       /**   {@inheritDoc }     */
        @Override protected void onForward(double[] lp, double[] xy, int startIndex, int numPoints)
        {
            for (int i = startIndex; i < startIndex + numPoints; i++)
            {
                int phi = i * 2 + PHI;
                int lam = i * 2 + LAMBDA;
                int x = i * 2 + X;
                int y = i * 2 + Y;
                double cosphi;
                double d = Math.sqrt(2 / (1 + (cosphi = Math.cos(lp[phi])) * Math.cos(lp[lam] *= _w)));
                xy[x] = _m * d * cosphi * Math.sin(lp[lam]);
                xy[y] = _rm * d * Math.sin(lp[phi]);
            }
        }

        /// <summary>
        /// Initializes the transform using the parameters from the specified coordinate system information
        /// </summary>
        /// <param name="projInfo">A ProjectionInfo class contains all the standard and custom parameters needed to initialize this transform</param>
        @Override protected void onInit(ProjectionInfo projInfo) throws ProjectionException
        {
            if (projInfo.w != null)
            {
                _w = projInfo.w;
                if (_w <= 0) throw new ProjectionException(27);
            }
            else
            {
                _w = .5;
            }
            if (projInfo.getM() != null)
            {
                _m = projInfo.getM();
                if (_m <= 0) throw new ProjectionException(27);
            }
            else
            {
                _m = 1;
            }
            _rm = 1 / _m;
            _m /= _w;
        }

    //</editor-fold>


}
