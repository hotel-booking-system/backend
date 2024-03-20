package br.com.hotel.booknow.bedrooms.service;

import br.com.hotel.booknow.bedrooms.domain.Bedroom;
import br.com.hotel.booknow.bedrooms.repository.BedroomRepository;
import br.com.hotel.booknow.exceptions.errors.ConflictException;
import br.com.hotel.booknow.exceptions.errors.NotFoundException;
import br.com.hotel.booknow.hotels.repository.HotelRepository;
import br.com.hotel.booknow.hotels.service.HotelService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class BedroomService {

	private final BedroomRepository bedroomRepository;
	private final HotelRepository hotelRepository;

	private final HotelService hotelService;

	@Transactional
	public Bedroom saveBedroom(Bedroom bedroom) {

		if (bedroomRepository.existsByNumber(bedroom.getNumber())) {
			throw new IllegalArgumentException("Bedroom number already exists");
		}

		return bedroomRepository.save(bedroom);

	}

	private void validateBedroom(Bedroom bedroom) throws ConflictException {
		if (bedroom.getNumber() == null || bedroom.getNumber() < 1 || bedroom.getNumber() > 5) {
			log.error("Invalid bedroom number. Number must be between 1 and 5.");
			throw new ConflictException();
		}
	}

	public Bedroom update(Bedroom bedroom) {
		verifyBedroomExists(bedroom.getId());
		log.info("Atualizando quarto com ID: {}", bedroom.getId());
		return bedroomRepository.save(bedroom);
	}

	public Bedroom findById(Long id) {
		return bedroomRepository.findById(id)
				.orElseThrow(() -> {
					log.error("Quarto não encontrado com o ID: {}", id);
					return new NotFoundException();
				});
	}

	public List<Bedroom> findAll() {
		log.info("Buscando todos os quartos");
		return bedroomRepository.findAll();
	}

	public void delete(Long id) {
		verifyBedroomExists(id);
		log.info("Excluindo quarto com ID: {}", id);
		bedroomRepository.deleteById(id);
	}

//	public List<Bedroom> findByType(RoomType roomType) {
//		log.info("Buscando quartos pelo tipo: {}", roomType);
//		return bedroomRepository.findByTipo(roomType);
//	}

//	public List<Bedroom> findByAvailableDate(LocalDateTime date) {
//		log.info("Buscando quartos disponíveis a partir da data: {}", date);
//		return bedroomRepository.findByDataDisponivelAfter(date);
//	}
//
//	public List<Bedroom> findByDailyRate(BigDecimal value) {
//		log.info("Buscando quartos com diária menor que: {}", value);
//		return bedroomRepository.findByValorDiariaLessThan(value);
//	}

	private void verifyBedroomExists(Long id) {
		if (!bedroomRepository.existsById(id)) {
			log.error("Quarto não encontrado com o ID: {}", id);
			throw new NotFoundException();
		}
	}

	public void quartoDisponivel() {

	}

	public void quartoIndisponivel() {

	}

	public void verificarNumberAndHotelId(Integer number, Long hotelId) {
		bedroomRepository.findByNumberAndHotelId(number, hotelId);

	}

	public Bedroom verificarId(Long bedroomId) {
		return bedroomRepository.findById(bedroomId)
				.orElseThrow(NotFoundException::new);
	}

}
