package com.orioninc.ProjectRestaurants.exceptions;

import com.orioninc.ProjectRestaurants.enums.AppText;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class DishNotFoundException extends NotFoundException {

    public DishNotFoundException(AppText message, Long id) {
        super(message.toString() + id);
    }

}
