package com.ineffable.inventoryservice.Exceptions;

public class StockOverflowException extends Exception{
    public StockOverflowException(String message) {
        super(message);
    }
}
