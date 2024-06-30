package com.orioninc.ProjectRestaurants.DTO.recipe.mapper;

import com.orioninc.ProjectRestaurants.DTO.recipe.RecipeUpdateDTO;
import com.orioninc.ProjectRestaurants.exceptions.DishNotFoundException;
import com.orioninc.ProjectRestaurants.exceptions.ProductNotFoundException;
import com.orioninc.ProjectRestaurants.model.Dish;
import com.orioninc.ProjectRestaurants.model.Product;
import com.orioninc.ProjectRestaurants.model.Recipe;
import com.orioninc.ProjectRestaurants.repository.DishRepository;
import com.orioninc.ProjectRestaurants.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class RecipeUpdateDTOMapper implements Function<RecipeUpdateDTO, Recipe> {

    private final DishRepository dishRepository;
    private final ProductRepository productRepository;

    @Override
    public Recipe apply(RecipeUpdateDTO recipeUpdateDTO) {

        Recipe recipe = new Recipe();
        recipe.setId(recipeUpdateDTO.id());

        Dish dish = dishRepository.findById(recipeUpdateDTO.dishId()).orElseThrow(() ->
                new DishNotFoundException("Dish is not found"));

        recipe.setDish(dish);
        recipe.setDishName(dish.getDishName());

        Product product = productRepository.findById(recipeUpdateDTO.productId()).orElseThrow(() ->
                new ProductNotFoundException("Product is not found"));

        recipe.setProduct(product);
        recipe.setProductName(product.getProductName());

        return recipe;
    }
}
