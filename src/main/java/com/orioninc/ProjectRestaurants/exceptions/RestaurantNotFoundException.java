package com.orioninc.ProjectRestaurants.exceptions;

import com.orioninc.ProjectRestaurants.enums.AppText;

public class RestaurantNotFoundException extends NotFoundException{

    public RestaurantNotFoundException(AppText message, Long id) {
        super(message.toString() + id);
    }

    public RestaurantNotFoundException(AppText message) {
        super(message.toString());
    }

}