package com.exam.helper;
public class UserFoundException extends Exception{
    public UserFoundException() {
        super("User already present in db ! please try with another one");
    }
    public UserFoundException(String message) {
        super(message);
    }
}