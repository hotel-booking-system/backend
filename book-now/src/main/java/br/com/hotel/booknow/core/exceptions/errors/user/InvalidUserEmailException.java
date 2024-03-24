package br.com.hotel.booknow.core.exceptions.errors.user;

/**
 * <b>HTTP Status 400 - Bad Request</b>
 */
public class InvalidUserEmailException extends RuntimeException {

    public InvalidUserEmailException() {
    }

    public InvalidUserEmailException(String message) {
        super(message);
    }

    public InvalidUserEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidUserEmailException(Throwable cause) {
        super(cause);
    }
}
