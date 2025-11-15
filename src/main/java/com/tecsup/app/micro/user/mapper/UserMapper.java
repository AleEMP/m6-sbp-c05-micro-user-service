package com.tecsup.app.micro.user.mapper;

import com.tecsup.app.micro.user.dto.User;
import com.tecsup.app.micro.user.dto.UserRequest;
import com.tecsup.app.micro.user.dto.UserResponse;
import com.tecsup.app.micro.user.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User toDomain(UserEntity entity);
    UserEntity toEntity(User domain);
    List<User> toDomain(List<UserEntity> entities);

    @Mapping(target = "id", ignore = true) // New users don't have ID
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    User toDomain(UserRequest request);

    UserResponse toResponse(User createUser);
}
