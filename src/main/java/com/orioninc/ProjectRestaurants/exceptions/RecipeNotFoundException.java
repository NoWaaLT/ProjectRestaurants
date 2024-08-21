package com.orioninc.ProjectRestaurants.exceptions;

import com.orioninc.ProjectRestaurants.enums.AppText;

public class RecipeNotFoundException extends NotFoundException {
    public RecipeNotFoundException(AppText message, Long id) {
        super(message.toString() + id);
    }

    public RecipeNotFoundException(AppText message) {
        super(message.toString());
    }
}
