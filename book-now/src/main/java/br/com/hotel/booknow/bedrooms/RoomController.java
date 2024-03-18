package br.com.hotel.booknow.bedrooms;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rooms")
public class RoomController {

	@GetMapping
	public String getBedrooms() {
		return "Bedroom Controller";
	}

}
