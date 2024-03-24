package br.com.hotel.booknow.app.bedrooms.service;

import br.com.hotel.booknow.app.bedrooms.dto.BedroomRequest;
import br.com.hotel.booknow.app.bedrooms.dto.BedroomResponse;
import br.com.hotel.booknow.app.bedrooms.entity.Bedroom;
import br.com.hotel.booknow.app.bedrooms.mapper.BedroomMapper;
import br.com.hotel.booknow.app.bedrooms.repository.BedroomRepository;
import br.com.hotel.booknow.app.bedrooms.service.helper.BedroomHelper;
import br.com.hotel.booknow.core.exceptions.errors.bedroom.UnableToDeleteRoomException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class BedroomService {

    private final BedroomRepository bedroomRepository;
    private final BedroomHelper bedroomHelper;

    @Transactional
    public BedroomResponse createBedroom(BedroomRequest request) {
        bedroomHelper.validateRoomNumberUniqueness(request);
        Bedroom bedroom = BedroomMapper.bedroomMapper().toBedroom(request);
        bedroom = bedroomRepository.save(bedroom);
        return BedroomMapper.bedroomMapper().toBedroomResponse(bedroom);
    }

    @Transactional(readOnly = true)
    public List<BedroomResponse> getAllBedrooms() {
        List<Bedroom> bedrooms = bedroomRepository.findAll();
        return BedroomMapper.bedroomMapper().toBedroomResponseList(bedrooms);
    }

    @Transactional(readOnly = true)
    public BedroomResponse getBedroomById(Long id) {
        bedroomHelper.validateBedroomId(id);
        return BedroomMapper.bedroomMapper()
                .toBedroomResponse(bedroomHelper.findBedroomByIdOrThrow(id));
    }

    @Transactional
    public BedroomResponse updateBedroom(Long id, BedroomRequest request) {
        bedroomHelper.validateBedroomId(id);
        Bedroom bedroom = bedroomHelper.findBedroomByIdOrThrow(id);
        bedroomHelper.updateBedroomFields(bedroom, request);
        return BedroomMapper.bedroomMapper()
                .toBedroomResponse(bedroomHelper.saveBedroomOrThrow(bedroom));
    }

    @Transactional
    public void deleteBedroom(Long id) {
        bedroomHelper.validateBedroomId(id);
        Bedroom bedroom = bedroomHelper.findBedroomByIdOrThrow(id);
        try {
            bedroomRepository.delete(bedroom);
        } catch (UnableToDeleteRoomException ex) {
            log.error("Ocorreu um erro ao tentar remover o quarto: {}", ex.getMessage());
            throw ex;
        }
    }

    @Transactional(readOnly = true)
    public List<BedroomResponse> getAllBedroomByHotelId(Long hotelId) {
        bedroomHelper.validateHotelExists(hotelId);
        List<Bedroom> bedrooms = bedroomRepository.findByHotelId(hotelId);
        return BedroomMapper.bedroomMapper().toBedroomResponseList(bedrooms);
    }

}
