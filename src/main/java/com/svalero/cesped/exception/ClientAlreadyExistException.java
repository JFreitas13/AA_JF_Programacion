package com.svalero.cesped.exception;

public class ClientAlreadyExistException extends Exception {

    public ClientAlreadyExistException(String message) {
        super(message);
    }

    public ClientAlreadyExistException() {
        super("El cliente ya existe.");
    }
}
