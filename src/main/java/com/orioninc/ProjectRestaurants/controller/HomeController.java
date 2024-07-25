package com.orioninc.ProjectRestaurants.controller;

import com.orioninc.ProjectRestaurants.auth.MyUserDetailsService;
import com.orioninc.ProjectRestaurants.auth.webtoken.JwtService;
import com.orioninc.ProjectRestaurants.auth.webtoken.LoginForm;
import com.orioninc.ProjectRestaurants.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class HomeController {

  private final AuthenticationManager authenticationManager;    // JJWT Helps us to authenticate by  username and pass
  private final JwtService jwtService;
  private final MyUserDetailsService myUserDetailsService;

  @GetMapping(value = "/welcome")
  public String welcome() {
    return "Welcome to Spring!";
  }

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
