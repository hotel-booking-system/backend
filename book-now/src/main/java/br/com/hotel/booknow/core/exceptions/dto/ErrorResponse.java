package br.com.hotel.booknow.exceptions.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class ErrorResponse {

	private HttpStatus status;
	private String error;
	private String message;

}
