package br.com.hotel.booknow.app.hotels.dto.response;

import br.com.hotel.booknow.app.hotels.entity.HotelType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelResponse {

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
	private LocalDateTime updatedAt;
	private List<Long> bedroomIds;


}
