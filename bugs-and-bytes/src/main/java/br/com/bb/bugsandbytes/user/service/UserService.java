package br.com.bb.bugsandbytes.user.service;

import br.com.bb.bugsandbytes.exception.erros.ApiBadRequestException;
import br.com.bb.bugsandbytes.exception.erros.IdNotFoundException;
import br.com.bb.bugsandbytes.exception.erros.UsernameAlreadyExistsException;
import br.com.bb.bugsandbytes.exception.erros.UsernameNotFoundException;
import br.com.bb.bugsandbytes.user.domain.dto.RegisterUserRequest;
import br.com.bb.bugsandbytes.user.domain.dto.RegisterUserResponse;
import br.com.bb.bugsandbytes.user.domain.dto.UpdateUserRequest;
import br.com.bb.bugsandbytes.user.domain.dto.UserResponse;
import br.com.bb.bugsandbytes.user.domain.entity.User;
import br.com.bb.bugsandbytes.user.domain.mapper.UserMapper;
import br.com.bb.bugsandbytes.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	UserMapper userMapper;

	public RegisterUserResponse createUser(RegisterUserRequest registerUserRequest)
			throws UsernameAlreadyExistsException, ApiBadRequestException {
		UserValidator.validateCreateUserRequest(registerUserRequest);

		if (userRepository.existsByUsername(registerUserRequest.getUsername())) {
			throw new UsernameAlreadyExistsException();
		}

		User user = userMapper.toEntity(registerUserRequest);
		user.setRegistrationDate(LocalDateTime.now());

		return userMapper.toResponse(userRepository.save(user));

	}

	public UserResponse findById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IdNotFoundException("Usuário não encontrado com o ID: " + id));
		return userMapper.userToUserResponse(user);
	}


	public UserResponse findByUsername(String username) {
		try {
			User user = userRepository.findByUsername(username);
			return userMapper.userToUserResponse(user);
		} catch (UsernameNotFoundException ex) {
			log.error("Usuário não encontrado com o username: {}", username);
			throw new UsernameNotFoundException();
		}
	}

	public List<UserResponse> findAll() {
		List<User> users = userRepository.findAll();
		return users.stream()
				.map(userMapper::userToUserResponse)
				.collect(Collectors.toList());
	}

	public void deleteById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IdNotFoundException("Usuário não encontrado com o ID: " + id));
		userRepository.delete(user);
	}

	public UserResponse update(Long id, UpdateUserRequest request) {

		User user = userRepository.findById(id)
				.orElseThrow(() -> new IdNotFoundException("Usuário não encontrado com o ID: " + id));

		if (request.getName() != null) {
			user.setName(request.getName());
		}

		if (request.getPhoneNumber() != null) {
			user.setPhoneNumber(request.getPhoneNumber());
		}

		if (request.getPassword() != null) {
			user.setPassword(request.getPassword());
		}

		userRepository.save(user);
		return userMapper.userToUserResponse(user);

	}

}
