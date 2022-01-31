package com.ineffable.orderservice.Models;

public class Variant {

    private String name;
    private String value;


    public Variant() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.name+"-"+this.value;
    }
}
