package br.com.hotel.booknow.app.hotels.domain.dto.request;

import br.com.hotel.booknow.app.hotels.domain.entity.HotelType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelRequest {

	private String hotelName;

	private String location;

	@Pattern(regexp = "^\\+?\\d{4,15}$")
	private String phoneNumber;

	@Email
	private String email;

	private HotelType hotelType;

	@CNPJ
	private String cnpjNumber;

	private String description;

	private Integer roomCount;

}
