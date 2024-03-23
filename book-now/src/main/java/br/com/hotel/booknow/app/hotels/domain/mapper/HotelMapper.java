package br.com.hotel.booknow.app.hotels.domain.mapper;

import br.com.hotel.booknow.app.hotels.domain.dto.request.HotelRequest;
import br.com.hotel.booknow.app.hotels.domain.dto.response.HotelResponse;
import br.com.hotel.booknow.app.hotels.domain.entity.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HotelMapper {

    HotelMapper INSTANCE = Mappers.getMapper(HotelMapper.class);

    static HotelMapper hotelMapper() {
        return INSTANCE;
    }

    Hotel toHotel(HotelRequest hotelRequest);

    HotelResponse toHotelResponse(Hotel hotel);

    List<HotelResponse> toHotelResponseList(List<Hotel> hotel);

}
