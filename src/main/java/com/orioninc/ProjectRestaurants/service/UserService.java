package com.orioninc.ProjectRestaurants.service;

import com.orioninc.ProjectRestaurants.DTO.user.UserRequestDTO;
import com.orioninc.ProjectRestaurants.DTO.user.UserUpdateDTO;
import com.orioninc.ProjectRestaurants.model.User;
import org.springframework.stereotype.Service;

public interface UserService {
    User saveUser(UserRequestDTO userRequestDTO);
    User updateUser(UserUpdateDTO userUpdateDTO);
}
