package com.udaan.interview.model;

import lombok.Getter;

@Getter
public abstract class Vehicle {
    private final VehicleType type;
    private final String number;
    public Vehicle(VehicleType type, String number) {
        this.type = type;
        this.number = number;
    }
}
