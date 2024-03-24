package br.com.hotel.booknow.app.reservation.repository;

import br.com.hotel.booknow.app.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByRoomIdAndCheckinDateAndCheckoutDate(Long roomId, LocalDate checkinDate, LocalDate checkoutDate);

}
