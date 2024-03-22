package br.com.hotel.booknow.core.exceptions.errors;

/**
 * <b>HTTP Status 400 - Bad Request</b>
 */
public class BadRequestException extends RuntimeException {

	public BadRequestException() {
	}

	public BadRequestException(String message) {
		super(message);
	}

	public BadRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public BadRequestException(Throwable cause) {
		super(cause);
	}
}
