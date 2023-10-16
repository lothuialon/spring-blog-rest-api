package com.lothuialon.blogapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class resourceNotFoundException extends RuntimeException{
    private String resorceName;
    private String title;
    private String message;


    public resourceNotFoundException(String resorceName, String title, String message) {
        super(resorceName + "not found with" + title + ": " + message);
        this.resorceName = resorceName;
        this.title = title;
        this.message = message;
    }

    public String getResorceName() {
        return this.resorceName;
    }

    public void setResorceName(String resorceName) {
        this.resorceName = resorceName;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
