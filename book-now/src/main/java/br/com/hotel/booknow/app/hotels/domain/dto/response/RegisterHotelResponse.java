package br.com.hotel.booknow.app.hotels.domain.dto.response;

import br.com.hotel.booknow.app.hotels.domain.entity.HotelType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterHotelResponse {

	private Long id;
	private String hotelName;
	private String location;
	private String phoneNumber;
	private String email;
	private HotelType hotelType;
	private String cnpjNumber;
	private String description;
	private Integer roomCount;
	private LocalDateTime createdAt;

}
