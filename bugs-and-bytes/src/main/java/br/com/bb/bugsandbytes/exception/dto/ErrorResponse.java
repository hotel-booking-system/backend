package br.com.bb.bugsandbytes.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ErrorResponse {
	private int code;
	private String status;
	private String message;
}
