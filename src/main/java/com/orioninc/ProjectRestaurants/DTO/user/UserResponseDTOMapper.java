package com.orioninc.ProjectRestaurants.DTO.user;

import com.orioninc.ProjectRestaurants.model.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserResponseDTOMapper implements Function<User, UserResponseDTO> {

    @Override
    public UserResponseDTO apply(User user) {
        return new UserResponseDTO(
                user.getUsername(),
                user.getRole()
        );
    }
}
