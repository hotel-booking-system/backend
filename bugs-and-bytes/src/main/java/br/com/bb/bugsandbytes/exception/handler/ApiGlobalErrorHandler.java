package br.com.bb.bugsandbytes.exception.handler;

import br.com.bb.bugsandbytes.exception.dto.ErrorResponse;
import br.com.bb.bugsandbytes.exception.dto.ErrorStatus;
import br.com.bb.bugsandbytes.exception.erros.ApiBadRequestException;
import br.com.bb.bugsandbytes.exception.erros.ServerErrorException;
import br.com.bb.bugsandbytes.exception.erros.UsernameAlreadyExistsException;
import br.com.bb.bugsandbytes.exception.erros.UsernameNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ApiGlobalErrorHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ApiBadRequestException.class)
	public ResponseEntity<ErrorResponse> handleApiBadRequestException(ApiBadRequestException ex) {
		log.error("Erro: Usuário não encontrado.", ex);
		ErrorResponse errorResponse = ErrorResponse.builder()
				.code(ErrorStatus.BAD_REQUEST.getCode())
				.status(ErrorStatus.BAD_REQUEST.getStatus())
				.message(ErrorStatus.BAD_REQUEST.getMessage())
				.build();
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException ex) {
		log.error("Erro: Usuário não encontrado.", ex);
		ErrorResponse errorResponse = ErrorResponse.builder()
				.code(ErrorStatus.USERNAME_NOT_FOUND.getCode())
				.status(ErrorStatus.USERNAME_NOT_FOUND.getStatus())
				.message(ErrorStatus.USERNAME_NOT_FOUND.getMessage())
				.build();
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UsernameAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException ex) {
		log.error("Erro: Usuário já cadastrado.", ex);
		ErrorResponse errorResponse = ErrorResponse.builder()
				.code(ErrorStatus.USERNAME_ALREADY_EXIST.getCode())
				.status(ErrorStatus.USERNAME_ALREADY_EXIST.getStatus())
				.message(ErrorStatus.USERNAME_ALREADY_EXIST.getMessage())
				.build();
		return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleAllException(Exception ex) {
		log.error("Erro inesperado", ex);
		ErrorResponse errorResponse = ErrorResponse.builder()
				.code(ErrorStatus.SERVER_ERROR.getCode())
				.status(ErrorStatus.SERVER_ERROR.getStatus())
				.message(ErrorStatus.SERVER_ERROR.getMessage())
				.build();
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
