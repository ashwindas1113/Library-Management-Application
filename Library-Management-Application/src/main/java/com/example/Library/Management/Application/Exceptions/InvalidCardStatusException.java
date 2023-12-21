package com.example.Library.Management.Application.Exceptions;

public class InvalidCardStatusException extends Exception{
    public InvalidCardStatusException(String message) {
        super(message);
    }
}
