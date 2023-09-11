package com.vinco_orbis.app.Excepciones;


public class BusinessException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

}