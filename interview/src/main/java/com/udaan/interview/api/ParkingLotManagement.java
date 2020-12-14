package com.udaan.interview.api;

import com.udaan.interview.impl.Slab;
import com.udaan.interview.model.VehicleType;

public interface ParkingLotManagement {
    String registerParkingLot(String address, String uniqueNumber, int twoWheelerCapacity, int fourWheelerCapacity, Slab twoWheelerSlab, Slab fourWheelerSlab);
}
