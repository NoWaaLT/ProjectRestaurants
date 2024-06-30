package com.orioninc.ProjectRestaurants.service.implementation;


import com.orioninc.ProjectRestaurants.DTO.order.OrderRequestDTO;
import com.orioninc.ProjectRestaurants.DTO.order.OrderResponseDTOMapper;
import com.orioninc.ProjectRestaurants.model.Order;
import com.orioninc.ProjectRestaurants.repository.OrderRepository;
import com.orioninc.ProjectRestaurants.service.OrderService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;

  @Override
  public List<OrderRequestDTO> getAllOrders() {
    return List.of();
  }

  @Override
  public Optional<Order> getOrderByUser(String username) {

    return orderRepository.findOrderByUser(username);
  }


//  @Override
//  public OrderResponseDTO getOrderByUser() {
//
//    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//    return orderResponseDTOMapper.apply(orderRepository.findOrderByUser().get());   // TODO fix this
//  }
}
