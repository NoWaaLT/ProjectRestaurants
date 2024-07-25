package com.orioninc.ProjectRestaurants.auth;

import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {
    Authentication getAuthentication();
}