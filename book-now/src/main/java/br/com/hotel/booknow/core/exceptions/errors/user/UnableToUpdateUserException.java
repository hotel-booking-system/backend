package br.com.hotel.booknow.core.exceptions.errors.user;

/**
 * <b>HTTP Status 400 - Bad Request</b>
 */
public class UnableToUpdateUserException extends RuntimeException {

    public UnableToUpdateUserException() {
    }

    public UnableToUpdateUserException(String message) {
        super(message);
    }

    public UnableToUpdateUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnableToUpdateUserException(Throwable cause) {
        super(cause);
    }
}
