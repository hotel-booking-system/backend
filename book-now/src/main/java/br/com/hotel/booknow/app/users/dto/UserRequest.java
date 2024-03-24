package br.com.hotel.booknow.app.users.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

	private String fullName;
	private String usernames;
	private String password;
	private String phoneNumber;

}
