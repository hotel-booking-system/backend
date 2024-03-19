package br.com.hotel.booknow.statistic;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class StatisticService {

	public Statistic create() {

		return Statistic.builder()
				.count(1L)
				.avg(BigDecimal.valueOf(10))
				.min(BigDecimal.valueOf(10))
				.max(BigDecimal.valueOf(10))
				.sum(BigDecimal.valueOf(10))
				.build();

	}

	// https://mari-azevedo.medium.com/construindo-uma-api-restful-com-java-e-spring-framework-46b74371d107

}
