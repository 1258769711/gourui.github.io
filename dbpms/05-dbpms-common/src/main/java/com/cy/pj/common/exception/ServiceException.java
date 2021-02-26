package com.cy.pj.common.exception;

public class ServiceException extends  RuntimeException{
    public ServiceException() {

    }
    public ServiceException(String message) {
        super(message);
    }
    public ServiceException(Throwable cause) {
        super(cause);
    }
}
