package br.com.hotel.booknow.core.exceptions.errors.hotel;

public class HotelNotFoundException extends RuntimeException {

	public HotelNotFoundException() {
	}

	public HotelNotFoundException(String message) {
		super(message);
	}

	public HotelNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public HotelNotFoundException(Throwable cause) {
		super(cause);
	}
}
