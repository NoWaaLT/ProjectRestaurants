package com.orioninc.ProjectRestaurants.dto.dish;

import com.orioninc.ProjectRestaurants.model.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DishMapper {

  DishMapper INSTANCE = Mappers.getMapper(DishMapper.class);

  @Mapping(source = "menuId", target = "menu.id")
  @Mapping(target = "recipeQuantities", ignore = true)
  Dish dishDtoToDish(DishDto dishDTO);

  @Mapping(source = "menu.id", target = "menuId")
  DishDto dishToDishDto(Dish dish);

}
