package br.com.bb.bugsandbytes.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorStatus {

	BAD_REQUEST(400, "Bad Request", "O campo é obrigatório e deve ser preenchido corretamente."),
	USERNAME_NOT_FOUND(404, "Not Found", "Usuário não encontrado."),
	USERNAME_ALREADY_EXIST(409, "Conflict", "Usuário já cadastrado."),
	SERVER_ERROR(500, "Internal Server Error", "Erro Inesperado");

	private final int code;
	private final String status;
	private final String message;

}
