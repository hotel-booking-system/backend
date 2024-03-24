package br.com.hotel.booknow.app.users.service;

import br.com.hotel.booknow.app.users.dto.UserRequest;
import br.com.hotel.booknow.app.users.dto.UserResponse;
import br.com.hotel.booknow.app.users.entity.Users;
import br.com.hotel.booknow.app.users.mapper.UserMapper;
import br.com.hotel.booknow.app.users.repository.UsersRepository;
import br.com.hotel.booknow.core.exceptions.errors.user.UnableToUpdateUserException;
import br.com.hotel.booknow.core.exceptions.errors.user.UserNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@AllArgsConstructor
public class UserHelper {

    private final UsersRepository usersRepository;

    public Users findUserById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    public void updateUserData(UserRequest request, Users user) {
        user.setName(request.getFullName());
        user.setUsernames(request.getUsernames());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(request.getPassword());
        user.setUpdatedAt(LocalDateTime.now());
    }

    public UserResponse saveUser(Users user) {
        try {
            user = usersRepository.save(user);
            return UserMapper.userMapper().toUserResponse(user);
        } catch (UnableToUpdateUserException ex) {
            log.error("Erro ao tentar atualizar Usuário.");
            throw ex;
        }
    }

}
