package br.com.hotel.booknow.app.users.domain.mapper;

import br.com.hotel.booknow.app.users.domain.dto.UserRequest;
import br.com.hotel.booknow.app.users.domain.dto.UserResponse;
import br.com.hotel.booknow.app.users.domain.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    static UserMapper userMapper() {
        return INSTANCE;
    }

    Users toEntity(UserRequest userRequest);

    UserResponse toUserResponse(Users users);

    List<UserResponse> toUserListResponse(List<Users> usersList);

}
