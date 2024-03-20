package br.com.hotel.booknow.core.exceptions.errors;

public class ServerErrorException extends RuntimeException {

	public ServerErrorException() {
	}

	public ServerErrorException(String message) {
		super(message);
	}

	public ServerErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServerErrorException(Throwable cause) {
		super(cause);
	}
}
