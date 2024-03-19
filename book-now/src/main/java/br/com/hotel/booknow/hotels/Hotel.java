package br.com.hotel.booknow.hotels;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hotels")
@Validated
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "{hotelName.blank}")
	@Size(min = 3, message = "{hotelName.size}")
	@Column(unique = true, nullable = false)
	private String hotelName;

	@NotBlank(message = "{location.blank}")
	@Size(min = 10, message = "{location.size}")
	@Column(nullable = false)
	private String location;

	@NotBlank(message = "{phoneNumber.blank}")
	@Pattern(regexp = "^\\+?\\d{4,15}$", message = "{phoneNumber.pattern}")
	@Column(nullable = false)
	private String phoneNumber;

	@NotBlank(message = "{email.blank}")
	@Email(message = "{email.email}")
	@Column(unique = true, nullable = false)
	private String email;

	@NotNull(message = "{hotelType.blank}")
	@Enumerated(EnumType.STRING)
	private HotelType hotelType;

	@NotBlank(message = "{cnpjNumber.blank}")
	@Size(min = 14, max = 14, message = "{cnpjNumber.size}")
	@CNPJ(message = "{cnpjNumber.cnpj}")
	@Column(unique = true, nullable = false)
	private String cnpjNumber;

	@NotBlank(message = "{description.blank}")
	@Size(min = 3, message = "{description.size}")
	@Column(nullable = false)
	private String description;

	@NotNull(message = "{roomCount.notNull}")
	@Min(value = 1, message = "{roomCount.min}")
	@Column(nullable = false)
	private Integer roomCount;

	@CreationTimestamp
	private LocalDateTime createdAt;

	@UpdateTimestamp
	private LocalDateTime updatedAt;

}
