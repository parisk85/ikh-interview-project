package com.iknowhow.springboot.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {

    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
