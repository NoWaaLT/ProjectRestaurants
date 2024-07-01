package com.orioninc.ProjectRestaurants.DTO.order;

import com.orioninc.ProjectRestaurants.exceptions.RestaurantNotFoundException;
import com.orioninc.ProjectRestaurants.model.Order;
import com.orioninc.ProjectRestaurants.model.Restaurant;

import com.orioninc.ProjectRestaurants.repository.RestaurantRepository;
import com.orioninc.ProjectRestaurants.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@AllArgsConstructor
@Service
public class OrderRequestDTOMapper implements Function<OrderDTO, Order> {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    @Override
    public Order apply(OrderDTO orderDTO) {

        Order order = new Order();

        Restaurant restaurant = restaurantRepository.findById(orderDTO.restaurantId()).orElseThrow(()
                -> new RestaurantNotFoundException("Restaurant not found"));

        order.setRestaurant(restaurant);

        return order;

    }
}
