package br.com.hotel.booknow.app.users.repository;

import br.com.hotel.booknow.app.users.domain.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
