package br.com.hotel.booknow.core.exceptions.errors.reservation;

public class UnableToCreateReservationException extends RuntimeException {
    public UnableToCreateReservationException() {
    }

    public UnableToCreateReservationException(String message) {
        super(message);
    }

    public UnableToCreateReservationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnableToCreateReservationException(Throwable cause) {
        super(cause);
    }
}
