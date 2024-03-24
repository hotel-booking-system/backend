package br.com.hotel.booknow.core.exceptions.errors.reservation;

public class InvalidReservationIdException extends RuntimeException {
    public InvalidReservationIdException() {
    }

    public InvalidReservationIdException(String message) {
        super(message);
    }

    public InvalidReservationIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidReservationIdException(Throwable cause) {
        super(cause);
    }
}
