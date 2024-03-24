package br.com.hotel.booknow.core.exceptions.errors.reservation;

public class UnableToCancelReservationException extends RuntimeException {
    public UnableToCancelReservationException() {
    }

    public UnableToCancelReservationException(String message) {
        super(message);
    }

    public UnableToCancelReservationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnableToCancelReservationException(Throwable cause) {
        super(cause);
    }
}
