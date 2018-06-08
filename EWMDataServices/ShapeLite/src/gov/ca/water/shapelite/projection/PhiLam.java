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

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PhiLam {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * Angular Y.
   */
  private double lambda;
  /**
   * Angular X.
   */
  private double phi;

  //</editor-fold>
  /**
   * Creates a new instance of the PhiLam class.
   */
  public PhiLam() {

  }

  /**
   * Creates a new instance of the PhiLam with specific values.
   * @param phi
   * @param lambda
   */
  public PhiLam(double phi, double lambda){
    this.phi = phi;
    this.lambda = lambda;
  }

  /**
   *
   * @return
   */
  public PhiLam copy(){
    PhiLam result = new PhiLam();
    result.copyProperties(this);
    return result;
  }

  /**
   * Copies the properties of this class.
   * @param original the original properties
   */
  public void copyProperties(PhiLam original){
    this.lambda = original.lambda;
    this.phi = original.phi;
  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  //</editor-fold>

  /**
   * @return the lambda
   */
  public double getLambda() {
    return lambda;
  }

  /**
   * @param lambda the lambda to set
   */
  public void setLambda(double lambda) {
    this.lambda = lambda;
  }

  /**
   * @return the phi
   */
  public double getPhi() {
    return phi;
  }

  /**
   * @param phi the phi to set
   */
  public void setPhi(double phi) {
    this.phi = phi;
  }
}
