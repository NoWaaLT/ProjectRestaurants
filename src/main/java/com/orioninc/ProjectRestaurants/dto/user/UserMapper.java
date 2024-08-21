package com.orioninc.ProjectRestaurants.dto.user;

import com.orioninc.ProjectRestaurants.model.User;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
@DecoratedWith(UserMapperDecorator.class)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "roles", ignore = true)
    UserResponseDto userRequestDtoToUserResponseDto(UserRequestDto userRequestDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderList", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User userRequestDtoToUser(UserRequestDto userRequestDto);

    @Mapping(target = "orderList", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User userUpdateDtoToUser(UserUpdateDto userUpdateDTO);

    UserResponseDto userToUserResponseDto(User user);
}
