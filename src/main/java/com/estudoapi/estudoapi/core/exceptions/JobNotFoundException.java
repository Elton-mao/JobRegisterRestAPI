package com.estudoapi.estudoapi.core.exceptions;

public class JobNotFoundException extends ModelNotFoundExceptions {

    public JobNotFoundException() {
        super("Job not found with id: ");
    }

    public JobNotFoundException(String message) {
        super(message);
    }
    
}
