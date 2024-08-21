package com.orioninc.ProjectRestaurants.dto.recipe;

import com.orioninc.ProjectRestaurants.model.Recipe;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
@DecoratedWith(value = RecipeMapperDecorator.class)
public interface RecipeMapper {

    RecipeMapper INSTANCE = Mappers.getMapper(RecipeMapper.class);

    @Mapping(source = "dish.id", target = "dishId")
    @Mapping(source = "product.id", target = "productId")
    RecipeResponseDto recipeToRecipeResponseDto(Recipe recipe);

    @Mapping(target = "dish", ignore = true)
    @Mapping(target = "dishName", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "productName", ignore = true)
    Recipe recipeSaveDtoToRecipe(RecipeSaveDto recipeSaveDTO);

    @Mapping(target = "dish", ignore = true)
    @Mapping(target = "dishName", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "productName", ignore = true)
    Recipe recipeUpdateDtoToRecipe(RecipeUpdateDto recipeUpdateDto);

}
