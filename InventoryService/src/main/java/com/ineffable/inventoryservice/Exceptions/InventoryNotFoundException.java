package com.ineffable.inventoryservice.Exceptions;

public class InventoryNotFoundException extends Exception {
    public InventoryNotFoundException(String message) {
        super(message);
    }
}
