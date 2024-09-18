package com.orioninc.ProjectRestaurants.service.implementation;

import com.orioninc.ProjectRestaurants.dto.user.*;
import com.orioninc.ProjectRestaurants.exceptions.IncorrectPasswordException;
import com.orioninc.ProjectRestaurants.exceptions.UserNotFoundException;
import com.orioninc.ProjectRestaurants.model.Role;
import com.orioninc.ProjectRestaurants.model.User;
import com.orioninc.ProjectRestaurants.repository.UserRepository;
import com.orioninc.ProjectRestaurants.service.RoleService;
import com.orioninc.ProjectRestaurants.service.UserService;

import lombok.AllArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.orioninc.ProjectRestaurants.enums.AppText.*;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
  public final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserMapper userMapper;
  private final RoleService roleService;

  @Transactional
  @Override
  public UserDto saveUser(UserCreateDto userCreateDto) {
    User user = initializeUser(userCreateDto);
    userRepository.save(user);
    return userMapper.toUserDto(user);
  }

  @Override
  public User initializeUser(UserCreateDto userCreateDto) {
    User user = new User();
    String salt = BCrypt.gensalt(12);
    user.setUsername(userCreateDto.getUsername());
    user.setPasswordHash(generatePassword(userCreateDto.getPassword(), salt));
    user.setSalt(salt);
    List<Role> roles = roleService.getAllRoles();
    user.setRoles(roles.stream().filter(role -> role.getId() == 3).toList());

    return user;
  }

  @Transactional(isolation = Isolation.READ_COMMITTED)
  @Override
  public UserDto updateUser(UserUpdateDto userUpdateDto) {
    User updatedUser = userMapper.toUser(userUpdateDto);

    User existingUser =
        userRepository
            .findById(userUpdateDto.id())
            .orElseThrow(() -> new UserNotFoundException(USER_BY_ID_NOT_FOUND, userUpdateDto.id()));

    if (existingUser != null) {
      if (isPasswordCorrect(existingUser, userUpdateDto.oldPassword())) {
        updatedUser = changePassword(updatedUser, userUpdateDto.password());
        userRepository.save(updatedUser);
      } else {
        throw new IncorrectPasswordException(INCORRECT_OLD_PASSWORD);
      }
    } else {
      throw new UserNotFoundException(USER_BY_USERNAME_NOT_FOUND, userUpdateDto.username());
    }

    //    List<Role> rolesList =
    //            userUpdateDto.roles().stream().map(RoleMapper.INSTANCE::roleDtoToRole).toList();
    //    updatedUser.setRoles(rolesList);

    return userMapper.toUserDto(updatedUser);
  }

  @Override
  public boolean isPasswordCorrect(User user, String oldPassword) {
    return passwordEncoder.matches(oldPassword + user.getSalt(), user.getPasswordHash());
  }

  public User changePassword(User user, String newPassword) {
    String salt = BCrypt.gensalt(12);
    String newPasswordHash = generatePassword(newPassword, salt);
    user.setSalt(salt);
    user.setPasswordHash(newPasswordHash);

    return user;
  }

  @Override
  public String generatePassword(String password, String salt) {
    return passwordEncoder.encode(password + salt);
  }

  @Transactional(readOnly = true)
  @Override
  public List<UserDto> getAllUsers() {
    List<User> usersList = userRepository.findAll();
    if (usersList.isEmpty()) {
      throw new UserNotFoundException(USERS_EMPTY);
    }
    return usersList.stream().map(userMapper::toUserDto).toList();
  }
}
