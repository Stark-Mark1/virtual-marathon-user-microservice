package com.virtualmarathon.user.customerror;

import org.springframework.http.HttpStatus;

public class UserInfoException extends RuntimeException{
    private final HttpStatus status;

    public UserInfoException(String message, HttpStatus status) {
        super(message);
        this.status=status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
