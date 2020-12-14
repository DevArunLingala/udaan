package com.udaan.interview.model;

import java.util.List;

public class History {
    private final Vehicle vehicle;
    private final List<Receipt> receipts;

    public History(Vehicle vehicle, List<Receipt> receipts) {
        this.vehicle = vehicle;
        this.receipts = receipts;
    }
}
