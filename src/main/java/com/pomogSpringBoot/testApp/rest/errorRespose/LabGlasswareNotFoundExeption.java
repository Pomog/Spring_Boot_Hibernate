package com.pomogSpringBoot.testApp.rest.errorRespose;

public class LabGlasswareNotFoundExeption extends RuntimeException {
    public LabGlasswareNotFoundExeption(String message) {
        super(message);
    }
    
    public LabGlasswareNotFoundExeption(String message, Throwable cause) {
        super(message, cause);
    }
    
    public LabGlasswareNotFoundExeption(Throwable cause) {
        super(cause);
    }
}
