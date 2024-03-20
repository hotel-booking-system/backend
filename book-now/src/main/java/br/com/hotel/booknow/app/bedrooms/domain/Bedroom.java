package br.com.hotel.booknow.bedrooms.domain;

import br.com.hotel.booknow.hotels.domain.entity.Hotel;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bedrooms")
public class Bedroom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Min(value = 1)
	@Max(value = 5)
	@Column(unique = true, nullable = false)
	private Integer number;

	@NotNull
	@Enumerated(EnumType.STRING)
	private RoomType roomType;

	@NotNull
	@Min(value = 1)
	@Max(value = 3)
	@Column(nullable = false)
	private Integer capacity;

	@NotNull
	@DecimalMin(value = "0.01")
	@Digits(integer = 10, fraction = 2)
	@Column(nullable = false)
	private BigDecimal dailyRate;

	@NotBlank
	@Size(min = 5)
	@Column(nullable = false)
	private String description;

	@NotNull
	@Column(nullable = false)
	private Boolean available;

	@ManyToOne
	@JoinColumn(name = "hotel_id", nullable = false)
	private Hotel hotel;

}
