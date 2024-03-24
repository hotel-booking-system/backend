package br.com.hotel.booknow.core.exceptions.errors.generics;

/**
 * <b>HTTP Status 400 - Bad Request</b>
 */
public class InvalidRequestException extends RuntimeException {

	public InvalidRequestException() {
	}

	public InvalidRequestException(String message) {
		super(message);
	}

	public InvalidRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidRequestException(Throwable cause) {
		super(cause);
	}
}
