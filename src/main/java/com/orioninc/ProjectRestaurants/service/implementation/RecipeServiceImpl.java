package com.orioninc.ProjectRestaurants.service.implementation;

import com.orioninc.ProjectRestaurants.dto.recipe.RecipeMapper;
import com.orioninc.ProjectRestaurants.dto.recipe.RecipeSaveDto;
import com.orioninc.ProjectRestaurants.dto.recipe.RecipeUpdateDto;
import com.orioninc.ProjectRestaurants.dto.recipe.RecipeResponseDto;
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
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.orioninc.ProjectRestaurants.enums.AppText.*;

@AllArgsConstructor
@Service
public class RecipeServiceImpl implements RecipeService {

  private final RecipeRepository recipeRepository;
  private final DishRepository dishRepository;
  private final ProductRepository productRepository;

  @Transactional(readOnly = true)
  @Override
  public List<RecipeResponseDto> getAllRecipes() {
    List<Recipe> recipeList = recipeRepository.findAll();
    if (recipeList.isEmpty()) {
      throw new RecipeNotFoundException(RECIPES_EMPTY);
    }
    return recipeList.stream().map(RecipeMapper.INSTANCE::recipeToRecipeResponseDto).toList();
  }

  @Transactional(readOnly = true)
  @Override
  public RecipeResponseDto getRecipeById(Long id) {
    return RecipeMapper.INSTANCE.recipeToRecipeResponseDto(
        recipeRepository
            .findById(id)
            .orElseThrow(() -> new RecipeNotFoundException(RECIPE_BY_ID_NOT_FOUND, id)));
  }

  @Transactional
  @Override
  public Recipe saveRecipe(RecipeSaveDto recipeSaveDTO) {
    return recipeRepository.save(RecipeMapper.INSTANCE.recipeSaveDtoToRecipe(recipeSaveDTO));
  }

  @Transactional(isolation = Isolation.READ_COMMITTED)
  @Override
  public Recipe updateRecipe(RecipeUpdateDto recipeUpdateDTO) {
    Recipe recipeToUpdate = RecipeMapper.INSTANCE.recipeUpdateDtoToRecipe(recipeUpdateDTO);
    Recipe existingRecipe =
        recipeRepository
            .findById(recipeToUpdate.getId())
            .orElseThrow(
                () -> new RecipeNotFoundException(RECIPE_BY_ID_NOT_FOUND, recipeToUpdate.getId()));

    existingRecipe.setId(recipeToUpdate.getId());

    Dish dish =
        dishRepository
            .findById(recipeToUpdate.getDish().getId())
            .orElseThrow(
                () ->
                    new DishNotFoundException(
                        DISH_BY_ID_NOT_FOUND, recipeToUpdate.getDish().getId()));
    existingRecipe.setDish(dish);
    existingRecipe.setDishName(dish.getDishName());

    Product product =
        productRepository
            .findById(
                recipeToUpdate
                    .getProduct()
                    .getId()) // Changed  from getId() to getProduct().getId()
            .orElseThrow(
                () ->
                    new ProductNotFoundException(
                        PRODUCT_BY_ID_NOT_FOUND, recipeToUpdate.getProduct().getId()));
    existingRecipe.setProduct(product);
    existingRecipe.setProductName(product.getProductName());

    existingRecipe.setQuantity(recipeUpdateDTO.quantity());
    return existingRecipe;
  }

  @Transactional
  @Override
  public void deleteRecipe(Long id) {
    recipeRepository.deleteById(id);
  }
}
