package com.orioninc.ProjectRestaurants.exceptions;

import com.orioninc.ProjectRestaurants.enums.AppText;

public class IncorrectPasswordException extends RuntimeException{
    public IncorrectPasswordException(AppText message) {
        super(message.getDescription());
    }
}