package com.orioninc.ProjectRestaurants.repository;

import com.orioninc.ProjectRestaurants.model.Permission;
import com.orioninc.ProjectRestaurants.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

 public interface RoleRepository extends JpaRepository<Role, Long> {

//public interface RoleRepository extends BaseRepository<Role, Long> {
  Optional<Role> findByRoleName(String roleName);
//  List<Permission> getPermissionsByRole();
}
