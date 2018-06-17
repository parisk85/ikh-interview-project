package com.iknowhow.springboot.controller;

import com.iknowhow.springboot.exceptions.UserAlreadyeExistException;
import com.iknowhow.springboot.exceptions.UserNotFoundException;
import com.iknowhow.springboot.util.CustomErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestApiControllerAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public CustomErrorType handleUserNotFoundException(Exception e, WebRequest request) {
        return new CustomErrorType("Resource not found.");
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserAlreadyeExistException.class)
    public CustomErrorType handleUserAlreadyExistException(Exception e, WebRequest request) {
        return new CustomErrorType("User already exist.");
    }
}
