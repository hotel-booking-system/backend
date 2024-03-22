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

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class BedroomService {

	private final BedroomRepository bedroomRepository;

	private final BedroomMapper bedroomMapper;

//	private final BedroomClient bedroomClient;

	// CREATE
	public BedroomResponse createBedroom(BedroomRequest request) {
		Bedroom bedroom = bedroomMapper.toBedroom(request);
		bedroomRepository.save(bedroom);
		return bedroomMapper.toBedroomResponse(bedroom);
	}

	// READ - Listar
	public List<BedroomResponse> getAllBedrooms() {

		List<Bedroom> bedrooms = bedroomRepository.findAll();
		List<BedroomResponse> bedroomResponses = new ArrayList<>();

		for (Bedroom bedroom : bedrooms) {
			bedroomResponses.add(BedroomResponse.builder()
					.bedroomId(bedroom.getId())
					.number(bedroom.getNumber())
					.roomType(bedroom.getRoomType())
					.capacity(bedroom.getCapacity())
					.dailyRate(bedroom.getDailyRate())
					.description(bedroom.getDescription())
					.available(bedroom.getAvailable())
					.hotelId(bedroom.getHotelId())
					.build());
		}

		return bedroomResponses;

	}

	// READ - Buscar
	public BedroomResponse getBedroomById(Long id) {

		Bedroom bedroom = bedroomRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Bedroom not found with id :" + id));

		return BedroomResponse.builder()
				.bedroomId(bedroom.getId())
				.number(bedroom.getNumber())
				.roomType(bedroom.getRoomType())
				.capacity(bedroom.getCapacity())
				.dailyRate(bedroom.getDailyRate())
				.description(bedroom.getDescription())
				.available(bedroom.getAvailable())
				.hotelId(bedroom.getHotelId())
				.build();
	}

	// UPDATE - Atualizar
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

		return BedroomResponse.builder()
				.bedroomId(bedroom.getId())
				.number(bedroom.getNumber())
				.roomType(bedroom.getRoomType())
				.capacity(bedroom.getCapacity())
				.dailyRate(bedroom.getDailyRate())
				.description(bedroom.getDescription())
				.available(bedroom.getAvailable())
				.hotelId(bedroom.getHotelId())
				.build();
	}

	// DELETE - Excluir
	public void deleteBedroom(Long id) {
		Bedroom bedroom = bedroomRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Bedroom not found with id :" + id));
		bedroomRepository.delete(bedroom);
	}

//	public List<BedroomResponse> listByHotelId(Long hotelId) {
//		return bedroomClient.listByHotelId(hotelId);
//	}


}
