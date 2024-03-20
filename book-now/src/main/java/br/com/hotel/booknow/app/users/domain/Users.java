package br.com.hotel.booknow.app.users.domain;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users {

	private Long id;
	private String fullName;
	private String email;
	private String password;
	private String phoneNumber;
	private Date createDt;

}
