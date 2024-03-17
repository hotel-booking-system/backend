package br.com.bb.bugsandbytes.user.domain.dto;

import lombok.*;

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
	private String phoneNumber;
	private LocalDateTime registrationDate;
	private LocalDateTime updateDate;

}
