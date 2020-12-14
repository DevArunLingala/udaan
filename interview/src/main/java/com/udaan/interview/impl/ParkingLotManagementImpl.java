package com.udaan.interview.impl;

import com.udaan.interview.api.ParkingLotManagement;
import org.springframework.stereotype.Component;

@Component
public class ParkingLotManagementImpl implements ParkingLotManagement {
    private final ParkingLotRegistry parkingLotRegistry;

    public ParkingLotManagementImpl(ParkingLotRegistry parkingLotRegistry) {
        this.parkingLotRegistry = parkingLotRegistry;
    }

    @Override
    public String registerParkingLot(String address, String uniqueNumber, int twoWheelerCapacity, int fourWheelerCapacity, Slab twoWheelerSlab, Slab fourWheelerSlab) {
        if (parkingLotRegistry.get(uniqueNumber) != null) {
            throw new IllegalArgumentException(String.format("Parking lot with numer=%s already registered", uniqueNumber));
        }

        parkingLotRegistry.addLot(new ParkingLot(uniqueNumber, address, twoWheelerCapacity, fourWheelerCapacity, twoWheelerSlab, fourWheelerSlab));
        return uniqueNumber;
    }
}
