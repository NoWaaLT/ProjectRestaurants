package com.orioninc.ProjectRestaurants.dto.recipe;

import com.orioninc.ProjectRestaurants.exceptions.DishNotFoundException;
import com.orioninc.ProjectRestaurants.exceptions.ProductNotFoundException;
import com.orioninc.ProjectRestaurants.model.Dish;
import com.orioninc.ProjectRestaurants.model.Product;
import com.orioninc.ProjectRestaurants.model.Recipe;
import com.orioninc.ProjectRestaurants.repository.DishRepository;
import com.orioninc.ProjectRestaurants.repository.ProductRepository;

import static com.orioninc.ProjectRestaurants.enums.AppText.DISH_BY_ID_NOT_FOUND;
import static com.orioninc.ProjectRestaurants.enums.AppText.PRODUCT_BY_ID_NOT_FOUND;

public abstract class RecipeMapperDecorator implements RecipeMapper {

    RecipeMapper recipeMapper;
    private DishRepository dishRepository;
    private ProductRepository productRepository;

    protected void setRecipeMapper(RecipeMapper recipeMapper) {
        this.recipeMapper = recipeMapper;
    }
    protected void setProductRepository(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    protected void setDishRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Recipe recipeSaveDtoToRecipe(RecipeSaveDto recipeSaveDTO) {
        Recipe recipe = new Recipe();
        Dish dish =
                dishRepository
                        .findById(recipeSaveDTO.dishId())
                        .orElseThrow(() -> new DishNotFoundException(DISH_BY_ID_NOT_FOUND, recipeSaveDTO.dishId()));
        recipe.setDish(dish);
        recipe.setDishName(dish.getDishName());

        Product product =
                productRepository
                        .findById(recipeSaveDTO.productId())
                        .orElseThrow(
                                () ->
                                        new ProductNotFoundException(
                                                PRODUCT_BY_ID_NOT_FOUND, recipeSaveDTO.productId()));
        recipe.setProduct(product);
        recipe.setProductName(product.getProductName());
        recipe.setQuantity(recipeSaveDTO.quantity());

        return recipe;
    }

    @Override
    public Recipe recipeUpdateDtoToRecipe(RecipeUpdateDto recipeUpdateDto) {
        Recipe recipe = new Recipe();
        Dish dish =
                dishRepository
                        .findById(recipeUpdateDto.dishId())
                        .orElseThrow(() -> new DishNotFoundException(DISH_BY_ID_NOT_FOUND, recipeUpdateDto.dishId()));
        recipe.setDish(dish);
        recipe.setDishName(dish.getDishName());

        Product product =
                productRepository
                        .findById(recipeUpdateDto.productId())
                        .orElseThrow(
                                () ->
                                        new ProductNotFoundException(
                                                PRODUCT_BY_ID_NOT_FOUND, recipeUpdateDto.productId()));
        recipe.setProduct(product);
        recipe.setProductName(product.getProductName());
        recipe.setQuantity(recipeUpdateDto.quantity());

        return recipe;
    }

}
