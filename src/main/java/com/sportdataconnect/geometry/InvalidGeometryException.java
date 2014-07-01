
package com.sportdataconnect.geometry;

/**
 *
 * @author sportdataconnect
 */
public final class InvalidGeometryException extends RuntimeException {

    public InvalidGeometryException(final Throwable cause) {
        super(cause);
    }

    public InvalidGeometryException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InvalidGeometryException(final String message) {
        super(message);
    }

    public InvalidGeometryException() {
    }
    
}
