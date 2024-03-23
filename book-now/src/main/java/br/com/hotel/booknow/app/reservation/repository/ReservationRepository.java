package br.com.hotel.booknow.app.reservation.repository;

import br.com.hotel.booknow.app.reservation.domain.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
