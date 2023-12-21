package com.example.Library.Management.Application.Exceptions;

public class BookNotFoundException extends Exception{
    public BookNotFoundException(String message) {
        super(message);
    }
}
