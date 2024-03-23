package br.com.hotel.booknow.app.users.domain.entity;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users {

	private Long id;

	private String fullName;

	private String usernames;

	private String password;

	private String phoneNumber;

	private LocalDateTime createDt;

}
