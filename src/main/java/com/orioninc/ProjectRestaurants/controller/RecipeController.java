package com.orioninc.ProjectRestaurants.controller;

import com.orioninc.ProjectRestaurants.DTO.recipe.RecipeResponseDTO;
import com.orioninc.ProjectRestaurants.DTO.recipe.RecipeSaveDTO;
import com.orioninc.ProjectRestaurants.DTO.recipe.RecipeUpdateDTO;
import com.orioninc.ProjectRestaurants.model.Recipe;
import com.orioninc.ProjectRestaurants.service.RecipeService;

import lombok.AllArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
@AllArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @PreAuthorize("hasPermission(#id, 'Recipe', 'read')")
    @GetMapping
    public List<RecipeResponseDTO> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @PreAuthorize("hasPermission(#id, 'Recipe', 'read')")
    @GetMapping(value = "/{id}")
    public RecipeResponseDTO getRecipeById(@PathVariable Long id) {
        return recipeService.getRecipeById(id);
    }

    @PreAuthorize("hasPermission(#id, 'Recipe', 'create')")
    @PostMapping
    public Recipe saveRecipe(@RequestBody RecipeSaveDTO recipeSaveDTO) {
        return recipeService.saveRecipe(recipeSaveDTO);
    }

    // TODO make it by id

    @PreAuthorize("hasPermission(#id, 'Recipe', 'update')")
    @PutMapping(value = "/recipes/update")
    public Recipe updateRecipe(@RequestBody RecipeUpdateDTO recipeUpdateDTO) {
        return recipeService.updateRecipe(recipeUpdateDTO);
    }

    @PreAuthorize("hasPermission(#id, 'Recipe', 'delete')")
    @DeleteMapping(value = "/{id}")
    public void deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
    }
}
