package br.com.hotel.booknow.hotels.repository;

import br.com.hotel.booknow.hotels.domain.entity.Hotel;
import br.com.hotel.booknow.hotels.domain.entity.HotelType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

	boolean existsByHotelNameIgnoreCase(String hotelName);

	boolean existsByEmailIgnoreCase(String email);

	boolean existsByCnpjNumber(String cnpjNumber);

	List<Hotel> findByHotelNameContainingIgnoreCase(String hotelName);

	List<Hotel> findByCnpjNumberContainingIgnoreCase(String cnpj);

	List<Hotel> findByEmailContainingIgnoreCase(String cnpjNumber);

	List<Hotel> findByHotelType(HotelType hotelType);

}
