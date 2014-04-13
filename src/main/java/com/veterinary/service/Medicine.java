package com.veterinary.service;

import com.veterinary.Service;

public enum Medicine implements Service {
    SOME_SORT(100);

    private int price;

    private Medicine(int price) {
        this.price = price;
    }

    public int price() {
        return this.price;
    }
}
