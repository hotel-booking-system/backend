package br.com.hotel.booknow.core.exceptions.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReservationErrorCodes {

    RESERVATION_NOT_FOUND("Reserva não encontrada."),
    INVALID_RESERVATION_ID("ID da reserva inválido."),
    INVALID_RESERVATION_DATE("Data da reserva inválida."),
    UNABLE_TO_CREATE_RESERVATION("Não foi possível criar a reserva."),
    UNABLE_TO_UPDATE_RESERVATION("Não foi possível atualizar a reserva."),
    UNABLE_TO_CANCEL_RESERVATION(" Não foi possível cancelar a reserva.");

    private final String message;

}
