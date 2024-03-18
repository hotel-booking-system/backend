package br.com.hotel.booknow.bedrooms;

import br.com.hotel.booknow.hoteis.Hotel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rooms")
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, unique = true)
	@NotEmpty(message = "Campo obrigatório")
	private Integer roomNumber;

	@Column(nullable = false)
	@NotEmpty(message = "Campo obrigatório")
	private String roomType;

	private String dailyRate;

	@Column(nullable = false)
	@NotEmpty(message = "Campo obrigatório")
	private String amenities;

	private String description;

	private String availability;

	@ManyToOne
	private Hotel hotelId; // vários quartos para um hotel

}
