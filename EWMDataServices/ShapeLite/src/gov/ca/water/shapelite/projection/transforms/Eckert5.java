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

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class Eckert5 extends Transform{

    //<editor-fold defaultstate="collapsed" desc="Fields">

        private static final double XF = 0.44101277172455148219;
        private static final double RXF = 2.26750802723822639137;
        private static final double YF = 0.88202554344910296438;
        private static final double RYF = 1.13375401361911319568;

    //</editor-fold>

    /**
     * Creates a new instance of the Eckert5 class.
     */
    public Eckert5(){
              setProj4Name("eck5");
            setEsriName("Eckert_V");
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
                xy[x] = XF * (1 + Math.cos(lp[phi])) * lp[lam];
                xy[y] = YF * lp[phi];
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
                lp[lam] = RXF * xy[x] / (1 + Math.cos(lp[phi] = RYF * xy[y]));
            }
        }

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
  


    //</editor-fold>

}
