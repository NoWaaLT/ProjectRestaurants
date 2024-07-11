package com.orioninc.ProjectRestaurants.DTO.user;

import com.orioninc.ProjectRestaurants.model.Role;
import com.orioninc.ProjectRestaurants.model.User;

import com.orioninc.ProjectRestaurants.service.implementation.RoleServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@AllArgsConstructor
@Service
public class UserRequestDTOMapper implements Function<UserRequestDTO, User> {

  private final RoleServiceImpl roleService;

  @Override
  public User apply(UserRequestDTO userRequestDTO) {

    User user = new User();
    user.setUsername(userRequestDTO.getUsername());
    user.setPasswordHash(userRequestDTO.getPassword());

    List<Role> roles = roleService.getAllRoles();

    // By default, a new user will have a user role
    user.setRoles(roles.stream()
            .filter(role ->
                    role.getId() == 3)
            .toList()
    );

    return user;
  }
}

// TODO db refactoring. DONE
