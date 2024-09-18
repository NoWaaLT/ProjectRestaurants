package com.orioninc.ProjectRestaurants.dto.user;

import com.orioninc.ProjectRestaurants.dto.role.RoleMapper;
import com.orioninc.ProjectRestaurants.model.Role;
import com.orioninc.ProjectRestaurants.model.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",  uses = RoleMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
//@DecoratedWith(UserMapperDecorator.class)
public interface UserMapper {

    @Mapping(target = "roles", ignore = true)
    UserDto toUserDto(UserCreateDto userCreateDto);

    @Mapping(source = "user.username" , target = "username")        //
    @Mapping(source = "user.roles", target = "roles", qualifiedByName = "mapRoles")
    UserDto toUserDto(UserCreateDto userCreateDto, User user);

    @Mapping(source = "roles", target = "roles", qualifiedByName = "mapRoles")
    UserDto toUserDto(User user);

    @Mapping(target = "orderList", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "salt", ignore = true)
    @Mapping(source = "roles", target = "roles")
    User toUser(UserUpdateDto userUpdateDTO);

//    @Mapping(target = "orderList", ignore = true)
//    @Mapping(target = "passwordHash", ignore = true)
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "username", ignore = true)
//    @Mapping(target = "roles", ignore = true)
//    User convertToUser(UserUpdateDto userUpdateDTO, User user);

    @Named("mapRoles")
    default List<String> mapRoles(List<Role> roles) {
        return roles.stream()
                .map(Role::getRoleName)
                .toList();
    }
}
