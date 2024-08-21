package com.orioninc.ProjectRestaurants.service;

import com.orioninc.ProjectRestaurants.dto.user.UserRequestDto;
import com.orioninc.ProjectRestaurants.dto.user.UserResponseDto;
import com.orioninc.ProjectRestaurants.dto.user.UserUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserResponseDto saveUser(UserRequestDto userRequestDTO);
    UserResponseDto updateUser(UserUpdateDto userUpdateDTO);
    List<UserResponseDto> getAllUsers();
}
