package com.pomogSpringBoot.testApp.rest.errorRespose;

public class LabGlasswareException extends RuntimeException {
    public LabGlasswareException(String message) {
        super(message);
    }
    
    public LabGlasswareException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public LabGlasswareException(Throwable cause) {
        super(cause);
    }
}
