package br.com.hotel.booknow.core.exceptions.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReservationErrorCodes {

    /**
     * 400 - Bad Request
     */
    INVALID_RESERVATION_ID(
            "ID da reserva inválido.",
            "O ID da reserva deve ser um número válido. " +
                    "Por favor, verifique se digitou corretamente."),
    /**
     * 400 - Bad Request
     */
    INVALID_RESERVATION_DATE(
            "Data da reserva inválida.",
            "A data da reserva deve ser uma data válida no futuro. " +
                    "Por favor, escolha uma data válida."),
    /**
     * 404 - Not Found
     */
    RESERVATION_NOT_FOUND(
            "Reserva não encontrada.",
            "A reserva com o ID informado não foi encontrada. " +
                    "Verifique se o ID está correto e tente novamente."),
    /**
     * 500 - Internal Server Error
     */
    UNABLE_TO_CREATE_RESERVATION(
            "Não foi possível criar a reserva.",
            "Ocorreu um erro ao criar a reserva. " +
                    "Tente novamente mais tarde ou entre em contato com o suporte."),
    /**
     * 500 - Internal Server Error
     */
    UNABLE_TO_UPDATE_RESERVATION(
            "Não foi possível atualizar a reserva.",
            "Não foi possível atualizar a sua reserva. " +
                    "Tente novamente mais tarde ou entre em contato com o suporte."),
    /**
     * 500 - Internal Server Error
     */
    UNABLE_TO_CANCEL_RESERVATION(
            " Não foi possível cancelar a reserva.",
            "Ocorreu um erro ao cancelar a sua reserva. " +
                    "Tente novamente mais tarde ou entre em contato com o suporte.");

    private final String error;
    private final String message;

}
