package com.firstsb.hellosb.web.Exception;

public class CosaNotFoundException extends RuntimeException {

    public CosaNotFoundException() {
        super();
    }

    public CosaNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public CosaNotFoundException(final String message) {
        super(message);
    }

    public CosaNotFoundException(final Throwable cause) {
        super(cause);
    }
}