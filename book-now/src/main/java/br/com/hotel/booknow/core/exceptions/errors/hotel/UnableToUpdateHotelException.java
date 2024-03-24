package br.com.hotel.booknow.core.exceptions.errors.hotel;

public class UnableToUpdateHotelException extends RuntimeException {

	public UnableToUpdateHotelException() {
	}

	public UnableToUpdateHotelException(String message) {
		super(message);
	}

	public UnableToUpdateHotelException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnableToUpdateHotelException(Throwable cause) {
		super(cause);
	}
}
