package br.com.hotel.booknow.exceptions.handler;

import br.com.hotel.booknow.exceptions.dto.ErrorCode;
import br.com.hotel.booknow.exceptions.dto.ErrorResponse;
import br.com.hotel.booknow.exceptions.errors.*;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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

		log.error("Erro inesperado: ", ex);

		ErrorResponse response = ErrorResponse.builder()
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.error(ErrorCode.SERVER_ERROR.getCode())
				.message(ErrorCode.SERVER_ERROR.getMessage())
				.build();

		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(response);

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

		log.error("Requisição inválida: ", ex);

		ErrorResponse response = ErrorResponse.builder()
				.status(HttpStatus.BAD_REQUEST)
				.error(ErrorCode.BAD_REQUEST.getCode())
				.message(ErrorCode.BAD_REQUEST.getMessage())
				.build();

		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(response);

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

		log.error("Usuário não autenticado: ", ex);

		ErrorResponse response = ErrorResponse.builder()
				.status(HttpStatus.UNAUTHORIZED)
				.error(ErrorCode.UNAUTHORIZED.getCode())
				.message(ErrorCode.UNAUTHORIZED.getMessage())
				.build();

		return ResponseEntity
				.status(HttpStatus.UNAUTHORIZED)
				.body(response);

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

		log.error("Usuário não tem permissão: ", ex);

		ErrorResponse response = ErrorResponse.builder()
				.status(HttpStatus.FORBIDDEN)
				.error(ErrorCode.FORBIDDEN.getCode())
				.message(ErrorCode.FORBIDDEN.getMessage())
				.build();

		return ResponseEntity
				.status(HttpStatus.FORBIDDEN)
				.body(response);

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

		log.error("Recurso não encontrado: ", ex);

		ErrorResponse response = ErrorResponse.builder()
				.status(HttpStatus.NOT_FOUND)
				.error(ErrorCode.NOT_FOUND.getCode())
				.message(ErrorCode.NOT_FOUND.getMessage())
				.build();

		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(response);

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

		log.error("Dado(s) já cadastrado(s): ", ex);

		ErrorResponse response = ErrorResponse.builder()
				.status(HttpStatus.CONFLICT)
				.error(ErrorCode.CONFLICT.getCode())
				.message(ErrorCode.CONFLICT.getMessage())
				.build();

		return ResponseEntity
				.status(HttpStatus.CONFLICT)
				.body(response);

	}

}