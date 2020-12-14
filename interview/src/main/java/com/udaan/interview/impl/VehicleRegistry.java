package com.udaan.interview.impl;

import com.udaan.interview.model.Vehicle;
import com.udaan.interview.model.VehicleType;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class VehicleRegistry {
    Map<VehicleType, Map<String, Vehicle>> vehicles = new ConcurrentHashMap<>();

    public void addVehicle(Vehicle vehicle) {
        switch (vehicle.getType()) {

            case TWO_WHEELER:
                Map<String, Vehicle> twoWheelers = vehicles.getOrDefault(VehicleType.TWO_WHEELER, new ConcurrentHashMap<>());
                twoWheelers.put(vehicle.getNumber(), vehicle);
                break;
            case FOUR_WHEELER:
                Map<String, Vehicle> fourWheelers = vehicles.getOrDefault(VehicleType.FOUR_WHEELER, new ConcurrentHashMap<>());
                fourWheelers.put(vehicle.getNumber(), vehicle);
                break;
        }
    }

    public Vehicle getVehicle(VehicleType type, String number) {
        return vehicles.get(type).get(number);
    }
}
