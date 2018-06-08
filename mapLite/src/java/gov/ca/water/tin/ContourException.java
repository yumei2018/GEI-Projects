/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.ca.water.tin;

/**
 * This exception represents a problem that occurred related to contours that 
 * might be more specific than letting the default error handling happen.
 * @author hdunsford
 */
public class ContourException extends Exception {

    /**
     * Creates a new instance of <code>ContourException</code> without a detail message.
     */
    public ContourException() {
    }


    /**
     * Constructs an instance of <code>ContourException</code> with the specified detail message.
     * @param message the detail message.
     */
    public ContourException(String message) {
        super(message);
    }

    /**
     * Constructs an instance of <code>ContourException</code> with the specified 
     * <code>Throwable</code> cause.
     * @param cause the original source of the message.
     */
    public ContourException(Throwable cause) {
        super(cause.getMessage(), cause);
    }

    /**
     * Constructs an instance of <code>ContourException</code> with the specified message 
     * and <code>Throwable</code> cause.
     * @param message the detail message.
     * @param cause the original source of the message.
     */
    public ContourException(String message, Throwable cause) {
        super(message, cause);
    }

    
}

