package br.com.hotel.booknow.core.exceptions.errors.user;

/**
 * <b>HTTP Status 400 - Bad Request</b>
 */
public class UserInactiveException extends RuntimeException {

    public UserInactiveException() {
    }

    public UserInactiveException(String message) {
        super(message);
    }

    public UserInactiveException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserInactiveException(Throwable cause) {
        super(cause);
    }
}
