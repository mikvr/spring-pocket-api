package com.rnd.corp.springpocketapi.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class OperationException extends GlobalResourceException {

    public OperationException(String message, HttpStatus status) {
        super(message, status);
    }

}
