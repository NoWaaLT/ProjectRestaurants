package com.orioninc.ProjectRestaurants.dto.user;

import com.orioninc.ProjectRestaurants.dto.role.RoleDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record UserUpdateDto(
    @NotNull(message = "User id must be specified.")
        @Positive(message = "User id must be a positive number.")
        Long id,
    @NotNull(message = "Username cannot be null.") @NotBlank(message = "Username cannot be empty.")
        String username,
    @Pattern(
            regexp = "^(?=.*\\d)(?=.*[A-Z]).{6,8}$",
            message =
                "Password must contain from 6 to 8 characters, at least 1 number and alphabet in capitals. No special chars allowed.")
        String password,
    List<RoleDto> roles) {}
