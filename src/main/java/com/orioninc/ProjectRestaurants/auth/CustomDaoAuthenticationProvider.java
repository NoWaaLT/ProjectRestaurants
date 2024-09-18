package com.orioninc.ProjectRestaurants.auth;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomDaoAuthenticationProvider extends DaoAuthenticationProvider {

  private final PasswordEncoder passwordEncoder;

  public CustomDaoAuthenticationProvider(
      PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
    this.passwordEncoder = passwordEncoder;
    this.setUserDetailsService(userDetailsService);
  }

  @Override
  public void additionalAuthenticationChecks(
      UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
      throws AuthenticationException {

    String presentedPassword = authentication.getCredentials().toString();
    String salt =
        ((MyUserDetails) userDetails).getSalt();

    if (!passwordEncoder.matches(presentedPassword + salt, userDetails.getPassword())) {
      throw new BadCredentialsException("Invalid credentials");
    }
  }
}
