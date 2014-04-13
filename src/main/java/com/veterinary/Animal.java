package com.veterinary;

import com.veterinary.service.Medicine;
import com.veterinary.service.Procedure;

import java.util.HashMap;
import java.util.Map;

public enum Animal {

    CAT(new HashMap<Service, Integer>(){{
        this.put(Procedure.REGULAR_CHECKUP, 1000);
        this.put(Medicine.SOME_SORT, Medicine.SOME_SORT.price());
    }}),

    HORSE(new HashMap<Service, Integer>(){{
        this.put(Procedure.REGULAR_CHECKUP, 5000);
        this.put(Procedure.SURGERY,10000);
        this.put(Medicine.SOME_SORT, Medicine.SOME_SORT.price());
    }});

    private final Map<Service, Integer> servicesToPriceMap;

    private Animal(Map<Service, Integer> servicesToPriceMap) {
        this.servicesToPriceMap = servicesToPriceMap;
    }

    public Integer priceFor(Service service) {
        return this.servicesToPriceMap.get(service);
    }

    public boolean canHaveRequested(Service service) {
        return this.servicesToPriceMap.containsKey(service);
    }
}
