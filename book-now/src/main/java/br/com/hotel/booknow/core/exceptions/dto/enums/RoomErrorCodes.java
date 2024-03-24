package br.com.hotel.booknow.core.exceptions.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoomErrorCodes {

    ROOM_NOT_FOUND("Quarto não encontrado."),
    ROOM_ALREADY_EXISTS("Quarto já cadastrado."),
    INVALID_ROOM_ID("ID do quarto inválido."),
    UNABLE_TO_UPDATE_ROOM("Não foi possível atualizar o quarto."),
    UNABLE_TO_DELETE_ROOM("Não foi possível remover o quarto."),
    ROOM_NOT_AVAILABLE("Quarto não disponível.");

    private final String message;

}
