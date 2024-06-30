package com.orioninc.ProjectRestaurants.DTO.recipe.mapper;

import com.orioninc.ProjectRestaurants.DTO.recipe.RecipeSaveDTO;
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

@AllArgsConstructor
@Service
public class RecipeRequestDTOMapper implements Function<RecipeSaveDTO, Recipe> {

    private final DishRepository dishRepository;
    private final ProductRepository productRepository;

//    @Override
//    public Recipe apply(RecipeResponseDTO recipeResponseDTO) {
//
//        Recipe recipe = new Recipe();
//
//        Dish dish = dishRepository.findById(recipeResponseDTO.dishId()).orElseThrow(() ->
//        new DishNotFoundException("Dish is not found"));
//
//        recipe.setDish(dish);
//        recipe.setDishName(dish.getDishName());
//
//        Product product = productRepository.findById(recipeResponseDTO.productId()).orElseThrow(() ->
//                new ProductNotFoundException("Product is not found"));
//
//        recipe.setProduct(product);
//        recipe.setProductName(product.getProductName());
//
//        recipe.setQuantity(recipeResponseDTO.quantity());
//
//        return recipe;
//    }

    @Override
    public Recipe apply(RecipeSaveDTO recipeSaveDTO) {

        Recipe recipe = new Recipe();

        Dish dish = dishRepository.findById(recipeSaveDTO.dishId()).orElseThrow(() ->
                new DishNotFoundException("Dish is not found"));

        recipe.setDish(dish);
        recipe.setDishName(dish.getDishName());

        Product product = productRepository.findById(recipeSaveDTO.productId()).orElseThrow(() ->
                new ProductNotFoundException("Product is not found"));

        recipe.setProduct(product);
        recipe.setProductName(product.getProductName());

        recipe.setQuantity(recipeSaveDTO.quantity());

        return recipe;
    }
}
