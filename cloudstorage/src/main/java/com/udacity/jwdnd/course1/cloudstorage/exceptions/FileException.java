package com.udacity.jwdnd.course1.cloudstorage.exceptions;

public class FileException extends RuntimeException {
    public FileException(String message, Throwable exception) {
        super(message, exception);
    }

    public FileException (String message) {
        super(message);
    }
}
