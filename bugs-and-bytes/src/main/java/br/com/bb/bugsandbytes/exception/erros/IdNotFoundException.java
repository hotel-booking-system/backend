package br.com.bb.bugsandbytes.exception.erros;

public class IdNotFoundException extends RuntimeException {

	public IdNotFoundException() {
	}

	public IdNotFoundException(String message) {
		super(message);
	}

	public IdNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public IdNotFoundException(Throwable cause) {
		super(cause);
	}
}
