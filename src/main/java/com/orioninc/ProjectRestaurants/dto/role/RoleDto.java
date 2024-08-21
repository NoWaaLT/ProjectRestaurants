package com.orioninc.ProjectRestaurants.dto.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RoleDto(
        @NotNull(message = "Role cannot be null.")
        @NotBlank(message = "Role cannot be empty.")
        String roleName) {

}
