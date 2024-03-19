package br.com.hotel.booknow.hotels;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/hotels")
@AllArgsConstructor
public class HotelController {

	private final HotelService hotelService;

	@GetMapping
	public String getHoteis() {
		return "Hotel Controller";
	}

	@PostMapping("/create")
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
		Hotel response = hotelService.createHotel(hotel);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Hotel> updateHotel(@PathVariable(name = "id") Long id,
											 @RequestBody Hotel hotel) {
		Hotel response = hotelService.updateHotel(id, hotel);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
	}

	@GetMapping("/search")
	public ResponseEntity<List<Hotel>> searchHotel(
			@RequestParam(required = false, name = "name") String name,
			@RequestParam(required = false, name = "cnpj") String cnpj,
			@RequestParam(required = false, name = "email") String email,
			@RequestParam(required = false, name = "hotelType") HotelType hotelType) {

		List<Hotel> response = hotelService.searchHotel(name, cnpj, email, hotelType);

		if (response.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Hotel> findHotelById(@PathVariable(name = "id") Long id) {
		Hotel hotel = hotelService.findHotelById(id);
		return ResponseEntity.ok(hotel);
	}

	@GetMapping
	public ResponseEntity<List<Hotel>> listHotels() {
		List<Hotel> hoteis = hotelService.listHotels();
		return ResponseEntity.ok(hoteis);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteHotel(@PathVariable(name = "id") Long id) {
		hotelService.deleteHotel(id);
		return ResponseEntity.noContent().build();
	}


}
