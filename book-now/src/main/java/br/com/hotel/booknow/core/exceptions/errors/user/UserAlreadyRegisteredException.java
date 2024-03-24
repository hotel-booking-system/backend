package br.com.hotel.booknow.core.exceptions.errors.user;

public class UserAlreadyRegisteredException extends RuntimeException {
    public UserAlreadyRegisteredException() {
    }

    public UserAlreadyRegisteredException(String message) {
        super(message);
    }

    public UserAlreadyRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAlreadyRegisteredException(Throwable cause) {
        super(cause);
    }
}
