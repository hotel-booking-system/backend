package br.com.hotel.booknow.core.exceptions.errors.bedroom;

public class UnableToUpdateRoomException extends RuntimeException {

	public UnableToUpdateRoomException() {
	}

	public UnableToUpdateRoomException(String message) {
		super(message);
	}

	public UnableToUpdateRoomException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnableToUpdateRoomException(Throwable cause) {
		super(cause);
	}
}
