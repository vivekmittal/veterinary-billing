package com.veterinary.service;

import com.veterinary.MedicalService;

public enum Medicine implements MedicalService {
    PARACETAMOL(100),
    UNIENZYME(40),
    CROCIN(10);

    private int price;

    private Medicine(int price) {
        this.price = price;
    }

    public int price() {
        return this.price;
    }
}
