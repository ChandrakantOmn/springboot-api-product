package com.example.demo.common;

import org.springframework.http.HttpStatus;

public class ApiResponse<T> {
    private String message;
    private HttpStatus status;
    private int statusCode;
    private T data;
    private String error;

    public ApiResponse(String message, HttpStatus status, int statusCode, T data, String error) {
        this.message = message;
        this.status = status;
        this.statusCode = statusCode;
        this.data = data;
        this.error = error;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getError() {
        return error;
    }
}
