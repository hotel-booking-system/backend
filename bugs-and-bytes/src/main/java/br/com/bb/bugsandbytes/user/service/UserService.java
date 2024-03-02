package br.com.bb.bugsandbytes.user.service;

import br.com.bb.bugsandbytes.exception.erros.ApiBadRequestException;
import br.com.bb.bugsandbytes.exception.erros.ServerErrorException;
import br.com.bb.bugsandbytes.exception.erros.UsernameNotFoundException;
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
//	private final UserMapper mapper;

	public UserResponse save(UserRequest userRequest) {

		try {
			if (userRepository.existsByUsername(userRequest.getUsername())) {
				throw new UsernameNotFoundException();
			}
			User user = toEntity(userRequest);
			user = userRepository.save(user);
			return toResponse(user);
		} catch (ApiBadRequestException ex) {
			log.error("Erro na requisição", ex);
			throw new ApiBadRequestException();
		} catch (Exception ex) {
			log.error("Erro ao cadastrar usuário", ex);
			throw new ServerErrorException();
		}

	}

	public static User toEntity(UserRequest userRequest) {
		return User.builder()
				.name(userRequest.getName())
				.username(userRequest.getUsername())
				.password(userRequest.getPassword())
				.phoneNumber(userRequest.getPhoneNumber())
				.build();
	}

	public static UserResponse toResponse(User user) {
		return UserResponse.builder()
				.id(user.getId())
				.name(user.getName())
				.username(user.getUsername())
				.password(user.getPassword())
				.phoneNumber(user.getPhoneNumber())
				.registrationDate(user.getRegistrationDate())
				.build();
	}

}
