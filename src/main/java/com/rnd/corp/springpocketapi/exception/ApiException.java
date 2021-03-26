package com.rnd.corp.springpocketapi.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ApiException {
    private String message;
    private HttpStatus status;
    private Throwable throwable;
    private Date timestamp;
}
