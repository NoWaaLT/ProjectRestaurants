package com.orioninc.ProjectRestaurants.dto.role;

import com.orioninc.ProjectRestaurants.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {

  RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "permissions", ignore = true)
  @Mapping(target = "users", ignore = true)
  Role roleDtoToRole(RoleDto roleDTO);
}
