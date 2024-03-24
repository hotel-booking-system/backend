package br.com.hotel.booknow.app.users.service;

import br.com.hotel.booknow.app.users.dto.UserRequest;
import br.com.hotel.booknow.app.users.dto.UserResponse;
import br.com.hotel.booknow.app.users.entity.Users;
import br.com.hotel.booknow.app.users.mapper.UserMapper;
import br.com.hotel.booknow.app.users.repository.UsersRepository;
import br.com.hotel.booknow.core.exceptions.errors.user.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    /**
     * <b>Create</b>
     * <p>
     * Cadastrar novo usuário
     * <p>
     * Senha não retorna no response e é criptografada no banco de dados.
     *
     * @param request
     *         Dados do formulário de entrada
     *
     * @return {@code UserResponse}
     */
    @Transactional
    public UserResponse createUser(UserRequest request) {
        Users users = UserMapper.userMapper().toEntity(request);
        users.setActive(Boolean.TRUE);
        usersRepository.save(users);
        return UserMapper.userMapper().toUserResponse(users);
    }

    /**
     * <b>Read</b>
     * <p>
     * Listar todos os Usuários
     *
     * @return {@code UserResponse}
     */
    @Transactional(readOnly = true)
    public List<UserResponse> getlAllUsers() {
        List<Users> users = usersRepository.findAll();
        return UserMapper.userMapper().toUserListResponse(users);
    }

    /**
     * <b>Read</b>
     * <p>
     * Buscar usuário por ID
     *
     * @param id
     *         Identificador do Usuário
     *
     * @return {@code UserResponse}
     */
    @Transactional(readOnly = true)
    public UserResponse getUserById(Long id) {
        Users user = usersRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        return UserMapper.userMapper().toUserResponse(user);
    }

    /**
     * <b>Update</b>
     * <p>
     * Atualiza os dados do usuário
     *
     * @param request
     *         Formulário com alguns dados para serem atualizados.
     *
     * @return {@code UserResponse}
     */
    @Transactional
    public UserResponse updateUser(Long id, UserRequest request) {
        Users user = usersRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        user.setName(request.getFullName());
        user.setUsernames(request.getUsernames());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(request.getPassword());
        user.setUpdatedAt(LocalDateTime.now());
        user = usersRepository.save(user);
        return UserMapper.userMapper().toUserResponse(user);
    }

    /**
     * <b>Delete</b>
     * <p>
     * Excluir usuário
     *
     * @param id
     *         Identificador do Usuário
     */
    @Transactional
    public void deleteUser(Long id) {
        Users user = usersRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        usersRepository.delete(user);
    }


}
