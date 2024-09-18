package com.orioninc.ProjectRestaurants.auth;

import com.orioninc.ProjectRestaurants.model.Permission;
import com.orioninc.ProjectRestaurants.model.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {

  private User user;

  public MyUserDetails(User user) {
    this.user = user;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {

    List<Permission> permissionList =
        user.getRoles().stream().toList().stream()
            .flatMap(
                role ->
                    role
                        .getPermissions()
                        .stream()) // FlatMap allows us to work with a list of lists
            .toList();

    return permissionList.stream()
        .map(permission -> new SimpleGrantedAuthority(permission.getActionName()))
        .toList();
  }

  @Override
  public String getPassword() {
    return user.getPasswordHash();
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }

  public String getSalt() {
    return user.getSalt();
  }

  @Override
  public boolean isAccountNonExpired() {
    return UserDetails.super.isAccountNonExpired();
  }

  @Override
  public boolean isAccountNonLocked() {
    return UserDetails.super.isAccountNonLocked();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return UserDetails.super.isCredentialsNonExpired();
  }

  @Override
  public boolean isEnabled() {
    return UserDetails.super.isEnabled();
  }
}
