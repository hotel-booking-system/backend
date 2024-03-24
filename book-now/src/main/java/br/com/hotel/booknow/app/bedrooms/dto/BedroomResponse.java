package br.com.hotel.booknow.app.bedrooms.dto;

import br.com.hotel.booknow.app.bedrooms.entity.RoomType;
import lombok.*;

import java.math.BigDecimal;

/**
 * Classe responsável por retornar os dados do Quarto
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BedroomResponse {

	private Long bedroomId;
	private String number;
	private RoomType roomType;
	private Integer capacity;
	private BigDecimal dailyRate;
	private String description;
	private Boolean available;
	private Long hotelId;

}
