package br.com.hotel.booknow.app.hotels.domain.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomResponse {

	private Long bedroomId;
	private String number;
	private RoomType roomType;
	private Integer capacity;
	private BigDecimal dailyRate;
	private String description;
	private Boolean available;

}
