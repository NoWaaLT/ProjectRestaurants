package com.orioninc.ProjectRestaurants.exceptions;

import com.orioninc.ProjectRestaurants.enums.AppText;

public class ExpireNotFoundException extends NotFoundException {

    public ExpireNotFoundException(AppText message) {
        super(message.getDescription());
    }

    public ExpireNotFoundException(AppText message, Long id) {
        super(message.toString() + id);
    }
}
