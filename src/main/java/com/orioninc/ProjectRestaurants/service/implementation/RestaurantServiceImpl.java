package com.orioninc.ProjectRestaurants.service.implementation;

import com.orioninc.ProjectRestaurants.DTO.restaurant.RestaurantDTO;
import com.orioninc.ProjectRestaurants.DTO.restaurant.RestaurantRequestDTOMapper;
import com.orioninc.ProjectRestaurants.DTO.restaurant.RestaurantResponseDTOMapper;
import com.orioninc.ProjectRestaurants.exceptions.RestaurantNotFoundException;
import com.orioninc.ProjectRestaurants.model.Restaurant;
import com.orioninc.ProjectRestaurants.repository.RestaurantRepository;
import com.orioninc.ProjectRestaurants.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantResponseDTOMapper restaurantResponseDTOMapper;
    private final RestaurantRequestDTOMapper restaurantRequestDTOMapper;

    @Override
    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantRepository.findAll()
                .stream()
                .map(restaurantResponseDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public RestaurantDTO getRestaurantById(Long id) {
        return restaurantResponseDTOMapper.apply(restaurantRepository.findById(id).orElseThrow(()
                -> new RestaurantNotFoundException("Restaurant is not found.")));
    }

    @Override
    public Restaurant saveRestaurant(RestaurantDTO restaurantDTO) {
        return restaurantRepository.save(restaurantRequestDTOMapper.apply(restaurantDTO));
    }

    @Override
    public Restaurant updateRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurantToUpdate = restaurantRequestDTOMapper.apply(restaurantDTO);
        Restaurant existingRestaurant = restaurantRepository.findById(restaurantToUpdate.getId()).orElseThrow(()
                -> new RestaurantNotFoundException("Restaurant is not Found"));

        existingRestaurant.setId(restaurantToUpdate.getId());
        existingRestaurant.setRestaurantName(restaurantToUpdate.getRestaurantName());
        existingRestaurant.setRestaurantType(restaurantToUpdate.getRestaurantType());

        return existingRestaurant;
    }

    @Override
    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }
}
