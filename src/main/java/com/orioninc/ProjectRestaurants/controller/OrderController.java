package com.orioninc.ProjectRestaurants.controller;

import com.orioninc.ProjectRestaurants.dto.order.OrderResponseDto;
import com.orioninc.ProjectRestaurants.model.Order;
import com.orioninc.ProjectRestaurants.service.OrderService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @PreAuthorize("hasPermission(#id, 'Order', 'read')")
  @GetMapping(value = "/my-orders")
  public ResponseEntity<Order> getOrderByUser() {
    return new ResponseEntity<>(orderService.getOrderByUserUsername(), HttpStatus.OK);
  }

  @PreAuthorize("hasPermission(#id, 'Order', 'read')")
  @GetMapping
  public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
    return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
  }

  @PreAuthorize("hasPermission(#id, 'Order', 'read')")
  @GetMapping(value = "/restaurant-{id}")
  public ResponseEntity<List<OrderResponseDto>> getAllOrdersByRestaurant(@PathVariable Long id) {
    return new ResponseEntity<>(orderService.getAllOrdersByRestaurant(id), HttpStatus.OK);
  }

  // TODO save order for user

  // TODO delete order for employee

  // TODO update order

}
