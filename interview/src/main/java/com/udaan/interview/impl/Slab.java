package com.udaan.interview.impl;

import lombok.Getter;

import java.util.Map;

@Getter
public class Slab {
    private final Map<Integer, Double> slabs;

    public Slab(Map<Integer, Double> slabs) {
        this.slabs = slabs;
    }
}
