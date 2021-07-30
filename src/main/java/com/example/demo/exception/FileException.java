package com.example.demo.exception;

/**
 * @Author 赵思阳
 * @Date 2021/7/15 11:06
 * @Version 1.0
 */
public class FileException extends RuntimeException{
    public FileException(String message) {
        super(message);
    }

    public FileException(String message, Throwable cause) {
        super(message, cause);
    }
}
