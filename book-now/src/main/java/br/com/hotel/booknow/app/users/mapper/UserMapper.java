package br.com.hotel.booknow.app.users.mapper;

import br.com.hotel.booknow.app.users.dto.UserRequest;
import br.com.hotel.booknow.app.users.dto.UserResponse;
import br.com.hotel.booknow.app.users.entity.Users;
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
