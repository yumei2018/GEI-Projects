/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.ca.water.exceptions;

/**
 *
 * @author asanchez
 */
public class GridException extends Exception {
  private String errMsg;
  
  public GridException(String errMsg) {
    this.errMsg = errMsg;
  }

  @Override
  public String getMessage() {
    return errMsg;
  }
}
