package com.orioninc.ProjectRestaurants.DTO.user;

import com.orioninc.ProjectRestaurants.enums.UserRole;

public record UserResponseDTO(String username,
                              UserRole role) {

}
