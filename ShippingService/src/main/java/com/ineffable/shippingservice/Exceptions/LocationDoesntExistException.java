package com.ineffable.shippingservice.Exceptions;

public class LocationDoesntExistException extends Exception {
    public LocationDoesntExistException(String message) {
        super(message);
    }
}
