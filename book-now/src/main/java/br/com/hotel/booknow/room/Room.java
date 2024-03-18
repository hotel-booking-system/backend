package br.com.hotel.booknow.bedrooms;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bedroom {

	private Long id;

	// informações básicas
	private String numero; // número que identifica o quarto
	private String tipoQuarto; // solteiro, casal, casal + filhos
	private String capacidade;
	private BigDecimal valorDiaria;

	// infraestrutura
	private String qtdeCamas;
	private String tamanhoQuarto; // em metros quadrados (opcional)
	private String vista; // mar, cidade, jardim etc.
	private String servicosDisponiveis; // ar condicionado, frigobar, wifi, tv

	// informações adicionais
	private String descricao; // TEXT
	private String fotos;
	private String disponibilidade; // status de disponibilidade do quarto (disponivel, reservado, ocupado)
	private String observacoes;

	// relacionamento com a tabela hotels
	private String hotel; // FOREIGN KEY references hotels.hotel_id

}
