package br.com.bb.bugsandbytes.user.domain.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {

	private String name;
	private String phoneNumber;
	private String password;

}
