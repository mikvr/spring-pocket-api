package com.rnd.corp.springpocketapi.exception;

import org.springframework.http.HttpStatus;

public class BadRequestHandler extends GlobalResourceException {

    public BadRequestHandler(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
