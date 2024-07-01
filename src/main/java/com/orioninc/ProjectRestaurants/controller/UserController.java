package com.orioninc.ProjectRestaurants.controller;

import com.orioninc.ProjectRestaurants.DTO.user.*;

import com.orioninc.ProjectRestaurants.service.UserService;
import lombok.AllArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/restaurant")
@AllArgsConstructor
public class UserController {
  private final UserService userService;
  private final UserResponseDTOMapper userResponseDTOMapper;

  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  @PostMapping(value = "/users/save")
  public UserResponseDTO saveUser(@RequestBody UserRequestDTO userRequestDTO) {
    return userResponseDTOMapper.apply(userService.saveUser(userRequestDTO));
  }

  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  @PutMapping(value = "/users/update")
  public UserResponseDTO updateUser(@RequestBody UserUpdateDTO userUpdateDTO) {
    return userResponseDTOMapper.apply(userService.updateUser(userUpdateDTO));
  }

}
