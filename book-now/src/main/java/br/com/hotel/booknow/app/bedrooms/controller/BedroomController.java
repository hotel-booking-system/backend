package br.com.hotel.booknow.bedrooms.controller;

import br.com.hotel.booknow.bedrooms.domain.Bedroom;
import br.com.hotel.booknow.bedrooms.service.BedroomService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/bedrooms")
@AllArgsConstructor
public class BedroomController {

	private final BedroomService bedroomService;

	@PostMapping("/register")
	public ResponseEntity<Bedroom> registerBedroom(@RequestBody Bedroom bedroom) {
		Bedroom savedBedroom = bedroomService.saveBedroom(bedroom);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedBedroom);
	}

	@PutMapping
	public ResponseEntity<Bedroom> atualizar(@RequestBody Bedroom bedroom) {
		Bedroom response = bedroomService.update(bedroom);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Bedroom> buscarPorId(@PathVariable Long id) {
		Bedroom response = bedroomService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/list")
	public ResponseEntity<List<Bedroom>> buscarTodos() {
		List<Bedroom> response = bedroomService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> excluir(@PathVariable Long id) {
		bedroomService.delete(id);
		return ResponseEntity.ok("Bedroom with ID " + id + " deleted successfully.");
	}

//	@GetMapping("/tipo")
//	public ResponseEntity<List<Bedroom>> buscarPorTipo(@RequestParam RoomType roomType) {
//		return ResponseEntity.ok(bedroomService.findByType(roomType));
//	}

//	@GetMapping("/data-disponivel")
//	public ResponseEntity<List<Bedroom>> buscarPorDataDisponivel(@RequestParam LocalDateTime data) {
//		return ResponseEntity.ok(bedroomService.findByAvailableDate(data));
//	}

//	@GetMapping("/valor")
//	public ResponseEntity<List<Bedroom>> buscarPorValorDiaria(@RequestParam BigDecimal valor) {
//		return ResponseEntity.ok(bedroomService.findByDailyRate(valor));
//	}

}
