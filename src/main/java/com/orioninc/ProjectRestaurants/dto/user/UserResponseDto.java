package com.orioninc.ProjectRestaurants.dto.user;

import com.orioninc.ProjectRestaurants.model.Role;

import java.util.List;

public record UserResponseDto(String username,
                              List<Role> roles) {
}
