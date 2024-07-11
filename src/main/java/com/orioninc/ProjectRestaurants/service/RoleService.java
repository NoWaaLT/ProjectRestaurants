package com.orioninc.ProjectRestaurants.service;

import com.orioninc.ProjectRestaurants.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<Role> getAllRoles();
    Optional<Role> findByRoleName(String roleName);

}
