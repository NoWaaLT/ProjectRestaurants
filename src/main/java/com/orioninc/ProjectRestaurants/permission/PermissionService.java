//package com.orioninc.ProjectRestaurants.permission;
//
//import com.orioninc.ProjectRestaurants.model.User;
//import org.springframework.stereotype.Service;
//
//@Service
//public class PermissionService {
//
//  public boolean userHasPermission(User user, String permissionName) {
//    return user.getRoles().stream()
//        .flatMap(role -> role.getPermissions().stream())
//        .anyMatch(permission -> permission.getActionName().equals(permissionName));
//  }
//}
