package com.rnd.corp.springpocketapi.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends GlobalResourceException {
    public static String MESSAGE_HANDLER = "La resource demandée n'a pas été trouvé";

    public ResourceNotFoundException() {
        super(MESSAGE_HANDLER, HttpStatus.NOT_FOUND);
    }
}
