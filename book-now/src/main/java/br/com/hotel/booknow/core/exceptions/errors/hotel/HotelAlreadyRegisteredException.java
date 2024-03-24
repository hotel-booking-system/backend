package br.com.hotel.booknow.core.exceptions.errors.hotel;

public class HotelAlreadyRegisteredException extends RuntimeException {

	public HotelAlreadyRegisteredException() {
	}

	public HotelAlreadyRegisteredException(String message) {
		super(message);
	}

	public HotelAlreadyRegisteredException(String message, Throwable cause) {
		super(message, cause);
	}

	public HotelAlreadyRegisteredException(Throwable cause) {
		super(cause);
	}
}
