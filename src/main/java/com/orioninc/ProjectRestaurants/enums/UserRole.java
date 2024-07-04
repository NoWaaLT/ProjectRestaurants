package com.orioninc.ProjectRestaurants.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.orioninc.ProjectRestaurants.enums.Permission.*;

@RequiredArgsConstructor
public enum UserRole {

  ROLE_ADMIN(
      Set.of(
          ADMIN_CREATE,
          ADMIN_READ,
          ADMIN_UPDATE,
          ADMIN_DELETE,
          EMPLOYEE_CREATE,
          EMPLOYEE_READ,
          EMPLOYEE_UPDATE,
          EMPLOYEE_DELETE)),

  ROLE_EMPLOYEE(Set.of(EMPLOYEE_CREATE, EMPLOYEE_READ, EMPLOYEE_UPDATE, EMPLOYEE_DELETE)),

  ROLE_USER(Set.of(USER_CREATE, USER_READ));

  @Getter private final Set<Permission> permissionSet;

  public List<SimpleGrantedAuthority> getAuthorities() {
    var authoritiesList =
        getPermissionSet().stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission1()))
            .collect(Collectors.toList());

    authoritiesList.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

    return authoritiesList;
  }
}
