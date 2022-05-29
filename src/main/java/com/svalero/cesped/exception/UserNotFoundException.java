package com.svalero.cesped.exception;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException() {
        super("Datos incorrecto. Intentalo de nuevo.");
    }
}
