package br.com.hotel.booknow.bedrooms.repository;

import br.com.hotel.booknow.bedrooms.domain.Bedroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BedroomRepository extends JpaRepository<Bedroom, Long> {

//	List<Bedroom> findByTipo(RoomType tipo);

//	List<Bedroom> findByDataDisponivelAfter(LocalDateTime data);

//	List<Bedroom> findByValorDiariaLessThan(BigDecimal valor);

	boolean existsByNumber(Integer number);

	Optional<Bedroom> findByNumberAndHotelId(Integer number, Long id);

	boolean existsByNumberAndHotelId(Integer number, Long id);
}
