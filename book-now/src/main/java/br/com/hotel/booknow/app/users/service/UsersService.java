package br.com.hotel.booknow.app.users.service;

import br.com.hotel.booknow.app.users.dto.UserRequest;
import br.com.hotel.booknow.app.users.dto.UserResponse;
import br.com.hotel.booknow.app.users.entity.Users;
import br.com.hotel.booknow.app.users.mapper.UserMapper;
import br.com.hotel.booknow.app.users.repository.UsersRepository;
import br.com.hotel.booknow.core.exceptions.errors.user.UnableToDeleteUserException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class UsersService {

    private final UserHelper userHelper;
    private final UsersRepository usersRepository;

    @Transactional
    public UserResponse createUser(UserRequest request) {
        Users users = UserMapper.userMapper().toEntity(request);
        users.setActive(Boolean.TRUE);
        return userHelper.saveUser(users);
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getlAllUsers() {
        List<Users> users = usersRepository.findAll();
        return UserMapper.userMapper().toUserListResponse(users);
    }

    @Transactional(readOnly = true)
    public UserResponse getUserById(Long id) {
        Users user = userHelper.findUserById(id);
        return UserMapper.userMapper().toUserResponse(user);
    }

    @Transactional
    public UserResponse updateUser(Long id, UserRequest request) {
        Users user = userHelper.findUserById(id);
        userHelper.updateUserData(request, user);
        return userHelper.saveUser(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        Users user = userHelper.findUserById(id);
        try {
            usersRepository.delete(user);
        } catch (UnableToDeleteUserException ex) {
            log.error("Erro ao tentar excluir Usuário.");
            throw ex;
        }
    }

}
