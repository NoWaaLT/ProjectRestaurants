package com.orioninc.ProjectRestaurants.service.implementation;

import com.orioninc.ProjectRestaurants.dto.restaurant.RestaurantDto;
import com.orioninc.ProjectRestaurants.dto.restaurant.RestaurantMapper;
import com.orioninc.ProjectRestaurants.exceptions.RestaurantNotFoundException;
import com.orioninc.ProjectRestaurants.model.Restaurant;
import com.orioninc.ProjectRestaurants.repository.RestaurantRepository;
import com.orioninc.ProjectRestaurants.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.orioninc.ProjectRestaurants.enums.AppText.RESTAURANTS_EMPTY;
import static com.orioninc.ProjectRestaurants.enums.AppText.RESTAURANT_BY_ID_NOT_FOUND;

@AllArgsConstructor
@Service
public class RestaurantServiceImpl implements RestaurantService {

  private final RestaurantRepository restaurantRepository;

  @Transactional(readOnly = true)
  @Override
  public List<RestaurantDto> getAllRestaurants() {
    List<Restaurant> restaurantList = restaurantRepository.findAll();
    if (restaurantList.isEmpty()) {
      throw new RestaurantNotFoundException(RESTAURANTS_EMPTY);
    }
    return restaurantList.stream()
        .map(RestaurantMapper.INSTANCE::restaurantToRestaurantDto)
        .toList();
  }

  @Transactional(readOnly = true)
  @Override
  public RestaurantDto getRestaurantById(Long id) {
    return RestaurantMapper.INSTANCE.restaurantToRestaurantDto(
        restaurantRepository
            .findById(id)
            .orElseThrow(() -> new RestaurantNotFoundException(RESTAURANT_BY_ID_NOT_FOUND, id)));
  }

  @Transactional
  @Override
  public Restaurant saveRestaurant(RestaurantDto restaurantDTO) {
    return restaurantRepository.save(
        RestaurantMapper.INSTANCE.restaurantDtoToRestaurant(restaurantDTO));
  }

  @Transactional(isolation = Isolation.READ_COMMITTED)
  @Override
  public Restaurant updateRestaurant(RestaurantDto restaurantDTO) {
    Restaurant restaurantToUpdate = RestaurantMapper.INSTANCE.restaurantDtoToRestaurant(restaurantDTO);
    Restaurant existingRestaurant =
        restaurantRepository
            .findById(restaurantToUpdate.getId())
            .orElseThrow(
                () ->
                    new RestaurantNotFoundException(
                        RESTAURANT_BY_ID_NOT_FOUND, restaurantToUpdate.getId()));

    existingRestaurant.setId(restaurantToUpdate.getId());
    existingRestaurant.setRestaurantName(restaurantToUpdate.getRestaurantName());
    existingRestaurant.setRestaurantType(restaurantToUpdate.getRestaurantType());

    return existingRestaurant;
  }

  @Transactional
  @Override
  public void deleteRestaurant(Long id) {
    restaurantRepository.deleteById(id);
  }
}
