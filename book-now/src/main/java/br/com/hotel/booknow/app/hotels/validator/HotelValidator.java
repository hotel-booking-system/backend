package br.com.hotel.booknow.app.hotels.validator;

import br.com.hotel.booknow.app.hotels.domain.entity.Hotel;
import br.com.hotel.booknow.app.hotels.domain.entity.HotelType;
import br.com.hotel.booknow.app.hotels.repository.HotelRepository;
import br.com.hotel.booknow.core.exceptions.errors.ConflictException;
import br.com.hotel.booknow.core.exceptions.errors.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Classe de validação para entidades Hotel.
 * <p>
 * Responsável por verificar dados únicos e buscar hotéis existentes no repositório.
 *
 * @author juliane.maran
 * @since 19-03-2024
 */
@Slf4j
@Component
@AllArgsConstructor
public class HotelValidator {

	/**
	 * Repositório de acesso a dados de hotéis.
	 */
	private final HotelRepository hotelRepository;

	/**
	 * Verifica se o hotel possui dados únicos (nome, email, CNPJ) já cadastrados.
	 *
	 * @param hotel
	 * 		Hotel a ser validado.
	 *
	 * @throws ConflictException
	 * 		Se o hotel possui nome, email ou CNPJ já cadastrado.
	 */
	public void verifyUniqueData(Hotel hotel) throws ConflictException {
		if (hotelRepository.existsByHotelNameIgnoreCase(hotel.getHotelName())) {
			log.warn("Hotel name already registered: {}", hotel.getHotelName());
			throw new ConflictException();
		}

		if (hotelRepository.existsByEmailIgnoreCase(hotel.getEmail())) {
			log.warn("Email already registered: {}", hotel.getEmail());
			throw new ConflictException();
		}

		if (hotelRepository.existsByCnpjNumber(hotel.getCnpjNumber())) {
			log.warn("CNPJ number already registered: {}", hotel.getCnpjNumber());
			throw new ConflictException();
		}
	}

	/**
	 * Recupera um hotel existente a partir do identificador.
	 *
	 * @param hotelId
	 * 		Identificador do hotel.
	 *
	 * @return Hotel encontrado.
	 *
	 * @throws NotFoundException
	 * 		Se o hotel não for encontrado.
	 */
	public Hotel getExistingHotel(Long hotelId) throws NotFoundException {
		return hotelRepository.findById(hotelId)
				.orElseThrow(() -> {
					log.error("Hotel not found for ID: {}", hotelId);
					return new NotFoundException();
				});
	}

	/**
	 * Verifica e atualiza os campos do hotel existente com os valores do hotel recebido. <br> Somente atualiza os
	 * campos que não são nulos no hotel recebido.
	 *
	 * @param hotel
	 * 		Hotel com os dados a serem atualizados (pode conter campos nulos).
	 * @param existingHotel
	 * 		Hotel existente a ser atualizado.
	 */
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

	/**
	 * Busca hotéis por tipo.
	 *
	 * @param hotelType
	 * 		Tipo de hotel a ser buscado.
	 *
	 * @return Lista de hotéis encontrados.
	 */
	public List<Hotel> findHotelType(HotelType hotelType) {
		log.debug("Searching hotels by type: {}", hotelType);
		return hotelRepository.findByHotelType(hotelType);
	}

	/**
	 * Busca hotéis por nome (pesquisa contendo o nome)
	 *
	 * @param nome
	 * 		Nome do hotel (busca case insensitive).
	 *
	 * @return Lista de hotéis encontrados.
	 */
	public List<Hotel> findByName(String nome) {
		log.debug("Finding hotels by name: {}", nome);
		return hotelRepository.findByHotelNameContainingIgnoreCase(nome);
	}

	/**
	 * Busca hotéis por CNPJ (pesquisa contendo o CNPJ).
	 *
	 * @param cnpj
	 * 		CNPJ do hotel (busca case insensitive).
	 *
	 * @return Lista de hotéis encontrados.
	 */
	public List<Hotel> findByCnpj(String cnpj) {
		log.debug("Finding hotels by CNPJ: {}", cnpj);
		return hotelRepository.findByCnpjNumberContainingIgnoreCase(cnpj);
	}

	/**
	 * Busca hotéis por email (pesquisa contendo o email).
	 *
	 * @param email
	 * 		Email do hotel (busca case insensitive).
	 *
	 * @return Lista de hotéis encontrados.
	 */
	public List<Hotel> findByEmail(String email) {
		log.debug("Finding hotels by email: {}", email);
		return hotelRepository.findByEmailContainingIgnoreCase(email);
	}

}