package com.firstsb.hellosb.web.Exception;

public class CosaIdMismatchException extends RuntimeException {

    public CosaIdMismatchException() {
        super();
    }

    public CosaIdMismatchException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public CosaIdMismatchException(final String message) {
        super(message);
    }

    public CosaIdMismatchException(final Throwable cause) {
        super(cause);
    }
}