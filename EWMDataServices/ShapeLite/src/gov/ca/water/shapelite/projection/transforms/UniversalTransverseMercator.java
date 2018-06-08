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
public class UniversalTransverseMercator extends TransverseMercator {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  //</editor-fold>
  /**
   * Creates a new instance of the UniversalTransverseMercator class.
   */
  public UniversalTransverseMercator() {
    super.setProj4Name("utm");
  }

  @Override
  protected void onInit(ProjectionInfo projInfo) throws ProjectionException {
    Y0 = projInfo.isSouth() ? 10000000 : 0;
    projInfo.setFalseNorthing(Y0);
    X0 = 500000;
    projInfo.setFalseEasting(X0);
    int zone;
    if (projInfo.Zone != null) {
      zone = projInfo.Zone;
      if (zone <= 0 || zone > 60) {
        throw new ProjectionException(35);
      }
      zone--;
    } else {
      zone = (int) Math.floor((Proj.Adjlon(Lam0) + Math.PI) * 30 / Math.PI);
      if (zone < 0) {
        zone = 0;
      }
      if (zone >= 60) {
        zone = 59;
      }
    }
    Lam0 = (zone + .5) * Math.PI / 30.00 - Math.PI;
    projInfo.setLam0(Lam0);
    K0 = 0.9996;
    Phi0 = 0;
    super.onInit(projInfo);
  }

}
