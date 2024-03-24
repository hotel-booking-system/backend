package br.com.hotel.booknow.core.exceptions.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorCodes {

    /**
     * 400 - Bad Request
     */
    INVALID_CREDENTIALS(
            "Credenciais inválidas.",
            "Ops, parece que suas credenciais estão incorretas. " +
                    "Verifique o username e password e tente novamente"),
    /**
     * 400 - Bad Request
     */
    INVALID_USER_EMAIL(
            "Email do usuário inválido.",
            "Ops, o email informado parece estar incorreto. " +
                    "Verifique o formato e tente novamente."),
    /**
     * 404 - Not Found
     */
    USER_NOT_FOUND("Usuário não encontrado.",
            "Ops, não encontramos nenhum usuário com esse ID. " +
                    "Verifique o ID e tente novamente."),
    /**
     * 403 - Forbidden
     * <p>
     * Observação: O usuário está autenticado, mas o acesso à sua conta está bloquado.
     */
    USER_INACTIVE(
            " Usuário inativo.",
            "Ops, seu acesso está bloqueado. " +
                    "Entre em contato com o suporte para reativar sua conta"),
    /**
     * 409 - Conflict
     */
    USER_ALREADY_REGISTERED(
            "Usuário já cadastrado.",
            "Ops, já existe um usuário cadastrado com esse email. " +
                    "Tente outro email ou faça login."),
    /**
     * 500 - Internal Server Error
     */
    UNABLE_TO_UPDATE_USER(
            "Não foi possível atualizar o usuário.",
            "Ops, não foi possível atualizar seus dados. Tente novamente mais tarde."),
    /**
     * 500 - Internal Server Error
     */
    UNABLE_TO_DELETE_USER(
            "Não foi possível remover o usuário.",
            "Ops, não foi possível remover sua conta. Tente novamente mais tarde.");

    private final String error;
    private final String message;

}
