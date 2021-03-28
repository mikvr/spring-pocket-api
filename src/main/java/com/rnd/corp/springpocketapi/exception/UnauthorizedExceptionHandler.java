package com.rnd.corp.springpocketapi.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedExceptionHandler extends GlobalResourceException {

    private static String message() {
        return "Error: Unauthorized resource";
    }

    public UnauthorizedExceptionHandler() {
        super(message(), HttpStatus.UNAUTHORIZED);
    }
}
