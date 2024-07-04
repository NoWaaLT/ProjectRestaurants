package com.orioninc.ProjectRestaurants.config.security;

import com.orioninc.ProjectRestaurants.enums.Permission;
import com.orioninc.ProjectRestaurants.enums.UserRole;
import com.orioninc.ProjectRestaurants.service.MyUserDetailsService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  @Bean
  public UserDetailsService userDetailsService() {    // provides save way to LoadUserByUsername()

    return new MyUserDetailsService();
  }


  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(
            auth ->
                auth.requestMatchers("*")
                    .permitAll()
                    .anyRequest()
                    .authenticated())           // Any other request only can be reach for authenticated users
        .httpBasic(Customizer.withDefaults())   // It's says it will be in form of http/https
        .formLogin(Customizer.withDefaults())   // It's says how login form should be handled
        .build();
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {                    // Process the request to retrieve user credentials
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();     // through UserServiceDetails via Dao
    provider.setUserDetailsService(userDetailsService());
    provider.setPasswordEncoder(passwordEncoder());

    return provider;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {    // One side code encryption
    return new BCryptPasswordEncoder();         // Built-in salt added
  }
}
