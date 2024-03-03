package br.com.bb.bugsandbytes.user.service;

import br.com.bb.bugsandbytes.exception.erros.ApiBadRequestException;
import br.com.bb.bugsandbytes.exception.erros.UsernameAlreadyExistsException;
import br.com.bb.bugsandbytes.user.domain.dto.UserRequest;
import br.com.bb.bugsandbytes.user.domain.dto.UserResponse;
import br.com.bb.bugsandbytes.user.domain.entity.User;
import br.com.bb.bugsandbytes.user.domain.mapper.UserMapper;
import br.com.bb.bugsandbytes.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	UserMapper userMapper;

	public UserResponse createUser(UserRequest userRequest)
			throws UsernameAlreadyExistsException, ApiBadRequestException {
		UserValidator.validateCreateUserRequest(userRequest);

		if (userRepository.existsByUsername(userRequest.getUsername())) {
			throw new UsernameAlreadyExistsException();
		}

		User user = userMapper.toEntity(userRequest);
		user.setRegistrationDate(LocalDateTime.now());

		return userMapper.toResponse(userRepository.save(user));

	}


}
