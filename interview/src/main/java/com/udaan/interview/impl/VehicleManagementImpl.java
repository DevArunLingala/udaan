package com.udaan.interview.impl;

import com.udaan.interview.api.VehicleManagement;
import com.udaan.interview.model.Receipt;
import com.udaan.interview.model.VehicleType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VehicleManagementImpl implements VehicleManagement {
    private final ParkingLotRegistry parkingLotRegistry;

    public VehicleManagementImpl(ParkingLotRegistry parkingLotRegistry) {
        this.parkingLotRegistry = parkingLotRegistry;
    }

    @Override
    public boolean allotParking(String parkingLotNumber, VehicleType type, String number) {
        ParkingLot parkingLot = parkingLotRegistry.get(parkingLotNumber);
        if (parkingLot == null) {
            throw new IllegalArgumentException(String.format("Parking lot with number=%s does not exists", parkingLotNumber));
        }
        return false;
    }

    @Override
    public boolean isParked(String parkingLotNumber, VehicleType type, String number) {
        return false;
    }

    @Override
    public double parkingCharge(VehicleType type, String number) {
        return 0;
    }

    @Override
    public List<Receipt> history(String parkingLotNumber, VehicleType type, String number) {
        return null;
    }
}
