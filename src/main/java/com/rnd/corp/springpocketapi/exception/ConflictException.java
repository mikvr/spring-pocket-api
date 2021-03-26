package com.rnd.corp.springpocketapi.exception;

import org.springframework.http.HttpStatus;

public class ConflictException extends GlobalResourceException {
    private static String message(String name) {
        return "L'utilisateur " + name + " existe déja";
    }

    public ConflictException(String name) {
        super(message(name), HttpStatus.CONFLICT);
    }
}
