package br.com.hotel.booknow.app.hotels.repository;

import br.com.hotel.booknow.app.hotels.domain.entity.Hotel;
import br.com.hotel.booknow.app.hotels.domain.entity.HotelType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

	boolean existsByHotelNameIgnoreCase(String name);

	boolean existsByEmailIgnoreCase(String email);

	boolean existsByCnpjNumber(String cnpj);

	Optional<Hotel> findById(Long id);

	List<Hotel> findByHotelType(HotelType type);

	List<Hotel> findByHotelNameContainingIgnoreCase(String name);

	List<Hotel> findByCnpjNumberContainingIgnoreCase(String cnpj);

	List<Hotel> findByEmailContainingIgnoreCase(String email);

	boolean existsByEmail(String email);
}
