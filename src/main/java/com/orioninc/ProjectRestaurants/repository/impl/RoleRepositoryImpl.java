package com.orioninc.ProjectRestaurants.repository.impl;

import com.orioninc.ProjectRestaurants.model.Permission;
import com.orioninc.ProjectRestaurants.model.Role;
import com.orioninc.ProjectRestaurants.repository.RoleRepository;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class RoleRepositoryImpl extends BaseRepositoryImpl<Role, Long> implements RoleRepository {

  public RoleRepositoryImpl(EntityManager entityManager) {
    super(Role.class, entityManager);
  }

  @Override
  public Optional<Role> findByRoleName(String roleName) {
    return Optional.ofNullable(
        jpaQueryFactory
            .select(role)
            .from(role)
            .where(role.roleName.equalsIgnoreCase(roleName))
            .fetchFirst());
  }

  @Override
  public List<Permission> getPermissionsByRole() {
    return null; // TODO change it
  }
}
