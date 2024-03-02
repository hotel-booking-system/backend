package br.com.bb.bugsandbytes.user.domain.entity;

import br.com.bb.bugsandbytes.validation.annotations.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@MinLength
	@OnlyLetters
	@Size(min = 3)
	@NotNullAndNotEmpty
	@Column(nullable = false)
	private String name;

	@Email
	@NotNullAndNotEmpty
	@Column(unique = true, nullable = false)
	private String username;

	@StrongPassword
	@NotNullAndNotEmpty
	@Column(nullable = false)
	private String password;

	@PhoneNumber
	@NotNullAndNotEmpty
	@Column(nullable = false)
	private String phoneNumber;

	@CreationTimestamp
	private LocalDateTime registrationDate;

	@UpdateTimestamp
	private LocalDateTime updateDate;

}
