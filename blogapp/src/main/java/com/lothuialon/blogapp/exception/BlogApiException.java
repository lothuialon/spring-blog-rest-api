package com.lothuialon.blogapp.exception;

import org.springframework.http.HttpStatus;

public class BlogApiException extends RuntimeException{
    
    private HttpStatus status;
    private String message;


    public BlogApiException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public BlogApiException(HttpStatus status, String message, String message1) {
        super(message);
        this.status = status;
        this.message = message1;

    }

    public HttpStatus getStatus() {
        return this.status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "{" +
            " status='" + getStatus() + "'" +
            ", message='" + getMessage() + "'" +
            "}";
    }

}
