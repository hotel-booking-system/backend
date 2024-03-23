package br.com.hotel.booknow.app.login.domain;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Login {

    private Long id;
    private Long username;
    private Long password;

}
