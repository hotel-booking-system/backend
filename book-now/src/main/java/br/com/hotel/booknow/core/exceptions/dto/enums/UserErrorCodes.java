package br.com.hotel.booknow.core.exceptions.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorCodes {

    USER_NOT_FOUND("Usuário não encontrado."),
    USER_ALREADY_REGISTERED("Usuário já cadastrado."),
    INVALID_CREDENTIALS("Credenciais inválidas."),
    INVALID_USER_EMAIL("Email do usuário inválido."),
    UNABLE_TO_UPDATE_USER("Não foi possível atualizar o usuário."),
    UNABLE_TO_DELETE_USER("Não foi possível remover o usuário."),
    USER_INACTIVE(" Usuário inativo.");

    private final String message;


}
