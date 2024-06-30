package com.orioninc.ProjectRestaurants.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ExpireNotFoundException extends RuntimeException {

    public ExpireNotFoundException(String message) {
        super(message);
    }

}
