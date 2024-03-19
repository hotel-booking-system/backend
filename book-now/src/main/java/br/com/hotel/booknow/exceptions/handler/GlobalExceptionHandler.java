package br.com.hotel.booknow.exceptions.handler;

import br.com.hotel.booknow.exceptions.dto.ErrorCode;
import br.com.hotel.booknow.exceptions.dto.ErrorResponse;
import br.com.hotel.booknow.exceptions.errors.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <b>Handler global de exceções da API.</b>
 * <p>
 * Responsável por interceptar e tratar exceções de forma centralizada, retornando respostas HTTP apropriadas com
 * detalhes da falha.
 *
 * @author juliane.maran
 * @since 19-03-2024
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Trata exceções gerais da aplicação.
	 *
	 * @param ex
	 * 		Exceção que ocorreu
	 *
	 * @return Resposta HTTP com status 500 (Internal Server Error) e detalhes da falha.
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception ex) {
		var errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
				"Erro interno do servidor", ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}

	/**
	 * Trata exceções de requisição inválida.
	 *
	 * @param ex
	 * 		Exceção de requisição inválida.
	 *
	 * @return Resposta HTTP com status 400 (Bad Request) e detalhes da falha.
	 */
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex) {
		var errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST,
				ErrorCode.BAD_REQUEST.getCode(), ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	/**
	 * Trata exceções de usuário não autenticado
	 *
	 * @param ex
	 * 		Exceção de usuário não autenticado
	 *
	 * @return Resposta HTTP com status 401 (Unauthorization) e detalhes da falha.
	 */
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorizedException ex) {
		var errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED,
				ErrorCode.UNAUTHORIZED.getCode(), ex.getMessage());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
	}

	/**
	 * Trata exceções de usuário não tem permissão
	 *
	 * @param ex
	 * 		Exceção de usuário não tem permissão
	 *
	 * @return Resposta HTTP com status 403 (Forbidden) e detalhes da falha.
	 */
	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<ErrorResponse> handleForbiddenException(ForbiddenException ex) {
		var errorResponse = new ErrorResponse(HttpStatus.FORBIDDEN,
				ErrorCode.FORBIDDEN.getCode(), ex.getMessage());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
	}

	/**
	 * Trata exceções de recurso não encontrado
	 *
	 * @param ex
	 * 		Exceção de recurso não encontrado
	 *
	 * @return Resposta HTTP com status 404 (Not Found) e detalhes da falha.
	 */
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
		var errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND,
				ErrorCode.NOT_FOUND.getCode(), ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}

	/**
	 * Trata exceções de conflito de dados
	 *
	 * @param ex
	 * 		Exceção de conflito de dados
	 *
	 * @return Resposta HTTP com status 409 (Conflict) e detalhes da falha.
	 */
	@ExceptionHandler(ConflictException.class)
	public ResponseEntity<ErrorResponse> handleConflictException(ConflictException ex) {
		var errorResponse = new ErrorResponse(HttpStatus.CONFLICT,
				ErrorCode.CONFLICT.getCode(), ex.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}

}