package br.com.hotel.booknow.hotels;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

/**
 * Representa a entidade Hotel do sistema.
 * <p>
 * Armazena informações sobre hotéis cadastrados no sistema, incluindo nome, localização, telefone, e-mail, tipo de
 * hotel, CNPJ, descrição e quantidade de quartos.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hotels")
@Validated
public class Hotel {

	/**
	 * <b>Identificador único do hotel (chave primária)</b>
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * <b>Nome do hotel</b>
	 * <p>
	 * Não pode ser vazio nem conter apenas espaços em branco <br> O nome do hotel deve ter no mínimo 3 caracteres <br>
	 * Único, não nulo
	 */
	@NotBlank(message = "{hotelName.blank}")
	@Size(min = 3, message = "{hotelName.size}")
	@Column(unique = true, nullable = false)
	private String hotelName;

	/**
	 * <b>Localização do hotel</b>
	 * <p>
	 * Não pode ser vazio nem conter apenas espaços em branco <br> A localização do hotel deve ter no mínimo 10
	 * caracteres <br> Não nulo.
	 */
	@NotBlank(message = "{location.blank}")
	@Size(min = 10, message = "{location.size}")
	@Column(nullable = false)
	private String location;

	/**
	 * <b>Número de telefone do hotel</b>
	 * <p>
	 * Não pode ser vazio nem conter apenas espaços em branco <br> O número de telefone deve ter no mínimo 4 e no máximo
	 * 15 caracteres. <br> O sinal + é opcional. <br> O número de telefone deve seguir o padrão +XXXXXXXXXXXXX ou
	 * XXXXXXXXXXXXXX ou +XXX ou XXXX <br> Não nulo.
	 */
	@NotBlank(message = "{phoneNumber.blank}")
	@Pattern(regexp = "^\\+?\\d{4,15}$", message = "{phoneNumber.pattern}")
	@Column(nullable = false)
	private String phoneNumber;

	/**
	 * <b>Email do hotel</b>
	 * <p>
	 * Não pode ser vazio nem conter apenas espaços em branco <br>
	 * <code>@Email</code>: Formato de Email válido. <br>
	 * Único e não nulo.
	 */
	@NotBlank(message = "{email.blank}")
	@Email(message = "{email.email}")
	@Column(unique = true, nullable = false)
	private String email;

	/**
	 * <b>Tipo do hotel (ENUM)</b>
	 * <p>
	 * Não pode ser nulo <br>
	 * <code>@Enumerated(EnumType.STRING)</code>: Armazenado como string
	 */
	@NotNull(message = "{hotelType.blank}")
	@Enumerated(EnumType.STRING)
	private HotelType hotelType;

	/**
	 * <b>CNPJ do hotel</b>
	 * <p>
	 * Não pode ser vazio nem conter apenas espaços em branco <br> O CNPJ deve ter 14 caracteres <br>
	 * <code>@CNPJ</code>: Formato de CNPJ válido.
	 * Único e não nulo.
	 */
	@NotBlank(message = "{cnpjNumber.blank}")
	@Size(min = 14, max = 14, message = "{cnpjNumber.size}")
	@CNPJ(message = "{cnpjNumber.cnpj}")
	@Column(unique = true, nullable = false)
	private String cnpjNumber;

	/**
	 * <b>Descrição do hotel</b>
	 * <p>
	 * Não pode ser vazio nem conter apenas espaços em branco <br> A descrição do hotel deve ter no mínimo 3 caracteres
	 * <br> Não nulo.
	 */
	@NotBlank(message = "{description.blank}")
	@Size(min = 3, message = "{description.size}")
	@Column(nullable = false)
	private String description;

	/**
	 * <b>Quantidade de quartos do hotel</b>
	 * <p>
	 * Não pode ser nulo <br> A quantidade de quartos deve ser maior ou igual a 1 <br> Não nulo.
	 */
	@NotNull(message = "{roomCount.notNull}")
	@Min(value = 1, message = "{roomCount.min}")
	@Column(nullable = false)
	private Integer roomCount;

	/**
	 * <b>Data de criação do hotel</b> (Preenchida automaticamente)
	 */
	@CreationTimestamp
	private LocalDateTime createdAt;

	/**
	 * <b>Data de atualização do hotel</b> (Preenchida automaticamente)
	 */
	@UpdateTimestamp
	private LocalDateTime updatedAt;

}
