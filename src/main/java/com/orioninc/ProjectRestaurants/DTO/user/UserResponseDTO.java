package com.orioninc.ProjectRestaurants.DTO.user;

import com.orioninc.ProjectRestaurants.model.Role;

import java.util.List;

public record UserResponseDTO(String username,
                             List<Role> roles) {
}
