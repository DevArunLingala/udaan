package com.udaan.interview.model;

import lombok.Getter;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

@Getter
public class ParkingDuration {
    private final Timestamp checkedInTime;
    private Timestamp checkoutOutTime;

    public ParkingDuration() {
        this.checkedInTime = new Timestamp(System.currentTimeMillis());
    }

    public void checkout() {
        this.checkoutOutTime = new Timestamp(System.currentTimeMillis());
    }

    public long parkedHours() {
        long durationInMillis = this.checkoutOutTime.getTime() - this.checkedInTime.getTime();
        // should return ceiling of hours as parking fee is based hourly slab rates
        // so adjusting durationMillis
        durationInMillis = durationInMillis + TimeUnit.MINUTES.toMillis(59);
        return TimeUnit.MILLISECONDS.toHours(durationInMillis);
    }
}
