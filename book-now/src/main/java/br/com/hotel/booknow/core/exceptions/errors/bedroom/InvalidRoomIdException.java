package br.com.hotel.booknow.core.exceptions.errors.bedroom;

public class InvalidRoomIdException extends RuntimeException {

	public InvalidRoomIdException() {
	}

	public InvalidRoomIdException(String message) {
		super(message);
	}

	public InvalidRoomIdException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidRoomIdException(Throwable cause) {
		super(cause);
	}
}
