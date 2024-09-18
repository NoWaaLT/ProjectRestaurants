package com.orioninc.ProjectRestaurants.config;

import com.orioninc.ProjectRestaurants.auth.CustomDaoAuthenticationProvider;
import com.orioninc.ProjectRestaurants.auth.webtoken.JwtAuthenticationFilter;
import com.orioninc.ProjectRestaurants.permission.CustomPermissionEvaluator;
import com.orioninc.ProjectRestaurants.auth.MyUserDetailsService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final CustomDaoAuthenticationProvider customDaoAuthenticationProvider;

  public SecurityConfig(
      JwtAuthenticationFilter jwtAuthenticationFilter,
      @Lazy CustomDaoAuthenticationProvider customDaoAuthenticationProvider) {
    this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    this.customDaoAuthenticationProvider = customDaoAuthenticationProvider;
  }

  @Bean
  public UserDetailsService userDetailsService() {
    return new MyUserDetailsService();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(
            auth ->
                auth.requestMatchers(
                        "/api/restaurants/**",
                        "/api/customer/**",
                        "/api/products/**",
                        "/api/products-expire",
                        "/api/dishes/**",
                        "/api/menus/**",
                        "/api/orders/**",
                        "/api/recipes/**")
                    .authenticated()
                    .requestMatchers("/register", "/authenticate", "/actuator")
                    .permitAll()
                    .anyRequest()
                    .anonymous())
        .httpBasic(Customizer.withDefaults()) // It's says it will be in the form of http/https
        .formLogin(Customizer.withDefaults()) // It's says how the login form should be handled
        .addFilterBefore(
            jwtAuthenticationFilter, // To enable AuthFilter before the specified filter
            UsernamePasswordAuthenticationFilter.class)
        .build();
  }

  @Bean
  public AuthenticationManager authenticationManager() { // Used for JJWT, to validate user and pass
    return new ProviderManager(customDaoAuthenticationProvider);
  }

  @Bean
  public PasswordEncoder passwordEncoder() { // One side code encryption
    return new BCryptPasswordEncoder(); // Built-in salt added
  }

  @Bean
  public MethodSecurityExpressionHandler expressionHandler(
      CustomPermissionEvaluator customPermissionEvaluator) {

    DefaultMethodSecurityExpressionHandler handler =
        new DefaultMethodSecurityExpressionHandler(); // Utilize the CustomPermissionEvaluator
    handler.setPermissionEvaluator(customPermissionEvaluator); // add the PermissionEvaluator

    return handler;
  }
}
