package com.orioninc.ProjectRestaurants.exceptions.error;

import lombok.Getter;

import java.util.Date;

@Getter
public class ErrorDetails {

    private final Date timestamp;
    private final String message;
    private final String details;
    private final String exception;

    public ErrorDetails(Date timestamp, String message, String details, String throwableName) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.exception = throwableName;
    }


}