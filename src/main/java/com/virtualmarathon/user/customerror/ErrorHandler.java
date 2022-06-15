package com.virtualmarathon.user.customerror;

import com.virtualmarathon.user.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorObject> handleException(UserInfoException e){
        ErrorObject error=new ErrorObject(e.getStatus().value(), e.getMessage(), Constants.getCurrentTime());
        return new ResponseEntity<>(error,e.getStatus());
    }
}
