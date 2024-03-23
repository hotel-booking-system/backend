package br.com.hotel.booknow.app.users.domain.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String fullName;
    private String usernames;
    private String phoneNumber;
    private LocalDateTime createDt;

}
