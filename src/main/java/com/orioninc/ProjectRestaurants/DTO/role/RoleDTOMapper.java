package com.orioninc.ProjectRestaurants.DTO.role;

import com.orioninc.ProjectRestaurants.model.Role;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class RoleDTOMapper implements Function<RoleDTO, Role> {

  @Override
  public Role apply(RoleDTO roleDTO) {
    Role role = new Role();
    role.setRoleName(roleDTO.roleName());

    return role;
  }
}
