package com.orioninc.ProjectRestaurants.controller;

import com.orioninc.ProjectRestaurants.DTO.user.*;

import com.orioninc.ProjectRestaurants.service.RoleService;
import com.orioninc.ProjectRestaurants.service.UserService;

import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/customer")
public class UserController {

  private final UserService userService;
  private final UserResponseDTOMapper userResponseDTOMapper;
  private final RoleService roleService;

  @PreAuthorize("hasPermission(#id, 'User', 'read')")
  @GetMapping
  public List<UserResponseDTO> getAllUsers() {
    return userService.getAllUsers();
  }

  @PreAuthorize("hasPermission(#id, 'User', 'create')")
  @PostMapping
  public UserResponseDTO saveUser(@RequestBody UserRequestDTO userRequestDTO) {
    return userResponseDTOMapper.apply(userService.saveUser(userRequestDTO));
  }

  // TODO Make update by providing username

  @PreAuthorize("hasPermission(#id, 'User', 'update')")
  @PutMapping(value = "/")
  public UserResponseDTO updateUser(@RequestBody UserUpdateDTO userUpdateDTO) {
    return userResponseDTOMapper.apply(userService.updateUser(userUpdateDTO));
  }

//  @PreAuthorize("hasPermission(#id, 'User', 'read')")
//  @GetMapping(value = "/findRole/{roleName}")
//  public Optional<Role> getByRoleName(@PathVariable String roleName) {
//    return roleService.findByRoleName(roleName);
//  }


}
