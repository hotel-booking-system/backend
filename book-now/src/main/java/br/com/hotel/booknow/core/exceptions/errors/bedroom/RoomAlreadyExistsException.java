package br.com.hotel.booknow.core.exceptions.errors.bedroom;

public class RoomAlreadyExistsException extends RuntimeException {

	public RoomAlreadyExistsException() {
	}

	public RoomAlreadyExistsException(String message) {
		super(message);
	}

	public RoomAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public RoomAlreadyExistsException(Throwable cause) {
		super(cause);
	}
}
