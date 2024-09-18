package com.orioninc.ProjectRestaurants.controller;

import com.orioninc.ProjectRestaurants.auth.MyUserDetailsService;
import com.orioninc.ProjectRestaurants.auth.webtoken.JwtService;
import com.orioninc.ProjectRestaurants.auth.webtoken.LoginForm;
import com.orioninc.ProjectRestaurants.enums.AppText;
import com.orioninc.ProjectRestaurants.exceptions.UserNotFoundException;
import com.orioninc.ProjectRestaurants.kafka.producer.MessageProducer;
import lombok.AllArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class HomeController {

  private final AuthenticationManager authenticationManager;    // JJWT Helps us to authenticate by  username and pass
  private final JwtService jwtService;
  private final MyUserDetailsService myUserDetailsService;
  private MessageProducer messageProducer;

  @GetMapping(value = "/welcome")
  public String welcome() {
    return "Welcome to Spring!";
  }

  @PostMapping(value = "/authenticate")   // TODO Move it to the service layer
  public String authenticateAndGetToken(@RequestBody LoginForm loginForm) {
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginForm.username(), loginForm.password()));
    if (authentication.isAuthenticated()) {
      return jwtService.generateToken(
          myUserDetailsService.loadUserByUsername(loginForm.username()));
    } else {
      throw new UserNotFoundException(AppText.USER_BY_ID_NOT_FOUND, loginForm.username());
    }
  }

  @PostMapping(value = "/kafka-test")
  public String sendMessageToKafka(@RequestParam("message") String message) {
    messageProducer.sendMessage("my-topic", message);
    return "Message sent: " + message;
  }
}
