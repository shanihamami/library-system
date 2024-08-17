package com.hamami.bookservice.exceptions;

public class BookPersistenceException extends Exception {
    public BookPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
