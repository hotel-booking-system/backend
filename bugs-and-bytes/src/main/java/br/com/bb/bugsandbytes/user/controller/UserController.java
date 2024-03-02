package br.com.bb.bugsandbytes.user.controller;

import br.com.bb.bugsandbytes.user.domain.dto.UserRequest;
import br.com.bb.bugsandbytes.user.domain.dto.UserResponse;
import br.com.bb.bugsandbytes.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping("/register")
	public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
		UserResponse response = userService.save(userRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

}
