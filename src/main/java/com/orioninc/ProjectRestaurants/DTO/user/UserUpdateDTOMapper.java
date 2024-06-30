package com.orioninc.ProjectRestaurants.DTO.user;

import com.orioninc.ProjectRestaurants.model.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserUpdateDTOMapper implements Function<UserUpdateDTO, User> {

    @Override
    public User apply(UserUpdateDTO userUpdateDTO) {
        return new User(
                userUpdateDTO.id(),
                userUpdateDTO.username(),
                userUpdateDTO.password(),
                userUpdateDTO.userRole()
        );
    }
}
