package br.com.hotel.booknow.room;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room {

	private Long id;
	private String roomNumber;
	private String roomType;
	private BigDecimal dailyRate;
	private String amenities;
	private String description;
	private String roomImages;
	private String availability;
	private String hotelId;

}
