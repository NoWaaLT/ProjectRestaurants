package com.orioninc.ProjectRestaurants.repository;

import com.orioninc.ProjectRestaurants.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

  @Query(value = "SELECT o FROM Order o WHERE o.user.username = ?1")
  Optional<Order> findOrderByUser(String username);
}
