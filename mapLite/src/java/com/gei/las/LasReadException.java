/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gei.las;

/**
 *
 * @author hdunsford
 */
public class LasReadException extends Exception {

    /**
     * Creates a new instance of <code>LasReadException</code> without a detail message.
     */
    public LasReadException() {
    }


    /**
     * Constructs an instance of <code>LasReadException</code> with the specified detail message.
     * @param message the detail message.
     */
    public LasReadException(String message) {
        super(message);
    }

    /**
     * Constructs an instance of <code>LasReadException</code> with the specified 
     * <code>Throwable</code> cause.
     * @param cause the original source of the message.
     */
    public LasReadException(Throwable cause) {
        super(cause.getMessage(), cause);
    }

    /**
     * Constructs an instance of <code>LasReadException</code> with the specified message 
     * and <code>Throwable</code> cause.
     * @param message the detail message.
     * @param cause the original source of the message.
     */
    public LasReadException(String message, Throwable cause) {
        super(message, cause);
    }

    
}
