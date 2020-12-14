package com.udaan.interview.impl;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ParkingLotRegistry {
    private Map<String, ParkingLot> parkingLots = new ConcurrentHashMap<>();

    public void addLot(ParkingLot parkingLot) {
        parkingLots.put(parkingLot.getNumber(), parkingLot);
    }

    public ParkingLot get(String uniqueNumber) {
        return parkingLots.get(uniqueNumber);
    }
}
