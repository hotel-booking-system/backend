package br.com.hotel.booknow.core.exceptions.handler;

import br.com.hotel.booknow.core.exceptions.dto.ErrorResponse;
import br.com.hotel.booknow.core.exceptions.dto.enums.UserErrorCodes;
import br.com.hotel.booknow.core.exceptions.errors.user.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author juliane.maran
 * @since 19-03-2024
 */
@Slf4j
@RestControllerAdvice
@AllArgsConstructor
public class UserExceptionHandler {


    /**
     * <b>Http Status Code:</b> 400 - Bad Request
     * <p>
     * <b>Descrição:</b>
     * <p>
     * <b>Utilização:</b>
     *
     * @param ex
     *
     * @return
     */
    @ExceptionHandler(InvalidCredentialException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentialException(InvalidCredentialException ex) {
        log.error("Credenciais inválidas: ", ex);
        return buildUserErrorResponse(HttpStatus.BAD_REQUEST, UserErrorCodes.INVALID_CREDENTIALS);
    }

    /**
     * <b>Http Status Code:</b> 400 - Bad Request
     * <p>
     * <b>Descrição:</b>
     * <p>
     * <b>Utilização:</b>
     *
     * @param ex
     *
     * @return
     */
    @ExceptionHandler(InvalidUserEmailException.class)
    public ResponseEntity<ErrorResponse> handleInvalidUserEmailException(InvalidUserEmailException ex) {
        log.error("Email do usuário inválido: ", ex);
        return buildUserErrorResponse(HttpStatus.BAD_REQUEST, UserErrorCodes.INVALID_USER_EMAIL);
    }

    /**
     * <b>Http Status Code:</b> 403 - Forbidden
     * <p>
     * Usuário está autenticado, mas o acesso à sua conta está bloqueada.
     * <p>
     * <b>Descrição:</b>
     * <p>
     * <b>Utilização:</b>
     *
     * @param ex
     *
     * @return
     */
    @ExceptionHandler(UserInactiveException.class)
    public ResponseEntity<ErrorResponse> handleHotelDataInvalidException(UserInactiveException ex) {
        log.error("Usuário inativo: ", ex);
        return buildUserErrorResponse(HttpStatus.FORBIDDEN, UserErrorCodes.USER_INACTIVE);
    }

    /**
     * <b>Http Status Code:</b> 404 - Not Found
     * <p>
     * <b>Descrição:</b>
     * <p>
     * <b>Utilização:</b>
     *
     * @param ex
     *
     * @return
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
        log.error("Usuário não encontrado: ", ex);
        return buildUserErrorResponse(HttpStatus.NOT_FOUND, UserErrorCodes.USER_NOT_FOUND);
    }

    /**
     * <b>Http Status Code:</b> 409 - Conflict
     * <p>
     * <b>Descrição:</b>
     * <p>
     * <b>Utilização:</b>
     *
     * @param ex
     *
     * @return
     */
    @ExceptionHandler(UserAlreadyRegisteredException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyRegisteredException(UserAlreadyRegisteredException ex) {
        log.error("Usuário já cadastrado: ", ex);
        return buildUserErrorResponse(HttpStatus.CONFLICT, UserErrorCodes.USER_ALREADY_REGISTERED);
    }

    /**
     * <b>Http Status Code:</b> 500 - Internal Server Error
     * <p>
     * <b>Descrição:</b>
     * <p>
     * <b>Utilização:</b>
     *
     * @param ex
     *
     * @return
     */
    @ExceptionHandler(UnableToUpdateUserException.class)
    public ResponseEntity<ErrorResponse> handleUnableToUpdateUserException(UnableToUpdateUserException ex) {
        log.error("Falha ao atualizar usuário: ", ex);
        return buildUserErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, UserErrorCodes.UNABLE_TO_UPDATE_USER);
    }

    /**
     * <b>Http Status Code:</b> 500 - Internal Server Error
     * <p>
     * <b>Descrição:</b>
     * <p>
     * <b>Utilização:</b>
     *
     * @param ex
     *
     * @return
     */
    @ExceptionHandler(UnableToDeleteUserException.class)
    public ResponseEntity<ErrorResponse> handleUnableToDeleteUserException(UnableToDeleteUserException ex) {
        log.error("Falha ao remover usuário: ", ex);
        return buildUserErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, UserErrorCodes.UNABLE_TO_DELETE_USER);
    }

    /**
     * <b>Descrição:</b>
     *
     * @param status
     * @param errorCode
     *
     * @return
     */
    private ResponseEntity<ErrorResponse> buildUserErrorResponse(HttpStatus status, UserErrorCodes errorCode) {
        ErrorResponse response = ErrorResponse.builder()
                .status(status)
                .error(errorCode.getError())
                .message(errorCode.getMessage())
                .build();
        return ResponseEntity.status(status).body(response);
    }

}