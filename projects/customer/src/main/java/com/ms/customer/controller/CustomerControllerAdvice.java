package com.ms.customer.controller;

import com.ms.customer.exceptions.ContentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomerControllerAdvice {
    @ResponseBody
    @ExceptionHandler(ContentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String contentNotFoundExceptionHandle(ContentNotFoundException exception){
        return exception.getMessage();
    }
}
