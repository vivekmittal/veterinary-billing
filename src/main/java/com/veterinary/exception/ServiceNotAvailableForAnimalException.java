package com.veterinary.exception;

import com.veterinary.Animal;
import com.veterinary.MedicalService;

public class ServiceNotAvailableForAnimalException extends RuntimeException {
    public ServiceNotAvailableForAnimalException(MedicalService medicalService, Animal animal) {
        super(
                String.format("Requested MedicalService %s is not available for %s", medicalService, animal)
        );
    }
}
