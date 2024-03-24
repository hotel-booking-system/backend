package br.com.hotel.booknow.app.hotels.service;

import br.com.hotel.booknow.app.hotels.dto.request.HotelRequest;
import br.com.hotel.booknow.app.hotels.dto.response.HotelResponse;
import br.com.hotel.booknow.app.hotels.entity.Hotel;
import br.com.hotel.booknow.app.hotels.mapper.HotelMapper;
import br.com.hotel.booknow.app.hotels.repository.HotelRepository;
import br.com.hotel.booknow.core.exceptions.errors.bedroom.UnableToUpdateRoomException;
import br.com.hotel.booknow.core.exceptions.errors.hotel.HotelDataInvalidException;
import br.com.hotel.booknow.core.exceptions.errors.hotel.HotelNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class HotelHelper {

    private final HotelRepository hotelRepository;

    public Hotel findHotelById(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(HotelNotFoundException::new);
    }

    public void validateHotelData(Hotel hotel) throws HotelDataInvalidException {
        if (hotel.getHotelName() == null || hotel.getHotelName().isEmpty()) {
            throw new HotelDataInvalidException();
        }
    }

    public static void updateHotelData(HotelRequest request, Hotel hotel) {
        hotel.setHotelName(request.getHotelName());
        hotel.setLocation(request.getLocation());
        hotel.setPhoneNumber(request.getPhoneNumber());
        hotel.setEmail(request.getEmail());
        hotel.setHotelType(request.getHotelType());
        hotel.setCnpjNumber(request.getCnpjNumber());
        hotel.setDescription(request.getDescription());
        hotel.setRoomCount(request.getRoomCount());
    }

    public HotelResponse saveHotel(Hotel hotel) {
        try {
            hotel = hotelRepository.save(hotel);
            return HotelMapper.hotelMapper().toHotelResponse(hotel);
        } catch (UnableToUpdateRoomException ex) {
            log.error("Erro ao tentar atualizar Hotel.");
            throw ex;
        }
    }

}
