package br.com.bb.bugsandbytes.user.domain.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {

	private String name;
	private String username;
	private String password;
	private String phoneNumber;

}
