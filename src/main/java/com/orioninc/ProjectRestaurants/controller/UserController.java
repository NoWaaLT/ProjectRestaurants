package com.orioninc.ProjectRestaurants.controller;

import com.orioninc.ProjectRestaurants.DTO.user.*;

import com.orioninc.ProjectRestaurants.auth.MyUserDetailsService;
import com.orioninc.ProjectRestaurants.auth.webtoken.JwtService;
import com.orioninc.ProjectRestaurants.auth.webtoken.LoginForm;
import com.orioninc.ProjectRestaurants.exceptions.UserNotFoundException;
import com.orioninc.ProjectRestaurants.model.Role;
import com.orioninc.ProjectRestaurants.service.RoleService;
import com.orioninc.ProjectRestaurants.service.UserService;
import lombok.AllArgsConstructor;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
//@RequestMapping(value = "/api/restaurant")
@AllArgsConstructor
public class UserController {

  private final UserService userService;
  private final UserResponseDTOMapper userResponseDTOMapper;
  private final RoleService roleService;
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;
  private final MyUserDetailsService myUserDetailsService;

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
  @GetMapping(value = "/api/restaurant/welcome")
  public String welcome() {
    return "Welcome to Spring!";
  }

  @PreAuthorize("hasAuthority('admin:get')")
  @GetMapping(value = "/api/restaurant/findRole/{roleName}")
  public Optional<Role> getByRoleName(@PathVariable String roleName) {
    return roleService.findByRoleName(roleName);
  }

  //  @PostAuthorize("hasPermission('User', 'admin:get')")
  @PostMapping(value = "/authenticate")
  public String authenticateAndGetToken(@RequestBody LoginForm loginForm) {
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginForm.username(), loginForm.password()));
    if (authentication.isAuthenticated()) {
      return jwtService.generateToken(
          myUserDetailsService.loadUserByUsername(loginForm.username()));
    } else {
      throw new UserNotFoundException("User by this username not found.");
    }
  }
}
