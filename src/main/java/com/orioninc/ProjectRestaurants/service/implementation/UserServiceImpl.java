package com.orioninc.ProjectRestaurants.service.implementation;

import com.orioninc.ProjectRestaurants.dto.user.*;
import com.orioninc.ProjectRestaurants.exceptions.UserNotFoundException;
import com.orioninc.ProjectRestaurants.model.User;
import com.orioninc.ProjectRestaurants.repository.UserRepository;
import com.orioninc.ProjectRestaurants.service.UserService;

import lombok.AllArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.orioninc.ProjectRestaurants.enums.AppText.USERS_EMPTY;
import static com.orioninc.ProjectRestaurants.enums.AppText.USER_BY_ID_NOT_FOUND;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

  public final UserRepository userRepository;
  private PasswordEncoder passwordEncoder;

  @Transactional
  @Override
  public UserResponseDto saveUser(UserRequestDto userRequestDTO) {
    userRequestDTO.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
    userRepository.save(UserMapper.INSTANCE.userRequestDtoToUser(userRequestDTO));
    return UserMapper.INSTANCE.userRequestDtoToUserResponseDto(userRequestDTO);
  }

  @Transactional(isolation = Isolation.READ_COMMITTED)
  @Override
  public UserResponseDto updateUser(UserUpdateDto userUpdateDTO) {
    User userToUpdate = UserMapper.INSTANCE.userUpdateDtoToUser(userUpdateDTO);

    User existingUser =
        userRepository
            .findById(userUpdateDTO.id())
            .orElseThrow(() -> new UserNotFoundException(USER_BY_ID_NOT_FOUND, userUpdateDTO.id()));

    existingUser.setId(userToUpdate.getId());
    existingUser.setUsername(userToUpdate.getUsername());
    existingUser.setPasswordHash(userToUpdate.getPasswordHash());
    existingUser.setRoles(userToUpdate.getRoles());

    return UserMapper.INSTANCE.userToUserResponseDto(existingUser);
  }

  @Transactional(readOnly = true)
  @Override
  public List<UserResponseDto> getAllUsers() {
    List<User> usersList = userRepository.findAll();
    if (usersList.isEmpty()) {
      throw new UserNotFoundException(USERS_EMPTY);
    }
    return usersList.stream().map(UserMapper.INSTANCE::userToUserResponseDto).toList();
  }
}
