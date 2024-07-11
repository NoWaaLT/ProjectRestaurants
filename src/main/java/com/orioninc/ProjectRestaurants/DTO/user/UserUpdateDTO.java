package com.orioninc.ProjectRestaurants.DTO.user;

import com.orioninc.ProjectRestaurants.DTO.role.RoleDTO;

import java.util.List;

public record UserUpdateDTO(Long id,
                            String username,
                            String password,
                            List<RoleDTO> roles) {
}
