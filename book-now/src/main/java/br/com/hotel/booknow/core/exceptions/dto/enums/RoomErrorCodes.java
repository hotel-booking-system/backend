package br.com.hotel.booknow.core.exceptions.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoomErrorCodes {

    /**
     * 400 - Bad Request
     */
    INVALID_ROOM_ID(
            "ID do quarto inválido.",
            "O ID do quarto parece estar incorreto. Verifique o formato e tente novamente."),
    /**
     * 404 - Not Found
     */
    ROOM_NOT_AVAILABLE(
            "Quarto não disponível.",
            "O quarto selecionado não está disponível. Escolha outro quarto ou data."),
    /**
     * 404 - Not Found
     */
    ROOM_NOT_FOUND(
            "Quarto não encontrado.",
            "Ops! Não encontramos o quarto que você procura. Tente novamente."),
    /**
     * 409 - Conflict
     */
    ROOM_ALREADY_EXISTS(
            "Quarto já cadastrado.",
            "Parece que esse quarto já está cadastrado. Tente outro número ou nome."),
    /**
     * 500 - Internal Server Error
     */
    UNABLE_TO_UPDATE_ROOM(
            "Não foi possível atualizar o quarto.",
            "Ocorreu um erro ao atualizar o quarto. Tente novamente mais tarde."),
    /**
     * 500 - Internal Server Error
     */
    UNABLE_TO_DELETE_ROOM(
            "Não foi possível remover o quarto.",
            "Ocorreu um erro ao remover o quarto. Tente novamente mais tarde.");

    private final String error;
    private final String message;

}
