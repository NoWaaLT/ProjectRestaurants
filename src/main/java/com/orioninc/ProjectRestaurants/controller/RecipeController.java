package com.orioninc.ProjectRestaurants.controller;

import com.orioninc.ProjectRestaurants.DTO.recipe.RecipeResponseDTO;
import com.orioninc.ProjectRestaurants.DTO.recipe.RecipeSaveDTO;
import com.orioninc.ProjectRestaurants.DTO.recipe.RecipeUpdateDTO;
import com.orioninc.ProjectRestaurants.model.Recipe;
import com.orioninc.ProjectRestaurants.service.RecipeService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
@AllArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping(value = "/recipes/get")
    public List<RecipeResponseDTO> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping(value = "/recipes/get/{id}")
    public RecipeResponseDTO getRecipeById(@PathVariable Long id) {
        return recipeService.getRecipeById(id);
    }

    @PostMapping(value = "/recipes/save")
    public Recipe saveRecipe(@RequestBody RecipeSaveDTO recipeSaveDTO) {
        return recipeService.saveRecipe(recipeSaveDTO);
    }

    @PutMapping(value = "/recipes/update")
    public Recipe updateRecipe(@RequestBody RecipeUpdateDTO recipeUpdateDTO) {
        return recipeService.updateRecipe(recipeUpdateDTO);
    }

    @DeleteMapping(value = "/recipes/delete/{id}")
    public void deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
    }
}
