package br.com.bb.bugsandbytes.user.domain;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

	@NotBlank(message = "O nome é obrigatório")
	@Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres")
	private String name;

	@Column(unique = true)
	@Email(message = "O username deve ser um email válido")
	@NotBlank(message = "O username é obrigatório")
	private String username;

	@NotBlank(message = "A senha é obrigatória")
	@Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
	private String password;

}
