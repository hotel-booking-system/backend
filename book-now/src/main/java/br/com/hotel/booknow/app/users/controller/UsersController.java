package br.com.hotel.booknow.users;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UsersController {

	@GetMapping
	public String getUsers() {
		return "User Controller";
	}

}
