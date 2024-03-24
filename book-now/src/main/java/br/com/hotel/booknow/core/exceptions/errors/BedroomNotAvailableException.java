package br.com.hotel.booknow.core.exceptions.errors;

public class BedroomNotAvailableException extends RuntimeException {
    public BedroomNotAvailableException() {
    }

    public BedroomNotAvailableException(String message) {
        super(message);
    }

    public BedroomNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public BedroomNotAvailableException(Throwable cause) {
        super(cause);
    }
}
