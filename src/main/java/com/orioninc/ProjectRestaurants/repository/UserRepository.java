package com.orioninc.ProjectRestaurants.repository;

import com.orioninc.ProjectRestaurants.model.QUser;
import com.orioninc.ProjectRestaurants.model.User;

import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String username);

//  // Example of QueryDSL usage
//  default List<User> findAllWithRoles() {
//    JPAQuery<User> query = new JPAQuery<>(this.getEntityManager());
//    QUser qUser = QUser.user; // Generated QueryDSL class for User entity
//
//    return query.select(qUser)
//            .leftJoin(qUser.roles).fetchJoin()
//            .fetch();
//  }

//  @Query("SELECT u FROM User u JOIN FETCH u.roles")
//  List<User> findAllWithRoles();
//
//  // Other query methods if needed
}

//@Repository
//public interface UserRepository extends JpaRepository<User, Long>, QuerydslPredicateExecutor<User> {
//
//  // Example of QueryDSL usage
//  default List<User> findAllWithRoles() {
//    JPAQuery<User> query = new JPAQuery<>(this.getEntityManager());
//    QUser qUser = QUser.user; // Generated QueryDSL class for User entity
//
//    return query.select(qUser)
//            .leftJoin(qUser.roles).fetchJoin()
//            .fetch();
//  }
//
//  // Other query methods if needed
//}