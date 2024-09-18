package com.orioninc.ProjectRestaurants.dto.user;

import java.util.List;

public record UserDto(String username,
                      List<String> roles) {
}