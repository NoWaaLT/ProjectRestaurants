package com.orioninc.ProjectRestaurants.service;

import com.orioninc.ProjectRestaurants.dto.recipe.RecipeResponseDto;
import com.orioninc.ProjectRestaurants.dto.recipe.RecipeSaveDto;
import com.orioninc.ProjectRestaurants.dto.recipe.RecipeUpdateDto;
import com.orioninc.ProjectRestaurants.model.Recipe;

import java.util.List;

public interface RecipeService {

    List<RecipeResponseDto> getAllRecipes();
    RecipeResponseDto getRecipeById(Long id);
    Recipe saveRecipe(RecipeSaveDto recipeSaveDTO);
    Recipe updateRecipe(RecipeUpdateDto recipeUpdateDTO);
    void deleteRecipe(Long id);

}
