package com.orioninc.ProjectRestaurants.controller;

import com.orioninc.ProjectRestaurants.dto.user.*;

import com.orioninc.ProjectRestaurants.service.UserService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/customer")
public class UserController {

  private final UserService userService;

  @PreAuthorize("hasPermission(#id, 'User', 'read')")
  @GetMapping
  public ResponseEntity<List<UserResponseDto>> getAllUsers() {
    return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
  }

  @PreAuthorize("hasPermission(#id, 'User', 'create')")
  @PostMapping
  public ResponseEntity<UserResponseDto> saveUser(@RequestBody UserRequestDto userRequestDTO) {
    return new ResponseEntity<>(userService.saveUser(userRequestDTO),HttpStatus.CREATED);
  }

  @PreAuthorize("hasPermission(#id, 'User', 'update')")
  @PutMapping(value = "/")
  public ResponseEntity<UserResponseDto> updateUser(@RequestBody UserUpdateDto userUpdateDTO) {
    return new ResponseEntity<>(userService.updateUser(userUpdateDTO), HttpStatus.OK);
  }

//  @PreAuthorize("hasPermission(#id, 'User', 'read')")
//  @GetMapping(value = "/findRole/{roleName}")
//  public Optional<Role> getByRoleName(@PathVariable String roleName) {
//    return roleService.findByRoleName(roleName);
//  }


}
