package com.orioninc.ProjectRestaurants.DTO.user;

import com.orioninc.ProjectRestaurants.enums.UserRole;

public record UserUpdateDTO(Long id,
                            String username,
                            String password,
                            UserRole userRole) {
}
