package com.pomogSpringBoot.testApp.rest.errorRespose;

public class LabGlasswareNotFoundException extends RuntimeException {
    public LabGlasswareNotFoundException(String message) {
        super(message);
    }
    
    public LabGlasswareNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public LabGlasswareNotFoundException(Throwable cause) {
        super(cause);
    }
}
