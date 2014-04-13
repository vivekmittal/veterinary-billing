package com.veterinary.billing;

import com.veterinary.Patient;
import com.veterinary.Service;

import java.util.Map;

public class Bill {
    private final Patient patient;
    private final Map<Service, Integer> services;
    private Integer totalCost;

    public Bill(Patient patient, Map<Service, Integer> services) {
        this.patient = patient;
        this.services = services;
    }

    public int value() {
        if (totalCost == null) {
            totalCost = 0;
            for (Integer price : services.values()) {
                totalCost += price;
            }
        }

        return totalCost;
    }

    public Patient patient() {
        return patient;
    }

    public boolean hasService(Service service) {
        return services.containsKey(service);
    }

    public int costOf(Service service) {
        return services.get(service);
    }

    public String toString() {
        StringBuffer bill = new StringBuffer();

        bill.append(
            String.format("Generating Bill for %s (%s) \n", patient.name(), patient.animal())
        );

        for (Map.Entry<Service, Integer> entry : services.entrySet()) {
            bill.append(
                String.format("%s - %d \n", entry.getKey(), entry.getValue())
            );
        }

        bill.append("Total Value: " + value() + "\n");

        return bill.toString();
    }
}
