package com.udaan.interview.model;

import lombok.Getter;

@Getter
public class Receipt {
    private final ParkingDuration parkingDuration;
    private final double price;

    public Receipt(Vehicle vehicle, ParkingDuration parkingDuration, double price) {
        this.parkingDuration = parkingDuration;
        this.price = price;
    }
}
