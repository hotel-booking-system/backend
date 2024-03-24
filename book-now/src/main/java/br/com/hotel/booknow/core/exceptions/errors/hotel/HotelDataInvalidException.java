package br.com.hotel.booknow.core.exceptions.errors.hotel;

public class HotelDataInvalidException extends RuntimeException {

	public HotelDataInvalidException() {
	}

	public HotelDataInvalidException(String message) {
		super(message);
	}

	public HotelDataInvalidException(String message, Throwable cause) {
		super(message, cause);
	}

	public HotelDataInvalidException(Throwable cause) {
		super(cause);
	}
}
