package br.com.hotel.booknow.core.exceptions.handler;

import br.com.hotel.booknow.core.exceptions.dto.ErrorResponse;
import br.com.hotel.booknow.core.exceptions.dto.enums.RoomErrorCodes;
import br.com.hotel.booknow.core.exceptions.errors.bedroom.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <b>Contém os métodos de tratamento de exceções específicas relacionadas a quartos</b>
 * <p>
 * A classe {@link BedroomExceptionHandler} utiliza a anotação {@code @RestControllerAdvice} para interceptar exceções
 * em controllers específicos.
 * <p>
 * Os métodos de tratamento de exceções específicas retornam um objeto {@link ResponseEntity} com um corpo do tipo
 * {@link ErrorResponse} que contém informações sobre o erro relacionado ao quarto.
 * <b>Observações Gerais:</b> <br>
 * - O método log.error registra a exceção no log de erros da aplicação. <br>
 *
 * @author juliane.maran
 * @since 19-03-2024
 */
@Slf4j
@RestControllerAdvice
@AllArgsConstructor
public class BedroomExceptionHandler {

    /**
     * <b>Http Status Code:</b> 400 - Bad Request
     * <p>
     * <b>Descrição:</b> Trata a exceção {@link InvalidRoomIdException}, a qual indica queo ID de quarto fornecdio é
     * inválido.
     * <p>
     * <b>Utilização:</b> Este método é invocado quando há uma tentativa de acessar um quarto utilizando um ID que não
     * existe ou não é válido.
     *
     * @param ex
     *         A exceção  {@link InvalidRoomIdException} lançada.
     *
     * @return Retorna um objeto {@link ResponseEntity} contendo um corpo do tipo {@link ErrorResponse} com o código de
     * status 400 - Bad Request e o código de erro INVALID_ROOM_ID.
     */
    @ExceptionHandler(InvalidRoomIdException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRoomIdException(InvalidRoomIdException ex) {
        log.error("ID do quarto inválido: ", ex);
        return buildRoomErrorResponse(HttpStatus.BAD_REQUEST, RoomErrorCodes.INVALID_ROOM_ID);
    }

    /**
     * <b>Http Status Code:</b> 400 - Bad Request
     * <p>
     * <b>Descrição:</b> Trata a exceção {@link RoomNotAvailableException}, a qual indica que o quarto selecionado não
     * está disponível para a data selecionada.
     * <p>
     * <b>Utilização:</b> Este método é invocado quando há uma tentativa de realizar uma operação em um quarto que está
     * indisponível.
     *
     * @param ex
     *         A exceção  {@link RoomNotAvailableException} lançada.
     *
     * @return Retorna um objeto {@link ResponseEntity} contendo um corpo do tipo {@link ErrorResponse} com o código de
     * status 400 - Bad Request e o código de erro ROOM_NOT_AVAILABLE.
     */
    @ExceptionHandler(RoomNotAvailableException.class)
    public ResponseEntity<ErrorResponse> handleRoomNotAvailableException(RoomNotAvailableException ex) {
        log.error("Quarto não disponível: ", ex);
        return buildRoomErrorResponse(HttpStatus.BAD_REQUEST, RoomErrorCodes.ROOM_NOT_AVAILABLE);
    }

    /**
     * <b>Http Status Code:</b> 404 - Not Found
     * <p>
     * <b>Descrição:</b> Trata a exceção {@link RoomNotFoundException}, a qual indica que o quarto solicitado não foi
     * encontrado
     * <p>
     * <b>Utilização:</b> Este método é invocado quando há uma tentativa de acessar um quarto que não existe no
     * sistema.
     *
     * @param ex
     *         A exceção {@link RoomNotFoundException} lançada.
     *
     * @return Retorna um objeto {@link ResponseEntity} contendo um corpo do tipo {@link ErrorResponse} com o código de
     * status 404 - Not Found e o código de erro ROOM_NOT_FOUND.
     */
    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRoomNotFoundException(RoomNotFoundException ex) {
        log.error("Quarto não encontrado: ", ex);
        return buildRoomErrorResponse(HttpStatus.NOT_FOUND, RoomErrorCodes.ROOM_NOT_FOUND);
    }

    /**
     * <b>Http Status Code:</b> 409 - Conflict
     * <p>
     * <b>Descrição:</b> Trata a exceção {@link RoomAlreadyExistsException}, a qual indica que o quarto solicitado
     * já está cadastrado.
     * <p>
     * <b>Utilização:</b> Este método é invocado quando há uma tentativa de cadastrar um novo quarto no sistema.
     *
     * @param ex
     *         A exceção {@link RoomAlreadyExistsException} lançada.
     *
     * @return Retorna um objeto {@link ResponseEntity} contendo um corpo do tipo {@link ErrorResponse} com o código de
     * status 409 - Conflict e o código de erro ROOM_ALREADY_EXISTS.
     */
    @ExceptionHandler(RoomAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleRoomAlreadyExistsException(RoomAlreadyExistsException ex) {
        log.error("Quarto já cadastrado: ", ex);
        return buildRoomErrorResponse(HttpStatus.CONFLICT, RoomErrorCodes.ROOM_ALREADY_EXISTS);
    }

    /**
     * <b>Http Status Code:</b> 500 - Internal Server Error
     * <p>
     * <b>Descrição:</b> Trata a exceção {@link UnableToUpdateRoomException}, a qual indica que houve falha ao
     * atualizar o quarto.
     * <p>
     * <b>Utilização:</b> Este método é invocado quando há uma tentativa de atualizar um quarto cadastrado no sistema.
     *
     * @param ex
     *         A exceção {@link UnableToUpdateRoomException} lançada.
     *
     * @return Retorna um objeto {@link ResponseEntity} contendo um corpo do tipo {@link ErrorResponse} com o código de
     * status 500 - Internal Server Error e o código de erro UNABLE_TO_UPDATE_ROOM.
     */
    @ExceptionHandler(UnableToUpdateRoomException.class)
    public ResponseEntity<ErrorResponse> handleUnableToUpdateRoomException(UnableToUpdateRoomException ex) {
        log.error("Falha ao atualizar quarto: ", ex);
        return buildRoomErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, RoomErrorCodes.UNABLE_TO_UPDATE_ROOM);
    }

    /**
     * <b>Http Status Code:</b> 500 - Internal Server Error
     * <p>
     * <b>Descrição:</b> Trata a exceção {@link UnableToDeleteRoomException}, a qual indica que houve falha ao
     * excluir o quarto.
     * <p>
     * <b>Utilização:</b> Este método é invocado quando há uma tentativa de excluir um quarto cadastrado no sistema.
     *
     * @param ex
     *         A exceção {@link UnableToDeleteRoomException} lançada.
     *
     * @return Retorna um objeto {@link ResponseEntity} contendo um corpo do tipo {@link ErrorResponse} com o código de
     * status 500 - Internal Server Error e o código de erro UNABLE_TO_DELETE_ROOM.
     */
    @ExceptionHandler(UnableToDeleteRoomException.class)
    public ResponseEntity<ErrorResponse> handleUnableToDeleteRoomException(UnableToDeleteRoomException ex) {
        log.error("Falha ao remover quarto: ", ex);
        return buildRoomErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, RoomErrorCodes.UNABLE_TO_DELETE_ROOM);
    }

    /**
     * <b>Descrição:</b> Constrói um objeto {@link ResponseEntity} contendo um corpo do tipo {@link ErrorResponse} com
     * base no código de status HTTP e código de erro fornecidos (relacionados a quartos).
     *
     * @param status
     *         O código de status HTTP da resposta.
     * @param errorCode
     *         O código de erro da enumeração {@link RoomErrorCodes}.
     *
     * @return Retorna o objeto {@link ResponseEntity} criado com o código de status e o erro especificados.
     */
    private ResponseEntity<ErrorResponse> buildRoomErrorResponse(HttpStatus status, RoomErrorCodes errorCode) {
        ErrorResponse response = ErrorResponse.builder()
                .status(status)
                .error(errorCode.getError())
                .message(errorCode.getMessage())
                .build();
        return ResponseEntity.status(status).body(response);
    }

}