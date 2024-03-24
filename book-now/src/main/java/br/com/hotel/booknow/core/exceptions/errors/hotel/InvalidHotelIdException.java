package br.com.hotel.booknow.core.exceptions.errors.hotel;

public class InvalidHotelIdException extends RuntimeException {

	public InvalidHotelIdException() {
	}

	public InvalidHotelIdException(String message) {
		super(message);
	}

	public InvalidHotelIdException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidHotelIdException(Throwable cause) {
		super(cause);
	}
}
