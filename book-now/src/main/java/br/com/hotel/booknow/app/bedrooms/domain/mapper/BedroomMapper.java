package br.com.hotel.booknow.app.bedrooms.domain.mapper;

import br.com.hotel.booknow.app.bedrooms.domain.entity.Bedroom;
import br.com.hotel.booknow.app.bedrooms.domain.dto.BedroomRequest;
import br.com.hotel.booknow.app.bedrooms.domain.dto.BedroomResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BedroomMapper {

	BedroomResponse toBedroomResponse(Bedroom bedroom);

	List<BedroomResponse> toBedroomResponseList(List<Bedroom> bedrooms);

	BedroomRequest toBedroomRequest(BedroomResponse bedroomResponse);

	Bedroom toBedroom(BedroomRequest bedroomRequest);

}
