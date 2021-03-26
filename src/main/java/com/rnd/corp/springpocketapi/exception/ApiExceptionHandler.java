package com.rnd.corp.springpocketapi.exception;

import java.util.Date;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(OperationException.class)
    public ResponseEntity<Object> operationExceptionHandler(OperationException e) {
        return responseException(e);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> resourceNotFoundExceptionHandler(ResourceNotFoundException e) {
        return responseException(e);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Object> conflictExceptionHandler(ConflictException e) {
        return responseException(e);
    }

    @ExceptionHandler(BadRequestHandler.class)
    public ResponseEntity<Object> badRequestExceptionHandler(BadRequestHandler e) {
        return responseException(e);
    }

    @ExceptionHandler(UnauthorizedExceptionHandler.class)
    public ResponseEntity<Object> unauthorizedExceptionHandler(UnauthorizedExceptionHandler e) {
        return responseException(e);
    }

    private ResponseEntity<Object> responseException(GlobalResourceException e) {
        ApiException exception = new ApiException(e.getMessage(), e.getStatus(), e, new Date());
        return ResponseEntity.status(exception.getStatus()).body(exception.getMessage());
    }

}
