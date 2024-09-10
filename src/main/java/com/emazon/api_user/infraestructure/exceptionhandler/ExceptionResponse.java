package com.emazon.api_user.infraestructure.exceptionhandler;

public class ExceptionResponse {

    private String message;
    private String status;

    public ExceptionResponse() {
    }

    public ExceptionResponse(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}