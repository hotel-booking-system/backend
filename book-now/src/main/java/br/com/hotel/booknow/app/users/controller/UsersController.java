package br.com.hotel.booknow.app.users.controller;

import br.com.hotel.booknow.app.users.dto.UserRequest;
import br.com.hotel.booknow.app.users.dto.UserResponse;
import br.com.hotel.booknow.app.users.service.UsersService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "users", description = "Operações relacionadas a usuários")
@RestController
@RequestMapping(value = "/users")
@AllArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRequest request) {
        UserResponse userResponse = usersService.createUser(request);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserResponse>> listUsers() {
        List<UserResponse> userResponses = usersService.getlAllUsers();
        return new ResponseEntity<>(userResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable(name = "id") Long id) {
        UserResponse userResponse = usersService.getUserById(id);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUserById(@PathVariable(name = "id") Long id,
                                                       @RequestBody UserRequest request) {
        UserResponse userResponse = usersService.updateUser(id, request);
        return new ResponseEntity<>(userResponse, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable(name = "id") Long id) {
        usersService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
