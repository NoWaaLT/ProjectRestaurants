package com.orioninc.ProjectRestaurants.repository;

import com.orioninc.ProjectRestaurants.model.Expire;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository

public interface ExpireRepository extends JpaRepository<Expire, Long>, CustomRepository {
    Optional<List<Expire>> findAllExpireByProductId(Long productId);
}
