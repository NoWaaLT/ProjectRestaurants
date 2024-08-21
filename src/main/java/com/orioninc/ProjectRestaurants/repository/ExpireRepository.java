package com.orioninc.ProjectRestaurants.repository;

import com.orioninc.ProjectRestaurants.model.Expire;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpireRepository extends JpaRepository<Expire, Long> {
//  Optional<List<ExpireResponseDTO>> findExpiresByProductId(Long id);
//  void editExpireData(float quantity, boolean status, long id);
}
