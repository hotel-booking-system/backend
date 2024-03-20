package br.com.hotel.booknow.app.bedrooms.repository;

import br.com.hotel.booknow.app.bedrooms.domain.Bedroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BedroomRepository extends JpaRepository<Bedroom, Long> {

	boolean existsByNumber(String number);

	Optional<Bedroom> findByNumberAndHotelId(String number, Long id);


}
