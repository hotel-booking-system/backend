package br.com.hotel.booknow.app.bedrooms.service;

import br.com.hotel.booknow.app.bedrooms.domain.dto.BedroomRequest;
import br.com.hotel.booknow.app.bedrooms.domain.dto.BedroomResponse;
import br.com.hotel.booknow.app.bedrooms.domain.entity.Bedroom;
import br.com.hotel.booknow.app.bedrooms.domain.mapper.BedroomMapper;
import br.com.hotel.booknow.app.bedrooms.repository.BedroomRepository;
import br.com.hotel.booknow.core.exceptions.errors.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class BedroomService {

    private final BedroomRepository bedroomRepository;

    public BedroomResponse createBedroom(BedroomRequest request) {
        Bedroom bedroom = BedroomMapper.bedroomMapper().toBedroom(request);
        bedroomRepository.save(bedroom);
        return BedroomMapper.bedroomMapper().toBedroomResponse(bedroom);
    }

    public List<BedroomResponse> getAllBedrooms() {
        List<Bedroom> bedrooms = bedroomRepository.findAll();
        return BedroomMapper.bedroomMapper().toBedroomResponseList(bedrooms);
    }

    public BedroomResponse getBedroomById(Long id) {
        Bedroom bedroom = bedroomRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Bedroom not found with id :" + id));
        return BedroomMapper.bedroomMapper().toBedroomResponse(bedroom);
    }

    public BedroomResponse updateBedroom(Long id, BedroomRequest request) {
        Bedroom bedroom = bedroomRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Bedroom not found with id :" + id));
        bedroom.setNumber(request.getNumber());
        bedroom.setRoomType(request.getRoomType());
        bedroom.setCapacity(request.getCapacity());
        bedroom.setDailyRate(request.getDailyRate());
        bedroom.setDescription(request.getDescription());
        bedroom.setAvailable(request.getAvailable());
        bedroom.setHotelId(request.getHotelId());
        bedroom = bedroomRepository.save(bedroom);
        return BedroomMapper.bedroomMapper().toBedroomResponse(bedroom);
    }

    public void deleteBedroom(Long id) {
        Bedroom bedroom = bedroomRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Bedroom not found with id :" + id));
        bedroomRepository.delete(bedroom);
    }

}
