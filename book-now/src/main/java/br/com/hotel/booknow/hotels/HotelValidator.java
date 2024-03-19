package br.com.hotel.booknow.hotels;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class HotelValidator {

	private final HotelRepository hotelRepository;

	public void verifyUniqueData(Hotel hotel) throws IllegalArgumentException {
		if (hotelRepository.existsByHotelNameIgnoreCase(hotel.getHotelName())) {
			log.warn("Hotel name already registered: {}", hotel.getHotelName());
			throw new IllegalArgumentException("Hotel name already registered!");
		}

		if (hotelRepository.existsByEmailIgnoreCase(hotel.getEmail())) {
			log.warn("Email already registered: {}", hotel.getEmail());
			throw new IllegalArgumentException("Email already registered!");
		}

		if (hotelRepository.existsByCnpjNumber(hotel.getCnpjNumber())) {
			log.warn("CNPJ number already registered: {}", hotel.getCnpjNumber());
			throw new IllegalArgumentException("CNPJ number already registered!");
		}
	}

	public Hotel getExistingHotel(Long hotelId) throws IllegalArgumentException {
		return hotelRepository.findById(hotelId)
				.orElseThrow(() -> {
					log.error("Hotel not found for ID: {}", hotelId);
					return new IllegalArgumentException("Hotel not found!");
				});
	}

	public void verifyUpdatedFields(Hotel hotel, Hotel existingHotel) {

		if (hotel.getLocation() != null) {
			existingHotel.setLocation(hotel.getLocation());
			log.info("Location updated for hotel: " + existingHotel.getLocation());
		}

		if (hotel.getHotelName() != null) {
			existingHotel.setHotelName(hotel.getHotelName());
			log.info("Hotel name updated to: " + existingHotel.getHotelName());
		}

		if (hotel.getPhoneNumber() != null) {
			existingHotel.setPhoneNumber(hotel.getPhoneNumber());
			log.info("Phone number updated for hotel: " + existingHotel.getPhoneNumber());
		}

		if (hotel.getEmail() != null) {
			existingHotel.setEmail(hotel.getEmail());
			log.info("Email updated for hotel: " + existingHotel.getEmail());
		}

		if (hotel.getHotelType() != null) {
			existingHotel.setHotelType(hotel.getHotelType());
			log.info("Hotel type updated for hotel: " + existingHotel.getHotelType());
		}

		if (hotel.getDescription() != null) {
			existingHotel.setDescription(hotel.getDescription());
			log.info("Description updated for hotel: " + existingHotel.getDescription());
		}

		if (hotel.getRoomCount() != null) {
			existingHotel.setRoomCount(hotel.getRoomCount());
			log.info("Room count updated for hotel: " + hotel.getRoomCount());
		}

	}

	public List<Hotel> findHotelType(HotelType hotelType) {
		log.debug("Searching hotels by type: {}", hotelType);
		return hotelRepository.findByHotelType(hotelType);
	}

	public List<Hotel> findByName(String nome) {
		log.debug("Finding hotels by name: {}", nome);
		return hotelRepository.findByHotelNameContainingIgnoreCase(nome);
	}

	public List<Hotel> findByCnpj(String cnpj) {
		log.debug("Finding hotels by CNPJ: {}", cnpj);
		return hotelRepository.findByCnpjNumberContainingIgnoreCase(cnpj);
	}

	public List<Hotel> findByEmail(String email) {
		log.debug("Finding hotels by email: {}", email);
		return hotelRepository.findByEmailContainingIgnoreCase(email);
	}

}