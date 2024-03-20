package br.com.hotel.booknow.app.bedrooms.domain.dto;

import br.com.hotel.booknow.app.bedrooms.domain.RoomType;
import br.com.hotel.booknow.app.hotels.domain.entity.Hotel;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BedroomRequest {

	private Integer number;
	private RoomType roomType;
	private Integer capacity;
	private BigDecimal dailyRate;
	private String description;
	private Boolean available;
	private Hotel hotel;

}
