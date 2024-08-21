package com.orioninc.ProjectRestaurants.exceptions;

import com.orioninc.ProjectRestaurants.enums.AppText;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException(AppText message, String username) {
        super(message.toString() + username);
    }

    public UserNotFoundException(AppText message, Long id) {
        super(message.toString() + id);
    }

    public UserNotFoundException(AppText message) {
        super(message.toString());
    }
}
