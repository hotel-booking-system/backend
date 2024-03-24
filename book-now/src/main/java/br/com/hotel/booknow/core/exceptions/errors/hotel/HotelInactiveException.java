package br.com.hotel.booknow.core.exceptions.errors.hotel;

public class HotelInactiveException extends RuntimeException {

	public HotelInactiveException() {
	}

	public HotelInactiveException(String message) {
		super(message);
	}

	public HotelInactiveException(String message, Throwable cause) {
		super(message, cause);
	}

	public HotelInactiveException(Throwable cause) {
		super(cause);
	}
}
