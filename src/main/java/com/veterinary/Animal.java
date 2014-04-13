package com.veterinary;

import com.veterinary.service.Medicine;
import com.veterinary.service.Procedure;

import java.util.HashMap;
import java.util.Map;

public enum Animal {

    CAT(new HashMap<MedicalService, Integer>(){{
        this.put(Procedure.REGULAR_CHECKUP, 1000);
        this.put(Medicine.PARACETAMOL, Medicine.PARACETAMOL.price());
        this.put(Medicine.CROCIN, Medicine.CROCIN.price());
        this.put(Medicine.UNIENZYME, Medicine.UNIENZYME.price());
    }}),

    HORSE(new HashMap<MedicalService, Integer>(){{
        this.put(Procedure.REGULAR_CHECKUP, 5000);
        this.put(Procedure.SURGERY,10000);
        this.put(Medicine.PARACETAMOL, Medicine.PARACETAMOL.price());
        this.put(Medicine.UNIENZYME, Medicine.UNIENZYME.price());
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
