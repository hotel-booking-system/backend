package br.com.hotel.booknow.hotels.controller;

import br.com.hotel.booknow.hotels.domain.entity.Hotel;
import br.com.hotel.booknow.hotels.domain.entity.HotelType;
import br.com.hotel.booknow.hotels.service.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author juliane.maran
 * @since 19-03-2024
 */
@RestController
@RequestMapping(value = "/hotels")
@AllArgsConstructor
public class HotelController {

	private final HotelService hotelService;

	/**
	 * <b>Cria um novo hotel.</b>
	 *
	 * @param hotel
	 * 		Objeto Hotel contendo os dados do hotel a ser criado.
	 *
	 * @return recém-criado persistido no banco de dados.
	 */
	@PostMapping("/create")
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
		Hotel response = hotelService.createHotel(hotel);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	/**
	 * <b>Atualiza os dados de um hotel existente.</b>
	 *
	 * @param id
	 * 		Identificador único do hotel a ser atualizado.
	 * @param hotel
	 * 		Objeto Hotel contendo os novos dados do hotel.
	 *
	 * @return Hotel atualizado persistido no banco de dados.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Hotel> updateHotel(@PathVariable(name = "id") Long id,
											 @RequestBody Hotel hotel) {
		Hotel updatedHotel = hotelService.updateHotel(id, hotel);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(updatedHotel);
	}

	/**
	 * <b>Busca hotéis de acordo com critérios de pesquisa.</b>
	 *
	 * @param name
	 * 		Nome do hotel (opcional). Pesquisa por hotéis que contenham o nome informado.
	 * @param cnpj
	 * 		CNPJ do hotel (opcional). Pesquisa por hotéis com o CNPJ exato informado.
	 * @param email
	 * 		Email do hotel (opcional). Pesquisa por hotéis com o e-mail exato informado.
	 * @param hotelType
	 * 		Tipo de hotel (opcional). Pesquisa por hotéis do tipo informado.
	 *
	 * @return Lista de hotéis que atendem aos critérios de pesquisa.
	 */
	@GetMapping("/search")
	public ResponseEntity<List<Hotel>> searchHotel(
			@RequestParam(required = false, name = "name") String name,
			@RequestParam(required = false, name = "cnpj") String cnpj,
			@RequestParam(required = false, name = "email") String email,
			@RequestParam(required = false, name = "type") HotelType type) {

		List<Hotel> response = hotelService.searchHotel(name, cnpj, email, type);

		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	/**
	 * <b>Busca um hotel por ID.</b>
	 *
	 * @param id
	 * 		Identificador único do hotel.
	 *
	 * @return Hotel encontrado, ou lança uma exceção caso não seja encontrado.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Hotel> findHotelById(@PathVariable(name = "id") Long id) {
		Hotel hotel = hotelService.findHotelById(id);
		return ResponseEntity.ok(hotel);
	}

	/**
	 * <b>Lista todos os hotéis cadastrados.</b>
	 *
	 * @return Lista de hotéis encontrados.
	 */
	@GetMapping("/list")
	public ResponseEntity<List<Hotel>> listHotels() {
		List<Hotel> hoteis = hotelService.listHotels();
		return ResponseEntity.ok(hoteis);
	}

	/**
	 * <b>Exclui um hotel por ID.</b>
	 *
	 * @param id
	 * 		Identificador único do hotel.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteHotel(@PathVariable(name = "id") Long id) {
		hotelService.deleteHotel(id);
		return ResponseEntity.ok("Hotel with ID " + id + " deleted successfully.");
	}

}
