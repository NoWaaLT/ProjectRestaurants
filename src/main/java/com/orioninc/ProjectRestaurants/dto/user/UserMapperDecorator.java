//package com.orioninc.ProjectRestaurants.dto.user;
//
//import com.orioninc.ProjectRestaurants.dto.role.RoleMapper;
//import com.orioninc.ProjectRestaurants.exceptions.UserNotFoundException;
//import com.orioninc.ProjectRestaurants.model.Role;
//import com.orioninc.ProjectRestaurants.model.User;
//import com.orioninc.ProjectRestaurants.repository.UserRepository;
//import com.orioninc.ProjectRestaurants.service.RoleService;
//
//import java.util.List;
//
//import static com.orioninc.ProjectRestaurants.enums.AppText.USER_BY_USERNAME_NOT_FOUND;
//
//public abstract class UserMapperDecorator implements UserMapper {
//
//  UserMapper userMapper;
//  private UserRepository userRepository;
//  private RoleService roleService;
//
//  protected void setUserMapper(UserMapper userMapper) {
//    this.userMapper = userMapper;
//  }
//
//  protected void setUserRepository(UserRepository userRepository) {
//    this.userRepository = userRepository;
//  }
//
//  protected void setRoleService(RoleService roleService) {
//    this.roleService = roleService;
//  }
//
////  @Override
////  public UserDto convertToUserDto(UserCreateDto userCreateDto) {
////    User user =
////        userRepository
////            .findByUsername(userCreateDto.getUsername())
////            .orElseThrow(
////                () ->
////                    new UserNotFoundException(
////                        USER_BY_USERNAME_NOT_FOUND, userCreateDto.getUsername()));
////
////    return new UserDto(user.getUsername(), user.getRoles());
////  }
//
////  @Override
////  public User convertToUser(UserCreateDto userCreateDto) {
////    User user = new User();
////    user.setUsername(userCreateDto.getUsername());
////    user.setPasswordHash(userCreateDto.getPassword());
////
////    List<Role> roles = roleService.getAllRoles();
////
////    // By default, a new user will have a user role
////    user.setRoles(roles.stream().filter(role -> role.getId() == 3).toList());
////
////    return user;
////  }
//
////  @Override
////  public User convertToUser(UserUpdateDto userUpdateDTO) {
////    User user = new User();
////    user.setId(userUpdateDTO.id());
////    user.setUsername(userUpdateDTO.username());
////    user.setPasswordHash(userUpdateDTO.password());
////
////    List<Role> rolesList =
////        userUpdateDTO.roles().stream().map(RoleMapper.INSTANCE::roleDtoToRole).toList();
////
////    user.setRoles(rolesList);
////
////    return user;
////  }
//}
