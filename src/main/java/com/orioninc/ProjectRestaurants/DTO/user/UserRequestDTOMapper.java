package com.orioninc.ProjectRestaurants.DTO.user;

import com.orioninc.ProjectRestaurants.model.User;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@AllArgsConstructor
@Service
public class UserRequestDTOMapper implements Function<UserRequestDTO, User> {

    @Override
    public User apply(UserRequestDTO userRequestDTO) {

        return new User(
                null,
                userRequestDTO.getUsername(),
                userRequestDTO.getPassword(),
                userRequestDTO.getRole()
        );
    }
}
