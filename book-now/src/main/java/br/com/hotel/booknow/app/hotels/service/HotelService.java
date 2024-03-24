package br.com.hotel.booknow.app.hotels.service;

import br.com.hotel.booknow.app.hotels.domain.dto.request.HotelRequest;
import br.com.hotel.booknow.app.hotels.domain.dto.response.HotelResponse;
import br.com.hotel.booknow.app.hotels.domain.entity.Hotel;
import br.com.hotel.booknow.app.hotels.domain.mapper.HotelMapper;
import br.com.hotel.booknow.app.hotels.repository.HotelRepository;
import br.com.hotel.booknow.core.exceptions.errors.hotel.HotelNotFoundException;
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

    @Transactional
    public HotelResponse createHotel(HotelRequest request) {
        Hotel hotel = HotelMapper.hotelMapper().toHotel(request);
        hotelRepository.save(hotel);
        return HotelMapper.hotelMapper().toHotelResponse(hotel);
    }

    @Transactional(readOnly = true)
    public List<HotelResponse> getAllHotels() {
        List<Hotel> hotelList = hotelRepository.findAll();
        return HotelMapper.hotelMapper().toHotelResponseList(hotelList);
    }

    @Transactional(readOnly = true)
    public HotelResponse getHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(HotelNotFoundException::new);
        return HotelMapper.hotelMapper().toHotelResponse(hotel);
    }

    @Transactional
    public HotelResponse updateHotel(Long id, HotelRequest request) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(HotelNotFoundException::new);
        hotel.setHotelName(request.getHotelName());
        hotel.setLocation(request.getLocation());
        hotel.setPhoneNumber(request.getPhoneNumber());
        hotel.setEmail(request.getEmail());
        hotel.setHotelType(request.getHotelType());
        hotel.setCnpjNumber(request.getCnpjNumber());
        hotel.setDescription(request.getDescription());
        hotel.setRoomCount(request.getRoomCount());
        hotel = hotelRepository.save(hotel);
        return HotelMapper.hotelMapper().toHotelResponse(hotel);
    }

    @Transactional
    public void deleteHotel(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(HotelNotFoundException::new);
        hotelRepository.delete(hotel);
    }

}