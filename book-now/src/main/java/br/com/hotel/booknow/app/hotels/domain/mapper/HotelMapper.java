package br.com.hotel.booknow.app.hotels.domain.mapper;

import br.com.hotel.booknow.app.bedrooms.domain.dto.BedroomRequest;
import br.com.hotel.booknow.app.bedrooms.domain.dto.BedroomResponse;
import br.com.hotel.booknow.app.bedrooms.domain.entity.Bedroom;
import br.com.hotel.booknow.app.hotels.domain.dto.request.HotelRequest;
import br.com.hotel.booknow.app.hotels.domain.dto.response.HotelResponse;
import br.com.hotel.booknow.app.hotels.domain.entity.Hotel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HotelMapper {

	HotelResponse toHotelResponse(Hotel hotel);

	BedroomResponse toBedroomResponse(Bedroom bedroom);

	Hotel toEntityHotel(HotelRequest hotelRequest);

	Bedroom toEntityBedroom(BedroomRequest bedroomRequest);


}
