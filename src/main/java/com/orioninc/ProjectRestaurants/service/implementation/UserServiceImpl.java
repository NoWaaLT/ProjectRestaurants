package com.orioninc.ProjectRestaurants.service.implementation;

import com.orioninc.ProjectRestaurants.DTO.user.*;
import com.orioninc.ProjectRestaurants.exceptions.UserNotFoundException;
import com.orioninc.ProjectRestaurants.model.User;
import com.orioninc.ProjectRestaurants.repository.UserRepository;
import com.orioninc.ProjectRestaurants.service.UserService;

import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

  public final UserRepository userRepository;
  public final UserRequestDTOMapper userRequestDTOMapper;
  public final UserResponseDTOMapper userResponseDTOMapper;
  public final UserUpdateDTOMapper userUpdateDTOMapper;
  private PasswordEncoder passwordEncoder;

  @Override
  public User saveUser(UserRequestDTO userRequestDTO) {
    userRequestDTO.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
    return userRepository.save(userRequestDTOMapper.apply(userRequestDTO));
  }

  @Override
  public User updateUser(UserUpdateDTO userUpdateDTO) {
    User userToUpdate = userUpdateDTOMapper.apply(userUpdateDTO);

    User existingUser =
        userRepository
            .findById(userUpdateDTO.id())
            .orElseThrow(() -> new UserNotFoundException("User is not found."));

    existingUser.setId(userToUpdate.getId());
    existingUser.setUsername(userToUpdate.getUsername());
    existingUser.setPasswordHash(userToUpdate.getPasswordHash());
    existingUser.setRoles(userToUpdate.getRoles());

    return existingUser;
  }

  @Override
  public List<UserResponseDTO> getAllUsers() {
    return userRepository.findAll().stream()
            .map(userResponseDTOMapper)
            .toList();
  }
}