package br.com.hotel.booknow.app.bedrooms.mapper;

import br.com.hotel.booknow.app.bedrooms.dto.BedroomRequest;
import br.com.hotel.booknow.app.bedrooms.dto.BedroomResponse;
import br.com.hotel.booknow.app.bedrooms.entity.Bedroom;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BedroomMapper {

    BedroomMapper INSTANCE = Mappers.getMapper(BedroomMapper.class);

    static BedroomMapper bedroomMapper() {
        return INSTANCE;
    }

    BedroomResponse toBedroomResponse(Bedroom bedroom);

    List<BedroomResponse> toBedroomResponseList(List<Bedroom> bedrooms);

    Bedroom toBedroom(BedroomRequest bedroomRequest);

}
