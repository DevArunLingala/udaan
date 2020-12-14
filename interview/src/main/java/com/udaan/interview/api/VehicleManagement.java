package com.udaan.interview.api;

import com.udaan.interview.model.Receipt;
import com.udaan.interview.model.Vehicle;
import com.udaan.interview.model.VehicleType;

import java.util.List;

public interface VehicleManagement {
    boolean allotParking(String parkingLotNumber, VehicleType type, String number);
    boolean isParked(String parkingLotNumber, VehicleType type, String number);
    double parkingCharge(VehicleType type, String number);
    List<Receipt> history(String parkingLotNumber, VehicleType type, String number);
}
