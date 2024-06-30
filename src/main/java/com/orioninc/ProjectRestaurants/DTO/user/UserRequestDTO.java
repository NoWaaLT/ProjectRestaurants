package com.orioninc.ProjectRestaurants.DTO.user;

import com.orioninc.ProjectRestaurants.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class UserRequestDTO {
  String username;
  String password;
  UserRole role;
}
