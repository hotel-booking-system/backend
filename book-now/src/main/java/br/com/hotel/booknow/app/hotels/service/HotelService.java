package br.com.hotel.booknow.app.hotels.service;

import br.com.hotel.booknow.app.hotels.dto.request.HotelRequest;
import br.com.hotel.booknow.app.hotels.dto.response.HotelResponse;
import br.com.hotel.booknow.app.hotels.entity.Hotel;
import br.com.hotel.booknow.app.hotels.mapper.HotelMapper;
import br.com.hotel.booknow.app.hotels.repository.HotelRepository;
import br.com.hotel.booknow.core.exceptions.errors.bedroom.UnableToUpdateRoomException;
import br.com.hotel.booknow.core.exceptions.errors.hotel.UnableToDeleteHotelException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author juliane.maran
 * @since 19-03-2024
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;
    private final HotelHelper hotelHelper;

    @Transactional
    public HotelResponse createHotel(HotelRequest request) {
        Hotel hotel = HotelMapper.hotelMapper().toHotel(request);
        hotelHelper.validateHotelData(hotel);
        return hotelHelper.saveHotel(hotel);
    }

    @Transactional(readOnly = true)
    public List<HotelResponse> getAllHotels() {
        List<Hotel> hotelList = hotelRepository.findAll();
        return HotelMapper.hotelMapper().toHotelResponseList(hotelList);
    }

    @Transactional(readOnly = true)
    public HotelResponse getHotelById(Long id) {
        Hotel hotel = hotelHelper.findHotelById(id);
        return HotelMapper.hotelMapper().toHotelResponse(hotel);
    }

    @Transactional
    public HotelResponse updateHotel(Long id, HotelRequest request) {
        Hotel hotel = hotelHelper.findHotelById(id);
        HotelHelper.updateHotelData(request, hotel);
        return hotelHelper.saveHotel(hotel);

    }

    @Transactional
    public void deleteHotel(Long id) {
        Hotel hotel = hotelHelper.findHotelById(id);
        try {
            hotelRepository.delete(hotel);
        } catch (UnableToDeleteHotelException ex) {
            log.error("Erro ao tentar excluir Hotel.");
            throw ex;
        }
    }

}