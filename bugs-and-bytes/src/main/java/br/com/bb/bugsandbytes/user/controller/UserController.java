package br.com.bb.bugsandbytes.user.controller;

import br.com.bb.bugsandbytes.exception.erros.ServerErrorException;
import br.com.bb.bugsandbytes.user.domain.dto.UserRequest;
import br.com.bb.bugsandbytes.user.domain.dto.UserResponse;
import br.com.bb.bugsandbytes.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping("/register")
	public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
		UserResponse userResponse = userService.createUser(userRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
	}

}
