package com.orioninc.ProjectRestaurants.DTO.user;

import com.orioninc.ProjectRestaurants.DTO.role.RoleDTOMapper;
import com.orioninc.ProjectRestaurants.model.Role;
import com.orioninc.ProjectRestaurants.model.User;
import com.orioninc.ProjectRestaurants.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class UserUpdateDTOMapper implements Function<UserUpdateDTO, User> {

  private final RoleRepository roleRepository;
  private final RoleDTOMapper roleDTOMapper;

  @Override
  public User apply(UserUpdateDTO userUpdateDTO) {

    User user = new User();
    user.setId(userUpdateDTO.id());
    user.setUsername(userUpdateDTO.username());
    user.setPasswordHash(userUpdateDTO.password());

    List<Role> rolesList = userUpdateDTO.roles().stream().map(roleDTOMapper).toList();

    user.setRoles(rolesList);

    //        return new User(
    //                userUpdateDTO.id(),
    //                userUpdateDTO.username(),
    //                userUpdateDTO.password(),
    //                userUpdateDTO.role()
    //        );

    return user;
  }
}

// TODO db refactoring, done
