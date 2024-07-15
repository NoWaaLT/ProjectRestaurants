package com.orioninc.ProjectRestaurants.config.security;

import com.orioninc.ProjectRestaurants.auth.webtoken.JwtAuthenticationFilter;
import com.orioninc.ProjectRestaurants.permission.CustomPermissionEvaluator;
import com.orioninc.ProjectRestaurants.auth.MyUserDetailsService;

import lombok.AllArgsConstructor;
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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {

  private final CustomPermissionEvaluator customPermissionEvaluator;
  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  @Bean
  public UserDetailsService userDetailsService() { // provides save way to LoadUserByUsername()
    return new MyUserDetailsService();
  }

  //    @Bean
  //    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
  //      return http.csrf(AbstractHttpConfigurer::disable)
  //          .authorizeHttpRequests(
  //              auth ->
  //                  auth.requestMatchers("*")
  //                      .permitAll()
  //                      .anyRequest()
  //                      .authenticated())           // Any other request only can be reach for
  // authenticated users
  //          .httpBasic(Customizer.withDefaults())   // It's says it will be in form of http/https
  //          .formLogin(Customizer.withDefaults())   // It's says how login form should be handled
  //          .build();
  //    }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(
            auth ->
                auth.requestMatchers("/api/restaurant/**")
                    .authenticated()
                    .requestMatchers("/login", "/register")
                    .permitAll()
                    .anyRequest()
                    .anonymous())
        .httpBasic(Customizer.withDefaults()) // It's says it will be in form of http/https
        .formLogin(Customizer.withDefaults()) // It's says how login form should be handled
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
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
  public AuthenticationManager authenticationManager() { // Used for JWT, to validate user and pass
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
