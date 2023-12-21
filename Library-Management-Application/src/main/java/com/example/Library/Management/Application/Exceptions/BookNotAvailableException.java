package com.example.Library.Management.Application.Exceptions;

public class BookNotAvailableException extends Exception{
    public BookNotAvailableException(String message) {
        super(message);
    }
}
