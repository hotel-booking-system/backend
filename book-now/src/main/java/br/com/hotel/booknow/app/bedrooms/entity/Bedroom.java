package br.com.hotel.booknow.app.bedrooms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * Classe responsável para armazenar e buscar os dados dos quartos no Banco de Dados
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bedrooms")
public class Bedroom {

	/**
	 * <b>Identificador único do quarto</b>
	 * <p>
	 * Gerado automaticamente
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long bedroomId;

	/**
	 * <b>Número do quarto</b>
	 * <p>
	 * Tamanho mínimo 1, máximo 10 caracteres
	 */
	@Size(min = 1, max = 10)
	@Column(nullable = false)
	private String number;

	/**
	 * <b>Tipo do quarto</b>
	 * <p>
	 * Enumeração com valores possíveis como:
	 * <ul>
	 *     <li>SOLTEIRO</li>
	 *     <li>CASAL</li>
	 *     <li>FAMILIA</li>
	 *     <li>LUXO</li>
	 * </ul>
	 */
	@Enumerated(EnumType.STRING)
	private RoomType roomType;

	/**
	 * <b>Capacidade do quarto</b>
	 * <p>
	 * Mínimo 1, máximo 10 pessoas.
	 */
	@Min(value = 1)
	@Max(value = 10)
	@Column(nullable = false)
	private Integer capacity;

	/**
	 * <b>Tarifa diária</b>
	 * <p>
	 * Mínimo 0.01, precisão de duas casas decimais
	 */
	@DecimalMin(value = "0.01")
	@Digits(integer = 10, fraction = 2)
	@Column(nullable = false)
	private BigDecimal dailyRate;

	/**
	 * <b>Descrição do quarto</b>
	 * <p>
	 * Tamanho mínimo 5 caracteres
	 */
	@Size(min = 5)
	@Column(nullable = false)
	private String description;

	/**
	 * <b>Disponibilidade do quarto</b>
	 * <p>
	 * Indica se o quarto está disponível (true) ou ocupado (false)
	 */
	@Column(nullable = false)
	private Boolean available;

	/**
	 * <b>Identificador do Hotel</b>
	 * <p>
	 * Identificador do hotel ao qual o quarto pertence.
	 */
	@Column(nullable = false)
	private Long hotelId;

}
