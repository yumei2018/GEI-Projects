
package gov.ca.water.shapelite.io;


public class LasReadException extends Exception {

    public LasReadException() {
    }


    public LasReadException(String message) {
        super(message);
    }

    public LasReadException(Throwable cause) {
        super(cause.getMessage(), cause);
    }

    public LasReadException(String message, Throwable cause) {
        super(message, cause);
    }

    
}
