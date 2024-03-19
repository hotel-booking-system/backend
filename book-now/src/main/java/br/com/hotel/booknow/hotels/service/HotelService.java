package br.com.hotel.booknow.hotels.service;

import br.com.hotel.booknow.exceptions.errors.BadRequestException;
import br.com.hotel.booknow.hotels.validator.HotelValidator;
import br.com.hotel.booknow.hotels.domain.entity.Hotel;
import br.com.hotel.booknow.hotels.domain.entity.HotelType;
import br.com.hotel.booknow.hotels.repository.HotelRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class HotelService {

	private final HotelRepository hotelRepository;
	private final HotelValidator hotelValidator;

	@Transactional
	public Hotel createHotel(Hotel hotel) {
		try {
			hotelValidator.verifyUniqueData(hotel);
			hotel.setCreatedAt(LocalDateTime.now());
			log.info("Creating hotel: {}", hotel);
			return hotelRepository.save(hotel);
		} catch (BadRequestException e) {
			log.error("Error creating hotel: {}", e.getMessage());
			throw new BadRequestException(e.getMessage());
		} catch (Exception e) {
			log.error("Unexpected error creating hotel: {}", e.getMessage());
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public Hotel findHotelById(Long id) {
		return hotelValidator.getExistingHotel(id);
	}

	@Transactional(readOnly = true)
	public List<Hotel> listHotels() {
		return hotelRepository.findAll();
	}

	@Transactional
	public void deleteHotel(Long id) {
		Hotel hotel = findHotelById(id);
		hotelRepository.delete(hotel);
	}

	@Transactional
	public Hotel updateHotel(Long hotelId, Hotel hotel) {
		try {
			Hotel existingHotel = hotelValidator.getExistingHotel(hotelId);
			hotelValidator.verifyUpdatedFields(hotel, existingHotel);
			existingHotel.setUpdatedAt(LocalDateTime.now());
			log.info("Updating hotel with ID {}: {}", hotelId, hotel);
			return hotelRepository.save(existingHotel);
		} catch (BadRequestException e) {
			log.error("Error updating hotel with ID {}: {}", hotelId, e.getMessage());
			throw new BadRequestException(e.getMessage());
		} catch (Exception e) {
			log.error("Unexpected error updating hotel with ID {}: {}", hotelId, e.getMessage());
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public List<Hotel> searchHotel(String nome, String cnpj, String email, HotelType hotelType) {

		List<Hotel> results = new ArrayList<>();

		if (StringUtils.hasText(nome)) {
			results.addAll(hotelValidator.findByName(nome));
		}

		if (StringUtils.hasText(cnpj)) {
			results.addAll(hotelValidator.findByCnpj(cnpj));
		}

		if (StringUtils.hasText(email)) {
			results.addAll(hotelValidator.findByEmail(email));
		}

		if (hotelType != null) {
			results.addAll(hotelValidator.findHotelType(hotelType));
		}

		return results;
	}

}