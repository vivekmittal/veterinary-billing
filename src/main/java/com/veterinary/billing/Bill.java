package com.veterinary.billing;

import com.veterinary.Hospital;
import com.veterinary.MedicalService;
import com.veterinary.Patient;

import java.util.Map;

public class Bill {
    private final Patient patient;
    private final Map<MedicalService, Integer> services;
    private Integer totalCost;

    public Bill(Patient patient, Map<MedicalService, Integer> services) {
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

    public boolean hasService(MedicalService medicalService) {
        return services.containsKey(medicalService);
    }

    public int costOf(MedicalService medicalService) {
        return services.get(medicalService);
    }

    public String toString() {
        StringBuffer bill = new StringBuffer(Hospital.NAME);
        bill.append("\n--------------------\n");

        bill.append(
            String.format("Bill for %s (%s) \n\n", patient.name(), patient.animal())
        );

        for (Map.Entry<MedicalService, Integer> entry : services.entrySet()) {
            bill.append(
                String.format("%s - %d \n", entry.getKey(), entry.getValue())
            );
        }

        bill.append("Total Value: " + value() + "\n");

        return bill.toString();
    }
}
