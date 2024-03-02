package br.com.bb.bugsandbytes.user.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@NotBlank(message = "O nome é obrigatório")
	@Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres")
	private String name;

	@Column(unique = true, nullable = false)
	@Email(message = "O username deve ser um email válido")
	@NotBlank(message = "O username é obrigatório")
	private String username;

	@Column(nullable = false)
	@NotBlank(message = "A senha é obrigatória")
	@Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
	private String password;

	@Column(nullable = false)
	@NotBlank(message = "Número de telefone é obrigatório")
	private String phoneNumber;

	@CreationTimestamp
	private LocalDateTime registrationDate;

	@UpdateTimestamp
	private LocalDateTime updateDate;

}
