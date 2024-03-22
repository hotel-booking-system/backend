package br.com.hotel.booknow.core.exceptions.errors;

/**
 * <b>HTTP Status 400 - Bad Request</b>
 */
public class InvalidCredentialException extends RuntimeException {

    public InvalidCredentialException() {
    }

    public InvalidCredentialException(String message) {
        super(message);
    }

    public InvalidCredentialException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCredentialException(Throwable cause) {
        super(cause);
    }
}
