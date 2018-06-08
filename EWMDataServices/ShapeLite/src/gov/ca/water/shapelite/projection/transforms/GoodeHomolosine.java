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
public class GoodeHomolosine extends Transform {

    //<editor-fold defaultstate="collapsed" desc="Fields">

        private static final double Y_COR = 0.05280;
        private static final double PHI_LIM = .71093078197902358062;
        private Mollweide _moll;
        private Sinusoidal _sinu;

    //</editor-fold>

    /**
     * Creates a new instance of the GoodeHomolosine class.
     */
    public GoodeHomolosine(){
              setProj4Name("goode");
            setEsriName("Goode_Homolosine");
    }

    //<editor-fold defaultstate="collapsed" desc="Methods">
  
       /**   {@inheritDoc }     */
        @Override protected void onForward(double[] lp, double[] xy, int startIndex, int numPoints)
        {
            System.arraycopy(lp, startIndex * 2, xy, startIndex * 2, numPoints * 2);
            for (int i = startIndex; i < startIndex + numPoints; i++)
            {
                int phi = i * 2 + PHI;
                //int lam = i * 2 + Lambda;
                //int x = i * 2 + X;
                int y = i * 2 + Y;
                if (Math.abs(lp[PHI]) <= PHI_LIM)
                {
                    _sinu.forward(xy, i * 2, 1);
                }
                else
                {
                    _moll.forward(xy, i * 2, 1);
                    xy[y] -= lp[phi] >= 0 ? Y_COR : -Y_COR;
                }
            }
        }

        /**   {@inheritDoc }     */
        @Override protected void onInverse(double[] xy, double[] lp, int startIndex, int numPoints)
        {
            System.arraycopy(lp, startIndex * 2, xy, startIndex * 2, numPoints * 2);
            for (int i = startIndex; i < startIndex + numPoints; i++)
            {
                //int phi = i*2 + Phi;
                //int lam = i*2 + Lambda;
                //int x = i*2 + X;
                int y = i * 2 + Y;
                if (Math.abs(xy[Y]) <= PHI_LIM)
                {
                    _sinu.inverse(xy, i * 2, 1);
                }
                else
                {
                    xy[y] += xy[y] >= 0 ? Y_COR : -Y_COR;
                    _moll.inverse(xy, i * 2, 1);
                }
            }
        }

        /// <summary>
        /// Initializes the transform using the parameters from the specified coordinate system information
        /// </summary>
        /// <param name="projInfo">A ProjectionInfo class contains all the standard and custom parameters needed to initialize this transform</param>
        @Override protected void onInit(ProjectionInfo projInfo) throws ProjectionException
        {
            _moll = new Mollweide();
            _moll.init(projInfo);
            _sinu = new Sinusoidal();
            _sinu.init(projInfo);
        }

    //</editor-fold>


}
