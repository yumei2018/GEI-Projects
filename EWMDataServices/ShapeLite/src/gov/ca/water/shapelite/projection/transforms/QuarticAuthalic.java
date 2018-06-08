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

import gov.ca.water.shapelite.projection.ProjectionInfo;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class QuarticAuthalic extends Transform {

    //<editor-fold defaultstate="collapsed" desc="Fields">

        private double _cP;
        private double _cX;
        private double _cY;
        private boolean _tanMode;

    //</editor-fold>

    /**
     * Creates a new instance of the QuarticAuthalic class.
     */
    public QuarticAuthalic(){
              setProj4Name("qua_aut");
            setEsriName("Quartic_Authalic");
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
                xy[x] = _cX * lp[lam] * Math.cos(lp[phi]);
                xy[y] = _cY;
                lp[phi] *= _cP;
                double c = Math.cos(lp[phi]);
                if (_tanMode)
                {
                    xy[x] *= c * c;
                    xy[y] *= Math.tan(lp[phi]);
                }
                else
                {
                    xy[x] /= c;
                    xy[y] *= Math.sin(lp[phi]);
                }
            }
        }

        /**   {@inheritDoc }     */
        @Override protected void onInverse(double[] xy, double[] lp, int startIndex, int numPoints)
        {
            for (int i = startIndex; i < startIndex + numPoints; i++)
            {
                int phi = i * 2 + PHI;
                int lam = i * 2 + LAMBDA;
                int x = i * 2 + X;
                int y = i * 2 + Y;
                xy[y] /= _cY;
                double c = Math.cos(lp[phi] = _tanMode ? Math.atan(xy[y]) : Proj.Aasin(xy[y]));
                lp[phi] /= _cP;
                lp[lam] = xy[x] / (_cX * Math.cos(lp[phi] /= _cP));
                if (_tanMode)
                    lp[lam] /= c * c;
                else
                    lp[lam] *= c;
            }
        }

        /// <summary>
        /// Initializes the transform using the parameters from the specified coordinate system information
        /// </summary>
        /// <param name="projInfo">A ProjectionInfo class contains all the standard and custom parameters needed to initialize this transform</param>
        @Override protected void onInit(ProjectionInfo projInfo)
        {
            Setup(2, 2, false);
        }

        /// <summary>
        /// Setup
        /// </summary>
        /// <param name="p"></param>
        /// <param name="q"></param>
        /// <param name="mode"></param>
        protected void Setup(double p, double q, boolean mode)
        {
            _cX = q / p;
            _cY = p;
            _cP = 1 / q;
            _tanMode = mode;
        }

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
  


    //</editor-fold>

}
