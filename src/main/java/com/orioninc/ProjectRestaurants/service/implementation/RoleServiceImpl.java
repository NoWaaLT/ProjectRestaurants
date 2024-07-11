package com.orioninc.ProjectRestaurants.service.implementation;

import com.orioninc.ProjectRestaurants.model.Role;
import com.orioninc.ProjectRestaurants.repository.RoleRepository;

import com.orioninc.ProjectRestaurants.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;

  @Override
  public List<Role> getAllRoles() {
    return roleRepository.findAll().stream().toList();
  }


  public Optional<Role> findByRoleName(String roleName) {
    return roleRepository.findByRoleName(roleName);
  }
}
