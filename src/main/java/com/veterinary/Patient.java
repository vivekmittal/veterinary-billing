package com.veterinary;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.veterinary.billing.Bill;
import com.veterinary.exception.ServiceNotAvailableForAnimalException;

import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

public class Patient {
    private final Animal animal;
    private final Set<Service> servicesUsed;
    private final String name;

    public Patient(Animal animal, String name) {
        this.animal = animal;
        this.name = name;
        this.servicesUsed = newHashSet();
    }

    public String name() {
        return this.name;
    }

    public void perform(Service service) {
        if (!animal.canHaveRequested(service)) {
            throw new ServiceNotAvailableForAnimalException(service, animal);
        }

        servicesUsed.add(service);
    }

    public Bill bill() {
        return new Bill(this, Maps.asMap(servicesUsed, new Function<Service, Integer>() {
            @Override
            public Integer apply(Service hospitalService) {
                return animal.priceFor(hospitalService);
            }
        }));
    }

    public String animal() {
        return animal.toString();
    }
}
