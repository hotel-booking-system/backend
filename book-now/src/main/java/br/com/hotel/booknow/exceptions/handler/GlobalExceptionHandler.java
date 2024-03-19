package br.com.hotel.booknow.exceptions.handler;

import br.com.hotel.booknow.exceptions.dto.ErrorCode;
import br.com.hotel.booknow.exceptions.dto.ErrorResponse;
import br.com.hotel.booknow.exceptions.errors.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Status 500 - Internal Server Error
	 * <p>
	 * Erro genérico. Utilizado quando houver falha de conexão com o servidor.
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception ex) {
		var errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
				"Erro interno do servidor", ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}

	/**
	 * Status 400 - Bad Request
	 * <p>
	 * Utilizado para erro na requisição, campo inválido, campo obrigatório não preenchido.
	 */
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex) {
		var errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST,
				ErrorCode.BAD_REQUEST.getCode(), ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	/**
	 * Status 401 - Unauthorized
	 * <p>
	 * O usuário tenta realizar uma ação, porém não está autenticado/logado no sistema.
	 */
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorizedException ex) {
		var errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED,
				ErrorCode.UNAUTHORIZED.getCode(), ex.getMessage());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
	}

	/**
	 * Status 403 - Forbidden
	 * <p>
	 * O usuário tenta realizar uma ação, porém não possui permissão, mesmo autenticado.
	 */
	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<ErrorResponse> handleForbiddenException(ForbiddenException ex) {
		var errorResponse = new ErrorResponse(HttpStatus.FORBIDDEN,
				ErrorCode.FORBIDDEN.getCode(), ex.getMessage());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
	}


	/**
	 * Status 404 - Not Found
	 * <p>
	 * Quando o campo solicitado não for encontrado ou não estiver cadastrado.
	 */
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
		var errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND,
				ErrorCode.NOT_FOUND.getCode(), ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}

	/**
	 * Status 409 - Conflict
	 * <p>
	 * Deve utilizar quando algum dado já estiver cadastrado/em uso.
	 */
	@ExceptionHandler(ConflictException.class)
	public ResponseEntity<ErrorResponse> handleConflictException(ConflictException ex) {
		var errorResponse = new ErrorResponse(HttpStatus.CONFLICT,
				ErrorCode.CONFLICT.getCode(), ex.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}

}