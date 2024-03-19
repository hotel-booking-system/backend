package br.com.hotel.booknow.statistic;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Statistic {

	// soma total
	private BigDecimal sum;

	// média dos valores
	private BigDecimal avg;

	// maior valor
	private BigDecimal max;

	// menor valor
	private BigDecimal min;

	// número total
	private Long count;

}
