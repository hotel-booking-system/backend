package br.com.hotel.booknow.app.reservation.service.mapper;

import br.com.hotel.booknow.app.reservation.dto.ReservationRequest;
import br.com.hotel.booknow.app.reservation.dto.ReservationResponse;
import br.com.hotel.booknow.app.reservation.entity.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReservationMapper {

    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    static ReservationMapper reservationMapper() {
        return INSTANCE;
    }

    Reservation toEntity(ReservationRequest request);

    ReservationResponse toReservationResponse(Reservation reservation);

}
