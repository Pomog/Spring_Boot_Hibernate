package com.pomogSpringBoot.testApp.rest.errorRespose;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class LabGlasswareExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<LabGlasswareErrorResponse> notFoundHandler (LabGlasswareNotFoundException exc){
        var error = new LabGlasswareErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(new java.util.Date());
        
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler
    public ResponseEntity<LabGlasswareErrorResponse> handleException(Exception exc) {
        var error = new LabGlasswareErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(new java.util.Date());
        
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
