package br.com.hotel.booknow.app.reservation.service;

import br.com.hotel.booknow.app.bedrooms.entity.Bedroom;
import br.com.hotel.booknow.app.reservation.dto.ReservationRequest;
import br.com.hotel.booknow.app.reservation.dto.ReservationResponse;
import br.com.hotel.booknow.app.reservation.entity.Reservation;
import br.com.hotel.booknow.app.reservation.helper.ReservationValidator;
import br.com.hotel.booknow.app.reservation.mapper.ReservationMapper;
import br.com.hotel.booknow.app.reservation.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationValidator reservationValidator;

    public ReservationResponse createReservation(ReservationRequest request) {
        reservationValidator.validate(request);
        Bedroom bedroom = reservationValidator.findRoomById(request.getRoomId());
        Reservation reservation = reservationValidator.createReservationEntity(bedroom, request);
        reservation = reservationRepository.save(reservation);
        return ReservationMapper.reservationMapper().toReservationResponse(reservation);
    }

}
