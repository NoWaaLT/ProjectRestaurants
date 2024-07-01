package com.orioninc.ProjectRestaurants.service.implementation;

import com.orioninc.ProjectRestaurants.DTO.order.OrderResponseDTO;
import com.orioninc.ProjectRestaurants.DTO.order.OrderResponseDTOMapper;
import com.orioninc.ProjectRestaurants.model.Order;
import com.orioninc.ProjectRestaurants.repository.OrderRepository;
import com.orioninc.ProjectRestaurants.service.OrderService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;
  private final OrderResponseDTOMapper orderResponseDTOMapper;

  @Override
  public List<OrderResponseDTO> getAllOrders() {
    return orderRepository.findAll().stream().map(orderResponseDTOMapper).toList();
  }

  @Override
  public List<OrderResponseDTO> getAllOrdersByRestaurant(Long id) {   // TODO check if it's works?
    return orderRepository.findAll()
            .stream()
            .filter((Order order) -> Objects.equals(order.getRestaurant().getId(), id))
            .map(orderResponseDTOMapper)
            .toList();
  }

  @Override
  public Optional<Order> getOrderByUser(String username) {

    return orderRepository.findOrderByUser(username);
  }
}
