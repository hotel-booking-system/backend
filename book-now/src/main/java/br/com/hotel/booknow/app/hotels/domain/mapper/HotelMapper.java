package br.com.hotel.booknow.app.hotels.domain.mapper;

import br.com.hotel.booknow.app.hotels.domain.dto.request.HotelRequest;
import br.com.hotel.booknow.app.hotels.domain.dto.response.HotelResponse;
import br.com.hotel.booknow.app.hotels.domain.entity.Hotel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HotelMapper {

    HotelResponse toHotelResponse(Hotel hotel);

    Hotel toEntityHotel(HotelRequest hotelRequest);

}
