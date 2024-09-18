package com.orioninc.ProjectRestaurants.dto.menu;

import com.orioninc.ProjectRestaurants.dto.restaurant.RestaurantMapper;
import com.orioninc.ProjectRestaurants.model.Menu;
import com.orioninc.ProjectRestaurants.model.Restaurant;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = RestaurantMapper.class)
public interface MenuMapper {
  MenuResponseDto menuToMenuResponseDto(Menu menu);

  @Mapping(target = "id", ignore = true)
  @Mapping(source = "restaurant", target = "restaurant.id")
  @Mapping(target = "dishList", ignore = true)
  @Mapping(source = "restaurant", target = "restaurantName")
  Menu menuRequestDtoToMenu(MenuRequestDto menuRequestDTO);

  @Mapping(target = "id", ignore = true)
  @Mapping(source = "restaurant", target = "restaurant")
  @Mapping(target = "dishList", ignore = true)
  @Mapping(source = "restaurant.restaurantName", target = "restaurantName", qualifiedByName = "trimName")
  @Mapping(source = "menuRequestDto.menuName", target = "menuName")
  Menu menuRequestDtoToMenu(Restaurant restaurant, MenuRequestDto menuRequestDto);

  List<MenuResponseDto> toResponseList(List<MenuRequestDto> menuRequestList);

  @Named("trimName")
  default String trimName(String name) {
    return name.trim();
  }
}