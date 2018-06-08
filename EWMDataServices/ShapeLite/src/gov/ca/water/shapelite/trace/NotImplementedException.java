/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.ca.water.shapelite.trace;

/**
 *
 * @author hdunsford
 */
public class NotImplementedException extends Exception {

  /**
   * Creates a new instance of <code>NotImplementedException</code> without a
   * detail message.
   */
  public NotImplementedException() {
  }

  /**
   * Constructs an instance of <code>NotImplementedException</code> with the
   * specified detail message.
   *
   * @param message the detail message.
   */
  public NotImplementedException(String message) {
    super(message);
  }

  /**
   * Constructs an instance of <code>NotImplementedException</code> with the
   * specified <code>Throwable</code> cause.
   *
   * @param cause the original source of the message.
   */
  public NotImplementedException(Throwable cause) {
    super(cause.getMessage(), cause);
  }

  /**
   * Constructs an instance of <code>NotImplementedException</code> with the
   * specified message and <code>Throwable</code> cause.
   *
   * @param message the detail message.
   * @param cause the original source of the message.
   */
  public NotImplementedException(String message, Throwable cause) {
    super(message, cause);
  }

}
