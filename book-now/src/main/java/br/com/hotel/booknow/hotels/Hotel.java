package br.com.hotel.booknow.hotels;

import br.com.hotel.booknow.bedrooms.Room;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hotels")
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, unique = true)
	@NotEmpty(message = "Campo obrigatório")
	@Size(min = 3, message = "O campo de ter no mínimo 3 caracteres")
	private String hotelName;

	@Column(nullable = false)
	@NotEmpty(message = "Campo obrigatório")
	private String hotelLocation;

	@Column(nullable = false)
	@Email(message = "Email inválido")
	@NotEmpty(message = "Campo obrigatório")
	private String email;

	@Column(nullable = false)
	@NotEmpty(message = "Campo obrigatório")
	private String phoneNumber;

	private String description;

	@Column(nullable = false)
	@NotNull(message = "Campo obrigatório")
	@Min(value = 1, message = "O campo deve ter no mínimo 1 quarto cadastrado")
	private Integer totalNumberOfRooms;

	@OneToMany
	private List<Room> rooms; // lista de quartos - 1 hotel para muitos quartos

}
