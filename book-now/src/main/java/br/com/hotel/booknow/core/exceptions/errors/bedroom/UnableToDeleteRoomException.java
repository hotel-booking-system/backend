package br.com.hotel.booknow.core.exceptions.errors.bedroom;

public class UnableToDeleteRoomException extends RuntimeException {

	public UnableToDeleteRoomException() {
	}

	public UnableToDeleteRoomException(String message) {
		super(message);
	}

	public UnableToDeleteRoomException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnableToDeleteRoomException(Throwable cause) {
		super(cause);
	}
}
