package br.com.hotel.booknow.core.exceptions.errors.reservation;

public class UnableToUpdateReservationException extends RuntimeException {
    public UnableToUpdateReservationException() {
    }

    public UnableToUpdateReservationException(String message) {
        super(message);
    }

    public UnableToUpdateReservationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnableToUpdateReservationException(Throwable cause) {
        super(cause);
    }
}
