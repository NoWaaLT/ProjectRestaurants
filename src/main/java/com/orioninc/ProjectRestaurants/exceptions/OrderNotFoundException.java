package com.orioninc.ProjectRestaurants.exceptions;

import com.orioninc.ProjectRestaurants.enums.AppText;

public class OrderNotFoundException extends NotFoundException {
  OrderNotFoundException(AppText message, Long id) {
    super(message.toString() + id);
  }

  public OrderNotFoundException(AppText message) {
    super(message.toString());
  }
}
