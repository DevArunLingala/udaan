package com.udaan.interview.impl;

import lombok.Getter;

@Getter
public class ParkingLotCapacity {
    private final int initialCapacity;
    private int usedCapacity = 0;

    public ParkingLotCapacity(int initialCapacity) {
        this.initialCapacity = initialCapacity;
    }

    public boolean allot() {
        if(usedCapacity < initialCapacity) {
            usedCapacity++;
            return true;
        }
        return false;
    }

    public void unAllot() {
        usedCapacity--;
    }
}
