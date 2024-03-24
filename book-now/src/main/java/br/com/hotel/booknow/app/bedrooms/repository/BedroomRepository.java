package br.com.hotel.booknow.app.bedrooms.repository;

import br.com.hotel.booknow.app.bedrooms.entity.Bedroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BedroomRepository extends JpaRepository<Bedroom, Long> {

    boolean existsByNumber(String number);

    List<Bedroom> findByHotelId(Long hotelId);

}
