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
@RequestMapping("/api/restaurant")
@AllArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/recipes/get")
    public List<RecipeResponseDTO> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/recipes/get/{id}")
    public RecipeResponseDTO getRecipeById(@PathVariable Long id) {
        return recipeService.getRecipeById(id);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(value = "/recipes/save")
    public Recipe saveRecipe(@RequestBody RecipeSaveDTO recipeSaveDTO) {
        return recipeService.saveRecipe(recipeSaveDTO);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping(value = "/recipes/update")
    public Recipe updateRecipe(@RequestBody RecipeUpdateDTO recipeUpdateDTO) {
        return recipeService.updateRecipe(recipeUpdateDTO);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping(value = "/recipes/delete/{id}")
    public void deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
    }
}
