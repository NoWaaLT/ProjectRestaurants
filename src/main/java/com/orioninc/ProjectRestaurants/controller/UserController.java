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
@RequestMapping(value = "/api/customer")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PreAuthorize("hasPermission(#id, 'User', 'read')")
  @GetMapping
  public ResponseEntity<List<UserDto>> getAllUsers() {
    return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
  }

  @PreAuthorize("hasPermission(#id, 'User', 'create')")
  @PostMapping
  public ResponseEntity<UserDto> saveUser(@RequestBody UserCreateDto userCreateDTO) {
    return new ResponseEntity<>(userService.saveUser(userCreateDTO),HttpStatus.CREATED);
  }

  @PreAuthorize("hasPermission(#id, 'User', 'update')")
  @PutMapping(value = "/")
  public ResponseEntity<UserDto> updateUser(@RequestBody UserUpdateDto userUpdateDTO) {
    return new ResponseEntity<>(userService.updateUser(userUpdateDTO), HttpStatus.OK);
  }

  // TODO change password and roles

//  @PreAuthorize("hasPermission(#id, 'User', 'read')")
//  @GetMapping(value = "/findRole/{roleName}")
//  public Optional<Role> getByRoleName(@PathVariable String roleName) {
//    return roleService.findByRoleName(roleName);
//  }

}
