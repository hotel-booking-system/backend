package br.com.hotel.booknow.app.bedrooms.service;

import br.com.hotel.booknow.app.bedrooms.dto.BedroomRequest;
import br.com.hotel.booknow.app.bedrooms.dto.BedroomResponse;
import br.com.hotel.booknow.app.bedrooms.entity.Bedroom;
import br.com.hotel.booknow.app.bedrooms.mapper.BedroomMapper;
import br.com.hotel.booknow.app.bedrooms.repository.BedroomRepository;
import br.com.hotel.booknow.app.reservation.entity.Reservation;
import br.com.hotel.booknow.app.reservation.repository.ReservationRepository;
import br.com.hotel.booknow.core.exceptions.errors.bedroom.RoomNotFoundException;
import br.com.hotel.booknow.core.exceptions.errors.generics.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class BedroomService {

    private final BedroomRepository bedroomRepository;
    private final ReservationRepository reservationRepository;

    @Transactional
    public BedroomResponse createBedroom(BedroomRequest request) {
        Bedroom bedroom = BedroomMapper.bedroomMapper().toBedroom(request);
        bedroomRepository.save(bedroom);
        return BedroomMapper.bedroomMapper().toBedroomResponse(bedroom);
    }

    @Transactional(readOnly = true)
    public List<BedroomResponse> getAllBedrooms() {
        List<Bedroom> bedrooms = bedroomRepository.findAll();
        return BedroomMapper.bedroomMapper().toBedroomResponseList(bedrooms);
    }

    @Transactional(readOnly = true)
    public BedroomResponse getBedroomById(Long id) {
        Bedroom bedroom = bedroomRepository.findById(id)
                .orElseThrow(RoomNotFoundException::new);
        return BedroomMapper.bedroomMapper().toBedroomResponse(bedroom);
    }

    @Transactional
    public BedroomResponse updateBedroom(Long id, BedroomRequest request) {
        Bedroom bedroom = bedroomRepository.findById(id)
                .orElseThrow(RoomNotFoundException::new);
        bedroom.setNumber(request.getNumber());
        bedroom.setRoomType(request.getRoomType());
        bedroom.setCapacity(request.getCapacity());
        bedroom.setDailyRate(request.getDailyRate());
        bedroom.setDescription(request.getDescription());
        bedroom.setAvailable(request.getAvailable());
        bedroom.setHotelId(request.getHotelId());
        bedroom = bedroomRepository.save(bedroom);
        return BedroomMapper.bedroomMapper().toBedroomResponse(bedroom);
    }

    @Transactional
    public void deleteBedroom(Long id) {
        Bedroom bedroom = bedroomRepository.findById(id)
                .orElseThrow(RoomNotFoundException::new);
        bedroomRepository.delete(bedroom);
    }

    public boolean isAvailable(Bedroom bedroom, LocalDate checkinDate, LocalDate checkoutDate) {
        // Buscar todas as reservas do quarto por período
        List<Reservation> reservations = reservationRepository
                .findByRoomIdAndCheckinDateAndCheckoutDate(bedroom.getId(), checkinDate, checkoutDate);
        // verificar se existe alguma reserva para o período
        return reservations.isEmpty();
    }
}
