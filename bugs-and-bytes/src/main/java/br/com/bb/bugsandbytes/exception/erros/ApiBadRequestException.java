package br.com.bb.bugsandbytes.exception.erros;

public class ApiBadRequestException extends RuntimeException {
	public ApiBadRequestException() {
	}

	public ApiBadRequestException(String message) {
		super(message);
	}

	public ApiBadRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApiBadRequestException(Throwable cause) {
		super(cause);
	}
}
