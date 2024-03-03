package br.com.bb.bugsandbytes.exception.dto;

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
