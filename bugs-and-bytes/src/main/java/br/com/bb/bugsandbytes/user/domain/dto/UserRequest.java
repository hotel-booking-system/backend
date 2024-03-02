package br.com.bb.bugsandbytes.user.domain.dto;

import br.com.bb.bugsandbytes.validation.annotations.NotNullAndNotEmpty;
import jakarta.validation.constraints.Email;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

	private String name;
	private String username;
	private String password;
	private String phoneNumber;

}
