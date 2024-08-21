package com.orioninc.ProjectRestaurants.service.implementation;

import com.orioninc.ProjectRestaurants.dto.order.OrderMapper;
import com.orioninc.ProjectRestaurants.dto.order.OrderResponseDto;
import com.orioninc.ProjectRestaurants.exceptions.OrderNotFoundException;
import com.orioninc.ProjectRestaurants.exceptions.ProductNotFoundException;
import com.orioninc.ProjectRestaurants.model.Order;
import com.orioninc.ProjectRestaurants.repository.OrderRepository;
import com.orioninc.ProjectRestaurants.service.OrderService;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.orioninc.ProjectRestaurants.enums.AppText.NO_ORDER_BY_USER;
import static com.orioninc.ProjectRestaurants.enums.AppText.ACTIVE_ORDERS_EMPTY;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;

  @Transactional(readOnly = true)
  @Override
  public Order getOrderByUserUsername() {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    return orderRepository
            .findOrderByUserUsername(username)
            .orElseThrow(() -> new ProductNotFoundException(NO_ORDER_BY_USER, username));
  }

  @Transactional(readOnly = true)
  @Override
  public List<OrderResponseDto> getAllOrders() {
    List<Order> orderList = orderRepository.findAll();
    if (orderList.isEmpty()) {
      throw new OrderNotFoundException(ACTIVE_ORDERS_EMPTY);
    }
    return orderList.stream().map(OrderMapper.INSTANCE::orderToOrderDto).toList();
  }

  @Transactional(readOnly = true)
  public List<OrderResponseDto> getAllOrdersByRestaurant(Long id) {
    List<Order> orderList = orderRepository.findAllOrderByRestaurantId(id);
    if (orderList.isEmpty()) {
      throw new OrderNotFoundException(ACTIVE_ORDERS_EMPTY);
    }
    return orderList.stream()
        .map(OrderMapper.INSTANCE::orderToOrderDto)
        .toList();
  }

  // TODO add orders methods
}
