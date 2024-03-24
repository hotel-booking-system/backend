package br.com.hotel.booknow.app.bedrooms.service.helper;

import br.com.hotel.booknow.app.bedrooms.dto.BedroomRequest;
import br.com.hotel.booknow.app.bedrooms.entity.Bedroom;
import br.com.hotel.booknow.app.bedrooms.repository.BedroomRepository;
import br.com.hotel.booknow.app.hotels.repository.HotelRepository;
import br.com.hotel.booknow.core.exceptions.errors.bedroom.InvalidRoomIdException;
import br.com.hotel.booknow.core.exceptions.errors.bedroom.RoomAlreadyExistsException;
import br.com.hotel.booknow.core.exceptions.errors.bedroom.RoomNotFoundException;
import br.com.hotel.booknow.core.exceptions.errors.bedroom.UnableToUpdateRoomException;
import br.com.hotel.booknow.core.exceptions.errors.hotel.HotelNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class BedroomHelper {

    private final BedroomRepository bedroomRepository;
    private final HotelRepository hotelRepository;

    public void validateHotelExists(Long hotelId) {
        if (!hotelRepository.existsById(hotelId)) {
            throw new HotelNotFoundException();
        }
    }

    public void validateRoomNumberUniqueness(BedroomRequest request) {
        if (bedroomRepository.existsByNumber(request.getNumber())) {
            throw new RoomAlreadyExistsException();
        }
    }

    public void validateBedroomId(Long id) {
        if (id == null || id <= 0) {
            throw new InvalidRoomIdException();
        }
    }

    public Bedroom findBedroomByIdOrThrow(Long id) {
        return bedroomRepository.findById(id)
                .orElseThrow(RoomNotFoundException::new);
    }

    public void updateBedroomFields(Bedroom bedroom, BedroomRequest request) {
        bedroom.setNumber(request.getNumber());
        bedroom.setRoomType(request.getRoomType());
        bedroom.setCapacity(request.getCapacity());
        bedroom.setDailyRate(request.getDailyRate());
        bedroom.setDescription(request.getDescription());
        bedroom.setAvailable(request.getAvailable());
        bedroom.setHotelId(request.getHotelId());
    }

    public Bedroom saveBedroomOrThrow(Bedroom bedroom) {
        try {
            return bedroomRepository.save(bedroom);
        } catch (UnableToUpdateRoomException ex) {
            log.error("Ocorreu um erro ao tentar atualizar o quarto: {}", ex.getMessage());
            throw ex;
        }
    }

}
