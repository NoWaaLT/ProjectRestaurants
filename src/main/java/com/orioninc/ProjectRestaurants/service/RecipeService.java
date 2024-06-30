package com.orioninc.ProjectRestaurants.service;

import com.orioninc.ProjectRestaurants.DTO.recipe.RecipeResponseDTO;
import com.orioninc.ProjectRestaurants.DTO.recipe.RecipeSaveDTO;
import com.orioninc.ProjectRestaurants.DTO.recipe.RecipeUpdateDTO;
import com.orioninc.ProjectRestaurants.model.Recipe;

import java.util.List;

public interface RecipeService {

    List<RecipeResponseDTO> getAllRecipes();
    RecipeResponseDTO getRecipeById(Long id);
    Recipe saveRecipe(RecipeSaveDTO recipeSaveDTO);
    Recipe updateRecipe(RecipeUpdateDTO recipeUpdateDTO);
    void deleteRecipe(Long id);

}
