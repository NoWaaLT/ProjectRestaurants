package com.orioninc.ProjectRestaurants.exceptions;

import com.orioninc.ProjectRestaurants.enums.AppText;

public class MenuNotFoundException extends NotFoundException {

    public MenuNotFoundException(AppText message, Long id) {
        super(message.toString() + id);
    }

    public MenuNotFoundException(AppText message) {
        super(message.toString());
    }

}
