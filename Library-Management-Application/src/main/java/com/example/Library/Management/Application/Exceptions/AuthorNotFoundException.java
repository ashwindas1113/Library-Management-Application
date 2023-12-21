package com.example.Library.Management.Application.Exceptions;

public class AuthorNotFoundException extends Exception{
    public AuthorNotFoundException(final String message) {
        super(message);
    }
}
