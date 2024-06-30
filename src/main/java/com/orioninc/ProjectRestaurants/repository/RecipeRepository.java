package com.orioninc.ProjectRestaurants.repository;

import com.orioninc.ProjectRestaurants.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
