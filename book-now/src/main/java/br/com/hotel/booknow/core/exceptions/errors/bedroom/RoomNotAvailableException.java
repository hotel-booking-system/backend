package br.com.hotel.booknow.core.exceptions.errors.bedroom;

public class RoomNotAvailableException extends RuntimeException {
    public RoomNotAvailableException() {
    }

    public RoomNotAvailableException(String message) {
        super(message);
    }

    public RoomNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoomNotAvailableException(Throwable cause) {
        super(cause);
    }
}
