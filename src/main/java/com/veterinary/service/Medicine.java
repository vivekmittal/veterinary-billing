package com.veterinary.service;

import com.veterinary.MedicalService;

public enum Medicine implements MedicalService {
    SOME_SORT(100);

    private int price;

    private Medicine(int price) {
        this.price = price;
    }

    public int price() {
        return this.price;
    }
}
