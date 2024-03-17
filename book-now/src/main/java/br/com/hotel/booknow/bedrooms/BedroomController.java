package br.com.hotel.booknow.bedrooms;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/bedrooms")
public class BedroomController {

	@GetMapping
	public String getBedrooms() {
		return "Bedroom Controller";
	}

}
