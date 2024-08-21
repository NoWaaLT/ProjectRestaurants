package com.orioninc.ProjectRestaurants.exceptions;

import com.orioninc.ProjectRestaurants.enums.AppText;

public class ProductNotFoundException extends NotFoundException {

  public ProductNotFoundException(AppText message, Long id) {
    super(message.toString() + id);
  }

  public ProductNotFoundException(AppText message, String productName, Long restaurantId) {
    super(message.toString() + productName + " and restaurant id: " + restaurantId);
  }

  public ProductNotFoundException(AppText message, String username) {
    super(message.toString() + username);
  }
}
