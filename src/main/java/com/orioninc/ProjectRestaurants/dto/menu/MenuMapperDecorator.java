//package com.orioninc.ProjectRestaurants.dto.menu;
//
//import com.orioninc.ProjectRestaurants.exceptions.RestaurantNotFoundException;
//import com.orioninc.ProjectRestaurants.model.Menu;
//import com.orioninc.ProjectRestaurants.model.Restaurant;
//import com.orioninc.ProjectRestaurants.repository.RestaurantRepository;
//import org.mapstruct.Context;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Component;
//
//import static com.orioninc.ProjectRestaurants.enums.AppText.PRODUCT_IN_RESTAURANT_BY_ID_NOT_FOUND;
//
//@Component
//public abstract class MenuMapperDecorator implements MenuMapper {
//
//  private RestaurantRepository restaurantRepository;
//
//  @Qualifier("delegate")
//  private MenuMapper delegate;
//
//  protected MenuMapperDecorator(RestaurantRepository restaurantRepository, MenuMapper delegate) {
//    this.restaurantRepository = restaurantRepository;
//    this.delegate = delegate;
//  }
//
//  @Override
//  public Menu menuRequestDtoToMenu(MenuRequestDto menuRequestDTO) {
//    Menu menu = delegate.menuRequestDtoToMenu(menuRequestDTO);
//
//    Restaurant restaurant =
//            restaurantRepository
//                    .findById(menuRequestDTO.restaurant())
//                    .orElseThrow(
//                            () ->
//                                    new RestaurantNotFoundException(
//                                            PRODUCT_IN_RESTAURANT_BY_ID_NOT_FOUND, menuRequestDTO.restaurant()));
//
//    menu.setRestaurant(restaurant);
//    menu.setRestaurantName(restaurant.getRestaurantName());
//
//    return menu;
//  }
//}