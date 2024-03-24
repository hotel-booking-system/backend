package br.com.hotel.booknow.app.hotels.repository;

import br.com.hotel.booknow.app.hotels.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    Optional<Hotel> findById(Long id);

}
