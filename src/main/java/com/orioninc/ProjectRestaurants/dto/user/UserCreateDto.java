package com.orioninc.ProjectRestaurants.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserCreateDto {
  @NotNull(message = "Username cannot be null.")
  @NotBlank(message = "Username cannot be empty.")
  String username;

  @Pattern(
      regexp = "^(?=.*\\d)(?=.*[A-Z]).{6,8}$",
      message =
          "Password must contain from 6 to 8 characters, at least 1 number and alphabet in capitals. No special chars allowed.")
  String password;
}
