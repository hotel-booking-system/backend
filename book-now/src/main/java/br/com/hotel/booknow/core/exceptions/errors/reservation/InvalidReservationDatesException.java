package br.com.hotel.booknow.core.exceptions.errors.reservation;

public class InvalidReservationDatesException extends RuntimeException {
    public InvalidReservationDatesException() {
    }

    public InvalidReservationDatesException(String message) {
        super(message);
    }

    public InvalidReservationDatesException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidReservationDatesException(Throwable cause) {
        super(cause);
    }
}
