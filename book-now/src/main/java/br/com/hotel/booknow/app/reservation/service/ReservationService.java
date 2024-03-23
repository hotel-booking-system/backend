package br.com.hotel.booknow.app.reservation.service;

import br.com.hotel.booknow.app.bedrooms.service.BedroomService;
import br.com.hotel.booknow.app.reservation.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final BedroomService bedroomService;



}
