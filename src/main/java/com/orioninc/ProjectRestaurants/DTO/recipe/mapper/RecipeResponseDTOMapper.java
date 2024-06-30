package com.orioninc.ProjectRestaurants.DTO.recipe.mapper;

import com.orioninc.ProjectRestaurants.DTO.recipe.RecipeResponseDTO;
import com.orioninc.ProjectRestaurants.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class RecipeResponseDTOMapper implements Function<Recipe, RecipeResponseDTO> {

    @Override
    public RecipeResponseDTO apply(Recipe recipe) {
        return new RecipeResponseDTO(
                recipe.getDish().getId(),
                recipe.getProduct().getId(),
                recipe.getQuantity()
        );
    }
}
