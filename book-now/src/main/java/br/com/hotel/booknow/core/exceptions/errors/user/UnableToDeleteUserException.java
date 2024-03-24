package br.com.hotel.booknow.core.exceptions.errors.user;

/**
 * <b>HTTP Status 400 - Bad Request</b>
 */
public class UnableToDeleteUserException extends RuntimeException {

    public UnableToDeleteUserException() {
    }

    public UnableToDeleteUserException(String message) {
        super(message);
    }

    public UnableToDeleteUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnableToDeleteUserException(Throwable cause) {
        super(cause);
    }
}
