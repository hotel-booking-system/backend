package br.com.hotel.booknow.core.exceptions.handler;

import br.com.hotel.booknow.core.exceptions.dto.ErrorResponse;
import br.com.hotel.booknow.core.exceptions.dto.enums.ErrorCodes;
import br.com.hotel.booknow.core.exceptions.errors.ConflictException;
import br.com.hotel.booknow.core.exceptions.errors.generics.ForbiddenException;
import br.com.hotel.booknow.core.exceptions.errors.generics.InvalidRequestException;
import br.com.hotel.booknow.core.exceptions.errors.generics.NotFoundException;
import br.com.hotel.booknow.core.exceptions.errors.generics.UnauthorizedException;
import br.com.hotel.booknow.core.exceptions.errors.user.InvalidCredentialException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <b>Contém os métodos de tratamento de exceções genéricas.</b>
 * <p>
 * A classe utiliza a anotação {@code @RestControllerAdvice} para interceptar exceções no sistema em geral.
 * <br>
 * Os métodos de tratamento de exceções genéricas retornam um objeto {@link ResponseEntity} com um corpo do tipo
 * {@link ErrorResponse} que contém informações sobre o erro.
 * <p>
 * <b>Observações Gerais:</b> <br>
 * - O método log.error registra a exceção no log de erros da aplicação. <br>
 *
 * @author juliane.maran
 * @since 19-03-2024
 */
@Slf4j
@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {

    /**
     * <b>Http Status Code:</b> 500 - Internal Server Error
     * <p>
     * <b>Descrição:</b> Este método é um tratador de exceção genérico. Ele captura qualquer exceção do tipo
     * {@link Exception} ou subclasses.
     * <p>
     * <b>Utilização:</b> Utilize esse método para exceções inesperadas e internas do servidor.
     *
     * @param ex
     *         A exceção lançada.
     *
     * @return Retorna um objeto {@link ResponseEntity} contendo um corpo do tipo {@link ErrorResponse} com o código de
     * status INTERNAL_SERVER_ERROR e o código de erro SERVER_ERROR.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        log.error("Erro inesperado: ", ex);
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCodes.SERVER_ERROR);
    }

    /**
     * <b>Http Status Code:</b> 400 Bad Request
     * <p>
     * <b>Descrição:</b> Este método lida com exceções do tipo {@link RuntimeException} relacionadas a solicitações
     * inválidas.
     * <p>
     * <b>Utilização:</b> <br>
     * - Utilize este método para tratar erros de validação e credenciais inválidas. <br> - O método
     * getErrorCodeForException é utilizado para obter o código de erro específico da exceção.
     *
     * @param ex
     *         A exceção lançada (deve ser {@link InvalidRequestException} ou {@link InvalidCredentialException}).
     *
     * @return Retorna um objeto {@link ResponseEntity} contendo um corpo do tipo {@link ErrorResponse} com o código de
     * status BAD_REQUEST e o código de erro apropriado de acordo com a exceção lançada (BAD_REQUEST para
     * {@link InvalidRequestException} e INVALID_CREDENTIAL para {@link InvalidCredentialException}).
     */
    @ExceptionHandler({
            InvalidRequestException.class,
            InvalidCredentialException.class
    })
    public ResponseEntity<ErrorResponse> handleBadRequestExceptions(RuntimeException ex) {
        log.error("Requisição inválida: ", ex);
        return buildErrorResponse(HttpStatus.BAD_REQUEST, getErrorCodeForException(ex));
    }

    /**
     * <b>Http Status Code:</b> 401 - Unauthorized
     * <p>
     * <b>Descrição:</b> Este método lida com exceções do tipo {@link UnauthorizedException} que indicam falta de
     * autorização do usuário.
     * <p>
     * <b>Utilização:</b> Utilize este método para casos onde o usuário não está autenticado.
     *
     * @param ex
     *         A exceção lançada (deve ser {@link UnauthorizedException}).
     *
     * @return Retorna um objeto {@link ResponseEntity} contendo um corpo do tipo {@link ErrorResponse} com o código de
     * status UNAUTHORIZED e o código de erro UNAUTHORIZED.
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorizedException ex) {
        log.error("Não autenticado: ", ex);
        return buildErrorResponse(HttpStatus.UNAUTHORIZED, ErrorCodes.UNAUTHORIZED);
    }

    /**
     * <b>Http Status Code:</b> 403 - Forbiddens
     * <p>
     * <b>Descrição:</b> Este método lida com exceções do tipo {@link ForbiddenException} que indicam falta de
     * permissão do usuário para o recurso acessado.
     * <p>
     * <b>Utilização:</b> Utilize este método para casos onde o usuário não tem permissão para acessar o recurso.
     *
     * @param ex
     *         A exceção lançada (deve ser {@link ForbiddenException}).
     *
     * @return Retorna um objeto {@link ResponseEntity} contendo um corpo do tipo {@link ErrorResponse} com o código de
     * status FORBIDDEN e o código de erro FORBIDDEN.
     */
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResponse> handleForbiddenException(ForbiddenException ex) {
        log.error("Não tem permissão: ", ex);
        return buildErrorResponse(HttpStatus.FORBIDDEN, ErrorCodes.FORBIDDEN);
    }

    /**
     * <b>Http Status Code:</b> 404 - Not Found <br>
     * <p>
     * <b>Descrição:</b>  Este método lida com exceções do tipo {@link NotFoundException} que indicam que o recurso
     * solicitado não foi encontrado.
     * <p>
     * <b>Observações:</b> Utilize este método para casos onde o recurso solicitado não existe.
     *
     * @param ex
     *         A exceção lançada (deve ser {@link NotFoundException}).
     *
     * @return Retorna um objeto {@link ResponseEntity} contendo um corpo do tipo {@link ErrorResponse} com o código de
     * status NOT_FOUND e o código de erro NOT_FOUND.
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        log.error("Recurso não encontrado: ", ex);
        return buildErrorResponse(HttpStatus.NOT_FOUND, ErrorCodes.NOT_FOUND);
    }

    /**
     * <b>Http Status Code:</b> 409 - Conflict <br>
     * <p>
     * <b>Descrição:</b> Este método lida com exceções do tipo {@link ConflictException} que indicam tentativa de
     * cadastro de dados duplicados.
     * <p>
     * <b>Utilização:</b> Utilize este método para casos onde o cliente tentou inserir dados que já existem no sistema.
     *
     * @param ex
     *         A exceção lançada (deve ser {@link ConflictException}).
     *
     * @return etorna um objeto {@link ResponseEntity} contendo um corpo do tipo {@link ErrorResponse} com o código de
     * status CONFLICT e o código de erro CONFLICT.
     */
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponse> handleConflictException(ConflictException ex) {
        log.error("Recurso já cadastrado: ", ex);
        return buildErrorResponse(HttpStatus.CONFLICT, ErrorCodes.CONFLICT);
    }

    /**
     * <b>Descrição:</b> Este método privado constrói um objeto {@link ErrorResponse} com base no código de status HTTP
     * e código de erro fornecidos.
     *
     * @param status
     *         O código de status HTTP da resposta.
     * @param errorCode
     *         O código de erro da enumeração {@link ErrorCodes}.
     *
     * @return Retorna um objeto {@link ResponseEntity} contendo um corpo do tipo {@link ErrorResponse}.
     */
    private ResponseEntity<ErrorResponse> buildErrorResponse(HttpStatus status, ErrorCodes errorCode) {
        ErrorResponse response = ErrorResponse.builder()
                .status(status)
                .error(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
        return ResponseEntity.status(status).body(response);
    }

    /**
     * <b>Descrição:</b> Este método privado recupera o código de erro apropriado para a exceção do tipo
     * {@link RuntimeException} lançada.
     * <p>
     * <b>Observações:</b> <br>
     * - Esse método é utilizado pelo handleBadRequestExceptions para obter o código de erro específico. <br> - Lança
     * uma {@link IllegalStateException} caso seja recebida uma exceção de tipo inesperado.
     *
     * @param ex
     *         A exceção lançada (deve ser {@link InvalidRequestException} ou {@link InvalidCredentialException}).
     *
     * @return Retorna o código de erro (BAD_REQUEST ou INVALID_CREDENTIAL) de acordo com a exceção.
     */
    private ErrorCodes getErrorCodeForException(RuntimeException ex) {
        if (ex instanceof InvalidRequestException) {
            return ErrorCodes.BAD_REQUEST;
        } else if (ex instanceof InvalidCredentialException) {
            return ErrorCodes.INVALID_CREDENTIAL;
        } else {
            throw new IllegalStateException("Exceção de tipo inesperado: " + ex.getClass().getName());
        }
    }

}