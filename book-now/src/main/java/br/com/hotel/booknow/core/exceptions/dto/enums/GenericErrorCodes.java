package br.com.hotel.booknow.core.exceptions.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GenericErrorCodes {

    UNEXPECTED_ERROR("Erro inesperado."),
    INTERNAL_SERVER_ERROR("Erro interno do servidor."),
    DATABASE_ERROR("Erro no banco de dados."),
    ACCESS_DENIED("Acesso negado."),
    UNAUTHORIZED("Não autorizado."),
    INVALID_REQUEST("Requisição inválida.");

    private final String message;

}
