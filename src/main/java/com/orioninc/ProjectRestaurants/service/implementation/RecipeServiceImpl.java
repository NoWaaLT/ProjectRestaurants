package com.orioninc.ProjectRestaurants.service.implementation;

import com.orioninc.ProjectRestaurants.DTO.recipe.RecipeSaveDTO;
import com.orioninc.ProjectRestaurants.DTO.recipe.RecipeUpdateDTO;
import com.orioninc.ProjectRestaurants.DTO.recipe.RecipeResponseDTO;
import com.orioninc.ProjectRestaurants.DTO.recipe.mapper.RecipeRequestDTOMapper;
import com.orioninc.ProjectRestaurants.DTO.recipe.mapper.RecipeResponseDTOMapper;
import com.orioninc.ProjectRestaurants.DTO.recipe.mapper.RecipeUpdateDTOMapper;
import com.orioninc.ProjectRestaurants.exceptions.DishNotFoundException;
import com.orioninc.ProjectRestaurants.exceptions.ProductNotFoundException;
import com.orioninc.ProjectRestaurants.exceptions.RecipeNotFoundException;
import com.orioninc.ProjectRestaurants.model.Dish;
import com.orioninc.ProjectRestaurants.model.Product;
import com.orioninc.ProjectRestaurants.model.Recipe;
import com.orioninc.ProjectRestaurants.repository.DishRepository;
import com.orioninc.ProjectRestaurants.repository.ProductRepository;
import com.orioninc.ProjectRestaurants.repository.RecipeRepository;
import com.orioninc.ProjectRestaurants.service.RecipeService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final DishRepository dishRepository;
    private final ProductRepository productRepository;
    private final RecipeResponseDTOMapper recipeResponseDTOMapper;
    private final RecipeRequestDTOMapper recipeRequestDTOMapper;
    private final RecipeUpdateDTOMapper recipeUpdateDTOMapper;


    @Override
    public List<RecipeResponseDTO> getAllRecipes() {
        return recipeRepository.findAll()
                .stream()
                .map(recipeResponseDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public RecipeResponseDTO getRecipeById(Long id) {
        return recipeResponseDTOMapper.apply(recipeRepository.findById(id).orElseThrow(()
                -> new RecipeNotFoundException("Recipe is not found.")));
    }

    @Override
    public Recipe saveRecipe(RecipeSaveDTO recipeSaveDTO) {
        return recipeRepository.save(recipeRequestDTOMapper.apply(recipeSaveDTO));
    }

    @Override
    public Recipe updateRecipe(RecipeUpdateDTO recipeUpdateDTO) {
        Recipe recipeToUpdate = recipeUpdateDTOMapper.apply(recipeUpdateDTO);
        Recipe existingRecipe = recipeRepository.findById(recipeToUpdate.getId()).orElseThrow(()
                -> new RecipeNotFoundException("Recipe is not found."));

        existingRecipe.setId(recipeToUpdate.getId());

        Dish dish = dishRepository.findById(recipeToUpdate.getDish().getId()).orElseThrow(()
                -> new DishNotFoundException("Dish in not found."));
        existingRecipe.setDish(dish);
        existingRecipe.setDishName(dish.getDishName());

        Product product = productRepository.findById(recipeToUpdate.getId()).orElseThrow(()
                -> new ProductNotFoundException("Product is not found."));
        existingRecipe.setProduct(product);
        existingRecipe.setProductName(product.getProductName());

        existingRecipe.setQuantity(recipeUpdateDTO.quantity());
        return existingRecipe;
    }

    @Override
    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }
}
