package com.rnd.corp.springpocketapi.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class GlobalResourceException extends RuntimeException {
    private HttpStatus status;

    public GlobalResourceException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
