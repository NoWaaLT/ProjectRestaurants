package com.orioninc.ProjectRestaurants.controller;

import com.orioninc.ProjectRestaurants.dto.recipe.RecipeResponseDto;
import com.orioninc.ProjectRestaurants.dto.recipe.RecipeSaveDto;
import com.orioninc.ProjectRestaurants.dto.recipe.RecipeUpdateDto;
import com.orioninc.ProjectRestaurants.model.Recipe;
import com.orioninc.ProjectRestaurants.service.RecipeService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<RecipeResponseDto>> getAllRecipes() {
        return new ResponseEntity<>(recipeService.getAllRecipes(), HttpStatus.OK);
    }

    @PreAuthorize("hasPermission(#id, 'Recipe', 'read')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<RecipeResponseDto> getRecipeById(@PathVariable Long id) {
        return new ResponseEntity<>(recipeService.getRecipeById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasPermission(#id, 'Recipe', 'create')")
    @PostMapping
    public ResponseEntity<Recipe> saveRecipe(@RequestBody RecipeSaveDto recipeSaveDTO) {
        return new ResponseEntity<>(recipeService.saveRecipe(recipeSaveDTO), HttpStatus.CREATED);
    }

    @PreAuthorize("hasPermission(#id, 'Recipe', 'update')")
    @PutMapping(value = "/recipes/update")
    public ResponseEntity<Recipe> updateRecipe(@RequestBody RecipeUpdateDto recipeUpdateDTO) {
        return new ResponseEntity<>(recipeService.updateRecipe(recipeUpdateDTO), HttpStatus.OK);
    }

    @PreAuthorize("hasPermission(#id, 'Recipe', 'delete')")
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Deleted succesfully!")
    public void deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
    }
}
