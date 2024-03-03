package br.com.bb.bugsandbytes.user.controller;

import br.com.bb.bugsandbytes.user.domain.dto.RegisterUserRequest;
import br.com.bb.bugsandbytes.user.domain.dto.RegisterUserResponse;
import br.com.bb.bugsandbytes.user.domain.dto.UpdateUserRequest;
import br.com.bb.bugsandbytes.user.domain.dto.UserResponse;
import br.com.bb.bugsandbytes.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping("/register")
	public ResponseEntity<RegisterUserResponse> createUser(
			@RequestBody RegisterUserRequest registerUserRequest) {
		RegisterUserResponse registerUserResponse = userService.createUser(registerUserRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(registerUserResponse);
	}

	@GetMapping("/search/{id}")
	public ResponseEntity<UserResponse> findById(@PathVariable(name = "id") Long id) {
		var response = userService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/search")
	public ResponseEntity<UserResponse> findByUsername(
			@RequestParam(value = "username") String username) {
		var response = userService.findByUsername(username);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/list")
	public ResponseEntity<List<UserResponse>> findAll() {
		var response = userService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<UserResponse> update(@PathVariable(name = "id") Long id,
											   @RequestBody UpdateUserRequest request) {

		var response = userService.update(id, request);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable(name = "id") Long id) {
		userService.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
