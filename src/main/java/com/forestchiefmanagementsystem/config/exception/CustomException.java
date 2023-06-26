package com.forestchiefmanagementsystem.config.exception;

public class CustomException extends RuntimeException{
    Object obj;
    public CustomException(String message) {
        super(message);
    }
    public static void cast(String message){
        throw new CustomException(message);
    }

}
