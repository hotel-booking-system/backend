package br.com.hotel.booknow.core.exceptions.handler;

import br.com.hotel.booknow.core.exceptions.dto.ErrorResponse;
import br.com.hotel.booknow.core.exceptions.dto.enums.HotelErrorCodes;
import br.com.hotel.booknow.core.exceptions.errors.hotel.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <b>Contém os métodos de tratamento de exceções específicas relacionadas a hotéis</b>
 * <p>
 * A classe {@link HotelExceptionHandler} utiliza a anotação {@code @RestControllerAdvice} para interceptar exceções em
 * controllers específicos.
 * <p>
 * Os métodos de tratamento de exceções específicas retornam um objeto {@link ResponseEntity} com um corpo do tipo
 * {@link ErrorResponse} que contém informações sobre o erro relacionado ao hotel.
 * <b>Observações Gerais:</b> <br>
 * - O método log.error registra a exceção no log de erros da aplicação. <br>
 *
 * @author juliane.maran
 * @since 19-03-2024
 */
@Slf4j
@RestControllerAdvice
@AllArgsConstructor
public class HotelExceptionHandler {

    /**
     * <b>Http Status Code:</b> 400 - Bad Request
     * <p>
     * <b>Descrição:</b> Trata a exceção {@link HotelDataInvalidException}, a qual indica que os dados fornecidos para
     * um hotel são inválidos.
     * <p>
     * <b>Utilização:</b> Este método é invocado quando há uma tentativa de criar ou atualizar um hotel com dados que
     * não atendem aos requisitos do sistema.
     *
     * @param ex
     *         A exceção {@link HotelDataInvalidException} lançada.
     *
     * @return Retorna um objeto {@link ResponseEntity} contendo um corpo do tipo {@link ErrorResponse} com o código de
     * status 400 - Bad Request e o código de erro HOTEL_DATA_INVALID.
     */
    @ExceptionHandler(HotelDataInvalidException.class)
    public ResponseEntity<ErrorResponse> handleHotelDataInvalidException(HotelDataInvalidException ex) {
        log.error("Dados do hotel inválidos: ", ex);
        return buildHotelErrorResponse(HttpStatus.BAD_REQUEST, HotelErrorCodes.HOTEL_DATA_INVALID);
    }

    /**
     * <b>Http Status Code:</b> 400 - Bad Request
     * <p>
     * <b>Descrição:</b> Trata a exceção {@link InvalidHotelIdException}, a qual indica queo ID de Hotel fornecdio é
     * inválido.
     * <p>
     * <b>Utilização:</b> Este método é invocado quando há uma tentativa de acessar um hotel utilizando um ID que não
     * existe ou não é válido.
     *
     * @param ex
     *         A exceção  {@link InvalidHotelIdException} lançada.
     *
     * @return Retorna um objeto {@link ResponseEntity} contendo um corpo do tipo {@link ErrorResponse} com o código de
     * status 400 - Bad Request e o código de erro INVALID_HOTEL_ID.
     */
    @ExceptionHandler(InvalidHotelIdException.class)
    public ResponseEntity<ErrorResponse> handleInvalidHotelIdException(InvalidHotelIdException ex) {
        log.error("ID do Hotel inválido: ", ex);
        return buildHotelErrorResponse(HttpStatus.BAD_REQUEST, HotelErrorCodes.INVALID_HOTEL_ID);
    }

    /**
     * <b>Http Status Code:</b> 400 - Bad Request
     * <p>
     * <b>Descrição:</b> Trata a exceção {@link HotelInactiveException}, a qual indica que o hotel acessado está
     * inativo.
     * <p>
     * <b>Utilização:</b> Este método é invocado quando há uma tentativa de realizar uma operação em um hotel que está
     * inativo no sistema.
     *
     * @param ex
     *         A exceção  {@link HotelInactiveException} lançada.
     *
     * @return Retorna um objeto {@link ResponseEntity} contendo um corpo do tipo {@link ErrorResponse} com o código de
     * status 400 - Bad Request e o código de erro HOTEL_INACTIVE.
     */
    @ExceptionHandler(HotelInactiveException.class)
    public ResponseEntity<ErrorResponse> handleHotelInactiveException(HotelInactiveException ex) {
        log.error("Hotel inativo: ", ex);
        return buildHotelErrorResponse(HttpStatus.BAD_REQUEST, HotelErrorCodes.HOTEL_INACTIVE);
    }

    /**
     * <b>Http Status Code:</b> 404 - Not Found
     * <p>
     * <b>Descrição:</b> Trata a exceção {@link HotelNotFoundException}, a qual indica que o hotel solicitado não foi
     * encontrado.
     * <p>
     * <b>Utilização:</b> Este método é invocado quando há uma tentativa de acessar um hotel que não existe no sistema.
     *
     * @param ex
     *         A exceção {@link HotelNotFoundException} lançada.
     *
     * @return Retorna um objeto {@link ResponseEntity} contendo um corpo do tipo {@link ErrorResponse} com o código de
     * status 404 - Not Found e o código de erro HOTEL_NOT_FOUND.
     */
    @ExceptionHandler(HotelNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleHotelNotFoundException(HotelNotFoundException ex) {
        log.error("Hotel não encontrado: ", ex);
        return buildHotelErrorResponse(HttpStatus.NOT_FOUND, HotelErrorCodes.HOTEL_NOT_FOUND);
    }

    /**
     * <b>Http Status Code:</b> 409 - Conflict
     * <p>
     * <b>Descrição:</b> Trata a exceção {@link HotelAlreadyRegisteredException}, a qual indica que o hotel solicitado
     * já está cadastrado.
     * <p>
     * <b>Utilização:</b> Este método é invocado quando há uma tentativa de cadastrar um novo hotel no sistema.
     *
     * @param ex
     *         A exceção {@link HotelAlreadyRegisteredException} lançada.
     *
     * @return Retorna um objeto {@link ResponseEntity} contendo um corpo do tipo {@link ErrorResponse} com o código de
     * status 409 - Conflict e o código de erro HOTEL_ALREADY_REGISTERED.
     */
    @ExceptionHandler(HotelAlreadyRegisteredException.class)
    public ResponseEntity<ErrorResponse> handleHotelAlreadyRegisteredException(HotelAlreadyRegisteredException ex) {
        log.error("Hotel já cadastrado: ", ex);
        return buildHotelErrorResponse(HttpStatus.CONFLICT, HotelErrorCodes.HOTEL_ALREADY_REGISTERED);
    }

    /**
     * <b>Http Status Code:</b> 500 - Internal Server Error
     * <p>
     * <b>Descrição:</b> Trata a exceção {@link UnableToUpdateHotelException}, a qual indica que houve falha ao
     * atualizar o Hotel.
     * <p>
     * <b>Utilização:</b> Este método é invocado quando há uma tentativa de atualizar um hotel cadastrado no sistema.
     *
     * @param ex
     *         A exceção {@link UnableToUpdateHotelException} lançada.
     *
     * @return Retorna um objeto {@link ResponseEntity} contendo um corpo do tipo {@link ErrorResponse} com o código de
     * status 500 - Internal Server Error e o código de erro UNABLE_TO_UPDATE_HOTEL.
     */
    @ExceptionHandler(UnableToUpdateHotelException.class)
    public ResponseEntity<ErrorResponse> handleUnableToUpdateHotelException(UnableToUpdateHotelException ex) {
        log.error("Falha ao atualizar hotel: ", ex);
        return buildHotelErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, HotelErrorCodes.UNABLE_TO_UPDATE_HOTEL);
    }

    /**
     * <b>Http Status Code:</b> 500 - Internal Server Error
     * <p>
     * <b>Descrição:</b> Trata a exceção {@link UnableToDeleteHotelException}, a qual indica que houve falha ao
     * excluir o Hotel.
     * <p>
     * <b>Utilização:</b> Este método é invocado quando há uma tentativa de excluir um hotel cadastrado no sistema.
     *
     * @param ex
     *         A exceção {@link UnableToDeleteHotelException} lançada.
     *
     * @return Retorna um objeto {@link ResponseEntity} contendo um corpo do tipo {@link ErrorResponse} com o código de
     * status 500 - Internal Server Error e o código de erro UNABLE_TO_DELETE_HOTEL.
     */
    @ExceptionHandler(UnableToDeleteHotelException.class)
    public ResponseEntity<ErrorResponse> handleUnableToDeleteHotelException(UnableToDeleteHotelException ex) {
        log.error("Falha ao remover hotel: ", ex);
        return buildHotelErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, HotelErrorCodes.UNABLE_TO_DELETE_HOTEL);
    }

    /**
     * <b>Descrição:</b> Constrói um objeto {@link ResponseEntity} contendo um corpo do tipo {@link ErrorResponse} com
     * base no código de status HTTP e código de erro fornecidos (relacionados a hotéis).
     *
     * @param status
     *         O código de status HTTP da resposta.
     * @param errorCode
     *         O código de erro da enumeração {@link HotelErrorCodes}.
     *
     * @return Retorna o objeto {@link ResponseEntity} criado com o código de status e o erro especificados.
     */
    private ResponseEntity<ErrorResponse> buildHotelErrorResponse(HttpStatus status, HotelErrorCodes errorCode) {
        ErrorResponse response = ErrorResponse.builder()
                .status(status)
                .error(errorCode.getError())
                .message(errorCode.getMessage())
                .build();
        return ResponseEntity.status(status).body(response);
    }

}