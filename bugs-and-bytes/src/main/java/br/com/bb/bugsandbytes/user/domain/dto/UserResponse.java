package br.com.bb.bugsandbytes.user.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

	private Long id;

	private String name;

	private String username;

	private String password;

	private String phoneNumber;

	@CreationTimestamp
	private LocalDateTime registrationDate;

	@UpdateTimestamp
	private LocalDateTime updateDate;

}
