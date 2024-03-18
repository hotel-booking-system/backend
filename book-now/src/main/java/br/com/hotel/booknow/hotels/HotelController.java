package br.com.hotel.booknow.hotels;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hotels")
public class HotelController {

	@GetMapping
	public String getHoteis() {
		return "Hotel Controller";
	}

}
