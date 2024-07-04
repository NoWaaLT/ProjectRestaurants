package com.orioninc.ProjectRestaurants.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {
  ADMIN_READ("admin:read"),

  ADMIN_CREATE("admin:create"),
  ADMIN_UPDATE("admin:update"),
  ADMIN_DELETE("admin:delete"),
  EMPLOYEE_READ("employee:read"),
  EMPLOYEE_CREATE("employee:create"),
  EMPLOYEE_UPDATE("employee:update"),
  EMPLOYEE_DELETE("employee:delete"),
  USER_READ("user:read"),
  USER_CREATE("user:create"),
  USER_UPDATE("user:update"),
  USER_DELETE("user:delete");

  private final String permission1; // Permission name
}
