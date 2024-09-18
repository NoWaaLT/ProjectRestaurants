//package com.orioninc.ProjectRestaurants.auth;
//
//import com.orioninc.ProjectRestaurants.exceptions.UserNotFoundException;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import static com.orioninc.ProjectRestaurants.enums.AppText.INCORRECT_PASSWORD;
//import static com.orioninc.ProjectRestaurants.enums.AppText.USER_BY_USERNAME_NOT_FOUND;
//
//@Component
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//
//  private final MyUserDetailsService myUserDetailsService;
//  private final PasswordEncoder passwordEncoder;
//
//  public CustomAuthenticationProvider(MyUserDetailsService myUserDetailsService, PasswordEncoder passwordEncoder) {
//    this.myUserDetailsService = myUserDetailsService;
//    this.passwordEncoder = passwordEncoder;
//  }
//
//  @Override
//  public Authentication authenticate(final Authentication authentication)
//      throws AuthenticationException {
//    final String username = authentication.getName();
//    final String password = authentication.getCredentials().toString();
//
//    MyUserDetails userDetails = (MyUserDetails) myUserDetailsService.loadUserByUsername(username);
//    if (userDetails == null) {
//      throw new UserNotFoundException(USER_BY_USERNAME_NOT_FOUND, username);
//    }
//
//    final String salt = userDetails.getSalt();
//
//    if (!passwordEncoder.matches(password + salt, userDetails.getPassword())) {
//      throw new BadCredentialsException(INCORRECT_PASSWORD.getDescription());
//    }
//
//    return new UsernamePasswordAuthenticationToken(
//        username, password + salt, userDetails.getAuthorities());
//  }
//
//  @Override
//  public boolean supports(Class<?> authentication) {
//    return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
//  }
//}
