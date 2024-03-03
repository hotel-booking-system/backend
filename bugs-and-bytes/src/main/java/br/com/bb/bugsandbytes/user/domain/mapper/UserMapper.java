package br.com.bb.bugsandbytes.user.domain.mapper;

import br.com.bb.bugsandbytes.user.domain.dto.RegisterUserRequest;
import br.com.bb.bugsandbytes.user.domain.dto.RegisterUserResponse;
import br.com.bb.bugsandbytes.user.domain.dto.UpdateUserRequest;
import br.com.bb.bugsandbytes.user.domain.dto.UserResponse;
import br.com.bb.bugsandbytes.user.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
		unmappedTargetPolicy = ReportingPolicy.IGNORE) // Assumindo integração com Spring
public interface UserMapper {

	UserMapper INSTANCIA = Mappers.getMapper(UserMapper.class);

	static UserMapper userMapper() {
		return INSTANCIA;
	}

	User toEntity(RegisterUserRequest registerUserRequest);

	RegisterUserResponse toResponse(User user);

	UserResponse userToUserResponse(User user);

	User updateUserRequestToUser(UpdateUserRequest updateUserRequest);

}
