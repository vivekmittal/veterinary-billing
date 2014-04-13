package com.veterinary;

import com.veterinary.service.Medicine;
import com.veterinary.service.Procedure;

import java.util.HashMap;
import java.util.Map;

public enum Animal {

    CAT(new HashMap<MedicalService, Integer>(){{
        this.put(Procedure.REGULAR_CHECKUP, 1000);
        this.put(Medicine.SOME_SORT, Medicine.SOME_SORT.price());
    }}),

    HORSE(new HashMap<MedicalService, Integer>(){{
        this.put(Procedure.REGULAR_CHECKUP, 5000);
        this.put(Procedure.SURGERY,10000);
        this.put(Medicine.SOME_SORT, Medicine.SOME_SORT.price());
    }});

    private final Map<MedicalService, Integer> servicesToPriceMap;

    private Animal(Map<MedicalService, Integer> servicesToPriceMap) {
        this.servicesToPriceMap = servicesToPriceMap;
    }

    public int priceFor(MedicalService medicalService) {
        return this.servicesToPriceMap.get(medicalService);
    }

    public boolean canHaveRequested(MedicalService medicalService) {
        return this.servicesToPriceMap.containsKey(medicalService);
    }
}
