package br.com.hotel.booknow.core.exceptions.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum que define os códigos de erro para Hotéis
 *
 * @author juliane.maran
 */
@Getter
@AllArgsConstructor
public enum HotelErrorCodes {

    /**
     * 400 - Bad Request
     */
    HOTEL_DATA_INVALID(
            "Dados do hotel inválidos.",
            "Verifique se todos os campos do hotel foram preenchidos corretamente e tente novamente."),

    /**
     * 400 - Bad Request
     */
    INVALID_HOTEL_ID(
            "ID do hotel inválido.",
            "O ID do hotel informado não é válido."),

    /**
     * 403 - Hotel inativo
     */
    HOTEL_INACTIVE(
            "Hotel inativo.",
            "O hotel que você procura está inativo. Entre em contato com o suporte para mais informações."),

    /**
     * 404 - Not Found
     */
    HOTEL_NOT_FOUND(
            "Hotel não encontrado.",
            "O hotel que você procura não existe."),

    /**
     * 409 - Conflict
     */
    HOTEL_ALREADY_REGISTERED(
            "Hotel já cadastrado.",
            "Um hotel com o mesmo nome ou CNPJ já está cadastrado."),

    /**
     * 500 - Internal Server Error
     */
    UNABLE_TO_UPDATE_HOTEL(
            "Falha ao atualizar hotel.",
            "Ocorreu um erro ao tentar atualizar o hotel. Por favor, tente novamente mais tarde."),

    /**
     * 500 - Internal Server Error
     */
    UNABLE_TO_DELETE_HOTEL(
            "Falha ao remover hotel.",
            "Ocorreu um erro ao tentar remover o hotel. Por favor, tente novamente mais tarde.");

    private final String error;
    private final String message;

}
