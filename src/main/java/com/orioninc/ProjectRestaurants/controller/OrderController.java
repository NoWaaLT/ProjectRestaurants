package com.orioninc.ProjectRestaurants.controller;

import com.orioninc.ProjectRestaurants.model.Order;
import com.orioninc.ProjectRestaurants.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/restaurant")
@AllArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @PreAuthorize("hasAuthority('ROLE_USER')")
  @GetMapping(value = "/my-order")
  public String getOrderByUser() {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    Optional<Order> orderOptional = orderService.getOrderByUser(authentication.getName());

    return orderOptional
        .map(order -> "Your order: " + order.getOrderName())
        .orElse("You don't have any orders.");
  }
}
