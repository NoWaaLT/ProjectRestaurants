package com.orioninc.ProjectRestaurants.permission;

import lombok.AllArgsConstructor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@AllArgsConstructor
@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

  private static final Logger logger = LogManager.getLogger(CustomPermissionEvaluator.class);

  @Override
  public boolean hasPermission(
      Authentication authentication, Object targetDomainObject, Object permission) {

    if ((authentication == null)
        || (targetDomainObject == null)
        || !(permission instanceof String)) {
      logger.debug(
          "Invalid parameters: authentication={}, targetDomainObject={}, permission={}",
          authentication,
          targetDomainObject,
          permission);
      return false;
    }

    final String targetType = targetDomainObject.getClass().getSimpleName();

    logger.info("targetDomainObject :  targetDomainObject.getClass()={}", targetDomainObject);

    logger.info("Evaluating permission: targetType={}, permission={}", targetType, permission);

    return hasPrivilege(authentication, targetType, ((String) permission));
  }

  @Override
  public boolean hasPermission(
      Authentication authentication, Serializable targetId, String targetType, Object permission) {
    if ((authentication == null) || (targetType == null) || !(permission instanceof String)) {
      logger.debug(
          "Invalid parameters: authentication={}, targetId={}, targetType={}, permission={}",
          authentication,
          targetId,
          targetType,
          permission);
      return false;
    }
    return hasPrivilege(authentication, targetType, permission.toString());
  }

  private boolean hasPrivilege(Authentication auth, String targetType, String permission) {

    for (GrantedAuthority grantedAuth : auth.getAuthorities()) {
      if (grantedAuth.getAuthority().contains(permission)) {
        return true;
      }
    }

    logger.debug("Permission denied: targetType={}, permission={}", targetType, permission);

    return false;
  }
}
