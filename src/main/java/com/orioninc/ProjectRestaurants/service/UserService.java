package com.orioninc.ProjectRestaurants.service;

import com.orioninc.ProjectRestaurants.dto.user.UserCreateDto;
import com.orioninc.ProjectRestaurants.dto.user.UserDto;
import com.orioninc.ProjectRestaurants.dto.user.UserUpdateDto;
import com.orioninc.ProjectRestaurants.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public interface UserService {
    UserDto saveUser(UserCreateDto userCreateDTO);
    UserDto updateUser(UserUpdateDto userUpdateDTO);
    List<UserDto> getAllUsers();
    User initializeUser(UserCreateDto userCreateDto);
    String generatePassword(String password, String salt);
    boolean isPasswordCorrect(User user, String oldPassword);
}
