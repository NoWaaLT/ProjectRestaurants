package com.orioninc.ProjectRestaurants.config.security;

import com.orioninc.ProjectRestaurants.auth.AuthenticationFacadeImpl;
import com.orioninc.ProjectRestaurants.auth.webtoken.JwtAuthenticationFilter;
import com.orioninc.ProjectRestaurants.permission.CustomPermissionEvaluator;
import com.orioninc.ProjectRestaurants.auth.MyUserDetailsService;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {

  private final CustomPermissionEvaluator customPermissionEvaluator;
  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  @Bean
  public UserDetailsService userDetailsService() { // Provides save way to LoadUserByUsername()
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
        .httpBasic(Customizer.withDefaults()) // It's says it will be in form of http/https
        .formLogin(Customizer.withDefaults()) // It's says how login form should be handled
        .addFilterBefore(
            jwtAuthenticationFilter, // To enable AuthFilter before the specified filter
            UsernamePasswordAuthenticationFilter.class)
        .build();
  }

  @Bean
  public AuthenticationProvider
      authenticationProvider() { // Process the request to retrieve user credentials
    DaoAuthenticationProvider provider =
        new DaoAuthenticationProvider(); // through UserServiceDetails via Dao
    provider.setUserDetailsService(userDetailsService());
    provider.setPasswordEncoder(passwordEncoder());

    return provider;
  }

  @Bean
  public AuthenticationManager authenticationManager() { // Used for JJWT, to validate user and pass
    return new ProviderManager(authenticationProvider());
  }

  @Bean
  public PasswordEncoder passwordEncoder() { // One side code encryption
    return new BCryptPasswordEncoder(); // Built-in salt added
  }

  //  @Bean
  //  public CustomPermissionEvaluator customPermissionEvaluator() {
  //    return new CustomPermissionEvaluator();
  //  }

  @Bean
  public MethodSecurityExpressionHandler expressionHandler(
      CustomPermissionEvaluator customPermissionEvaluator) {

    DefaultMethodSecurityExpressionHandler handler =
        new DefaultMethodSecurityExpressionHandler(); // Utilize the CustomPermissionEvaluator
    handler.setPermissionEvaluator(customPermissionEvaluator); // add the PermissionEvaluator

    return handler;
  }
}
