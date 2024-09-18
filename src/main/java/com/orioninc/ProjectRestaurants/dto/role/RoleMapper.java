package com.orioninc.ProjectRestaurants.dto.role;

import com.orioninc.ProjectRestaurants.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "permissions", ignore = true)
  @Mapping(target = "users", ignore = true)
  Role roleDtoToRole(RoleDto roleDTO);

  @Mapping(source = "roleName", target = "roleName", qualifiedByName = "mapAddress")
  RoleResponseDto toRoleResponseDto(Role role);

  @Named("mapAddress")
  default List<String> mapAddress(String roleName) {
    List<String> rolesList = new ArrayList<>();
    rolesList.add(roleName);
    return rolesList;
  }

//  default String getName(Role role) {
//    return role.getRoleName();
//  }

}