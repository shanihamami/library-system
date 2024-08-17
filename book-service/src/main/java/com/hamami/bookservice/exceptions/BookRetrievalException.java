package com.hamami.bookservice.exceptions;

public class BookRetrievalException extends RuntimeException {
    public BookRetrievalException(String message, Throwable cause) {
        super(message, cause);
    }
}
