package br.com.hotel.booknow.core.exceptions.errors.hotel;

public class UnableToDeleteHotelException extends RuntimeException {

	public UnableToDeleteHotelException() {
	}

	public UnableToDeleteHotelException(String message) {
		super(message);
	}

	public UnableToDeleteHotelException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnableToDeleteHotelException(Throwable cause) {
		super(cause);
	}
}
