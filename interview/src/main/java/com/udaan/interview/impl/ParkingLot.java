package com.udaan.interview.impl;

import com.udaan.interview.model.ParkingDuration;
import com.udaan.interview.model.Receipt;
import com.udaan.interview.model.Vehicle;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class ParkingLot {
    private final String number;
    private final String address;
    private final ParkingLotCapacity twoWheelerCapacity;
    private final ParkingLotCapacity fourWheelerCapacity;
    private final Slab twoWheelerSlab;
    private final Slab fourWheelerSlab;
    Map<Vehicle, ParkingDuration> currentParkingStore = new ConcurrentHashMap<>();
    Map<Vehicle, List<Receipt>> archivedParkingStore = new ConcurrentHashMap<>();

    public ParkingLot(String number, String address, int twoWheelerCapacity, int fourWheelerCapacity, Slab twoWheelerSlab, Slab fourWheelerSlab) {
        this.number = number;
        this.address = address;
        this.twoWheelerCapacity = new ParkingLotCapacity(twoWheelerCapacity);
        this.fourWheelerCapacity = new ParkingLotCapacity(fourWheelerCapacity);
        this.twoWheelerSlab = twoWheelerSlab;
        this.fourWheelerSlab = fourWheelerSlab;
    }

    public boolean park(Vehicle vehicle) {
        boolean canPark = false;
        switch (vehicle.getType()) {

            case TWO_WHEELER:
                canPark = twoWheelerCapacity.allot();
            case FOUR_WHEELER:
                canPark = fourWheelerCapacity.allot();
        }

        currentParkingStore.put(vehicle, new ParkingDuration());
        return canPark;
    }

    public Receipt unpark(Vehicle vehicle) {
        ParkingDuration parkingDuration = currentParkingStore.get(vehicle);
        if (parkingDuration == null) {
            throw new IllegalArgumentException("Vehicle not parked");
        }

        switch (vehicle.getType()) {
            case TWO_WHEELER:
                 twoWheelerCapacity.unAllot();
                 break;
            case FOUR_WHEELER:
                 fourWheelerCapacity.unAllot();
                 break;
        }

        ParkingDuration duration = currentParkingStore.remove(vehicle);
        duration.checkout();

        return generateZReceipt(vehicle, duration);
    }

    private Receipt generateZReceipt(Vehicle vehicle, ParkingDuration duration) {
        Slab slab = null;
        switch (vehicle.getType()) {

            case TWO_WHEELER:
                slab = twoWheelerSlab;
                break;
            case FOUR_WHEELER:
                slab = fourWheelerSlab;
                break;
        }

        Integer[] slabs  = slab.getSlabs().keySet().toArray(new Integer[0]);
        Arrays.sort(slabs);

        long parkedHours = duration.parkedHours();

        double price = 0;

        for (int i = 0; i < slabs.length && parkedHours > 0; i++) {
            int hours = slabs[i];
            double hrPrice = slab.getSlabs().get(hours);
            price = price + (hours * hrPrice);
            parkedHours = parkedHours - hours;
        }

        if (parkedHours > 0) {
            int hours = slabs[slabs.length - 1];
            double hrPrice = slab.getSlabs().get(hours);
            price = price + (hours * hrPrice);
        }

        Receipt receipt = new Receipt(vehicle, duration, price);
        List<Receipt> list = archivedParkingStore.getOrDefault(vehicle, new ArrayList<>());
        list.add(receipt);

        return receipt;
    }

    public List<Receipt> history(Vehicle vehicle) {
        return archivedParkingStore.getOrDefault(vehicle, new ArrayList<>());
    }
}
