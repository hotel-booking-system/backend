package br.com.hotel.booknow.app.bedrooms.controller;

import br.com.hotel.booknow.app.bedrooms.domain.dto.BedroomRequest;
import br.com.hotel.booknow.app.bedrooms.domain.dto.BedroomResponse;
import br.com.hotel.booknow.app.bedrooms.service.BedroomService;
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

	@PostMapping
	public ResponseEntity<BedroomResponse> createBedroom(@RequestBody BedroomRequest bedroomRequest) {
		BedroomResponse bedroomResponse = bedroomService.createBedroom(bedroomRequest);
		return new ResponseEntity<>(bedroomResponse, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<BedroomResponse>> getAllBedrooms() {
		List<BedroomResponse> bedroomResponses = bedroomService.getAllBedrooms();
		return new ResponseEntity<>(bedroomResponses, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<BedroomResponse> getBedroomById(@PathVariable(name = "id") Long id) {
		BedroomResponse bedroomResponse = bedroomService.getBedroomById(id);
		return new ResponseEntity<>(bedroomResponse, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<BedroomResponse> updateBedroom(@PathVariable(name = "id") Long id,
														 @RequestBody BedroomRequest bedroomRequest) {
		BedroomResponse bedroomResponse = bedroomService.updateBedroom(id, bedroomRequest);
		return new ResponseEntity<>(bedroomResponse, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBedroom(@PathVariable(name = "id") Long id) {
		bedroomService.deleteBedroom(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
