package com.schoolserver.util;

public class ResponseError {
    private String message;

    public ResponseError(String message, String... args) {
        this.message = String.format(message, args);
    }

    public ResponseError(Exception ex) {
        this.message = ex.getMessage();
    }

    public String getMessage() {
        return this.message;
    }
}
