package com.orioninc.ProjectRestaurants.auth.webtoken;

import com.orioninc.ProjectRestaurants.auth.MyUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.AllArgsConstructor;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

// JJWT 5. Used to authenticate and authorize the token

@Configuration
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final HandlerExceptionResolver handlerExceptionResolver;
  private final JwtService jwtService;
  private final MyUserDetailsService myUserDetailsService;

  @Override
  protected void doFilterInternal (
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String authHeader = request.getHeader("Authorization"); // Get the authHeader if exists
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {  // Checks
      filterChain.doFilter(request, response);  // Does nothing
      return;
    }
    try {
    String jwt = authHeader.substring(7); // 7 due to "Bearer " contains 7 elements. Extracts only token
    String username = jwtService.extractUsername(jwt);

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {   // Checks is user authenticated
      UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);

      if (userDetails != null && jwtService.isTokenValid(jwt)) {            // Checks is token not expired
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                username, userDetails.getPassword(), userDetails.getAuthorities()
        );

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        ThreadContext.put("id",username);

      }
    }

    filterChain.doFilter(request, response);  // Proceeding to next chain element
      } catch (ExpiredJwtException e) {
      handlerExceptionResolver.resolveException(request, response, null, e);
    }
  }
}
