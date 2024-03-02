package br.com.bb.bugsandbytes.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorStatus {

	USERNAME_NOT_FOUND(404, "Conflict", "Usuário não encontrado."),
	USERNAME_ALREADY_EXIST(409, "Conflict", "Usuário já cadastrado.");

	private final int code;
	private final String status;
	private final String message;

}
