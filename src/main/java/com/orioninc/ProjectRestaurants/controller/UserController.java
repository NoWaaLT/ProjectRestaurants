package com.orioninc.ProjectRestaurants.controller;

import com.orioninc.ProjectRestaurants.DTO.user.*;

import com.orioninc.ProjectRestaurants.model.Role;
import com.orioninc.ProjectRestaurants.service.RoleService;
import com.orioninc.ProjectRestaurants.service.UserService;
import lombok.AllArgsConstructor;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/restaurant")
@AllArgsConstructor
public class UserController {

  private final UserService userService;
  private final UserResponseDTOMapper userResponseDTOMapper;
  private final RoleService roleService;

  @PreAuthorize("hasPermission(authentication, 'admin:create')")
  @PostMapping(value = "/users/save")
  public UserResponseDTO saveUser(@RequestBody UserRequestDTO userRequestDTO) {
    return userResponseDTOMapper.apply(userService.saveUser(userRequestDTO));
  }

  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  @PutMapping(value = "/users/update")
  public UserResponseDTO updateUser(@RequestBody UserUpdateDTO userUpdateDTO) {
    return userResponseDTOMapper.apply(userService.updateUser(userUpdateDTO));
  }

  @PostAuthorize("hasPermission('User', 'admin:get')")
  @GetMapping(value = "/welcome")
  public String welcome() {
    return "Welcome to Spring!";
  }

  @PreAuthorize("hasAuthority('admin:get')")
  @GetMapping(value = "/findRole/{roleName}")
  public Optional<Role> getByRoleName(@PathVariable String roleName) {
    return roleService.findByRoleName(roleName);
  }
}

 // TODO db refactoring
