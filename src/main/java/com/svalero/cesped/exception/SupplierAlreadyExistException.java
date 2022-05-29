package com.svalero.cesped.exception;

public class SupplierAlreadyExistException extends Exception{
    public SupplierAlreadyExistException(String message) {
        super(message);
    }

    public SupplierAlreadyExistException () {
        super("El proveedor ya existe.");
    }
}
