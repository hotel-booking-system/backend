package br.com.hotel.booknow.core.exceptions.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum que define os códigos de erro da API.
 *
 * @author juliane.maran
 * @since 19-03-2024
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {

    /**
     * <b>Requisição inválida</b>
     * <p>
     * Code: 400
     * <p>
     * Message: Ops, algo deu errado com sua requisição. Verifique se os dados estão corretos.
     */
    BAD_REQUEST(
            "400",
            "Ops, algo deu errado com sua requisição. Verifique se os dados estão corretos."),
    /**
     * <b>Credenciais Inválidas.</b>
     * <p>
     * Code: 400
     * <p>
     * Message: Credenciais Inválidas.
     */
    INVALID_CREDENTIAL(
            "400",
            "Credenciais inválidas!"),
    /**
     * <b>Usuário não autenticado.</b>
     * <p>
     * Code: 401
     * <p>
     * Message: Você precisa se conectar para acessar este recurso. Faça login ou crie uma conta.
     */
    UNAUTHORIZED(
            "401",
            "Você precisa se conectar para acessar este recurso. Faça login ou crie uma conta."),
    /**
     * <b>Usuário não tem permissão.</b>
     * <p>
     * Code: 403
     * <p>
     * Message: Você não tem permissão para acessar este recurso. Entre em contato com o administrador para solicitar
     * acesso.
     */
    FORBIDDEN(
            "403",
            "Você não tem permissão para acessar este recurso. Entre em contato com o administrador para solicitar acesso."),
    /**
     * <b>Recurso não encontrado.</b>
     * <p>
     * Code: 404
     * <p>
     * Message: O que você procura não foi encontrado. Verifique se o endereço está correto ou tente buscar por algo
     * diferente.
     */
    NOT_FOUND(
            "404",
            "O que você procura não foi encontrado. Verifique se o endereço está correto ou tente buscar por algo diferente."),
    /**
     * <b>Conflito de dados.</b>
     * <p>
     * Code: 409
     * <p>
     * Message: Este dado já está sendo utilizado. Tente outro ou entre em contato com o suporte.
     */
    CONFLICT(
            "409",
            "Este dado já está sendo utilizado. Tente outro ou entre em contato com o suporte."),
    /**
     * <b>Erro interno do servidor.</b>
     * <p>
     * Code: 500
     * <p>
     * Message: Ops, estamos com problemas técnicos. Tente novamente mais tarde.
     */
    SERVER_ERROR(
            "500",
            "Ops, estamos com problemas técnicos. Tente novamente mais tarde.");

    private final String code;
    private final String message;

}
