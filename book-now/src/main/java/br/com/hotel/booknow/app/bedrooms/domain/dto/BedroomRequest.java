package br.com.hotel.booknow.app.bedrooms.domain.dto;

import br.com.hotel.booknow.app.bedrooms.domain.RoomType;
import lombok.*;

import java.math.BigDecimal;

/**
 * Classe responsável por receber as informações do Usuário
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BedroomRequest {

	private String number;
	private RoomType roomType;
	private Integer capacity;
	private BigDecimal dailyRate;
	private String description;
	private Boolean available;
	private Long hotelId;

}
