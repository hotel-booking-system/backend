package br.com.hotel.booknow.core.exceptions.errors;

public class UnauthorizedException extends RuntimeException {

	public UnauthorizedException() {
	}

	public UnauthorizedException(String message) {
		super(message);
	}

	public UnauthorizedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnauthorizedException(Throwable cause) {
		super(cause);
	}
}
